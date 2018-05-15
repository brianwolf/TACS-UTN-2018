package ar.utn.tacs.telegram.command;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.telegram.abilitybots.api.objects.MessageContext;

public class Logout extends Command {

	private static final String ENDPOINT = "/users/logout";

	public static String exec(MessageContext ctx) {
		int userId = ctx.user().id();
		if (getToken(userId) == null)
			return "No tiene sesión activa en el servidor.";
		try {
			new RestTemplate().put(API + ENDPOINT, getRequest(null, userId));
			removeToken(userId);
			return "Ha terminado su sesión en el servidor.";
		} catch (HttpClientErrorException e) {
			removeToken(userId);
			e.printStackTrace();
			return "No tiene sesión activa en el servidor.";
		} catch (HttpServerErrorException e) {
			e.printStackTrace();
			return "No tiene sesión activa en el servidor.";
		} catch (Exception e) {
			e.printStackTrace();
			return "No tiene sesión activa en el servidor.";
		}
	}

}