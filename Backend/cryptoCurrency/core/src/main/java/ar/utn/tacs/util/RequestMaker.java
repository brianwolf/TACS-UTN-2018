package ar.utn.tacs.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

public class RequestMaker {

	public static final String GET_METHOD = "GET";
	public static final String POST_METHOD = "POST";
	public static final String PUT_METHOD = "PUT";

	private static Gson gson = new Gson();

	public static String makeRequest(String method, Object body, String url) {

		try {
			URI uri = new URI(url);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");

			HttpEntity<String> getEntity = new HttpEntity<String>(gson.toJson(body), headers);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.exchange(uri, getHttpMethod(method), getEntity, String.class);

//			System.out.println("\nSTATUS : " + response.getStatusCode() + " " + response.getStatusCode().getReasonPhrase());
//			System.out.println("Response :" + response.getBody());

			return response.hasBody() ? response.getBody() : null;
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return  null;
		} catch (InvalidMediaTypeException e) {
			e.printStackTrace();
			return  null;
		}
	}

	private static HttpMethod getHttpMethod(String method) {
		return HttpMethod.resolve(method);
	}
}
