package ar.utn.tacs.userService.rest;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.model.user.User;
import ar.utn.tacs.rest.GenericRest;


public interface UserRest extends GenericRest{
	
	public static final String BASE= "/usuarios"; 
	
	public static final String NEW_USER= "";
	public static final String GET_USER_BY_LOGIN= "/{nick}/{pass}";
	public static final String GET_USER_BY_ID= "/{id}";
	public static final String GET_ALL_USERS_IDS= "/ids";
	
	/**
	 * @param user
	 * @return {@link JsonValue}
	 */
	Response newUser(User user);
	
	/**
	 * @param nick
	 * @param pass
	 * @return {@link JsonValue}
	 */
	Response getUserByLogin(String nick, String pass);
	
	/**
	 * @param id
	 * @return {@link JsonValue}
	 */
	Response getUserById(long id);
	
	/**
	 * @return {@link JsonValue}
	 */
	Response getAllUserIds();
}
