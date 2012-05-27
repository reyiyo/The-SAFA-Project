package org.safaproject.safa.web.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.safaproject.safa.dao.RoleDAO;
import org.safaproject.safa.dao.UserDAO;
import org.safaproject.safa.model.user.Role;
import org.safaproject.safa.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

@Transactional
public class SafaUserDetailsService implements UserDetailsService,
		AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

	private static final String USERNAME = "lastname";

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RoleDAO roleDAO;

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
				user.getUsername(), "", user.getIsLocked(),
				user.getIsLocked(), user.getIsLocked(), user.getIsLocked(),
				getGrantedAuthorities(user));
	}

	@Override
	public UserDetails loadUserDetails(OpenIDAuthenticationToken token)
			throws UsernameNotFoundException {

		String openIdUrl = token.getIdentityUrl();

		Map<String, OpenIDAttribute> attributes = Maps.uniqueIndex(
				token.getAttributes(),
				new OpenIdAttributesTransformerFunction());

		try {
			UserDetails user = loadUserByUsername(attributes.get(USERNAME)
					.getValues().get(0));
			return user;
		} catch (UsernameNotFoundException e) {
			return createNewOpenIdUser(openIdUrl, attributes);
		}

	}

	private UserDetails createNewOpenIdUser(final String openIdUrl,
			final Map<String, OpenIDAttribute> attributes) {

		User user = new User(openIdUrl, attributes.get(USERNAME).getValues()
				.get(0), "null@null.com", new HashSet<Role>(Arrays.asList(roleDAO
				.getDefaultRole())));

		userDAO.save(user);

		return createWebUser(user);

	}

	/**
	 * Wraps {@link org.safaproject.safa.model.user.Role} roles to
	 * {@link SimpleGrantedAuthority} objects
	 * 
	 * @param roleswith
	 *            {@link String} of roles
	 * @return list of granted authorities
	 */
	public static List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

}
