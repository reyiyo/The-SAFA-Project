package org.safaproject.safa.tagging.node;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.safaproject.safa.node.Tag;
import org.safaproject.safa.node.TagType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.node.Neo4jHelper;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:tagging-unit-test-context.xml")
@Transactional
public class TagEntityTest {

	@Autowired
	Neo4jTemplate template;
	
	@Rollback(false)
	@BeforeTransaction
	public void clearDatabase() {
		Neo4jHelper.cleanDb(template);
	}

	@Test
	public void persistedTagShouldBeRetrievableFromGraphDb() {
		TagType universidad = template.save(new TagType("Universidad"));
		Tag utn = template.save(new Tag(universidad, "UTN"));
		Tag retrievedTag = template.findOne(utn.getNodeId(), Tag.class);

		Assert.assertEquals("retrieved tag type matches persisted one", utn,
				retrievedTag);

		Assert.assertEquals("retrieved Tag value matches", "UTN",
				retrievedTag.getValue());

		Assert.assertEquals("retrieved Tag Type matches", universidad,
				retrievedTag.getTagType());
	}

	@Test
	public void persistedTagShouldBeRetrievableFromGraphDbByIndex() {
		String value = "UTN";
		TagType universidad = template.save(new TagType("Universidad"));
		Tag utn = template.save(new Tag(universidad, value));

		GraphRepository<Tag> tagRepository = template.repositoryFor(Tag.class);

		Tag retrievedTag = tagRepository.findByPropertyValue("value", value);

		Assert.assertEquals("retrieved tag type matches persisted one", utn,
				retrievedTag);

		Assert.assertEquals("retrieved Tag value matches", "UTN",
				retrievedTag.getValue());

		Assert.assertEquals("retrieved Tag Type matches", universidad,
				retrievedTag.getTagType());
	}
}
