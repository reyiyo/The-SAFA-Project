package org.safaproject.safa.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.safaproject.safa.model.Tag;
import org.safaproject.safa.model.TagDataTypeEnum;
import org.safaproject.safa.model.TagType;
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

	@Before
	public void setUp() {
		tagTypeDAO.save(createTagType("Teacher", TagDataTypeEnum.STRING));
		tagTypeDAO.save(createTagType("Exam Date", TagDataTypeEnum.DATE));
	}

	@Test
	public void shallCreateTag() {

		final Tag tag = tagDAO.save(createTag("Lidia Capurro", "Teacher"));

		Tag tagFromDB = tagDAO.findById(tag.getTagId());

		assertEquals(tag, tagFromDB);
	}

	@Test
	public void shallFindAll() {

		tagDAO.save(createTag("Lidia Capurro", "Teacher"));
		tagDAO.save(createTag("2010/01/01", "Exam Date"));

		assertEquals(tagTypeDAO.findAll().size(), 2);
	}

	@Test
	public void shallFindByExample() {
		tagDAO.save(createTag("Lidia Capurro", "Teacher"));
		tagDAO.save(createTag("2010/01/01", "Exam Date"));

		Tag example = new Tag();
		example.setTagType(tagTypeDAO.findById("Teacher"));
		example.setValue("Lidia Capurro");
		// FIXME: Fix find by example search (@see
		// org.safaproject.safa.dao.GenericHibernateDAO)

		assertEquals(1, tagDAO.findByExample(example).size());
	}

	// TODO: complete the tests

	private TagType createTagType(final String tagName,
			final TagDataTypeEnum tagDataType) {
		TagType tagType = new TagType();
		tagType.setTagName(tagName);
		tagType.setTagDataType(tagDataType);

		return tagType;
	}

	private Tag createTag(final String value, final String tagName) {
		Tag tag = new Tag();
		tag.setValue(value);
		tag.setTagType(tagTypeDAO.findById(tagName));
		return tag;
	}
}
