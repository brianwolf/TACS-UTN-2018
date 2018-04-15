package ar.utn.tacs.rest.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.model.role.AdminRole;
import ar.utn.tacs.model.role.Role;
import ar.utn.tacs.model.role.UserRole;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.wallet.Wallet;
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
	public Response newUser(User user) {
		try {
			user.setId(479845465l);
			return Response.status(Response.Status.OK).entity(user).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path(UserRest.GET_USER_BY_LOGIN)
	@Override
	public Response getUserByLogin(@PathParam("nick") String nick, @PathParam("pass") String pass) {
		try {
			List<Role> roles = new ArrayList<Role>();
				roles.add(new AdminRole());
				roles.add(new UserRole());

			List<User> listaUsuariosHard = new ArrayList<User>();
				listaUsuariosHard.add(new User(1l, "juan", "1234", 1, true, new Wallet(), roles));
				listaUsuariosHard.add(new User(2l, "carlos", "1234", 0, true, new Wallet(), roles));
				listaUsuariosHard.add(new User(3l, "nico", "1234", 0, true, new Wallet(), roles));

			User userEncontrado = null;
			for (User user : listaUsuariosHard) {
				if (String.valueOf(user.getNick()).equals(nick) && String.valueOf(user.getPass()).equals(pass)) {
					userEncontrado = user;
				}
			}
			return Response.status(Response.Status.OK).entity(userEncontrado).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path(UserRest.GET_USER_BY_ID)
	@Override
	public Response getUserById(@PathParam("id") long id) {
		try {
			List<Role> roles = new ArrayList<Role>();
			roles.add(new AdminRole());
			roles.add(new UserRole());

			List<User> listaUsuariosHard = new ArrayList<User>();
				listaUsuariosHard.add(new User(1l, "juan", "1234", 1, true, new Wallet(), roles));
				listaUsuariosHard.add(new User(2l, "carlos", "1234", 0, true, new Wallet(), roles));
				listaUsuariosHard.add(new User(3l, "nico", "1234", 0, true, new Wallet(), roles));

			User userEncontrado = null;
			for (User user : listaUsuariosHard) {
				if (user.getId() == id) {
					userEncontrado = user;
				}
			}

			return Response.status(Response.Status.OK).entity(userEncontrado).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path(UserRest.GET_ALL_USERS_IDS)
	@Override
	public Response getAllUserIds() {
		try {
			List<Long> listaHard = new ArrayList<Long>();
				listaHard.add(1l);
				listaHard.add(2l);
				listaHard.add(3l);

			return Response.status(Response.Status.OK).entity(listaHard).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
