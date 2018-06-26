package ar.utn.tacs.model.email;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.model.deposit.Deposit;
import ar.utn.tacs.model.user.User;

public class MailBuilder {
	
	private static final String DEPOSIT_CHANGE_SUBJECT = "Estado de deposito {numero_deposito}";
	private static final String DEPOSIT_CHANGE_BODY = "Estimado {nick}, el deposito {numero_deposito} de monto {monto_deposito} ha sido {estado_deposito}";
	private static String UTN_TACS_CRYPTO_USERNAME="utn.tacs.crypto.currency@gmail.com";
	private static String UTN_TACS_CRYPTO_PASSWORD="UTNTACS1234";
	private static String UTN_TACS_CRYPTO_EMAIL="utn.tacs.crypto.currency@gmail.com";
	private static String RELOG_BODY="Estimado {nick}, su nuevo password es {nuevo_password}. No sea manco y recuerdelo para la proxima. ";
	private static String RELOG_SUBJECT="Cambio de password Utn Tacs Crypto";

	public static Mail buildRelogMail(User user,String newPassword) throws UtnTacsException {
		
		if(user.getPerson().getEmail()==null||user.getPerson().getEmail().isEmpty()) {
			throw new UtnTacsException();
		}
		

		Mail mail = createDefaultMail();
		mail.setTo(user.getPerson().getEmail());
		mail.setSubject(RELOG_SUBJECT);
		mail.setBody(RELOG_BODY.replace("{nick}", user.getLogin().getNick()).replace("{nuevo_password}", newPassword));
		
		return mail;
	}

	private static Mail createDefaultMail() {
		Mail mail = new Mail();
		mail.setFrom(UTN_TACS_CRYPTO_EMAIL);
		mail.setSenderUsername(UTN_TACS_CRYPTO_USERNAME);
		mail.setSenderPassword(UTN_TACS_CRYPTO_PASSWORD);
		return mail;
	}

	public static Mail buildDepositMail(Deposit deposit) {
		Mail mail = createDefaultMail();
		mail.setTo(deposit.getUser().getPerson().getEmail());
		
		String depositNumber = deposit.getNumber();
		String depositAmount = String.valueOf(deposit.getAmount());
		String depositState = deposit.getUserState();
		
		mail.setSubject(DEPOSIT_CHANGE_SUBJECT.replace("{numero_deposito}", depositNumber));
		mail.setBody(DEPOSIT_CHANGE_BODY.replace("{nick}", deposit.getUser().getLogin().getNick())
				.replace("{numero_deposito}", depositNumber)
				.replace("{monto_deposito}", depositAmount)
				.replace("{estado_deposito}", depositState));
		
		return mail;
		
	}

}
