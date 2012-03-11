package org.safaproject.safa.web.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.safaproject.safa.model.tag.Tag;

public class SearchRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Tag> selectedTags = new ArrayList<Tag>();
	private Integer firstResult;
	private Integer maxResults;

	public SearchRequest() {

	}

	public List<Tag> getSelectedTags() {
		return selectedTags;
	}

	public void setSelectedTags(List<Tag> selectedTags) {
		this.selectedTags = selectedTags;
	}

	public Integer getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

}
