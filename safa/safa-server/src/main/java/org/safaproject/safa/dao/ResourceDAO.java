package org.safaproject.safa.dao;

import org.apache.commons.lang.NotImplementedException;
import org.safaproject.safa.commons.dao.GenericJPADAO;
import org.safaproject.safa.commons.dao.criteria.EntityCriteriaBuilder;
import org.safaproject.safa.model.content.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceDAO extends GenericJPADAO<Resource, Long> {

	@Override
	public EntityCriteriaBuilder<Resource> getCriteriaBuilder() {
		throw new NotImplementedException();
	}

}
