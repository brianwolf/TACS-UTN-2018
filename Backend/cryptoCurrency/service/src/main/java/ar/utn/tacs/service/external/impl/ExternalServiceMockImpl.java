package ar.utn.tacs.service.external.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.service.external.ExternalService;

public class ExternalServiceMockImpl extends ExternalServiceImpl{
	
	private static ExternalService EXTERNAL_SERVICE = new ExternalServiceMockImpl();
	
	private List<Coin> coinList = new ArrayList<Coin>();
	
	public static ExternalService getInstance() {
		return EXTERNAL_SERVICE;
	}
	
	public ExternalServiceMockImpl() {
		this.coinList = getCoinMarketCapPosta();
	}

	private List<Coin> getCoinMarketCapPosta() {
		String response = makeRequest("GET",null,COIN_MARKET_CAP_URL);
		
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
		return coinList.stream().filter(coin -> coin.getName().equals(name))
		.findFirst()
		.get();
	}

}
