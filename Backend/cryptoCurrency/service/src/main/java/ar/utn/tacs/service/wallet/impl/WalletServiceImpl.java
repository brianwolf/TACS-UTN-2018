package ar.utn.tacs.service.wallet.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.dao.wallet.WalletDao;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.transaction.TransactionBuilder;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.wallet.Wallet;
import ar.utn.tacs.service.external.impl.ExternalServiceMockImpl;
import ar.utn.tacs.service.user.impl.UserServiceImpl;
import ar.utn.tacs.service.wallet.WalletService;

public class WalletServiceImpl implements WalletService {

	private static WalletService WALLET_SERVICE = new WalletServiceImpl();
	
	@Autowired
	private WalletDao walletDao;

	public static WalletService getInstance() {
		return WALLET_SERVICE;
	}
	
	@Override
	public List<Transaction> userTransactionHistory(String token, String coinSymbol) {
		return walletDao.userTransactionHistory(token, coinSymbol);
	}
	
	@SuppressWarnings("unchecked")
	private Transaction getTransaction(String operation,Map<String,Object> map) {
		TransactionBuilder transactionBuilder = new TransactionBuilder();
		String token = (String) map.get("token");
		User user = UserServiceImpl.getInstance().getUserByToken(token);
		Coin coin = ExternalServiceMockImpl.getInstance().getCoinByName((String) ((Map<String,Object>)map.get("coin")).get("name"));
		String amountString = String.valueOf(map.get("amount"));
		BigDecimal amount = BigDecimal.valueOf(Double.valueOf(amountString));
		
		Transaction transaction= transactionBuilder.createTransaction(operation,user,coin,amount);
		
		return transaction;
	}

	@Override
	public Boolean buy(Map<String, Object> map) {
		String operation = "buy";
		String token = (String) map.get("token");
		Transaction transaction = getTransaction(operation, map);
		return walletDao.buy(token,transaction);
	}

	@Override
	public Boolean sale(Map<String, Object> resultMap) {
		String operation = "sale";
		String token = (String) resultMap.get("token");
		Transaction transaction = getTransaction(operation, resultMap);
		return walletDao.buy(token,transaction);
	}

	@Override
	public Wallet userWalletByToken(String token) {
		
		Wallet userWallet = UserServiceImpl.getInstance().getUserByToken(token).getWallet();
			userWallet.updateCoinsValue(ExternalServiceMockImpl.getInstance().coinMarketCap());
		
		return userWallet; 
	}
}
