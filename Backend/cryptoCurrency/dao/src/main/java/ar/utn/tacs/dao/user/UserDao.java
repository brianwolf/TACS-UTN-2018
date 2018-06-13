package ar.utn.tacs.dao.user;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.bson.types.ObjectId;

import ar.utn.tacs.dao.GenericDao;
import ar.utn.tacs.model.commons.UserNotFoundException;
import ar.utn.tacs.model.role.Role;
import ar.utn.tacs.model.user.ConnectedUser;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;

public interface UserDao extends GenericDao {
	
	/**
	 * @param token
	 */
	void deleteConnectedUserByToken(String token);

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
	
	void updateUser(User user);

	User getUserByLogin(Login login) throws UserNotFoundException;

	void deleteConnectedUserById(ObjectId id);

	void insertConnectedUser(ConnectedUser connectedUser);

	void insertUser(User user);

	Role getRolByDescription(String descripcion);
}
