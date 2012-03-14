package org.safaproject.safa.model.content;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.safaproject.safa.model.tag.Tag;

public class SearchRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Tag> selectedTags = new ArrayList<Tag>();
	private Integer firstResult;
	private Integer maxResults;
	private String orderBy;
	private OrderDirections orderDirection;

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

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public OrderDirections getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(OrderDirections orderDirection) {
		this.orderDirection = orderDirection;
	}

}
