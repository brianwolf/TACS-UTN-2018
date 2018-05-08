package ar.utn.tacs.dao.admin.impl;

import java.math.BigInteger;

import ar.utn.tacs.dao.admin.AdminDao;
import ar.utn.tacs.dao.impl.GenericAbstractDaoImpl;
import ar.utn.tacs.model.user.User;

public class AdminDaoImpl extends GenericAbstractDaoImpl<User> implements AdminDao{

	@Override
	public User compareBalance(String nickA, String nickB) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigInteger statesLastWeek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigInteger statesLastMonth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigInteger statesAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigInteger statesByBeforeDays(Integer beforeDays) {
		// TODO Auto-generated method stub
		return null;
	}


}
