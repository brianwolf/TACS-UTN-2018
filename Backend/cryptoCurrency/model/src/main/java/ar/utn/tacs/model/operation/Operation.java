package ar.utn.tacs.model.operation;

import java.math.BigDecimal;

import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.user.User;

public abstract class Operation {

	protected Long id;
	protected String description;
	protected Coin coin;
	protected User user;
	protected BigDecimal amount;
	
	protected Operation(Long id, String description) {
		this.id = id;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public abstract void doOperation();

	public void setCoin(Coin coin) {
		this.coin = coin;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Coin getCoin() {
		return coin;
	}
	

}
