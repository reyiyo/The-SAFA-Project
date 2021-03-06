package org.safaproject.safa.service;

import java.util.List;

import org.safaproject.safa.model.tag.Tag;
import org.safaproject.safa.model.tag.TagType;

public interface TagService {

	List<TagType> getAllTagTypes();

	List<Tag> filterTags(TagType tagType, List<Tag> selectedTags, String value);

}
