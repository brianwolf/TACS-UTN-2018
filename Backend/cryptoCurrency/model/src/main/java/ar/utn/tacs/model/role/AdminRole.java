package ar.utn.tacs.model.role;

import java.math.BigInteger;

import org.springframework.data.annotation.TypeAlias;

@TypeAlias(value="ar.utn.tacs.model.role.AdminRole")
public class AdminRole extends Role{

	private static final String DESCRIPTION = "Administrador";
	
	public AdminRole(BigInteger id) {
		super(id,DESCRIPTION);
	}
	
}
