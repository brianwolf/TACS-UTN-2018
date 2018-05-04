package ar.utn.tacs.telegram.model;

import lombok.Getter;
import lombok.Setter;

public class User {
	
	@Getter @Setter private String name;
	@Getter @Setter private String pass;
	
	@Override
	public String toString() {
		return String.format("{ \"nick\"=\"%s\", \"pass\"=\"%s\" }", name, pass);
	}

}
