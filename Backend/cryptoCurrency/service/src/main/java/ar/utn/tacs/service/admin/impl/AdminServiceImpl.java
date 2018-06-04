package ar.utn.tacs.service.admin.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.dao.admin.AdminDao;
import ar.utn.tacs.model.commons.ExistingDepositException;
import ar.utn.tacs.model.commons.NotExistDepositException;
import ar.utn.tacs.model.commons.RejectingApprovedDepositException;
import ar.utn.tacs.model.commons.RejectingRejectedDepositException;
import ar.utn.tacs.model.deposit.Deposit;
import ar.utn.tacs.model.commons.ApprovingApprovedDepositException;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.user.UserTransactionRest;
import ar.utn.tacs.service.admin.AdminService;
import ar.utn.tacs.service.user.UserService;
import ar.utn.tacs.service.wallet.WalletService;
import ar.utn.tacs.util.BeanUtil;

public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	
	@Override
	public User compareBalance(String nickA, String nickB) {
		return adminDao.compareBalance(nickA, nickB);
	}

	@Override
	public BigInteger statesLastWeek() {
		return adminDao.statesLastWeek();
	}

	@Override
	public BigInteger statesLastMonth() {
		return adminDao.statesLastMonth();
	}

	@Override
	public BigInteger statesAll() {
		return adminDao.statesAll();
	}

	@Override
	public BigInteger statesByBeforeDays(Integer beforeDays) {
		return adminDao.statesByBeforeDays(beforeDays);
	}

	@Override
	public List<String> getUsersNickAll() {
		return BeanUtil.getBean(UserService.class).getUsersNickAll();
	}
	
	@Override
	public UserTransactionRest getUser(String nick) {
		
		User user = BeanUtil.getBean(UserService.class).getUser(nick);
		
		UserTransactionRest userTransactionRest = new UserTransactionRest();
			userTransactionRest.setUser(user);
			userTransactionRest.addTransactionCounter("today", this.statesByBeforeDays(user,0));
			userTransactionRest.addTransactionCounter("all", this.statesAll(user));
		
		return userTransactionRest;
	}

	private BigInteger statesAll(User user) {
		return adminDao.statesAll(user);
	}

	private BigInteger statesByBeforeDays(User user, Integer days) {
		return adminDao.statesByBeforeDays(user,days);
	}

	@Override
	public void addDeposit(Deposit deposit) throws ExistingDepositException {
		this.adminDao.addDeposit(deposit);
	}

	@Override
	public void approveDeposit(String depositNumber) throws ApprovingApprovedDepositException, NotExistDepositException{
		
		try {
			Deposit deposit = this.adminDao.getDepositByDepositNumber(depositNumber);
			this.adminDao.approveDeposit(deposit);	
			
			BeanUtil.getBean(WalletService.class).doDeposit(deposit);
			
		} catch (ApprovingApprovedDepositException approvingApprovedDepositException) {
			throw approvingApprovedDepositException;
		
		} catch (NotExistDepositException notExistDepositException) {
			throw notExistDepositException;
		
		} catch (Exception e) {
			//aca se tendria que hacer un rollback
		}
	}
	
	@Override
	public void rejectDeposit(String depositNumber) throws RejectingRejectedDepositException, RejectingApprovedDepositException, NotExistDepositException {
		
		Deposit deposit = this.adminDao.getDepositByDepositNumber(depositNumber);
		this.adminDao.rejectDeposit(deposit);
	}

	@Override
	public List<Deposit> getDeposits(String statusDescription) {
		return this.adminDao.getDeposits(statusDescription);
	}
	
	@Override
	public List<Deposit> getDepositsAll() {
		return this.adminDao.getDepositsAll();
	}

}
