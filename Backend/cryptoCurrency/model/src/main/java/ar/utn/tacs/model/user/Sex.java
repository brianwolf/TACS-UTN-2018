package ar.utn.tacs.model.user;

import java.math.BigInteger;

public class Sex {

	private BigInteger id;
	private String description;

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
}
