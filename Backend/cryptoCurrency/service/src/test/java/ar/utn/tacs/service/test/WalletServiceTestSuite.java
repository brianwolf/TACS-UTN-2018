package ar.utn.tacs.service.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.model.commons.DontHaveOperationCoinException;
import ar.utn.tacs.model.commons.InsufficientCryptoCurrencyException;
import ar.utn.tacs.model.commons.InsufficientMoneyException;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.wallet.CoinAmountRest;

public class WalletServiceTestSuite extends ServiceTestSuite{
	
	@Test
	public void tostadoCompraUnBitcoinYEsMillonario() {
		
		@SuppressWarnings("unused")
		String token = getTokenTostado();
		
		BigDecimal cantidadBitcoin = getUserTostadoPosta().getWallet().getCoinAmountByTicker("BTC").getAmount();
		
		tostadoCompraBitcoin(new BigDecimal(1));
		
		
		BigDecimal cantidadNuevaBitcoin = getUserTostadoPosta().getWallet().getCoinAmountByTicker("BTC").getAmount();
		
		Assert.assertEquals(cantidadNuevaBitcoin,(new BigDecimal(1)).add(cantidadBitcoin));
	}
	
	@Test(expected = InsufficientMoneyException.class)
	public void tostadoIntentaComprarDiezMilBitcoinYNoTienePlata() throws UtnTacsException {
		
		String token = getTokenTostado();
		
		CoinAmountRest coinAmountRest = new CoinAmountRest();
		coinAmountRest.setTicker("BTC");
		coinAmountRest.setAmount("10000");
		
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
		
		Assert.assertEquals(cantidadNuevaBitcoin.compareTo(new BigDecimal(0)),0);
	}
	
	@Test
	public void tostadoTieneTransaccionesPorqueEsElLoboDeWallStreet() {
		
		tostadoCompraBitcoin(new BigDecimal(1));
		
		String token = getTokenTostado();
		
		List<Transaction> transacciones = null;
		try {
			transacciones = walletService.userTransactionHistory(token,"BTC");
		} catch (UtnTacsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertNotNull(transacciones);
		Assert.assertEquals(transacciones.size(), 1);
	}
}
