package org.safaproject.safa.social.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.safaproject.safa.dao.SocialUserDAO;
import org.safaproject.safa.model.user.SocialUser;
import org.safaproject.safa.social.exception.UserNotFoundException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.stereotype.Service;

@Service("socialUserService")
public class SocialUserServiceImpl implements SocialUserService {

	@Inject
	private SocialUserDAO socialUserDAO;

	@Override
	public SocialUser getUserByUserIdAndConnection(String userId,
			Connection<?> connection) throws UserNotFoundException {

		ConnectionData data = connection.createData();

		try {
			return socialUserDAO.getSocialUserCriteriaBuilder()
					.withUserId(userId)
					.withProviderUserId(data.getProviderId())
					.withProviderUserId(data.getProviderUserId())
					.getSingleResult();
		} catch (NoResultException e) {
			throw new UserNotFoundException("Username with id: "
					+ data.getProviderUserId() + " from provider: "
					+ data.getProviderId() + " was not found in the system.", e);
		}
	}

	@Override
	public List<SocialUser> findUsersWithConnection(Connection<?> connection) {
		ConnectionData data = connection.createData();
		return socialUserDAO.getSocialUserCriteriaBuilder()
				.withProviderId(data.getProviderId())
				.withProviderUserId(data.getProviderUserId()).list();
	}

	public void setSocialUserDAO(SocialUserDAO socialUserDAO) {
		this.socialUserDAO = socialUserDAO;
	}

	@Override
	public SocialUser createUserWithConnection(Connection<?> connection) {
		// TODO Auto-generated method stub

		// TODO: Implement here the logic to create a new user
		// Investigate about the data given by each provider and see if it is
		// viable to implement the data dynamically with tags in the UserProfile
		return null;
	}

}
