package ar.utn.tacs.dao.wallet;

import java.util.List;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.dao.GenericDao;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.deposit.Deposit;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;

public interface WalletDao extends GenericDao {


	/**
	 * @param user
	 * @param transaction
	 * @throws UtnTacsException
	 */
	void buy(User user,Transaction transaction) throws UtnTacsException;

	/**
	 * @param user
	 * @param transaction
	 * @throws UtnTacsException
	 */
	void sale(User user, Transaction transaction) throws UtnTacsException;

	/**
	 * @param idUser
	 * @param idCoin
	 * @return {@link List} {@link Transaction}
	 */
	List<Transaction> userTransactionHistory(User user, Coin coin);

	/**
	 * @param deposit
	 */
	void doDeposit(Deposit deposit);
}
