package ar.utn.tacs.model.operation;

import java.math.BigDecimal;
import java.util.Map;

import ar.utn.tacs.model.coin.Coin;

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

		Map<Coin, BigDecimal> coinMap = super.getUser().getWallet().getCoinsMap();

		coinMap.get(this.getCoin()).subtract(this.amount);

	}

}
