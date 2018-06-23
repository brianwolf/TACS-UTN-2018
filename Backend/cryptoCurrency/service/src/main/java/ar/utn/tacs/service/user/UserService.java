package ar.utn.tacs.service.user;

import java.util.List;

import org.bson.types.ObjectId;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.model.commons.UserNotFoundException;
import ar.utn.tacs.model.deposit.DepositRest;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.service.GenericService;

public interface UserService extends GenericService {

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
	 * @throws UtnTacsException
	 */
	User getUserByToken(String token) throws UtnTacsException;

	/**
	 * @param id
	 * @return {@link User}
	 */
	User getUserById(ObjectId id);

	/**
	 * @param nick
	 * @return {@link User}
	 */
	User getUser(String nick);

	/**
	 * @return {@link String}
	 */
	List<String> getUsersNickAll();

	/**
	 * @param token
	 * @param depositRest
	 */
	void declareDeposit(String token, DepositRest depositRest);

	/**
	 * @param nick
	 * @throws UserNotFoundException 
	 */
	void convertUserToAdmin(String nick) throws UserNotFoundException;

	/**
	 * @param nick
	 * @throws UserNotFoundException
	 * @throws UtnTacsException 
	 */
	void relogUserByNick(String nick) throws UserNotFoundException, UtnTacsException;

	/**
	 * @param token
	 * @param login
	 * @throws UtnTacsException
	 */
	void changePassword(String token, Login login) throws UtnTacsException;

	/**
	 * @param token
	 * @param oldUser
	 * @param newUser
	 * @throws UtnTacsException
	 */
	void updateUser(String token, User oldUser, User newUser) throws UtnTacsException;
	
	/**
	 * 
	 */
	void updateConectedUsersInServer(Integer timeInMinutes);
	
}
