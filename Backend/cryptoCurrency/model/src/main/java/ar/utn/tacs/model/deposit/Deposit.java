package ar.utn.tacs.model.deposit;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import ar.utn.tacs.dao.persistent.impl.MongoPersistentObject;
import ar.utn.tacs.model.user.User;

@Document(collection = "deposits")
@JsonIgnoreProperties(value = { "id", "user" })
public class Deposit extends MongoPersistentObject {

	private String number;

	private String state;

	private BigDecimal amount;

	private User user;

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JsonProperty(value = "userNick")
	private String getUserNick() {
		return this.user.getLogin().getNick();
	}

}
