package org.safaproject.safa.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * This class defines the type of a tag.
 * 
 * @author reyiyo
 * 
 */
@Entity
public class TagType {

	/**
	 * The TagType Name cannot be changed/updated because it is the ID of the
	 * entity. To change it, it is needed to remove the old one and create a new
	 * one
	 */
	@Id
	private String tagName;

	@Enumerated(EnumType.STRING)
	@NotNull
	// TODO: Investigate why this constraint is not being evaluated
	private TagDataTypeEnum tagDataType;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tagName")
	private Set<Tag> tags;

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
	public TagDataTypeEnum getTagDataType() {
		return tagDataType;
	}

	/**
	 * @param tagType
	 *            the tagType to set
	 */
	public void setTagDataType(TagDataTypeEnum tagDataType) {
		this.tagDataType = tagDataType;
	}

	/**
	 * The TagType Name cannot be changed/updated because it is the ID of the
	 * entity
	 */
	/**
	 * @return the tags
	 */
	public Set<Tag> getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

}
