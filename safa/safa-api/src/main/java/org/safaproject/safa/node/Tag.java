package org.safaproject.safa.node;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.safaproject.safa.node.dto.TagDTO;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

@NodeEntity
public class Tag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2729083306760515739L;

	@GraphId
	private Long nodeId;

	@Fetch
	private TagType tagType;

	@Indexed
	private String value;

	@Fetch
	@RelatedToVia
	Set<ParentTag> parents = new HashSet<ParentTag>();

	public Tag() {

	}
	
	public Tag(TagDTO tag) {
		this.nodeId = tag.getNodeId();
		this.tagType = new TagType(tag.getTagType());
		this.value = tag.getValue();
		// ParentTags will remain null when constructing from DTO, as they are
		// not necessary. Be carefull if needed.
	}

	public ParentTag addParent(Tag parent) {
		ParentTag p = new ParentTag(this, parent);
		parents.add(p);
		return p;
	}

	public Boolean isChildOf(Tag otherTag) {
		return this.parents.contains(otherTag);
	}

	public Tag(TagType tagType, String value) {
		this.tagType = tagType;
		this.value = value;
	}

	@Override
	public int hashCode() {
		return (nodeId == null) ? 0 : nodeId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (nodeId == null)
			return other.nodeId == null;
		return nodeId.equals(other.nodeId);
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public TagType getTagType() {
		return tagType;
	}

	public void setTagType(TagType tagType) {
		this.tagType = tagType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Set<ParentTag> getParents() {
		return parents;
	}

	public void setParents(Set<ParentTag> parents) {
		this.parents = parents;
	}
}
