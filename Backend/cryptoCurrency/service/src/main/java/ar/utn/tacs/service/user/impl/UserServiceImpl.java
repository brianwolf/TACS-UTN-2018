package ar.utn.tacs.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.dao.user.UserDao;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.service.user.UserService;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public String getTokenByLogin(Login login) {
		return userDao.getTokenByLogin(login);
	}

	@Override
	public void newUser(User user) {
		userDao.newUser(user);
	}

	@Override
	public void logOutUserByToken(String token) {
		userDao.logOutUserByToken(token);
	}

	@Override
	public User getUserByToken(String token) {
		return this.userDao.getUserByToken(token);
	}

	@Override
	public User getUserById(Long userId) {
		return this.userDao.getUserById(userId);
	}
}