package org.safaproject.safa.social.service;

import java.util.List;

import org.safaproject.safa.model.user.SocialUser;
import org.safaproject.safa.social.exception.UserNotFoundException;
import org.springframework.social.connect.Connection;

public interface SocialUserService {

	SocialUser getUserByUserIdAndConnection(String userId,
			Connection<?> connection) throws UserNotFoundException;

	List<SocialUser> findUsersWithConnection(Connection<?> connection);

	SocialUser createUserWithConnection(Connection<?> connection);

}
