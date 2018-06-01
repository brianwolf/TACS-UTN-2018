package ar.utn.tacs.dao.admin.impl;

import java.math.BigDecimal;
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
import ar.utn.tacs.dao.user.UserDao;
import ar.utn.tacs.model.commons.ApprovingApprovedDepositException;
import ar.utn.tacs.model.commons.ExistingDepositException;
import ar.utn.tacs.model.commons.RejectingApprovedDepositException;
import ar.utn.tacs.model.commons.RejectingRejectedDepositException;
import ar.utn.tacs.model.deposit.Deposit;
import ar.utn.tacs.model.deposit.StateDepositNumber;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.util.BeanUtil;

public class AdminDaoImpl extends GenericAbstractDaoImpl implements AdminDao {

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

		Query q = new Query();
		q.addCriteria(Criteria.where("dateStart").lte(new Date()));

		return BigInteger.valueOf(mongoTemplate.count(q, Transaction.class));
	}

	@Override
	public BigInteger statesByBeforeDays(Integer beforeDays) {

		Calendar minDate = Calendar.getInstance();
		minDate.setTime(new Date());

		if (beforeDays != null) {
			minDate.add(Calendar.DAY_OF_YEAR, -beforeDays);
		}

		Query q = new Query();
		q.addCriteria(Criteria.where("dateStart").lte(new Date()).and("dateStart").gt(minDate.getTime()));

		Long count = mongoTemplate.count(q, Transaction.class);
		return new BigInteger(count.toString());
	}

	@Override
	public List<Transaction> transactionsStatesByBeforeDays(Integer beforeDays) {

		Calendar minDate = Calendar.getInstance();
		minDate.setTime(new Date());

		if (beforeDays != null) {
			minDate.add(Calendar.DAY_OF_YEAR, -beforeDays);
		}

		Query q = new Query();
		q.addCriteria(Criteria.where("dateStart").lte(new Date()).and("dateStart").gt(minDate.getTime()));
		List<Transaction> transactions = mongoTemplate.find(q, Transaction.class);

		return transactions;
	}

	@Override
	public void addDeposit(Deposit deposit) throws ExistingDepositException {

		if (this.getByProperty("number", deposit.getNumber(), Deposit.class) != null) {
			throw new ExistingDepositException();
		}

		this.insert(deposit);
	}

	@Override
	public void approveDeposit(Deposit deposit) throws ApprovingApprovedDepositException {
		Deposit depositFounded = this.getDepositByDepositNumber(deposit.getNumber());

		if (depositFounded.getState().equals(StateDepositNumber.APPROVED.toString())) {
			throw new ApprovingApprovedDepositException();
		}

		depositFounded.setState(StateDepositNumber.APPROVED.toString());
		this.update(deposit);
	}

	@Override
	public void rejectDeposit(Deposit deposit) throws RejectingApprovedDepositException, RejectingRejectedDepositException {
		Deposit depositFounded = this.getDepositByDepositNumber(deposit.getNumber());

		if (depositFounded.getState().equals(StateDepositNumber.APPROVED.toString())) {
			throw new RejectingApprovedDepositException();
		}

		if (depositFounded.getState().equals(StateDepositNumber.REJECTED.toString())) {
			throw new RejectingRejectedDepositException();
		}

		depositFounded.setState(StateDepositNumber.REJECTED.toString());
		this.update(deposit);
	}

	@Override
	public Deposit getDepositByDepositNumber(String depositNumber) {
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

}
