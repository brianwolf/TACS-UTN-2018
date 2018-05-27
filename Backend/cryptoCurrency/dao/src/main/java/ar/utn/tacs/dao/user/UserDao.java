package ar.utn.tacs.dao.user;

import java.math.BigInteger;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import ar.utn.tacs.dao.GenericDao;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;

public interface UserDao extends GenericDao {
	
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
	 * @param userId
	 * @return {@link User}
	 */
	User getUserById(BigInteger id);
	
	
	/**
	 * @param nick
	 * @return {@link Use}
	 */
	User getUserByNick(String nick);
	
	/**
	 * @return {@link String}
	 */
	List<String> getUsersNicksAll();
}
