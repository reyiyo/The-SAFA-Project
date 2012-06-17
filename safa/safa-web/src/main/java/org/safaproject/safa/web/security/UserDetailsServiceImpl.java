package org.safaproject.safa.web.security;

import org.safaproject.safa.dao.UserDAO;
import org.safaproject.safa.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User domainUser = userDAO.findByUsername(username);
		if (domainUser == null) {
			throw new UsernameNotFoundException(
					"Could not find user with name '" + username + "'");
		}
		return new UserDetailsImpl(domainUser);
	}
}
