package ar.utn.tacs.ejbImpl;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.dao.UserDao;
import ar.utn.tacs.ejb.UserEjb;
import ar.utn.tacs.usuario.User;

public class UserServiceEjbImpl implements UserEjb{
	
	@Autowired
	private UserDao userDao;

	@Override
	public User getUserById(int userId) {
		User user = userDao.getUserById(userId);
		return user;
	}

	@Override
	public User validateNickAndPass(String nick, String pass) {
		User user = userDao.validateNickAndPass(nick, pass);
		return user;
	}

}
