package neu.edu.controller;

import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class SendRegistrationEmail {
	public SendRegistrationEmail() {
	}
	
	public static void sendEmail(String toEmail) {
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", "smtp-relay.sendinblue.com");
	    properties.setProperty("mail.smtp.port", "587");
	    properties.setProperty("mail.smtp.auth", "true");

	    Session session = Session.getDefaultInstance(properties);

	    try {
	    	 MimeMessage mimeMessage = new MimeMessage(session);
		     mimeMessage.setFrom(new InternetAddress("akumalla.s@northeastern.edu"));
		     mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		     mimeMessage.setSubject("Message Board");
		     mimeMessage.setText("Registration Successfull!!");

		     Transport transport = session.getTransport("smtp");
		     transport.connect("smtp-relay.sendinblue.com", "akumalla.s@northeastern.edu", "xsmtpsib-24732c7f326b94c0cbd67171187a56a72e76b5aed11c88358b72ac02f3a22c9c-LvyHkU3MxKsqhIPm");
		     transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		     transport.close();			
		     System.out.println("Email sent successfully");
		} catch (MessagingException e) {
			System.out.println("Error sending email: " + e.getMessage());
		}
	}

}
