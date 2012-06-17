package org.safaproject.safa.commons.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.safaproject.safa.commons.dao.criteria.EntityCriteriaBuilder;
import org.springframework.beans.factory.annotation.Required;

/**
 * JPA implementation of the GenericDAO.
 * 
 * @param <T>
 *            The persistent type
 * @param <ID>
 *            The primary key type
 */
public abstract class GenericJPADAO<T, ID extends Serializable> implements
		GenericDAO<T, ID> {

	private final Class<T> persistentClass;

	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public GenericJPADAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public GenericJPADAO(final Class<T> persistentClass) {
		super();
		this.persistentClass = persistentClass;
	}

	/**
	 * @see org.safaproject.safa.commons.dao.GenericDAO#countAll
	 */
	public Long countAll() {
		EntityCriteriaBuilder<T> criteriaBuilder = new EntityCriteriaBuilder<T>(
				entityManager, persistentClass);
		return criteriaBuilder.count();
	}

	/**
	 * @see org.safaproject.safa.commons.dao.GenericDAO#findAll()
	 */
	public List<T> findAll() {
		EntityCriteriaBuilder<T> criteriaBuilder = new EntityCriteriaBuilder<T>(
				entityManager, persistentClass);
		return criteriaBuilder.list();
	}

	/**
	 * @see org.safaproject.safa.commons.dao.GenericDAO#findById(java.io.Serializable)
	 */
	public T findById(final ID id) {
		final T result = getEntityManager().find(persistentClass, id);
		return result;
	}

	/**
	 * @see org.safaproject.safa.commons.dao.GenericDAO#findByNamedQuery(java.lang.String,
	 *      java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(final String name, Object... params) {
		Query query = getEntityManager().createNamedQuery(name);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}

		final List<T> result = (List<T>) query.getResultList();
		return result;
	}

	/**
	 * @see org.safaproject.safa.commons.dao.GenericDAO#findByNamedQueryAndNamedParams(java.lang.String,
	 *      java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQueryAndNamedParams(final String name,
			final Map<String, ? extends Object> params) {
		Query query = getEntityManager().createNamedQuery(name);

		for (final Map.Entry<String, ? extends Object> param : params
				.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}

		final List<T> result = (List<T>) query.getResultList();
		return result;
	}

	/**
	 * @see org.safaproject.safa.commons.dao.GenericDAO#getEntityClass()
	 */
	public Class<T> getEntityClass() {
		return persistentClass;
	}

	/**
	 * set the JPA entity manager to use.
	 * 
	 * @param entityManager
	 */
	@Required
	@PersistenceContext
	public void setEntityManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @see org.safaproject.safa.commons.dao.GenericDAO#delete(java.lang.Object)
	 */
	public void delete(T entity) {
		getEntityManager().remove(entity);

	}

	/**
	 * @see org.safaproject.safa.commons.dao.GenericDAO#save(java.lang.Object)
	 */
	public T update(T entity) {
		final T savedEntity = getEntityManager().merge(entity);
		return savedEntity;
	}

	/**
	 * @see org.safaproject.safa.commons.dao.GenericDAO#persist(java.lang.Object)
	 */
	public void save(T entity) {
		getEntityManager().persist(entity);
		getEntityManager().flush();
	}
	
	public abstract EntityCriteriaBuilder<T> getCriteriaBuilder();
}