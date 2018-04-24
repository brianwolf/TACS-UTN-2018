package ar.utn.tacs.model.transaction;

import java.math.BigDecimal;

import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.operation.Operation;
import ar.utn.tacs.model.user.User;

public class Transaction {

	Long id;
	private User user;
	private Coin coin;
	private Operation operation;
	BigDecimal amount;
	BigDecimal quoteTimeSold;
	BigDecimal quoteTimeNow;
	BigDecimal quoteDifference;

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getQuoteTimeSold() {
		return quoteTimeSold;
	}

	public void setQuoteTimeSold(BigDecimal quoteTimeSold) {
		this.quoteTimeSold = quoteTimeSold;
	}

	public BigDecimal getQuoteTimeNow() {
		return quoteTimeNow;
	}

	public void setQuoteTimeNow(BigDecimal quoteTimeNow) {
		this.quoteTimeNow = quoteTimeNow;
	}

	public BigDecimal getQuoteDifference() {
		return quoteDifference;
	}

	public void setQuoteDifference(BigDecimal quoteDifference) {
		this.quoteDifference = quoteDifference;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Coin getCoin() {
		return coin;
	}

	public void setCoin(Coin coin) {
		this.coin = coin;
	}

}
