package ar.utn.tacs.rest.user;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.rest.GenericRest;


public interface UserRest extends GenericRest{
	
	public static final String BASE= "/users"; 
	
	public static final String NEW_USER = "";
	public static final String GET_TOKEN_BY_LOGIN = "/login";
	public static final String GET_USER_BY_TOKEN = "/loggedin";
	public static final String LOGOUT_USER_BY_TOKEN = "/logout";
	
	/**
	 * {@link POST}
	 * 
	 * @param user
	 * @return {@link JsonValue}
	 */
	Response newUser(User user);
	
	/**
	 * {@link POST}
	 * 
	 * @param nick
	 * @param pass
	 * @return {@link JsonValue}
	 */
	Response getTokenByLogin(Login login);
	
	/**
	 * {@link GET}
	 * 
	 * @param token
	 * @return {@link Response}
	 */
	Response getUserByToken(String token);
	
	/**
	 * {@link PUT}
	 * 
	 * @param token
	 * @return
	 */
	Response logOutUserByToken(String token);
	
}
