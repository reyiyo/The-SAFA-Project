package org.safaproject.safa.api.request;

import java.io.Serializable;
import java.util.Set;

import org.safaproject.safa.node.dto.TagDTO;
import org.safaproject.safa.node.dto.TagTypeDTO;

public class FilterTagsRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private TagTypeDTO tagType;
	private Set<TagDTO> selectedTags;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getTagType().toString());
		sb.append(" - ");
		for (TagDTO tag : this.getSelectedTags()) {
			sb.append(tag.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	public TagTypeDTO getTagType() {
		return tagType;
	}

	public void setTagType(TagTypeDTO tagType) {
		this.tagType = tagType;
	}

	public Set<TagDTO> getSelectedTags() {
		return selectedTags;
	}

	public void setSelectedTags(Set<TagDTO> selectedTags) {
		this.selectedTags = selectedTags;
	}

}
