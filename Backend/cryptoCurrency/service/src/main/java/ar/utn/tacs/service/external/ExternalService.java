package ar.utn.tacs.service.external;

import java.util.List;
import java.util.Map;

import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.service.GenericService;

public interface ExternalService extends GenericService {

	/**
	 * @return {@link Map}
	 */
	List<Coin> coinMarketCap();

	Coin getCoinByName(String name);
}
