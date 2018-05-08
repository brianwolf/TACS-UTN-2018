package ar.utn.tacs.service.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.model.commons.DontHaveOperationCoinException;
import ar.utn.tacs.model.commons.InsufficientCryptoCurrencyException;
import ar.utn.tacs.model.commons.InsufficientMoneyException;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.wallet.CoinAmountRest;
import ar.utn.tacs.service.wallet.WalletService;

public class WalletServiceTestSuite extends ServiceTestSuite{
	
	@Autowired
	WalletService walletService;
	
	@Test
	public void tostadoCompraUnBitcoinYEsMillonario() {
		
		String token = getTokenTostado();
		
		BigDecimal cantidadBitcoin = (new BigDecimal(0)).add(getUserTostadoPosta().getWallet().getCoinAmountByTicker("BTC").getAmount());
		
		CoinAmountRest coinAmountRest = new CoinAmountRest();
		coinAmountRest.setTicker("BTC");
		coinAmountRest.setAmount("1");
		
		try {
			walletService.buy(token, coinAmountRest);
		} catch (UtnTacsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BigDecimal cantidadNuevaBitcoin = getUserTostadoPosta().getWallet().getCoinAmountByTicker("BTC").getAmount();
		
		Assert.assertEquals(cantidadNuevaBitcoin,(new BigDecimal(1)).add(cantidadBitcoin));
	}
	
	@Test(expected = InsufficientMoneyException.class)
	public void tostadoIntentaComprarDiezBitcoinYNoTienePlata() throws UtnTacsException {
		
		tostadoCompraUnBitcoinYEsMillonario();
		
		String token = getTokenTostado();
		
		CoinAmountRest coinAmountRest = new CoinAmountRest();
		coinAmountRest.setTicker("BTC");
		coinAmountRest.setAmount("10");
		
		walletService.buy(token, coinAmountRest);
		
	}
	
	@Test(expected = InsufficientCryptoCurrencyException.class)
	public void tostadoIntentaVenderDiezBitcoinYNoTieneSuficiente() throws UtnTacsException {
		
		String token = getTokenTostado();
		
		CoinAmountRest coinAmountRest = new CoinAmountRest();
		coinAmountRest.setTicker("BTC");
		coinAmountRest.setAmount("10");
		
		walletService.sale(token, coinAmountRest);
	}
	
	@Test(expected = DontHaveOperationCoinException.class)
	public void tostadoIntentaVenderUnRippleYNoTieneMoneda() throws UtnTacsException {
		
		tostadoCompraUnBitcoinYEsMillonario();
		
		String token = getTokenTostado();
		
		CoinAmountRest coinAmountRest = new CoinAmountRest();
		coinAmountRest.setTicker("XRP");
		coinAmountRest.setAmount("1");
		
		walletService.sale(token, coinAmountRest);
	}
	
	@Test
	public void tostadoVendeTodosSusBitcoinYQuedaPobre() {
		
		String token = getTokenTostado();
		
		BigDecimal cantidadBitcoin = getUserTostadoPosta().getWallet().getCoinAmountByTicker("BTC").getAmount();
		
		CoinAmountRest coinAmountRest = new CoinAmountRest();
		coinAmountRest.setTicker("BTC");
		coinAmountRest.setAmount(cantidadBitcoin.toString());
		
		try {
			walletService.sale(token, coinAmountRest);
		} catch (UtnTacsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BigDecimal cantidadNuevaBitcoin = getUserTostadoPosta().getWallet().getCoinAmountByTicker("BTC").getAmount();
		
		Assert.assertTrue(cantidadNuevaBitcoin.equals(new BigDecimal(0)));
	}
	
	@Test
	public void tostadoTieneTransaccionesPorqueEsElLoboDeWallStreet() {
		
		tostadoCompraUnBitcoinYEsMillonario();
		
		String token = getTokenTostado();
		
		List<Transaction> transacciones = walletService.userTransactionHistory(token,"BTC");
		
		Assert.assertNotNull(transacciones);
		Assert.assertNotEquals(transacciones.size(), 0);
	}
}
