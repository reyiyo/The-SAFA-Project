package org.safaproject.safa.service;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.safaproject.safa.dao.RoleDAO;
import org.safaproject.safa.dao.UserDAO;
import org.safaproject.safa.exception.UserRegistrationException;
import org.safaproject.safa.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private RoleDAO roleDAO;

	private static Logger logger = Logger.getLogger("UserService");

	public User findByLogin(String login) {
		return userDAO.findByUsername(login);
	}

	public void registerUser(User user) throws UserRegistrationException {

		Preconditions.checkArgument(!Strings.isNullOrEmpty(user.getUsername()),
				"Username cannot be empty");

		user.setRoles(Sets.newHashSet(roleDAO.getDefaultRole()));

		if (user.getPassword() == null) {
			String password = generatePassword();
			user.setPassword(password);
		}

		try {
			userDAO.save(user);
		} catch (PersistenceException e) {
			logger.error("An error occurred while trying to register a user", e);
			throw new UserRegistrationException(e);
		}
	}

	private static final String RANDOM_PASSWORD_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890-_!$*";

	private static final int RANDOM_PASSWORD_LENGTH = 12;

	private String generatePassword() {
		StringBuilder password = new StringBuilder();
		for (int i = 0; i < RANDOM_PASSWORD_LENGTH; i++) {
			int charIndex = (int) (Math.random() * RANDOM_PASSWORD_CHARS
					.length());
			char randomChar = RANDOM_PASSWORD_CHARS.charAt(charIndex);
			password.append(randomChar);
		}
		return password.toString();
	}
}
