package org.safaproject.safa.commons.dao;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import org.safaproject.safa.commons.dao.criteria.EntityCriteriaBuilder;

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
	 * Performs a count operation on the query results
	 * 
	 * @return Long the number of hits that match the query
	 */
	Long countAll();

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

	/**
	 * Returns the concrete EntityCriteriaBuilder for each domain object
	 * 
	 * @return A builder that extends EntityCriteriaBuilder
	 */
	EntityCriteriaBuilder<T> getCriteriaBuilder();

}