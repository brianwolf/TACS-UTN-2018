package ar.utn.tacs.dao.wallet.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.utn.tacs.dao.impl.GenericAbstractDaoImpl;
import ar.utn.tacs.dao.wallet.WalletDao;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.wallet.Wallet;

public class WalletDaoMockImpl extends GenericAbstractDaoImpl<Wallet> implements WalletDao {

	private HashMap<String, List<Transaction>> history = new HashMap<String,List<Transaction>>();

	public WalletDaoMockImpl() {
		this.history = new HashMap<String, List<Transaction>>();
	}

	@Override
	public Boolean buy(String token,Transaction transaction) {
		
		if(!history.containsKey(token)) {
			history.put(token,new ArrayList<Transaction>());
		}
		
		history.get(token).add(transaction);
		return true;
	}

	@Override
	public Boolean sale(String token,Transaction transaction) {
		if(!history.containsKey(token)) {
			history.put(token,new ArrayList<Transaction>());
		}
		
		history.get(token).add(transaction);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> userTransactionHistory(String token, String coinSymbol) {
		List<Transaction> transactionsResult = history.get(token);
		return (List<Transaction>) transactionsResult.stream().filter(transaction -> transaction.getOperations().get(0).getCoin().getTicker().equals(coinSymbol));
	}
}
