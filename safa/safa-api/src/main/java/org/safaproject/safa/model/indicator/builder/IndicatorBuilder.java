package org.safaproject.safa.model.indicator.builder;

import org.safaproject.safa.model.indicator.Indicator;
import org.safaproject.safa.model.indicator.IndicatorType;

import com.google.common.base.Preconditions;

public class IndicatorBuilder {
	
	private IndicatorType indicatorType;

	private Integer value;
	
	public IndicatorBuilder withIndicatorType(IndicatorType indicatorType) {
		this.indicatorType = indicatorType;
		return this;
	}
	
	public IndicatorBuilder withValue(Integer value) {
		this.value = value;
		return this;
	}
	
	public Indicator build() {
		Preconditions.checkNotNull(value);
		Preconditions.checkNotNull(indicatorType);
		Preconditions.checkState(value <= indicatorType.getMaxValue());
		Preconditions.checkState(value >= indicatorType.getMinValue());
		
		return new Indicator(indicatorType, value);
	}
}
