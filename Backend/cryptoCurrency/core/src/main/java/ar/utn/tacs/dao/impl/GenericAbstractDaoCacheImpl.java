package ar.utn.tacs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.utn.tacs.dao.GenericDao;

public abstract class GenericAbstractDaoCacheImpl<T extends GenericDao> extends GenericAbstractDaoImpl {

	protected Map<String, List<Object>> mapCache = new HashMap<String, List<Object>>();

	protected T genericDao;

	@SuppressWarnings("unchecked")
	protected GenericAbstractDaoCacheImpl(GenericDao genericDao) {
		this.genericDao = (T) genericDao;
	}

	protected void putDBCache(String keyDBName) {
		this.mapCache.put(keyDBName, new ArrayList<Object>());
	}

	protected void addDataInCache(String keyDBName, Object object) {
		this.mapCache.get(keyDBName).add(object);
	}

	@SuppressWarnings("unchecked")
	protected <H> List<H> getListCache(String keyDBName, Class<H> clazz) {
		return (List<H>) this.mapCache.get(keyDBName);
	}
	
	protected void executeAsynchronously(Runnable runnable) {
		Thread thread = new Thread(runnable);
		thread.start();
	}
}
