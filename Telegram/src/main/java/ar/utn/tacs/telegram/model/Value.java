package ar.utn.tacs.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {

	@Getter @Setter private Long id;
	@Getter @Setter private String quote;

}
