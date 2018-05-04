package ar.utn.tacs.rest.admin;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.service.GenericService;

public interface AdminRest extends GenericService{
	
	public static final String BASE = "/admin";
	
	public static final String COMPARE_BALANCE = "/balance/{idUserA}/{idUserB}";
	public static final String STATES_TODAY = "/states";
	public static final String STATES_THREE_DAYS = "/states/threeDays";
	public static final String STATES_LAST_WEEK = "/states/lastWeek";
	public static final String STATES_LAST_MOTH = "/states/lastMonth";
	public static final String STATES_START_TIMES = "/states/startTimes";
	
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
	Response statesToday();
	
	/**
	 * {@link GET}
	 * 
	 * @return {@link JsonValue}
	 */
	Response statesThreeDays();
	
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
	Response statesStartTimes();
}
