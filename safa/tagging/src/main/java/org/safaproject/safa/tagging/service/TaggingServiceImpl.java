package org.safaproject.safa.tagging.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.safaproject.safa.common.function.TagToId;
import org.safaproject.safa.node.Tag;
import org.safaproject.safa.node.TagType;
import org.safaproject.safa.tagging.repository.TagRepository;
import org.safaproject.safa.tagging.repository.TagTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;

@Service
public class TaggingServiceImpl implements TaggingService {

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private TagTypeRepository tagTypeRepository;

	@Autowired
	private DatabasePopulator databasePopulator;

	@Override
	public Set<Tag> filterTags(TagType tagType, Set<Tag> selectedTags) {
		if (CollectionUtils.isEmpty(selectedTags)) {
			return Sets
					.newHashSet(tagRepository.filterTags(tagType.getNodeId()));
		} else {
			Set<Long> tagIds = new HashSet<Long>();
			tagIds.addAll(Collections2.transform(selectedTags, new TagToId()));

			return Sets.newHashSet(tagRepository.filterTags(tagIds,
					tagType.getName()));
		}

	}

	@Override
	public Set<TagType> getAllTagTypes() {
		return Sets.newHashSet(tagTypeRepository.findAll());
	}

	@Override
	public Set<Tag> populateDb() {
		return Sets.newHashSet(databasePopulator.populateDb());
	}

	public TagRepository getTagRepository() {
		return tagRepository;
	}

	public void setTagRepository(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	public TagTypeRepository getTagTypeRepository() {
		return tagTypeRepository;
	}

	public void setTagTypeRepository(TagTypeRepository tagTypeRepository) {
		this.tagTypeRepository = tagTypeRepository;
	}
}
