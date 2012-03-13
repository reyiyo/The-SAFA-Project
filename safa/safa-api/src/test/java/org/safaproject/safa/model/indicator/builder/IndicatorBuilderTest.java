package org.safaproject.safa.model.indicator.builder;

import org.junit.Assert;
import org.junit.Test;
import org.safaproject.safa.model.indicator.Indicator;
import org.safaproject.safa.model.indicator.IndicatorType;

public class IndicatorBuilderTest {

	@Test
	public void shallBuild() {
		IndicatorType indicatorType = new IndicatorType();
		indicatorType.setIndicatorName("Indicator");
		indicatorType.setMaxValue(100);
		indicatorType.setMinValue(20);

		Indicator indicator = new IndicatorBuilder()
				.withIndicatorType(indicatorType).withValue(50).build();

		Assert.assertNotNull(indicator);
		Assert.assertNotNull(indicator.getIndicatorType());
		Assert.assertNotNull(indicator.getValue());

		Assert.assertEquals(indicatorType, indicator.getIndicatorType());
		Assert.assertEquals(Integer.valueOf(50), indicator.getValue());
	}

	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullIndicatorType() {
		new IndicatorBuilder().withValue(50).build();
	}

	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullValue() {
		new IndicatorBuilder().withIndicatorType(new IndicatorType()).build();
	}

	@Test(expected = IllegalStateException.class)
	public void shallFailBecauseOfTooBigValue() {
		IndicatorType indicatorType = new IndicatorType();
		indicatorType.setIndicatorName("Indicator");
		indicatorType.setMaxValue(30);
		indicatorType.setMinValue(20);

		new IndicatorBuilder().withIndicatorType(indicatorType).withValue(50).build();
	}
	
	@Test(expected = IllegalStateException.class)
	public void shallFailBecauseOfTooSmallValue() {
		IndicatorType indicatorType = new IndicatorType();
		indicatorType.setIndicatorName("Indicator");
		indicatorType.setMaxValue(100);
		indicatorType.setMinValue(20);

		new IndicatorBuilder().withIndicatorType(indicatorType).withValue(10).build();
	}
}
