package ar.utn.tacs.externalService.restImpl;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.externalService.rest.ExternalRest;
import ar.utn.tacs.externalService.service.ExternalService;

@Path(ExternalRestImpl.base)
public class ExternalRestImpl implements ExternalRest{
	
	@Autowired
	private ExternalService externalService;
	
	
}
