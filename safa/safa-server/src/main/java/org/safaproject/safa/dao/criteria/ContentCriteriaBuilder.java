package org.safaproject.safa.dao.criteria;

import java.util.List;

import javax.persistence.EntityManager;

import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.content.OrderDirections;
import org.safaproject.safa.model.tag.Tag;

public class ContentCriteriaBuilder extends EntityCriteriaBuilder<Content> {

	public ContentCriteriaBuilder(EntityManager entityManager) {
		super(entityManager, Content.class);
	}

	public ContentCriteriaBuilder withTag(Tag tag) {
		this.isMember("tags", tag);
		return this;
	}

	public ContentCriteriaBuilder withTags(List<Tag> tags) {
		for (Tag t : tags) {
			this.isMember("tags", t);
		}
		return this;
	}

	public ContentCriteriaBuilder orderBy(OrderDirections direction,
			String... fields) {
		super.orderBy(direction, fields);
		return this;
	}

}
