package org.safaproject.safa.service;

import java.util.List;

import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.content.OrderDirections;
import org.safaproject.safa.model.tag.Tag;
import org.safaproject.safa.service.search.SearchStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ContentServiceImpl implements ContentService {

	@Autowired
	private SearchStrategyFactory searchStrategyFactory;

	@Override
	public List<Content> search(List<Tag> selectedTags, Integer firstResult,
			Integer maxResults, String orderBy, OrderDirections orderDirections) {

		return searchStrategyFactory.getStrategyFor(orderBy)
				.search(selectedTags, firstResult, maxResults, orderBy,
						orderDirections);

	}

}
