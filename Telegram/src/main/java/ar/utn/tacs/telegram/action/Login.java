package ar.utn.tacs.telegram.action;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.telegram.abilitybots.api.objects.MessageContext;

import ar.utn.tacs.telegram.model.User;

public class Login implements Action {

	private static final String ENDPOINT = "/users/login";

	public static String exec(MessageContext ctx) {
		User user = new User();
		user.setName(ctx.firstArg());
		user.setPass(ctx.secondArg());
		try {
			HttpEntity<User> request = new HttpEntity<>(user);
			ResponseEntity<User> response = new RestTemplate()
					.exchange(API + ENDPOINT, HttpMethod.POST, request, User.class);
			if (response.getStatusCode().equals(HttpStatus.OK)) {
				return "Login exitoso.";
			} else {
				return "Login fallido.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error al ingresar al sistema.";
		}
	}

}
