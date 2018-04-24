package ar.utn.tacs.rest.wallet.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.rest.wallet.WalletRest;
import ar.utn.tacs.service.wallet.WalletService;

@Path(WalletRest.BASE)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WalletRestImpl implements WalletRest{
	
	@Autowired
	private WalletService walletService;
	
	@POST
	@Path(WalletRest.BUY)
	@Override
	public Response buy(Map<String, String> resultMap) {
		//@PathParam("idUser") long idUser,@PathParam("idCoin") long idCoin, @PathParam("amount") BigDecimal amount
		try {
			return Response.status(Response.Status.OK).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path(WalletRest.SALE)
	@Override
	public Response sale(Map<String, String> resultMap) {
		try {
			return Response.status(Response.Status.OK).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path(WalletRestImpl.USER_TRANSACTION_HISTORY)
	@Override
	public Response userTransactionHistory(String token, String coinSymbol) {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		try {
			transactions = walletService.userTransactionHistory(token, coinSymbol);
			return Response.status(Response.Status.OK).entity(transactions).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
