package ar.utn.tacs.rest.external.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.rest.external.ExternalRest;
import ar.utn.tacs.service.external.ExternalService;

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
			
			Map<String,Object> mapResult = externalService.coinMarketCap();

			return Response.status(Response.Status.OK).entity(mapResult).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
