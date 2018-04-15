package ar.utn.tacs.rest.wallet.impl;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.rest.wallet.WalletRest;
import ar.utn.tacs.service.user.UserService;
import ar.utn.tacs.service.wallet.WalletService;

@Path(WalletRest.BASE)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WalletRestImpl implements WalletRest{
	
	@Autowired
	private WalletService externalService;
	
	@Autowired
	private UserService userService;
	
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
	@Path(WalletRest.BUY_HISTORY)
	@Override
	public Response buyHistory(@PathParam("idUser") Long idUser) {
		try {
			return Response.status(Response.Status.OK).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path(WalletRestImpl.USER_TRANSACTION_HISTORY)
	@Override
	public Response userTransactionHistory(Long idUser, Long idCoin) {
		try {
			return Response.status(Response.Status.OK).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
