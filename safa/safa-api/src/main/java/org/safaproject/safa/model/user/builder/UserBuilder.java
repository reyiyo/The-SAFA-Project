package org.safaproject.safa.model.user.builder;

import java.util.HashSet;
import java.util.Set;

import org.safaproject.safa.model.user.Role;
import org.safaproject.safa.model.user.SocialUser;
import org.safaproject.safa.model.user.User;

import com.google.common.base.Preconditions;

public class UserBuilder {

	private String username;

	private Set<Role> roles = new HashSet<Role>();

	private Boolean isLocked = false;

	private Set<SocialUser> connectedSocialProfiles;

	private String password;

	public UserBuilder withUsername(String username) {
		this.username = username;
		return this;
	}

	public UserBuilder withRoles(Set<Role> roles) {
		this.roles = roles;
		return this;
	}

	public UserBuilder withLocked(boolean locked) {
		this.isLocked = locked;
		return this;
	}

	public UserBuilder withSocialUsers(Set<SocialUser> socialUsers) {
		this.connectedSocialProfiles = socialUsers;
		return this;
	}

	public UserBuilder withPassword(String password) {
		this.password = password;
		return this;
	}

	public User build() {
		Preconditions.checkNotNull(username);

		return new User(username, password, roles, isLocked,
				connectedSocialProfiles);
	}
}
