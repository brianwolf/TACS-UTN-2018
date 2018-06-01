package ar.utn.tacs.model.admin;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import ar.utn.tacs.model.user.User;

@JsonIgnoreProperties(value = {"id", "user"})
public class Deposit {

	public BigInteger id;

	public String number;

	public StateDepositNumber state;

	public BigDecimal amount;

	public User user;

	public Deposit() {
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Deposit)) {
			return false;
		}

		Deposit other = (Deposit) obj;
		return this.number.equals(other.number);
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public StateDepositNumber getState() {
		return state;
	}

	public void setState(StateDepositNumber state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}
	
	@JsonProperty(value="userNick")
	private String getUserNick(){
		return this.user.getLogin().getNick();
	}

}
