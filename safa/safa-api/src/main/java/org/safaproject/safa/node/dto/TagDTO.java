package org.safaproject.safa.node.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.safaproject.safa.node.ParentTag;
import org.safaproject.safa.node.Tag;

public class TagDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6470282839898009636L;

	private Long nodeId;

	private TagTypeDTO tagType;

	private String value;

	/**
	 * {@code Set<Long>} with the nodeId of the parent tags.
	 */
	private Set<Long> parents = new HashSet<Long>();

	public TagDTO() {

	}

	public TagDTO(Tag tag) {
		this.nodeId = tag.getNodeId();
		this.tagType = new TagTypeDTO(tag.getTagType());
		for (ParentTag parent : tag.getParents()) {
			this.parents.add(parent.getParent().getNodeId());
		}
		this.value = tag.getValue();

	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public TagTypeDTO getTagType() {
		return tagType;
	}

	public void setTagType(TagTypeDTO tagType) {
		this.tagType = tagType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Set<Long> getParents() {
		return parents;
	}

	public void setParents(Set<Long> parents) {
		this.parents = parents;
	}

}
