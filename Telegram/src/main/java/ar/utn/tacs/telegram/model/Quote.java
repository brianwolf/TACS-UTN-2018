package ar.utn.tacs.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    @Getter @Setter private String type;
    @Getter @Setter private Value value;

}
