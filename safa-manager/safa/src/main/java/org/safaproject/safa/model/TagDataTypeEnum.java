package org.safaproject.safa.model;


public enum TagDataTypeEnum {
	
	STRING("String"), NUMERIC("Numeric"), DATE("Date");
	
	private String type;

	TagDataTypeEnum(final String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

}
