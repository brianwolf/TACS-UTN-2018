package ar.utn.tacs.dao.admin.impl;

import java.math.BigInteger;
import java.util.List;

import ar.utn.tacs.dao.admin.AdminDao;
import ar.utn.tacs.dao.impl.GenericAbstractDaoImpl;
import ar.utn.tacs.model.admin.Deposit;
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

	@Override
	public void addDeposit(Deposit deposit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void approveDeposit(Deposit deposit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejectDeposit(Deposit deposit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Deposit getDepositByDepositNumber(String depositNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Deposit> getDeposits(String statusDescription) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Deposit> getDepositsAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
