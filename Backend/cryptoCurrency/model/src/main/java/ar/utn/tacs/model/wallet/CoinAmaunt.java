package ar.utn.tacs.model.wallet;

import java.math.BigDecimal;

import ar.utn.tacs.model.coin.Coin;

public class CoinAmaunt {
	
	private Coin coin;
	private BigDecimal amount;
	
	public CoinAmaunt() {
	}
	
	public CoinAmaunt(Coin coin, BigDecimal amount) {
		this.amount = amount;
		this.coin = coin;
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
}
