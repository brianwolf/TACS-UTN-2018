package ar.utn.tacs.service.user;

import java.math.BigInteger;
import java.util.List;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.model.admin.DepositRest;
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
	User getUserById(BigInteger id);
	
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
}
