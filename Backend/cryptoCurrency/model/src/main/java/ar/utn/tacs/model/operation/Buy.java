package ar.utn.tacs.model.operation;

import java.math.BigDecimal;
import java.util.Map;

import ar.utn.tacs.model.coin.Coin;

public class Buy extends Operation{

	private static final Long ID = 1l;
	private static final String DESCRIPTION = "Compra";
	
	public Buy() {
		super(ID, DESCRIPTION);
	}

	//ACA SE DEBERIAN HACER VALIDACIONES
	@Override
	public void doOperation() {
		
		Map<Coin,BigDecimal> coinMap = this.user.getWallet().getCoinsMap();
		
		if(!coinMap.containsKey(this.coin)) {
			coinMap.put(this.coin, this.amount);
		}
		else {
			coinMap.get(this.coin).add(this.amount);
		}
		
	}

}
