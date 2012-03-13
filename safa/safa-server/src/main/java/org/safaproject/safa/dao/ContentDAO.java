package org.safaproject.safa.dao;

import java.util.List;

import javax.persistence.Query;

import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.tag.Tag;
import org.springframework.stereotype.Repository;

@Repository
public class ContentDAO extends GenericHibernateDAO<Content, Long> {
	
	@SuppressWarnings("unchecked")
	public List<Content> search(List<Tag> tags) {
		Query query = entityManager.createQuery("Select c from Content c left join c.indicators i where :p1 = some elements(c.tags) group by c order by avg(i.value) DESC");
		
		query.setParameter("p1", tags);
		return query.getResultList();
	}
}
