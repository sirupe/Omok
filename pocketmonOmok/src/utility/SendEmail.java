package utility;

import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


// 이메일을 보내는 클래스.
// 사용법 : new SendEmail(String인증번호, 사용자email); 
public class SendEmail {
	public SendEmail(String certification, String userEmail) throws AddressException, MessagingException {
		String emailPort = "465";
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.host", "smtp.gmail.com");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", emailPort);
		prop.put("mail.smtp.socketFactory.port", emailPort);
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.put("mail.smtp.socketFactory.fallback", "false");
		prop.setProperty("mail.smtp.quitwait", "false");
		
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("jijungsinkim@gmail.com", "dpswpflsjtm");
			}
		};
		
		Session session = Session.getDefaultInstance(prop, auth);
		MimeMessage message = new MimeMessage(session);
		message.setSender(new InternetAddress("jijungsinkim@gmail.com"));
		message.setSubject("포켓못 오목에서 발송한 인증번호입니다.");
		
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
		Multipart multiPart = new MimeMultipart();
		
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setText(certification);
		multiPart.addBodyPart(mimeBodyPart);
		
		MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
		mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
		mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
		mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		CommandMap.setDefaultCommandMap(mc);

		message.setContent(multiPart);
		
		Transport.send(message);
	}
}
