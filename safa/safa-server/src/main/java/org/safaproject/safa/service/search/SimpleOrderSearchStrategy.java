package org.safaproject.safa.service.search;

import java.util.List;

import org.safaproject.safa.dao.TagDAO;
import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.content.OrderDirections;
import org.safaproject.safa.model.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.Sort;

public class SimpleOrderSearchStrategy extends SearchStrategy {

	@Autowired
	private TagDAO tagDAO;

	@Override
	public List<Content> search(List<Tag> selectedTags, Integer firstResult,
			Integer maxResults, String orderBy, OrderDirections orderDirections) {

		Search s = new Search(Content.class);

		for (Tag tag : selectedTags) {
			Tag attachedTag = tagDAO.findById(tag.getTagId());
			s.addFilterSome("tags",
					Filter.equal(Filter.ROOT_ENTITY, attachedTag));
		}
		s.addSort(new Sort(orderBy, orderDirections.getOrder()))
				.setFirstResult(firstResult).setMaxResults(maxResults);

		return contentDAO.search(s);
	}

	public void setTagDAO(TagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}

}
