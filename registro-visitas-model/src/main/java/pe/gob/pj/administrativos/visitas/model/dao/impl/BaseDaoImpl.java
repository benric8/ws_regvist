package pe.gob.pj.administrativos.visitas.model.dao.impl;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import pe.gob.pj.administrativos.visitas.model.dao.BaseDao;

public abstract class BaseDaoImpl<PK extends Serializable, T> implements BaseDao<PK, T> {
	private final static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);
	protected Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	@Override
	public void persist(T entity) {
		getSession().persist(entity);
		logger.info("Entidad {}", entity.toString());
	}

	@Override
	public void flush() {
		getSession().flush();
		logger.info("Sincronizando las entidades con la base de datos");
	}

	@SuppressWarnings("unchecked")
	@Override
	public PK save(T entity) {
		PK pk = (PK) getSession().save(entity);
		logger.info("Entidad {}", entity.toString());
		return pk;
	}

	@Override
	public void refresh(T entity) {
		getSession().refresh(entity);
		logger.info("Entidad {}", entity.toString());
	}
	
	@Override
	public void update(T entity) {
		getSession().update(entity);
		logger.info("Entidad {}", entity.toString());
	}

	@Override
	public void delete(T entity) {
		getSession().delete(entity);
		logger.info("Entidad {}", entity.toString());
	}

	@Override
	public void delete(PK key) {
		T entity = getByKey(key);
		getSession().delete(entity);
		logger.info("Entidad {}", entity.toString());
	}

	/**
	 * Crea una consulta "select object(o) from Entity as o "
	 * 
	 * @return
	 */
	protected String createBasicQuery() {
		String query = "select object(o) from ".concat(persistentClass.getName()).concat(" as o ");
		return query;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listAll() {
		String query = createBasicQuery();
		Query q = getSession().createQuery(query);
		return (List<T>) q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listAll(String sortField) {
		String query = createBasicQuery();
		query = query.concat(" order by ").concat(sortField).concat(" asc");
		Query q = getSession().createQuery(query);
		return (List<T>) q.list();
	}

}