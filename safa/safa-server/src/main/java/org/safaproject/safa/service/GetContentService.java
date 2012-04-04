package org.safaproject.safa.service;

import org.safaproject.safa.exception.ContentNotFoundException;
import org.safaproject.safa.model.content.Content;

public interface GetContentService {
	
	public Content get(final long contentId) throws ContentNotFoundException;

}
