package ar.utn.tacs.walletService.rest;

import java.util.HashMap;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.rest.GenericRest;

public interface WalletRest extends GenericRest {

	public static final String BASE = "/walletService";
	
	public static final String BUY = "/buy";
	public static final String SALE = "/sale";
	public static final String BUY_HISTORY = "/buyHistory/{idUser}";
	public static final String USER_TRANSACTION_HISTORY = "/userTransactionHistory/{idUser}/{idCoin}";

	/**
	 * @param idUser
	 * @param idCoin
	 * @param amount
	 * @return {@link JsonValue}
	 */
	Response buy(HashMap<String, String> resultMap);

	/**
	 * @param idUser
	 * @param idCoin
	 * @param amount
	 * @return {@link JsonValue}
	 */
	Response sale(HashMap<String, String> resultMap);

	/**
	 * @param idUser
	 * @return {@link JsonValue}
	 */
	Response buyHistory(long idUser);

	/**
	 * @param idUser
	 * @param idCoin
	 * @return {@link JsonValue}
	 */
	Response userTransactionHistory(long idUser, long idCoin);
}
