package ar.utn.tacs.service.external;

import java.util.Map;

import ar.utn.tacs.service.GenericService;

public interface ExternalService extends GenericService {

	/**
	 * @return {@link Map}
	 */
	Map<String, Object> coinMarketCap();
}
