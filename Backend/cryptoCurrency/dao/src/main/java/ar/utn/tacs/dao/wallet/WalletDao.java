package ar.utn.tacs.dao.wallet;

import java.util.List;

import ar.utn.tacs.dao.GenericDao;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;

public interface WalletDao extends GenericDao {

	/**
	 * @param user
	 * @param transaction
	 * @return {@link Boolean}
	 */
	public void buy(User user,Transaction transaction);

	/**
	 * @param user
	 * @param transaction
	 * @return {@link Boolean}
	 */
	public void sale(User user, Transaction transaction);

	/**
	 * @param idUser
	 * @param idCoin
	 * @return {@link List} {@link Transaction}
	 */
	public List<Transaction> userTransactionHistory(User user, Coin coin);
}
