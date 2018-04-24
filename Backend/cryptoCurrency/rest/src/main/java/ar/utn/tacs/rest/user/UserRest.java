package ar.utn.tacs.rest.user;

import java.util.Map;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.model.user.User;
import ar.utn.tacs.rest.GenericRest;


public interface UserRest extends GenericRest{
	
	public static final String BASE= "/users"; 
	
	public static final String NEW_USER= "";
	public static final String GET_TOKEN_BY_LOGIN= "/login/{nick}/{pass}";
	public static final String LOGOUT_USER_BY_TOKEN= "/logout";
	
	/**
	 * @param user
	 * @return {@link JsonValue}
	 */
	Response newUser(Map<String, Object> map);
	
	/**
	 * @param nick
	 * @param pass
	 * @return {@link JsonValue}
	 */
	Response getTokenByLogin(String nick, String pass);
	
	/**
	 * @param token
	 * @return
	 */
	Response logOutUserByToken(String token);
}
