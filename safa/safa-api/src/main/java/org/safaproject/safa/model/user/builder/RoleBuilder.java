package org.safaproject.safa.model.user.builder;

import org.safaproject.safa.model.user.Role;
import org.safaproject.safa.model.user.Roles;

import com.google.common.base.Preconditions;

public class RoleBuilder {
	
	private Roles name;
	
	public RoleBuilder withName(Roles name) {
		this.name = name;
		return this;
	}
	
	public Role build() {
		Preconditions.checkNotNull(name);
		return new Role(name);
	}
}
