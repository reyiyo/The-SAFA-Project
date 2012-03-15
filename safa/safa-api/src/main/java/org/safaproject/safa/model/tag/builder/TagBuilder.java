package org.safaproject.safa.model.tag.builder;

import org.safaproject.safa.model.tag.Tag;
import org.safaproject.safa.model.tag.TagType;

import com.google.common.base.Preconditions;

public class TagBuilder {

	private TagType tagType;

	private String value;

	private String iconURL;

	public TagBuilder withTagType(TagType tagType) {
		this.tagType = tagType;
		return this;
	}

	public TagBuilder withValue(String value) {
		this.value = value;
		return this;
	}

	public TagBuilder withIconURL(String iconURL) {
		this.iconURL = iconURL;
		return this;
	}

	public Tag build() {
		Preconditions.checkNotNull(tagType);
		Preconditions.checkNotNull(value);
		
		return new Tag(tagType, value, iconURL);
	}

}
