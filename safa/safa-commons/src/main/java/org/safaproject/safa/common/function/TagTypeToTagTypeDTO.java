package org.safaproject.safa.common.function;

import org.safaproject.safa.node.TagType;
import org.safaproject.safa.node.dto.TagTypeDTO;

import com.google.common.base.Function;

public class TagTypeToTagTypeDTO implements Function<TagType, TagTypeDTO> {

	@Override
	public TagTypeDTO apply(TagType tagType) {
		return new TagTypeDTO(tagType);
	}

}
