package ar.utn.tacs.dao.wallet;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.dao.GenericDao;
import ar.utn.tacs.model.transaction.Transaction;

public interface WalletDao extends GenericDao {

	/**
	 * @param resultMap
	 * @return
	 */
	Boolean buy(String token,Transaction transaction);

	/**
	 * @param idUser
	 * @param idCoin
	 * @param amount
	 * @return {@link JsonValue}
	 */
	Boolean sale(String token,Transaction transaction);

	/**
	 * @param idUser
	 * @param idCoin
	 * @return {@link List} {@link Transaction}
	 */
	List<Transaction> userTransactionHistory(String token, String coinSymbol);
}
