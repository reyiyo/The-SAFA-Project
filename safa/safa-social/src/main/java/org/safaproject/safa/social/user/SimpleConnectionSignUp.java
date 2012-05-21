package org.safaproject.safa.social.user;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.safaproject.safa.dao.SocialUserDAO;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("simpleConnectionSignUp")
public class SimpleConnectionSignUp implements ConnectionSignUp {

	@Inject
	private SocialUserDAO socialUserDAO;

	@Transactional
	public String execute(Connection<?> connection) {
		ConnectionData data = connection.createData();

		try {
			return socialUserDAO.getSocialUserCriteriaBuilder()
					.withProviderId(data.getProviderId())
					.withProviderUserId(data.getProviderUserId())
					.getSingleResult().getUserId();
		} catch (PersistenceException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		}

	}
}
