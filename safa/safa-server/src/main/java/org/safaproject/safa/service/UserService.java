package org.safaproject.safa.service;

import org.safaproject.safa.exception.UserRegistrationException;
import org.safaproject.safa.model.user.User;

public interface UserService {

	User findByLogin(String login);

	void registerUser(User user) throws UserRegistrationException;
}
