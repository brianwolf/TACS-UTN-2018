package ar.utn.tacs.telegram.action;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.telegram.abilitybots.api.objects.MessageContext;

import ar.utn.tacs.telegram.model.Coin;

public class ActualQuote implements Action {

	private static final String ENDPOINT = "https://api.coinmarketcap.com/v1/ticker/{coin}";

	public static String exec(MessageContext ctx) {
		String coin = ctx.firstArg();
		try {
			ResponseEntity<Coin[]> response = new RestTemplate()
					.getForEntity(ENDPOINT, Coin[].class, coin);
			if (response.getStatusCode().equals(HttpStatus.OK)) {
				Coin c[] = response.getBody();
				return "La cotización actual de " + coin + " es " + c[0].getPrice_usd() + " USD.";
			} else {
				return "Usted no posee " + coin;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error al obtener la información.";
		}
	}

}
