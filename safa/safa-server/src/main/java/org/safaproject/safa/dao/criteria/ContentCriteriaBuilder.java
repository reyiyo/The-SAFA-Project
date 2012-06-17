package org.safaproject.safa.dao.criteria;

import java.util.List;

import javax.persistence.EntityManager;

import org.safaproject.safa.commons.dao.criteria.EntityCriteriaBuilder;
import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.content.OrderDirections;
import org.safaproject.safa.model.tag.Tag;

public class ContentCriteriaBuilder extends EntityCriteriaBuilder<Content> {

	public ContentCriteriaBuilder(EntityManager entityManager) {
		super(entityManager, Content.class);
	}
	
	private static final String TAGS = "tags";

	public ContentCriteriaBuilder withTag(Tag tag) {
		this.isMember(TAGS, tag);
		return this;
	}

	public ContentCriteriaBuilder withTags(List<Tag> tags) {
		for (Tag t : tags) {
			this.isMember(TAGS, t);
		}
		return this;
	}

	public ContentCriteriaBuilder orderBy(OrderDirections direction,
			String... fields) {
		super.orderBy(direction, fields);
		return this;
	}

}
