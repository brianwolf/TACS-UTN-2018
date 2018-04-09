package ar.utn.tacs.walletService.rest;

import java.math.BigDecimal;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.rest.GenericRest;

public interface WalletRest extends GenericRest {

	public static final String base = "/walletService";
	
	public static final String buy = "/buy";
	public static final String sale = "/sale";
	public static final String buyHistory = "/buyHistory/{idUser}";
	public static final String transactionHistory = "/userTransactionHistory/{idUser}/{idCoin}";

	/**
	 * @param idUser
	 * @param idCoin
	 * @param amount
	 * @return {@link JsonValue}
	 */
	Response buy(long idUser, long idCoin, BigDecimal amount);

	/**
	 * @param idUser
	 * @param idCoin
	 * @param amount
	 * @return {@link JsonValue}
	 */
	Response sale(long idUser, long idCoin, BigDecimal amount);

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
