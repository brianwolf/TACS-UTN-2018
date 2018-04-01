package ar.utn.tacs.servicesImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import ar.utn.tacs.services.UsuarioService;
import ar.utn.tacs.services.UsuarioServicePath;
import ar.utn.tacs.usuario.Usuario;

@Path(UsuarioServicePath.baseUsuarioService)
public class UsuarioServiceImpl implements UsuarioService{
	
	@GET
	@Path(UsuarioServicePath.getUsuarioPorId)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Usuario getUsuarioPorId(@PathParam(value = "nick") int idUsuario) {
		
		Usuario usuarioResultado = new Usuario();

		/* y aqui estaria mi logica...
		 * SI TUVIERA UNA...!!!
		 * */
		
		return usuarioResultado;
	}
	
	@POST
	@Path(UsuarioServicePath.validarNickYPass)
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Usuario validarNickYPass(@PathParam(value = "nick") String nick, @PathParam(value = "pass") String pass) {
		
		Usuario usuarioResultado = new Usuario();

		return usuarioResultado;
	}
}
