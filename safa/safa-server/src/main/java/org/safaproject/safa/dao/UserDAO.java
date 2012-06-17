package org.safaproject.safa.dao;

import javax.persistence.Query;

import org.apache.commons.lang.NotImplementedException;
import org.safaproject.safa.commons.dao.GenericJPADAO;
import org.safaproject.safa.commons.dao.criteria.EntityCriteriaBuilder;
import org.safaproject.safa.model.user.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends GenericJPADAO<User, Long> {

	public User findByUsername(String username) {
		Query query = entityManager
				.createQuery("SELECT u FROM User u WHERE u.username= :username");
		query.setParameter("username", username);
		return (User) query.getSingleResult();
	}

	@Override
	public EntityCriteriaBuilder<User> getCriteriaBuilder() {
		throw new NotImplementedException();
	}

}
