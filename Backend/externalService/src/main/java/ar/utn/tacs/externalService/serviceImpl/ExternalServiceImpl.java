package ar.utn.tacs.externalService.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.externalService.dao.ExternalDao;
import ar.utn.tacs.externalService.service.ExternalService;

public class ExternalServiceImpl implements ExternalService{
	
	@Autowired
	private ExternalDao externalDao;
	


}
