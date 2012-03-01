package org.safaproject.safa.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.safaproject.safa.model.TagDataTypeEnum;
import org.safaproject.safa.model.TagType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:safa-unit-test-context.xml")
@Transactional
public class TagTypeDAOTest {

	@Autowired
	private TagTypeDAO tagTypeDAO;

	@Test
	public void shallCreateTagType() {

		TagType tagType = createTagType("Teacher", TagDataTypeEnum.STRING);
		tagType = tagTypeDAO.save(tagType);

		TagType tagTypeFromDB = tagTypeDAO.findById(tagType.getTagName());

		assertEquals(tagType, tagTypeFromDB);
	}

	@Test
	public void shallFindAll() {

		TagType tagType1 = createTagType("Teacher", TagDataTypeEnum.STRING);
		tagType1 = tagTypeDAO.save(tagType1);

		TagType tagType2 = createTagType("Exam Date", TagDataTypeEnum.DATE);
		tagType2 = tagTypeDAO.save(tagType2);

		assertEquals(tagTypeDAO.findAll().size(), 2);
	}

	@Test
	public void shallFindByExample() {
		TagType tagType = createTagType("University", TagDataTypeEnum.STRING);

		tagType = tagTypeDAO.save(tagType);

		TagType example = new TagType();
		example.setTagName("University");

		TagType tagTypeFromDB = tagTypeDAO.findByExample(example).get(0);

		assertEquals(tagType, tagTypeFromDB);
	}

	@Test
	public void shallGetTagsTypeCount() {
		TagType tagType1 = createTagType("University", TagDataTypeEnum.STRING);
		tagType1 = tagTypeDAO.save(tagType1);

		TagType tagType2 = createTagType("Exam Date", TagDataTypeEnum.DATE);
		tagType2 = tagTypeDAO.save(tagType2);

		assertEquals(tagTypeDAO.countAll(), new Long(2));
	}

	@Test
	public void shallCountByExample() {
		TagType tagType1 = createTagType("University", TagDataTypeEnum.STRING);
		tagType1 = tagTypeDAO.save(tagType1);

		TagType tagType2 = createTagType("Exam Date", TagDataTypeEnum.DATE);
		tagType2 = tagTypeDAO.save(tagType2);

		TagType example = new TagType();

		example.setTagName("Exam Date");
		example.setTagDataType(TagDataTypeEnum.STRING);

		assertEquals(tagTypeDAO.countByExample(example), new Long(1));
	}

	@Test
	public void shallDeleteTagType() {
		TagType tagType1 = createTagType("University", TagDataTypeEnum.STRING);
		tagType1 = tagTypeDAO.save(tagType1);

		TagType tagType2 = createTagType("Exam Date", TagDataTypeEnum.DATE);
		tagType2 = tagTypeDAO.save(tagType2);

		tagTypeDAO.delete(tagType1);

		assertEquals(tagTypeDAO.countAll(), new Long(1));

		tagTypeDAO.delete(tagType2);

		assertEquals(tagTypeDAO.countAll(), new Long(0));

	}

	@Test
	public void updateTest() {
		TagType tagType1 = createTagType("University", TagDataTypeEnum.STRING);
		tagType1 = tagTypeDAO.save(tagType1);

		assertEquals(tagTypeDAO.countAll(), new Long(1));

		TagType toUpdate = tagTypeDAO.findById(tagType1.getTagName());

		/**
		 * The TagType Name cannot be changed/updated because it is the ID of
		 * the entity
		 */

		toUpdate.setTagDataType(TagDataTypeEnum.NUMERIC);
		toUpdate = tagTypeDAO.save(toUpdate);

		assertEquals(tagTypeDAO.countAll(), new Long(1));

		TagType updated = tagTypeDAO.findById(toUpdate.getTagName());

		assertEquals(TagDataTypeEnum.NUMERIC, updated.getTagDataType());

	}

	 @Test
	 public void shallFailBecauseOfNullDataType() {
		 TagType tagType = new TagType();
		 tagType.setTagName("Career");
		 tagType.setTagDataType(null);
		 tagType = tagTypeDAO.save(tagType);
		// TODO: Investigate why this constraint is not being evaluated
	 }

	private TagType createTagType(String tagName, TagDataTypeEnum tagDataType) {
		TagType tagType = new TagType();
		tagType.setTagName(tagName);
		tagType.setTagDataType(tagDataType);

		return tagType;
	}

}
