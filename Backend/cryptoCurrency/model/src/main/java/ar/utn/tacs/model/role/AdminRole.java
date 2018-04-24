package ar.utn.tacs.model.role;

import org.codehaus.jackson.annotate.JsonIgnoreType;

//ESTO SE PONE POR AHORA PORQUE JACKSON ROMPE AL SERIALIZAR CLASES VACIAS
@JsonIgnoreType 
public class AdminRole extends Role{

	private static final Long ID_ROLE = 1l;
	private static final String DESCRIPTION = "Administrador";
	
	public AdminRole() {
		super(ID_ROLE, DESCRIPTION);
	}
	
}
