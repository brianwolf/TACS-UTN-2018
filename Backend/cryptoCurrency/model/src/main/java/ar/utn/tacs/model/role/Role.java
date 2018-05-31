package ar.utn.tacs.model.role;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"id", "funcionalities"})
public abstract class Role {
	
	private Long id;
	
	private String description;
	
	private List<Funcionality> funcionalities;  
	
	public Role(Long id, String description) {
		this.id = id;
		this.description = description;
	}

	public Role() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Funcionality> getFuncionalities() {
		return funcionalities;
	}

	public void setFuncionalities(List<Funcionality> funcionalities) {
		this.funcionalities = funcionalities;
	}

	public boolean canAccessTo(String requestPath) {
		
		return this.funcionalities.stream().anyMatch(f->f.canAccessTo(requestPath));
	}

}
