package ar.utn.tacs.dao.admin;

import ar.utn.tacs.dao.GenericDao;
import ar.utn.tacs.model.user.User;

public interface AdminDao extends GenericDao {

	/**
	 * @param userId
	 * @return 
	 */
	public User getUserById(int userId);

	/**
	 * @param nick
	 * @param pass
	 * @return
	 */
	public User validateNickAndPass(String nick, String pass);
}
