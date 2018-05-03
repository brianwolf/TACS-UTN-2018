package ar.utn.tacs.model.operation;

import java.math.BigDecimal;

import ar.utn.tacs.model.wallet.CoinAmount;
import ar.utn.tacs.model.wallet.Wallet;

public class Sale extends Operation {

	private static final Long ID = 1l;
	private static final String DESCRIPTION = "Venta";

	public Sale() {
		super(ID, DESCRIPTION);
	}

	@Override
	public void doOperation() {
		
		Wallet userWallet = super.user.getWallet();
		
		CoinAmount virtualCoin = userWallet.getCoinAmountByCoin(super.coinAmount.getCoin());
		if (virtualCoin == null) {
			//new noTenesLaMonedaException()
			return; 
		}
		
		if (!userWallet.haveEnoughCoins(super.coinAmount.getCoin(), super.coinAmount.getAmount())) {
			//new SosUnPobreException()
			return;
		}
		BigDecimal finalCoinAmount = virtualCoin.getAmount().subtract(super.coinAmount.getAmount());
		virtualCoin.setAmount(finalCoinAmount);
		
		BigDecimal finalPrice = super.coinAmount.getAmount().multiply(super.coinAmount.getCoin().getValueInDollars());
		
		BigDecimal finalDolarAmount = userWallet.getDolarAmount().add(finalPrice);
		userWallet.setDolarAmount(finalDolarAmount);
	}

}
