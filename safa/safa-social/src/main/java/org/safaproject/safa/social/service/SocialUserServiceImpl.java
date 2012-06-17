package org.safaproject.safa.social.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.safaproject.safa.social.dao.SocialUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;

import com.google.common.base.Preconditions;

public class SocialUserServiceImpl implements SocialUserService {

	@Autowired
	private SocialUserDAO socialUserDAO;

	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;

	private TextEncryptor textEncryptor;

	@Value("${social.crypto.password}")
	private String encryptionPassword;

	@Value("${social.crypto.enabled:true}")
	private boolean encryptCredentials;

	/**
	 * The text() encryptor will be used in Production Environment. For dev, we
	 * use noOpText that performs NO encryption, because otherway we'll need to
	 * install the JCE extension on each machine.
	 */
	@PostConstruct
	public void initializeTextEncryptor() {
		
		//TODO: Make this initialization dynamic

		textEncryptor = Encryptors.text(encryptionPassword, KeyGenerators
				.string().generateKey());

		textEncryptor = Encryptors.noOpText();
	}

	public List<String> findUserIdsWithConnection(Connection<?> connection) {
		ConnectionKey key = connection.getKey();
		return socialUserDAO.findUserIdsByProviderIdAndProviderUserId(
				key.getProviderId(), key.getProviderUserId());
	}

	public Set<String> findUserIdsConnectedTo(String providerId,
			Set<String> providerUserIds) {
		return new HashSet<String>(
				socialUserDAO.findUserIdsByProviderIdAndProviderUserIds(
						providerId, providerUserIds));
	}

	public ConnectionRepository createConnectionRepository(String userId) {
		Preconditions.checkNotNull(userId, "userId cannot be null");

		return new SocialUserConnectionRepositoryImpl(userId, socialUserDAO,
				connectionFactoryLocator, (encryptCredentials ? textEncryptor
						: null));
	}
}
