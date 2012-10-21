package org.safaproject.safa.tagging.service;

import java.util.Set;

import org.safaproject.safa.node.Tag;
import org.safaproject.safa.node.TagType;

public interface TaggingService {

	Set<Tag> filterTags(TagType tagType, Set<Tag> selectedTags);

	Set<TagType> getAllTagTypes();
	
	Set<Tag> populateDb();

}