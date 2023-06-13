package com.app.moda.americana.service;

import java.io.IOException;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;


@Service
public class EmailService {
	
	// Usa propiedad del archivo de ambiente
	/*
	@Value("${mail.username}")
	private String mailAccount;
	*/
	
	private final SendGrid sendGrid;
	String apiKey = System.getenv("SENDGRID_API_KEY");
	String siteUrl = "https://modaamericana-production.up.railway.app/";
	
	
    public EmailService() {
        sendGrid = new SendGrid(apiKey);
    }
	
	
	public void sendPromotionEmails(List<String> emailList, String subject, String promotionMessage) throws IOException {
        for (String email : emailList) {
            Email from = new Email("marcojime23@gmail.com");
            Email to = new Email(email);
            
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
                    + "<h1>Promoción Especial- Moda Americana</h1>"
                    + "<p>Estimado cliente,</p>"
                    + "<p>Descubre nuestra última promoción y consigue descuentos exclusivos:</p>"
                    + "<p>" + promotionMessage + "</p><br>"
                    + "<p><a class='button' href='" + siteUrl + "'>Compra Ahora</a></p><br>"
                    + "<p>No te pierdas esta increíble oportunidad!</p>"
                    + "<p>Gracias por elegir Moda Americana.</p>"
                    + "<p><em>Nota: Este es un correo electrónico automatizado, por favor no responda.</em></p>"
                    + "</div>"
                    + "</body>"
                    + "</html>";
            
            Content mailContent = new Content("text/html", content);
            Mail mail = new Mail(from, subject, to, mailContent);
            Request request = new Request();
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sendGrid.api(request);
                System.out.println(response.getStatusCode());
                System.out.println(response.getBody());
                System.out.println(response.getHeaders());
            } catch (IOException ex) {
                throw ex;
            }
        }
    }
	
	

	
}

