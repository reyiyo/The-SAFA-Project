package org.safaproject.safa.model.user.builder;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.safaproject.safa.model.user.Role;
import org.safaproject.safa.model.user.SocialUser;
import org.safaproject.safa.model.user.User;

public class UserBuilderTest {

	@Test
	public void shallBuild() {
		Set<Role> roles = new HashSet<Role>();
		Set<SocialUser> socialUsers = new HashSet<SocialUser>();

		User user = new UserBuilder().withLocked(true).withRoles(roles)
				.withUsername("username").withSocialUsers(socialUsers).build();

		Assert.assertEquals(Boolean.TRUE, user.getIsLocked());
		Assert.assertEquals(roles, user.getRoles());
		Assert.assertEquals("username", user.getUsername());
		Assert.assertEquals(socialUsers, user.getConnectedSocialProfiles());
	}

	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullUsername() {
		new UserBuilder().withLocked(true).withRoles(new HashSet<Role>())
				.build();
	}
}
