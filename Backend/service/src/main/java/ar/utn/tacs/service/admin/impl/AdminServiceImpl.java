package ar.utn.tacs.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.dao.admin.AdminDao;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.service.admin.AdminService;

public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	@Override
	public User compareBalance(long idUserA, long idUserB) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> statesToday() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> statesThreeDays() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> statesLastWeek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> statesLastMonth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> statesStartTimes() {
		// TODO Auto-generated method stub
		return null;
	}

}
