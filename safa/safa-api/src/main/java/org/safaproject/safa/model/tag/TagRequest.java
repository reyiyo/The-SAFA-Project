package org.safaproject.safa.model.tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.user.User;

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
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	@Column(name = "tagName", nullable = false)
	@Size(min = 1, message = "{TagRequest.tagName.Size")
	private String tagName;

	@Column(name = "tagValue", nullable = false)
	@Size(min = 1, message = "{TagRequest.tagValue.Size")
	private String tagValue;

	@Column(name = "reason", nullable = false)
	@Size(min = 1, message = "{TagRequest.reason.Size")
	private String reason;

	@ManyToOne
	@JoinColumn(name = "contentId", nullable = false)
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
