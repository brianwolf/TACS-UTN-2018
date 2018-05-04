package ar.utn.tacs.service.admin;

import java.util.List;

import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;
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
	List<Transaction> statesLastWeek();

	/**
	 * @return {@link List}{@link Transaction}
	 */
	List<Transaction> statesLastMonth();

	/**
	 * @return {@link List}{@link Transaction}
	 */
	List<Transaction> statesGetAll();
	
	
	/**
	 * @param beforeDays
	 * @return {@link List} {@link Transaction}
	 */
	List<Transaction> statesByBeforeDays(Integer beforeDays);
}
