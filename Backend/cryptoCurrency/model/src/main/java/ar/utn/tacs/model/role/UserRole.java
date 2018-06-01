package ar.utn.tacs.model.role;

import java.math.BigInteger;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserRole extends Role{
	
	private static final String DESCRIPTION = "Usuario";
	
	public UserRole(BigInteger id) {
		super(id,DESCRIPTION);
	}
}
