package ar.utn.tacs.telegram.command;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import ar.utn.tacs.telegram.RepoToken;

public abstract class Command {

	public static final String API = "http://localhost:8080/utn/crypto-currency";

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

	public static String getToken(int userId) {
		return RepoToken.getInstance().getTokens().get(userId);
	}

	public static void setToken(int userId, String token) {
		RepoToken.getInstance().getTokens().put(userId, token);
	}

}
