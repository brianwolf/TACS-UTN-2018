package ar.utn.tacs.service.external;

import java.util.List;
import java.util.Map;

import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.email.Mail;
import ar.utn.tacs.service.GenericService;

public interface ExternalService extends GenericService {

	/**
	 * @return {@link Map} {@link Coin}
	 */
	public List<Coin> coinMarketCap();

	/**
	 * @param name
	 * @return {@link Coin}
	 */
	public Coin getCoinByName(String name);
	
	/**
	 * @param ticker
	 * @return {@link Coin}
	 */
	public Coin getCoinByTicker(String ticker);
	
	/**
	 * @param mail
	 * @return {@link Boolean}
	 */
	public Boolean sendMail(Mail mail);

	/**
	 * 
	 */
	void updateCoinMarketCap();
}
