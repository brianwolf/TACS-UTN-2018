package ar.utn.tacs.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.dao.admin.AdminDao;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.service.admin.AdminService;
import ar.utn.tacs.service.user.UserService;
import ar.utn.tacs.util.BeanUtil;

public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	
	@Override
	public User compareBalance(String nickA, String nickB) {
		return adminDao.compareBalance(nickA, nickB);
	}

	@Override
	public List<Transaction> statesLastWeek() {
		return adminDao.statesLastWeek();
	}

	@Override
	public List<Transaction> statesLastMonth() {
		return adminDao.statesLastMonth();
	}

	@Override
	public List<Transaction> statesGetAll() {
		return adminDao.statesAll();
	}

	@Override
	public List<Transaction> statesByBeforeDays(Integer beforeDays) {
		return adminDao.statesByBeforeDays(beforeDays);
	}

	@Override
	public List<String> getUsersNickAll() {
		return BeanUtil.getBean(UserService.class).getUsersNickAll();
	}
	
	@Override
	public User getUser(String nick) {
		return BeanUtil.getBean(UserService.class).getUser(nick);
	}

}
