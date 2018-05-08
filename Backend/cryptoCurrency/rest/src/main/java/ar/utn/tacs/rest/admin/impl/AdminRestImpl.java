package ar.utn.tacs.rest.admin.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.model.user.User;
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
		
		try {
			userResul = adminService.compareBalance(nickA, nickB);
			return Response.status(Response.Status.OK).entity(userResul).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path(AdminRest.STATES_LAST_WEEK)
	@Override
	public Response statesLastWeek() {

		HashMap<String, BigInteger> map = new HashMap<>();
		
		try {
			BigInteger transaccionsCount = adminService.statesLastWeek();
			map.put("transactionsCount", transaccionsCount);
			
			return Response.status(Response.Status.OK).entity(map).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path(AdminRest.STATES_LAST_MONTH)
	@Override
	public Response statesLastMonth() {

		HashMap<String, BigInteger> map = new HashMap<>();
		
		try {
			BigInteger transaccionsCount = adminService.statesLastMonth();
			map.put("transactionsCount", transaccionsCount);
			
			return Response.status(Response.Status.OK).entity(map).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path(AdminRest.STATES_ALL)
	@Override
	public Response statesAll() {

		HashMap<String, BigInteger> map = new HashMap<>();
		
		try {
			BigInteger transaccionsCount = adminService.statesAll();
			map.put("transactionsCount", transaccionsCount);
			
			return Response.status(Response.Status.OK).entity(map).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path(AdminRest.STATES_BY_BEFORE_DAYS)
	@Override
	public Response statesByBeforeDays(@DefaultValue("0") @QueryParam("beforeDays") Integer beforeDays) {
		
		HashMap<String, BigInteger> map = new HashMap<>();
		
		try {
			BigInteger transaccionsCount = adminService.statesByBeforeDays(beforeDays);
			map.put("transactionsCount", transaccionsCount);
			
			return Response.status(Response.Status.OK).entity(map).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path(AdminRest.USERS_NICKS_ALL)
	@Override
	public Response getUsersNickAll() {
		
		try {
			List<String> nicks = adminService.getUsersNickAll();
			return Response.status(Response.Status.OK).entity(nicks).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path(AdminRest.GET_USER)
	@Override
	public Response getUser(@DefaultValue("") @QueryParam("nick") String nick) {
		try {
			User user = adminService.getUser(nick);
			
			if (user == null) {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
			
			return Response.status(Response.Status.OK).entity(user).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
