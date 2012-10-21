package org.safaproject.safa.node;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.safaproject.safa.node.dto.TagTypeDTO;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class TagType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6534556343172944550L;

	@GraphId
	private Long nodeId;

	@Indexed
	private String name;

	@Fetch
	@RelatedTo(type = "DEPENDS", direction = Direction.OUTGOING)
	private Set<TagType> dependencies = new HashSet<TagType>();

	public TagType() {

	}

	public TagType(String name) {
		this.name = name;
	}

	public TagType(TagTypeDTO tagType) {
		this.nodeId = tagType.getNodeId();
		this.name = tagType.getName();
		// Dependencies will remain null when constructing from DTO, as they are
		// not necessary. Be carefull if needed.
	}

	public Boolean dependsOf(TagType otherTagType) {
		return this.dependencies.contains(otherTagType);
	}

	public void addDependency(TagType dependency) {
		this.dependencies.add(dependency);
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
		TagType other = (TagType) obj;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TagType> getDependencies() {
		return dependencies;
	}

	public void setDependencies(Set<TagType> dependencies) {
		this.dependencies = dependencies;
	}
}
