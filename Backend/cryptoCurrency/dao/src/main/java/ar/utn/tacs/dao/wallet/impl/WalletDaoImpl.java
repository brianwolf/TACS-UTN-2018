package ar.utn.tacs.dao.wallet.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import ar.utn.tacs.dao.impl.GenericAbstractDaoImpl;
import ar.utn.tacs.dao.wallet.WalletDao;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;

public class WalletDaoImpl extends GenericAbstractDaoImpl implements WalletDao {

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
	public void insertTransaction(Transaction transaction) {
		this.insert(transaction);
		
	}
}
