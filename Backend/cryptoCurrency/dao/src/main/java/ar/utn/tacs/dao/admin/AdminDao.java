package ar.utn.tacs.dao.admin;

import java.util.List;

import ar.utn.tacs.dao.GenericDao;
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
	List<Transaction> statesToday();

	/**
	 * @return {@link List}{@link Transaction}
	 */
	List<Transaction> statesThreeDays();

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
	List<Transaction> statesStartTimes();
}
