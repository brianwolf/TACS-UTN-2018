package ar.utn.tacs.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.dao.UserDao;
import ar.utn.tacs.service.UserService;
import ar.utn.tacs.user.User;

public class UserServiceImpl implements UserService{
	
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
