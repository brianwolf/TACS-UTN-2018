package ar.utn.tacs.adminService.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.adminService.dao.AdminDao;
import ar.utn.tacs.adminService.service.AdminService;
import ar.utn.tacs.user.User;

public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao adminDao;

	@Override
	public User getUserById(int userId) {
		User user = adminDao.getUserById(userId);
		return user;
	}

	@Override
	public User validateNickAndPass(String nick, String pass) {
		User user = adminDao.validateNickAndPass(nick, pass);
		return user;
	}

}
