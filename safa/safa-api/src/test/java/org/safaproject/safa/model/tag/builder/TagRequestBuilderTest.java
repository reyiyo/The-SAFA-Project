package org.safaproject.safa.model.tag.builder;

import junit.framework.Assert;

import org.junit.Test;
import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.content.builder.ContentBuilder;
import org.safaproject.safa.model.tag.TagRequest;
import org.safaproject.safa.model.user.User;
import org.safaproject.safa.model.user.builder.UserBuilder;

public class TagRequestBuilderTest {

	@Test
	public void shallBuild() {
		Content content = new Content();
		User user = new User();

		TagRequest tagRequest = new TagRequestBuilder().withContent(content)
				.withReason("Reason").withTagName("tagName")
				.withTagValue("tagValue").withUser(user).build();

		Assert.assertEquals(content, tagRequest.getContent());
		Assert.assertEquals("Reason", tagRequest.getReason());
		Assert.assertEquals("tagName", tagRequest.getTagName());
		Assert.assertEquals("tagValue", tagRequest.getTagValue());
		Assert.assertEquals(user, tagRequest.getUser());
	}

	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullContent() {
		User user = new UserBuilder().withEmail("sarasa@frula.com")
				.withLocked(true).withOpenIDurlToken("token")
				.withPassword("password").withUsername("username").build();

		new TagRequestBuilder().withReason("Reason").withTagName("tagName")
				.withTagValue("tagValue").withUser(user).build();
	}

	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullReason() {
		Content content = new ContentBuilder().withAvailable(true)
				.withDescription("Desc").withReviewed(true)
				.withTitle("Design Patterns").build();
		User user = new UserBuilder().withEmail("sarasa@frula.com")
				.withLocked(true).withOpenIDurlToken("token")
				.withPassword("password").withUsername("username").build();

		new TagRequestBuilder().withContent(content).withTagName("tagName")
				.withTagValue("tagValue").withUser(user).build();
	}

	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullTagName() {
		Content content = new ContentBuilder().withAvailable(true)
				.withDescription("Desc").withReviewed(true)
				.withTitle("Design Patterns").build();
		User user = new UserBuilder().withEmail("sarasa@frula.com")
				.withLocked(true).withOpenIDurlToken("token")
				.withPassword("password").withUsername("username").build();

		new TagRequestBuilder().withContent(content).withReason("Reason")
				.withTagValue("tagValue").withUser(user).build();
	}

	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullTagValue() {
		Content content = new ContentBuilder().withAvailable(true)
				.withDescription("Desc").withReviewed(true)
				.withTitle("Design Patterns").build();
		User user = new UserBuilder().withEmail("sarasa@frula.com")
				.withLocked(true).withOpenIDurlToken("token")
				.withPassword("password").withUsername("username").build();

		new TagRequestBuilder().withContent(content).withReason("Reason")
				.withTagName("tagName").withUser(user).build();
	}

	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullUser() {
		Content content = new ContentBuilder().withAvailable(true)
				.withDescription("Desc").withReviewed(true)
				.withTitle("Design Patterns").build();
		
		new TagRequestBuilder().withContent(content).withReason("Reason")
				.withTagName("tagName").withTagValue("tagValue").build();
	}
}
