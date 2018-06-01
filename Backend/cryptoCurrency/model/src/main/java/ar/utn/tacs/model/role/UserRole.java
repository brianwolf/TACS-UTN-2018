package ar.utn.tacs.model.role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.TypeAlias;

@TypeAlias(value="UserRole")
public class UserRole extends Role{
	
	private static final Long ID_ROLE = 2l;
	private static final String DESCRIPTION = "Usuario";
	
	public UserRole() {
		super(ID_ROLE, DESCRIPTION);
		List<Funcionality> funcionalities = new ArrayList<Funcionality>();
		funcionalities.add(new Funcionality(1l,"users","/users"));
		funcionalities.add(new Funcionality(2l,"wallet","/wallet"));
		funcionalities.add(new Funcionality(3l,"external services","/services/external"));
		
		this.setFuncionalities(funcionalities);
	}
}
