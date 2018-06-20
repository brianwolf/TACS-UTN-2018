package ar.utn.tacs.model.coin;

import java.util.ArrayList;
import java.util.List;

public class UpdatedCoinsPrice {
	
	public static String COIN_MARKET_CAP_URL = "https://api.coinmarketcap.com/v1/ticker/";

	private static List<Coin> coinsList = new ArrayList<Coin>();
	
	private UpdatedCoinsPrice() {
	}
	
	public static List<Coin> getCoinsList() {
		return coinsList;
	}

	public static void setCoinsList(List<Coin> coinsList) {
		UpdatedCoinsPrice.coinsList = coinsList;
	}
}
