package org.safaproject.safa.web.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.safaproject.safa.model.user.Role;
import org.safaproject.safa.model.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Wraps {@link org.safaproject.safa.model.user.User} roles to
 * {@link org.springframework.security.core.userdetails.UserDetails} objects
 */
public class UserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6705805063637482154L;

	private User user;

	public UserDetailsImpl(User user) {
		this.user = user;
	}

	/**
	 * Wraps {@link org.safaproject.safa.model.user.Role} roles to
	 * {@link SimpleGrantedAuthority} objects
	 * 
	 * @param roleswith
	 *            {@link String} of roles
	 * @return list of granted authorities
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.getIsLocked();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.getIsLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.getIsLocked();
	}

	@Override
	public boolean isEnabled() {
		return user.getIsLocked();
	}

	public User getUser() {
		return user;
	}
}
