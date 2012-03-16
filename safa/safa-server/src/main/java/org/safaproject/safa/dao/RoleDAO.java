package org.safaproject.safa.dao;

import javax.persistence.Query;

import org.safaproject.safa.model.user.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAO extends GenericHibernateDAO<Role, Long> {

	public Role getDefaultRole() {
		Query query = entityManager
				.createQuery("SELECT r FROM Role r WHERE r.name = 'ROLE_USER'");
		return (Role) query.getSingleResult();
	}
}
