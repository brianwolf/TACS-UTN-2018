package ar.utn.tacs.rest.user.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.rest.user.UserRest;
import ar.utn.tacs.service.user.UserService;

@Path(UserRestImpl.BASE)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRestImpl implements UserRest {

	@Autowired
	private UserService userService;

	@POST
	@Path(UserRest.NEW_USER)
	@Override
	public Response newUser(User user) {

		try {
			userService.newUser(user);
			
			return Response.status(Response.Status.CREATED).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path(UserRest.GET_TOKEN_BY_LOGIN)
	@Override
	public Response getTokenByLogin(Login login) {
		
		try {
			Map<String, Object> tokenMap = new HashMap<String, Object>();
			tokenMap.put("token", this.userService.getTokenByLogin(login));
			
			return Response.status(Response.Status.CREATED).entity(tokenMap).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Path(UserRest.LOGOUT_USER_BY_TOKEN)
	@Override
	public Response logOutUserByToken(@HeaderParam(value = "token")String token) {
		
		try {
			userService.logOutUserByToken(token);
			return Response.status(Response.Status.CREATED).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path(UserRest.GET_USER_BY_TOKEN)
	@Override
	public Response getUserByToken(@HeaderParam(value = "token")String token) {
		
		try {
			User userResult = userService.getUserByToken(token);
			return Response.status(Response.Status.OK).entity(userResult).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
