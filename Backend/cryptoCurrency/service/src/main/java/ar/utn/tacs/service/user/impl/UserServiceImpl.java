package ar.utn.tacs.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.dao.user.UserDao;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.service.external.impl.ExternalServiceMockImpl;
import ar.utn.tacs.service.user.UserService;

public class UserServiceImpl implements UserService{
	
	private static UserService USER_SERVICE = new UserServiceImpl();
	
	@Autowired
	private UserDao userDao;

	public static UserService getInstance() {
		return USER_SERVICE;
	}
	
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
		
		User user = this.userDao.getUserByToken(token);
		List<Coin> updatedCoins = ExternalServiceMockImpl.getInstance().coinMarketCap();
		user.getWallet().updateCoinsValue(updatedCoins);
		
		return user;
	}

	@Override
	public User getUserById(Long userId) {
		return this.userDao.getUserById(userId);
	}
}