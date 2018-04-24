package ar.utn.tacs.model.coin;

import java.math.BigDecimal;

public class Coin {

	private Long id;
	private String name;
	private String ticker;
	private BigDecimal valueInDollars;

	public Coin() {
	}

	public Coin(Long id, String name, String ticker) {
		this.id = id;
		this.name = name;
		this.ticker = ticker;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public BigDecimal getValueInDollars() {
		return valueInDollars;
	}

	public void setValueInDollars(BigDecimal valueUsd) {
		this.valueInDollars = valueUsd;
	}


}
