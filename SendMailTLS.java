package com.xo.mail;
/**
 * @author Amit Sahu
 * 
 */
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTLS {

	public static void main(String[] args) {

		final String username = "YOUREMAIL@gmail.com";
		final String password = "YOURPASSWORD";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("YOUREMAIL@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("RECEIVER@gmail.com"));
			message.setSubject("SUBJECT LINE");
			message.setText("CONTENT FIRST LINE"
				+ "\n\n Don't mess with an Engineer");
			for (int i = 0; i < 100; i++) 
				
			{
				

				Transport.send(message);

				System.out.println("Done");
			}
			

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
