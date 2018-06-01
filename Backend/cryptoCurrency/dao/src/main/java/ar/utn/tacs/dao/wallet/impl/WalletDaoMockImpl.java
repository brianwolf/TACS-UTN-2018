package ar.utn.tacs.dao.wallet.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.dao.wallet.WalletDao;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.deposit.Deposit;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.wallet.Wallet;

public class WalletDaoMockImpl implements WalletDao {

	private HashMap<User, List<Transaction>> history;
	
	public WalletDaoMockImpl() {
		this.history = new HashMap<User, List<Transaction>>(); 
	}
	
	public HashMap<User, List<Transaction>> getHistory() {
		return history;
	}

	public void setHistory(HashMap<User, List<Transaction>> history) {
		this.history = history;
	}

	@Override
	public void buy(User user, Transaction transaction) throws UtnTacsException {
		
		transaction.doOperations();
		transaction.setDateFinal(new Date());
		
		if(!getHistory().containsKey(user)) {
			getHistory().put(user, new ArrayList<Transaction>());
		}
		
		getHistory().get(user).add(transaction);
	}

	@Override
	public void sale(User user, Transaction transaction) throws UtnTacsException {
		
		transaction.doOperations();
		transaction.setDateFinal(new Date());
		
		if(!getHistory().containsKey(user)) {
			getHistory().put(user, new ArrayList<Transaction>());
		}
		
		getHistory().get(user).add(transaction);
	}

	@Override
	public List<Transaction> userTransactionHistory(User user, Coin coin) {
		
		List<Transaction> transactionsResult = new ArrayList<Transaction>(); 
		
		if (user == null || !this.history.containsKey(user)) {
			return transactionsResult;
		}
		
		if (coin == null) {
			return history.get(user); 
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

	@Override
	public void doDeposit(Deposit deposit) {
		
		Wallet wallet = deposit.getUser().getWallet();
		BigDecimal finalAmount = wallet.getDolarAmount().add(deposit.getAmount());
		
		wallet.setDolarAmount(finalAmount);
	}

}
