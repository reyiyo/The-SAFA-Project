package org.safaproject.safa.dao;

import org.apache.commons.lang.NotImplementedException;
import org.safaproject.safa.commons.dao.GenericJPADAO;
import org.safaproject.safa.commons.dao.criteria.EntityCriteriaBuilder;
import org.safaproject.safa.model.content.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAO extends GenericJPADAO<Comment, Long> {

	@Override
	public EntityCriteriaBuilder<Comment> getCriteriaBuilder() {
		throw new NotImplementedException();
	}

}
