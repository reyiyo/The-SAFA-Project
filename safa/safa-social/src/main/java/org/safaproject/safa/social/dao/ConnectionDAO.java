package org.safaproject.safa.social.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.safaproject.safa.dao.RoleDAO;
import org.safaproject.safa.dao.SocialUserDAO;
import org.safaproject.safa.model.user.SocialUser;
import org.safaproject.safa.model.user.builder.SocialUserBuilder;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.google.common.collect.Sets;

public class ConnectionDAO implements ConnectionRepository {

	@Inject
	private SocialUserDAO socialUserDAO;

	@Inject
	private RoleDAO roleDAO;

	private final String userId;

	private final ConnectionFactoryLocator connectionFactoryLocator;

	private final TextEncryptor textEncryptor;

	private final ServiceProviderConnectionMapper connectionMapper = new ServiceProviderConnectionMapper();

	public ConnectionDAO(String userId,
			ConnectionFactoryLocator connectionFactoryLocator,
			TextEncryptor textEncryptor) {
		this.userId = userId;
		this.connectionFactoryLocator = connectionFactoryLocator;
		this.textEncryptor = textEncryptor;
	}

	@Override
	public void addConnection(Connection<?> connection) {
		try {
			ConnectionData data = connection.createData();
			int rank = socialUserDAO.getSocialUserCriteriaBuilder()
					.withUserId(userId).withProviderId(data.getProviderId())
					.selectMaxRank().intValue() + 1;

			socialUserDAO.save(new SocialUserBuilder().withUserId(userId)
					.withProviderId(data.getProviderId())
					.withProviderUserId(data.getProviderUserId())
					.withRank(rank).withDisplayName(data.getDisplayName())
					.withProfileUrl(data.getProfileUrl())
					.withImageUrl(data.getImageUrl())
					.withAccessToken(encrypt(data.getAccessToken()))
					.withSecret(encrypt(data.getSecret()))
					.withRefreshToken(encrypt(data.getRefreshToken()))
					.withExpireTime(data.getExpireTime())
					.withRoles(Sets.newHashSet(roleDAO.getDefaultRole()))
					.withLocked(false).build());

		} catch (PersistenceException e) {
			throw new DuplicateConnectionException(connection.getKey());
		}

	}

	@Override
	public MultiValueMap<String, Connection<?>> findAllConnections() {
		List<Connection<?>> resultList = connectionMapper
				.mapEntities(socialUserDAO.getSocialUserCriteriaBuilder()
						.withUserId(userId).list());

		MultiValueMap<String, Connection<?>> connections = new LinkedMultiValueMap<String, Connection<?>>();
		Set<String> registeredProviderIds = connectionFactoryLocator
				.registeredProviderIds();
		for (String registeredProviderId : registeredProviderIds) {
			connections.put(registeredProviderId,
					Collections.<Connection<?>> emptyList());
		}
		for (Connection<?> connection : resultList) {
			String providerId = connection.getKey().getProviderId();
			if (connections.get(providerId).size() == 0) {
				connections.put(providerId, new LinkedList<Connection<?>>());
			}
			connections.add(providerId, connection);
		}
		return connections;
	}

	@Override
	public List<Connection<?>> findConnections(String providerId) {
		return connectionMapper.mapEntities(socialUserDAO
				.getSocialUserCriteriaBuilder().withUserId(userId)
				.withProviderId(providerId).list());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A> List<Connection<A>> findConnections(Class<A> apiType) {
		List<?> connections = findConnections(getProviderId(apiType));
		return (List<Connection<A>>) connections;
	}

	@Override
	public MultiValueMap<String, Connection<?>> findConnectionsToUsers(
			MultiValueMap<String, String> providerUsers) {
		if (providerUsers.isEmpty()) {
			throw new IllegalArgumentException(
					"Unable to execute find: no providerUsers provided");
		}

		List<Connection<?>> resultList = connectionMapper
				.mapEntities(socialUserDAO.getSocialUserCriteriaBuilder()
						.listUsersWithProviderUsers(userId, providerUsers));

		MultiValueMap<String, Connection<?>> connectionsForUsers = new LinkedMultiValueMap<String, Connection<?>>();
		for (Connection<?> connection : resultList) {
			String providerId = connection.getKey().getProviderId();
			List<String> userIds = providerUsers.get(providerId);
			List<Connection<?>> connections = connectionsForUsers
					.get(providerId);
			if (connections == null) {
				connections = new ArrayList<Connection<?>>(userIds.size());
				for (int i = 0; i < userIds.size(); i++) {
					connections.add(null);
				}
				connectionsForUsers.put(providerId, connections);
			}
			int connectionIndex = userIds.indexOf(connection.getKey()
					.getProviderUserId());
			connections.set(connectionIndex, connection);
		}
		return connectionsForUsers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A> Connection<A> findPrimaryConnection(Class<A> apiType) {
		String providerId = getProviderId(apiType);
		return (Connection<A>) findPrimaryConnection(providerId);
	}

	@Override
	public Connection<?> getConnection(ConnectionKey connectionKey) {
		Connection<?> connection = connectionMapper.mapEntity(socialUserDAO
				.getSocialUserCriteriaBuilder().withUserId(userId)
				.withProviderId(connectionKey.getProviderId())
				.withProviderUserId(connectionKey.getProviderUserId())
				.getSingleResult());

		if (connection == null)
			throw new NoSuchConnectionException(connectionKey);
		return connection;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A> Connection<A> getConnection(Class<A> apiType,
			String providerUserId) {
		String providerId = getProviderId(apiType);
		return (Connection<A>) getConnection(new ConnectionKey(providerId,
				providerUserId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A> Connection<A> getPrimaryConnection(Class<A> apiType) {
		String providerId = getProviderId(apiType);
		Connection<A> connection = (Connection<A>) findPrimaryConnection(providerId);
		if (connection == null) {
			throw new NotConnectedException(providerId);
		}
		return connection;
	}

	@Override
	public void removeConnection(ConnectionKey connectionKey) {
		List<SocialUser> connections = socialUserDAO
				.getSocialUserCriteriaBuilder().withUserId(userId)
				.withProviderId(connectionKey.getProviderId())
				.withProviderUserId(connectionKey.getProviderUserId()).list();

		for (SocialUser socialUser : connections) {
			socialUserDAO.delete(socialUser);
		}

	}

	@Override
	public void removeConnections(String providerId) {
		List<SocialUser> connections = socialUserDAO
				.getSocialUserCriteriaBuilder().withUserId(userId)
				.withProviderId(providerId).list();

		for (SocialUser socialUser : connections) {
			socialUserDAO.delete(socialUser);
		}

	}

	@Override
	public void updateConnection(Connection<?> connection) {
		ConnectionData data = connection.createData();

		SocialUser socialUser = socialUserDAO.getSocialUserCriteriaBuilder()
				.withUserId(userId).withProviderId(data.getProviderId())
				.withProviderUserId(data.getProviderUserId()).getSingleResult();
		if (socialUser != null) {
			socialUser.setDisplayName(data.getDisplayName());
			socialUser.setProfileUrl(data.getProfileUrl());
			socialUser.setImageUrl(data.getImageUrl());
			socialUser.setAccessToken(encrypt(data.getAccessToken()));
			socialUser.setSecret(encrypt(data.getSecret()));
			socialUser.setRefreshToken(encrypt(data.getRefreshToken()));
			socialUser.setExpireTime(data.getExpireTime());

			socialUser = socialUserDAO.update(socialUser);
		}

	}

	private <A> String getProviderId(Class<A> apiType) {
		return connectionFactoryLocator.getConnectionFactory(apiType)
				.getProviderId();
	}

	private Connection<?> findPrimaryConnection(String providerId) {
		List<Connection<?>> connections = connectionMapper
				.mapEntities(socialUserDAO.getSocialUserCriteriaBuilder()
						.withUserId(userId).withProviderId(providerId).list());
		if (connections.size() > 0) {
			return connections.get(0);
		} else {
			return null;
		}
	}

	private String encrypt(String text) {
		return text != null ? textEncryptor.encrypt(text) : text;
	}

	public void setSocialUserDAO(SocialUserDAO socialUserDAO) {
		this.socialUserDAO = socialUserDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	private final class ServiceProviderConnectionMapper {

		public List<Connection<?>> mapEntities(List<SocialUser> socialUsers) {
			List<Connection<?>> result = new ArrayList<Connection<?>>();
			for (SocialUser su : socialUsers) {
				result.add(mapEntity(su));
			}
			return result;
		}

		public Connection<?> mapEntity(SocialUser socialUser) {
			ConnectionData connectionData = mapConnectionData(socialUser);
			ConnectionFactory<?> connectionFactory = connectionFactoryLocator
					.getConnectionFactory(connectionData.getProviderId());
			return connectionFactory.createConnection(connectionData);
		}

		private ConnectionData mapConnectionData(SocialUser socialUser) {
			return new ConnectionData(socialUser.getProviderId(),
					socialUser.getProviderUserId(),
					socialUser.getDisplayName(), socialUser.getProfileUrl(),
					socialUser.getImageUrl(),
					decrypt(socialUser.getAccessToken()),
					decrypt(socialUser.getSecret()),
					decrypt(socialUser.getRefreshToken()),
					expireTime(socialUser.getExpireTime()));
		}

		private String decrypt(String encryptedText) {
			return encryptedText != null ? textEncryptor.decrypt(encryptedText)
					: encryptedText;
		}

		private Long expireTime(Long expireTime) {
			return expireTime == null || expireTime == 0 ? null : expireTime;
		}

	}

}
