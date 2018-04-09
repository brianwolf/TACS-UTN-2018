package ar.utn.tacs.walletService.service;

import java.math.BigDecimal;
import java.util.List;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.service.GenericService;

public interface WalletService extends GenericService{
	
	Transaction buy(long idUser, long idCoin, BigDecimal amount);

	/**
	 * @param idUser
	 * @param idCoin
	 * @param amount
	 * @return {@link JsonValue}
	 */
	Transaction sale(long idUser, long idCoin, BigDecimal amount);

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
