package ar.utn.tacs.model.transaction;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.model.operation.Buy;
import ar.utn.tacs.model.operation.Operation;
import ar.utn.tacs.model.operation.Sale;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.wallet.CoinAmount;

public class TransactionBuilder {

	@SuppressWarnings("rawtypes")
	private HashMap<String, Class> operationsClassMap = new HashMap<String, Class>();
	
	public TransactionBuilder() {
		operationsClassMap.put(Buy.DESCRIPTION, Buy.class);
		operationsClassMap.put(Sale.DESCRIPTION, Sale.class);
	}
	
	public Transaction createTransaction(String operationName, User user, CoinAmount coinAmount) throws UtnTacsException {
		
		Operation operation = getOperation(operationName);
			operation.setUser(user);
			operation.setCoinAmount(coinAmount);
			operation.setQuoteTimeSold(coinAmount.getCoin().getValueInDollars());
			operation.setDate(new Date());
		
		Transaction transaction = new Transaction();
			transaction.setDateStart(new Date());
			transaction.addOperation(operation);
		
		return transaction;
	}

	@SuppressWarnings("unchecked")
	private Operation getOperation(String operationClassName) {
		
		try {
			return (Operation) this.operationsClassMap.get(operationClassName).getConstructor().newInstance();
			
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		//ESTO DESPUES HAY QUE CHARLARLO, XQ DEBERIA DE ROMPER (Y NO DEVOLVER UNA COMPRA)
		return new Buy();
	}
	
}
