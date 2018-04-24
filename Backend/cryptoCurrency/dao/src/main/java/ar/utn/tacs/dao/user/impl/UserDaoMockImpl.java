package ar.utn.tacs.dao.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import ar.utn.tacs.dao.impl.GenericAbstractDaoImpl;
import ar.utn.tacs.dao.user.UserDao;
import ar.utn.tacs.model.role.AdminRole;
import ar.utn.tacs.model.role.Role;
import ar.utn.tacs.model.role.UserRole;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.wallet.Wallet;

public class UserDaoMockImpl extends GenericAbstractDaoImpl<User> implements UserDao{
	
	private HashMap<String, User> sessions = new HashMap<String,User>();
 	
	private List<User> users = new ArrayList<User>();
	
	public UserDaoMockImpl() {
		
		List<Role> roles = new ArrayList<Role>();
			roles.add(new AdminRole());
			roles.add(new UserRole());
			
		this.users.add(new User(1l, "brian", "1234", 0, true, new Wallet(), roles));
		this.users.add(new User(2l, "alexis", "1234", 0, true, new Wallet(), roles));
	}
	
	@Override
	public User getUserById(Long userId) {
		
		return users.stream()
				.filter(user -> user.getId().equals(userId))
				.findFirst()
				.get();
	}

	@Override
	public String getTokenByLogin(String nick, String pass) {
		
		User usuarioEncontrado = users.stream()
				.filter(user -> user.getNick().equals(nick) && user.getPass().equals(pass))
				.findFirst()
				.get(); 
		
		String token = "";
		if (usuarioEncontrado != null) {
			token = this.getRandomHashSession();
			sessions.put(token, usuarioEncontrado);
		}
		
		return token;
	}

	@Override
	public List<User> getUsers() {
		return users;
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
}

