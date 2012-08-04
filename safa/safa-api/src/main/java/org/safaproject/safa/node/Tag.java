package org.safaproject.safa.node;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

@NodeEntity
public class Tag {

	@GraphId
	private Long nodeId;

	private TagType tagType;

	@Indexed
	private String value;
	
	@RelatedToVia
    Set<ParentTag> parents=new HashSet<ParentTag>();
    public ParentTag addParent(Tag parent) {
        ParentTag p=new ParentTag(this,parent);
        parents.add(p);
        return p;
    }

//	@Fetch
//	@RelatedToVia(type = "FROM", direction = Direction.OUTGOING)
//	Set<Tag> parents = new HashSet<Tag>();

	public Tag() {

	}

	public Boolean isChildOf(Tag otherTag) {
		return this.parents.contains(otherTag);
	}

//	public void addParent(Tag parent) {
//		this.parents.add(parent);
//	}

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
