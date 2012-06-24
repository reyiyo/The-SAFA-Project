package org.safaproject.safa.model.user.builder;

import junit.framework.Assert;

import org.junit.Test;
import org.safaproject.safa.model.user.Role;
import org.safaproject.safa.model.user.Roles;

public class RoleBuilderTest {

	@Test
	public void shallBuild() {
		for(Roles aRole: Roles.values()){
			Role role = new RoleBuilder().withName(aRole).build();
			
			Assert.assertEquals(aRole, role.getName());
		}
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullName() {
		new RoleBuilder().build();
	}
}
