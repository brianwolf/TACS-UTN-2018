package ar.utn.tacs.model.wallet;

import java.math.BigDecimal;
import java.util.List;

import ar.utn.tacs.model.coin.Coin;

public class CoinAmountRest {

	private String ticker;
	private String amount;

	public CoinAmountRest() {
	}

	public CoinAmountRest(String ticker, String amount) {
		this.ticker = ticker;
		this.amount = amount;
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof CoinAmountRest)) {
			return false;
		}

		CoinAmountRest other = (CoinAmountRest) obj;
		return this.ticker.equals(other.ticker) && this.amount.equals(other.amount);
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public CoinAmount toCoinAmount(List<Coin> coins) {
		
		Coin coin = coins.stream().filter(c -> c.getTicker().equals(this.getTicker())).findFirst().get();
		BigDecimal amount = new BigDecimal(this.getAmount());
		
		return new CoinAmount(coin, amount);
	}

}
