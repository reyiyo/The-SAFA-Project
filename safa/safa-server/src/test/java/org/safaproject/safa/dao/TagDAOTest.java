package org.safaproject.safa.dao;

import static org.junit.Assert.assertEquals;

import javax.persistence.PersistenceException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.safaproject.safa.model.tag.Tag;
import org.safaproject.safa.model.tag.TagDataTypeEnum;
import org.safaproject.safa.model.tag.TagType;
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

		/**
		 * NOTE: findByExample does NOT take into account relationships. It only
		 * looks for basic data fields. For example, this will NOT work as
		 * expected: example.setTagType(tagTypeDAO.findById("Teacher"));
		 */
		example.setValue("Lidia Capurro");

		assertEquals(1, tagDAO.findByExample(example).size());
	}

	@Test
	public void shallCountByExample() {
		tagDAO.save(createTag("Lidia Capurro", "Teacher"));
		tagDAO.save(createTag("2010/01/01", "Exam Date"));

		Tag example = new Tag();

		/**
		 * NOTE: countByExample does NOT take into account relationships. It
		 * only looks for basic data fields. For example, this will NOT work as
		 * expected: example.setTagType(tagTypeDAO.findById("Teacher"));
		 */

		example.setValue("Lidia Capurro");

		assertEquals(Long.valueOf(1), tagDAO.countByExample(example));
	}

	@Test
	public void shallDeleteTag() {
		Tag tag1 = tagDAO.save(createTag("Lidia Capurro", "Teacher"));
		Tag tag2 = tagDAO.save(createTag("2010/01/01", "Exam Date"));

		tagDAO.delete(tag1);

		assertEquals(tagDAO.countAll(), new Long(1));

		tagDAO.delete(tag2);

		assertEquals(tagDAO.countAll(), new Long(0));

	}

	@Test
	public void shallUpdateTag() {
		Tag tag1 = tagDAO.save(createTag("Lidia Capurro", "Teacher"));

		tag1.setValue("Susana Granado Peralta");
		tagDAO.save(tag1);

		Tag updated = tagDAO.findById(tag1.getTagId());

		assertEquals(tag1.getTagId(), updated.getTagId());
		assertEquals(tag1.getTagType(), updated.getTagType());
		assertEquals(tag1.getValue(), updated.getValue());

	}

	@Test(expected = PersistenceException.class)
	public void shallFailBecauseOfNullType() {
		Tag tag = createTag("Lidia Capurro", "Teacher");
		tag.setTagType(null);
		tagDAO.save(tag);
	}

	@Test(expected = PersistenceException.class)
	public void shallFailBecauseOfNullValue() {
		Tag tag = createTag("Lidia Capurro", "Teacher");
		tag.setValue(null);
		tagDAO.save(tag);
	}

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
