package org.safaproject.safa.model.indicator.builder;

import org.safaproject.safa.model.indicator.IndicatorType;

import com.google.common.base.Preconditions;


public class IndicatorTypeBuilder {
	
	private String indicatorName;

	private Integer minValue;

	private Integer maxValue;
	
	public IndicatorTypeBuilder withIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
		return this;
	}
	
	public IndicatorTypeBuilder withMinValue(Integer minValue) {
		this.minValue = minValue;
		return this;
	}
	
	public IndicatorTypeBuilder withMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
		return this;
	}
	
	public IndicatorType build() {
		Preconditions.checkNotNull(indicatorName);
		Preconditions.checkNotNull(minValue);
		Preconditions.checkNotNull(maxValue);
		
		return this.buildPlaneObject();
	}

	public IndicatorType buildPlaneObject() {
		return new IndicatorType(indicatorName, minValue, maxValue);
	}
}
