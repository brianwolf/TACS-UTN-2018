package ar.utn.tacs.rest.wallet;

import java.util.Map;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.rest.GenericRest;

public interface WalletRest extends GenericRest {

	public static final String BASE = "/wallet";
	
	public static final String BUY = "/buy";
	public static final String SALE = "/sale";
	public static final String BUY_HISTORY = "/history/purchases/{idUser}";
	public static final String USER_TRANSACTION_HISTORY = "/history/transactions/{idUser}/{4}";

	/**
	 * @param idUser
	 * @param idCoin
	 * @param amount
	 * @return {@link JsonValue}
	 */
	Response buy(Map<String, String> resultMap);

	/**
	 * @param idUser
	 * @param idCoin
	 * @param amount
	 * @return {@link JsonValue}
	 */
	Response sale(Map<String, String> resultMap);

	/**
	 * @param idUser
	 * @return {@link JsonValue}
	 */
	Response buyHistory(Long idUser);

	/**
	 * @param idUser
	 * @param idCoin
	 * @return {@link JsonValue}
	 */
	Response userTransactionHistory(Long idUser, Long idCoin);
}
