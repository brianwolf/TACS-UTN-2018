package ar.utn.tacs.externalService.restImpl;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.externalService.rest.ExternalRest;
import ar.utn.tacs.externalService.service.ExternalService;

@Path(ExternalRest.BASE)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExternalRestImpl implements ExternalRest{
	
	@Autowired
	private ExternalService externalService;

	@GET
	@Path(ExternalRest.COIN_MARKET_CAP)
	@Override
	public Response coinMarketCap() {
		try {
			HashMap<String, String> mapResult = new HashMap<String, String>();
				mapResult.put("id", "bitcoin");
				mapResult.put("name", "Bitcoin");
				mapResult.put("symbol", "BTC");
				mapResult.put("rank", "1");
				mapResult.put("price_usd", "573.137");
				mapResult.put("price_btc", "1.0");
				mapResult.put("24h_volume_usd", "72855700.0");
				mapResult.put("market_cap_usd", "9080883500.0");
				mapResult.put("available_supply", "15844176.0");
				mapResult.put("total_supply", "15844176.0");
				mapResult.put("percent_change_1h", "0.04");
				mapResult.put("percent_change_24h", "-0.3");
				mapResult.put("percent_change_7d", "-0.57");
				mapResult.put("last_updated", "1472762067");
				

			return Response.status(Response.Status.OK).entity(mapResult).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
