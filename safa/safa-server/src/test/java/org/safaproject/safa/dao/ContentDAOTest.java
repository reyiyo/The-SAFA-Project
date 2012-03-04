package org.safaproject.safa.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.safaproject.safa.model.content.Comment;
import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.content.Resource;
import org.safaproject.safa.model.content.builder.CommentBuilder;
import org.safaproject.safa.model.content.builder.ContentBuilder;
import org.safaproject.safa.model.content.builder.ResourceBuilder;
import org.safaproject.safa.model.tag.Tag;
import org.safaproject.safa.model.tag.TagDataTypes;
import org.safaproject.safa.model.tag.TagType;
import org.safaproject.safa.model.tag.builder.TagBuilder;
import org.safaproject.safa.model.tag.builder.TagTypeBuilder;
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

	@Autowired
	private TagTypeDAO tagTypeDAO;

	@Autowired
	private TagDAO tagDAO;

	@Autowired
	private ResourceDAO resourceDAO;

	private User testingUser = new UserBuilder().withUsername("Test")
			.withEmail("test@test.com")
			.withOpenIDurlToken("http://laputamadre.com").build();

	private TagType testTagType = new TagTypeBuilder()
			.withTagName("Resource Type").withTagDataType(TagDataTypes.STRING)
			.build();

	private Tag testResourceType = new TagBuilder().withTagType(testTagType)
			.withValue("PDF").build();

	private Resource testResource = new ResourceBuilder().withDescription("")
			.withSize(10L).withUrl("http://tuvieja.com")
			.withResourceType(testResourceType).build();

	@Before
	public void init() {
		userDao.save(testingUser);
		tagTypeDAO.save(testTagType);
		tagDAO.save(testResourceType);
		resourceDAO.save(testResource);
	}

	@Test
	public void shallCreateContent() {
		Content content = new ContentBuilder()
				.withAvailable(true)
				.withDescription("Desc")
				.withReviewed(true)
				.withThumbnail(testResource)
				.withTitle("Design Patterns")
				.withResources(
						new HashSet<Resource>(Arrays.asList(testResource)))
				.withUploadDate(new Date()).withUser(testingUser).build();

		contentDao.save(content);

		Content contentFromDB = contentDao.findById(content.getContentId());

		Assert.assertEquals(content, contentFromDB);
	}

	@Test
	public void shallFindAll() {
		contentDao.save(new ContentBuilder()
				.withAvailable(true)
				.withDescription("Desc")
				.withReviewed(true)
				.withTitle("Design Patterns")
				.withUploadDate(new Date())
				.withResources(
						new HashSet<Resource>(Arrays.asList(testResource)))
				.withThumbnail(testResource).withUser(testingUser).build());
		contentDao.save(new ContentBuilder()
				.withAvailable(true)
				.withDescription("Desc")
				.withReviewed(true)
				.withTitle("Clean Code")
				.withUploadDate(new Date())
				.withResources(
						new HashSet<Resource>(Arrays.asList(testResource)))
				.withThumbnail(testResource).withUser(testingUser).build());

		List<Content> all = contentDao.findAll();

		Assert.assertEquals(2, all.size());
	}

	@Test
	public void shallFindByExample() {
		String title = "Design Patterns";
		String desc = "Desc";
		Content content = new ContentBuilder()
				.withAvailable(true)
				.withDescription(desc)
				.withReviewed(true)
				.withTitle(title)
				.withUploadDate(new Date())
				.withResources(
						new HashSet<Resource>(Arrays.asList(testResource)))
				.withThumbnail(testResource).withUser(testingUser).build();
		contentDao.save(content);

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
				.withAvailable(true)
				.withDescription(desc)
				.withReviewed(true)
				.withTitle(title)
				.withUploadDate(new Date())
				.withThumbnail(testResource)
				.withResources(
						new HashSet<Resource>(Arrays.asList(testResource)))
				.withUser(testingUser).build());

		Long countByExample = contentDao.countByExample(new ContentBuilder()
				.withTitle(title).withDescription(desc).withReviewed(true)
				.withAvailable(true).build());

		Assert.assertEquals(Long.valueOf(1), countByExample);
	}

	@Test
	public void shallDeleteContent() {
		Content content = new ContentBuilder()
				.withAvailable(true)
				.withDescription("Desc")
				.withReviewed(true)
				.withTitle("Design Patterns")
				.withUploadDate(new Date())
				.withResources(
						new HashSet<Resource>(Arrays.asList(testResource)))
				.withThumbnail(testResource).withUser(testingUser).build();
		contentDao.save(content);

		Content content2 = new ContentBuilder()
				.withAvailable(true)
				.withDescription("Desc")
				.withReviewed(true)
				.withTitle("Clean Code")
				.withUploadDate(new Date())
				.withResources(
						new HashSet<Resource>(Arrays.asList(testResource)))
				.withThumbnail(testResource).withUser(testingUser).build();

		contentDao.save(content2);

		Assert.assertEquals(Long.valueOf(2), contentDao.countAll());

		contentDao.delete(content);

		Assert.assertEquals(Long.valueOf(1), contentDao.countAll());

		contentDao.delete(content2);

		Assert.assertEquals(Long.valueOf(0), contentDao.countAll());
	}

	@Test
	public void shallUpdateContent() {
		String modifiedTitle = "Clean Code";
		Content content = new ContentBuilder()
				.withAvailable(true)
				.withDescription("Desc")
				.withReviewed(true)
				.withTitle("Design Patterns")
				.withUploadDate(new Date())
				.withResources(
						new HashSet<Resource>(Arrays.asList(testResource)))
				.withThumbnail(testResource).withUser(testingUser).build();

		contentDao.save(content);

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
				.withAvailable(true)
				.withDescription("Desc")
				.withReviewed(true)
				.withTitle("Design Patterns")
				.withUploadDate(new Date())
				.withThumbnail(testResource)
				.withUser(testingUser)
				.withResources(
						new HashSet<Resource>(Arrays.asList(testResource)))
				.withComments(comments).build());

		Assert.assertEquals(1, contentDao.findAll().get(0).getComments().size());
		Assert.assertEquals(1, commentDao.findAll().size());

		commentDao.delete(comment);
	}

	@Test(expected = PersistenceException.class)
	public void shallFailBecauseOfNullTitle() {
		Content content = new ContentBuilder()
				.withAvailable(true)
				.withDescription("Desc")
				.withReviewed(true)
				.withTitle("Design Patterns")
				.withUploadDate(new Date())
				.withResources(
						new HashSet<Resource>(Arrays.asList(testResource)))
				.withThumbnail(testResource).withUser(testingUser).build();

		content.setTitle(null);
		contentDao.save(content);
	}

}
