package org.safaproject.safa.dao;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import com.googlecode.genericdao.search.ISearch;

/**
 * Generic Repository, providing basic CRUD operations
 * 
 * @param <T>
 *            the entity type
 * @param <ID>
 *            the primary key type
 */
public interface GenericDAO<T, ID extends Serializable> {

	/**
	 * Get the Class of the entity
	 * 
	 * @return the class
	 */
	Class<T> getEntityClass();

	/**
	 * Find an entity by its primary key
	 * 
	 * @param id
	 *            the primary key
	 * @return the entity
	 */
	T findById(ID id);

	/**
	 * Load all entities
	 * 
	 * @return the list of entities
	 */
	List<T> findAll();

	/**
	 * Find entities based on an example
	 * 
	 * 
	 * NOTE: findByExample does NOT take into account relationships. It only
	 * looks for basic data fields. For example, this will NOT work as expected:
	 * example.setTagType(tagTypeDAO.findById("Teacher"));
	 * 
	 * @param exampleInstance
	 *            the example
	 * @return the list of entities
	 */
	List<T> findByExample(T exampleInstance);

	/**
	 * Find using a named query
	 * 
	 * @param queryName
	 *            the name of the query
	 * @param params
	 *            the query parameters
	 * 
	 * @return the list of entities
	 */
	List<T> findByNamedQuery(String queryName, Object... params);

	/**
	 * Find using a named query
	 * 
	 * @param queryName
	 *            the name of the query
	 * @param params
	 *            the query parameters
	 * 
	 * @return the list of entities
	 */
	List<T> findByNamedQueryAndNamedParams(String queryName,
			Map<String, ? extends Object> params);

	/**
	 * Counts all entities
	 * 
	 * @return the number of entities
	 */
	Long countAll();

	/**
	 * Count entities based on an example
	 * 
	 * NOTE: countByExample does NOT take into account relationships. It only
	 * looks for basic data fields. For example, this will NOT work as expected:
	 * example.setTagType(tagTypeDAO.findById("Teacher"));
	 * 
	 * @param exampleInstance
	 *            the search criteria
	 * @return the number of entities
	 */
	Long countByExample(T exampleInstance);

	/**
	 * Saves a NEW entity.
	 * 
	 * @param entity
	 *            the entity to save
	 * 
	 * @return the saved entity
	 */
	void save(T entity);

	/**
	 * Deletes an entity from the database
	 * 
	 * @param entity
	 *            the entity to delete
	 */
	void delete(T entity);

	/**
	 * Updates an entity from the database which doesn't need to be attached.
	 * This can be either a INSERT or UPDATE in the database
	 * 
	 * @param entity
	 *            the entity to delete
	 */
	T update(T entity);
	
	List<T> search(ISearch search);
}