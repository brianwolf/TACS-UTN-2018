package ar.utn.tacs.service.wallet.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.dao.wallet.WalletDao;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.operation.Buy;
import ar.utn.tacs.model.operation.Sale;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.transaction.TransactionBuilder;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.wallet.CoinAmount;
import ar.utn.tacs.model.wallet.CoinAmountRest;
import ar.utn.tacs.model.wallet.Wallet;
import ar.utn.tacs.service.external.ExternalService;
import ar.utn.tacs.service.user.UserService;
import ar.utn.tacs.service.wallet.WalletService;
import ar.utn.tacs.util.BeanUtil;

public class WalletServiceImpl implements WalletService {

	
	@Autowired
	private WalletDao walletDao;
	
	/**
	 * @param operationName
	 * @param user
	 * @param coinAmount
	 * @return {@link Transaction}
	 * @throws UtnTacsException 
	 */
	private Transaction getTransaction(String operationName, User user, CoinAmount coinAmount) throws UtnTacsException {
		
		TransactionBuilder transactionBuilder = new TransactionBuilder();
		return transactionBuilder.createTransaction(operationName, user, coinAmount);
	}

	@Override
	public List<Transaction> userTransactionHistory(String token, String ticker) throws UtnTacsException{
		
		Coin coin = ticker.equals("")? null : BeanUtil.getBean(ExternalService.class).getCoinByTicker(ticker);
		User user = BeanUtil.getBean(UserService.class).getUserByToken(token);

		return walletDao.userTransactionHistory(user, coin);
	}

	@Override
	public void buy(String token, CoinAmountRest coinAmountRest) throws UtnTacsException {

		CoinAmount coinAmount = coinAmountRest.toCoinAmount(BeanUtil.getBean(ExternalService.class).coinMarketCap());
		User user = BeanUtil.getBean(UserService.class).getUserByToken(token);
		
		Transaction transaction = this.getTransaction(Buy.class.getName(), user, coinAmount);
		walletDao.buy(user,transaction);
	}

	@Override
	public void sale(String token, CoinAmountRest coinAmountRest) throws UtnTacsException {
		
		CoinAmount coinAmount = coinAmountRest.toCoinAmount(BeanUtil.getBean(ExternalService.class).coinMarketCap());
		User user = BeanUtil.getBean(UserService.class).getUserByToken(token);
		
		Transaction transaction = this.getTransaction(Sale.class.getName(), user, coinAmount);
			
		walletDao.sale(user,transaction);
	}

	@Override
	public Wallet userWalletByToken(String token)  throws UtnTacsException{
		
		Wallet userWallet = BeanUtil.getBean(UserService.class).getUserByToken(token).getWallet();
			userWallet.updateCoinsValue(BeanUtil.getBean(ExternalService.class).coinMarketCap());
		
		return userWallet; 
	}

	@Override
	public CoinAmount userCoinAmountByToken(String token, String ticker) throws UtnTacsException {
		
		Wallet userWallet = userWalletByToken(token);
		
		return userWallet.getCoinAmountByTicker(ticker);
	}
}
