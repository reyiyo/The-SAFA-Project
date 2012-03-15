package org.safaproject.safa.model.content.builder;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.safaproject.safa.model.content.Comment;
import org.safaproject.safa.model.user.User;

public class CommentBuilderTest {

	@Test
	public void shallBuild() {
		User user = new User();
		Date date = new Date();
		Comment comment = new CommentBuilder().withCommentDate(date)
				.withCommentText("comment").withUser(user).build();

		Assert.assertEquals(user, comment.getUser());
		Assert.assertEquals(date, comment.getCommentDate());
		Assert.assertEquals("comment", comment.getCommentText());
	}

	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullUser() {
		new CommentBuilder().withCommentDate(new Date())
				.withCommentText("comment").build();
	}

	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullCommentDate() {
		new CommentBuilder().withUser(new User()).withCommentText("comment")
				.build();
	}

	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullCommentText() {
		new CommentBuilder().withCommentDate(new Date()).withUser(new User())
				.build();
	}
}
