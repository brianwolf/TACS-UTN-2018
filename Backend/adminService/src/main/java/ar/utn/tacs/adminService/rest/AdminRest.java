package ar.utn.tacs.adminService.rest;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.service.GenericService;

public interface AdminRest extends GenericService{
	
	public static final String BASE = "/admin";
	
	public static final String COMPARE_BALANCE = "/comparador/{idUserA}/{idUserB}";
	public static final String STATES_TODAY = "/estados";
	public static final String STATES_THREE_DAYS = "/estados/threeDays";
	public static final String STATES_LAST_WEEK = "/estados/lastWeek";
	public static final String STATES_LAST_MOTH = "/estados/lastMonth";
	public static final String STATES_START_TIMES = "/estados/startTimes";

	/**
	 * @param idUserA
	 * @param idUserB
	 * @return {@link JsonValue}
	 */
	Response compareBalance(Long idUserA, Long idUserB);
	
	/**
	 * @return {@link JsonValue}
	 */
	Response statesToday();
	
	/**
	 * @return {@link JsonValue}
	 */
	Response statesThreeDays();
	
	/**
	 * @return {@link JsonValue}
	 */
	Response statesLastWeek();
	
	/**
	 * @return {@link JsonValue}
	 */
	Response statesLastMonth();
	
	/**
	 * @return {@link JsonValue}
	 */
	Response statesStartTimes();
}
