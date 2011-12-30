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

		TagType tagType1 = createTagType("Teacher", TagDataTypeEnum.STRING);
		tagType1 = tagTypeDAO.save(tagType1);

		TagType tagType2 = createTagType("Exam Date", TagDataTypeEnum.DATE);
		tagType2 = tagTypeDAO.save(tagType2);

		assertEquals(tagTypeDAO.findAll().size(), 2);
	}

	//TODO: complete the tests

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
