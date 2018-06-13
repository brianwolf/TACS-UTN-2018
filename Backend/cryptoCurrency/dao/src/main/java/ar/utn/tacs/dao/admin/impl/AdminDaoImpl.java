package ar.utn.tacs.dao.admin.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import ar.utn.tacs.dao.admin.AdminDao;
import ar.utn.tacs.dao.impl.GenericAbstractDaoImpl;
import ar.utn.tacs.model.deposit.Deposit;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;

public class AdminDaoImpl extends GenericAbstractDaoImpl implements AdminDao {

	@Override
	public BigInteger statesLastWeek() {

		List<Transaction> transactions = this.transactionsStatesByBeforeDays(7);

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

		List<Transaction> transactions = this.transactionsStatesByBeforeDays(30);

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
	public List<Transaction> transactionsStatesByBeforeDays(Integer beforeDays) {

		Calendar minDate = Calendar.getInstance();
		minDate.setTime(new Date());

		if (beforeDays != null) {
			minDate.add(Calendar.DAY_OF_YEAR, -beforeDays);
			minDate.set(Calendar.HOUR_OF_DAY, 0);
		}

		Query q = new Query();
		q.addCriteria(Criteria.where("dateStart").lte(new Date()).gt(minDate.getTime()));
		List<Transaction> transactions = mongoTemplate.find(q, Transaction.class);

		return transactions;
	}

	@Override
	public Deposit getDepositByNumber(String depositNumber) {
		return this.getByProperty("number", depositNumber, Deposit.class);
	}

	@Override
	public List<Deposit> getDeposits(String statusDescription) {
		
		List<Deposit> deposits = this.getAllByProperty("state", statusDescription, Deposit.class);
		return deposits == null? new ArrayList<>() : deposits;
	}

	@Override
	public List<Deposit> getDepositsAll() {
		return this.getAll(Deposit.class);
	}

	@Override
	public BigInteger statesAll(User user) {
		Query q = new Query();
		q.addCriteria(Criteria.where("dateStart").lte(new Date()));
		
		if(user!=null) {
			q.addCriteria(Criteria.where("user.id").is(user.getId()));
		}

		return BigInteger.valueOf(mongoTemplate.count(q, Transaction.class));
	}

	@Override
	public BigInteger statesByBeforeDays(User user, Integer beforeDays) {
		Calendar minDate = Calendar.getInstance();
		minDate.setTime(new Date());

		if (beforeDays != null) {
			minDate.add(Calendar.DAY_OF_YEAR, -beforeDays);
			minDate.set(Calendar.HOUR_OF_DAY, 0);
		}

		Query q = new Query();
		q.addCriteria(Criteria.where("dateStart").lte(new Date()).gt(minDate.getTime()));
		
		if(user!=null) {
			q.addCriteria(Criteria.where("user.id").is(user.getId()));
		}

		Long count = mongoTemplate.count(q, Transaction.class);
		return new BigInteger(count.toString());
	}

	@Override
	public void insertDeposit(Deposit deposit){
		this.insert(deposit);
	}

	@Override
	public void updateDeposit(Deposit deposit) {
		this.update(deposit);
	}

}
