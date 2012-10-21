package org.safaproject.safa.common.function;

import org.safaproject.safa.node.Tag;

import com.google.common.base.Function;

public class TagToId implements Function<Tag, Long> {

	@Override
	public Long apply(Tag tag) {
		return tag.getNodeId();
	}

}