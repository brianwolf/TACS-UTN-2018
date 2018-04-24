package ar.utn.tacs.model.operation;

public abstract class Operation {

	protected Long id;
	protected String description;
	
	protected Operation(Long id, String description) {
		this.id = id;
		this.description = description;
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

	public abstract void doOperation();
}
