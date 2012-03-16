package org.safaproject.safa.model.user.builder;

import java.util.HashSet;
import java.util.Set;

import org.safaproject.safa.model.user.Role;
import org.safaproject.safa.model.user.User;
import org.safaproject.safa.model.user.UserProfile;

import com.google.common.base.Preconditions;

public class UserBuilder {

	private User user;
	
	private String openIDurlToken;

	private String username;

	private String password;

	private String email;

	private Set<Role> roles = new HashSet<Role>();

	private UserProfile profile;

	private Boolean isLocked = false;
	
	public UserBuilder withOpenIDurlToken(String token) {
		this.openIDurlToken = token;
		return this;
	}
	public UserBuilder withUsername(String username) {
		this.username = username;
		return this;
	}
	public UserBuilder withPassword(String password) {
		this.password = password;
		return this;
	}
	public UserBuilder withEmail(String email) {
		this.email = email;
		return this;
	}
	public UserBuilder withRoles(Set<Role> roles) {
		this.roles = roles;
		return this;
	}
	public UserBuilder withProfile(UserProfile profile) {
		this.profile = profile;
		return this;
	}
	public UserBuilder withLocked(boolean locked) {
		this.isLocked = locked;
		return this;
	}
	
	public User build(){
		Preconditions.checkNotNull(openIDurlToken);
		Preconditions.checkNotNull(username);
		Preconditions.checkNotNull(email);
		
		this.user = new User(this.openIDurlToken, this.username, this.email, this.roles);
		this.user.setIsLocked(isLocked);
		this.user.setPassword(password);
		this.user.setProfile(profile);
		return this.user;
	}
}
