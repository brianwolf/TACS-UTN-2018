package ar.utn.tacs.model.wallet;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.codehaus.jackson.annotate.JsonAnyGetter;

import ar.utn.tacs.model.coin.Coin;

public class Wallet {

	private HashMap<Coin, BigDecimal> coinsMap;
	
	public Wallet(HashMap<Coin, BigDecimal> coinsMap) {
		this.coinsMap = coinsMap;
	}
	
	public Wallet() {
		this.coinsMap = new HashMap<Coin, BigDecimal>();
	}
	
	@JsonAnyGetter
	public HashMap<Coin, BigDecimal> getCoinsMap() {
		return coinsMap;
	}

	public void setCoinsMap(HashMap<Coin, BigDecimal> coinsMap) {
		this.coinsMap = coinsMap;
	}

	public void updateCoinsValue(List<Coin> coinsWithUpdatedValue) {
		
		HashMap<Coin, BigDecimal> updatedCoinsMap = new HashMap<Coin, BigDecimal>();
		for (Coin coin : this.coinsMap.keySet()) {
			
			Optional<Coin> optionalCoin = coinsWithUpdatedValue.stream().filter(cmk -> cmk.equals(coin)).findFirst();
			if (optionalCoin.isPresent()) {
				updatedCoinsMap.put(optionalCoin.get(), this.coinsMap.get(coin));
			}
		};
		
		this.coinsMap = updatedCoinsMap;
	}
}
