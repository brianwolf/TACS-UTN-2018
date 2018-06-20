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
	
	/**
	 * @param user
	 */
	void updateUser(User user);

	/**
	 * @param login
	 * @return
	 * @throws UserNotFoundException
	 */
	User getUserByLogin(Login login) throws UserNotFoundException;

	/**
	 * @param id
	 */
	void deleteConnectedUserById(ObjectId id);

	/**
	 * @param connectedUser
	 */
	void insertConnectedUser(ConnectedUser connectedUser);

	/**
	 * @param user
	 */
	void insertUser(User user);

	/**
	 * @param descripcion
	 * @return
	 */
	Role getRolByDescription(String descripcion);
	
	/**
	 * 
	 */
	void updateConectedUsersInServer(Integer timeInMinutes);
}
