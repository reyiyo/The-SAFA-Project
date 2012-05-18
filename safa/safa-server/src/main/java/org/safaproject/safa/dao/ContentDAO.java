package org.safaproject.safa.dao;

import java.util.List;

import org.safaproject.safa.dao.criteria.ContentCriteriaBuilder;
import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.tag.Tag;
import org.springframework.stereotype.Repository;

@Repository
public class ContentDAO extends GenericHibernateDAO<Content, Long> {

	@SuppressWarnings("unchecked")
	public List<Content> search(List<Tag> tags, Integer firstResult,
			Integer maxResults) {
		return entityManager
				.createQuery(
						"Select c from Content c left join c.indicators i left join c.tags t "
								+ "where t in (:_tags)"
								+ " group by c order by avg(i.value) DESC")
				.setParameter("_tags", tags).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();
	}
	
	public ContentCriteriaBuilder getContentCriteriaBuilder() {
		return new ContentCriteriaBuilder(entityManager);
	}
}
