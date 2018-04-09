package ar.utn.tacs.model.transaction;

import java.math.BigDecimal;
import java.util.List;

import ar.utn.tacs.model.operation.Operation;
import ar.utn.tacs.model.user.User;

public class Transaction {

	long id;
	private List<User> user;
	private List<Operation> operations;
	BigDecimal amount;
	BigDecimal quoteTimeSold;
	BigDecimal quoteTimeNow;
	BigDecimal quoteDifference;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

}
