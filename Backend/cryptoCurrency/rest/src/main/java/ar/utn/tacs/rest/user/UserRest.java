package ar.utn.tacs.rest.user;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.model.commons.UserNotFoundException;
import ar.utn.tacs.model.user.ChangeUserRequest;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.rest.GenericRest;


public interface UserRest extends GenericRest{
	
	public static final String BASE= "/users"; 
	
	public static final String NEW_USER = "";
	public static final String GET_TOKEN_BY_LOGIN = "/login";
	public static final String GET_USER_BY_TOKEN = "/loggedin";
	public static final String LOGOUT_USER_BY_TOKEN = "/logout";
	public static final String CHANGE_USER = "";
	public static final String RELOG = "/relog/{nick}";

	
	/**
	 * {@link POST}
	 * 
	 * @param user
	 * @return {@link JsonValue}
	 * @throws UtnTacsException 
	 */
	Response newUser(User user) throws UtnTacsException;
	
	/**
	 * {@link POST}
	 * 
	 * @param nick
	 * @param pass
	 * @return {@link JsonValue}
	 * @throws UtnTacsException 
	 */
	Response getTokenByLogin(Login login) throws UtnTacsException;
	
	/**
	 * {@link GET}
	 * 
	 * @param token
	 * @return {@link Response}
	 * @throws UtnTacsException 
	 */
	Response getUserByToken(String token) throws UtnTacsException;
	
	/**
	 * {@link PUT}
	 * 
	 * @param token
	 * @return
	 */
	Response logOutUserByToken(String token);
	
	/**
	 * {@link POST}
	 * 
	 * @param nick
	 * @return
	 * @throws UserNotFoundException 
	 * @throws UtnTacsException 
	 */
	Response reLog(String nick) throws UserNotFoundException, UtnTacsException;
	
	/**
	 * {@link PUT}
	 * 
	 * @param token
	 * @param changeUserRequest
	 * @return {@link JsonValue}
	 * @throws UtnTacsException 
	 */
	Response updateUser(String token, ChangeUserRequest changeUserRequest) throws UtnTacsException;
	
}
