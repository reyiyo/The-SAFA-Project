package org.safaproject.safa.dao;

import javax.persistence.Query;

import org.apache.commons.lang.NotImplementedException;
import org.safaproject.safa.commons.dao.GenericJPADAO;
import org.safaproject.safa.commons.dao.criteria.EntityCriteriaBuilder;
import org.safaproject.safa.model.user.Role;
import org.safaproject.safa.model.user.Roles;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAO extends GenericJPADAO<Role, Long> {

	public Role getDefaultRole() {
		Query query = entityManager
				.createQuery("SELECT r FROM Role r WHERE r.name = :name");
		query.setParameter("name", Roles.USER.getValue());
		return (Role) query.getSingleResult();
	}

	@Override
	public EntityCriteriaBuilder<Role> getCriteriaBuilder() {
		throw new NotImplementedException();
	}
}
