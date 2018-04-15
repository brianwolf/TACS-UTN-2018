package ar.utn.tacs.rest.admin.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.model.role.AdminRole;
import ar.utn.tacs.model.role.Role;
import ar.utn.tacs.model.role.UserRole;
import ar.utn.tacs.model.transaction.Transaction;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.wallet.Wallet;
import ar.utn.tacs.rest.admin.AdminRest;
import ar.utn.tacs.service.admin.AdminService;

@Path(AdminRest.BASE)
public class AdminRestImpl implements AdminRest {

	@Autowired
	private AdminService adminService;

	@POST
	@Path(AdminRest.COMPARE_BALANCE)
	@Override
	public Response compareBalance(@PathParam("idUserA") Long idUserA, @PathParam("idUserB") Long idUserB) {
		try {
			List<Role> roles = new ArrayList<Role>();
				roles.add(new AdminRole());
				roles.add(new UserRole());
				
			User user = new User(1l, "juan", "1234", 1, true, new Wallet(), roles);
			
			
			return Response.status(Response.Status.OK).entity(user).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path(AdminRest.STATES_TODAY)
	@Override
	public Response statesToday() {
		try {
			Transaction transaccion = new Transaction();
				transaccion.setId(1l);
				transaccion.setAmount(new BigDecimal("0.21"));
				transaccion.setQuoteTimeNow(new BigDecimal("0.21"));
				transaccion.setQuoteTimeSold(new BigDecimal("0.22"));
				transaccion.setQuoteDifference(transaccion.getQuoteTimeNow().subtract(transaccion.getQuoteTimeSold()));

			return Response.status(Response.Status.OK).entity(transaccion).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path(AdminRest.STATES_THREE_DAYS)
	@Override
	public Response statesThreeDays() {
		try {
			Transaction transaccion = new Transaction();
			transaccion.setId(1l);
			transaccion.setAmount(new BigDecimal("0.21"));
			transaccion.setQuoteTimeNow(new BigDecimal("0.21"));
			transaccion.setQuoteTimeSold(new BigDecimal("0.22"));
			transaccion.setQuoteDifference(transaccion.getQuoteTimeNow().subtract(transaccion.getQuoteTimeSold()));


			return Response.status(Response.Status.OK).entity(transaccion).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path(AdminRest.STATES_LAST_WEEK)
	@Override
	public Response statesLastWeek() {
		try {
			Transaction transaccion = new Transaction();
			transaccion.setId(1l);
			transaccion.setAmount(new BigDecimal("0.21"));
			transaccion.setQuoteTimeNow(new BigDecimal("0.21"));
			transaccion.setQuoteTimeSold(new BigDecimal("0.22"));
			transaccion.setQuoteDifference(transaccion.getQuoteTimeNow().subtract(transaccion.getQuoteTimeSold()));


			return Response.status(Response.Status.OK).entity(transaccion).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path(AdminRest.STATES_LAST_MOTH)
	@Override
	public Response statesLastMonth() {
		try {
			Transaction transaccion = new Transaction();
			transaccion.setId(1l);
			transaccion.setAmount(new BigDecimal("0.21"));
			transaccion.setQuoteTimeNow(new BigDecimal("0.21"));
			transaccion.setQuoteTimeSold(new BigDecimal("0.22"));
			transaccion.setQuoteDifference(transaccion.getQuoteTimeNow().subtract(transaccion.getQuoteTimeSold()));


			return Response.status(Response.Status.OK).entity(transaccion).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path(AdminRest.STATES_START_TIMES)
	@Override
	public Response statesStartTimes() {
		try {
			Transaction transaccion = new Transaction();
				transaccion.setId(1l);
				transaccion.setAmount(new BigDecimal("0.21"));
				transaccion.setQuoteTimeNow(new BigDecimal("0.21"));
				transaccion.setQuoteTimeSold(new BigDecimal("0.22"));
				transaccion.setQuoteDifference(transaccion.getQuoteTimeNow().subtract(transaccion.getQuoteTimeSold()));


			return Response.status(Response.Status.OK).entity(transaccion).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
