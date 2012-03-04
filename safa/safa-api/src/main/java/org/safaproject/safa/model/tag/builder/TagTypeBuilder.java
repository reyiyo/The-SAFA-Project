package org.safaproject.safa.model.tag.builder;

import org.safaproject.safa.model.tag.TagDataTypes;
import org.safaproject.safa.model.tag.TagType;

public class TagTypeBuilder {

	private String tagName;

	private TagDataTypes tagDataType;

	public TagTypeBuilder withTagName(String tagName) {
		this.tagName = tagName;
		return this;
	}

	public TagTypeBuilder withTagDataType(TagDataTypes tagDataType) {
		this.tagDataType = tagDataType;
		return this;
	}

	public TagType build() {
		return new TagType(tagName, tagDataType);
	}

}
