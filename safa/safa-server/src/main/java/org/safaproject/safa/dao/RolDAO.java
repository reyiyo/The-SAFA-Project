package org.safaproject.safa.dao;

import javax.persistence.Query;

import org.safaproject.safa.model.Rol;
import org.springframework.stereotype.Repository;

@Repository
public class RolDAO extends GenericHibernateDAO<Rol, Long> {

	public Rol getDefaultRol() {
		Query query = entityManager
				.createQuery("SELECT r FROM Rol r WHERE r.name = 'ROLE_USER'");
		return (Rol) query.getSingleResult();
	}
}
