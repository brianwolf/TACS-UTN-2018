package ar.utn.tacs.rest.wallet;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.rest.GenericRest;

public interface WalletRest extends GenericRest {

	public static final String BASE = "/wallet";
	
	public static final String BUY = "/buy";
	public static final String SALE = "/sale";
	public static final String USER_WALLET = "";
	public static final String USER_TRANSACTION_HISTORY = "/history/transactions/{ticker}";

	/**
	 * {@link POST}
	 * 
	 * @param idUser
	 * @param idCoin
	 * @param amount
	 * @return {@link JsonValue}
	 */
	Response buy(String token,Map<String, Object> resultMap);

	/**
	 * {@link POST}
	 * 
	 * @param idUser
	 * @param idCoin
	 * @param amount
	 * @return {@link JsonValue}
	 */
	Response sale(String token,Map<String, Object> resultMap);

	/**
	 * {@link GET}
	 * 
	 * @param idUser
	 * @param idCoin
	 * @return {@link JsonValue}
	 */
	Response userTransactionHistory(String token, String coinSymbol);
	
	
	/**
	 * {@link GET}
	 * 
	 * @param token
	 * @return {@link Response}
	 */
	Response userWalletByToken(String token);

}
