package ar.utn.tacs.service.user.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.dao.user.UserDao;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.commons.UserNotFoundException;
import ar.utn.tacs.model.deposit.Deposit;
import ar.utn.tacs.model.deposit.DepositRest;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.service.admin.AdminService;
import ar.utn.tacs.service.external.ExternalService;
import ar.utn.tacs.service.user.UserService;
import ar.utn.tacs.util.BeanUtil;

public class UserServiceImpl implements UserService{
	
	
	@Autowired
	private UserDao userDao;

	@Override
	public String getTokenByLogin(Login login) throws UtnTacsException {
		return userDao.getTokenByLogin(login);
	}

	@Override
	public void newUser(User user) throws UtnTacsException {
		userDao.newUser(user);
	}

	@Override
	public void logOutUserByToken(String token) {
		userDao.logOutUserByToken(token);
	}

	@Override
	public User getUserByToken(String token) throws UtnTacsException {
		
		User user = this.userDao.getUserByToken(token);
		
		if(user==null) {
			throw new UserNotFoundException();
		}
		
		List<Coin> updatedCoins = BeanUtil.getBean(ExternalService.class).coinMarketCap();
		user.getWallet().updateCoinsValue(updatedCoins);
		
		return user;
	}

	@Override
	public User getUserById(ObjectId id) {
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

	@Override
	public void declareDeposit(String token, DepositRest depositRest) {
		
		try {
			User user = this.getUserByToken(token);
			Deposit deposit = depositRest.toDeposit(user);
			deposit.setState(Deposit.WAITING);
			
			BeanUtil.getBean(AdminService.class).addDeposit(deposit);
			
		} catch (UtnTacsException e) {
			e.printStackTrace();
		}
	}
}