package ar.utn.tacs.dao.wallet.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.dao.impl.GenericAbstractDaoImpl;
import ar.utn.tacs.dao.wallet.WalletDao;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.deposit.Deposit;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.wallet.Wallet;

public class WalletDaoImpl extends GenericAbstractDaoImpl implements WalletDao {

	@Override
	public void buy(User user, Transaction transaction) throws UtnTacsException {
		
		transaction.doOperations();
		transaction.setDateFinal(new Date());

		User userTransaction = new User();
		userTransaction.setId(user.getId());

		transaction.setUser(userTransaction);
		this.insert(transaction);
		
		this.update(user);
	}

	@Override
	public void sale(User user, Transaction transaction) throws UtnTacsException {
		
		transaction.doOperations();
		transaction.setDateFinal(new Date());

		User userTransaction = new User();
		userTransaction.setId(user.getId());
		
		transaction.setUser(userTransaction);
		this.insert(transaction);
		
		this.update(user);
	}

	@Override
	public List<Transaction> userTransactionHistory(User user, Coin coin) {

		Query q = new Query();
		q.addCriteria(Criteria.where("user._id").is(user.getId()));

		if (coin != null) {
			q.addCriteria(Criteria.where("operations").elemMatch(Criteria.where("coinAmount.coin.ticker").is(coin.getTicker())));
		}
		
		return mongoTemplate.find(q, Transaction.class);
	}

	@Override
	public void doDeposit(Deposit deposit) {
		Wallet wallet = deposit.getUser().getWallet();
		BigDecimal finalAmount = wallet.getDolarAmount().add(deposit.getAmount());
		
		wallet.setDolarAmount(finalAmount);
		this.update(deposit.getUser());
	}
}
