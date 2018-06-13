package ar.utn.tacs.dao.wallet.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bson.types.ObjectId;

import ar.utn.tacs.dao.wallet.WalletDao;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;

public class WalletDaoMockImpl implements WalletDao {

	private HashMap<ObjectId, List<Transaction>> history;
	
	public WalletDaoMockImpl() {
		this.history = new HashMap<ObjectId, List<Transaction>>(); 
	}
	
	public HashMap<ObjectId, List<Transaction>> getHistory() {
		return history;
	}

	public void setHistory(HashMap<ObjectId, List<Transaction>> history) {
		this.history = history;
	}

	@Override
	public List<Transaction> userTransactionHistory(User user, Coin coin) {
		
		List<Transaction> transactionsResult = new ArrayList<Transaction>(); 
		
		if (user == null || !this.history.containsKey(user.getId())) {
			return transactionsResult;
		}
		
		if (coin == null) {
			return history.get(user.getId()); 
		}
		
		List<Transaction> historyUser = this.history.get(user.getId());
		Stream<Transaction> transactionsStram = historyUser.stream()
				.filter(t -> t.getOperations().stream()
						.anyMatch(o -> o.getCoinAmount().getCoin().equals(coin)));
		
		if (transactionsStram != null) {
			transactionsResult = transactionsStram.collect(Collectors.toList());
		}
		
		return transactionsResult; 
	}

	@Override
	public void insertTransaction(Transaction transaction) {
		
		if(!this.history.containsKey(transaction.getUser().getId())) {
			List<Transaction> t=new ArrayList<Transaction>();
			this.history.put(transaction.getUser().getId(), t);
		}
		this.history.get(transaction.getUser().getId()).add(transaction);
	}

}
