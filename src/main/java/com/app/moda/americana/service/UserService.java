package com.app.moda.americana.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.moda.americana.domain.Role;
import com.app.moda.americana.domain.User;
import com.app.moda.americana.repository.IUserRepository;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import net.bytebuddy.utility.RandomString;

@Service
@Transactional
public class UserService {

	private final IUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JavaMailSender mailSender;

	// Usa propiedad del archivo de ambiente
	@Value("${mail.username}")
	private String mailAccount;

	@Autowired
	public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender mailSender) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.mailSender = mailSender;
	}

	public List<User> listAll() {
		return userRepository.findAll();
	}

	// Metodo para el grafico usuarios por genero
	public List<Map<String, Object>> usersByGender() {
		List<User> users = userRepository.findAll();
		int numHombres = (int) users.stream().filter(user -> "Hombre".equals(user.getGender())).count();
		int numMujeres = (int) users.stream().filter(user -> "Mujer".equals(user.getGender())).count();
		List<Map<String, Object>> usersByGender = new ArrayList<>();
		Map<String, Object> hombre = new HashMap<>();
		hombre.put("gender", "Hombre");
		hombre.put("count", numHombres);
		usersByGender.add(hombre);
		Map<String, Object> mujer = new HashMap<>();
		mujer.put("gender", "Mujer");
		mujer.put("count", numMujeres);
		usersByGender.add(mujer);
		return usersByGender;
	}

	public User getOne(Long id) {
		return userRepository.findById(id).get();
	}

	public void register(User user, String siteURL) throws MessagingException, IOException {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		// Agrega un rol de tipo usuario ROLE_USER, para administrador seria ROLE_ADMIN
		// pero quemado en BD solo se ocupa 1
		// el resto de usuarios registrados por defecto tendrán el rol tipo usuario.
		List<Role> roles = new ArrayList<>();
		Role userRole = new Role();
		userRole.setAuthority("ROLE_USER");
		roles.add(userRole);
		user.setRoles(roles);

		String randomCode = RandomString.make(64);
		user.setVerificationCode(randomCode);
		user.setEnabled(false);
		userRepository.save(user);
		sendVerificationEmail(user, siteURL);
	}

	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}

	public void updateUser(User user) {

		if (user == null) {
			throw new IllegalArgumentException("El objeto User no puede ser nulo.");
		}
		if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
			throw new IllegalArgumentException("El campo Email es obligatorio.");
		}
		if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
			throw new IllegalArgumentException("El campo Password es obligatorio.");
		}
		if (user.getRoles() == null || user.getRoles().isEmpty()) {
			throw new IllegalArgumentException("El campo Roles es obligatorio.");
		}
		if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
			throw new IllegalArgumentException("El campo First Name es obligatorio.");
		}
		if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
			throw new IllegalArgumentException("El campo Last Name es obligatorio.");
		}
		if (user.getIdentificationType() == null || user.getIdentificationType().trim().isEmpty()) {
			throw new IllegalArgumentException("El campo Identification Type es obligatorio.");
		}
		if (user.getIdentification() == null || user.getIdentification().trim().isEmpty()) {
			throw new IllegalArgumentException("El campo Identification es obligatorio.");
		}
		if (user.getGender() == null || user.getGender().trim().isEmpty()) {
			throw new IllegalArgumentException("El campo Gender es obligatorio.");
		}
		if (user.getPhoneNumber() == null || user.getPhoneNumber().trim().isEmpty()) {
			throw new IllegalArgumentException("El campo Phone Number es obligatorio.");
		}

		userRepository.save(user);

	}

	private void sendVerificationEmail(User user, String siteURL) throws IOException {
		String apiKey = System.getenv("SENDGRID_API_KEY");
		Email from = new Email(mailAccount);
		String subject = "Por favor verifica tu registro";
		Email to = new Email(user.getEmail());

		String content = "<html>"
	            + "<head>"
	            + "<style>"
	            + "body { font-family: Arial, sans-serif; background-color: #f6f6f6; padding: 20px; }"
	            + ".container { max-width: 600px; margin: 0 auto; background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1); }"
	            + "h1 { font-size: 24px; margin-bottom: 20px; }"
	            + "p { margin-bottom: 10px; }"
	            + "a { color: #fff !important; background-color: #007bff; padding: 10px 20px; text-decoration: none !important; border-radius: 5px; }"
	            + "</style>"
	            + "</head>"
	            + "<body>"
	            + "<div class='container'>"
	            + "<h1>Verifique su cuenta - Moda Americana</h1>"
	            + "<p>Hola " + user.getFullName() + ",</p>"
	            + "<p>Por favor, haga clic en el botón de abajo para verificar su registro:</p><br>"
	            + "<p><a class='button' href='" + siteURL + "/verify?code=" + user.getVerificationCode() + "'>Verificar Cuenta</a></p><br>"
	            + "<p>Si no se registró para obtener una cuenta, ignore este correo electrónico.</p>"
	            + "<p>Gracias,</p>"
	            + "<p>El equipo de Moda Americana</p>"
	            + "<p><em>Nota: Este es un correo electrónico automatizado, por favor no responda.</em></p>"
	            + "</div>"
	            + "</body>"
	            + "</html>";

		Content mailContent = new Content("text/html", content);
		Mail mail = new Mail(from, subject, to, mailContent);

		SendGrid sg = new SendGrid(apiKey);
		Request request = new Request();

		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());

			Response response = sg.api(request);
			System.out.println("Email has been sent. Response code: " + response.getStatusCode());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public boolean verify(String verificationCode) {
		User user = userRepository.findFirstByVerificationCode(verificationCode);
		if (user == null || user.isEnabled()) {
			return false;
		} else {
			user.setVerificationCode(null);
			user.setEnabled(true);
			userRepository.save(user);
			return true;
		}
	}

	public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
		User user = userRepository.findFirstByEmail(email);
		if (user != null) {
			user.setResetPasswordToken(token);
			userRepository.save(user);
		} else {
			throw new UserNotFoundException("Could not find any user with the email " + email);
		}
	}

	public User getByResetPasswordToken(String token) {
		return userRepository.findByResetPasswordToken(token);
	}

	public void updatePassword(User user, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(newPassword);
		user.setPassword(encodedPassword);
		user.setResetPasswordToken(null);
		userRepository.save(user);
	}

}