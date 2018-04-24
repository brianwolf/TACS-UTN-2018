package ar.utn.tacs.service.external.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ar.utn.tacs.model.coin.Coin;

public class ExternalServiceMockImpl extends ExternalServiceImpl{
	
	private List<Coin> coinList = new ArrayList<Coin>();
	
	public ExternalServiceMockImpl() {
		this.coinList = getCoinMarketCapPosta();
	}

	private List<Coin> getCoinMarketCapPosta() {
		String response = makeRequest("GET",null,COIN_MARKET_CAP_URL);
		
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
