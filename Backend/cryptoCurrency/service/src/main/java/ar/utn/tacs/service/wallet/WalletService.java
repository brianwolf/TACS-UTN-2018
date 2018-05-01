package ar.utn.tacs.service.wallet;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.wallet.Wallet;
import ar.utn.tacs.service.GenericService;

public interface WalletService extends GenericService{
	
	/**
	 * @param resultMap
	 * @return
	 */
	Boolean buy(Map<String, Object> map);

	/**
	 * @param idUser
	 * @param idCoin
	 * @param amount
	 * @return {@link JsonValue}
	 */
	Boolean sale(Map<String, Object> resultMap);

	/**
	 * @param idUser
	 * @param idCoin
	 * @return {@link JsonValue}
	 */
	List<Transaction> userTransactionHistory(String token, String coinSymbol);
	
	/**
	 * @param token
	 * @return {@link Wallet}
	 */
	Wallet userWalletByToken(String token);
}
