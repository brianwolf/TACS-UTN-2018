package ar.utn.tacs.service.user;

import java.util.List;

import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.service.GenericService;

public interface UserService extends GenericService{
	
	/**
	 * @param user
	 */
	void newUser(User user);
	
	/**
	 * @param nick
	 * @param pass
	 * @return {@link User}
	 */
	String getTokenByLogin(Login login);
	
	/**
	 * @param token
	 */
	void logOutUserByToken(String token);

	/**
	 * @param token
	 * @return {@link User}
	 */
	User getUserByToken(String token);
	
	/**
	 * @param id
	 * @return {@link User}
	 */
	User getUserByid(Long id);
	
	/**
	 * @param nick
	 * @return {@link User}
	 */
	User getUser(String nick);
	
	/**
	 * @return {@link String}
	 */
	List<String> getUsersNickAll();
}
