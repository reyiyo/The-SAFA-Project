package org.safaproject.safa.model.user.builder;

import junit.framework.Assert;

import org.junit.Test;
import org.safaproject.safa.model.user.Role;

public class RoleBuilderTest {

	@Test
	public void shallBuild() {
		Role role = new RoleBuilder().withName("Role").build();
		
		Assert.assertEquals("Role", role.getName());
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullName() {
		new RoleBuilder().build();
	}
}
