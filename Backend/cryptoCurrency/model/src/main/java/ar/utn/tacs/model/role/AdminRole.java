package ar.utn.tacs.model.role;

import org.codehaus.jackson.annotate.JsonIgnoreType;

//ESTO SE PONE POR AHORA PORQUE JACKSON ROMPE AL SERIALIZAR CLASES VACIAS
@JsonIgnoreType 
public class AdminRole extends Role{

}
