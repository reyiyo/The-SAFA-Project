package org.safaproject.safa.node;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Content implements Serializable {

	private static final long serialVersionUID = 6061837681279030593L;

	@GraphId
	private Long nodeId;

	@Indexed(unique=true)
	private Long contentId;

	@Fetch
	@RelatedTo(type = "HAS", direction = Direction.OUTGOING)
	private Set<Tag> tags = new HashSet<Tag>();

	public Content() {
		// Default constructor for frameworks.
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Content other = (Content) obj;
		if (nodeId == null)
			return other.nodeId == null;
		return nodeId.equals(other.nodeId);
	}

	public void addTag(Tag tag) {
		this.tags.add(tag);
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
}
