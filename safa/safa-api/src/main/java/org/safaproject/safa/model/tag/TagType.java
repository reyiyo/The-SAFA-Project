package org.safaproject.safa.model.tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class defines the type of a tag.
 * 
 * @author reyiyo
 * 
 */
@Entity
@Table(name = "TAG_TYPE")
public class TagType {

	/**
	 * The TagType Name cannot be changed/updated because it is the ID of the
	 * entity. To change it, it is needed to remove the old one and create a new
	 * one
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name = "tagName", unique = true, nullable = false)
	private String tagName;

	@Column(name = "tagDataType", nullable = false)
	@Enumerated(EnumType.STRING)
	private TagDataTypes tagDataType;

	public TagType() {
		// Constructor for hibernate
	}

	public TagType(String tagName, TagDataTypes tagDataType) {
		super();
		this.tagName = tagName;
		this.tagDataType = tagDataType;
	}

	public String toString() {
		return this.getTagName();
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
	 * @return the tagType
	 */
	public TagDataTypes getTagDataType() {
		return tagDataType;
	}

	/**
	 * @param tagType
	 *            the tagType to set
	 */
	public void setTagDataType(TagDataTypes tagDataType) {
		this.tagDataType = tagDataType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
