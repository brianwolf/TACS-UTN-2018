package ar.utn.tacs.dao.wallet;

import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.dao.GenericDao;
import ar.utn.tacs.model.transaction.Transaction;

public interface WalletDao extends GenericDao {

	/**
	 * @param resultMap
	 * @return
	 */
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
	 * @param idCoin
	 * @return {@link List} {@link Transaction}
	 */
	List<Transaction> userTransactionHistory(String token, String coinSymbol);
}
