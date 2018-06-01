package ar.utn.tacs.model.coin;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"id"})
public class Coin {

	private BigInteger id;
	private String name;
	private String ticker;
	private BigDecimal valueInDollars = new BigDecimal(0);

	public Coin() {
	}

	public Coin(BigInteger id, String name, String ticker) {
		this.id = id;
		this.name = name;
		this.ticker = ticker;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Coin)) {
			return false;
		}

		Coin other = (Coin) obj;
		return this.getTicker().equals(other.getTicker());
	}
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
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
