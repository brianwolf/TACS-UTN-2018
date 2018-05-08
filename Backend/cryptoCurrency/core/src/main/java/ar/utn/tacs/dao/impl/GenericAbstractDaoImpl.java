package ar.utn.tacs.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.dao.GenericDao;

public abstract class GenericAbstractDaoImpl<T> implements GenericDao{
	
	@Autowired
	protected SessionFactory sessionFactory;
}
