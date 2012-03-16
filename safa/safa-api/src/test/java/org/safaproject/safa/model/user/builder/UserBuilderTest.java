package org.safaproject.safa.model.user.builder;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.safaproject.safa.model.user.Role;
import org.safaproject.safa.model.user.User;
import org.safaproject.safa.model.user.UserProfile;

public class UserBuilderTest {

	@Test
	public void shallBuild() {
		UserProfile profile = new UserProfile();
		Set<Role> roles = new HashSet<Role>();
		
		User user = new UserBuilder()
				.withEmail("test@test.com")
				.withLocked(true)
				.withOpenIDurlToken("1234asd")
				.withPassword("password")
				.withProfile(profile)
				.withRoles(roles)
				.withUsername("username")
				.build();
		
		Assert.assertEquals("test@test.com", user.getEmail());
		Assert.assertEquals(Boolean.TRUE, user.getIsLocked());
		Assert.assertEquals("1234asd", user.getOpenIDurlToken());
		Assert.assertEquals("password", user.getPassword());
		Assert.assertEquals(profile, user.getProfile());
		Assert.assertEquals(roles, user.getRoles());
		Assert.assertEquals("username", user.getUsername());
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullOpenIDurlToken() {
		new UserBuilder()
			.withEmail("test@test.com")
			.withLocked(true)
			.withPassword("password")
			.withProfile(new UserProfile())
			.withRoles(new HashSet<Role>())
			.withUsername("username")
			.build();
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullUsername() {
		new UserBuilder()
			.withEmail("test@test.com")
			.withLocked(true)
			.withOpenIDurlToken("1234asd")
			.withPassword("password")
			.withProfile(new UserProfile())
			.withRoles(new HashSet<Role>())
			.build();
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullEmail() {
		new UserBuilder()
			.withLocked(true)
			.withOpenIDurlToken("1234asd")
			.withPassword("password")
			.withProfile(new UserProfile())
			.withRoles(new HashSet<Role>())
			.withUsername("username")
			.build();
	}
}
