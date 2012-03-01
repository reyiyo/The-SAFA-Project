package org.safaproject.safa.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * This class ...
 * 
 * @author cutrix2k
 * 
 */

// TODO: review needed for this class by cutrix2k
@Entity
@Table(name = "TAG_TREE_NODE")
public class TagTreeNode {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tagTreeNodeId")
	private Long tagTreeNodeId;

	@OneToMany
	@JoinColumn(name = "childId")
	private List<TagTreeNode> childs;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	private TagTreeNode parent;

	@NotNull
	private String nodeName;

	// TODO: Document this field...
	@ManyToOne
	@NotNull
	private Tag myTag;

	/**
	 * @return the tagTreeNodeId
	 */
	public Long getTagTreeNodeId() {
		return tagTreeNodeId;
	}

	/**
	 * @param tagTreeNodeId
	 *            the tagTreeNodeId to set
	 */
	public void setTagTreeNodeId(Long tagTreeNodeId) {
		this.tagTreeNodeId = tagTreeNodeId;
	}

	/**
	 * @return the childs
	 */
	public List<TagTreeNode> getChilds() {
		return childs;
	}

	/**
	 * @param childs
	 *            the childs to set
	 */
	public void setChilds(List<TagTreeNode> childs) {
		this.childs = childs;
	}

	/**
	 * @return the parent
	 */
	public TagTreeNode getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(TagTreeNode parent) {
		this.parent = parent;
	}

	/**
	 * @return the nodeName
	 */
	public String getNodeName() {
		return nodeName;
	}

	/**
	 * @param nodeName
	 *            the nodeName to set
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	/**
	 * @return the myTag
	 */
	public Tag getMyTag() {
		return myTag;
	}

	/**
	 * @param myTag
	 *            the myTag to set
	 */
	public void setMyTag(Tag myTag) {
		this.myTag = myTag;
	}

}
