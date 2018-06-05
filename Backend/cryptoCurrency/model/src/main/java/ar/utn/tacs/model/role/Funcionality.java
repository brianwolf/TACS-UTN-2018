package ar.utn.tacs.model.role;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Funcionality {

	private String description;

	private String baseURL;

	public Funcionality(String description, String baseURL) {
		this.description = description;
		this.baseURL = baseURL;
	}
	
	public Funcionality() {
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
