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
import ar.utn.tacs.service.wallet.WalletService;

public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletDao walletDao;
	
	@Override
	public List<Transaction> userTransactionHistory(String token, String coinSymbol) {
		return walletDao.userTransactionHistory(token, coinSymbol);
	}

	@Override
	public Boolean buy(Map<String, Object> map) {
		TransactionBuilder transactionBuilder = new TransactionBuilder();
		String operation = "buy";
		User user = new User();
		Coin coin = new Coin();
		BigDecimal amount = null;
		String token = null;
		
		Transaction transaction= transactionBuilder.createTransaction(operation,user,coin,amount);
		
		return walletDao.buy(token,transaction);
	}

	@Override
	public Transaction sale(Map<String, Object> resultMap) {
		// TODO Auto-generated method stub
		return null;
	}
}
