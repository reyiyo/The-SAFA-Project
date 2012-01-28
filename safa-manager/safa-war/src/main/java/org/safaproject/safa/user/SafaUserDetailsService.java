package org.safaproject.safa.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;

import org.safaproject.safa.dao.UserDAO;
import org.safaproject.safa.model.Rol;
import org.safaproject.safa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;

public class SafaUserDetailsService implements UserDetailsService,
		AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		User user = null;
		try {
			user = userDAO.findByUsername(username);
		} catch (NoResultException e) {
			throw new UsernameNotFoundException("The user with username "
					+ username + " was not Found.");
		}
		return createWebUser(user);
	}

	private UserDetails createWebUser(User user) {
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), user.getIsLocked(),
				user.getIsLocked(), user.getIsLocked(), user.getIsLocked(),
				getGrantedAuthorities(user));
	}

	@Override
	public UserDetails loadUserDetails(OpenIDAuthenticationToken arg0)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Wraps {@link org.safaproject.safa.model.Rol} roles to
	 * {@link SimpleGrantedAuthority} objects
	 * 
	 * @param roleswith
	 *            {@link String} of roles
	 * @return list of granted authorities
	 */
	public static List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Rol role : user.getRols()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

}
