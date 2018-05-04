package ar.utn.tacs.dao.admin.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import ar.utn.tacs.dao.admin.AdminDao;
import ar.utn.tacs.dao.impl.GenericAbstractDaoImpl;
import ar.utn.tacs.dao.user.impl.UserDaoMockImpl;
import ar.utn.tacs.dao.wallet.impl.WalletDaoMockImpl;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.util.BeanUtil;

public class AdminDaoMockImpl extends GenericAbstractDaoImpl<User> implements AdminDao{

//	public List<Transaction> transactions;
//	public List<User> usersInSession;
	
//	public AdminDaoMockImpl() {
//		transactions = new ArrayList<Transaction>();
//		this.usersInSession = new ArrayList<User>();
//	}
//	
//	@PostConstruct
//	public void init() {
//		BeanUtil.getBean("walletDao", WalletDaoMockImpl.class).getHistory().values().stream().forEach(t -> transactions.addAll(t));;
//		BeanUtil.getBean("userDao", UserDaoMockImpl.class).getSessions().values().stream().forEach(u -> this.usersInSession.add(u));;
//	}
	
	@Override
	public User compareBalance(String nickA, String nickB) {
		User userA = BeanUtil.getBean("userDao", UserDaoMockImpl.class).getUserByNick(nickA);
		User userB = BeanUtil.getBean("userDao", UserDaoMockImpl.class).getUserByNick(nickB);
		
		BigDecimal balanceA = userA.getWallet().getDolarFinalBalance();
		BigDecimal balanceB = userB.getWallet().getDolarFinalBalance();
		
		return balanceA.compareTo(balanceB) > 0? userA : userB;
	}

	@Override
	public List<Transaction> statesToday() {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		BeanUtil.getBean("walletDao", WalletDaoMockImpl.class).getHistory().values().stream().forEach(t -> transactions.addAll(t));;

		return transactions.stream().filter(t -> {
			
			Calendar transactionDate = Calendar.getInstance();
			transactionDate.setTime(t.getDateStart());
			
			Calendar today = Calendar.getInstance();
			today.setTime(t.getDateStart());
			
			return transactionDate.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR);
			
		}).collect(Collectors.toList());
	}

	@Override
	public List<Transaction> statesThreeDays() {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		BeanUtil.getBean("walletDao", WalletDaoMockImpl.class).getHistory().values().stream().forEach(t -> transactions.addAll(t));;

		
		Calendar threeDaysBefore = Calendar.getInstance();
			threeDaysBefore.setTime(new Date());
			threeDaysBefore.add(Calendar.DAY_OF_MONTH, -3);
		
		return transactions.stream().filter(t -> t.getDateStart().after(threeDaysBefore.getTime())).collect(Collectors.toList());
	}

	@Override
	public List<Transaction> statesLastWeek() {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		BeanUtil.getBean("walletDao", WalletDaoMockImpl.class).getHistory().values().stream().forEach(t -> transactions.addAll(t));;		
		
		Calendar lastWeek = Calendar.getInstance();
			lastWeek.setTime(new Date());
			lastWeek.add(Calendar.DAY_OF_MONTH, -7);
		
		return transactions.stream().filter(t -> t.getDateStart().after(lastWeek.getTime())).collect(Collectors.toList());
	}

	@Override
	public List<Transaction> statesLastMonth() {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		BeanUtil.getBean("walletDao", WalletDaoMockImpl.class).getHistory().values().stream().forEach(t -> transactions.addAll(t));;

		
		Calendar lastWeek = Calendar.getInstance();
			lastWeek.setTime(new Date());
			lastWeek.add(Calendar.DAY_OF_MONTH, -30);
		
		return transactions.stream().filter(t -> t.getDateStart().after(lastWeek.getTime())).collect(Collectors.toList());
	}

	@Override
	public List<Transaction> statesStartTimes() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		BeanUtil.getBean("walletDao", WalletDaoMockImpl.class).getHistory().values().stream().forEach(t -> transactions.addAll(t));;
		
		return transactions;
	}
}
