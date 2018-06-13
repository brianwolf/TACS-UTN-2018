package ar.utn.tacs.dao.admin;

import java.math.BigInteger;
import java.util.List;

import ar.utn.tacs.dao.GenericDao;
import ar.utn.tacs.model.commons.ExistingDepositException;
import ar.utn.tacs.model.commons.NotExistDepositException;
import ar.utn.tacs.model.deposit.Deposit;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;

public interface AdminDao extends GenericDao {

	/**
	 * @return {@link List}{@link Transaction}
	 */
	BigInteger statesLastWeek();

	/**
	 * @return {@link List}{@link Transaction}
	 */
	BigInteger statesLastMonth();

	/**
	 * @return {@link List}{@link Transaction}
	 */
	BigInteger statesAll();

	/**
	 * @param beforeDays
	 * @return {@link List} {@link Transaction}
	 */
	BigInteger statesByBeforeDays(Integer beforeDays);

	void insertDeposit(Deposit deposit) throws ExistingDepositException;

	/**
	 * @param depositNumber
	 * @return {@link Deposit}
	 * @throws NotExistDepositException 
	 */
	Deposit getDepositByNumber(String depositNumber) throws NotExistDepositException;

	/**
	 * @param statusDescription
	 * @return {@link List} {@link Deposit}
	 */
	List<Deposit> getDeposits(String statusDescription);

	/**
	 * @return {@link List} {@link Deposit}
	 */
	List<Deposit> getDepositsAll();

	/**
	 * @param beforeDays
	 * @return {@link List} {@link Transaction}
	 */
	List<Transaction> transactionsStatesByBeforeDays(Integer beforeDays);

	/**
	 * @param user
	 * @return
	 */
	BigInteger statesAll(User user);

	/**
	 * @param user
	 * @param days
	 * @return
	 */
	BigInteger statesByBeforeDays(User user, Integer days);

	void updateDeposit(Deposit deposit);

}
