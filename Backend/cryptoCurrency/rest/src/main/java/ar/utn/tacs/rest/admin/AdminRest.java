package ar.utn.tacs.rest.admin;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

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
	public static final String APPROVE_DEPOSIT = "/deposit/approve/{depositNumber}";
	public static final String REJECT_DEPOSIT = "/deposit/reject/{depositNumber}";

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
	 */
	Response approveDeposit(String token, String despositNumber);

	/**
	 * {@link PUT}
	 * 
	 * @param token
	 * @param despositNumber
	 * @return {@link Response}
	 */
	Response rejectDeposit(String token, String despositNumber);
}
