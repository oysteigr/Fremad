package fremad.tools;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class GmailSmtp {
	
	private static final Logger LOG = LoggerFactory.getLogger(GmailSmtp.class);
	
	Properties mailServerProperties;
	Session mailSession;
	
	@Autowired
	ConfigProperties configProperties;

	final static String USERNAME = "gmail_userName" ;
	final static String PASSWORD = "gmail_password";

	public GmailSmtp(){
		mailServerProperties = new Properties();
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
		mailServerProperties.put("mail.smtp.port", "587");
		mailSession = Session.getInstance(mailServerProperties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								configProperties.getPropValues(USERNAME),
								configProperties.getPropValues(PASSWORD));
					}
				});
	}
	 
	public void sendEmail(String recipient, String subject, String body) throws AddressException, MessagingException{
		
		LOG.info("\n\n Sending mail to " + recipient + " with subject " + subject);

		Message message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress(configProperties.getPropValues(USERNAME)));
		message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(recipient));
		message.setSubject(subject);
		message.setContent(body, "text/html; charset=utf-8");
	
		Transport.send(message);


		LOG.info("Mail sent");
	}

}