package org.safaproject.safa.dao.criteria;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.safaproject.safa.model.user.SocialUser;
import org.springframework.util.MultiValueMap;

public class SocialUserCriteriaBuilder extends
		EntityCriteriaBuilder<SocialUser> {

	private static final String USER_ID = "userId";
	private static final String PROVIDER_ID = "providerId";
	private static final String PROVIDER_USER_ID = "providerUserId";
	private static final String RANK = "rank";

	public SocialUserCriteriaBuilder(EntityManager entityManager) {
		super(entityManager, SocialUser.class);
	}

	public SocialUserCriteriaBuilder withUserId(String userId) {
		this.withValue(USER_ID, userId);
		return this;
	}

	public SocialUserCriteriaBuilder withProviderId(String providerId) {
		this.withValue(PROVIDER_ID, providerId);
		return this;
	}

	public SocialUserCriteriaBuilder withProviderUserId(String providerUserId) {
		this.withValue(PROVIDER_USER_ID, providerUserId);
		return this;
	}

	public Long selectMaxRank() {
		return this.selectMax(RANK);
	}

	/**
	 * This is bullshit, but what else can we do? :P
	 */
	public List<SocialUser> listUsersWithProviderUsers(String userId,
			MultiValueMap<String, String> providerUsers) {
		Predicate where = null;
		for (Iterator<Entry<String, List<String>>> it = providerUsers
				.entrySet().iterator(); it.hasNext();) {
			Entry<String, List<String>> entry = it.next();
			String providerId = entry.getKey();
			Path<String> path = root.get(PROVIDER_USER_ID);
			Predicate in = path.in(entry.getValue());
			if (where == null) {
				where = criteriaBuilder
						.equal(root.get(PROVIDER_ID), providerId);
				where = criteriaBuilder.and(whereClause, in);
			} else {
				where = criteriaBuilder.or(
						where,
						criteriaBuilder.and(criteriaBuilder.equal(
								root.get(PROVIDER_ID), providerId), in));
			}
		}
		whereClause = criteriaBuilder.and(where,
				criteriaBuilder.equal(root.get(USER_ID), userId));
		return this.list();
	}
}
