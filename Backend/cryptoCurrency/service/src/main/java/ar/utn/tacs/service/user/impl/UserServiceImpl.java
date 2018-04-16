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
	public User getUserByLogin(String nick, String pass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		return null;
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
}
