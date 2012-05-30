package org.safaproject.safa.model.user.builder;

import java.util.Date;
import java.util.Set;

import org.safaproject.safa.model.user.Role;
import org.safaproject.safa.model.user.SocialUser;
import org.safaproject.safa.model.user.UserProfile;

public class SocialUserBuilder {

	private String userId;

	private String providerId;

	private String providerUserId;

	private int rank;

	private String displayName;

	private String profileUrl;

	private String imageUrl;

	private String accessToken;

	private String secret;

	private String refreshToken;

	private Long expireTime;

	private Date createDate = new Date();

	private Set<Role> roles;

	private UserProfile profile;

	private Boolean locked;

	public SocialUserBuilder withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public SocialUserBuilder withProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
		return this;
	}

	public SocialUserBuilder withProviderId(String providerId) {
		this.providerId = providerId;
		return this;
	}

	public SocialUserBuilder withProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
		return this;
	}

	public SocialUserBuilder withRank(int rank) {
		this.rank = rank;
		return this;
	}

	public SocialUserBuilder withDisplayName(String displayName) {
		this.displayName = displayName;
		return this;
	}

	public SocialUserBuilder withImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		return this;
	}

	public SocialUserBuilder withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}

	public SocialUserBuilder withSecret(String secret) {
		this.secret = secret;
		return this;
	}

	public SocialUserBuilder withRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
		return this;
	}

	public SocialUserBuilder withExpireTime(Long expireTime) {
		this.expireTime = expireTime;
		return this;
	}

	public SocialUserBuilder withCreateDate(Date createDate) {
		this.createDate = createDate;
		return this;
	}

	public SocialUserBuilder withRoles(Set<Role> roles) {
		this.roles = roles;
		return this;
	}

	public SocialUserBuilder withProfile(UserProfile profile) {
		this.profile = profile;
		return this;
	}

	public SocialUserBuilder withLocked(Boolean locked) {
		this.locked = locked;
		return this;
	}

	public SocialUser build() {
		return new SocialUser(userId, providerId, providerUserId, rank,
				displayName, profileUrl, imageUrl, accessToken, secret,
				refreshToken, expireTime, createDate, roles, profile, locked);
	}

}
