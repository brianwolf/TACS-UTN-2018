package ar.utn.tacs.telegram.command;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.telegram.abilitybots.api.objects.MessageContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Login extends Command {

	private static final String ENDPOINT = "/users/login";

	public static ObjectNode getBody(MessageContext ctx) {
		return new ObjectMapper()
				.createObjectNode()
				.put("nick", ctx.firstArg())
				.put("pass", ctx.secondArg());
	}

	public static String exec(MessageContext ctx) {
		int userId = ctx.user().id();
		try {
			ResponseEntity<ObjectNode> response = new RestTemplate().exchange(API + ENDPOINT, HttpMethod.POST,
					getRequest(getBody(ctx), userId), ObjectNode.class);
			setToken(userId, response.getBody().get("token").asText());
			return "Login exitoso.";
		} catch (Exception e) {
			return handleError(e, userId);
		}
	}

}
