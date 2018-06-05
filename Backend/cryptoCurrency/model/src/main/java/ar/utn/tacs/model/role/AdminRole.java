package ar.utn.tacs.model.role;

import org.springframework.data.annotation.TypeAlias;

@TypeAlias(value = "ar.utn.tacs.model.role.AdminRole")
public class AdminRole extends Role {
	
	public AdminRole() {
		super();
		this.setDescription(Role.ADMIN);
	}
}
