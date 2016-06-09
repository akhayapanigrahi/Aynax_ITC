package com.itc.util;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.ITestResult;

public class ReportSending {
	
	public static void execute(String fromMail,String tomail)
	{
	Properties props = new Properties();
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.socketFactory.port", "465");
	props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.port", "465");
	Session session = Session.getDefaultInstance(props,
	new javax.mail.Authenticator() {
	protected PasswordAuthentication getPasswordAuthentication() {
	return new PasswordAuthentication("testaynaxdemo@gmail.com","testaynax@1");
	}
	});
	try {
	MimeMessage message = new MimeMessage(session);
	message.setFrom(new InternetAddress(fromMail));
	message.setRecipients(Message.RecipientType.TO,
	InternetAddress.parse(tomail));
	message.setSubject("Script Status");
	MimeBodyPart messageBodyPart = new MimeBodyPart();
	messageBodyPart.setText("PFA");

	Multipart multipart = new MimeMultipart();
	multipart.addBodyPart(messageBodyPart);

	messageBodyPart = new MimeBodyPart();
	String emailPath = System.getProperty("user.dir")+"\\test-output"+"\\emailable-report.html";
	String fileName = "TestNG_Report.html";
	DataSource source = new FileDataSource(emailPath);
	messageBodyPart.setDataHandler(new DataHandler(source));
	messageBodyPart.setFileName(fileName);
	multipart.addBodyPart(messageBodyPart);
	message.setContent(multipart);

	System.out.println("Sending mail to Respective mailer");
	Transport.send(message);
	System.out.println("Mail sent ...Please check your inbox..");
	} catch (MessagingException ex) {
	throw new RuntimeException(ex);
	}
	}
	 public void onTestFailure(ITestResult result) {

         //returns the test which failed
         result.getMethod().getMethodName();

         //create an object of the send mail class and call the method which send the mail and pass the test name which failed as an argument to it.

     }
	}