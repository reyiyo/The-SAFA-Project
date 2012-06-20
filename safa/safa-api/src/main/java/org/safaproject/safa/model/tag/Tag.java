package org.safaproject.safa.model.tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author reyiyo
 * 
 */
@Entity
@Table(name = "TAG")
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tagId")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "tagName", nullable = false)
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
	 * @return the tagId
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the tagId to set
	 */
	public void setId(Long id) {
		this.id = id;
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
