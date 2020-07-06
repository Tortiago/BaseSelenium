package br.com.tortiago.utils.email;

import static br.com.tortiago.utils.email.ConstEmail.addDetinatarios;
import static br.com.tortiago.utils.CryptoUtils.decryptBase64;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

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

public class EnviaEmail {

	private static Properties getProps() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp do outlook");
		props.put("mail.smtp.socketFactory.port", "25");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "25");
		return props;
	}
	
	public static void addAnexo(MimeBodyPart messageBodyPart, Multipart multipart, String arquivo) throws IOException, MessagingException{
		if(arquivo != null) {
			messageBodyPart = new MimeBodyPart();
			File file = new File(arquivo);
			messageBodyPart.attachFile(file);
			multipart.addBodyPart(messageBodyPart);
		}
	}
	
	public static void enviaeEmail(String corpoMensagem) throws IOException {
		Session session = Session.getDefaultInstance(getProps(), new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ConstEmail.EMAIL, decryptBase64(ConstEmail.SENHA));
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(ConstEmail.EMAIL));			
			message.setRecipients(Message.RecipientType.TO, addDetinatarios(ConstEmail.EMAIL_DEST));
			message.setRecipients(Message.RecipientType.CC, addDetinatarios(ConstEmail.EMAIL_CC));
			message.setSubject(ConstEmail.EMAIL_ASSUNTO);
			
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(corpoMensagem);
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			
			addAnexo(messageBodyPart, multipart, "Caminho do anexo.");

			message.setContent(multipart);
			
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
	
}
