package ar.utn.tacs.telegram.action;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.telegram.abilitybots.api.objects.MessageContext;

public class Amount implements Action {

	private static final String ENDPOINT = "/users/amount/{user}/{coin}";

	public static String exec(MessageContext ctx) {
		String userId = "X";
		String coin = ctx.firstArg();
		try {
			ResponseEntity<String> response = new RestTemplate()
					.getForEntity(API + ENDPOINT, String.class, userId, coin);
			if (response.getStatusCode().equals(HttpStatus.OK)) {
				String body = response.toString();
				return "Usted posee "+body+" de la cryptomoneda "+coin;
			} else {
				return "Usted no posee la cryptomoneda "+coin;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error al obtener la información.";
		}
	}

}
