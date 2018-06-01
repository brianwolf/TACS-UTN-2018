package ar.utn.tacs.model.operation;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.wallet.CoinAmount;

@Document
@JsonIgnoreProperties(value = {"id", "user"})
public abstract class Operation {

	protected String description;
	
	protected CoinAmount coinAmount;

	@Transient
	protected User user;
	
	protected BigDecimal quoteTimeSold;
	
	protected Date date;
	
	public Operation() {
	}
	
	public Operation(String description) {
		this.description = description;
	}
	
	public BigDecimal getQuoteTimeSold() {
		return quoteTimeSold;
	}

	public void setQuoteTimeSold(BigDecimal quoteTimeSold) {
		this.quoteTimeSold = quoteTimeSold;
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
