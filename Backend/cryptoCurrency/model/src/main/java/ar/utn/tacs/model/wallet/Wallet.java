package ar.utn.tacs.model.wallet;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import ar.utn.tacs.model.coin.Coin;

public class Wallet {

	private List<CoinAmaunt> coinAmaunts;
	
	public Wallet() {
		
	}
	
	public Wallet(List<CoinAmaunt> coinAmaunts) {
		this.coinAmaunts = coinAmaunts;
	}

	public List<CoinAmaunt> getCoinAmaunts() {
		return coinAmaunts;
	}

	public void setCoinAmaunts(List<CoinAmaunt> coinAmaunts) {
		this.coinAmaunts = coinAmaunts;
	}

	public void updateCoinsValue(List<Coin> coinsWithUpdatedValue) {
		
		for (CoinAmaunt coinAmount : this.coinAmaunts) {
			
			Optional<Coin> optionalCoin = coinsWithUpdatedValue.stream().filter(cmk -> cmk.equals(coinAmount.getCoin())).findFirst();
			if (optionalCoin.isPresent()) {
				coinAmount.setCoin(optionalCoin.get());
			}
		};
	}
	
	public boolean containsCoin(Coin coin) {
		return this.coinAmaunts.stream().anyMatch(coinAmount -> coinAmount.getCoin().equals(coin));
	}
	
	public boolean haveEnoughCoins(Coin coin, BigDecimal amount) {
		
		CoinAmaunt coinAmaunt = this.getCoinAmountByCoin(coin);
		if (coinAmaunt == null) return false;

		return coinAmaunt.getAmount().subtract(amount).doubleValue() >= 0f;
	}
	
	public boolean haveEnoughCoins(String ticker, BigDecimal amount) {
		
		CoinAmaunt coinAmaunt = this.getCoinAmountByTicker(ticker);
		if (coinAmaunt == null) return false;

		return coinAmaunt.getAmount().subtract(amount).doubleValue() >= 0f;
	}
	
	public CoinAmaunt getCoinAmountByCoin(Coin coin) {
		Optional<CoinAmaunt> optionalCoin = this.coinAmaunts.stream().filter(ca -> ca.getCoin().equals(coin)).findFirst();
		return optionalCoin.isPresent()? optionalCoin.get() : null;
	}
	
	public CoinAmaunt getCoinAmountByTicker(String ticker) {
		Optional<CoinAmaunt> optionalCoin = this.coinAmaunts.stream().filter(ca -> ca.getCoin().getTicker().equals(ticker)).findFirst();
		return optionalCoin.isPresent()? optionalCoin.get() : null;
	}
}
