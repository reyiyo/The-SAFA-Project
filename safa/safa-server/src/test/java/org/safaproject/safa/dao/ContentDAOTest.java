package org.safaproject.safa.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.safaproject.safa.model.content.Comment;
import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.content.builder.CommentBuilder;
import org.safaproject.safa.model.content.builder.ContentBuilder;
import org.safaproject.safa.model.user.User;
import org.safaproject.safa.model.user.builder.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:safa-unit-test-context.xml")
@Transactional
public class ContentDAOTest {

	@Autowired
	private ContentDAO contentDao;

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private CommentDAO commentDao;

	private User testingUser = new UserBuilder().withUsername("Test")
			.withEmail("test@test.com")
			.withOpenIDurlToken("http://laputamadre.com").build();

	@Before
	public void init() {
		testingUser = userDao.save(testingUser);
	}

	@Test
	public void shallCreateContent() {
		Content content = new ContentBuilder().withAvailable(true)
				.withDescription("Desc").withReviewed(true)
				.withTitle("Design Patterns").withUploadDate(new Date())
				.withUser(testingUser).build();

		content = contentDao.save(content);

		Content contentFromDB = contentDao.findById(content.getContentId());

		Assert.assertEquals(content, contentFromDB);
	}

	@Test
	public void shallFindAll() {
		contentDao.save(new ContentBuilder()
				.withAvailable(true).withDescription("Desc").withReviewed(true)
				.withTitle("Design Patterns").withUploadDate(new Date())
				.withUser(testingUser).build());
		contentDao.save(new ContentBuilder()
				.withAvailable(true).withDescription("Desc").withReviewed(true)
				.withTitle("Clean Code").withUploadDate(new Date())
				.withUser(testingUser).build());

		List<Content> all = contentDao.findAll();

		Assert.assertEquals(2, all.size());
	}

	@Test
	public void shallFindByExample() {
		String title = "Design Patterns";
		String desc = "Desc";
		Content content = contentDao.save(new ContentBuilder()
				.withAvailable(true).withDescription(desc).withReviewed(true)
				.withTitle(title).withUploadDate(new Date())
				.withUser(testingUser).build());

		Content contentByExample = contentDao.findByExample(
				new ContentBuilder().withTitle(title).withDescription(desc)
						.withReviewed(true).withAvailable(true).build()).get(0);

		Assert.assertEquals(content, contentByExample);
	}

	@Test
	public void shallCountByExample() {
		String title = "Design Patterns";
		String desc = "Desc";
		contentDao.save(new ContentBuilder()
				.withAvailable(true).withDescription(desc).withReviewed(true)
				.withTitle(title).withUploadDate(new Date())
				.withUser(testingUser).build());

		Long countByExample = contentDao.countByExample(new ContentBuilder()
				.withTitle(title).withDescription(desc).withReviewed(true)
				.withAvailable(true).build());

		Assert.assertEquals(Long.valueOf(1), countByExample);
	}

	@Test
	public void shallDeleteContent() {
		Content content = contentDao.save(new ContentBuilder()
				.withAvailable(true).withDescription("Desc").withReviewed(true)
				.withTitle("Design Patterns").withUploadDate(new Date())
				.withUser(testingUser).build());
		Content content2 = contentDao.save(new ContentBuilder()
				.withAvailable(true).withDescription("Desc").withReviewed(true)
				.withTitle("Clean Code").withUploadDate(new Date())
				.withUser(testingUser).build());

		contentDao.delete(content);

		Assert.assertEquals(Long.valueOf(1), contentDao.countAll());

		contentDao.delete(content2);

		Assert.assertEquals(Long.valueOf(0), contentDao.countAll());
	}

	@Test
	public void shallUpdateContent() {
		String modifiedTitle = "Clean Code";
		Content content = contentDao.save(new ContentBuilder()
				.withAvailable(true).withDescription("Desc").withReviewed(true)
				.withTitle("Design Patterns").withUploadDate(new Date())
				.withUser(testingUser).build());

		content.setTitle(modifiedTitle);
		contentDao.save(content);

		Assert.assertEquals(modifiedTitle, contentDao.findAll().get(0)
				.getTitle());
	}

	@Test
	public void shallCascadeComments() {
		Comment comment = new CommentBuilder().withUser(testingUser)
				.withCommentDate(new Date())
				.withCommentText("This book is the post!").build();
		Set<Comment> comments = new HashSet<Comment>();
		comments.add(comment);
		
		contentDao.save(new ContentBuilder()
				.withAvailable(true).withDescription("Desc").withReviewed(true)
				.withTitle("Design Patterns").withUploadDate(new Date())
				.withUser(testingUser).withComments(comments).build());
		
		Assert.assertEquals(1, contentDao.findAll().get(0).getComments().size());
		Assert.assertEquals(1, commentDao.findAll().size());
		
		commentDao.delete(comment);
	}
}
