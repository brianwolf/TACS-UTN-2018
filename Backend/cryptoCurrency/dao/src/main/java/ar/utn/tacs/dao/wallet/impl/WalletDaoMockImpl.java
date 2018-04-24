package ar.utn.tacs.dao.wallet.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import ar.utn.tacs.dao.impl.GenericAbstractDaoImpl;
import ar.utn.tacs.dao.wallet.WalletDao;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.wallet.Wallet;

public class WalletDaoMockImpl extends GenericAbstractDaoImpl<Wallet> implements WalletDao {

	private HashMap<String, List<Transaction>> history = new HashMap<String,List<Transaction>>();

	@Override
	public Boolean buy(String token,Transaction transaction) {
		
		if(!getHistory().containsKey(token)) {
			getHistory().put(token,new ArrayList<Transaction>());
		}
		
		getHistory().get(token).add(transaction);
		return true;
	}

	@Override
	public Boolean sale(String token,Transaction transaction) {
		if(!getHistory().containsKey(token)) {
			getHistory().put(token,new ArrayList<Transaction>());
		}
		
		getHistory().get(token).add(transaction);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> userTransactionHistory(String token, String coinSymbol) {
		List<Transaction> transactionsResult = getHistory().get(token);
		return (List<Transaction>) transactionsResult.stream().filter(transaction -> transaction.getOperations().get(0).getCoin().getTicker().equals(coinSymbol)).collect(Collectors.toList());
	}

	public HashMap<String, List<Transaction>> getHistory() {
		return history;
	}

	public void setHistory(HashMap<String, List<Transaction>> history) {
		this.history = history;
	}
}
