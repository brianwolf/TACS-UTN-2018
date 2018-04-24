package ar.utn.tacs.rest.wallet.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	public Response buy(@HeaderParam(value = "token")String token,Map<String, Object> resultMap) {
		//@PathParam("idUser") long idUser,@PathParam("idCoin") long idCoin, @PathParam("amount") BigDecimal amount
		try {
			//ESTO LUEGO SE VA A CAMBIAR
			resultMap.put("token",token);
			walletService.buy(resultMap);
			return Response.status(Response.Status.OK).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path(WalletRest.SALE)
	@Override
	public Response sale(@HeaderParam(value = "token")String token,Map<String, Object> resultMap) {
		try {
			//ESTO LUEGO SE VA A CAMBIAR
			resultMap.put("token",token);
			walletService.sale(resultMap);
			return Response.status(Response.Status.OK).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path(WalletRestImpl.USER_TRANSACTION_HISTORY)
	@Override
	public Response userTransactionHistory(@HeaderParam(value = "token")String token, @PathParam("ticker")String coinSymbol) {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		try {
			transactions = walletService.userTransactionHistory(token, coinSymbol);
			return Response.status(Response.Status.OK).entity(transactions).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
