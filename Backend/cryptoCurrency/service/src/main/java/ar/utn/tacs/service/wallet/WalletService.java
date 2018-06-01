package ar.utn.tacs.service.wallet;

import java.util.List;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.model.commons.ExistingDepositException;
import ar.utn.tacs.model.deposit.Deposit;
import ar.utn.tacs.model.deposit.DepositRest;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.wallet.CoinAmount;
import ar.utn.tacs.model.wallet.CoinAmountRest;
import ar.utn.tacs.model.wallet.Wallet;
import ar.utn.tacs.service.GenericService;

public interface WalletService extends GenericService{
	
	/**
	 * @param resultMap
	 * @return
	 * @throws UtnTacsException 
	 */
	void buy(String token, CoinAmountRest coinAmountRest) throws UtnTacsException;

	/**
	 * @param idUser
	 * @param idCoin
	 * @param amount
	 * @return {@link JsonValue}
	 * @throws UtnTacsException 
	 */
	void sale(String token, CoinAmountRest coinAmountRest) throws UtnTacsException;

	/**
	 * @param idUser
	 * @param idCoin
	 * @return {@link JsonValue}
	 * @throws UtnTacsException 
	 */
	List<Transaction> userTransactionHistory(String token, String coinSymbol) throws UtnTacsException;
	
	/**
	 * @param token
	 * @return {@link Wallet}
	 * @throws UtnTacsException 
	 */
	Wallet userWalletByToken(String token) throws UtnTacsException;

	/**
	 * @param token
	 * @param ticker
	 * @return {@link CoinAmount}
	 * @throws UtnTacsException 
	 */
	CoinAmount userCoinAmountByToken(String token, String ticker) throws UtnTacsException;
	
	/**
	 * @param amount
	 */
	void doDeposit(Deposit deposit);

	/**
	 * @param token
	 * @param depositRest
	 * @throws ExistingDepositException 
	 */
	void declareDeposit(String token, DepositRest depositRest) throws ExistingDepositException;
}
