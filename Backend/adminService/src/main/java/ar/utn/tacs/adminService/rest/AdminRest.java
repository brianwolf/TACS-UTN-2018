package ar.utn.tacs.adminService.rest;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.service.GenericService;

public interface AdminRest extends GenericService{
	
	public static final String BASE = "/adminService";
	
	public static final String COMPARE_BALANCE = "/compareBalance/{idUserA}/{idUserB}";
	public static final String STATES_TODAY = "/statesToday";
	public static final String STATES_THREE_DAYS = "/statesThreeDays";
	public static final String STATES_LAST_WEEK = "/statesLastWeek";
	public static final String STATES_LAST_MOTH = "/statesLastMonth";
	public static final String STATES_START_TIMES = "/statesStartTimes";

	/**
	 * @param idUserA
	 * @param idUserB
	 * @return {@link JsonValue}
	 */
	Response compareBalance(long idUserA, long idUserB);
	
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
