package ar.utn.tacs.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.dao.admin.AdminDao;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.service.admin.AdminService;

public class AdminServiceImpl implements AdminService {

	private static AdminService ADMIN_SERVICE = new AdminServiceImpl();
	
	@Autowired
	private AdminDao adminDao;
	
	public static AdminService getInstance() {
		return ADMIN_SERVICE;
	}

	@Override
	public User compareBalance(String nickA, String nickB) {
		return null;
	}

	@Override
	public List<Transaction> statesToday() {
		return adminDao.statesToday();
	}

	@Override
	public List<Transaction> statesThreeDays() {
		return adminDao.statesThreeDays();
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
	public List<Transaction> statesStartTimes() {
		return adminDao.statesStartTimes();
	}

}
