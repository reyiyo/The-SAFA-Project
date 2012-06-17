package org.safaproject.safa.social.dao.criteria;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.safaproject.safa.commons.dao.criteria.EntityCriteriaBuilder;
import org.safaproject.safa.model.content.OrderDirections;
import org.safaproject.safa.model.user.SocialUser;
import org.springframework.util.MultiValueMap;

import com.google.common.collect.Lists;

public class SocialUserCriteriaBuilder extends
		EntityCriteriaBuilder<SocialUser> {

	public SocialUserCriteriaBuilder(EntityManager entityManager) {
		super(entityManager, SocialUser.class);
	}

	public SocialUserCriteriaBuilder withUserId(String userId) {
		this.withValue(Fields.USER_ID.getField(), userId);
		return this;
	}

	public SocialUserCriteriaBuilder withProviderId(String providerId) {
		this.withValue(Fields.PROVIDER_ID.getField(), providerId);
		return this;
	}

	public SocialUserCriteriaBuilder withProviderUserId(String providerUserId) {
		this.withValue(Fields.PROVIDER_USER_ID.getField(), providerUserId);
		return this;
	}

	public SocialUserCriteriaBuilder withProviderUserIds(
			Collection<String> providerUserIds) {
		this.in(Fields.PROVIDER_USER_ID.getField(),
				Lists.newArrayList(providerUserIds));
		return this;
	}

	public Integer selectMaxRank() {
		return this.selectMax(Fields.RANK.getField()).intValue();
	}

	/**
	 * This is bullshit, but what else can we do? :P
	 */
	public List<SocialUser> listUsersWithProviderUsers(String userId,
			MultiValueMap<String, String> providerUsers) {

		// TODO: Make it beautifull :)

		Predicate where = null;
		for (Iterator<Entry<String, List<String>>> it = providerUsers
				.entrySet().iterator(); it.hasNext();) {
			Entry<String, List<String>> entry = it.next();
			String providerId = entry.getKey();
			Path<String> path = root.get(Fields.PROVIDER_USER_ID.getField());
			Predicate in = path.in(entry.getValue());
			if (where == null) {
				where = criteriaBuilder.equal(
						root.get(Fields.PROVIDER_ID.getField()), providerId);
				where = criteriaBuilder.and(whereClause, in);
			} else {
				where = criteriaBuilder.or(where, criteriaBuilder.and(
						criteriaBuilder.equal(
								root.get(Fields.PROVIDER_ID.getField()),
								providerId), in));
			}
		}
		whereClause = criteriaBuilder.and(where, criteriaBuilder.equal(
				root.get(Fields.USER_ID.getField()), userId));
		return this.list();
	}

	public SocialUserCriteriaBuilder orderBy(OrderDirections direction,
			String... fields) {
		super.orderBy(direction, fields);
		return this;
	}

	public static enum Fields {
		USER_ID("socialUserId"), PROVIDER_ID("providerId"), PROVIDER_USER_ID(
				"providerUserId"), RANK("rank");

		private String field;

		Fields(String field) {
			this.field = field;
		}

		public String getField() {
			return this.field;
		}
	}
}
