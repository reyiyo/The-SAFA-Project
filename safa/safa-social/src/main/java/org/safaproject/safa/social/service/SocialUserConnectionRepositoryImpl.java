package org.safaproject.safa.social.service;

import java.util.ArrayList;
import java.util.List;

import org.safaproject.safa.model.user.SocialUser;
import org.safaproject.safa.model.user.builder.SocialUserBuilder;
import org.safaproject.safa.social.dao.SocialUserDAO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.DuplicateConnectionException;
import org.springframework.social.connect.NoSuchConnectionException;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SuppressWarnings("unchecked")
public class SocialUserConnectionRepositoryImpl implements ConnectionRepository {

	private String userId;
	private SocialUserDAO socialUserDAO;
	private ConnectionFactoryLocator connectionFactoryLocator;
	private TextEncryptor textEncryptor;

	public SocialUserConnectionRepositoryImpl(String userId,
			SocialUserDAO socialUserDAO,
			ConnectionFactoryLocator connectionFactoryLocator,
			TextEncryptor textEncryptor) {
		this.userId = userId;
		this.socialUserDAO = socialUserDAO;
		this.connectionFactoryLocator = connectionFactoryLocator;
		this.textEncryptor = textEncryptor;
	}

	public MultiValueMap<String, Connection<?>> findAllConnections() {
		MultiValueMap<String, Connection<?>> connections = new LinkedMultiValueMap<String, Connection<?>>();

		List<SocialUser> socialUsers = socialUserDAO.findByUserId(userId);

		// TODO: Use guava
		for (SocialUser socialUser : socialUsers) {
			ConnectionData connectionData = toConnectionData(socialUser);
			Connection<?> connection = createConnection(connectionData);
			connections.add(connectionData.getProviderId(), connection);
		}

		return connections;
	}

	public List<Connection<?>> findConnections(String providerId) {
		List<Connection<?>> connections = new ArrayList<Connection<?>>();

		List<SocialUser> socialUsers = socialUserDAO.findByUserIdAndProviderId(
				userId, providerId);
		// TODO: Use guava
		for (SocialUser socialUser : socialUsers) {
			connections.add(createConnection(toConnectionData(socialUser)));
		}

		return connections;
	}

	public <A> List<Connection<A>> findConnections(Class<A> apiType) {
		String providerId = connectionFactoryLocator.getConnectionFactory(
				apiType).getProviderId();

		// do some lame stuff to make the casting possible
		List<?> connections = findConnections(providerId);
		return (List<Connection<A>>) connections;
	}

	public MultiValueMap<String, Connection<?>> findConnectionsToUsers(
			MultiValueMap<String, String> providerUserIds) {
		MultiValueMap<String, Connection<?>> connections = new LinkedMultiValueMap<String, Connection<?>>();

		List<SocialUser> socialUsers = socialUserDAO
				.findByUserIdAndProviderUserIds(userId, providerUserIds);

		// TODO: Use guava
		for (SocialUser socialUser : socialUsers) {
			ConnectionData connectionData = toConnectionData(socialUser);
			Connection<?> connection = createConnection(connectionData);
			connections.add(connectionData.getProviderId(), connection);
		}

		return connections;
	}

	public Connection<?> getConnection(ConnectionKey connectionKey) {
		SocialUser socialUser = socialUserDAO.get(userId,
				connectionKey.getProviderId(),
				connectionKey.getProviderUserId());
		if (socialUser == null) {
			throw new NoSuchConnectionException(connectionKey);
		}
		return createConnection(toConnectionData(socialUser));
	}

	public <A> Connection<A> getConnection(Class<A> apiType,
			String providerUserId) {
		String providerId = connectionFactoryLocator.getConnectionFactory(
				apiType).getProviderId();
		SocialUser socialUser = socialUserDAO.get(userId, providerId,
				providerUserId);
		if (socialUser == null) {
			throw new NoSuchConnectionException(new ConnectionKey(providerId,
					providerUserId));
		}
		return (Connection<A>) createConnection(toConnectionData(socialUser));
	}

	public <A> Connection<A> getPrimaryConnection(Class<A> apiType) {
		Connection<A> connection = findPrimaryConnection(apiType);
		if (connection == null) {
			String providerId = connectionFactoryLocator.getConnectionFactory(
					apiType).getProviderId();
			throw new NotConnectedException(providerId);
		}
		return connection;
	}

	public <A> Connection<A> findPrimaryConnection(Class<A> apiType) {
		String providerId = connectionFactoryLocator.getConnectionFactory(
				apiType).getProviderId();

		List<SocialUser> socialUsers = socialUserDAO
				.findPrimaryByUserIdAndProviderId(userId, providerId);
		Connection<A> connection = null;
		if (socialUsers != null && !socialUsers.isEmpty()) {
			connection = (Connection<A>) createConnection(toConnectionData(socialUsers
					.get(0)));
		}

		return connection;
	}

	@Transactional
	public void addConnection(Connection<?> connection) {
		ConnectionData connectionData = connection.createData();

		// check if this social account is already connected to a local account
		List<String> userIds = socialUserDAO
				.findUserIdsByProviderIdAndProviderUserId(
						connectionData.getProviderId(),
						connectionData.getProviderUserId());
		if (!userIds.isEmpty()) {
			throw new DuplicateConnectionException(new ConnectionKey(
					connectionData.getProviderId(),
					connectionData.getProviderUserId()));
		}
		// check if this user already has a connected account for this provider
		List<SocialUser> socialUsers = socialUserDAO.findByUserIdAndProviderId(
				userId, connectionData.getProviderId());
		if (!socialUsers.isEmpty()) {
			throw new DuplicateConnectionException(new ConnectionKey(
					connectionData.getProviderId(),
					connectionData.getProviderUserId()));
		}

		Integer maxRank = socialUserDAO.selectMaxRankByUserIdAndProviderId(
				userId, connectionData.getProviderId());
		int nextRank = (maxRank == null ? 1 : maxRank + 1);

		SocialUser socialUser = new SocialUserBuilder().withUserId(userId)
				.withProviderId(connectionData.getProviderId())
				.withProviderUserId(connectionData.getProviderUserId())
				.withRank(nextRank)
				.withDisplayName(connectionData.getDisplayName())
				.withProfileUrl(connectionData.getProfileUrl())
				.withImageUrl(connectionData.getImageUrl())
				.withAccessToken(encrypt(connectionData.getAccessToken()))
				.withSecret(encrypt(connectionData.getSecret()))
				.withRefreshToken(encrypt(connectionData.getRefreshToken()))
				.withExpireTime(connectionData.getExpireTime()).build();

		try {
			socialUserDAO.save(socialUser);
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateConnectionException(new ConnectionKey(
					connectionData.getProviderId(),
					connectionData.getProviderUserId()));
		}
	}

	public void updateConnection(Connection<?> connection) {
		ConnectionData connectionData = connection.createData();
		SocialUser socialUser = socialUserDAO.get(userId,
				connectionData.getProviderId(),
				connectionData.getProviderUserId());
		if (socialUser != null) {
			socialUser.setDisplayName(connectionData.getDisplayName());
			socialUser.setProfileUrl(connectionData.getProfileUrl());
			socialUser.setImageUrl(connectionData.getImageUrl());

			socialUser.setAccessToken(encrypt(connectionData.getAccessToken()));
			socialUser.setSecret(encrypt(connectionData.getSecret()));
			socialUser
					.setRefreshToken(encrypt(connectionData.getRefreshToken()));

			socialUser.setExpireTime(connectionData.getExpireTime());
			socialUserDAO.update(socialUser);
		}
	}

	public void removeConnections(String providerId) {
		List<SocialUser> socialUsers = socialUserDAO.findByUserIdAndProviderId(
				userId, providerId);
		for (SocialUser socialUser : socialUsers) {
			socialUserDAO.delete(socialUser);
		}
	}

	public void removeConnection(ConnectionKey connectionKey) {
		SocialUser socialUser = socialUserDAO.get(userId,
				connectionKey.getProviderId(),
				connectionKey.getProviderUserId());
		if (socialUser != null) {
			socialUserDAO.delete(socialUser);
		}
	}

	private ConnectionData toConnectionData(SocialUser socialUser) {
		return new ConnectionData(socialUser.getProviderId(),
				socialUser.getProviderUserId(), socialUser.getDisplayName(),
				socialUser.getProfileUrl(), socialUser.getImageUrl(),

				decrypt(socialUser.getAccessToken()),
				decrypt(socialUser.getSecret()),
				decrypt(socialUser.getRefreshToken()),

				convertZeroToNull(socialUser.getExpireTime()));
	}

	private Connection<?> createConnection(ConnectionData connectionData) {
		ConnectionFactory<?> connectionFactory = connectionFactoryLocator
				.getConnectionFactory(connectionData.getProviderId());
		return connectionFactory.createConnection(connectionData);
	}

	private Long convertZeroToNull(Long expireTime) {
		return (expireTime != null && expireTime == 0 ? null : expireTime);
	}

	private String decrypt(String encryptedText) {
		return (textEncryptor != null && encryptedText != null) ? textEncryptor
				.decrypt(encryptedText) : encryptedText;
	}

	private String encrypt(String text) {
		return (textEncryptor != null && text != null) ? textEncryptor
				.encrypt(text) : text;
	}
}
