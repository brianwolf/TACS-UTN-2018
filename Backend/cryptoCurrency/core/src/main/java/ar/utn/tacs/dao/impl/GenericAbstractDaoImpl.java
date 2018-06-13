package ar.utn.tacs.dao.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import ar.utn.tacs.dao.GenericDao;

public abstract class GenericAbstractDaoImpl implements GenericDao {

	@Autowired
	protected MongoTemplate mongoTemplate;

	protected <T> void insert(T objeto) {
		if (!mongoTemplate.collectionExists(objeto.getClass())) {
			mongoTemplate.createCollection(objeto.getClass());
		}
		mongoTemplate.insert(objeto);
	}

	protected <T> void update(T objeto) {
		mongoTemplate.save(objeto);
	}

	protected <T> void delete(T objeto) {
		mongoTemplate.remove(objeto);
	}

	protected <A> void deleteByProperty(String propertyName, Object propertyValue, Class<A> clazz) {

		Query q = new Query();
		q.addCriteria(Criteria.where(propertyName).is(propertyValue));
		mongoTemplate.remove(q, clazz);

	}

	protected <T> List<T> getAll(Class<T> clazz) {
		return mongoTemplate.findAll(clazz);
	}

	protected <A> A getById(Object id, Class<A> clazz) {
		return mongoTemplate.findById(id, clazz);
	}

	protected <A> List<A> getAllByProperty(String propertyName, Object propertyValue, Class<A> clazz) {
		Query q = new Query();
		q.addCriteria(Criteria.where(propertyName).is(propertyValue));
		return mongoTemplate.find(q, clazz);
	}

	protected <A> List<A> getAllByProperties(Map<String, Object> propertiesAndValues, Class<A> clazz) {
		Query q = new Query();

		for (String key : propertiesAndValues.keySet()) {
			q.addCriteria(Criteria.where(key).is(propertiesAndValues.get(key)));
		}

		return mongoTemplate.find(q, clazz);
	}

	protected <T> T getByProperty(String propertyName, Object propertyValue, Class<T> clazz, String collectionName) {
		Query q = new Query();
		q.addCriteria(Criteria.where(propertyName).is(propertyValue));
		return mongoTemplate.findOne(q, clazz, collectionName);
	}

	protected <A> A getByProperty(String propertyName, Object propertyValue, Class<A> clazz) {
		Query q = new Query();
		q.addCriteria(Criteria.where(propertyName).is(propertyValue));
		return mongoTemplate.findOne(q, clazz);
	}

	protected <T> T getByProperties(Map<String, Object> propertiesAndValues, Class<T> clazz) {
		Query q = new Query();
		for (String key : propertiesAndValues.keySet()) {
			q.addCriteria(Criteria.where(key).is(propertiesAndValues.get(key)));
		}
		return mongoTemplate.findOne(q, clazz);
	}

	protected <A, T> List<A> getPropertyAll(String propertyName, Class<A> propertyClazz, Class<T> clazz) {
		Query q = new Query();
		q.fields().include(propertyName);
		List<T> list = mongoTemplate.find(q, clazz);
		return list.stream().map(o -> getPropertyByReflection(propertyName, o, propertyClazz)).collect(Collectors.toList());

	}

	@SuppressWarnings("unchecked")
	private <A> A getPropertyByReflection(String propertyName, Object o, Class<A> propertyClazz) {

		Object finalObject = o;

		for (String actualProperty : propertyName.split(Pattern.quote("."))) {
			try {
				Field f = finalObject.getClass().getDeclaredField(actualProperty);
				f.setAccessible(true);
				finalObject = f.get(finalObject);
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (A) finalObject;
	}

}
