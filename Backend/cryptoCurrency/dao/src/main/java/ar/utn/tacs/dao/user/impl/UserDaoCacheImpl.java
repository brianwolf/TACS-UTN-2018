package ar.utn.tacs.dao.user.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;

import ar.utn.tacs.dao.GenericDao;
import ar.utn.tacs.dao.impl.GenericAbstractDaoCacheImpl;
import ar.utn.tacs.dao.user.UserDao;
import ar.utn.tacs.model.commons.UserNotFoundException;
import ar.utn.tacs.model.role.Role;
import ar.utn.tacs.model.user.ConnectedUser;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;

public class UserDaoCacheImpl extends GenericAbstractDaoCacheImpl<UserDao> implements UserDao {

	private static final String USERS_CACHE = "USERS";
	private static final String CONNECTED_USERS_CACHE = "CONNECTED_USERS";

	private UserDaoCacheImpl(GenericDao genericDao) {
		super(genericDao);

		super.putDBCache(USERS_CACHE);
		super.putDBCache(CONNECTED_USERS_CACHE);
	}

	private void updateCacheByToken(String token) {
		ConnectedUser connectedUser = this.getByProperty("token", token, ConnectedUser.class);
		connectedUser.setTimeConnecting(new Date());
		this.addDataInCache(CONNECTED_USERS_CACHE, connectedUser);
		
		User user = genericDao.getUserById(connectedUser.getIdUser());
		this.addDataInCache(USERS_CACHE, user);
		
		this.executeAsynchronously(() -> {
			genericDao.deleteConnectedUserById(connectedUser.getId());
			genericDao.insertConnectedUser(connectedUser);
		});
	}

	private ConnectedUser getConnectedUserInCacheByToken(String token) {

		List<ConnectedUser> cacheConnectedUsers = this.getListCache(CONNECTED_USERS_CACHE, ConnectedUser.class);
		
		ConnectedUser connectedUser = cacheConnectedUsers.stream().filter(cu -> cu.getToken().equals(token)).findFirst().isPresent()?
				cacheConnectedUsers.stream().filter(cu -> cu.getToken().equals(token)).findFirst().get() :
				null;

		return connectedUser;
	}

	private User getUserInCacheById(ObjectId objectId) {

		List<User> cacheUsers = this.getListCache(USERS_CACHE, User.class);
		User user = cacheUsers.stream().filter(u -> u.getId().equals(objectId)).findFirst().get();

		return user;
	}

	private User getUserInCacheByToken(String token) {
		ConnectedUser connectedUser = this.getConnectedUserInCacheByToken(token);
		return this.getUserInCacheById(connectedUser.getIdUser());
	}

	@Override
	public void deleteConnectedUserByToken(String token) {
		List<ConnectedUser> connectedUsersCache = this.getListCache(CONNECTED_USERS_CACHE, ConnectedUser.class);
		connectedUsersCache.removeIf(cu -> cu.getToken().equals(token));

		this.executeAsynchronously(() -> {
			genericDao.deleteConnectedUserByToken(token);
		});

	}

	@Override
	public User getUserByToken(String token) {

		ConnectedUser connectedUser = this.getConnectedUserInCacheByToken(token);

		if (connectedUser == null) {
			this.updateCacheByToken(token);
			return this.getUserInCacheByToken(token);
		}

		ConnectedUser connectedUserInDB =  this.getById(connectedUser.getId(), ConnectedUser.class);
		if (connectedUserInDB == null) {
			return null;	
		}
		
		Date lastUpdate  = this.getById(connectedUser.getId(), ConnectedUser.class).getTimeConnecting();
		if (connectedUser.getTimeConnecting().before(lastUpdate)) {
			this.updateCacheByToken(token);
			return this.getUserInCacheByToken(token);
		}

		return this.getUserInCacheById(connectedUser.getIdUser());
	}

	@Override
	public User getUserById(ObjectId id) {

		User user = this.getUserInCacheById(id);
		if (user == null) {
			return this.genericDao.getUserById(id);
		}
		return user;
	}

	@Override
	public User getUserByNick(String nick) {

		List<User> userCache = this.getListCache(USERS_CACHE, User.class);
		User user = userCache.stream().filter(u -> u.getLogin().getNick().equals(nick)).findFirst().get();
		if (user == null) {
			return this.genericDao.getUserByNick(nick);
		}
		return user;
	}

	@Override
	public List<String> getUsersNicksAll() {
		return this.genericDao.getUsersNicksAll();
	}

	@Override
	public void updateUser(User user) {
		this.genericDao.updateUser(user);
	}

	@Override
	public User getUserByLogin(Login login) throws UserNotFoundException {

		User user = this.genericDao.getUserByLogin(login);
		this.addDataInCache(USERS_CACHE, user);
		return user;
	}

	@Override
	public void deleteConnectedUserById(ObjectId id) {
		List<ConnectedUser> connectedUsersCache = this.getListCache(CONNECTED_USERS_CACHE, ConnectedUser.class);
		connectedUsersCache.removeIf(cu -> cu.getId().equals(id));

		this.executeAsynchronously(() -> {
			genericDao.deleteConnectedUserById(id);
		});
	}

	@Override
	public void insertConnectedUser(ConnectedUser connectedUser) {

		ConnectedUser connectedUserInCache = this.getConnectedUserInCacheByToken(connectedUser.getToken());
		if (connectedUserInCache == null) {
			this.addDataInCache(CONNECTED_USERS_CACHE, connectedUser);
		}

		List<ConnectedUser> connectedUsersCache = this.getListCache(CONNECTED_USERS_CACHE, ConnectedUser.class);
		connectedUsersCache.remove(connectedUserInCache);
		connectedUsersCache.add(connectedUser);
		
		this.executeAsynchronously(() -> {
			genericDao.insertConnectedUser(connectedUser);
		});
	}

	@Override
	public void insertUser(User user) {
		this.addDataInCache(USERS_CACHE, user);
		
		this.executeAsynchronously(() -> {
			genericDao.insertUser(user);
		}); 
	}

	@Override
	public Role getRolByDescription(String descripcion) {
		return genericDao.getRolByDescription(descripcion);
	}

	@Override
	public void updateConectedUsersInServer(Integer timeInMinutes) {
		Calendar minTime = Calendar.getInstance();
		minTime.setTime(new Date());
		minTime.add(Calendar.MINUTE, -timeInMinutes);

		List<ConnectedUser> connectedUsersCache = this.getListCache(CONNECTED_USERS_CACHE, ConnectedUser.class);
		List<ConnectedUser> connectedUsersToRemove = connectedUsersCache.stream().filter(cu -> cu.getTimeConnecting().before(minTime.getTime())).collect(Collectors.toList());;
		connectedUsersCache.removeAll(connectedUsersToRemove);
		
		List<User> usersCache = this.getListCache(USERS_CACHE, User.class);
		usersCache.removeIf(u -> connectedUsersToRemove.stream().anyMatch(cu -> cu.getIdUser().equals(u.getId())));
		
		this.executeAsynchronously(() -> {
			genericDao.updateConectedUsersInServer(timeInMinutes);
		});
	}

}
