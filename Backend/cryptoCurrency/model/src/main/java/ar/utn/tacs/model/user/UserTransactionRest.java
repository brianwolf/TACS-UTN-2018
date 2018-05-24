package ar.utn.tacs.model.user;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class UserTransactionRest {
	
	private User user = new User();
	
	private Map<String, BigInteger> transactionCounter = new HashMap<String, BigInteger>();
	
	public UserTransactionRest() {
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, BigInteger> getTransactionCounter() {
		return transactionCounter;
	}

	public void setTransactionCounter(Map<String, BigInteger> transactionCounter) {
		this.transactionCounter = transactionCounter;
	}
	
	public void addTransactionCounter(String transactionDescription, BigInteger count) {
		this.transactionCounter.put(transactionDescription, count);
	}

}
