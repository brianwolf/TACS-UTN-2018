package ar.utn.tacs.service.external.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ar.utn.tacs.model.coin.Coin;

public class CoinBuilder {
	
	public List<Coin> createCoinList(List<Map<String, Object>> mapList) {
		
		List<Coin> coins = new ArrayList<Coin>();
		
		for (Map<String,Object> map : mapList) {
			
			coins.add(createCoin(map));
		}
		
		return coins;
	}

	private Coin createCoin(Map<String, Object> map) {
		
		String name = String.valueOf(map.get("name"));
		String ticker = String.valueOf(map.get("symbol"));
		BigDecimal valueUsd = BigDecimal.valueOf(Double.valueOf((String) map.get("price_usd")));
		
		Coin newCoin = new Coin();
		newCoin.setName(name);
		newCoin.setTicker(ticker);
		newCoin.setValueInDollars(valueUsd);
		
		return newCoin;
	}

}
