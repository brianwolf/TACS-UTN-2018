package ar.utn.tacs.model.admin;

import java.math.BigDecimal;

import ar.utn.tacs.model.user.User;

public class Deposit {

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

}
