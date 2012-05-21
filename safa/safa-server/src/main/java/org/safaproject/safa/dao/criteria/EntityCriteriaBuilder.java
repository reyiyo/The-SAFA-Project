package org.safaproject.safa.dao.criteria;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.safaproject.safa.model.content.OrderDirections;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

public class EntityCriteriaBuilder<Entity> {

	final protected CriteriaBuilder criteriaBuilder;
	@SuppressWarnings("rawtypes")
	final protected CriteriaQuery query;
	final protected Root<Entity> root;
	protected Predicate whereClause;
	private int maxRows;
	protected EntityManager entityManager;
	protected Class<Entity> domainClass;

	@SuppressWarnings("unchecked")
	protected EntityCriteriaBuilder(EntityManager entityManager,
			Class<Entity> domainClass) {
		this.entityManager = entityManager;
		this.domainClass = domainClass;
		criteriaBuilder = entityManager.getCriteriaBuilder();
		query = criteriaBuilder.createQuery();
		whereClause = criteriaBuilder.equal(criteriaBuilder.literal(1), 1);
		root = query.from(domainClass);
	}

	protected void withValue(String name, Object value) {
		whereClause = criteriaBuilder.and(whereClause,
				criteriaBuilder.equal(root.get(name), value));
	}

	protected void lessThanOptionalValue(String name, Date value) {
		if (value != null) {
			Expression<Date> expression = root.get(name);
			whereClause = criteriaBuilder.and(whereClause,
					criteriaBuilder.lessThan(expression, value));
		}
	}

	protected void joinValue(String joinName, String name, Object value) {
		whereClause = criteriaBuilder.and(whereClause,
				criteriaBuilder.equal(root.join(joinName).get(name), value));
	}

	/**
	 * Adds a where criterion to query to filter the elements that has the field
	 * <code>name</code> in <code>NULL</code>
	 * 
	 * @param name
	 *            String, the name of the field to evaluate
	 */
	protected void isNullValue(String name) {
		whereClause = criteriaBuilder.and(whereClause,
				criteriaBuilder.isNull(root.get(name)));
	}

	/**
	 * Adds a where criterion to query to filter the elements where the
	 * <code>name</code> field is NOT null
	 * 
	 * @param name
	 *            String, the name of the field to evaluate
	 */
	protected void isNotNullValue(String name) {
		whereClause = criteriaBuilder.and(whereClause,
				criteriaBuilder.isNotNull(root.get(name)));
	}

	protected void in(String name, List<?> value) {
		Path<Long> path = root.get(name);
		Predicate in = path.in(value);
		whereClause = criteriaBuilder.and(whereClause, in);
	}

	protected void isMember(String field, Object value) {
		Expression<Collection<Object>> list = root.get(field);
		whereClause = criteriaBuilder.and(whereClause,
				criteriaBuilder.isMember(criteriaBuilder.literal(value), list));
	}

	protected void like(String field, String value) {
		Expression<String> fieldValue = root.get(field);
		whereClause = criteriaBuilder.and(whereClause,
				criteriaBuilder.like(fieldValue, "%" + value + "%"));
	}

	@SuppressWarnings("unchecked")
	protected EntityCriteriaBuilder<Entity> orderBy(OrderDirections direction,
			String... fields) {
		Preconditions.checkNotNull(fields, "Order list cannot be null");
		Preconditions.checkNotNull(direction, "Order direction cannot be null");
		List<Order> orders = Lists.newArrayList();
		for (String field : fields) {
			if (direction.equals(OrderDirections.ASC)) {
				orders.add(criteriaBuilder.asc(root.get(field)));
			} else {
				orders.add(criteriaBuilder.desc(root.get(field)));
			}
		}
		query.orderBy(orders);
		return this;
	}

	/**
	 * Gets a query of type <code>CriteriaQuery<<Entity>></code> to use for
	 * listing results
	 * 
	 * @return <code>CriteriaQuery<<Entity>></code> for listing results
	 */
	@SuppressWarnings("unchecked")
	public CriteriaQuery<Entity> getQuery() {
		query.select(root);
		query.where(whereClause);
		return query;
	}

	/**
	 * Gets a query of type <code>CriteriaQuery<<Long>></code> to use for
	 * counting results
	 * 
	 * @return <code>CriteriaQuery<<Long>></code> for counting results
	 */
	@SuppressWarnings("unchecked")
	public CriteriaQuery<Long> getQueryForCount() {
		query.select(criteriaBuilder.count(root));
		query.where(whereClause);
		return query;
	}

	/**
	 * Gets a query of type <code>CriteriaQuery<<Long>></code> to use for
	 * selecting the Max value of @param field
	 * 
	 * @return <code>CriteriaQuery<<Long>></code> for counting results
	 */
	@SuppressWarnings("unchecked")
	public CriteriaQuery<Long> getQueryForMax(String field) {
		Path<Number> path = root.get(field);
		Expression<Number> maxExpression = criteriaBuilder.max(path);
		query.select(criteriaBuilder.coalesce(maxExpression, 0));
		query.where(whereClause);
		return query;
	}

	public List<Entity> list() {
		return this.list(0, maxRows);
	}

	public List<Entity> list(int first, int maxRows) {
		TypedQuery<Entity> q = this.entityManager.createQuery(this.getQuery());
		q.setFirstResult(first);
		q.setMaxResults(maxRows);
		return q.getResultList();

	}

	public Entity getSingleResult() {
		TypedQuery<Entity> q = this.entityManager.createQuery(this.getQuery());
		return q.getSingleResult();
	}

	protected Long selectMax(String field) {
		TypedQuery<Long> q = this.entityManager.createQuery(this
				.getQueryForMax(field));
		return q.getSingleResult();
	}

	/**
	 * Performs a count operation on the query results
	 * 
	 * @return Long the number of hits that match the query
	 */
	public Long count() {
		TypedQuery<Long> q = this.entityManager.createQuery(this
				.getQueryForCount());
		return q.getSingleResult();
	}

	public int getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

}