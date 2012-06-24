package org.safaproject.safa.web.security;

import java.util.ArrayList;
import java.util.List;

import org.safaproject.safa.model.user.Role;
import org.safaproject.safa.model.user.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Utility methods for working with Spring Security.
 */
public class SecurityUtil {

	public static User getLoggedInUser() {
		User user = null;
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication auth;
		if (securityContext != null) {
			auth = securityContext.getAuthentication();
			if (auth != null) {
				Object principal = auth.getPrincipal();
				if (principal instanceof UserDetailsImpl) {
					UserDetailsImpl authUser = (UserDetailsImpl) principal;
					user = authUser.getUser();
				}
			}
		}
		return user;
	}

	public static Authentication signInUser(User user) {
		List<GrantedAuthority> roles = getRoles(user);
		UserDetailsImpl springSecurityUser = new UserDetailsImpl(user);
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				springSecurityUser, "", roles);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return authentication;
	}

	public static List<GrantedAuthority> getRoles(User user) {
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		for (Role role : user.getRoles()) {
			roles.add(new SimpleGrantedAuthority(role.getName().name()));
		}
		return roles;
	}
}
