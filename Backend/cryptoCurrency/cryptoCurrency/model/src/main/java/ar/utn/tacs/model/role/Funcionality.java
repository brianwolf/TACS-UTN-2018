package ar.utn.tacs.model.role;

import javax.persistence.Id;

public class Funcionality {

	@Id
	private Long id;

	private String description;

	private String baseURL;

	public Funcionality(Long id, String description, String baseURL) {
		this.id = id;
		this.description = description;
		this.baseURL = baseURL;
	}
	
	public Funcionality() {
		
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

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public boolean canAccessTo(String requestPath) {
		
		if(requestPath==null) {
			return false;
		}
		
		return requestPath.startsWith(this.baseURL);
	}

}
