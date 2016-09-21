package utility;

import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import enums.etc.UtilityEnums;


// 이메일을 보내는 클래스.
// 사용법 : new SendEmail(String인증번호, 사용자email); 
// 발송되는 이메일 제목을 바꾸고 싶다면 UtilityEnums 에서 SENDER_EMAIL_SUBJECT 변경하면 됨.
public class SendEmail {
	public SendEmail(String certification, String userEmail) {
		try {
			Properties prop = new Properties();
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.host", "smtp.gmail.com");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.port", UtilityEnums.SENDER_EMAIL_PORT.getStr());
			prop.put("mail.smtp.socketFactory.port", UtilityEnums.SENDER_EMAIL_PORT.getStr());
			prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			prop.put("mail.smtp.socketFactory.fallback", "false");
			prop.setProperty("mail.smtp.quitwait", "false");
			
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(UtilityEnums.SENDER_EMAIL_ADDRESS.getStr(), UtilityEnums.SENDER_EMAIL_PASSWD.getStr());
				}
			};
			
			Session session = Session.getDefaultInstance(prop, auth);
			MimeMessage message = new MimeMessage(session);
			message.setSender(new InternetAddress(UtilityEnums.SENDER_EMAIL_ADDRESS.getStr()));
			message.setSubject(UtilityEnums.SENDER_EMAIL_SUBJECT.getStr());
			
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
