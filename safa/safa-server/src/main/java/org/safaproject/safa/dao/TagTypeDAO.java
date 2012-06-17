package org.safaproject.safa.dao;

import org.apache.commons.lang.NotImplementedException;
import org.safaproject.safa.commons.dao.GenericJPADAO;
import org.safaproject.safa.commons.dao.criteria.EntityCriteriaBuilder;
import org.safaproject.safa.model.tag.TagType;
import org.springframework.stereotype.Repository;

@Repository
public class TagTypeDAO extends GenericJPADAO<TagType, String> {

	@Override
	public EntityCriteriaBuilder<TagType> getCriteriaBuilder() {
		throw new NotImplementedException();
	}

}
