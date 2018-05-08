package ar.utn.tacs.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.dao.user.UserDao;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.service.external.ExternalService;
import ar.utn.tacs.service.user.UserService;
import ar.utn.tacs.util.BeanUtil;

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
		
		User user = this.userDao.getUserByToken(token);
		List<Coin> updatedCoins = BeanUtil.getBean(ExternalService.class).coinMarketCap();
		user.getWallet().updateCoinsValue(updatedCoins);
		
		return user;
	}

	@Override
	public User getUserById(Long id) {
		return this.userDao.getUserById(id);
	}

	@Override
	public User getUser(String nick) {
		
		User user = null;
		
		if (!nick.equals("")) {
			user = this.userDao.getUserByNick(nick);
		}
		
		return user;
	}

	@Override
	public List<String> getUsersNickAll() {
		return this.userDao.getUsersNicksAll();
	}
}