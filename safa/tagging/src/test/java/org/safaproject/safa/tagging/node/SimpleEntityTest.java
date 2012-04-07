package org.safaproject.safa.tagging.node;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:tagging-unit-test-context.xml")
@Transactional
public class SimpleEntityTest {

	@Autowired
	Neo4jTemplate template;

	@Test
	@Transactional
	public void persistedTagTypeShouldBeRetrievableFromGraphDb() {

		TagType universidad = template.save(new TagType("Universidad"));
		TagType retrievedTagType = template.findOne(universidad.getNodeId(),
				TagType.class);

		Assert.assertEquals("retrieved tag type matches persisted one",
				universidad, retrievedTagType);

		Assert.assertEquals("retrieved Tag Type name matches", "Universidad",
				retrievedTagType.getName());
	}

	@Test
	@Transactional
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
	@Transactional
	public void persistedTagTypeShouldBeRetrievableFromGraphDbByIndex() {
		String name = "Universidad";
		TagType universidad = template.save(new TagType(name));
		GraphRepository<TagType> tagTypeRepository = template
				.repositoryFor(TagType.class);

		TagType retrievedTagType = tagTypeRepository.findByPropertyValue(
				"name", name);

		Assert.assertEquals("retrieved Tag Type matches persisted one",
				universidad, retrievedTagType);

		Assert.assertEquals("retrieved Tag Type name matches", name,
				retrievedTagType.getName());
	}

	@Test
	@Transactional
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
