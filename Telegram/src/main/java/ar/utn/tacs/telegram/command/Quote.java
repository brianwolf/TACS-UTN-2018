package ar.utn.tacs.telegram.command;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.telegram.abilitybots.api.objects.MessageContext;

import com.fasterxml.jackson.databind.JsonNode;

public class Quote extends Command {

	private static final String ENDPOINT = "https://api.coinmarketcap.com/v1/ticker";

	public static String exec(MessageContext ctx) {
		String coin = ctx.firstArg();
		try {
			ResponseEntity<JsonNode> response = new RestTemplate().getForEntity(ENDPOINT, JsonNode.class);
			if (response.getStatusCode().equals(HttpStatus.OK)) {
				JsonNode coins = response.getBody();
				for (JsonNode _coin : coins)
					if (_coin.get("id").asText().equals(coin) || _coin.get("name").asText().equals(coin)
							|| _coin.get("symbol").asText().equals(coin.toUpperCase()))
						return String.format("La cotización actual de %s es u$s %.2f.", coin.toUpperCase(),
								_coin.get("price_usd").asDouble());
			}
			return "No se ha encontrado la cryptomoneda " + coin;
		} catch (HttpClientErrorException e) {
			return e.getMessage();
		} catch (HttpServerErrorException e) {
			return e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
