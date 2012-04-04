package org.safaproject.safa.service;

import org.safaproject.safa.dao.ContentDAO;
import org.safaproject.safa.exception.ContentNotFoundException;
import org.safaproject.safa.model.content.Content;
import org.springframework.beans.factory.annotation.Autowired;

public class GetContentServiceImpl implements GetContentService{

	@Autowired
	private ContentDAO contentDAO;
	
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
