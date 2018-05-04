package ar.utn.tacs.dao.user;

import javax.jws.soap.SOAPBinding.Use;

import ar.utn.tacs.dao.GenericDao;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;

public interface UserDao extends GenericDao {
	
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
	
	
	/**
	 * @param nick
	 * @return {@link Use}
	 */
	public User getUserByNick(String nick);
}
