package ar.utn.tacs.dao.user.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import ar.utn.tacs.dao.impl.GenericAbstractDaoImpl;
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

public class UserDaoMockImpl extends GenericAbstractDaoImpl<User> implements UserDao{
	
	private HashMap<String, User> sessions = new HashMap<String,User>();
 	
	private List<User> users = new ArrayList<User>();
	private List<Role> roles = new ArrayList<Role>();
	
	public UserDaoMockImpl() {
		
		this.roles.add(new AdminRole());
		this.roles.add(new UserRole());
		
		List<CoinAmount> coinAmaunts = new ArrayList<CoinAmount>();
			coinAmaunts.add(new CoinAmount(new Coin(1l, "bitcoin", "BTC"), new BigDecimal(0.005f)));
			coinAmaunts.add(new CoinAmount(new Coin(2l, "ethereum", "ETH"), new BigDecimal(0.08f)));
			
		this.users.add(new User(1l, new Login("lobezzzno", "1234", true, 0), new Person("brian", "lobo", "lobezzzno@gmail.com"), roles, new Wallet(coinAmaunts, new BigDecimal(10000f))));
		this.users.add(new User(2l, new Login("tostado", "1234", true, 0), new Person("alexis", "taberna", "tostado@gmail.com"), roles, new Wallet(coinAmaunts, new BigDecimal(10000f))));
	}
	
	
	public HashMap<String, User> getSessions() {
		return sessions;
	}

	public void setSessions(HashMap<String, User> sessions) {
		this.sessions = sessions;
	}

	@Override
	public User getUserById(Long userId) {
		
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
		user.setRoles(roles);
		user.setId(Long.valueOf(this.users.size()+1));
		
		this.users.add(user);
		
	}

	@Override
	public User getUserByNick(String nick) {
		User userresult = this.sessions.values().stream().filter(u -> u.getLogin().getNick().equals(nick)).findFirst().get();
		return userresult;
	}
}

