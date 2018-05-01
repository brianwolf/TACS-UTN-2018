package ar.utn.tacs.model.operation;

import java.math.BigDecimal;

import ar.utn.tacs.model.wallet.CoinAmaunt;
import ar.utn.tacs.model.wallet.Wallet;

public class Sale extends Operation {

	private static final Long ID = 1l;
	private static final String DESCRIPTION = "Venta";

	public Sale() {
		super(ID, DESCRIPTION);
		// TODO Auto-generated constructor stub
	}

	//ACA SE DEBERIAN HACER VALIDACIONES
	@Override
	public void doOperation() {
		
		Wallet userWallet = super.user.getWallet();
		
		CoinAmaunt virtualCoin = userWallet.getCoinAmountByCoin(super.coin);
		if (virtualCoin == null) {
			//noTenesLaMonedaException()
			return; 
		}
		
		if (!userWallet.haveEnoughCoins(super.coin, super.amount)) {
			//new SosUnPobreException()
			return;
		}
		
		BigDecimal finalPrice = super.amount.multiply(super.coin.getValueInDollars());
		
		CoinAmaunt dolar = userWallet.getCoinAmountByTicker("USD");
		dolar.getAmount().add(finalPrice);
		
		virtualCoin.getAmount().subtract(super.amount);
	}

}
