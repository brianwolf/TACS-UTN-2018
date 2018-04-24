package ar.utn.tacs.model.role;

import org.codehaus.jackson.annotate.JsonIgnoreType;

//ESTO SE PONE POR AHORA PORQUE JACKSON ROMPE AL SERIALIZAR CLASES VACIAS
@JsonIgnoreType 
public class UserRole extends Role{
	
	private static final Long ID_ROLE = 2l;
	private static final String DESCRIPTION = "Usuario";
	
	public UserRole() {
		super(ID_ROLE, DESCRIPTION);
	}
}
