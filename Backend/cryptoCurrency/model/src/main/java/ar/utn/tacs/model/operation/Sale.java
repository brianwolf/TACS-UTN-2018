package ar.utn.tacs.model.operation;

import java.math.BigDecimal;
import java.math.BigInteger;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.model.commons.DontHaveOperationCoinException;
import ar.utn.tacs.model.commons.InsufficientCryptoCurrencyException;
import ar.utn.tacs.model.wallet.CoinAmount;
import ar.utn.tacs.model.wallet.Wallet;

public class Sale extends Operation {

	private static final String DESCRIPTION = "Venta";

	public Sale(BigInteger id) {
		super(id, DESCRIPTION);
	}

	@Override
	public void doOperation() throws UtnTacsException {
		
		Wallet userWallet = super.user.getWallet();
		
		CoinAmount virtualCoin = userWallet.getCoinAmountByCoin(super.coinAmount.getCoin());
		if (virtualCoin == null) {
			throw new DontHaveOperationCoinException();
		}
		
		if (!userWallet.haveEnoughCoins(super.coinAmount.getCoin(), super.coinAmount.getAmount())) {
			throw new InsufficientCryptoCurrencyException();
		}
		
		BigDecimal finalCoinAmount = virtualCoin.getAmount().subtract(super.coinAmount.getAmount());
		virtualCoin.setAmount(finalCoinAmount);
		
		BigDecimal finalPrice = super.coinAmount.getAmount().multiply(super.coinAmount.getCoin().getValueInDollars());
		
		BigDecimal finalDolarAmount = userWallet.getDolarAmount().add(finalPrice);
		userWallet.setDolarAmount(finalDolarAmount);
	}

}
