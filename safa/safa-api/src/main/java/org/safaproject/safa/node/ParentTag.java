package org.safaproject.safa.node;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type = "FROM")
public class ParentTag {
	
	@GraphId
	private Long nodeId;

	@StartNode
	private Tag child;

	@EndNode
	private Tag parent;

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	
	public ParentTag() {
		
	}

	public ParentTag(Tag child, Tag parent) {
		this.child = child;
		this.parent = parent;
	}

	public Tag getChild() {
		return child;
	}

	public void setChild(Tag child) {
		this.child = child;
	}

	public Tag getParent() {
		return parent;
	}

	public void setParent(Tag parent) {
		this.parent = parent;
	}

}
