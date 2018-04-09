package ar.utn.tacs.externalService.rest;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonValue;

import ar.utn.tacs.rest.GenericRest;

public interface ExternalRest extends GenericRest {

	public static final String BASE = "/externalService";

	public static final String COIN_MARKET_CAP = "/CoinMarketCap";

	/**
	 * @return {@link JsonValue}
	 */
	Response coinMarketCap();
}
