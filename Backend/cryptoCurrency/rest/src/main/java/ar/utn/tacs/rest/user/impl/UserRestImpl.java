package ar.utn.tacs.rest.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.model.user.User;
import ar.utn.tacs.rest.user.UserRest;
import ar.utn.tacs.service.user.UserService;

@Path(UserRestImpl.BASE)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRestImpl implements UserRest {

	@Autowired
	private UserService userService;

	@GET
	@Path("/getPrueba")
	public Response getPrueba() {
		
		List<User> users = null;
		
		try {
			
			users = this.userService.getUsers();
			return Response.status(Response.Status.OK).entity(users).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("/postPrueba")
	public Response postPrueba(User user) {
		try {
			return Response.status(Response.Status.OK).entity("Tu nickt es " + user.getNick()).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path(UserRest.NEW_USER)
	@Override
	public Response newUser(Map<String,Object> map) {

		try {
			String nick = (String) map.get("nick");
			String pass = (String) map.get("pass");
			
			userService.newUser(nick,pass);
			
			return Response.status(Response.Status.OK).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path(UserRest.GET_TOKEN_BY_LOGIN)
	@Override
	public Response getTokenByLogin(@PathParam("nick") String nick, @PathParam("pass") String pass) {
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		String token = "";
		
		try {
			token = this.userService.getTokenByLogin(nick, pass);
			tokenMap.put("token", token);
			
			return Response.status(Response.Status.OK).entity(tokenMap).build();
			
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
			return Response.status(Response.Status.OK).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
