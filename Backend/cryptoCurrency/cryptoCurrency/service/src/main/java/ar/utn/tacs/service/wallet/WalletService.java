package ar.utn.tacs.service.wallet;

import java.util.List;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.wallet.CoinAmountRest;
import ar.utn.tacs.model.wallet.Wallet;
import ar.utn.tacs.service.GenericService;

public interface WalletService extends GenericService{
	
	/**
	 * @param resultMap
	 * @return
	 * @throws UtnTacsException 
	 */
	public void buy(String token, CoinAmountRest coinAmountRest) throws UtnTacsException;

	/**
	 * @param idUser
	 * @param idCoin
	 * @param amount
	 * @return {@link JsonValue}
	 * @throws UtnTacsException 
	 */
	public void sale(String token, CoinAmountRest coinAmountRest) throws UtnTacsException;

	/**
	 * @param idUser
	 * @param idCoin
	 * @return {@link JsonValue}
	 * @throws UtnTacsException 
	 */
	public List<Transaction> userTransactionHistory(String token, String coinSymbol) throws UtnTacsException;
	
	/**
	 * @param token
	 * @return {@link Wallet}
	 * @throws UtnTacsException 
	 */
	public Wallet userWalletByToken(String token) throws UtnTacsException;
}
