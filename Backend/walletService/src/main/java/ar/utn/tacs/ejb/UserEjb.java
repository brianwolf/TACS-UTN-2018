package ar.utn.tacs.ejb;

import ar.utn.tacs.user.User;

public interface UserEjb extends GenericEjb{
	
	
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
}
