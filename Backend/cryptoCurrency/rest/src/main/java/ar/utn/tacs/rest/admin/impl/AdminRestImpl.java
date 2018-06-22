package ar.utn.tacs.rest.admin.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.model.commons.ApprovingApprovedDepositException;
import ar.utn.tacs.model.commons.NotExistDepositException;
import ar.utn.tacs.model.commons.RejectingApprovedDepositException;
import ar.utn.tacs.model.commons.RejectingRejectedDepositException;
import ar.utn.tacs.model.commons.UserNotFoundException;
import ar.utn.tacs.model.deposit.Deposit;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.user.UserTransactionRest;
import ar.utn.tacs.rest.admin.AdminRest;
import ar.utn.tacs.service.admin.AdminService;

@Path(AdminRest.BASE)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminRestImpl implements AdminRest {

	@Autowired
	private AdminService adminService;

	@GET
	@Path(AdminRest.COMPARE_BALANCE)
	@Override
	public Response compareBalance(@PathParam("nickA") String nickA, @PathParam("nickB") String nickB) {
		User userResul;

		userResul = adminService.compareBalance(nickA, nickB);
		return Response.status(Response.Status.OK).entity(userResul).build();
	}

	@GET
	@Path(AdminRest.STATES_LAST_WEEK)
	@Override
	public Response statesLastWeek() {

		HashMap<String, BigInteger> map = new HashMap<>();
		
		BigInteger transaccionsCount = adminService.statesLastWeek();
		map.put("transactionsCount", transaccionsCount);

		return Response.status(Response.Status.OK).entity(map).build();
	}

	@GET
	@Path(AdminRest.STATES_LAST_MONTH)
	@Override
	public Response statesLastMonth() {

		HashMap<String, BigInteger> map = new HashMap<>();

		BigInteger transaccionsCount = adminService.statesLastMonth();
		map.put("transactionsCount", transaccionsCount);

		return Response.status(Response.Status.OK).entity(map).build();
	}

	@GET
	@Path(AdminRest.STATES_ALL)
	@Override
	public Response statesAll() {

		HashMap<String, BigInteger> map = new HashMap<>();
		
		BigInteger transaccionsCount = adminService.statesAll();
		map.put("transactionsCount", transaccionsCount);

		return Response.status(Response.Status.OK).entity(map).build();
	}

	@GET
	@Path(AdminRest.STATES_BY_BEFORE_DAYS)
	@Override
	public Response statesByBeforeDays(@DefaultValue("0") @QueryParam("beforeDays") Integer beforeDays) {

		HashMap<String, BigInteger> map = new HashMap<>();
	
		BigInteger transaccionsCount = adminService.statesByBeforeDays(beforeDays);
		map.put("transactionsCount", transaccionsCount);

		return Response.status(Response.Status.OK).entity(map).build();
	}

	@GET
	@Path(AdminRest.USERS_NICKS_ALL)
	@Override
	public Response getUsersNickAll() {
		
		List<String> nicks = adminService.getUsersNickAll();
		return Response.status(Response.Status.OK).entity(nicks).build();
	}

	@GET
	@Path(AdminRest.GET_USER)
	@Override
	public Response getUser(@DefaultValue("") @QueryParam("nick") String nick) {
		
		UserTransactionRest userTransactionRest = adminService.getUser(nick);
	
		if (userTransactionRest == null) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	
		return Response.status(Response.Status.OK).entity(userTransactionRest).build();
	}

	@PUT
	@Path(AdminRest.APPROVE_DEPOSIT)
	@Override
	public Response approveDeposit(@PathParam(value="depositNumber") String despositNumber) throws ApprovingApprovedDepositException, NotExistDepositException {
		
		this.adminService.approveDeposit(despositNumber);
		return Response.status(Response.Status.OK).build();
	}

	@PUT
	@Path(AdminRest.REJECT_DEPOSIT)
	@Override
	public Response rejectDeposit(@PathParam(value="depositNumber") String despositNumber) throws RejectingRejectedDepositException, RejectingApprovedDepositException, NotExistDepositException {
		
		this.adminService.rejectDeposit(despositNumber);
		return Response.status(Response.Status.OK).build();
	}

	@GET
	@Path(AdminRest.GET_DEPOSITS)
	@Override
	public Response getDeposits(@DefaultValue(value="") @QueryParam(value="status") String statusDescription) {
		
		List<Deposit> deposits = this.adminService.getDeposits(statusDescription);
		
		if (deposits.isEmpty()) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.status(Response.Status.OK).entity(deposits).build();
	}
	
	@GET
	@Path(AdminRest.GET_DEPOSITS_ALL)
	@Override
	public Response getDepositsAll() {
		
		List<Deposit> deposits = this.adminService.getDepositsAll();
		
		if (deposits.isEmpty()) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.status(Response.Status.OK).entity(deposits).build();
	}

	@PUT
	@Path(AdminRest.CONVERT_USER_TO_ADMIN)
	@Override
	public Response convertUserToAdmin(@DefaultValue(value="") @QueryParam(value="nick") String nick) throws UserNotFoundException {
		
		this.adminService.convertUserToAdmin(nick);
		return Response.status(Response.Status.OK).build();
	}
}
