package org.safaproject.safa.social.config;

import javax.inject.Inject;

import org.safaproject.safa.social.dao.SocialUserConnectionDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

@Configuration
public class SocialConfig {

	@Inject
	private Environment environment;
	
	@Inject
	private ConnectionSignUp connectionSignUp; 

	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new FacebookConnectionFactory(environment
				.getProperty("facebook.clientId"), environment
				.getProperty("facebook.clientSecret")));
		return registry;
	}

	@Bean
	public UsersConnectionRepository usersConnectionRepository() {
		SocialUserConnectionDAO repository = new SocialUserConnectionDAO(
				connectionFactoryLocator(), Encryptors.noOpText());
		repository.setConnectionSignUp(connectionSignUp);
		return repository;
	}

}
