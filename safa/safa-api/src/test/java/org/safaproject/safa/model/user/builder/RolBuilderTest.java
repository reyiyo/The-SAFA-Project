package org.safaproject.safa.model.user.builder;

import junit.framework.Assert;

import org.junit.Test;
import org.safaproject.safa.model.user.Rol;

public class RolBuilderTest {

	@Test
	public void shallBuild() {
		Rol rol = new RolBuilder().withName("Rol").build();
		
		Assert.assertEquals("Rol", rol.getName());
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullName() {
		new RolBuilder().build();
	}
}
