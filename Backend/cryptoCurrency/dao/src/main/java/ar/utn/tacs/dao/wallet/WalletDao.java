package ar.utn.tacs.dao.wallet;

import java.util.List;

import ar.utn.tacs.dao.GenericDao;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;

public interface WalletDao extends GenericDao {

	/**
	 * @param idUser
	 * @param idCoin
	 * @return {@link List} {@link Transaction}
	 */
	List<Transaction> userTransactionHistory(User user, Coin coin);

	void insertTransaction(Transaction transaction);
}
