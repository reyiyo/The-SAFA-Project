package org.safaproject.safa.service;

import java.util.List;

import org.safaproject.safa.dao.TagTypeDAO;
import org.safaproject.safa.model.tag.TagType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class TagServiceImpl implements TagService {

	@Autowired
	private TagTypeDAO tagTypeDAO;

	@Override
	@Transactional
	public List<TagType> getAllTagTypes() {
		return tagTypeDAO.findAll();
	}

}
