package org.safaproject.safa.model.tag.builder;

import java.util.List;

import org.safaproject.safa.model.tag.Tag;
import org.safaproject.safa.model.tag.TagTreeNode;

import com.google.common.base.Preconditions;

public class TagTreeNodeBuilder {

	private List<TagTreeNode> children;

	private TagTreeNode parent;

	private String nodeName;
	
	private Tag myTag;
	
	public TagTreeNodeBuilder withChildren(List<TagTreeNode> children) {
		this.children = children;
		return this;
	}
	
	public TagTreeNodeBuilder withParent(TagTreeNode parent) {
		this.parent = parent;
		return this;
	}
	
	public TagTreeNodeBuilder withNodeName(String nodeName) {
		this.nodeName = nodeName;
		return this;
	}
	
	public TagTreeNodeBuilder withMyTag(Tag myTag) {
		this.myTag = myTag;
		return this;
	}
	
	public TagTreeNode build() {
		Preconditions.checkNotNull(nodeName);
		Preconditions.checkNotNull(parent);
		Preconditions.checkNotNull(myTag);
		
		return new TagTreeNode(children, parent, nodeName, myTag);
	}
}
