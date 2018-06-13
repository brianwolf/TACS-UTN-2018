package ar.utn.tacs.dao.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import ar.utn.tacs.dao.impl.GenericAbstractDaoImpl;
import ar.utn.tacs.dao.user.UserDao;
import ar.utn.tacs.model.commons.UserNotFoundException;
import ar.utn.tacs.model.role.Role;
import ar.utn.tacs.model.user.ConnectedUser;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.util.BeanUtil;
import ar.utn.tacs.util.HashUtil;

public class UserDaoImpl extends GenericAbstractDaoImpl implements UserDao {

	@Override
	public User getUserByLogin(Login login) throws UserNotFoundException {

		String hashedPass = BeanUtil.getBean(HashUtil.class).getStringHash(login.getPass());
		
		Map<String, Object> propertiesAndValues = new HashMap<String, Object>();
		propertiesAndValues.put("login.nick", login.getNick());
		propertiesAndValues.put("login.pass", hashedPass);
		
		User user = null;
		
		try {
			user = this.getByProperties(propertiesAndValues, User.class);
			
		} catch (NullPointerException e) {
			// TODO: handle exception
			return null;
		}

		return user==null||!user.getLogin().getActive() ? null : user;
	}

	@Override
	public void deleteConnectedUserByToken(String token) {
		this.deleteByProperty("token", token, ConnectedUser.class);
	}

	@Override
	public User getUserByToken(String token) {
		ConnectedUser connectedUser = this.getByProperty("token", token, ConnectedUser.class);
		return getById(connectedUser.getIdUser(), User.class);
	}

	@Override
	public Role getRolByDescription(String descripcion) {

		return getByProperty("description", descripcion, Role.class);
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

	@Override
	public void updateUser(User user) {
		this.update(user);		
	}

	@Override
	public void deleteConnectedUserById(ObjectId id) {
		this.deleteByProperty("idUser", id, ConnectedUser.class);
		
	}

	@Override
	public void insertConnectedUser(ConnectedUser connectedUser) {
		this.insert(connectedUser);
	}

	@Override
	public void insertUser(User user) {
		this.insert(user);
	}

}
