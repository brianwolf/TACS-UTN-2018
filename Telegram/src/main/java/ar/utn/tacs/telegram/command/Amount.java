package ar.utn.tacs.telegram.command;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.telegram.abilitybots.api.objects.MessageContext;

import com.fasterxml.jackson.databind.JsonNode;

public class Amount extends Command {

	private static final String ENDPOINT = "/wallet?ticker={coin}";

	public static String exec(MessageContext ctx) {
		int userId = ctx.user().id();
		if (getToken(userId) == null)
			return "Debe iniciar sesión antes de operar.";
		String coin = ctx.firstArg().toUpperCase();
		try {
			ResponseEntity<JsonNode> response = new RestTemplate().exchange(API + ENDPOINT, HttpMethod.GET,
					getRequest(null, userId), JsonNode.class, coin);
			if (response.getBody() == null)
				return String.format("Usted no poseé la cryptomoneda %s.", coin);
			JsonNode amount = response.getBody().get("amount");
			return String.format("Usted poseé %s de la cryptomoneda %s.", amount, coin);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch (HttpServerErrorException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

}
