package ar.utn.tacs.dao.user.impl;

import java.math.BigInteger;
import java.util.List;

import ar.utn.tacs.dao.impl.GenericAbstractDaoImpl;
import ar.utn.tacs.dao.user.UserDao;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;

public class UserDaoImpl extends GenericAbstractDaoImpl<User> implements UserDao{
	
	UserDaoMockImpl userDaoMock = new UserDaoMockImpl();
	
	public User getUserById(BigInteger userId) {
		return getById(userId, User.class);
	}

	public String getTokenByLogin(Login login) {
		
		return userDaoMock.getTokenByLogin(login);
	}

	@Override
	public void logOutUserByToken(String token) {
		userDaoMock.logOutUserByToken(token);
		
	}

	@Override
	public User getUserByToken(String token) {
		return userDaoMock.getUserByToken(token);
	}

	@Override
	public void newUser(User user) {
		if (!mongoTemplate.collectionExists(User.class)) {
			mongoTemplate.createCollection(User.class);
		}
		mongoTemplate.insert(user);	
	}

	@Override
	public User getUserByNick(String nick) {
		return getByProperty("login.nick",nick, User.class);
	}

	@Override
	public List<String> getUsersNicksAll() {
		return getPropertyAll("login.nick", String.class, User.class);
	}
}
