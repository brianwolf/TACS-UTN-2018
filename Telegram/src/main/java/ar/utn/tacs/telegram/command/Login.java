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
import ar.utn.tacs.telegram.model.User;

public class Login implements Command {

	private static final String ENDPOINT = "/users/login";	

	public static String exec(MessageContext ctx) {
		Integer userId = ctx.user().id();
		User user = new User();
		user.setNick(ctx.firstArg());
		user.setPass(ctx.secondArg());
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			String body = new ObjectMapper().writeValueAsString(user);
			HttpEntity<String> request = new HttpEntity<>(body, headers);			
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

