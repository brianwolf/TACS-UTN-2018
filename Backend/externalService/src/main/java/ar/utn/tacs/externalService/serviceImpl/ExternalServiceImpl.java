package ar.utn.tacs.externalService.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.externalService.dao.ExternalDao;
import ar.utn.tacs.externalService.service.ExternalService;
import ar.utn.tacs.user.User;

public class ExternalServiceImpl implements ExternalService{
	
	@Autowired
	private ExternalDao externalDao;

	@Override
	public User getUserById(int userId) {
		User user = externalDao.getUserById(userId);
		return user;
	}

	@Override
	public User validateNickAndPass(String nick, String pass) {
		User user = externalDao.validateNickAndPass(nick, pass);
		return user;
	}

}
