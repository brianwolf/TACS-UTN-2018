package ar.utn.tacs.service.external.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import ar.utn.tacs.dao.external.ExternalDao;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.service.external.ExternalService;


public class ExternalServiceImpl implements ExternalService{
	
	protected static String COIN_MARKET_CAP_URL = "https://api.coinmarketcap.com/v1/ticker/";
	
	
	@SuppressWarnings("unused")
	@Autowired
	private ExternalDao externalDao;
	
	private Gson gson = new Gson();
	
	
	@Override
	public List<Coin> coinMarketCap() {
		
		return null;
	}
	
	protected String makeRequest(String method,Object objeto,String url ) {
		
		try {
			String urlMail = url;
			URI uri = new URI(urlMail);

			HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Type", "application/json");
	        headers.add("Accept", "application/json");
	        
	        HttpEntity<String> getEntity = new HttpEntity<String>(gson.toJson(objeto), headers);
	        
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> response =restTemplate.exchange(uri, getHttpMethod(method),getEntity,String.class);

	        System.out.println("\nSTATUS : "+ response.getStatusCode()+" "+response.getStatusCode().getReasonPhrase());
	        System.out.println("Response :"+ response.getBody());
	        
	        return response.hasBody() ? response.getBody() : null;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvalidMediaTypeException e) {
			e.printStackTrace();
		}
		return null;
	}

	private HttpMethod getHttpMethod(String method) {
		
		return HttpMethod.resolve(method);
	}

	@Override
	public Coin getCoinByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Coin getCoinByTicker(String ticker) {
		// TODO Auto-generated method stub
		return null;
	}

}
