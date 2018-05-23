package ar.utn.tacs.telegram.command;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import ar.utn.tacs.telegram.RepoToken;

public abstract class Command {

	public static final String API = "https://utn-crypto-currency-rest.herokuapp.com/utn/crypto-currency";

	public static <T> HttpEntity<T> getRequest(T body, int userId) {
		return new HttpEntity<T>(body, getHeaders(userId));
	}

	public static HttpHeaders getHeaders(int userId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		if (getToken(userId) != null)
			headers.add("token", getToken(userId));
		return headers;
	}

	public static String removeToken(int userId) {
		return RepoToken.getInstance().getTokens().remove(userId);
	}

	public static String getToken(int userId) {
		return RepoToken.getInstance().getTokens().get(userId);
	}

	public static void setToken(int userId, String token) {
		RepoToken.getInstance().getTokens().put(userId, token);
	}

	public static String handleError(Exception error, int userId) {
		error.printStackTrace();
		if (error.getClass().equals(HttpClientErrorException.class)) {
			HttpStatus httpStatusCode = ((HttpClientErrorException) error).getStatusCode();
			switch (httpStatusCode) {
			case UNAUTHORIZED:
				removeToken(userId);
				return "Debe iniciar sesión para operar.";
			default:
				return error.getMessage();
			}
		} else if (error.getClass().equals(HttpServerErrorException.class)) {
			HttpStatus httpStatusCode = ((HttpServerErrorException) error).getStatusCode();
			switch (httpStatusCode) {
			case INTERNAL_SERVER_ERROR:
				return "La operación NO se pudo completar.";
			default:
				return error.getMessage();
			}
		}
		return "Ha ocurrido un error, contacte al administrador";
	}

}
