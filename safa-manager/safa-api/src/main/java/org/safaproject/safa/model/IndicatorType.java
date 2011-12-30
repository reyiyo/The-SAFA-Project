package org.safaproject.safa.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * This class defines the type of an indicator.
 * 
 * @author reyiyo
 * 
 */
@Entity
public class IndicatorType {

	@Id
	private String indicatorName;

	@OneToMany
	@JoinColumn(name = "indicatorName")
	private Set<Indicator> indicators;

	@NotNull
	private Integer minValue;

	@NotNull
	private Integer maxValue;

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
	 * @return the indicators
	 */
	public Set<Indicator> getIndicators() {
		return indicators;
	}

	/**
	 * @param indicators
	 *            the indicators to set
	 */
	public void setIndicators(Set<Indicator> indicators) {
		this.indicators = indicators;
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
