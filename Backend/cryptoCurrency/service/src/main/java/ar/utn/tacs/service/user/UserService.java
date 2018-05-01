package ar.utn.tacs.service.user;

import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.service.GenericService;

public interface UserService extends GenericService{
	
	/**
	 * @param user
	 */
	public void newUser(User user);
	
	/**
	 * @param nick
	 * @param pass
	 * @return {@link User}
	 */
	public String getTokenByLogin(Login login);
	
	/**
	 * @param token
	 */
	public void logOutUserByToken(String token);

	/**
	 * @param token
	 * @return {@link User}
	 */
	public User getUserByToken(String token);
	
	/**
	 * @param userId
	 * @return {@link User}
	 */
	public User getUserById(Long userId);
}
