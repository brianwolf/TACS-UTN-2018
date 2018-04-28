package ar.utn.tacs.model.transaction;

import java.math.BigDecimal;
import java.util.Date;

import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.operation.Buy;
import ar.utn.tacs.model.operation.Operation;
import ar.utn.tacs.model.operation.Sale;
import ar.utn.tacs.model.user.User;

public class TransactionBuilder {

	public Transaction createTransaction(String operationName, User user, Coin coin,BigDecimal amount) {
		
		Operation  operation = getOperation(operationName);
		operation.setCoin(coin);
		operation.setUser(user);
		operation.setAmount(amount);
		
		Transaction transaction = new Transaction();
		transaction.setDateStart(new Date());
		transaction.addOperation(operation);
		
		return transaction;
	}

	private Operation getOperation(String operation) {
		
		Operation o = null;
		
		switch (operation) {
		case "buy":
			o = new Buy();
			break;
		case "sale":
			o = new Sale();
			break;

		default:
			o = new Buy();
			break;
		}
		return o;
	}
	
}
