package org.safaproject.safa.tagging.node;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.safaproject.safa.node.Content;
import org.safaproject.safa.node.Tag;
import org.safaproject.safa.node.TagType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.node.Neo4jHelper;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:tagging-unit-test-context.xml")
@Transactional
public class ContentEntityTest {

	@Autowired
	Neo4jTemplate template;

	@Rollback(false)
	@BeforeTransaction
	public void clearDatabase() {
		Neo4jHelper.cleanDb(template);
	}

	@Test
	public void persistedContentShouldBeRetrievableFromGraphDb() {
		TagType universidad = template.save(new TagType("Universidad"));
		Tag utn = template.save(new Tag(universidad, "UTN"));
		Tag uba = template.save(new Tag(universidad, "UBA"));

		utn.setTagType(universidad);
		uba.setTagType(universidad);

		Content content = new Content();
		content.addTag(utn);
		content.addTag(uba);

		content.setContentId(1L);
		template.save(content);

		Content retrieved = template
				.findOne(content.getNodeId(), Content.class);

		Assert.assertEquals("retrieved tag type matches persisted one",
				content, retrieved);

		Assert.assertEquals("retrieved Content ID matches", new Long(1),
				retrieved.getContentId());

		Assert.assertTrue("retrieved content's Tags matches", retrieved
				.getTags().contains(uba));
		Assert.assertTrue("retrieved content's Tags matches", retrieved
				.getTags().contains(utn));
	}

	@Test
	public void persistedTagShouldBeRetrievableFromGraphDbByIndex() {

		TagType universidad = template.save(new TagType("Universidad"));
		Tag utn = template.save(new Tag(universidad, "UTN"));
		Tag uba = template.save(new Tag(universidad, "UBA"));

		utn.setTagType(universidad);
		uba.setTagType(universidad);

		Content content = new Content();
		content.addTag(utn);
		content.addTag(uba);

		content.setContentId(1L);
		template.save(content);

		GraphRepository<Content> contentRepository = template
				.repositoryFor(Content.class);

		Content retrieved = contentRepository.findByPropertyValue("contentId",
				1L);

		Assert.assertEquals("retrieved tag type matches persisted one",
				content, retrieved);

		Assert.assertEquals("retrieved Content ID matches", new Long(1),
				retrieved.getContentId());

		Assert.assertTrue("retrieved content's Tags matches", retrieved
				.getTags().contains(uba));
		Assert.assertTrue("retrieved content's Tags matches", retrieved
				.getTags().contains(utn));

	}

	/**
	 * The behavior of @code{@Indexed(unique=true)} is a
	 * bit tricky:
	 * 
	 * The uniqueness will be taken into account when creating the entity by
	 * reusing an existing entity if that unique key-combination already exists.
	 * On saving of the field it will be cross-checked against the index and
	 * fail with a DataIntegrityViolationException if the field was changed to
	 * an already existing unique value. Null values are no longer allowed for
	 * these properties.
	 * http://static.springsource.org/spring-data/data-graph/snapshot
	 * -site/reference/html/#d5e1035
	 * 
	 * What means, the DataIntegrityViolationException will only be raised if an
	 * existing entity is updated to an unique index value that already exists.
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public void shallFailBecauseOfUniqueConstraint() {
		TagType universidad = template.save(new TagType("Universidad"));
		Tag utn = template.save(new Tag(universidad, "UTN"));
		Content content = new Content();
		content.setContentId(1L);
		Content content2 = new Content();
		content2.setContentId(1L);
		content2.setTags(Sets.newHashSet(utn));

		// This saves the first content
		template.save(content);

		// This updates the first content, as they have the same index value
		template.save(content2);

		// Look :P
		Assert.assertEquals(content, content2);

		// This is how it will fail:
		Content content3 = new Content();
		// We set a different index
		content3.setContentId(2L);
		template.save(content3);

		// Now we update it to an existing index
		content3.setContentId(1L);

		// And this is how it raises the exception:
		// Updating an existing entity's index, with an existing index value
		template.save(content3);
	}
}
