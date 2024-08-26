package pe.gob.pj.administrativos.visitas.model.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<PK extends Serializable, T> {

	/**
	 * 
	 * @param key
	 * @return
	 */
	T getByKey(PK key);

	/**
	 * 
	 * @param entity
	 */
	void persist(T entity);

	/**
	 * 
	 */
	void flush();

	/**
	 * Guarda un objeto y devuelve el ID autogenerado
	 * 
	 * @param entity
	 * @return
	 */
	PK save(T entity);

	/**
	 * 
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 
	 * @param entity
	 */
	void delete(T entity);

	/**
	 * 
	 * @param key
	 */
	void delete(PK key);

	/**
	 * 
	 * @return
	 */
	List<T> listAll();

	/**
	 * @param sortField
	 * @return
	 */
	List<T> listAll(String sortField);

	/**
	 * @param entity
	 */
	void refresh(T entity);
}
