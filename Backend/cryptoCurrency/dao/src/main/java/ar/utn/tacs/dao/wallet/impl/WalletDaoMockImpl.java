package ar.utn.tacs.dao.wallet.impl;

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
	public Transaction buy(HashMap<String, String> resultMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction sale(HashMap<String, String> resultMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> userTransactionHistory(String token, String coinSymbol) {
		List<Transaction> transactionsResult = history.get(token);
		return (List<Transaction>) transactionsResult.stream().filter(transaction -> transaction.getCoin().getTicker().equals(coinSymbol));
	}
}
