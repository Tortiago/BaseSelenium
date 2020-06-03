package br.com.tortiago.utils.email;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ConstEmail {

	public static final String
		
		// REMETENTE
		EMAIL			= "tortiago.eu@hotmail.com",
		SENHA			= "Senha criptografada :-)",
		
		// DESTINATARIOS
		EMAIL_DEST		= "",
		
		// DESTINATRIOS EM COPIA
		EMAIL_CC		= "",
		
		// ASSUNTO
		EMAIL_ASSUNTO	= "";
		

	public static Address[] addDetinatarios(String lista) throws AddressException {
		Address[] EMAIL_DESTINATARIOS = InternetAddress.parse(lista);
		return EMAIL_DESTINATARIOS;
	}
		
}
