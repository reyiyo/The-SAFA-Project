package org.safaproject.safa.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericHibernateDAO<T, ID extends Serializable>
		implements GenericDAO<T, ID> {

	private Class<T> persistentClass;
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public GenericHibernateDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public GenericHibernateDAO(Class<T> type) {
		this.persistentClass = type;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@SuppressWarnings("unchecked")
	public T findById(ID id, boolean lock) {
		T entity;
		if (lock)
			entity = (T) sessionFactory.getCurrentSession().load(getPersistentClass(), id,
					LockOptions.UPGRADE);
		else
			entity = (T) sessionFactory.getCurrentSession().load(getPersistentClass(), id);

		return entity;
	}

	public List<T> findAll() {
		return findByCriteria();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance).excludeZeroes()
				.enableLike()
				.ignoreCase();
		crit.add(example);
		return crit.list();
	}
	
	public T makePersistent(T entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		return entity;
	}

	public void makeTransient(T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	public void flush() {
		sessionFactory.getCurrentSession().flush();
	}

	public void clear() {
		sessionFactory.getCurrentSession().clear();
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}
	
	public Long count(){
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(getPersistentClass());
		crit.setProjection(Projections.rowCount());
		return (Long) crit.uniqueResult();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

}
