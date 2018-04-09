package ar.utn.tacs.walletService.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.rest.GenericRest;

public interface WalletRest extends GenericRest {

	public static final String BASE = "/wallet";
	
	public static final String BUY = "/compra";
	public static final String SALE = "/venta";
	public static final String BUY_HISTORY = "/historial/compras/{idUser}";
	public static final String USER_TRANSACTION_HISTORY = "/historial/transacciones/{idUser}/{idCoin}";

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
