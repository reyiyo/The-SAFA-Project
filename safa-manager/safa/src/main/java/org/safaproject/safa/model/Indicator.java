package org.safaproject.safa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * This class is used as a calculated value for all votes in a content. It does
 * not comply with the First Normal Form, but is a performance decision. The
 * value attribute will be calculated with a cron job and a Content will have a
 * Set of indicators with the score for each indicator type.
 * 
 * @author reyiyo
 * 
 */
@Entity
public class Indicator {

	@Id
	@GeneratedValue
	private Long indicatorId;

	@ManyToOne
	@JoinColumn(name = "indicatorName")
	private IndicatorType indicatorType;

	private Integer value;

	/**
	 * @return the indicatorId
	 */
	public Long getIndicatorId() {
		return indicatorId;
	}

	/**
	 * @param indicatorId
	 *            the indicatorId to set
	 */
	public void setIndicatorId(Long indicatorId) {
		this.indicatorId = indicatorId;
	}

	/**
	 * @return the indicatorType
	 */
	public IndicatorType getIndicatorType() {
		return indicatorType;
	}

	/**
	 * @param indicatorType
	 *            the indicatorType to set
	 */
	public void setIndicatorType(IndicatorType indicatorType) {
		this.indicatorType = indicatorType;
	}

	/**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

}
