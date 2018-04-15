package ar.utn.tacs.service.user;

import java.util.List;

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
	User getUserByLogin(String nick, String pass);
	
	/**
	 * @param id
	 * @return {@link User}
	 */
	User getUserById(long id);
	
	/**
	 * @return {@link List} {@link Long}
	 */
	List<Long> getAllUserIds();

	/**
	 * @return {@link List}
	 */
	List<User> getUsers();
}
