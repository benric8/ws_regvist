/**
 * 
 */
package pe.gob.pj.administrativos.visitas.service.impl;

import java.io.Serializable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.pj.administrativos.visitas.model.dao.BaseDao;
import pe.gob.pj.administrativos.visitas.service.BaseService;

/**
 * @author pjudicial
 *
 */
public abstract class BaseServiceImpl<PK extends Serializable, T> implements BaseService<PK, T> {
	private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

	@Autowired
	private BaseDao<PK, T> baseDao;

	@Transactional(readOnly = false, value = "transactionManager")
	@Override
	public T getByKey(PK key) {
		long time_start = 0, time_end = 0;
		if (logger.isDebugEnabled()) {
			time_start = System.currentTimeMillis();
		}
		T obj = this.baseDao.getByKey(key);
		if (logger.isDebugEnabled()) {
			time_end = System.currentTimeMillis();
			logger.debug("Tiempo de Ejecucion {} milliseconds", (time_end - time_start));
		}
		return obj;
	}

	@Transactional(readOnly = false, value = "transactionManager", rollbackFor = RuntimeException.class)
	@Override
	public void persist(T entity) {
		this.baseDao.persist(entity);
	}

	@Transactional(readOnly = false, value = "transactionManager", rollbackFor = RuntimeException.class)
	@Override
	public PK save(T entity) {
		return this.baseDao.save(entity);
	}

	@Transactional(readOnly = false, value = "transactionManager", rollbackFor = RuntimeException.class)
	@Override
	public void update(T entity) {
		this.baseDao.update(entity);
	}

	@Transactional(readOnly = false, value = "transactionManager", rollbackFor = RuntimeException.class)
	@Override
	public void delete(T entity) {
		this.baseDao.delete(entity);
	}

	@Transactional(readOnly = false, value = "transactionManager", rollbackFor = RuntimeException.class)
	@Override
	public void delete(PK key) {
		this.baseDao.delete(key);
	}

	@Transactional(readOnly = true, value = "transactionManager")
	@Override
	public List<T> listAll() {
		return this.baseDao.listAll();
	}

	@Transactional(readOnly = true, value = "transactionManager")
	@Override
	public List<T> listAll(String sortField) {
		return this.baseDao.listAll(sortField);
	}

}