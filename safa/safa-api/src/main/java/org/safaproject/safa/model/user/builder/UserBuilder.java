package org.safaproject.safa.model.user.builder;

import java.util.HashSet;
import java.util.Set;

import org.safaproject.safa.model.user.Rol;
import org.safaproject.safa.model.user.User;
import org.safaproject.safa.model.user.UserProfile;

public class UserBuilder {

	private User user;
	
	private String openIDurlToken;

	private String username;

	private String password;

	private String email;

	private Set<Rol> rols = new HashSet<Rol>();

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
	public UserBuilder withRols(Set<Rol> rols) {
		this.rols = rols;
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
		this.user = new User(this.openIDurlToken, this.username, this.email, this.rols);
		this.user.setIsLocked(isLocked);
		this.user.setPassword(password);
		this.user.setProfile(profile);
		return this.user;
	}
}
