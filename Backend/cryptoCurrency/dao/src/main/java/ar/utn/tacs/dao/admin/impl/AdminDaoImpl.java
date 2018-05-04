package ar.utn.tacs.dao.admin.impl;

import java.util.List;

import ar.utn.tacs.dao.admin.AdminDao;
import ar.utn.tacs.dao.impl.GenericAbstractDaoImpl;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;

public class AdminDaoImpl extends GenericAbstractDaoImpl<User> implements AdminDao{

	@Override
	public User compareBalance(String nickA, String nickB) {
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
	public List<Transaction> statesAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> statesByBeforeDays(Integer beforeDays) {
		// TODO Auto-generated method stub
		return null;
	}

}
