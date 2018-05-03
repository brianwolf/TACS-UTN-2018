package ar.utn.tacs.dao.wallet.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ar.utn.tacs.dao.impl.GenericAbstractDaoImpl;
import ar.utn.tacs.dao.wallet.WalletDao;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.wallet.Wallet;

public class WalletDaoMockImpl extends GenericAbstractDaoImpl<Wallet> implements WalletDao {

	private HashMap<User, List<Transaction>> history;
	
	public WalletDaoMockImpl() {
		this.history = new HashMap<User, List<Transaction>>(); 
	}

	@Override
	public Boolean buy(User user, Transaction transaction) {
		
		transaction.setDateFinal(new Date());
		
		if(!getHistory().containsKey(user)) {
			getHistory().put(user, new ArrayList<Transaction>());
		}
		
		getHistory().get(user).add(transaction);
		return true;
	}

	@Override
	public Boolean sale(User user, Transaction transaction) {
		
		transaction.setDateFinal(new Date());
		
		if(!getHistory().containsKey(user)) {
			getHistory().put(user, new ArrayList<Transaction>());
		}
		
		getHistory().get(user).add(transaction);
		return true;
	}

	@Override
	public List<Transaction> userTransactionHistory(User user, Coin coin) {
		
		List<Transaction> transactionsResult = new ArrayList<Transaction>(); 

		if (user == null || coin == null || !this.history.containsKey(user)) {
			return transactionsResult;
		}
		
		List<Transaction> historyUser = this.history.get(user);
		Stream<Transaction> transactionsStram = historyUser.stream()
				.filter(t -> t.getOperations().stream()
						.anyMatch(o -> o.getCoinAmount().getCoin().equals(coin)));
		
		if (transactionsStram != null) {
			transactionsResult = transactionsStram.collect(Collectors.toList());
		}
		
		return transactionsResult; 
	}

	public HashMap<User, List<Transaction>> getHistory() {
		return history;
	}

	public void setHistory(HashMap<User, List<Transaction>> history) {
		this.history = history;
	}
}
