package ar.utn.tacs.model.email;

import ar.utn.tacs.model.user.User;

public class MailBuilder {
	
	private static String UTN_TACS_CRYPTO_USERNAME="utn.tacs.crypto.currency@gmail.com";
	private static String UTN_TACS_CRYPTO_PASSWORD="UTNTACS1234";
	private static String UTN_TACS_CRYPTO_EMAIL="utn.tacs.crypto.currency@gmail.com";
	private static String RELOG_BODY="Estimado {nick}, su nuevo password es {nuevo_password}. No sea manco y recuerdelo para la proxima. ";
	private static String RELOG_SUBJECT="Cambio de password Utn Tacs Crypto";

	public static Mail buildRelogMail(User user,String newPassword) {
		
		if(user.getPerson().getEmail()==null||user.getPerson().getEmail().isEmpty()) {
			throw new RuntimeException();
		}
		
		Mail mail = new Mail();
		mail.setFrom(UTN_TACS_CRYPTO_EMAIL);
		mail.setTo(user.getPerson().getEmail());
		mail.setSubject(RELOG_SUBJECT);
		mail.setBody(RELOG_BODY.replace("{nick}", user.getLogin().getNick()).replace("{nuevo_password}", newPassword));
		mail.setSenderUsername(UTN_TACS_CRYPTO_USERNAME);
		mail.setSenderPassword(UTN_TACS_CRYPTO_PASSWORD);
		
		return mail;
	}

}
