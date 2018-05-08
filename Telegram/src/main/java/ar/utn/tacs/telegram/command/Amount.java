package ar.utn.tacs.telegram.command;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.telegram.abilitybots.api.objects.MessageContext;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ar.utn.tacs.telegram.RepoToken;

public class Amount implements Command {

	private static final String ENDPOINT = "/wallet";

	public static String exec(MessageContext ctx) {
		Integer userId = ctx.user().id();
		String token = RepoToken.getInstance().getTokens().get(userId);
		if (token == null)
			return "Debe iniciar sesión antes de operar.";
		String coin = ctx.firstArg();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("token", token);
			ResponseEntity<ObjectNode> response = new RestTemplate()
					.exchange(API + ENDPOINT, HttpMethod.GET, new HttpEntity<>(headers), ObjectNode.class);
			if (response.getStatusCode().equals(HttpStatus.OK)) {
				JsonNode amount = response.getBody().get("amount");
				return String.format("Usted posee %s de la cryptomoneda %s.", amount, coin);
			} else
				return "Usted no posee la cryptomoneda " + coin;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error al obtener la información.";
		}
	}

}
