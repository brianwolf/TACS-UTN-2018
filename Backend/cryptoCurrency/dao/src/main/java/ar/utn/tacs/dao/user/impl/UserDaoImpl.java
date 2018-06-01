package ar.utn.tacs.dao.user.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.dao.impl.GenericAbstractDaoImpl;
import ar.utn.tacs.dao.user.UserDao;
import ar.utn.tacs.model.commons.UserNotFoundException;
import ar.utn.tacs.model.user.ConnectedUser;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.util.BeanUtil;
import ar.utn.tacs.util.TokenMakerUtil;

public class UserDaoImpl extends GenericAbstractDaoImpl implements UserDao {

	public String getTokenByLogin(Login login) throws UtnTacsException {

		String token = "";

		Map<String, Object> propertiesAndValues = new HashMap<String, Object>();
		propertiesAndValues.put("login.nick", login.getNick());
		propertiesAndValues.put("login.pass", login.getPass());

		User user = this.getByProperties(propertiesAndValues, User.class);
		if (user == null) {
			throw new UserNotFoundException();
		}

		user.getLogin().setLastLogin(new Date());
		this.update(user);

		token = BeanUtil.getBean(TokenMakerUtil.class).makeToken();
		ConnectedUser connectedUser = new ConnectedUser(token, user.getId());

		this.deleteByProperty("idUser", user.getId(), ConnectedUser.class);
		this.insert(connectedUser);

		return token;
	}

	@Override
	public void logOutUserByToken(String token) {
		this.deleteByProperty("token", token, ConnectedUser.class);
	}

	@Override
	public User getUserByToken(String token) {
		ConnectedUser connectedUser = this.getByProperty("token", token, ConnectedUser.class);
		return getById(connectedUser.getIdUser(), User.class);
	}

	@Override
	public void newUser(User user) {
		this.insert(user);
	}

	@Override
	public User getUserByNick(String nick) {
		return getByProperty("login.nick", nick, User.class);
	}

	@Override
	public List<String> getUsersNicksAll() {
		return getPropertyAll("login.nick", String.class, User.class);
	}

	@Override
	public User getUserById(ObjectId id) {
		return getById(id, User.class);
	}
}
