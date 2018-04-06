package ar.utn.tacs.adminService.service;

import ar.utn.tacs.service.GenericService;
import ar.utn.tacs.user.User;

public interface AdminService extends GenericService{
	
	
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
