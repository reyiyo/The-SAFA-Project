package org.safaproject.safa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This class describes a user request for a new tag that doesn't exist in the
 * system.
 * 
 * @author reyiyo
 * 
 */
@Entity
@Table(name = "TAG_REQUEST")
public class TagRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tagRequestId")
	private Long tagRequestId;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@Column(name = "tagName")
	private String tagName;

	@Column(name = "tagValue")
	private String tagValue;

	@Column(name = "reason")
	private String reason;

	@ManyToOne
	@JoinColumn(name = "contentId")
	private Content content;

	/**
	 * @return the tagRequestId
	 */
	public Long getTagRequestId() {
		return tagRequestId;
	}

	/**
	 * @param tagRequestId
	 *            the tagRequestId to set
	 */
	public void setTagRequestId(Long tagRequestId) {
		this.tagRequestId = tagRequestId;
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
	 * @return the tagName
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * @param tagName
	 *            the tagName to set
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	/**
	 * @return the tagValue
	 */
	public String getTagValue() {
		return tagValue;
	}

	/**
	 * @param tagValue
	 *            the tagValue to set
	 */
	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
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

}
