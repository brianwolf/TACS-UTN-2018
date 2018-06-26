package ar.utn.tacs.model.deposit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import ar.utn.tacs.dao.persistent.impl.MongoPersistentObject;
import ar.utn.tacs.model.user.User;

@Document(collection = "deposits")
@JsonIgnoreProperties(value = { "id", "user" ,"states"})
public class Deposit extends MongoPersistentObject {
	
	private class DepositState{
		private Date date;
		private String state;
		
		public DepositState(String state,Date date) {
			this.date=date;
			this.state = state;
		}
		
		public Date getDate() {
			return date;
		}
		
		public String getState() {
			return state;
		}
	}
	
	public static final String APPROVED = "APPROVED";
	
	public static final String REJECTED = "REJECTED";
	
	public static final String WAITING = "WAITING";

	private String number;

	private BigDecimal amount;

	private User user;
	
	private List<DepositState> states = new ArrayList<DepositState>();

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
	
	@JsonProperty(value = "state")
	public String getState() {
		return getActualState().getState();
	}
	
	@JsonIgnore
	public String getUserState() {
		String userState="";
		
		switch (getActualState().getState()) {
		case APPROVED:
			userState = "APROBADO";
			break;
		case REJECTED:
			userState = "RECHAZADO";
			break;
		case WAITING:
			userState = "EN ESPERA";
			break;
		}
		
		return userState;
	}

	public void setState(String state) {
		if(!state.equals(getActualState().getState())) {
			states.add(new DepositState(state, new Date()));
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@JsonProperty(value = "date")
	public Date getDate() {
		return getActualState().getDate();
	}

//	public void setDate(Date date) {
//		getActualState().setDate(date);
//	}
	
	private DepositState getActualState() {
		return states.stream().max(Comparator.comparing(DepositState::getDate)).orElse(new DepositState(null,null));
	}

	@JsonProperty(value = "userNick")
	private String getUserNick() {
		return this.user.getLogin().getNick();
	}

}
