package org.safaproject.safa.model.indicator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.user.User;

/**
 * This class defines each vote of an user.
 * 
 * @author reyiyo
 * 
 */
@Entity
@Table(name = "VOTE")
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voteId")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "indicatorId", nullable = false)
	private Indicator indicator;

	@ManyToOne
	@JoinColumn(name = "contentId", nullable = false)
	private Content content;

	@Column(name = "value", nullable = false)
	private Integer value;
	
	public Vote() {
		// Hibernate constructor
	}
	
	public Vote(User user, Indicator indicator, Content content, Integer value) {
		this.user = user;
		this.indicator = indicator;
		this.content = content;
		this.value = value;
	}

	/**
	 * @param id
	 *            the voteId to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the voteId
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the indicator
	 */
	public Indicator getIndicator() {
		return indicator;
	}

	/**
	 * @param indicator
	 *            the indicator to set
	 */
	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}

	/**
	 * @return the content
	 */
	public Content getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(Content content) {
		this.content = content;
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
