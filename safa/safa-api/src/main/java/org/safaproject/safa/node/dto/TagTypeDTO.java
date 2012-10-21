package org.safaproject.safa.node.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.safaproject.safa.node.TagType;

/**
 * TagType Data Transfer Object to map TagType nodes to JSON.
 * 
 * @author reyiyo
 * 
 */
public class TagTypeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6540849054819594542L;

	private Long nodeId;

	private String name;

	/**
	 * This is a {@code Set<String>} containing the {@code TagType name}
	 * property of the dependencies, as they are indexed.
	 */
	private Set<String> dependencies = new HashSet<String>();

	public TagTypeDTO() {

	}

	public TagTypeDTO(TagType tagType) {
		this.nodeId = tagType.getNodeId();
		this.name = tagType.getName();
		for (TagType t : tagType.getDependencies()) {
			this.dependencies.add(t.getName());
		}
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

	public Set<String> getDependencies() {
		return dependencies;
	}

	public void setDependencies(Set<String> dependencies) {
		this.dependencies = dependencies;
	}

}
