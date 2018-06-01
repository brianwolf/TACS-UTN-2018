package ar.utn.tacs.service.admin;

import java.math.BigInteger;
import java.util.List;

import ar.utn.tacs.model.commons.ExistingDepositException;
import ar.utn.tacs.model.commons.NotExistDepositException;
import ar.utn.tacs.model.commons.RejectingApprovedDepositException;
import ar.utn.tacs.model.commons.RejectingRejectedDepositException;
import ar.utn.tacs.model.deposit.Deposit;
import ar.utn.tacs.model.commons.ApprovingApprovedDepositException;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.user.UserTransactionRest;
import ar.utn.tacs.service.GenericService;

public interface AdminService extends GenericService {

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
	 * @return {@link List} {@link String}
	 */
	List<String> getUsersNickAll();

	/**
	 * @param nick
	 * @return {@link User}
	 */
	UserTransactionRest getUser(String nick);

	/**
	 * @param depositNumber
	 * @throws ExistingDepositException 
	 */
	void addDeposit(Deposit depositNumber) throws ExistingDepositException;

	/**
	 * @param depositNumber
	 * @throws ApprovingApprovedDepositException 
	 * @throws NotExistDepositException 
	 */
	void approveDeposit(String depositNumber) throws ApprovingApprovedDepositException, NotExistDepositException;

	/**
	 * @param depositNumber
	 * @throws RejectingApprovedDepositException 
	 * @throws RejectingRejectedDepositException 
	 * @throws NotExistDepositException 
	 */
	void rejectDeposit(String depositNumber) throws RejectingRejectedDepositException, RejectingApprovedDepositException, NotExistDepositException;

	/**
	 * @param statusDescription
	 */
	List<Deposit> getDeposits(String statusDescription);

	/**
	 * @return
	 */
	List<Deposit> getDepositsAll();
}
