package org.safaproject.safa.common.function;

import org.safaproject.safa.node.Tag;
import org.safaproject.safa.node.dto.TagDTO;

import com.google.common.base.Function;

public class TagDTOToTag implements Function<TagDTO, Tag> {

	@Override
	public Tag apply(TagDTO tagDto) {
		return new Tag(tagDto);
	}

}
