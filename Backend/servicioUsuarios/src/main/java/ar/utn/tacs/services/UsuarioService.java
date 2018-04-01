package ar.utn.tacs.services;

import ar.utn.tacs.servicio.Servicio;
import ar.utn.tacs.usuario.Usuario;


public interface UsuarioService extends Servicio{
	
	/**
	 * Retorna un usuario por su id
	 * 
	 * @param idUsuario
	 * @return {@link Usuario}
	 */
	public Usuario getUsuarioPorId(int idUsuario);
	
	
	/**
	 * Valida la existencia de un usuario con ese nick y esa password
	 * 
	 * @param nick
	 * @param pass
	 * @return {@link Usuario}
	 */
	public Usuario validarNickYPass(String nick, String pass);
}
