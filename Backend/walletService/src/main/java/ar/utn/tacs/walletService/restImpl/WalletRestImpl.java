package ar.utn.tacs.walletService.restImpl;

import java.math.BigDecimal;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.walletService.rest.WalletRest;
import ar.utn.tacs.walletService.service.WalletService;

@Path(WalletRestImpl.base)
public class WalletRestImpl implements WalletRest{
	
	@Autowired
	private WalletService externalService;
	
	@Override
	public Response buy(long idUser, long idCoin, BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Response sale(long idUser, long idCoin, BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Response buyHistory(long idUser) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Response userTransactionHistory(long idUser, long idCoin) {
		// TODO Auto-generated method stub
		return null;
	}
}
