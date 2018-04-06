package ar.utn.tacs.walletService.restImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.user.User;
import ar.utn.tacs.walletService.rest.WalletRest;
import ar.utn.tacs.walletService.service.WalletService;

@Path(WalletRestImpl.base)
public class WalletRestImpl implements WalletRest{
	
	@Autowired
	private WalletService externalService;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@GET
	@Path("/getPrueba")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getPrueba() {
		
		try {
			User user = new User();
				user.setApellido("Tagrande");
				user.setNombre("Juancho");
				user.setNick("juancito");
		
			String json = mapper.writeValueAsString(user);
			
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
			
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
	
	
	@GET
	@Path(WalletRestImpl.getUserById)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response getUserById(@PathParam(value = "userId") int userId) {
		
		try {
			User user = this.externalService.getUserById(userId);
			String json = mapper.writeValueAsString(user);
			
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
			
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
	
	@POST
	@Path(WalletRestImpl.validateNickAndPass)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response ValidateNickAndPass(@PathParam(value = "nick") String nick, @PathParam(value = "pass") String pass) {
		
		try {
			User user = this.externalService.validateNickAndPass(nick, pass);
			String json = mapper.writeValueAsString(user);

			return Response.ok(json, MediaType.APPLICATION_JSON).build();
			
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response newUser(String nick, String pass) {
		try {

			return Response.ok(null, MediaType.APPLICATION_JSON).build();
			
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}
