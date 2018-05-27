package ar.utn.tacs.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import ar.utn.tacs.dao.GenericDao;

public abstract class GenericAbstractDaoImpl<T> implements GenericDao{
	
	@Autowired
	protected MongoTemplate mongoTemplate;
}
