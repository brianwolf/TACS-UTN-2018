package ar.utn.tacs.model.operation;

import java.math.BigDecimal;

import org.springframework.data.annotation.TypeAlias;

import ar.utn.tacs.model.commons.InsufficientMoneyException;
import ar.utn.tacs.model.wallet.CoinAmount;
import ar.utn.tacs.model.wallet.Wallet;

@TypeAlias(value = "ar.utn.tacs.model.operation.Buy")
public class Buy extends Operation{
	
	public static final String DESCRIPTION = "BUY";

	public Buy() {
		super();
		this.description = "BUY";
	}

	@Override
	public void doOperation() throws InsufficientMoneyException {
	
		Wallet userWallet = super.user.getWallet();
		
		BigDecimal finalPrice = super.coinAmount.getAmount().multiply(super.coinAmount.getCoin().getValueInDollars());
		if (!userWallet.haveEnoughDolar(finalPrice)) {
			throw new InsufficientMoneyException();
		}
		
		BigDecimal finalDolarAmount = userWallet.getDolarAmount().subtract(finalPrice);
		userWallet.setDolarAmount(finalDolarAmount);
		
		CoinAmount newCoin = userWallet.getCoinAmountByCoin(super.coinAmount.getCoin());
		if (newCoin == null) {
			newCoin = new CoinAmount(super.coinAmount.getCoin(), new BigDecimal(0f));
			userWallet.getCoinAmounts().add(newCoin);
		}
		
		BigDecimal finalCoinAmount = newCoin.getAmount().add(super.coinAmount.getAmount());
		userWallet.updateCoinAmount(newCoin, finalCoinAmount);
	}

}
