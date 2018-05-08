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

public class Login implements Command {

	private static final String ENDPOINT = "/users/login";	

	public static String exec(MessageContext ctx) {
		Integer userId = ctx.user().id();
		ObjectNode user = new ObjectMapper()
				.createObjectNode()
				.put("nick", ctx.firstArg())
				.put("pass", ctx.secondArg());
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<ObjectNode> request = new HttpEntity<>(user, headers);			
			ResponseEntity<ObjectNode> response = new RestTemplate()
					.exchange(API + ENDPOINT, HttpMethod.POST, request, ObjectNode.class);
			if (response.getStatusCode().equals(HttpStatus.CREATED)) {
				String token = response.getBody().get("token").asText();
				RepoToken.getInstance().getTokens().put(userId, token);
				return "Login exitoso.";
			} else {
				return "Login fallido.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Login fallido.";
//			return ERROR;
		}
	}

}

