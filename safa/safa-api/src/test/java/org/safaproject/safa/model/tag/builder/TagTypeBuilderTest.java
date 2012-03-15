package org.safaproject.safa.model.tag.builder;

import junit.framework.Assert;

import org.junit.Test;
import org.safaproject.safa.model.tag.TagDataTypes;
import org.safaproject.safa.model.tag.TagType;

public class TagTypeBuilderTest {
	
	@Test
	public void shallBuild() {
		TagType tagType = new TagTypeBuilder()
				.withTagDataType(TagDataTypes.STRING)
				.withTagName("tag")
				.build();
		
		Assert.assertEquals(TagDataTypes.STRING, tagType.getTagDataType());
		Assert.assertEquals("tag", tagType.getTagName());
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullTagDataType() {
		new TagTypeBuilder()
				.withTagName("tag")
				.build();
	}
}
