package ar.utn.tacs.model.admin;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonIgnore;

import ar.utn.tacs.model.user.User;

public class DepositRest {

	public String number;

	public String state;

	public String amount;

	public String userNick;

	public DepositRest() {
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getState() {
		return state;
	}

	@JsonIgnore
	public void setState(String state) {
		this.state = state;
	}

	public String getUserNick() {
		return userNick;
	}

	@JsonIgnore
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Deposit toDeposit(User user) {

		Deposit depositNumber = new Deposit();
		depositNumber.setUser(user);
		depositNumber.setState(this.state == null? StateDepositNumber.WAITING : StateDepositNumber.valueOf(this.state));
		depositNumber.setNumber(this.number);
		depositNumber.setAmount(new BigDecimal(this.amount));

		return depositNumber;
	}
}
