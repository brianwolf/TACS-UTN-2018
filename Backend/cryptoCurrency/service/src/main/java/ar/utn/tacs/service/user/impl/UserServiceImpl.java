package ar.utn.tacs.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.dao.user.UserDao;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.service.user.UserService;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public String getTokenByLogin(String nick, String pass) {
		return userDao.getTokenByLogin(nick, pass);
	}

	@Override
	public List<User> getUsers() {
		 return userDao.getUsers();
	}

	@Override
	public List<Long> getAllUserIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void newUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logOutUserByToken(String token) {
		
	}
}
