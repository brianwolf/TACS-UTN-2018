package ar.utn.tacs.model.role;

import org.springframework.data.annotation.TypeAlias;

@TypeAlias(value = "ar.utn.tacs.model.role.UserRole")
public class UserRole extends Role {
	
	public UserRole() {
		super();
		this.description = Role.USER;
	}
}
