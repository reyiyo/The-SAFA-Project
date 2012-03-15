package org.safaproject.safa.model.content.builder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.safaproject.safa.model.content.Comment;
import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.content.Resource;
import org.safaproject.safa.model.indicator.Indicator;
import org.safaproject.safa.model.tag.Tag;
import org.safaproject.safa.model.user.User;

public class ContentBuilderTest {

	@Test
	public void shallBuild() {
		Set<Comment> comments = new HashSet<Comment>();
		Set<Indicator> indicators = new HashSet<Indicator>();
		Set<Resource> resources = new HashSet<Resource>();
		Set<Tag> tags = new HashSet<Tag>();
		Resource thumbnail = new Resource();
		Date uploadDate = new Date();
		User user = new User();
		
		Content content = new ContentBuilder().withAvailable(true)
				.withComments(comments)
				.withDescription("sarasa")
				.withIndicators(indicators)
				.withResources(resources)
				.withReviewed(true)
				.withTags(tags)
				.withThumbnail(thumbnail)
				.withTitle("Title")
				.withUploadDate(uploadDate)
				.withUser(user)
				.build();
		
		Assert.assertEquals(Boolean.TRUE, content.isAvailable());
		Assert.assertEquals(comments, content.getComments());
		Assert.assertEquals("sarasa", content.getDescription());
		Assert.assertEquals(indicators, content.getIndicators());
		Assert.assertEquals(resources, content.getResources());
		Assert.assertEquals(Boolean.TRUE, content.isReviewed());
		Assert.assertEquals(tags, content.getTags());
		Assert.assertEquals(thumbnail, content.getThumbnail());
		Assert.assertEquals(uploadDate, content.getUploadDate());
		Assert.assertEquals(user, content.getUser());
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullTitle() {
		new ContentBuilder().withAvailable(true)
				.withComments(new HashSet<Comment>())
				.withDescription("sarasa")
				.withIndicators(new HashSet<Indicator>())
				.withResources(new HashSet<Resource>())
				.withReviewed(true)
				.withTags(new HashSet<Tag>())
				.withThumbnail(new Resource())
				.withUploadDate(new Date())
				.withUser(new User())
				.build();
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullDescription() {
		new ContentBuilder().withAvailable(true)
				.withComments(new HashSet<Comment>())
				.withIndicators(new HashSet<Indicator>())
				.withResources(new HashSet<Resource>())
				.withReviewed(true)
				.withTags(new HashSet<Tag>())
				.withThumbnail(new Resource())
				.withTitle("Title")
				.withUploadDate(new Date())
				.withUser(new User())
				.build();
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullUploadDate() {
		new ContentBuilder().withAvailable(true)
				.withComments(new HashSet<Comment>())
				.withDescription("sarasa")
				.withIndicators(new HashSet<Indicator>())
				.withResources(new HashSet<Resource>())
				.withReviewed(true)
				.withTags(new HashSet<Tag>())
				.withThumbnail(new Resource())
				.withTitle("Title")
				.withUser(new User())
				.build();
	}
}
