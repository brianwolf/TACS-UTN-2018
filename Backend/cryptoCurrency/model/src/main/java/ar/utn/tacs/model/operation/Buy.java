package ar.utn.tacs.model.operation;

import java.math.BigDecimal;

import ar.utn.tacs.model.wallet.CoinAmaunt;
import ar.utn.tacs.model.wallet.Wallet;

public class Buy extends Operation{

	private static final Long ID = 1l;
	private static final String DESCRIPTION = "Compra";
	
	public Buy() {
		super(ID, DESCRIPTION);
	}

	@Override
	public void doOperation() {
	
		Wallet userWallet = super.user.getWallet();
		
		BigDecimal finalPrice = super.amount.multiply(super.coin.getValueInDollars());
		if (!userWallet.haveEnoughCoins("USD", finalPrice)) {
			//new SosUnPobreException()
			return;
		}
		
		CoinAmaunt newCoin = userWallet.getCoinAmountByCoin(coin);
		if (newCoin == null) {
			newCoin = new CoinAmaunt(super.coin, super.amount);
			userWallet.getCoinAmaunts().add(newCoin);
		}
		newCoin.getAmount().add(super.amount);
		
		CoinAmaunt dolar = userWallet.getCoinAmountByTicker("USD");
		dolar.getAmount().subtract(finalPrice);
	}

}
