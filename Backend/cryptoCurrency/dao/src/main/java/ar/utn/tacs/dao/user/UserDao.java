package ar.utn.tacs.dao.user;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.bson.types.ObjectId;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.dao.GenericDao;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;

public interface UserDao extends GenericDao {
	
	/**
	 * @param user
	 * @throws UtnTacsException 
	 */
	void newUser(User user) throws UtnTacsException;
	
	/**
	 * @param nick
	 * @param pass
	 * @return {@link User}
	 * @throws UtnTacsException 
	 */
	String getTokenByLogin(Login login) throws UtnTacsException;
	
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
	User getUserById(ObjectId id);
	
	
	/**
	 * @param nick
	 * @return {@link Use}
	 */
	User getUserByNick(String nick);
	
	/**
	 * @return {@link String}
	 */
	List<String> getUsersNicksAll();
	
	/**
	 * @param nick
	 */
	void convertUserToAdmin(User user);

	void changePassword(User user, String newPassword);
}
