package org.safaproject.safa.model.tag.builder;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.safaproject.safa.model.tag.Tag;
import org.safaproject.safa.model.tag.TagTreeNode;

public class TagTreeNodeBuilderTest {

	@Test
	public void shallBuild() {
		List<TagTreeNode> children = new ArrayList<TagTreeNode>();
		TagTreeNode parent = new TagTreeNode();
		Tag myTag = new Tag();
		
		TagTreeNode tagTreeNode = new TagTreeNodeBuilder()
				.withNodeName("Node")
				.withChildren(children)
				.withParent(parent)
				.withMyTag(myTag)
				.build();
		
		Assert.assertEquals(children, tagTreeNode.getChildren());
		Assert.assertEquals(parent, tagTreeNode.getParent());
		Assert.assertEquals(myTag, tagTreeNode.getMyTag());
		Assert.assertEquals("Node", tagTreeNode.getNodeName());
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullNodeName() {
		new TagTreeNodeBuilder()
				.withChildren(new ArrayList<TagTreeNode>())
				.withParent(new TagTreeNode())
				.withMyTag(new Tag())
				.build();
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullParent() {
		new TagTreeNodeBuilder()
				.withNodeName("Node")
				.withChildren(new ArrayList<TagTreeNode>())
				.withMyTag(new Tag())
				.build();
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullTag() {
		new TagTreeNodeBuilder()
				.withNodeName("Node")
				.withChildren(new ArrayList<TagTreeNode>())
				.withParent(new TagTreeNode())
				.build();
	}
}
