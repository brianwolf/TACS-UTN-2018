package ar.utn.tacs.telegram.command;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.telegram.abilitybots.api.objects.MessageContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Transaction extends Command {

	public static ObjectNode getBody(MessageContext ctx) {
		return new ObjectMapper()
				.createObjectNode()
				.put("ticker", ctx.firstArg().toUpperCase())
				.put("amount", ctx.secondArg());
	}

	public static String exec(MessageContext ctx, String endpoint) {
		int userId = ctx.user().id();
		if (getToken(userId) == null)
			return "Debe iniciar sesión antes de operar.";
		ObjectNode body = getBody(ctx);
		if (body.get("amount").asDouble() <= 0)
			return "Debe ingresar una cantidad positiva.";
		try {
			new RestTemplate().exchange(API + endpoint, HttpMethod.POST, getRequest(body, userId), ObjectNode.class);
			return "Transacción exitosa.";
		} catch (Exception e) {
			return handleError(e, userId);
		}
	}

}
