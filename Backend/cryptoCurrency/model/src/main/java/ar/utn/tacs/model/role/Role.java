package ar.utn.tacs.model.role;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"id", "functionalities"})
public abstract class Role {
	
	private BigInteger id;
	
	private String description;
	
	private List<Funcionality> functionalities = new ArrayList<Funcionality>();
	
	public Role(BigInteger id, String description) {
		this.id = id;
		this.description = description;
	}

	public Role() {
		super();
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public List<Funcionality> getFunctionalities() {
		return functionalities;
	}

	public void setFunctionalities(List<Funcionality> functionalities) {
		this.functionalities = functionalities;
	}

	public boolean canAccessTo(String requestPath) {
		
		return this.functionalities.stream().anyMatch(f->f.canAccessTo(requestPath));
	}

}
