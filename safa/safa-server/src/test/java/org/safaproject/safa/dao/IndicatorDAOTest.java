package org.safaproject.safa.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.safaproject.safa.model.indicator.Indicator;
import org.safaproject.safa.model.indicator.IndicatorType;
import org.safaproject.safa.model.indicator.builder.IndicatorBuilder;
import org.safaproject.safa.model.indicator.builder.IndicatorTypeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:safa-unit-test-context.xml")
@Transactional
public class IndicatorDAOTest {

	private IndicatorType indicatorType = new IndicatorTypeBuilder().withIndicatorName("WTFperMINUTE").withMaxValue(20).withMinValue(5).build();
	
	@Autowired
	private IndicatorTypeDAO indicatorTypeDAO;
	
	@Autowired
	private IndicatorDAO indicatorDAO;
	
	@Before
	public void init() {
		indicatorTypeDAO.save(indicatorType);
	}
	
	@Test
	public void shallCreateIndicator() {
		Indicator indicator = new IndicatorBuilder().withIndicatorType(indicatorType).withValue(8).build();
		
		indicatorDAO.save(indicator);
		
		Indicator indicatorFromDB = indicatorDAO.findById(indicator.getIndicatorId());
		
		Assert.assertEquals(indicator, indicatorFromDB);
	}
	
	@Test
	public void shallFindAll() {
		Indicator indicator = new IndicatorBuilder().withIndicatorType(indicatorType).withValue(8).build();
		Indicator indicator2 = new IndicatorBuilder().withIndicatorType(indicatorType).withValue(9).build();
		indicatorDAO.save(indicator);
		indicatorDAO.save(indicator2);
		
		List<Indicator> allIndicators = indicatorDAO.findAll();
		
		Assert.assertTrue(allIndicators.contains(indicator));
		Assert.assertTrue(allIndicators.contains(indicator2));
		Assert.assertEquals(2, allIndicators.size());
	}
	
	@Test
	public void shallFindByExample() {
		Indicator indicator = new IndicatorBuilder().withIndicatorType(indicatorType).withValue(8).build();
		indicatorDAO.save(indicator);

		Indicator indicatorByExample = indicatorDAO.findByExample(new IndicatorBuilder().withIndicatorType(indicatorType).withValue(8).build()).get(0);
		
		Assert.assertEquals(indicator, indicatorByExample);
	}
	
	@Test
	public void shallCountByExample() {
		Indicator indicator = new IndicatorBuilder().withIndicatorType(indicatorType).withValue(8).build();
		indicatorDAO.save(indicator);

		Long countByExample = indicatorDAO.countByExample(new IndicatorBuilder().withIndicatorType(indicatorType).withValue(8).build());
		
		Assert.assertEquals(new Long(1), countByExample);
	}
	
	@Test
	public void shallDeleteIndicator() {
		Indicator indicator = new IndicatorBuilder().withIndicatorType(indicatorType).withValue(8).build();
		Indicator indicator2 = new IndicatorBuilder().withIndicatorType(indicatorType).withValue(9).build();
		indicatorDAO.save(indicator);
		indicatorDAO.save(indicator2);
		
		Assert.assertEquals(new Long(2), indicatorDAO.countAll());		
		indicatorDAO.delete(indicator);
		Assert.assertEquals(new Long(1), indicatorDAO.countAll());
		indicatorDAO.delete(indicator2);
		Assert.assertEquals(new Long(0), indicatorDAO.countAll());
	}
	
	@Test
	public void shallUpdateIndicator() {
		Indicator indicator = new IndicatorBuilder().withIndicatorType(indicatorType).withValue(8).build();
		Integer newValue = 9;
		indicatorDAO.save(indicator);
		
		indicator.setValue(newValue);
		indicatorDAO.save(indicator);
		
		Assert.assertEquals(newValue, indicatorDAO.findAll().get(0).getValue());
	}
	
	@Test
	public void shallRetrieveIndicatorType() {
		Indicator indicator = new IndicatorBuilder().withIndicatorType(indicatorType).withValue(8).build();
		
		indicatorDAO.save(indicator);
		
		Indicator indicatorFromDB = indicatorDAO.findById(indicator.getIndicatorId());
		
		Assert.assertEquals(indicatorType, indicatorFromDB.getIndicatorType());
	}
	
	@Ignore
	@Test(expected=RuntimeException.class)
	public void shallFailBecauseOutOfMaxRangeFromIndicatorType() {
		Indicator indicator = new IndicatorBuilder().withIndicatorType(indicatorType).withValue(30).buildPlaneObject();
		
		indicatorDAO.save(indicator);
	}
	
	@Ignore
	@Test(expected=RuntimeException.class)
	public void shallFailBecauseOutOfMinRangeFromIndicatorType() {
		Indicator indicator = new IndicatorBuilder().withIndicatorType(indicatorType).withValue(4).buildPlaneObject();
		
		indicatorDAO.save(indicator);
	}
}
