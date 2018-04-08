package ar.utn.tacs.userService.restImpl;

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

import ar.utn.tacs.user.User;
import ar.utn.tacs.userService.rest.UserRest;
import ar.utn.tacs.userService.service.UserService;

@Path(UserRestImpl.BASE)
public class UserRestImpl implements UserRest{
	
	@Autowired
	private UserService userService;
	
	@GET
	@Path("/getPrueba")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPrueba() {
		
		try {
			User user = new User();
				user.setApellido("Tagrande");
				user.setNombre("Juancho");
				user.setNick("juancito");
		
			return Response.status(Response.Status.OK).entity(user).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	@Path("/postPrueba")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postPrueba(User user) {
		
		try {
			
			return Response.status(Response.Status.OK).entity("Tu nombre es "+user.getNombre()).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	
	@GET
	@Path("/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response getUserById(@PathParam(value = "userId") int userId) {
		
		try {
			User user = this.userService.getUserById(userId);
			
			return Response.status(Response.Status.OK).entity(user).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	@Path(UserRestImpl.VALIDATE+"/{nick}/{pass}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response validateNickAndPass(@PathParam(value = "nick") String nick, @PathParam(value = "pass") String pass) {
		
		try {
			User user = this.userService.validateNickAndPass(nick, pass);

			return Response.status(Response.Status.OK).entity(user).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public Response newUser(String nick, String pass) {
		try {

			return Response.status(Response.Status.OK).entity(null).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
