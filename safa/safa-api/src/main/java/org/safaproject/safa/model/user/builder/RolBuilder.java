package org.safaproject.safa.model.user.builder;

import org.safaproject.safa.model.user.Rol;

import com.google.common.base.Preconditions;

public class RolBuilder {
	
	private String name;
	
	public RolBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public Rol build() {
		Preconditions.checkNotNull(name);
		return new Rol(name);
	}
}
