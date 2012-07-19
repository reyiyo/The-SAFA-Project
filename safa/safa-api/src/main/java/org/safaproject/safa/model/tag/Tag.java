package org.safaproject.safa.model.tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.safaproject.safa.model.BaseEntity;

/**
 * @author reyiyo
 * 
 */
@Entity
@Table(name = "TAG")
public class Tag extends BaseEntity{

	@ManyToOne
	@JoinColumn(name = "tagTypeId", nullable = false)
	private TagType tagType;

	@Column(name = "value", nullable = false)
	private String value;

	@Column(name = "iconURL")
	private String iconURL;

	public Tag() {
		// Constructor for hibernate
	}

	public Tag(TagType tagType, String value, String iconURL) {
		super();
		this.tagType = tagType;
		this.value = value;
		this.iconURL = iconURL;
	}

	public String toString() {
		return this.getTagType().toString() + ": " + this.getValue();
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the tagType
	 */
	public TagType getTagType() {
		return tagType;
	}

	/**
	 * @param tagType
	 *            the tagType to set
	 */
	public void setTagType(TagType tagType) {
		this.tagType = tagType;
	}

	/**
	 * @return the iconURL
	 */
	public String getIconURL() {
		return iconURL;
	}

	/**
	 * @param iconURL
	 *            the iconURL to set
	 */
	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}

}
