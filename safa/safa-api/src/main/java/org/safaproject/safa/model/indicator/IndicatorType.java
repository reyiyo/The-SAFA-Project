package org.safaproject.safa.model.indicator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class defines the type of an indicator.
 * 
 * @author reyiyo
 * 
 */
@Entity
@Table(name = "INDICATOR_TYPE")
public class IndicatorType {

	@Id
	@Column(name = "indicatorName", nullable = false)
	private String indicatorName;

	@Column(name = "minValue", nullable = false)
	private Integer minValue;

	@Column(name = "maxValue", nullable = false)
	private Integer maxValue;
	
	public IndicatorType() {
		// Hibernate constructor
	}
	
	public IndicatorType(String indicatorName, Integer minValue, Integer maxValue) {
		this.indicatorName = indicatorName;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	/**
	 * @return the indicatorName
	 */
	public String getIndicatorName() {
		return indicatorName;
	}

	/**
	 * @param indicatorName
	 *            the indicatorName to set
	 */
	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}

	/**
	 * @return the minValue
	 */
	public Integer getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue
	 *            the minValue to set
	 */
	public void setMinValue(Integer minValue) {
		this.minValue = minValue;
	}

	/**
	 * @return the maxValue
	 */
	public Integer getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue
	 *            the maxValue to set
	 */
	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}

}
