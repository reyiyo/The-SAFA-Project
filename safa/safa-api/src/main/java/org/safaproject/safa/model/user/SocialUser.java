package org.safaproject.safa.model.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "SOCIAL_USER", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "userId", "providerId",
				"providerUserId" }),
		@UniqueConstraint(columnNames = { "userId", "providerId", "rank" }) })
public class SocialUser {

	public SocialUser() {
		// Constructor for hibernate
	}

	public SocialUser(String userId, String providerId, String providerUserId,
			int rank, String displayName, String profileUrl, String imageUrl,
			String accessToken, String secret, String refreshToken,
			Long expireTime, Date createDate) {
		this.userId = userId;
		this.providerId = providerId;
		this.providerUserId = providerUserId;
		this.rank = rank;
		this.displayName = displayName;
		this.profileUrl = profileUrl;
		this.imageUrl = imageUrl;
		this.accessToken = accessToken;
		this.secret = secret;
		this.refreshToken = refreshToken;
		this.expireTime = expireTime;
		this.createDate = createDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/**
	 * A local identifier for the user, in our case the username.
	 */
	private String userId;

	/**
	 * This is the string provider id value, e.g. "facebook", "twitter", etc.
	 */
	@Column(nullable = false)
	private String providerId;

	/**
	 * This is the user’s unique id in the provider’s system
	 */
	private String providerUserId;

	/**
	 * Spring Social actually allows for 1-n accounts per provider per user
	 * (e.g. multiple Facebook accounts associated with one user in your
	 * application), and this value determines the order of importance of those
	 * accounts; generally though there will just be 1 account per provider per
	 * user and this value will generally be 1
	 */
	@Column(nullable = false)
	private int rank;

	/**
	 * Some profile data field that may or may not be sent to your application
	 * by the provider
	 */
	private String displayName;

	/**
	 * Some profile data field that may or may not be sent to your application
	 * by the provider
	 */
	private String profileUrl;

	/**
	 * Some profile data field that may or may not be sent to your application
	 * by the provider
	 */
	private String imageUrl;

	/**
	 * OAuth credentials and related information
	 */
	@Column(nullable = false)
	private String accessToken;

	/**
	 * OAuth credentials and related information
	 */
	private String secret;

	/**
	 * OAuth credentials and related information
	 */
	private String refreshToken;
	/**
	 * OAuth credentials and related information
	 */
	private Long expireTime;

	private Date createDate = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}