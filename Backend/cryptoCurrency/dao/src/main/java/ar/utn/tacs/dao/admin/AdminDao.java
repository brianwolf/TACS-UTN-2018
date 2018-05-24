package ar.utn.tacs.dao.admin;

import java.math.BigInteger;
import java.util.List;

import ar.utn.tacs.dao.GenericDao;
import ar.utn.tacs.model.admin.Deposit;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;

public interface AdminDao extends GenericDao{

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
	 */
	void addDeposit(Deposit deposit);
	
	/**
	 * @param deposit
	 */
	void approveDeposit(Deposit deposit);
	
	/**
	 * @param deposit
	 */
	void rejectDeposit(Deposit deposit);
	
	/**
	 * @param depositNumber
	 * @return {@link Deposit}
	 */
	Deposit getDepositByDepositNumber(String depositNumber);
}
