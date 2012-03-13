package org.safaproject.safa.model.indicator.builder;

import org.junit.Assert;
import org.junit.Test;
import org.safaproject.safa.model.indicator.IndicatorType;

public class IndicatorTypeBuilderTest {

	@Test
	public void shallBuild() {
		IndicatorType indicatorType = new IndicatorTypeBuilder()
				.withIndicatorName("Indicator").withMaxValue(500)
				.withMinValue(200).build();

		Assert.assertNotNull(indicatorType);
		Assert.assertEquals("Indicator", indicatorType.getIndicatorName());
		Assert.assertEquals(Integer.valueOf(500), indicatorType.getMaxValue());
		Assert.assertEquals(Integer.valueOf(200), indicatorType.getMinValue());
	}

	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullIndicatorName() {
		new IndicatorTypeBuilder().withMaxValue(500).withMinValue(200).build();
	}

	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullMaxValue() {
		new IndicatorTypeBuilder().withIndicatorName("Indicator").withMinValue(200).build();
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullMinValue() {
		new IndicatorTypeBuilder().withIndicatorName("Indicator").withMaxValue(500).build();
	}
}
