package ar.utn.tacs.service.wallet.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.dao.wallet.WalletDao;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.service.wallet.WalletService;

public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletDao externalDao;

	@Override
	public List<Transaction> buyHistory(long idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> userTransactionHistory(long idUser, long idCoin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction buy(HashMap<String, String> resultMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction sale(HashMap<String, String> resultMap) {
		// TODO Auto-generated method stub
		return null;
	}
}
