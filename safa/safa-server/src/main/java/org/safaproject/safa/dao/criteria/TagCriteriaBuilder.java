package org.safaproject.safa.dao.criteria;

import javax.persistence.EntityManager;

import org.safaproject.safa.commons.dao.criteria.EntityCriteriaBuilder;
import org.safaproject.safa.model.tag.Tag;
import org.safaproject.safa.model.tag.TagType;

public class TagCriteriaBuilder extends EntityCriteriaBuilder<Tag> {

	public TagCriteriaBuilder(EntityManager entityManager) {
		super(entityManager, Tag.class);
	}

	private static final String TAG_TYPE = "tagType";
	private static final String VALUE = "value";

	public TagCriteriaBuilder withTagType(TagType tagType) {
		this.withValue(TAG_TYPE, tagType);
		return this;
	}

	public TagCriteriaBuilder withValueLike(String value) {
		this.like(VALUE, value);
		return this;
	}
}
