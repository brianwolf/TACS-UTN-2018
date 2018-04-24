package ar.utn.tacs.rest.admin.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.rest.admin.AdminRest;
import ar.utn.tacs.service.admin.AdminService;

@Path(AdminRest.BASE)
public class AdminRestImpl implements AdminRest {

	@Autowired
	private AdminService adminService;

	@POST
	@Path(AdminRest.COMPARE_BALANCE)
	@Override
	public Response compareBalance(@PathParam("nickA") String nickA, @PathParam("nickB") String nickB) {
		User userResul;
		
		try {
			userResul = adminService.compareBalance(nickA, nickB);
			return Response.status(Response.Status.OK).entity(userResul).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path(AdminRest.STATES_TODAY)
	@Override
	public Response statesToday() {
		List<Transaction> transaccionResult = new ArrayList<Transaction>();
		
		try {
			transaccionResult = adminService.statesToday();
			return Response.status(Response.Status.OK).entity(transaccionResult).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path(AdminRest.STATES_THREE_DAYS)
	@Override
	public Response statesThreeDays() {
		List<Transaction> transaccionResult = new ArrayList<Transaction>();
		
		try {
			transaccionResult = adminService.statesThreeDays();
			return Response.status(Response.Status.OK).entity(transaccionResult).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path(AdminRest.STATES_LAST_WEEK)
	@Override
	public Response statesLastWeek() {
		List<Transaction> transaccionResult = new ArrayList<Transaction>();
		
		try {
			transaccionResult = adminService.statesLastWeek();
			return Response.status(Response.Status.OK).entity(transaccionResult).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path(AdminRest.STATES_LAST_MOTH)
	@Override
	public Response statesLastMonth() {
		List<Transaction> transaccionResult = new ArrayList<Transaction>();
		
		try {
			transaccionResult = adminService.statesLastMonth();
			return Response.status(Response.Status.OK).entity(transaccionResult).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path(AdminRest.STATES_START_TIMES)
	@Override
	public Response statesStartTimes() {
		
		List<Transaction> transaccionResult = new ArrayList<Transaction>();
		
		try {
			transaccionResult = adminService.statesStartTimes();
			return Response.status(Response.Status.OK).entity(transaccionResult).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
