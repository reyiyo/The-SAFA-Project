package org.safaproject.safa.model;

public enum TagDataTypeEnum {

	// FIXME: Evaluate if is convenient to change this to a db table instead of
	// an enum, to have more configuration flexibility

	STRING("String"), NUMERIC("Numeric"), DATE("Date");

	private String type;

	TagDataTypeEnum(final String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

}
