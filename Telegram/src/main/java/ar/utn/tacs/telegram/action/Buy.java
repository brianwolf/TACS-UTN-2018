package ar.utn.tacs.telegram.action;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.telegram.abilitybots.api.objects.MessageContext;

public class Buy implements Action {

	private static final String ENDPOINT = "/market/buy/{user}/{coin}/{amount}";

	public static String exec(MessageContext ctx) {
		String userId = "X";
		String coin = ctx.firstArg();
		String amount = ctx.secondArg();
		try {
			ResponseEntity<String> response = new RestTemplate()
					.getForEntity(API + ENDPOINT, String.class, userId, coin, amount);
			if (response.getStatusCode().equals(HttpStatus.OK)) {
				return "Se compro " + amount + " " + coin;
			} else {
				return "Usted no posee los fondos necesarios para comprar " + amount + " " + coin;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error al obtener la información.";
		}
	}

}
