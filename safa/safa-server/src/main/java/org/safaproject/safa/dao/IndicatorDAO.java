package org.safaproject.safa.dao;

import org.apache.commons.lang.NotImplementedException;
import org.safaproject.safa.commons.dao.GenericJPADAO;
import org.safaproject.safa.commons.dao.criteria.EntityCriteriaBuilder;
import org.safaproject.safa.model.indicator.Indicator;
import org.springframework.stereotype.Repository;

@Repository
public class IndicatorDAO extends GenericJPADAO<Indicator, Long> {

	@Override
	public EntityCriteriaBuilder<Indicator> getCriteriaBuilder() {
		throw new NotImplementedException();
	}

}
