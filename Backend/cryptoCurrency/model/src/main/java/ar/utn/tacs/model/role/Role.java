package ar.utn.tacs.model.role;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;

import ar.utn.tacs.dao.persistent.impl.MongoPersistentObject;

@Document(collection="roles")
@JsonIgnoreProperties(value = { "id", "functionalities" })
public abstract class Role extends MongoPersistentObject {

	private String description;

	private List<Funcionality> functionalities = new ArrayList<Funcionality>();

	public Role(String description) {

		this.description = description;
	}

	public Role() {
		super();
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

		return this.functionalities.stream().anyMatch(f -> f.canAccessTo(requestPath));
	}

}
