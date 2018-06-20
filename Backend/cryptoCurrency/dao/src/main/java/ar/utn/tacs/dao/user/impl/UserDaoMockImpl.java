package ar.utn.tacs.dao.user.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;

import ar.utn.tacs.dao.user.UserDao;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.role.AdminRole;
import ar.utn.tacs.model.role.Role;
import ar.utn.tacs.model.role.UserRole;
import ar.utn.tacs.model.user.ConnectedUser;
import ar.utn.tacs.model.user.Gender;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.Person;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.wallet.CoinAmount;
import ar.utn.tacs.model.wallet.Wallet;

public class UserDaoMockImpl implements UserDao{
	
	private List<User> users = new ArrayList<User>();
	private List<Role> roles = new ArrayList<Role>();

	private List<ConnectedUser> connectedUsers = new ArrayList<ConnectedUser>();
	
	public UserDaoMockImpl() {
		
		this.roles.add(new UserRole());
		this.roles.add(new AdminRole());
		
		this.users.add(new User(new Login("lobezzzno", "1234", true, 0), new Person("brian", "lobo", "lobezzzno@gmail.com", Gender.MALE), roles, new Wallet(newCoinAmounts(), new BigDecimal(10000f))));
		//TENGO 1 DOLAR MAS QUE LOBO, SOY LA POSSSTINHA
		this.users.add(new User(new Login("tostado", "1234", true, 0), new Person("alexis", "taberna", "tostado@gmail.com", Gender.MALE), roles, new Wallet(newCoinAmounts(), new BigDecimal(10001f))));
		//LE DOY SOLO EL ROL DE USER PARA QUE NO MANQUEE NADA XP
		this.users.add(new User(new Login("boberman", "1234", true, 0), new Person("alejandro", "bobero", "bobero@gmail.com", Gender.MALE), roles.subList(0, 1), new Wallet(newCoinAmounts(), new BigDecimal(10000f))));
	}
	
	
	private List<CoinAmount> newCoinAmounts() {
		List<CoinAmount> coinAmounts = new ArrayList<CoinAmount>();
		coinAmounts.add(new CoinAmount(new Coin(new BigInteger("1"), "bitcoin", "BTC"), new BigDecimal(0.005f)));
		coinAmounts.add(new CoinAmount(new Coin(new BigInteger("2"), "ethereum", "ETH"), new BigDecimal(0.08f)));
		return coinAmounts;
	}

	@Override
	public User getUserById(ObjectId id) {
		
		return users.stream()
				.filter(user -> user.getId().equals(id))
				.findFirst().orElse(null);
	}

	@Override
	public User getUserByToken(String token) {
		ConnectedUser connectedUser = this.connectedUsers.stream().filter(c->c.getToken().equals(token)).findFirst().orElse(new ConnectedUser());
		return this.getUserById(connectedUser.getIdUser());
	}
	
	@Override
	public User getUserByNick(String nick) {
		User userresult = users.stream().filter(u -> u.getLogin().getNick().equals(nick)).findFirst().orElse(null);
		return userresult;
	}

	@Override
	public List<String> getUsersNicksAll() {
		List<String> nicks = this.users.stream().map(u -> u.getLogin().getNick()).collect(Collectors.toList());
		return nicks;
	}

	@Override
	public void updateUser(User user) {
		this.users.remove(this.getUserById(user.getId()));
		this.insertUser(user);
	}


	@Override
	public User getUserByLogin(Login login){
		return this.users.stream().filter(u->u.getLogin().getNick().equals(login.getNick())&&u.getLogin().getPass().equals(login.getPass())).findFirst().orElse(null);
	}


	@Override
	public void deleteConnectedUserById(ObjectId id) {
		ConnectedUser connectedUser = this.connectedUsers.stream().filter(c->c.getId().equals(id)).findFirst().orElse(null);
		if(connectedUser!=null) {
			this.connectedUsers.remove(connectedUser);
		}
	}


	@Override
	public void insertConnectedUser(ConnectedUser connectedUser) {
		this.connectedUsers.add(connectedUser);
	}


	@Override
	public void insertUser(User user) {
		this.users.add(user);
	}


	@Override
	public Role getRolByDescription(String descripcion) {
		return this.roles.stream().filter(r->r.getDescription().equals(descripcion)).findFirst().orElse(null);
	}


	@Override
	public void deleteConnectedUserByToken(String token) {
		ConnectedUser connectedUser = this.connectedUsers.stream().filter(c->c.getToken().equals(token)).findFirst().orElse(new ConnectedUser());
		this.deleteConnectedUserById(connectedUser.getId());
	}


	@Override
	public void updateConectedUsersInServer(Integer timeInMinutes) {
		// TODO Auto-generated method stub
		
	}


}

