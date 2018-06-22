package ar.utn.tacs.rest.wallet;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.model.commons.ExistingDepositException;
import ar.utn.tacs.model.deposit.DepositRest;
import ar.utn.tacs.model.wallet.CoinAmountRest;
import ar.utn.tacs.rest.GenericRest;

public interface WalletRest extends GenericRest {

	public static final String BASE = "/wallet";
	
	public static final String BUY = "/buy";
	public static final String SALE = "/sale";
	public static final String USER_WALLET = "";
	public static final String USER_TRANSACTION_HISTORY = "/history/transactions";
	public static final String DEPOSITS = "/deposits";

	/**
	 * {@link POST}
	 * 
	 * @param token
	 * @param coinAmountRest
	 * @return {@link JsonValue}
	 * @throws UtnTacsException 
	 */
	Response buy(String token, CoinAmountRest coinAmountRest) throws UtnTacsException;

	/**
	 * {@link POST}
	 * 
	 * @param token
	 * @param coinAmountRest
	 * @return {@link JsonValue}
	 * @throws UtnTacsException 
	 */
	Response sale(String token, CoinAmountRest coinAmountRest) throws UtnTacsException;
	
	/**
	 * {@link GET}
	 * 
	 * {@link QueryParam}
	 * "ticker": {@link String}
	 * 
	 * @param ticker
	 * @return {@link String}
	 * @throws UtnTacsException 
	 */
	Response userTransactionHistory(String token, String ticker) throws UtnTacsException;

	/**
	 * {@link GET}
	 * 
	 * @param token
	 * @param ticker
	 * @return
	 * @throws UtnTacsException 
	 */
	Response userWalletByToken(String token, String ticker) throws UtnTacsException;
	
	/**
	 * {@link POST}
	 * 
	 * @param token
	 * @param depositRest
	 * @return {@link Response}
	 * @throws ExistingDepositException 
	 */
	Response declareDeposit(String token, DepositRest depositRest) throws ExistingDepositException;

	Response getDepositsByToken(String token) throws UtnTacsException;
}
