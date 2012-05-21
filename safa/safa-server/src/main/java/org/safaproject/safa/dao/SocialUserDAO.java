package org.safaproject.safa.dao;

import org.safaproject.safa.dao.criteria.SocialUserCriteriaBuilder;
import org.safaproject.safa.model.user.SocialUser;
import org.springframework.stereotype.Repository;

@Repository("socialUserDAO")
public class SocialUserDAO extends GenericHibernateDAO<SocialUser, Long> {
	
	public SocialUserCriteriaBuilder getSocialUserCriteriaBuilder() {
		return new SocialUserCriteriaBuilder(entityManager);
	}

}
