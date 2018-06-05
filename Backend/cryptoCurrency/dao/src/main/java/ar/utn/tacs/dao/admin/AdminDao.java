package ar.utn.tacs.dao.admin;

import java.math.BigInteger;
import java.util.List;

import ar.utn.tacs.dao.GenericDao;
import ar.utn.tacs.model.commons.ExistingDepositException;
import ar.utn.tacs.model.commons.NotExistDepositException;
import ar.utn.tacs.model.commons.RejectingApprovedDepositException;
import ar.utn.tacs.model.commons.RejectingRejectedDepositException;
import ar.utn.tacs.model.deposit.Deposit;
import ar.utn.tacs.model.commons.ApprovingApprovedDepositException;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;

public interface AdminDao extends GenericDao {

	/**
	 * @param idUserA
	 * @param idUserB
	 * @return {@link User}
	 */
	User compareBalance(String nickA, String nickB);

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

	/**
	 * @param deposit
	 * @throws ExistingDepositException 
	 */
	void addDeposit(Deposit deposit) throws ExistingDepositException;

	/**
	 * @param deposit
	 * @throws ApprovingApprovedDepositException 
	 * @throws NotExistDepositException 
	 */
	void approveDeposit(Deposit deposit) throws ApprovingApprovedDepositException, NotExistDepositException;

	/**
	 * @param deposit
	 * @throws RejectingRejectedDepositException 
	 * @throws RejectingApprovedDepositException 
	 * @throws NotExistDepositException 
	 */
	void rejectDeposit(Deposit deposit) throws RejectingRejectedDepositException, RejectingApprovedDepositException, NotExistDepositException;

	/**
	 * @param depositNumber
	 * @return {@link Deposit}
	 * @throws NotExistDepositException 
	 */
	Deposit getDepositByDepositNumber(String depositNumber) throws NotExistDepositException;

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
}
