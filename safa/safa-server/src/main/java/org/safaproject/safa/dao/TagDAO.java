package org.safaproject.safa.dao;

import org.safaproject.safa.dao.criteria.TagCriteriaBuilder;
import org.safaproject.safa.model.tag.Tag;
import org.springframework.stereotype.Repository;

@Repository
public class TagDAO extends GenericHibernateDAO<Tag, Long> {

	public TagCriteriaBuilder getTagCriteriaBuilder() {
		return new TagCriteriaBuilder(entityManager);
	}

}
