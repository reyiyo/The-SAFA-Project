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
public class TagTypeEntityTest {

	@Autowired
	Neo4jTemplate template;

	@Test
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

}
