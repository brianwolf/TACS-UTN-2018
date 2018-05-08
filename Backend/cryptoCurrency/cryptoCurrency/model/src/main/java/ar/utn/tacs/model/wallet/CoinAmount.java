package ar.utn.tacs.model.wallet;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonIgnore;

import ar.utn.tacs.model.coin.Coin;

public class CoinAmount {
	
	private Coin coin;
	private BigDecimal amount;
	
	public CoinAmount() {
	}
	
	public CoinAmount(Coin coin, BigDecimal amount) {
		this.amount = amount;
		this.coin = coin;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof CoinAmount)) {
			return false;
		}
		
		CoinAmount other = (CoinAmount) obj;
		return this.getCoin().equals(other.getCoin()) && this.getAmount().equals(other.getAmount());
	}

	public Coin getCoin() {
		return coin;
	}

	public void setCoin(Coin coin) {
		this.coin = coin;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	/**
	 * Retorna coin.valueInDollars * amount
	 * 
	 * @return {@link BigDecimal}
	 */
	@JsonIgnore(value=true)
	public BigDecimal getDolarFinalBalance() {
		return this.coin.getValueInDollars().multiply(this.amount);
	}
}
