package ar.utn.tacs.dao.user.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import ar.utn.tacs.dao.impl.GenericAbstractDaoImpl;
import ar.utn.tacs.dao.user.UserDao;
import ar.utn.tacs.model.user.ConnectedUser;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.util.BeanUtil;
import ar.utn.tacs.util.TokenMakerUtil;

public class UserDaoImpl extends GenericAbstractDaoImpl<User> implements UserDao {

//	UserDaoMockImpl userDaoMock = new UserDaoMockImpl();

	public User getUserById(BigInteger userId) {
		return getById(userId, User.class);
	}

	public String getTokenByLogin(Login login) {
		
		if (!mongoTemplate.collectionExists(User.class)) {
			mongoTemplate.createCollection(User.class);
		}
		
		String token = "";
		
		Map<String, Object> propertiesAndValues = new HashMap<String, Object>();
		propertiesAndValues.put("login.nick", login.getNick());
		propertiesAndValues.put("login.pass", login.getPass());
		
		User user = super.getByProperties(propertiesAndValues, User.class);
		if (user == null) {
			return token;
		}
		
		user.getLogin().setLastLogin(new Date());
		super.update(user);
		
		token = BeanUtil.getBean(TokenMakerUtil.class).makeToken();
		ConnectedUser connectedUser = new ConnectedUser(token, user.getId());
		mongoTemplate.insert(connectedUser);
		
		return token;
	}

	@Override
	public void logOutUserByToken(String token) {
		
		Query q = new Query();
		q.addCriteria(Criteria.where("token").is(token));
		mongoTemplate.remove(q, ConnectedUser.class);
	}

	@Override
	public User getUserByToken(String token) {
		
		Query q = new Query();
		q.addCriteria(Criteria.where("token").is(token));
		ConnectedUser connectedUser = mongoTemplate.findOne(q, ConnectedUser.class);
		
		return getById(connectedUser.getId(), User.class);
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
		return getByProperty("login.nick", nick, User.class);
	}

	@Override
	public List<String> getUsersNicksAll() {
		return getPropertyAll("login.nick", String.class, User.class);
	}
}
