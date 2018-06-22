package ar.utn.tacs.rest.wallet.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.model.commons.ExistingDepositException;
import ar.utn.tacs.model.deposit.Deposit;
import ar.utn.tacs.model.deposit.DepositRest;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.wallet.CoinAmountRest;
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
	public Response buy(@HeaderParam(value = "token")String token, CoinAmountRest coinAmountRest) throws UtnTacsException {
		
		walletService.buy(token, coinAmountRest);
		return Response.status(Response.Status.CREATED).build();
	}

	@POST
	@Path(WalletRest.SALE)
	@Override
	public Response sale(@HeaderParam(value = "token")String token, CoinAmountRest coinAmountRest) throws UtnTacsException {
		
		walletService.sale(token, coinAmountRest);
		return Response.status(Response.Status.CREATED).build();
	}

	@GET
	@Path(WalletRestImpl.USER_TRANSACTION_HISTORY)
	@Override
	public Response userTransactionHistory(
			@HeaderParam(value = "token") String token, 
			@DefaultValue("") @QueryParam("ticker")String ticker
	) throws UtnTacsException {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		transactions = walletService.userTransactionHistory(token, ticker);
		return Response.status(Response.Status.OK).entity(transactions).build();
	}

	@GET
	@Path(WalletRestImpl.USER_WALLET)
	@Override
	public Response userWalletByToken(@HeaderParam(value = "token") String token, @QueryParam("ticker") String ticker) throws UtnTacsException {
		
		Object response = String.valueOf(ticker)!="null"&&!String.valueOf(ticker).isEmpty() ? 
				walletService.userCoinAmountByToken(token,ticker) : 
				walletService.userWalletByToken(token);
		
		return Response.status(Response.Status.OK).entity(response).build();
	}

	@POST
	@Path(WalletRestImpl.DEPOSITS)
	@Override
	public Response declareDeposit(@HeaderParam(value = "token") String token, DepositRest depositRest) throws ExistingDepositException {
		
		this.walletService.declareDeposit(token, depositRest);
		return Response.status(Response.Status.OK).build();
	}
	
	@GET
	@Path(WalletRestImpl.DEPOSITS)
	@Override
	public Response getDepositsByToken(@HeaderParam(value = "token") String token) throws UtnTacsException {
		
		List<Deposit> deposits = this.walletService.getDepositsByToken(token);
		
		if (deposits.isEmpty()) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.status(Response.Status.OK).entity(deposits).build();
	}
	
}
