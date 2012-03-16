package org.safaproject.safa.model.user.builder;

import org.safaproject.safa.model.user.Role;

import com.google.common.base.Preconditions;

public class RoleBuilder {
	
	private String name;
	
	public RoleBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public Role build() {
		Preconditions.checkNotNull(name);
		return new Role(name);
	}
}
