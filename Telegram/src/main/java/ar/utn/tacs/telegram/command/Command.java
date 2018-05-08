package ar.utn.tacs.telegram.command;

import org.telegram.abilitybots.api.objects.MessageContext;

public interface Command {

	public static final String API = "http://localhost:8080/utn/crypto-currency";
	public static final String ERROR = "ERROR: No se pudo conectar sistema.";

	public static String exec(MessageContext ctx) {
		return ":)";
	}

}
