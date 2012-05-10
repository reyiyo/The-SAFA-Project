package org.safaproject.safa.node;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class TagType {

	@GraphId
	private Long nodeId;

	@Indexed
	private String name;

	@RelatedTo(type = "DEPENDS", direction = Direction.OUTGOING)
	private Set<TagType> dependencies = new HashSet<TagType>();

	public TagType() {

	}

	public TagType(String name) {
		this.name = name;
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
