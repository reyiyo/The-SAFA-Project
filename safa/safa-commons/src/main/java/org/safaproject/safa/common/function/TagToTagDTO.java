package org.safaproject.safa.common.function;

import org.safaproject.safa.node.Tag;
import org.safaproject.safa.node.dto.TagDTO;

import com.google.common.base.Function;

public class TagToTagDTO implements Function<Tag, TagDTO> {

	@Override
	public TagDTO apply(Tag tag) {
		return new TagDTO(tag);
	}

}
