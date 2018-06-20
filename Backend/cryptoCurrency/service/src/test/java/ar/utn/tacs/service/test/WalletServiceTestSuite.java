package ar.utn.tacs.service.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.model.commons.DontHaveOperationCoinException;
import ar.utn.tacs.model.commons.InsufficientCryptoCurrencyException;
import ar.utn.tacs.model.commons.InsufficientMoneyException;
import ar.utn.tacs.model.deposit.Deposit;
import ar.utn.tacs.model.deposit.DepositRest;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.wallet.CoinAmount;
import ar.utn.tacs.model.wallet.CoinAmountRest;

public class WalletServiceTestSuite extends ServiceTestSuite{
	
	@Test
	public void tostadoCompraUnBitcoinYEsMillonario() {
		
		BigDecimal cantidadDolares = getUserTostadoPosta().getWallet().getDolarAmount();
		
		BigDecimal cantidadBitcoinsAComprar = new BigDecimal(1);
		
		
		BigDecimal cantidadBitcoin = getUserTostadoPosta().getWallet().getCoinAmountByTicker("BTC").getAmount();
		
		tostadoCompraBitcoin(cantidadBitcoinsAComprar);
		
		//ESTO ES UNA NEGRADA, LO HAGO PARA OBTENER EL PRECIO QUE SE USO EN LA COMPRA
		BigDecimal costoDeBitcoin = getUserTostadoPosta().getWallet().getCoinAmountByTicker("BTC").getCoin().getValueInDollars();
		
		BigDecimal cantidadNuevaBitcoin = getUserTostadoPosta().getWallet().getCoinAmountByTicker("BTC").getAmount();
		BigDecimal cantidadNuevaDolares = getUserTostadoPosta().getWallet().getDolarAmount();
		
		Assert.assertEquals(cantidadBitcoinsAComprar.add(cantidadBitcoin),cantidadNuevaBitcoin);
		Assert.assertEquals(cantidadDolares.subtract(costoDeBitcoin.multiply(cantidadBitcoinsAComprar)),cantidadNuevaDolares);
	}
	
	@Test
	public void tostadoCompraUnRippleYEsMillonario() {
		
		BigDecimal cantidadDolares = getUserTostadoPosta().getWallet().getDolarAmount();
		
		BigDecimal cantidadRippleAComprar = new BigDecimal(1);
		
		BigDecimal cantidadRipple = getUserTostadoPosta().getWallet()
									.getCoinAmountByTicker("XRP")==null ? new BigDecimal(0) 
									: getUserTostadoPosta().getWallet().getCoinAmountByTicker("XRP").getAmount();
		
		tostadoCompraMoneda("XRP",cantidadRippleAComprar);
		
		//ESTO ES UNA NEGRADA, LO HAGO PARA OBTENER EL PRECIO QUE SE USO EN LA COMPRA
		BigDecimal costoDeRipple = getUserTostadoPosta().getWallet().getCoinAmountByTicker("XRP").getCoin().getValueInDollars();
		
		BigDecimal cantidadNuevaRipple = getUserTostadoPosta().getWallet().getCoinAmountByTicker("XRP").getAmount();
		BigDecimal cantidadNuevaDolares = getUserTostadoPosta().getWallet().getDolarAmount();
		
		Assert.assertEquals((new BigDecimal(1)).add(cantidadRipple),cantidadNuevaRipple);
		Assert.assertEquals(cantidadDolares.subtract(costoDeRipple.multiply(cantidadRippleAComprar)),cantidadNuevaDolares);
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
		
		CoinAmount amountBitcoinNuevo = getUserTostadoPosta().getWallet().getCoinAmountByTicker("BTC");
		
		Assert.assertNull(amountBitcoinNuevo);
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
		Assert.assertEquals(1,transacciones.size());
	}
	
	//Incidente en produccion (1 compra de una moneda se le acredita a todos los users)
	@Test
	public void tostadoCompraUnBitcoinYSoloSeLeAcreditaAEl() {
		
		BigDecimal cantidadBitcoinsAComprar = new BigDecimal(1);
		
		
		BigDecimal cantidadBitcoinTostado = getUserTostadoPosta().getWallet().getCoinAmountByTicker("BTC").getAmount();
		BigDecimal cantidadBitcoinLobezzzno = getUserLobezzznoPosta().getWallet().getCoinAmountByTicker("BTC").getAmount();
		
		tostadoCompraBitcoin(cantidadBitcoinsAComprar);
		
		
		BigDecimal cantidadNuevaBitcoinTostado = getUserTostadoPosta().getWallet().getCoinAmountByTicker("BTC").getAmount();
		BigDecimal cantidadNuevaBitcoinLobezzzno = getUserLobezzznoPosta().getWallet().getCoinAmountByTicker("BTC").getAmount();
		
		Assert.assertEquals(cantidadBitcoinsAComprar.add(cantidadBitcoinTostado),cantidadNuevaBitcoinTostado);
		Assert.assertEquals(cantidadBitcoinLobezzzno,cantidadNuevaBitcoinLobezzzno);
	}
	
	@Test
	public void tostadoYLobezzznoDepositanPeroTostadoSoloVeSuDeposito() throws UtnTacsException{
		
		String nickTostado = getLoginTostado().getNick();
		String tokenTostado = getTokenTostado();
		String tokenLobezzzno = getTokenLobezzzno();
		
		DepositRest depositRestTostado = new DepositRest();
		depositRestTostado.setNumber("1234");
		depositRestTostado.setState("WAITING");
		depositRestTostado.setAmount("1000");
		DepositRest depositRestLobezzzno = new DepositRest();
		depositRestLobezzzno.setNumber("5678");
		depositRestLobezzzno.setState("WAITING");
		depositRestLobezzzno.setAmount("581");
		
		walletService.declareDeposit(tokenTostado, depositRestTostado);
		walletService.declareDeposit(tokenLobezzzno, depositRestLobezzzno);
		
		
		List<Deposit> depositosTostado = walletService.getDepositsByToken(tokenTostado);
		
		Assert.assertEquals(depositosTostado.size(), 1);
		Assert.assertEquals(depositosTostado.get(0).getUser().getLogin().getNick(), nickTostado);
	}

}
