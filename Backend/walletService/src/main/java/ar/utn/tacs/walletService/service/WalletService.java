package ar.utn.tacs.walletService.service;

import ar.utn.tacs.service.GenericService;
import ar.utn.tacs.user.User;

public interface WalletService extends GenericService{
	
	
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
