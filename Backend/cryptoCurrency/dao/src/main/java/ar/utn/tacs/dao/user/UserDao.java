package ar.utn.tacs.dao.user;

import java.util.List;

import ar.utn.tacs.dao.GenericDao;
import ar.utn.tacs.model.user.User;

public interface UserDao extends GenericDao {
	
	/**
	 * @param userId
	 * @return {@link User}
	 */
	public User getUserById(Long userId);

	/**
	 * @param nick
	 * @param pass
	 * @return {@link String}
	 */
	public String getTokenByLogin(String nick, String pass);
	
	/**
	 * @return {@link List}{@link User}
	 */
	public List<User> getUsers();
	
	/**
	 * @param token
	 */
	public void logOutUserByToken(String token);

	public User getUserByToken(String token);

	public void createUser(User user);
}
