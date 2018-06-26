package ar.utn.tacs.service.admin.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.dao.admin.AdminDao;
import ar.utn.tacs.dao.user.UserDao;
import ar.utn.tacs.model.commons.ApprovingApprovedDepositException;
import ar.utn.tacs.model.commons.ExistingDepositException;
import ar.utn.tacs.model.commons.NotExistDepositException;
import ar.utn.tacs.model.commons.RejectingApprovedDepositException;
import ar.utn.tacs.model.commons.RejectingRejectedDepositException;
import ar.utn.tacs.model.commons.UserNotFoundException;
import ar.utn.tacs.model.deposit.Deposit;
import ar.utn.tacs.model.email.Mail;
import ar.utn.tacs.model.email.MailBuilder;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.user.UserTransactionRest;
import ar.utn.tacs.service.admin.AdminService;
import ar.utn.tacs.service.external.ExternalService;
import ar.utn.tacs.service.external.impl.ExternalServiceImpl;
import ar.utn.tacs.service.user.UserService;
import ar.utn.tacs.service.wallet.WalletService;
import ar.utn.tacs.util.BeanUtil;

public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	
	@Override
	public User compareBalance(String nickA, String nickB) {
		
		User userA = BeanUtil.getBean(UserDao.class).getUserByNick(nickA);
		User userB = BeanUtil.getBean(UserDao.class).getUserByNick(nickB);

		BigDecimal balanceA = userA.getWallet().getDolarFinalBalance();
		BigDecimal balanceB = userB.getWallet().getDolarFinalBalance();

		return balanceA.compareTo(balanceB) > 0 ? userA : userB;
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
		
		try {
			if (this.adminDao.getDepositByNumber(deposit.getNumber()) != null) {
				throw new ExistingDepositException();
			}
		} catch (NotExistDepositException e) {
			// TODO Auto-generated catch block
		}

		this.adminDao.insertDeposit(deposit);
	}

	@Override
	public void approveDeposit(String depositNumber) throws ApprovingApprovedDepositException, NotExistDepositException{
		
		try {
			Deposit deposit = this.adminDao.getDepositByNumber(depositNumber);
			Deposit depositFounded = this.adminDao.getDepositByNumber(deposit.getNumber());

			if (depositFounded.getState().equals(Deposit.APPROVED)) {
				throw new ApprovingApprovedDepositException();
			}

			depositFounded.setState(Deposit.APPROVED);
			this.adminDao.updateDeposit(depositFounded);
			
			BeanUtil.getBean(WalletService.class).doDeposit(deposit);
			
			this.sendDepositChangeMail(depositFounded);

			
		} catch (ApprovingApprovedDepositException approvingApprovedDepositException) {
			throw approvingApprovedDepositException;
		
		} catch (NotExistDepositException notExistDepositException) {
			throw notExistDepositException;
		
		} catch (Exception e) {
			//aca se tendria que hacer un rollback
		}
	}
	
	private void sendDepositChangeMail(Deposit deposit) {
		Mail mail = MailBuilder.buildDepositMail(deposit);
		BeanUtil.getBean(ExternalService.class).sendMail(mail);
	}

	@Override
	public void rejectDeposit(String depositNumber) throws RejectingRejectedDepositException, RejectingApprovedDepositException, NotExistDepositException {
		
		Deposit deposit = this.adminDao.getDepositByNumber(depositNumber);
		Deposit depositFounded = this.adminDao.getDepositByNumber(deposit.getNumber());

		if (depositFounded.getState().equals(Deposit.APPROVED)) {
			throw new RejectingApprovedDepositException();
		}

		if (depositFounded.getState().equals(Deposit.REJECTED)) {
			throw new RejectingRejectedDepositException();
		}

		depositFounded.setState(Deposit.REJECTED);
		this.adminDao.updateDeposit(depositFounded);
		
		this.sendDepositChangeMail(depositFounded);
	}

	@Override
	public List<Deposit> getDeposits(String statusDescription) {
		return this.adminDao.getDeposits(statusDescription);
	}
	
	@Override
	public List<Deposit> getDepositsAll() {
		return this.adminDao.getDepositsAll();
	}

	@Override
	public void convertUserToAdmin(String nick) throws UserNotFoundException {
		BeanUtil.getBean(UserService.class).convertUserToAdmin(nick);
	}
}
