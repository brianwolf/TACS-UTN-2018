package ar.utn.tacs.model.role;

import java.math.BigInteger;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AdminRole extends Role{

	private static final String DESCRIPTION = "Administrador";
	
	public AdminRole(BigInteger id) {
		super(id,DESCRIPTION);
	}
	
}
