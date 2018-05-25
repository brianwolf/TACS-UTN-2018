package ar.utn.tacs.rest.wallet;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.model.admin.DepositRest;
import ar.utn.tacs.model.wallet.CoinAmountRest;
import ar.utn.tacs.rest.GenericRest;

public interface WalletRest extends GenericRest {

	public static final String BASE = "/wallet";
	
	public static final String BUY = "/buy";
	public static final String SALE = "/sale";
	public static final String USER_WALLET = "";
	public static final String USER_TRANSACTION_HISTORY = "/history/transactions";
	public static final String DECLARE_DEPOSIT = "/deposit";

	/**
	 * {@link POST}
	 * 
	 * @param token
	 * @param coinAmountRest
	 * @return {@link JsonValue}
	 */
	Response buy(String token, CoinAmountRest coinAmountRest);

	/**
	 * {@link POST}
	 * 
	 * @param token
	 * @param coinAmountRest
	 * @return {@link JsonValue}
	 */
	Response sale(String token, CoinAmountRest coinAmountRest);
	
	/**
	 * {@link GET}
	 * 
	 * {@link QueryParam}
	 * "ticker": {@link String}
	 * 
	 * @param ticker
	 * @return {@link String}
	 */
	Response userTransactionHistory(String token, String ticker);

	/**
	 * {@link GET}
	 * 
	 * @param token
	 * @param ticker
	 * @return
	 */
	Response userWalletByToken(String token, String ticker);
	
	/**
	 * {@link POST}
	 * 
	 * @param token
	 * @param depositRest
	 * @return {@link Response}
	 */
	Response declarerDeposit(String token, DepositRest depositRest);
}
