package ar.utn.tacs.service.external.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.dao.external.ExternalDao;
import ar.utn.tacs.service.external.ExternalService;

public class ExternalServiceImpl implements ExternalService{
	
	@Autowired
	private ExternalDao externalDao;

	@Override
	public Map<String, Object> coinMarketCap() {
		// TODO Auto-generated method stub
		return null;
	}

}
