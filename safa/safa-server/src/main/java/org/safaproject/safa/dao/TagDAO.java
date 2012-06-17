package org.safaproject.safa.dao;

import org.safaproject.safa.commons.dao.GenericJPADAO;
import org.safaproject.safa.dao.criteria.TagCriteriaBuilder;
import org.safaproject.safa.model.tag.Tag;
import org.springframework.stereotype.Repository;

@Repository
public class TagDAO extends GenericJPADAO<Tag, Long> {

	public TagCriteriaBuilder getCriteriaBuilder() {
		return new TagCriteriaBuilder(entityManager);
	}

}
