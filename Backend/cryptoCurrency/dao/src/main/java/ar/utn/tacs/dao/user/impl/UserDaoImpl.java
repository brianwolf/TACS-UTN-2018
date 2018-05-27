package ar.utn.tacs.dao.user.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import ar.utn.tacs.dao.impl.GenericAbstractDaoImpl;
import ar.utn.tacs.dao.user.UserDao;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;

public class UserDaoImpl extends GenericAbstractDaoImpl<User> implements UserDao{
	
	public User getUserById(BigInteger userId) {
		User user = new User();
		user.setId(userId);
//		user.setApellido("Tagrande");
//		user.setNombre("Juancho");
//		user.setNick("juancito");
		return user;
	}

	public String getTokenByLogin(Login login) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
//		Session session = this.sessionFactory.openSession();
//		StoredProcedureQuery query = session.createStoredProcedureQuery("sp_traer_users");
//		Query query = session.getNamedQuery("findUsers");
		List<User> userList = null;
//		userList = query.getResultList();
//		session.close();
		return userList;
	}

	@Override
	public void logOutUserByToken(String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserByToken(String token) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getUsersNicksAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
