package ar.utn.tacs.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import ar.utn.tacs.dao.GenericDao;

public abstract class GenericAbstractDaoImpl<T> implements GenericDao{
	
	@Autowired
	protected MongoTemplate mongoTemplate;
	
	protected void insert(Object objeto) {
		if (!mongoTemplate.collectionExists(objeto.getClass())) {
			mongoTemplate.createCollection(objeto.getClass());
		}
		mongoTemplate.insert(objeto);	
	}
	
	protected void update(T objeto) {
		mongoTemplate.save(objeto);
	}
	
	protected void delete(T objeto) {
		mongoTemplate.remove(objeto);
	}
	
	protected List<T> getAll(Class<T> clazz) {
		return mongoTemplate.findAll(clazz);
	}
	
	protected T getById(Object id,Class<T> clazz) {
		return mongoTemplate.findById(id, clazz);
	}
	
	protected List<T> getByProperty(String propertyName,Class<T> clazz) {
		Query q = new Query();
		q.fields().include(propertyName);
		return mongoTemplate.find(q, clazz);
	}
}
