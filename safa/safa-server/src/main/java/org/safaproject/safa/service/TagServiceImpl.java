package org.safaproject.safa.service;

import java.util.List;

import org.safaproject.safa.dao.TagDAO;
import org.safaproject.safa.dao.TagTypeDAO;
import org.safaproject.safa.model.tag.Tag;
import org.safaproject.safa.model.tag.TagType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class TagServiceImpl implements TagService {

	@Autowired
	private TagTypeDAO tagTypeDAO;

	@Autowired
	private TagDAO tagDAO;

	@Override
	@Transactional
	public List<TagType> getAllTagTypes() {
		return tagTypeDAO.findAll();
	}

	// FIXME: This should call the tagging module to filter according to the
	// previously selected tags. We're doing this now to test integration
	@Override
	public List<Tag> filterTags(TagType tagType, List<Tag> selectedTags,
			String value) {

		return tagDAO.getTagCriteriaBuilder().withTagType(tagType)
				.withValueLike(value).list();
	}

}
