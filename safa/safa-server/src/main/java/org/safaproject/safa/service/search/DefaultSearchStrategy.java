package org.safaproject.safa.service.search;

import java.util.List;

import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.content.OrderDirections;
import org.safaproject.safa.model.tag.Tag;

public class DefaultSearchStrategy extends SearchStrategy {

	@Override
	public List<Content> search(List<Tag> selectedTags, Integer firstResult,
			Integer maxResults, String orderBy, OrderDirections orderDirections) {
		return contentDAO.search(selectedTags, firstResult, maxResults);
	}

}
