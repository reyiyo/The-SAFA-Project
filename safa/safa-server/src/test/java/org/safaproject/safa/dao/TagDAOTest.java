package org.safaproject.safa.dao;

import static org.junit.Assert.assertEquals;

import javax.persistence.PersistenceException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.safaproject.safa.model.tag.Tag;
import org.safaproject.safa.model.tag.TagDataTypes;
import org.safaproject.safa.model.tag.TagType;
import org.safaproject.safa.model.tag.builder.TagBuilder;
import org.safaproject.safa.model.tag.builder.TagTypeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:safa-unit-test-context.xml")
@Transactional
public class TagDAOTest {

	@Autowired
	private TagTypeDAO tagTypeDAO;

	@Autowired
	private TagDAO tagDAO;

	private TagType teacher = new TagTypeBuilder().withTagName("Teacher")
			.withTagDataType(TagDataTypes.STRING).build();

	private TagType examDate = new TagTypeBuilder().withTagName("Exam Date")
			.withTagDataType(TagDataTypes.DATE).build();

	@Before
	public void setUp() {
		tagTypeDAO.save(teacher);
		tagTypeDAO.save(examDate);
	}

	@Test
	public void shallCreateTag() {

		final Tag tag = new TagBuilder().withValue("Lidia Capurro")
				.withTagType(teacher).build();

		tagDAO.save(tag);

		Tag tagFromDB = tagDAO.findById(tag.getId());

		assertEquals(tag, tagFromDB);
	}

	@Test
	public void shallFindAll() {

		tagDAO.save(new TagBuilder().withValue("Lidia Capurro")
				.withTagType(teacher).build());

		tagDAO.save(new TagBuilder().withValue("2010/01/01")
				.withTagType(examDate).build());

		assertEquals(tagTypeDAO.findAll().size(), 2);
	}

	@Test
	public void shallDeleteTag() {
		Tag tag1 = new TagBuilder().withValue("Lidia Capurro")
				.withTagType(teacher).build();
		Tag tag2 = new TagBuilder().withValue("2010/01/01")
				.withTagType(examDate).build();

		tagDAO.save(tag1);
		tagDAO.save(tag2);

		tagDAO.delete(tag1);

		assertEquals(tagDAO.countAll(), new Long(1));

		tagDAO.delete(tag2);

		assertEquals(tagDAO.countAll(), new Long(0));

	}

	@Test
	public void shallUpdateTag() {
		Tag tag1 = new TagBuilder().withValue("Lidia Capurro")
				.withTagType(teacher).build();
		tagDAO.save(tag1);

		tag1.setValue("Susana Granado Peralta");
		tagDAO.save(tag1);

		Tag updated = tagDAO.findById(tag1.getId());

		assertEquals(tag1.getId(), updated.getId());
		assertEquals(tag1.getTagType(), updated.getTagType());
		assertEquals(tag1.getValue(), updated.getValue());

	}

	@Test(expected = PersistenceException.class)
	public void shallFailBecauseOfNullType() {
		Tag tag = new TagBuilder().withValue("Lidia Capurro")
				.withTagType(teacher).build();
		tag.setTagType(null);
		tagDAO.save(tag);
	}

	@Test(expected = PersistenceException.class)
	public void shallFailBecauseOfNullValue() {
		Tag tag = new TagBuilder().withValue("Lidia Capurro")
				.withTagType(teacher).build();
		tag.setValue(null);
		tagDAO.save(tag);
	}
}