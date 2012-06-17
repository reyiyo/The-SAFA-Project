package org.safaproject.safa.social.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

/**
 * We should try to make this configuration in XML in the future
 * 
 * @author reyiyo
 * 
 */
@Configuration
public class APIsConfig {

	@Autowired
	private ConnectionRepository connectionRepository;

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Facebook facebook() {
		Connection<Facebook> facebook = connectionRepository
				.findPrimaryConnection(Facebook.class);
		return facebook != null ? facebook.getApi() : new FacebookTemplate();
	}

	public void setConnectionRepository(
			ConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}

}
