package org.safaproject.safa.dao;

import javax.persistence.Query;

import org.safaproject.safa.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends GenericHibernateDAO<User, Long> {

	public User findByUsername(String username) {
		Query query = entityManager
				.createQuery("SELECT u FROM User u WHERE u.username= :username");
		query.setParameter("username", username);
		return (User) query.getSingleResult();
	}
}
