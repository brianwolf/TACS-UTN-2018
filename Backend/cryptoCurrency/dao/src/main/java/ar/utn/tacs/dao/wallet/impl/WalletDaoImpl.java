package ar.utn.tacs.dao.wallet.impl;

import java.util.List;

import ar.utn.tacs.dao.impl.GenericAbstractDaoImpl;
import ar.utn.tacs.dao.wallet.WalletDao;
import ar.utn.tacs.model.admin.Deposit;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.wallet.Wallet;

public class WalletDaoImpl extends GenericAbstractDaoImpl<Wallet> implements WalletDao{

	@Override
	public void buy(User user,Transaction transaction) {
		// TODO Auto-generated method stub
	}

	@Override
	public void sale(User user, Transaction transaction) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Transaction> userTransactionHistory(User user, Coin coin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doDeposit(Deposit deposit) {
		// TODO Auto-generated method stub
		
	}
}
