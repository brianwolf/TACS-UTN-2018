package ar.utn.tacs.adminService.service;

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
	User compareBalance(long idUserA, long idUserB);

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
