package ar.utn.tacs.model.wallet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ar.utn.tacs.model.coin.Coin;

public class Wallet {

	private List<CoinAmount> coinAmounts = new ArrayList<CoinAmount>();
	

	private BigDecimal dolarAmount = new BigDecimal(0);
	
	public Wallet() {
	}
	
	public Wallet(List<CoinAmount> coinAmount, BigDecimal dolarAmount) {
		this.coinAmounts = coinAmount;
		this.dolarAmount = dolarAmount;
	}
	
	public List<CoinAmount> getCoinAmounts() {
		return coinAmounts;
	}
	
	public void setCoinAmounts(List<CoinAmount> coinAmounts) {
		this.coinAmounts = coinAmounts;
	}

	public BigDecimal getDolarAmount() {
		return dolarAmount;
	}

	public void setDolarAmount(BigDecimal dolarAmount) {
		this.dolarAmount = dolarAmount;
	}

	public void updateCoinsValue(List<Coin> coinsWithUpdatedValue) {
		for (CoinAmount coinAmount : this.coinAmounts) {
			
			Optional<Coin> optionalCoin = coinsWithUpdatedValue.stream().filter(cmk -> cmk.equals(coinAmount.getCoin())).findFirst();
			if (optionalCoin.isPresent()) {
				coinAmount.setCoin(optionalCoin.get());
			}
		};
	}
	
	public boolean containsCoin(Coin coin) {
		return this.coinAmounts.stream().anyMatch(coinAmount -> coinAmount.getCoin().equals(coin));
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
		Optional<CoinAmount> optionalCoin = this.coinAmounts.stream().filter(ca -> ca.getCoin().equals(coin)).findFirst();
		return optionalCoin.isPresent()? optionalCoin.get() : null;
	}
	
	public CoinAmount getCoinAmountByTicker(String ticker) {
		Optional<CoinAmount> optionalCoin = this.coinAmounts.stream().filter(ca -> ca.getCoin().getTicker().equals(ticker)).findFirst();
		return optionalCoin.isPresent()? optionalCoin.get() : null;
	}
	
	public BigDecimal getDolarFinalBalance() {
		BigDecimal finalBalance = new BigDecimal(0f);
		
		for (CoinAmount coinAmount : coinAmounts) {
			finalBalance = finalBalance.add(coinAmount.getDolarFinalBalance());
		}
		
		finalBalance = finalBalance.add(this.dolarAmount);
		
		return finalBalance;
	}

	public void updateCoinAmount(CoinAmount coinAmount, BigDecimal amount) {
		if(amount.compareTo(new BigDecimal("0"))==0) {
			coinAmounts.remove(coinAmount);
		}
		else {
			coinAmount.setAmount(amount);
		}
	}
	
}
