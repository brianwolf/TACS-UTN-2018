package ar.utn.tacs.model.role;

import java.math.BigInteger;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Funcionality {

	private BigInteger id;

	private String description;

	private String baseURL;

	public Funcionality(BigInteger id, String description, String baseURL) {
		this.id = id;
		this.description = description;
		this.baseURL = baseURL;
	}
	
	public Funcionality() {
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
