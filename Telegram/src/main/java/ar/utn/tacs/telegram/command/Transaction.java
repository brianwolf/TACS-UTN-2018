package ar.utn.tacs.telegram.command;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.telegram.abilitybots.api.objects.MessageContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ar.utn.tacs.telegram.RepoToken;

public class Transaction implements Command {

	public static String exec(MessageContext ctx, String endpoint) {
		Integer userId = ctx.user().id();
		String token = RepoToken.getInstance().getTokens().get(userId);
		if (token == null)
			return "Debe iniciar sesión antes de operar.";
		ObjectNode operation = new ObjectMapper()
				.createObjectNode()
				.put("ticker", ctx.firstArg().toUpperCase())
				.put("amount", ctx.secondArg());
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("token", token);
			HttpEntity<ObjectNode> request = new HttpEntity<>(operation, headers);
			ResponseEntity<String> response = new RestTemplate()
					.exchange(API + endpoint, HttpMethod.POST, request, String.class);
			if (response.getStatusCode().equals(HttpStatus.CREATED))
				return "Transacción exitosa.";
			else if (response.getStatusCode().equals(HttpStatus.UNAUTHORIZED))
				return "Debe iniciar sesión antes de operar.";
			else
				return "Transaccion fallida. Chequeé el ticker o si dispone de saldos.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Transaccion fallida. Chequeé el ticker o si dispone de saldos.";
			// return ERROR;
		}
	}

}
