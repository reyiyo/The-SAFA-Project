package org.safaproject.safa.model.user;

public enum Roles {

	USER("ROLE_USER"), ADMIN("ROLE_ADMIN"), MOD("ROLE_MOD");

	private String value;

	Roles(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
