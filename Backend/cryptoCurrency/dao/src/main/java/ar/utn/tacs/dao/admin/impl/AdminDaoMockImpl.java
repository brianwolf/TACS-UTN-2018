package ar.utn.tacs.dao.admin.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.dao.admin.AdminDao;
import ar.utn.tacs.dao.impl.GenericAbstractDaoImpl;
import ar.utn.tacs.dao.user.UserDao;
import ar.utn.tacs.dao.user.impl.UserDaoMockImpl;
import ar.utn.tacs.dao.wallet.WalletDao;
import ar.utn.tacs.dao.wallet.impl.WalletDaoMockImpl;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;

public class AdminDaoMockImpl extends GenericAbstractDaoImpl<User> implements AdminDao{

	@Autowired
	private WalletDao walletDao;
	
	@Autowired
	private UserDao userDao;
	
	public List<Transaction> transactions;
	public List<User> users;
	
	public AdminDaoMockImpl() {
		transactions = new ArrayList<Transaction>();
		((WalletDaoMockImpl)walletDao).history.values().forEach(transaction -> this.transactions.add((Transaction) transaction));
		
		this.users = new ArrayList<User>();
		this.users = ((UserDaoMockImpl) userDao).users;
	}
	
	@Override
	public User compareBalance(String nickA, String nickB) {
		User userA = (User) this.users.stream().filter(user -> user.getNick().equals(nickA));
		User userB = (User) this.users.stream().filter(user -> user.getNick().equals(nickB));
		
		return this.mayorCapitalEntre(userA, userB);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Transaction> statesToday() {
		return (List<Transaction>) this.transactions.stream().filter(transaction -> transaction.getDate().getDay() == new Date().getDate());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> statesThreeDays() {
		Calendar threeDays = Calendar.getInstance();
		threeDays.setTime(new Date());
		threeDays.add(Calendar.DAY_OF_MONTH, -3);
		
		return (List<Transaction>) this.transactions.stream().filter(transaction -> transaction.getDate().after(threeDays.getTime()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> statesLastWeek() {
		Calendar lastWeek = Calendar.getInstance();
		lastWeek.setTime(new Date());
		lastWeek.add(Calendar.DAY_OF_MONTH, -7);
		
		return (List<Transaction>) this.transactions.stream().filter(transaction -> transaction.getDate().after(lastWeek.getTime()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> statesLastMonth() {
		Calendar lastWeek = Calendar.getInstance();
		lastWeek.setTime(new Date());
		lastWeek.add(Calendar.DAY_OF_MONTH, -30);
		
		return (List<Transaction>) this.transactions.stream().filter(transaction -> transaction.getDate().after(lastWeek.getTime()));
	}

	@Override
	public List<Transaction> statesStartTimes() {
		return this.transactions;
	}
	
	private User mayorCapitalEntre(User userA, User userB) {
		return userA;
	}

}
