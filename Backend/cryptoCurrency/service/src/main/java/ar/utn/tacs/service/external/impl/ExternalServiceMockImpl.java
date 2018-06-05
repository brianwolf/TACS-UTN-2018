package ar.utn.tacs.service.external.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.email.Mail;
import ar.utn.tacs.service.external.ExternalService;

public class ExternalServiceMockImpl implements ExternalService {

	protected static String COIN_MARKET_CAP_URL = "https://api.coinmarketcap.com/v1/ticker/";

	private List<Coin> coinList = new ArrayList<Coin>();
	
	private Gson gson = new Gson();


	public ExternalServiceMockImpl() {
		this.coinList = getCoinMarketCapPosta();
	}

	protected String makeRequest(String method, Object objeto, String url) {

		try {
			String urlMail = url;
			URI uri = new URI(urlMail);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");

			HttpEntity<String> getEntity = new HttpEntity<String>(gson.toJson(objeto), headers);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.exchange(uri, getHttpMethod(method), getEntity,
					String.class);

			System.out.println(
					"\nSTATUS : " + response.getStatusCode() + " " + response.getStatusCode().getReasonPhrase());
			System.out.println("Response :" + response.getBody());

			return response.hasBody() ? response.getBody() : null;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidMediaTypeException e) {
			e.printStackTrace();
		}
		return null;
	}

	private HttpMethod getHttpMethod(String method) {

		return HttpMethod.resolve(method);
	}

	private List<Coin> getCoinMarketCapPosta() {
		String response = makeRequest("GET", null, COIN_MARKET_CAP_URL);

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> mapResult = gson.fromJson(response, List.class);

		CoinBuilder coinBuilder = new CoinBuilder();

		return coinBuilder.createCoinList(mapResult);
	}

	@Override
	public List<Coin> coinMarketCap() {
		return this.coinList;
	}

	@Override
	public Coin getCoinByName(String name) {
		return coinList.stream().filter(coin -> coin.getName().equals(name)).findFirst().get();
	}

	@Override
	public Coin getCoinByTicker(String ticker) {
		return coinList.stream().filter(coin -> coin.getTicker().equals(ticker)).findFirst().get();
	}

	@Override
	public Boolean sendMail(Mail mail) {
		// TODO Auto-generated method stub
		return null;
	}

}
