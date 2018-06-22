package ar.utn.tacs.rest.admin;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import ar.utn.tacs.model.commons.ApprovingApprovedDepositException;
import ar.utn.tacs.model.commons.NotExistDepositException;
import ar.utn.tacs.model.commons.RejectingApprovedDepositException;
import ar.utn.tacs.model.commons.RejectingRejectedDepositException;
import ar.utn.tacs.model.commons.UserNotFoundException;
import ar.utn.tacs.service.GenericService;

public interface AdminRest extends GenericService{
	
	public static final String BASE = "/admin";
	
	public static final String COMPARE_BALANCE = "/balance/{nickA}/{nickB}";
	public static final String STATES_LAST_WEEK = "/states/lastweek";
	public static final String STATES_LAST_MONTH = "/states/lastmonth";
	public static final String STATES_ALL = "/states/all";
	public static final String STATES_BY_BEFORE_DAYS = "/states";
	public static final String USERS_NICKS_ALL = "/users/nicks";
	public static final String GET_USER = "/users";
	public static final String APPROVE_DEPOSIT = "/deposits/approve/{depositNumber}";
	public static final String REJECT_DEPOSIT = "/deposits/reject/{depositNumber}";
	public static final String GET_DEPOSITS = "/deposits";
	public static final String GET_DEPOSITS_ALL = "/deposits/all";
	public static final String CONVERT_USER_TO_ADMIN = "/users/toAdmin";

	/**
	 * {@link GET}
	 * 
	 * @param idUserA
	 * @param idUserB
	 * @return {@link Response}
	 */
	Response compareBalance(String nickA, String nickB);
	
	/**
	 * {@link GET}
	 * 
	 * @return {@link Response}
	 */
	Response statesLastWeek();
	
	/**
	 * {@link GET}
	 * 
	 * @return {@link Response}
	 */
	Response statesLastMonth();
	
	/**
	 * {@link GET}
	 * 
	 * @return {@link Response}
	 */
	Response statesAll();
	
	/**
	 * {@link GET}
	 * 
	 * {@link QueryParam}
	 * "beforeDays" : {@link Integer} 
	 * 
	 * @return {@link Response}
	 */
	Response statesByBeforeDays(Integer beforeDays);

	/**
	 * {@link GET}
	 * 
	 * @return {@link Response}
	 */
	Response getUsersNickAll();

	/**
	 * {@link GET}
	 * 
	 * {@link QueryParam}
	 * nick: {@link String} 
	 * 
	 * @return {@link Response}
	 */
	Response getUser(String nick);
	
	/**
	 * {@link PUT}
	 * 
	 * @param token
	 * @return {@link Response}
	 * @throws NotExistDepositException 
	 * @throws ApprovingApprovedDepositException 
	 */
	Response approveDeposit(String despositNumber) throws ApprovingApprovedDepositException, NotExistDepositException;

	/**
	 * {@link PUT}
	 * 
	 * @param token
	 * @param despositNumber
	 * @return {@link Response}
	 * @throws NotExistDepositException 
	 * @throws RejectingApprovedDepositException 
	 * @throws RejectingRejectedDepositException 
	 */
	Response rejectDeposit(String despositNumber) throws RejectingRejectedDepositException, RejectingApprovedDepositException, NotExistDepositException;
	
	/**
	 * {@link GET}
	 * 
	 * @param statusDescription {@link QueryParam}
	 * @return {@link Response}
	 */
	Response getDeposits(String statusDescription);
	
	/**
	 * {@link GET}
	 * 
	 * @return {@link Response}
	 */
	Response getDepositsAll();
	
	
	/**
	 * {@link PUT}
	 * 
	 * @param nick {@link QueryParam}
	 * @return
	 * @throws UserNotFoundException 
	 */
	Response convertUserToAdmin(String nick) throws UserNotFoundException;
}
