package org.safaproject.safa.dao;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.safaproject.safa.model.content.Comment;
import org.safaproject.safa.model.content.builder.CommentBuilder;
import org.safaproject.safa.model.user.User;
import org.safaproject.safa.model.user.builder.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:safa-unit-test-context.xml")
@Transactional
public class CommentDAOTest {

	@Autowired
	private CommentDAO commentDao;

	@Autowired
	private UserDAO userDao;

	private User testingUser = new UserBuilder().withUsername("Test")
			.withEmail("test@test.com")
			.withOpenIDurlToken("http://laputamadre.com").build();

	@Before
	public void init() {
		testingUser = userDao.save(testingUser);
	}

	@Test
	public void shallCreateComment() {
		Comment comment = new CommentBuilder().withCommentDate(new Date())
				.withCommentText("Hello world!").withUser(testingUser).build();

		comment = commentDao.save(comment);

		Comment commentFromDB = commentDao.findById(comment.getCommentId());

		Assert.assertEquals(comment, commentFromDB);
	}

	@Test
	public void shallFindAll() {
		Comment comment = new CommentBuilder().withCommentDate(new Date())
				.withCommentText("Hello world!").withUser(testingUser).build();
		Comment comment2 = new CommentBuilder().withCommentDate(new Date())
				.withCommentText("Goodbye world!").withUser(testingUser)
				.build();
		
		comment = commentDao.save(comment);
		comment2 = commentDao.save(comment2);
		
		Assert.assertEquals(2, commentDao.findAll().size());
	}
	
	@Test
	public void shallFindByExampleOnUser() {
		Comment comment = new CommentBuilder().withCommentDate(new Date())
				.withCommentText("Hello world!").withUser(testingUser).build();
		comment = commentDao.save(comment);
		
		Comment commentByExample = commentDao.findByExample(new CommentBuilder().withUser(testingUser).build()).get(0);
		
		Assert.assertEquals(comment, commentByExample);
	}
	
	@Test
	public void shallFindByExampleOnCommentDate() {
		Date now = new Date();
		Comment comment = new CommentBuilder().withCommentDate(now)
				.withCommentText("Hello world!").withUser(testingUser).build();
		comment = commentDao.save(comment);
		
		Comment commentByExample = commentDao.findByExample(new CommentBuilder().withCommentDate(now).build()).get(0);
		
		Assert.assertEquals(comment, commentByExample);
	}
	
	@Test
	public void shallFindByExampleOnCommentText() {
		String text = "Hello world!";
		Comment comment = new CommentBuilder().withCommentDate(new Date())
				.withCommentText(text).withUser(testingUser).build();
		comment = commentDao.save(comment);
		
		Comment commentByExample = commentDao.findByExample(new CommentBuilder().withCommentText(text).build()).get(0);
		
		Assert.assertEquals(comment, commentByExample);
	}
	
	@Test
	public void shallCountByExample() {
		Comment comment = new CommentBuilder().withCommentDate(new Date())
				.withCommentText("Hello world!").withUser(testingUser).build();
		
		comment = commentDao.save(comment);
		
		Long countByExample = commentDao.countByExample(new CommentBuilder().withUser(testingUser).build());
		
		Assert.assertEquals(Long.valueOf(1), countByExample);
	}
	
	@Test
	public void shallDeleteComment() {
		Comment comment = commentDao.save(new CommentBuilder().withCommentDate(new Date())
				.withCommentText("Hello world!").withUser(testingUser).build());
		Comment comment2 = commentDao.save(new CommentBuilder().withCommentDate(new Date())
				.withCommentText("Goodbye world!").withUser(testingUser)
				.build());
		
		commentDao.delete(comment);
		
		Assert.assertEquals(Long.valueOf(1), commentDao.countAll());
		
		commentDao.delete(comment2);
		
		Assert.assertEquals(Long.valueOf(0), commentDao.countAll());
	}
	
	@Test
	public void shallUpdateComment() {
		String modifiedText = "Modificado!";
		Comment comment = commentDao.save(new CommentBuilder().withCommentDate(new Date())
				.withCommentText("Hello world!").withUser(testingUser).build());
		
		comment.setCommentText(modifiedText);
		commentDao.save(comment);
		
		Assert.assertEquals(comment.getCommentText(), commentDao.findAll().get(0).getCommentText());

	}
}
