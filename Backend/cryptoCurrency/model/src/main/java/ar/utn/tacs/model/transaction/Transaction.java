package ar.utn.tacs.model.transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.utn.tacs.model.operation.Operation;
import ar.utn.tacs.model.user.User;

public class Transaction {

	private Long id;
	private User user;
	private List<Operation> operations = new ArrayList<Operation>();
	private BigDecimal quoteTimeSold;
	private BigDecimal quoteTimeNow;
	private BigDecimal quoteDifference;
	private Date date;
	
	public List<Operation> getOperations() {
		return operations;
	}

	public void addOperation(Operation operation) {
		this.operations.add(operation);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
