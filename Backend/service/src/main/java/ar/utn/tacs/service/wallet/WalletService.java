package ar.utn.tacs.service.wallet;

import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.service.GenericService;

public interface WalletService extends GenericService{
	
	Transaction buy(HashMap<String, String> resultMap);

	/**
	 * @param idUser
	 * @param idCoin
	 * @param amount
	 * @return {@link JsonValue}
	 */
	Transaction sale(HashMap<String, String> resultMap);

	/**
	 * @param idUser
	 * @return {@link JsonValue}
	 */
	List<Transaction> buyHistory(long idUser);

	/**
	 * @param idUser
	 * @param idCoin
	 * @return {@link JsonValue}
	 */
	List<Transaction> userTransactionHistory(long idUser, long idCoin);
}
