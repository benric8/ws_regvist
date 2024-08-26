/**
 * 
 */
package pe.gob.pj.administrativos.visitas.service;

import java.io.Serializable;
import java.util.List;

/**
 * @author pjudicial
 *
 */
public abstract interface BaseService<PK extends Serializable, T> {

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
	 * Lista todos los registros de una tabla ordenados por sortField
	 * ascendentemente.
	 * 
	 * @param sortField
	 * @return
	 */
	public abstract List<T> listAll(String sortField);

}