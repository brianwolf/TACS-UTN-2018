package ar.utn.tacs.model.operation;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Transient;

import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.user.User;

public abstract class Operation {

	@Id
	protected Long id;
	
	protected String description;
	
	protected Coin coin;
	
	@Transient
	protected User user;
	
	protected BigDecimal amount;
	
	private BigDecimal quoteTimeSold;

	@Transient
	private BigDecimal quoteTimeNow;
	
	@Transient
	private BigDecimal quoteDifference;
	
	private Date date;
	
	protected Operation(Long id, String description) {
		this.id = id;
		this.description = description;
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
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getQuoteDifference() {
		this.quoteDifference = this.quoteTimeSold == null || this.quoteTimeNow == null? 
				new BigDecimal(0l) : 
				this.quoteTimeSold.subtract(this.quoteTimeNow);
				
		return this.quoteDifference;
	}

}
