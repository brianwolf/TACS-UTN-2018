package ar.utn.tacs.telegram.action;

import org.telegram.abilitybots.api.objects.MessageContext;

public interface Action {

	public static final String API = "http://localhost:8080/apiWeb";
	
	public static String exec(MessageContext ctx) {
		return ":)";
	}

}
