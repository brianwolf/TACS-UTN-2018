package ar.utn.tacs.model.role;

import java.util.ArrayList;
import java.util.List;

public class AdminRole extends Role{

	private static final Long ID_ROLE = 1l;
	private static final String DESCRIPTION = "Administrador";
	
	public AdminRole() {
		super(ID_ROLE, DESCRIPTION);
		List<Funcionality> funcionalities = new ArrayList<Funcionality>();
		funcionalities.add(new Funcionality(1l,"admin","/admin"));
		funcionalities.add(new Funcionality(2l,"wallet","/wallet"));
		funcionalities.add(new Funcionality(3l,"external services","/services/external"));
		funcionalities.add(new Funcionality(4l,"users","/users"));
		
		this.setFuncionalities(funcionalities);
	}
	
}
