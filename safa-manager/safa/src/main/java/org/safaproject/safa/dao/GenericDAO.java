package org.safaproject.safa.dao;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Generic Repository, providing basic CRUD operations
 *
 * @param <T> the entity type
 * @param <ID> the primary key type
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
     * @param id the primary key
     * @return the entity
     */
    T findById(final ID id);

    /**
     * Load all entities
     *
     * @return the list of entities
     */
    List<T> findAll();

    /**
     * Find entities based on an example
     *
     * @param exampleInstance the example
     * @return the list of entities
     */
    List<T> findByExample(final T exampleInstance);

    /**
     * Find using a named query
     *
     * @param queryName the name of the query
     * @param params the query parameters
     *
     * @return the list of entities
     */
    List<T> findByNamedQuery(
        final String queryName,
        Object... params
    );

    /**
     * Find using a named query
     *
     * @param queryName the name of the query
     * @param params the query parameters
     *
     * @return the list of entities
     */
    List<T> findByNamedQueryAndNamedParams(
        final String queryName,
        final Map<String, ?extends Object> params
    );

    /**
     * Counts all entities
     *
     * @return the number of entities
     */
    Long countAll();

    /**
     * Count entities based on an example
     *
     * @param exampleInstance the search criteria
     * @return the number of entities
     */
    Long countByExample(final T exampleInstance);

    
    /**
     * Saves an entity. This can be either a INSERT or UPDATE in the database
     * 
     * @param entity the entity to save
     * 
     * @return the saved entity
     */
    T save(final T entity);

    /**
     * Deletes an entity from the database
     * 
     * @param entity the entity to delete
     */
    void delete(final T entity);
}