package org.safaproject.safa.social.dao;

import java.util.List;
import java.util.Set;

import org.safaproject.safa.common.function.SocialUserToUserId;
import org.safaproject.safa.commons.dao.GenericJPADAO;
import org.safaproject.safa.model.content.OrderDirections;
import org.safaproject.safa.model.user.SocialUser;
import org.safaproject.safa.social.dao.criteria.SocialUserCriteriaBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

@Repository
public class SocialUserDAOImpl extends GenericJPADAO<SocialUser, Long>
		implements SocialUserDAO {
	
	@Override
	public List<SocialUser> findByProviderId(String providerId) {
		return getCriteriaBuilder().withProviderId(providerId).list();
	}

	@Override
	public List<SocialUser> findByUserId(String userId) {
		return getCriteriaBuilder().withUserId(userId).list();
	}

	@Override
	public List<SocialUser> findByUserIdAndProviderId(String userId,
			String providerId) {
		return getCriteriaBuilder().withUserId(userId)
				.withProviderId(providerId).list();
	}

	@Override
	public List<SocialUser> findByUserIdAndProviderUserIds(String userId,
			MultiValueMap<String, String> providerUserIds) {
		return getCriteriaBuilder().listUsersWithProviderUsers(userId,
				providerUserIds);

	}

	@Override
	public SocialUser get(String userId, String providerId,
			String providerUserId) {
		return getCriteriaBuilder().withUserId(userId)
				.withProviderId(providerId).withProviderUserId(providerUserId)
				.getSingleResult();
	}

	@Override
	public List<SocialUser> findPrimaryByUserIdAndProviderId(String userId,
			String providerId) {
		return getCriteriaBuilder()
				.withUserId(userId)
				.withProviderId(providerId)
				.orderBy(OrderDirections.ASC,
						SocialUserCriteriaBuilder.Fields.RANK.name()).list();
	}

	@Override
	public Integer selectMaxRankByUserIdAndProviderId(String userId,
			String providerId) {
		return Integer.valueOf(getCriteriaBuilder().withUserId(userId)
				.withProviderId(providerId).selectMaxRank().intValue());
	}

	@Override
	public List<String> findUserIdsByProviderIdAndProviderUserId(
			String providerId, String providerUserId) {
		return Lists.newArrayList(Collections2.transform(getCriteriaBuilder()
				.withProviderId(providerId).withProviderUserId(providerUserId)
				.list(), new SocialUserToUserId()));
	}

	@Override
	public List<String> findUserIdsByProviderIdAndProviderUserIds(
			String providerId, Set<String> providerUserIds) {
		return Lists.newArrayList(Collections2.transform(getCriteriaBuilder()
				.withProviderId(providerId)
				.withProviderUserIds(providerUserIds).list(),
				new SocialUserToUserId()));
	}

	@Override
	public SocialUserCriteriaBuilder getCriteriaBuilder() {
		return new SocialUserCriteriaBuilder(entityManager);
	}

}
