package org.safaproject.safa.dao;

import org.apache.commons.lang.NotImplementedException;
import org.safaproject.safa.commons.dao.GenericJPADAO;
import org.safaproject.safa.commons.dao.criteria.EntityCriteriaBuilder;
import org.safaproject.safa.model.indicator.IndicatorType;
import org.springframework.stereotype.Repository;

@Repository
public class IndicatorTypeDAO extends GenericJPADAO<IndicatorType, String> {

	@Override
	public EntityCriteriaBuilder<IndicatorType> getCriteriaBuilder() {
		throw new NotImplementedException();
	}

}
