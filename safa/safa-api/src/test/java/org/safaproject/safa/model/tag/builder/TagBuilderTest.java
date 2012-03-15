package org.safaproject.safa.model.tag.builder;

import junit.framework.Assert;

import org.junit.Test;
import org.safaproject.safa.model.tag.Tag;
import org.safaproject.safa.model.tag.TagType;

public class TagBuilderTest {

	@Test
	public void shallBuild() {
		TagType tagType = new TagType();
		
		Tag tag = new TagBuilder()
				.withIconURL("http://tuvieja.com/tuvieja.jpg")
				.withTagType(tagType)
				.withValue("value")
				.build();
		
		Assert.assertEquals("http://tuvieja.com/tuvieja.jpg", tag.getIconURL());
		Assert.assertEquals(tagType, tag.getTagType());
		Assert.assertEquals("value", tag.getValue());
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullTagType() {
		new TagBuilder()
				.withIconURL("http://tuvieja.com/tuvieja.jpg")
				.withValue("value")
				.build();
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullValue() {
		new TagBuilder()
				.withIconURL("http://tuvieja.com/tuvieja.jpg")
				.withTagType(new TagType())
				.build();
	}
}
