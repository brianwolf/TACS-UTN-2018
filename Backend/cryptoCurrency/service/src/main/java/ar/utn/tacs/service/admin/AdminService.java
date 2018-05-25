package ar.utn.tacs.service.admin;

import java.math.BigInteger;
import java.util.List;

import ar.utn.tacs.model.admin.Deposit;
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
	 */
	void addDeposit(Deposit depositNumber);

	/**
	 * @param depositNumber
	 */
	void approveDeposit(String depositNumber);

	/**
	 * @param depositNumber
	 */
	void rejectDeposit(String depositNumber);

	/**
	 * @param statusDescription
	 */
	List<Deposit> getDeposits(String statusDescription);

	/**
	 * @return
	 */
	List<Deposit> getDepositsAll();
}
