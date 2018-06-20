package ar.utn.tacs.service.wallet.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.dao.user.UserDao;
import ar.utn.tacs.dao.wallet.WalletDao;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.commons.ExistingDepositException;
import ar.utn.tacs.model.deposit.Deposit;
import ar.utn.tacs.model.deposit.DepositRest;
import ar.utn.tacs.model.operation.Buy;
import ar.utn.tacs.model.operation.Sale;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.transaction.TransactionBuilder;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.wallet.CoinAmount;
import ar.utn.tacs.model.wallet.CoinAmountRest;
import ar.utn.tacs.model.wallet.Wallet;
import ar.utn.tacs.service.admin.AdminService;
import ar.utn.tacs.service.external.ExternalService;
import ar.utn.tacs.service.user.UserService;
import ar.utn.tacs.service.wallet.WalletService;
import ar.utn.tacs.util.BeanUtil;

public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletDao walletDao;

	private Transaction getTransaction(String operationName, User user, CoinAmount coinAmount) throws UtnTacsException {

		TransactionBuilder transactionBuilder = new TransactionBuilder();
		return transactionBuilder.createTransaction(operationName, user, coinAmount);
	}

	@Override
	public List<Transaction> userTransactionHistory(String token, String ticker) throws UtnTacsException {

		Coin coin = ticker.equals("") ? null : BeanUtil.getBean(ExternalService.class).getCoinByTicker(ticker);
		User user = BeanUtil.getBean(UserService.class).getUserByToken(token);

		return walletDao.userTransactionHistory(user, coin);
	}

	@Override
	public void buy(String token, CoinAmountRest coinAmountRest) throws UtnTacsException {

		CoinAmount coinAmount = coinAmountRest.toCoinAmount(BeanUtil.getBean(ExternalService.class).coinMarketCap());
		User user = BeanUtil.getBean(UserService.class).getUserByToken(token);

		Transaction transaction = this.getTransaction(Buy.DESCRIPTION, user, coinAmount);
		
		transaction.doOperations();
		transaction.setDateFinal(new Date());

		User userTransaction = new User();
		userTransaction.setId(user.getId());

		transaction.setUser(userTransaction);
		this.walletDao.insertTransaction(transaction);
		
		this.updateUser(user);
	}
	
	private void updateUser(User user) {
		BeanUtil.getBean(UserDao.class).updateUser(user);

	}

	@Override
	public void sale(String token, CoinAmountRest coinAmountRest) throws UtnTacsException {

		CoinAmount coinAmount = coinAmountRest.toCoinAmount(BeanUtil.getBean(ExternalService.class).coinMarketCap());
		User user = BeanUtil.getBean(UserService.class).getUserByToken(token);

		Transaction transaction = this.getTransaction(Sale.DESCRIPTION, user, coinAmount);
		
		transaction.doOperations();
		transaction.setDateFinal(new Date());

		User userTransaction = new User();
		userTransaction.setId(user.getId());
		
		transaction.setUser(userTransaction);
		this.walletDao.insertTransaction(transaction);
		
		this.updateUser(user);
	}

	@Override
	public Wallet userWalletByToken(String token) throws UtnTacsException {

		Wallet userWallet = BeanUtil.getBean(UserService.class).getUserByToken(token).getWallet();
		userWallet.updateCoinsValue(BeanUtil.getBean(ExternalService.class).coinMarketCap());

		return userWallet;
	}

	@Override
	public CoinAmount userCoinAmountByToken(String token, String ticker) throws UtnTacsException {

		Wallet userWallet = userWalletByToken(token);

		return userWallet.getCoinAmountByTicker(ticker);
	}

	@Override
	public void doDeposit(Deposit deposit) {
		Wallet wallet = deposit.getUser().getWallet();
		BigDecimal finalAmount = wallet.getDolarAmount().add(deposit.getAmount());
		
		wallet.setDolarAmount(finalAmount);
		this.updateUser(deposit.getUser());
	}

	@Override
	public void declareDeposit(String token, DepositRest depositRest) throws ExistingDepositException {

		try {
			User user = BeanUtil.getBean(UserService.class).getUserByToken(token);

			Deposit deposit = depositRest.toDeposit(user);
			deposit.setState(Deposit.WAITING);

			BeanUtil.getBean(AdminService.class).addDeposit(deposit);

		} catch (ExistingDepositException existingDepositException) {
			throw existingDepositException;
		
		} catch (UtnTacsException e) {
			e.printStackTrace();
			//hacer rollback
		}

	}

	@Override
	public List<Deposit> getDepositsByToken(String token) throws UtnTacsException {
		
		User user = BeanUtil.getBean(UserService.class).getUserByToken(token);
		
		return walletDao.getDepositsByUser(user);
	}
}
