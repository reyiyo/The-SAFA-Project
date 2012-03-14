package org.safaproject.safa.model.content;

public enum OrderDirections {

	ASC(false), DESC(true);

	private Boolean order;

	OrderDirections(String order) {
		this.order = true;

		if ("ASC".equals(order)) {
			this.order = false;
		}
	}

	OrderDirections(Boolean order) {
		this.order = order;
	}

	public Boolean getOrder() {
		return this.order;
	}

}
