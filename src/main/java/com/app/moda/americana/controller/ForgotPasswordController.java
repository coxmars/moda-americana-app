package com.app.moda.americana.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.moda.americana.util.Utility;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.app.moda.americana.domain.User;
import com.app.moda.americana.service.UserNotFoundException;
import com.app.moda.americana.service.UserService;

import net.bytebuddy.utility.RandomString;


@Controller
public class ForgotPasswordController {

	private final JavaMailSender mailSender;
	private final UserService userService;

	@Value("${mail.username}")
	private String mailAccount;

	// AAJ - Se puede usar constructor falso con la anotación @Autowired para la
	// inyección de dependencias
	@Autowired
	public ForgotPasswordController(JavaMailSender mailSender, UserService userService) {
		this.mailSender = mailSender;
		this.userService = userService;
	}

	@GetMapping("/forgot_password")
	public String showForgotPasswordForm() {
		return ("authentication/forgot_password_form");
	}

	@PostMapping("/forgot_password")
	public String processForgotPassword(HttpServletRequest request, Model model) throws IOException {
		String email = request.getParameter("email");
		String token = RandomString.make(30);
		// Obtener la URL de la solicitud
		String url = request.getRequestURL().toString();
		String queryString = request.getQueryString();
		if (queryString != null) {
			url += "?" + queryString;
		}

		// Ahora tienes la URL completa con los parámetros, incluido "lan"
		// Ejemplo de URL: "https:/www.modaamericana.com/?lan=en"

		// A continuación, puedes extraer el valor de "lan" de la URL
		String lan = obtenerValorParametro(url, "lan");

		try {
			userService.updateResetPasswordToken(token, email);
			String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
			sendEmail(email, resetPasswordLink);
			model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

		} catch (UserNotFoundException ex) {
			
			model.addAttribute("error", ex.getMessage());
		} catch (UnsupportedEncodingException e) {
			model.addAttribute("error", "Error while sending email");
		}

		return ("authentication/forgot_password_form");
	}

	private String obtenerValorParametro(String url, String parametro) {
		String valor = null;
		int indice = url.indexOf(parametro + "=");

		if (indice != -1) {
			int inicio = indice + parametro.length() + 1;
			int fin = url.indexOf("&", inicio);

			if (fin == -1) {
				fin = url.length();
			}

			valor = url.substring(inicio, fin);
		}

		return valor;
	}
	
	public void sendEmail(String recipientEmail, String link) throws IOException {
		String apiKey = System.getenv("SENDGRID_API_KEY");
	    Email from = new Email(mailAccount);
	    String subject = "Aquí está el enlace para restablecer su contraseña";
	    Email to = new Email(recipientEmail);

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
	            + "<h1>Reestablecer contraseña - Moda Americana</h1>"
	            + "<p>Hola,</p>"
	            + "<p>Ha solicitado restablecer la contraseña de su cuenta de Moda Americana.</p>"
	            + "<p>Para su privacidad y seguridad, haga clic en el botón de abajo para cambiar su contraseña:</p><br>"
	            + "<p><a class='button' href='" + link + "'>Cambiar contraseña</a></p><br>"
	            + "<p>Si no solicitó restablecer su contraseña, ignore este correo electrónico.</p>"
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

	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
		User user = userService.getByResetPasswordToken(token);
		model.addAttribute("token", token);

		if (user == null) {
			model.addAttribute("message", "Invalid Token");
			return "message";
		}

		return ("authentication/reset_password_form");
	}

	@PostMapping("/reset_password")
	public String processResetPassword(HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");

		User user = userService.getByResetPasswordToken(token);
		model.addAttribute("title", "Reset your password");

		if (user == null) {
			model.addAttribute("message", "Invalid Token");
			return "authentication/message";
		} else {
			userService.updatePassword(user, password);
			model.addAttribute("message", "You have successfully changed your password.");
		}
		return "authentication/message";
	}
}
