package ar.utn.tacs.walletService.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.user.User;
import ar.utn.tacs.walletService.dao.WalletDao;
import ar.utn.tacs.walletService.service.WalletService;

public class WalletServiceImpl implements WalletService{
	
	@Autowired
	private WalletDao externalDao;

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
