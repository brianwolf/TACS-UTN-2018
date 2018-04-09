package ar.utn.tacs.userService.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.model.user.User;
import ar.utn.tacs.userService.dao.UserDao;
import ar.utn.tacs.userService.service.UserService;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public ar.utn.tacs.model.user.User getUserByLogin(String nick, String pass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ar.utn.tacs.model.user.User getUserById(long id) {
		// TODO Auto-generated method stub
		return null;
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
