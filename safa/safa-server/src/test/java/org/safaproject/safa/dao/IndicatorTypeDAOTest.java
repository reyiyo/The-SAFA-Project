package org.safaproject.safa.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.safaproject.safa.model.indicator.IndicatorType;
import org.safaproject.safa.model.indicator.builder.IndicatorTypeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:safa-unit-test-context.xml")
@Transactional
public class IndicatorTypeDAOTest {

	
	@Autowired
	private IndicatorTypeDAO indicatorTypeDAO;
	
	@Test
	public void shallCreateIndicatorType() {
		IndicatorType indicatorType = new IndicatorTypeBuilder().withIndicatorName("WTFperMINUTE").withMaxValue(20).withMinValue(5).build();
		indicatorTypeDAO.save(indicatorType);
		
		IndicatorType indicatorTypeFromDB = indicatorTypeDAO.findById(indicatorType.getIndicatorName());
		
		Assert.assertEquals(indicatorType, indicatorTypeFromDB);
	}
	
	@Test
	public void shallFindAll() {
		IndicatorType indicatorType = new IndicatorTypeBuilder().withIndicatorName("WTFperMINUTE").withMaxValue(20).withMinValue(5).build();
		indicatorTypeDAO.save(indicatorType);
		IndicatorType indicatorType2 = new IndicatorTypeBuilder().withIndicatorName("LCDTOMperMINUTE").withMaxValue(10).withMinValue(2).build();
		indicatorTypeDAO.save(indicatorType2);
		
		List<IndicatorType> indicatorTypes = indicatorTypeDAO.findAll();
		
		Assert.assertEquals(2, indicatorTypes.size());
		Assert.assertTrue(indicatorTypes.contains(indicatorType));
		Assert.assertTrue(indicatorTypes.contains(indicatorType2));
	}
	
	@Test
	public void shallFindByExample() {
		IndicatorType indicatorType = new IndicatorTypeBuilder().withIndicatorName("WTFperMINUTE").withMaxValue(20).withMinValue(5).build();
		indicatorTypeDAO.save(indicatorType);
		
		IndicatorType indicatorTypeByExample = indicatorTypeDAO.findByExample(new IndicatorTypeBuilder().withIndicatorName("WTFperMINUTE").withMaxValue(20).withMinValue(5).build()).get(0);
		
		Assert.assertEquals(indicatorType, indicatorTypeByExample);
	}
	
	@Test
	public void shallCountByExample() {
		IndicatorType indicatorType = new IndicatorTypeBuilder().withIndicatorName("WTFperMINUTE").withMaxValue(20).withMinValue(5).build();
		indicatorTypeDAO.save(indicatorType);
		
		Long countByExample = indicatorTypeDAO.countByExample(new IndicatorTypeBuilder().withIndicatorName("WTFperMINUTE").withMaxValue(20).withMinValue(5).build());
		
		Assert.assertEquals(new Long(1), countByExample);
	}
	
	@Test
	public void shallDeleteIndicatorType() {
		IndicatorType indicatorType = new IndicatorTypeBuilder().withIndicatorName("WTFperMINUTE").withMaxValue(20).withMinValue(5).build();
		indicatorTypeDAO.save(indicatorType);
		IndicatorType indicatorType2 = new IndicatorTypeBuilder().withIndicatorName("LCDTOMperMINUTE").withMaxValue(10).withMinValue(2).build();
		indicatorTypeDAO.save(indicatorType2);
		
		Assert.assertEquals(new Long(2), indicatorTypeDAO.countAll());
		indicatorTypeDAO.delete(indicatorType);
		Assert.assertEquals(new Long(1), indicatorTypeDAO.countAll());
		indicatorTypeDAO.delete(indicatorType2);
		Assert.assertEquals(new Long(0), indicatorTypeDAO.countAll());
	}
	
	@Test
	public void shallUpdateIndicatorType() {
		Integer newMaxValue = 8;
		IndicatorType indicatorType = new IndicatorTypeBuilder().withIndicatorName("WTFperMINUTE").withMaxValue(20).withMinValue(5).build();
		indicatorTypeDAO.save(indicatorType);
		
		indicatorType.setMaxValue(newMaxValue);
		indicatorTypeDAO.save(indicatorType);
		
		Assert.assertEquals(newMaxValue, indicatorTypeDAO.findAll().get(0).getMaxValue());
	}
	
	@Test(expected=PersistenceException.class)
	public void shallFailBecauseOfNullName() {
		IndicatorType indicatorType = new IndicatorTypeBuilder().withIndicatorName(null).withMaxValue(20).withMinValue(5).buildPlaneObject();
		indicatorTypeDAO.save(indicatorType);
	}
	
	@Ignore
	@Test(expected=NullPointerException.class)
	public void shallFailBecauseOfNullMinValue() {
		IndicatorType indicatorType = new IndicatorTypeBuilder().withIndicatorName("WTFperMINUTE").withMaxValue(null).withMinValue(5).buildPlaneObject();
		indicatorTypeDAO.save(indicatorType);
	}
	
	@Ignore
	@Test(expected=NullPointerException.class)
	public void shallFailBecauseOfNullMaxValue() {
		IndicatorType indicatorType = new IndicatorTypeBuilder().withIndicatorName("WTFperMINUTE").withMaxValue(20).withMinValue(null).buildPlaneObject();
		indicatorTypeDAO.save(indicatorType);
	}
}
