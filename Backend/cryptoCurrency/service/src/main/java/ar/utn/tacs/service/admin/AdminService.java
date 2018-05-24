package ar.utn.tacs.service.admin;

import java.math.BigInteger;
import java.util.List;

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
}
