package org.safaproject.safa.model.indicator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "INDICATOR")
public class Indicator implements Comparable<Indicator> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "indicatorId")
	private Long indicatorId;

	@ManyToOne
	@JoinColumn(name = "indicatorName", nullable = false)
	private IndicatorType indicatorType;

	@Column(name = "value", nullable = false)
	private Integer value;
	
	public Indicator() {
		// Hibernate constructor
	}
	
	public Indicator(IndicatorType indicatorType, Integer value) {
		this.indicatorType = indicatorType;
		this.value = value;
	}

	@Override
	public int compareTo(Indicator other) {
		if (other.getIndicatorType().getIndicatorName()
				.equals(this.getIndicatorType().getIndicatorName())) {
			return this.getValue() - other.getValue();
		} else {
			throw new ClassCastException();
		}

	}

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
