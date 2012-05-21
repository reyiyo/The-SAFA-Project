package org.safaproject.safa.social.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.safaproject.safa.dao.SocialUserDAO;
import org.safaproject.safa.model.user.SocialUser;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;

public class SocialUserConnectionDAO implements UsersConnectionRepository {

	@Inject
	private SocialUserDAO socialUserDAO;

	private final ConnectionFactoryLocator connectionFactoryLocator;

	private final TextEncryptor textEncryptor;

	private ConnectionSignUp connectionSignUp;

	public SocialUserConnectionDAO(
			ConnectionFactoryLocator connectionFactoryLocator,
			TextEncryptor textEncryptor) {
		this.connectionFactoryLocator = connectionFactoryLocator;
		this.textEncryptor = textEncryptor;
	}

	@Override
	public ConnectionRepository createConnectionRepository(String userId) {
		Preconditions.checkArgument(userId != null);
		return new ConnectionDAO(userId, connectionFactoryLocator,
				textEncryptor);
	}

	@Override
	public Set<String> findUserIdsConnectedTo(String providerId,
			Set<String> providerUserIds) {

		return Sets.newHashSet(Collections2.transform(socialUserDAO
				.getSocialUserCriteriaBuilder().withProviderId(providerId)
				.withProviderUserIds(providerUserIds).list(),
				new SocialUserToId()));
	}

	@Override
	public List<String> findUserIdsWithConnection(Connection<?> connection) {
		List<String> usrs = new ArrayList<String>();
		ConnectionKey key = connection.getKey();

		List<SocialUser> users = socialUserDAO.getSocialUserCriteriaBuilder()
				.withProviderId(key.getProviderId())
				.withProviderUserId(key.getProviderUserId()).list();

		if (!users.isEmpty()) {
			for (SocialUser user : users) {
				usrs.add(user.getUserId());
			}
			return usrs;
		}

		if (connectionSignUp != null) {
			String newUserId = connectionSignUp.execute(connection);
			if (newUserId == null)
				// auto signup failed, so we need to go to a sign up form
				return usrs;
			createConnectionRepository(newUserId).addConnection(connection);
			usrs.add(newUserId);
		}
		// if empty we should go to the sign up form
		return usrs;
	}

	public void setSocialUserDAO(SocialUserDAO socialUserDAO) {
		this.socialUserDAO = socialUserDAO;
	}

	public ConnectionFactoryLocator getConnectionFactoryLocator() {
		return connectionFactoryLocator;
	}

	/**
	 * The command to execute to create a new local user profile in the event no
	 * user id could be mapped to a connection. Allows for implicitly creating a
	 * user profile from connection data during a provider sign-in attempt.
	 * Defaults to null, indicating explicit sign-up will be required to
	 * complete the provider sign-in attempt.
	 * 
	 * @see #findUserIdWithConnection(Connection)
	 */
	public void setConnectionSignUp(ConnectionSignUp connectionSignUp) {
		this.connectionSignUp = connectionSignUp;
	}

	private class SocialUserToId implements Function<SocialUser, String> {

		@Override
		public String apply(SocialUser user) {
			return user.getUserId();
		}

	}

}
