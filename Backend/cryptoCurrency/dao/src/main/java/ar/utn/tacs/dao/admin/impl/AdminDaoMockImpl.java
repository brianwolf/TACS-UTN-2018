package ar.utn.tacs.dao.admin.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import ar.utn.tacs.dao.admin.AdminDao;
import ar.utn.tacs.dao.wallet.impl.WalletDaoMockImpl;
import ar.utn.tacs.model.commons.NotExistDepositException;
import ar.utn.tacs.model.deposit.Deposit;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.util.BeanUtil;

public class AdminDaoMockImpl implements AdminDao{
	
	private List<Deposit> listDeposit = new ArrayList<Deposit>();
	
	public List<Deposit> getListDeposit() {
		return listDeposit;
	}

	public void setListDeposit(List<Deposit> listDeposit) {
		this.listDeposit = listDeposit;
	}


	@Override
	public BigInteger statesLastWeek() {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		BeanUtil.getBean("walletDao", WalletDaoMockImpl.class).getHistory().values().stream().forEach(t -> transactions.addAll(t));

		List<Transaction> transactionsFilter = transactions.stream().filter(t -> {
			
			Calendar transactionDate = Calendar.getInstance();
				transactionDate.setTime(t.getDateStart());
			
			Calendar minDate = Calendar.getInstance();
				minDate.setTime(new Date());
			
			return transactionDate.get(Calendar.WEEK_OF_MONTH) == minDate.get(Calendar.WEEK_OF_MONTH);
			
		}).collect(Collectors.toList());
		
		return BigInteger.valueOf(transactionsFilter.size());
	}

	@Override
	public BigInteger statesLastMonth() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		BeanUtil.getBean("walletDao", WalletDaoMockImpl.class).getHistory().values().stream().forEach(t -> transactions.addAll(t));

		List<Transaction> transactionsFilter = transactions.stream().filter(t -> {
			
			Calendar transactionDate = Calendar.getInstance();
				transactionDate.setTime(t.getDateStart());
			
			Calendar minDate = Calendar.getInstance();
				minDate.setTime(new Date());
			
			return transactionDate.get(Calendar.MONTH) == minDate.get(Calendar.MONTH);
			
		}).collect(Collectors.toList());
		
		return BigInteger.valueOf(transactionsFilter.size());
	}

	@Override
	public BigInteger statesAll() {
		return statesAll(null);
	}

	@Override
	public BigInteger statesByBeforeDays(Integer beforeDays) {
		
		return statesByBeforeDays(null, beforeDays);
	}

	@Override
	public Deposit getDepositByNumber(String depositNumber) throws NotExistDepositException {
		
		if (!this.listDeposit.stream().anyMatch(d -> d.getNumber().equals(depositNumber))) {
			throw new NotExistDepositException();
		}
		
		return this.listDeposit.stream().filter(d -> d.getNumber().equals(depositNumber)).findFirst().get();
	}

	@Override
	public List<Deposit> getDeposits(String statusDescription) {
		
		if (statusDescription.equals("")) {
			return new ArrayList<Deposit>();
		}
		
		return this.listDeposit.stream().filter(d -> d.getState().equals(statusDescription)).collect(Collectors.toList());
	}

	@Override
	public List<Deposit> getDepositsAll() {

		return this.listDeposit;
	}

	@Override
	public List<Transaction> transactionsStatesByBeforeDays(Integer beforeDays) {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		BeanUtil.getBean("walletDao", WalletDaoMockImpl.class).getHistory().values().stream().forEach(t -> transactions.addAll(t));

		List<Transaction> transactionsFilter = transactions.stream().filter(t -> {
			
			Calendar transactionDate = Calendar.getInstance();
				transactionDate.setTime(t.getDateStart());
			
			Calendar minDate = Calendar.getInstance();
				minDate.setTime(new Date());
			
			if (beforeDays != null) {
				minDate.add(Calendar.DAY_OF_YEAR, -beforeDays);
			}
			
			return transactionDate.get(Calendar.DAY_OF_YEAR) >= minDate.get(Calendar.DAY_OF_YEAR);
			
		}).collect(Collectors.toList());
		
		return transactionsFilter;
	}

	@Override
	public BigInteger statesAll(User user) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		BeanUtil.getBean("walletDao", WalletDaoMockImpl.class).getHistory().values().stream().forEach(t -> transactions.addAll(t));
		
		return BigInteger.valueOf(transactions.stream().filter(t->user==null||t.getUser().getId().equals(t.getUser().getId())).count());
	}

	@Override
	public BigInteger statesByBeforeDays(User user, Integer beforeDays) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		BeanUtil.getBean("walletDao", WalletDaoMockImpl.class).getHistory().values().stream().forEach(t -> transactions.addAll(t));

		List<Transaction> transactionsFilter = transactions.stream().filter(t -> {
			
			Calendar transactionDate = Calendar.getInstance();
				transactionDate.setTime(t.getDateStart());
			
			Calendar minDate = Calendar.getInstance();
				minDate.setTime(new Date());
			
			if (beforeDays != null) {
				minDate.add(Calendar.DAY_OF_YEAR, -beforeDays);
			}
			
			return transactionDate.get(Calendar.DAY_OF_YEAR) >= minDate.get(Calendar.DAY_OF_YEAR)&&(user==null||t.getUser().getId().equals(t.getUser().getId()));
			
		}).collect(Collectors.toList());
		
		return BigInteger.valueOf(transactionsFilter.size());
	}

	@Override
	public void insertDeposit(Deposit deposit){
		this.listDeposit.add(deposit);		
	}

	@Override
	public void updateDeposit(Deposit deposit) {
		try {
			this.listDeposit.remove(this.getDepositByNumber(deposit.getNumber()));
		} catch (NotExistDepositException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.insertDeposit(deposit);
	}
}
