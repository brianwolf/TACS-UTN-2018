package ar.utn.tacs.dao.user.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import ar.utn.tacs.dao.user.UserDao;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.role.AdminRole;
import ar.utn.tacs.model.role.Role;
import ar.utn.tacs.model.role.UserRole;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.Person;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.wallet.CoinAmount;
import ar.utn.tacs.model.wallet.Wallet;

public class UserDaoMockImpl implements UserDao{
	
	private HashMap<String, User> sessions = new HashMap<String,User>();
 	
	private List<User> users = new ArrayList<User>();
	private List<Role> roles = new ArrayList<Role>();
	
	public UserDaoMockImpl() {
		
		this.roles.add(new UserRole(null));
		this.roles.add(new AdminRole(null));
		
		this.users.add(new User(new BigInteger("1"), new Login("lobezzzno", "1234", true, 0), new Person("brian", "lobo", "lobezzzno@gmail.com"), roles, new Wallet(newCoinAmounts(), new BigDecimal(10000f))));
		//TENGO ! DOLAR MAS QUE LOBO, SOY LA POSSSTINHA
		this.users.add(new User(new BigInteger("2"), new Login("tostado", "1234", true, 0), new Person("alexis", "taberna", "tostado@gmail.com"), roles, new Wallet(newCoinAmounts(), new BigDecimal(10001f))));
		//LE DOY SOLO EL ROL DE USER PARA QUE NO MANQUEE NADA XP
		this.users.add(new User(new BigInteger("3"), new Login("boberman", "1234", true, 0), new Person("alejandro", "bobero", "bobero@gmail.com"), roles.subList(0, 1), new Wallet(newCoinAmounts(), new BigDecimal(10000f))));
	}
	
	
	private List<CoinAmount> newCoinAmounts() {
		List<CoinAmount> coinAmounts = new ArrayList<CoinAmount>();
		coinAmounts.add(new CoinAmount(new Coin(new BigInteger("1"), "bitcoin", "BTC"), new BigDecimal(0.005f)));
		coinAmounts.add(new CoinAmount(new Coin(new BigInteger("2"), "ethereum", "ETH"), new BigDecimal(0.08f)));
		return coinAmounts;
	}


	public HashMap<String, User> getSessions() {
		return sessions;
	}

	public void setSessions(HashMap<String, User> sessions) {
		this.sessions = sessions;
	}

	@Override
	public User getUserById(BigInteger userId) {
		
		return users.stream()
				.filter(user -> user.getId().equals(userId))
				.findFirst()
				.get();
	}

	@Override
	public String getTokenByLogin(Login login) {
		
		User usuarioEncontrado = users.stream()
				.filter(user -> user.getLogin().equals(login))
				.findFirst()
				.get(); 
		
		//ELIMINA SESION
		Optional<String> key = sessions.keySet().stream().filter(k->sessions.get(k).getId().equals(usuarioEncontrado.getId())).findFirst();
		logOutUserByToken(key.hashCode()==0 ? null : key.get());
		
		String token = "";
		if (usuarioEncontrado != null) {
			
			usuarioEncontrado.getLogin().setLastLogin(new Date());
			token = this.getRandomHashSession();
			sessions.put(token, usuarioEncontrado);
		}
		
		return token;
	}
	
	private String getRandomHashSession() {
        
		String baseCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder hashResult = new StringBuilder();
        Random rnd = new Random();
        
        while (hashResult.length() < 32) { // length of the random string.
            int index = (int) (rnd.nextFloat() * baseCharacters.length());
            hashResult.append(baseCharacters.charAt(index));
        }
        
        return hashResult.toString();
    }

	public void logOutUserByToken(String token) {
		this.sessions.remove(token);
	}

	@Override
	public User getUserByToken(String token) {
		return sessions.get(token);
	}
	
	private Boolean existsUser(User user) {
		return this.users.stream().anyMatch(u->u.getLogin().equals(user.getLogin()));
	}

	@Override
	public void newUser(User user) {
		
		if(existsUser(user)) {
			throw new RuntimeException();
		}
		
		user.getLogin().setActive(true);
		user.getLogin().setTries(0);
		user.setWallet(new Wallet());
		user.setRoles(roles.subList(0, 1));
		user.setId(BigInteger.valueOf(this.users.size()+1));
		
		this.users.add(user);
		
	}

	@Override
	public User getUserByNick(String nick) {
		User userresult = users.stream().filter(u -> u.getLogin().getNick().equals(nick)).findFirst().get();
		return userresult;
	}

	@Override
	public List<String> getUsersNicksAll() {
		List<String> nicks = this.users.stream().map(u -> u.getLogin().getNick()).collect(Collectors.toList());
		return nicks;
	}
}

