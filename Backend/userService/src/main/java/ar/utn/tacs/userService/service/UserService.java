package ar.utn.tacs.userService.service;

import java.util.List;

import ar.utn.tacs.service.GenericService;
import ar.utn.tacs.user.User;

public interface UserService extends GenericService{
	
	
	/**
	 * @param userId
	 * @return {@link User}
	 */
	public User getUserById(int userId);
	
	
	/**
	 * @param nick
	 * @param pass
	 * @return {@link User}
	 */
	public User validateNickAndPass(String nick, String pass);

	/**
	 * @return {@link List<User>}
	 */
	public List<User> getUsers();
}
