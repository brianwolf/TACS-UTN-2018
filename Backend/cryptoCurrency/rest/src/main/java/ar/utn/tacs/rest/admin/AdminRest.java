package ar.utn.tacs.rest.admin;

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.service.GenericService;

public interface AdminRest extends GenericService{
	
	public static final String BASE = "/admin";
	
	public static final String COMPARE_BALANCE = "/balance/{idUserA}/{idUserB}";
	public static final String STATES_LAST_WEEK = "/states/lastweek";
	public static final String STATES_LAST_MONTH = "/states/lastmonth";
	public static final String STATES_ALL = "/states/all";
	public static final String STATES_BY_BEFORE_DAYS = "/states";
	
	/**
	 * {@link GET}
	 * 
	 * @param idUserA
	 * @param idUserB
	 * @return {@link JsonValue}
	 */
	Response compareBalance(String nickA, String nickB);
	
	/**
	 * {@link GET}
	 * 
	 * @return {@link JsonValue}
	 */
	Response statesLastWeek();
	
	/**
	 * {@link GET}
	 * 
	 * @return {@link JsonValue}
	 */
	Response statesLastMonth();
	
	/**
	 * {@link GET}
	 * 
	 * @return {@link JsonValue}
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
}
