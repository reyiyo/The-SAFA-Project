package org.safaproject.safa.api.request;

import java.io.Serializable;
import java.util.List;

import org.safaproject.safa.model.tag.Tag;
import org.safaproject.safa.model.tag.TagType;

public class FilterTagsRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private TagType tagType;
	private List<Tag> selectedTags;
	private String value;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getTagType().toString());
		sb.append(" - ");
		for (Tag tag : this.getSelectedTags()) {
			sb.append(tag.toString());
			sb.append("\n");
		}
		sb.append("filterValue: " + this.getValue());
		return sb.toString();		
	}

	public TagType getTagType() {
		return tagType;
	}

	public void setTagType(TagType tagType) {
		this.tagType = tagType;
	}

	public List<Tag> getSelectedTags() {
		return selectedTags;
	}

	public void setSelectedTags(List<Tag> selectedTags) {
		this.selectedTags = selectedTags;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
