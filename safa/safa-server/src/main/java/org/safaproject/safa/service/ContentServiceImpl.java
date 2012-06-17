package org.safaproject.safa.service;

import java.util.List;

import org.safaproject.safa.dao.ContentDAO;
import org.safaproject.safa.exception.ContentNotFoundException;
import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.content.OrderDirections;
import org.safaproject.safa.model.tag.Tag;
import org.safaproject.safa.service.search.SearchStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private SearchStrategyFactory searchStrategyFactory;
	
	@Autowired
	private ContentDAO contentDAO;

	@Override
	public List<Content> search(List<Tag> selectedTags, Integer firstResult,
			Integer maxResults, String orderBy, OrderDirections orderDirections) {

		return searchStrategyFactory.getStrategyFor(orderBy)
				.search(selectedTags, firstResult, maxResults, orderBy,
						orderDirections);

	}

	@Override
	public Content get(final long contentId) throws ContentNotFoundException {

		final Content content = contentDAO.findById(contentId);
		if(content==null)
			throw new ContentNotFoundException();
		return content;

	}
	
	public void setContentDAO(final ContentDAO contentDAO) {
		this.contentDAO = contentDAO;
	}

}
