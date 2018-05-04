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
	public List<Transaction> statesLastWeek() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		BeanUtil.getBean("walletDao", WalletDaoMockImpl.class).getHistory().values().stream().forEach(t -> transactions.addAll(t));;

		return transactions.stream().filter(t -> {
			
			Calendar transactionDate = Calendar.getInstance();
				transactionDate.setTime(t.getDateStart());
			
			Calendar minDate = Calendar.getInstance();
				minDate.setTime(new Date());
			
			return transactionDate.get(Calendar.WEEK_OF_MONTH) == minDate.get(Calendar.WEEK_OF_MONTH);
			
		}).collect(Collectors.toList());
	}

	@Override
	public List<Transaction> statesLastMonth() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		BeanUtil.getBean("walletDao", WalletDaoMockImpl.class).getHistory().values().stream().forEach(t -> transactions.addAll(t));;

		return transactions.stream().filter(t -> {
			
			Calendar transactionDate = Calendar.getInstance();
				transactionDate.setTime(t.getDateStart());
			
			Calendar minDate = Calendar.getInstance();
				minDate.setTime(new Date());
			
			return transactionDate.get(Calendar.MONTH) == minDate.get(Calendar.MONTH);
			
		}).collect(Collectors.toList());
	}

	@Override
	public List<Transaction> statesAll() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		BeanUtil.getBean("walletDao", WalletDaoMockImpl.class).getHistory().values().stream().forEach(t -> transactions.addAll(t));;
		
		return transactions;
	}

	@Override
	public List<Transaction> statesByBeforeDays(Integer beforeDays) {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		BeanUtil.getBean("walletDao", WalletDaoMockImpl.class).getHistory().values().stream().forEach(t -> transactions.addAll(t));;

		return transactions.stream().filter(t -> {
			
			Calendar transactionDate = Calendar.getInstance();
				transactionDate.setTime(t.getDateStart());
			
			Calendar minDate = Calendar.getInstance();
				minDate.setTime(new Date());
			
			if (beforeDays != null) {
				minDate.add(Calendar.DAY_OF_YEAR, -beforeDays);
			}
			
			return transactionDate.get(Calendar.DAY_OF_YEAR) >= minDate.get(Calendar.DAY_OF_YEAR);
			
		}).collect(Collectors.toList());
	}
	
}
