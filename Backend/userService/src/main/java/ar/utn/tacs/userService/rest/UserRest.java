package ar.utn.tacs.userService.rest;

import javax.ws.rs.core.Response;

import ar.utn.tacs.rest.GenericRest;
import ar.utn.tacs.user.User;


public interface UserRest extends GenericRest{
	
	public static final String BASE= "/usuarios"; 
	public static final String VALIDATE= "/validate";
	
	/**
	 * Retorna un usuario por su id
	 * 
	 * @param idUsuario
	 * @return {@link User}
	 */
	public Response getUserById(Integer userId);
	
	
	/**
	 * Valida la existencia de un usuario con ese nick y esa password
	 * 
	 * @param nick
	 * @param pass
	 * @return {@link User}
	 */
	public Response validateNickAndPass(String nick, String pass);
	
	/**
	 * Crea un nuevo usuario con nick y pass
	 * 
	 * @param nick
	 * @param pass
	 * @return {@link User}
	 */
	public Response newUser(String nick, String pass);
}
