package org.safaproject.safa.service.search;

import java.util.List;

import org.safaproject.safa.dao.ContentDAO;
import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.content.OrderDirections;
import org.safaproject.safa.model.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SearchStrategy {

	@Autowired
	protected ContentDAO contentDAO;

	public abstract List<Content> search(List<Tag> selectedTags,
			Integer firstResult, Integer maxResults, String orderBy,
			OrderDirections orderDirections);

	public void setContentDAO(ContentDAO contentDAO) {
		this.contentDAO = contentDAO;
	}
}
