package ar.utn.tacs.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coin {

    @Getter @Setter private String id;
    @Getter @Setter private String name;
    @Getter @Setter private String symbol;
    @Getter @Setter private String price_usd;
    @Getter @Setter private String price_btc;   

}
