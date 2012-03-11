package org.safaproject.safa.service;

import java.util.List;

import org.safaproject.safa.dao.ContentDAO;
import org.safaproject.safa.dao.TagDAO;
import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;

public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentDAO contentDAO;

	@Autowired
	private TagDAO tagDAO;

	public List<Content> search(final List<Tag> selectedTags,
			final Integer firstResult, final Integer maxResults) {

		Search s = new Search(Content.class);

		for (Tag tag : selectedTags) {
			Tag attachedTag = tagDAO.findById(tag.getTagId());
			s.addFilterSome("tags",
					Filter.equal(Filter.ROOT_ENTITY, attachedTag));
		}

		s.setFirstResult(firstResult);
		s.setMaxResults(maxResults);
		return contentDAO.search(s);
	}

}
