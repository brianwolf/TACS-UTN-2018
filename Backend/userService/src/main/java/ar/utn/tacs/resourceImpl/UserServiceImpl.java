package ar.utn.tacs.resourceImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.ejb.UserEjb;
import ar.utn.tacs.resource.UserService;
import ar.utn.tacs.usuario.User;

@Path(UserServiceImpl.base)
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserEjb userEjb;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@GET
	@Path(UserServiceImpl.getUserById)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response getUserById(@PathParam(value = "userId") int userId) {
		
		try {
			User user = this.userEjb.getUserById(userId);
			String json = mapper.writeValueAsString(user);
			
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
			
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
	
	@POST
	@Path(UserServiceImpl.validateNickAndPass)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response ValidateNickAndPass(@PathParam(value = "nick") String nick, @PathParam(value = "pass") String pass) {
		
		try {
			User user = this.userEjb.validateNickAndPass(nick, pass);
			String json = mapper.writeValueAsString(user);

			return Response.ok(json, MediaType.APPLICATION_JSON).build();
			
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}
