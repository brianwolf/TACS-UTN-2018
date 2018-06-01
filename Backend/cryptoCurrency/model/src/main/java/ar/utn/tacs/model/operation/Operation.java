package ar.utn.tacs.model.operation;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.wallet.CoinAmount;

@JsonIgnoreProperties(value = {"id", "user"})
public abstract class Operation {

	@Id
	protected BigInteger id;
	
	protected String description;
	
	protected CoinAmount coinAmount;

	@Transient
	protected User user;
	
	protected BigDecimal quoteTimeSold;
	
	protected Date date;
	
	public Operation() {
	}
	
	public Operation(BigInteger id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public BigDecimal getQuoteTimeSold() {
		return quoteTimeSold;
	}

	public void setQuoteTimeSold(BigDecimal quoteTimeSold) {
		this.quoteTimeSold = quoteTimeSold;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public CoinAmount getCoinAmount() {
		return coinAmount;
	}

	public void setCoinAmount(CoinAmount coinAmount) {
		this.coinAmount = coinAmount;
	}

	@JsonProperty(value="quoteDifference")
	public BigDecimal getQuoteDifference() {
		return this.quoteTimeSold == null || this.coinAmount == null? 
				new BigDecimal(0l) : 
				this.quoteTimeSold.subtract(this.coinAmount.getCoin().getValueInDollars());
	}
	
	@JsonProperty(value="quoteTimeNow")
	public BigDecimal getQuoteTimeNow() {
		return this.coinAmount != null? this.coinAmount.getCoin().getValueInDollars() : new BigDecimal(0f);
	}
	
	public abstract void doOperation() throws UtnTacsException;
}
