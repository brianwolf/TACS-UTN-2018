package ar.utn.tacs.dao.user;

import java.util.List;

import ar.utn.tacs.dao.GenericDao;
import ar.utn.tacs.model.user.User;

public interface UserDao extends GenericDao {
	
	public User getUserById(Long userId);

	public User validateNickAndPass(String nick, String pass);
	
	public List<User> getUsers();
}
