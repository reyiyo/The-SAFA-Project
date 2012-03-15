package org.safaproject.safa.model.content.builder;

import junit.framework.Assert;

import org.junit.Test;
import org.safaproject.safa.model.content.Resource;
import org.safaproject.safa.model.tag.Tag;

public class ResourceBuilderTest {

	@Test
	public void shallBuild() {
		Tag resourceType = new Tag();
		
		Resource resource = new ResourceBuilder()
				.withDescription("sarasa")
				.withResourceType(resourceType)
				.withSize(50L)
				.withUrl("http://tuvieja.com")
				.build();
		
		Assert.assertEquals("sarasa", resource.getDescription());
		Assert.assertEquals(resourceType, resource.getResourceType());
		Assert.assertEquals(Long.valueOf(50), resource.getSize());
		Assert.assertEquals("http://tuvieja.com", resource.getUrl());
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullUrl() {
		new ResourceBuilder()
				.withDescription("sarasa")
				.withResourceType(new Tag())
				.withSize(50L)
				.build();
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullSize() {
		new ResourceBuilder()
				.withDescription("sarasa")
				.withResourceType(new Tag())
				.withUrl("http://tuvieja.com")
				.build();
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullResourceType() {
		new ResourceBuilder()
				.withDescription("sarasa")
				.withSize(50L)
				.withUrl("http://tuvieja.com")
				.build();
	}
}
