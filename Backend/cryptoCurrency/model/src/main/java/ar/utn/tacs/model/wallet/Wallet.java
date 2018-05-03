package ar.utn.tacs.model.wallet;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import ar.utn.tacs.model.coin.Coin;

public class Wallet {

	private List<CoinAmount> coinAmaunts;
	
	private BigDecimal dolarAmount;
	
	public Wallet() {
	}
	
	public Wallet(List<CoinAmount> coinAmaunts, BigDecimal dolarAmount) {
		this.coinAmaunts = coinAmaunts;
		this.dolarAmount = dolarAmount;
	}

	public List<CoinAmount> getCoinAmaunts() {
		return coinAmaunts;
	}

	public void setCoinAmaunts(List<CoinAmount> coinAmaunts) {
		this.coinAmaunts = coinAmaunts;
	}
	
	public BigDecimal getDolarAmount() {
		return dolarAmount;
	}

	public void setDolarAmount(BigDecimal dolarAmount) {
		this.dolarAmount = dolarAmount;
	}

	public void updateCoinsValue(List<Coin> coinsWithUpdatedValue) {
		
		for (CoinAmount coinAmount : this.coinAmaunts) {
			
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
		
		CoinAmount coinAmaunt = this.getCoinAmountByCoin(coin);
		if (coinAmaunt == null) return false;

		return coinAmaunt.getAmount().subtract(amount).doubleValue() >= 0f;
	}
	
	public boolean haveEnoughCoins(String ticker, BigDecimal amount) {
		
		CoinAmount coinAmaunt = this.getCoinAmountByTicker(ticker);
		if (coinAmaunt == null) return false;

		return coinAmaunt.getAmount().subtract(amount).doubleValue() >= 0f;
	}
	
	public boolean haveEnoughDolar(BigDecimal amount) {

		return dolarAmount.subtract(amount).doubleValue() >= 0f;
	}
	
	public CoinAmount getCoinAmountByCoin(Coin coin) {
		Optional<CoinAmount> optionalCoin = this.coinAmaunts.stream().filter(ca -> ca.getCoin().equals(coin)).findFirst();
		return optionalCoin.isPresent()? optionalCoin.get() : null;
	}
	
	public CoinAmount getCoinAmountByTicker(String ticker) {
		Optional<CoinAmount> optionalCoin = this.coinAmaunts.stream().filter(ca -> ca.getCoin().getTicker().equals(ticker)).findFirst();
		return optionalCoin.isPresent()? optionalCoin.get() : null;
	}
	
}
