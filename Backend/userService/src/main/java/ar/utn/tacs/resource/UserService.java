package ar.utn.tacs.resource;

import javax.ws.rs.core.Response;

import ar.utn.tacs.resource.GenericResource;
import ar.utn.tacs.usuario.User;


public interface UserService extends GenericResource{
	
	public static final String base= "/usuarioService"; 
	
	public static final String getUserById= "/getUserById/{userId}";
	public static final String validateNickAndPass= "/validateNickAndPass/{nick}/{pass}";
	
	/**
	 * Retorna un usuario por su id
	 * 
	 * @param idUsuario
	 * @return {@link User}
	 */
	public Response getUserById(int userId);
	
	
	/**
	 * Valida la existencia de un usuario con ese nick y esa password
	 * 
	 * @param nick
	 * @param pass
	 * @return {@link User}
	 */
	public Response ValidateNickAndPass(String nick, String pass);
}
