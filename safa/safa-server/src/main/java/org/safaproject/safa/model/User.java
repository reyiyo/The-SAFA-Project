package org.safaproject.safa.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.URL;

/**
 * This class contains the basic data from an user. Most of the data will be
 * provided by the openID profile when the user first log in
 * 
 * @author reyiyo
 * 
 */
@Entity
@Table(name = "SAFA_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId")
	private Long userId;

	@Column(name = "urlToken", unique = true, nullable = false)
	@URL
	private String openIDurlToken;

	@Pattern(regexp = "^[a-zA-Z0-9]+[\\.\\-_a-zA-Z0-9]+$")
	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Size(min = 6, max = 20)
	@Column(name = "password")
	private String password;

	@Email
	@Column(name = "email", nullable = false)
	private String email;

	@ManyToMany
	@JoinTable(name = "SAFA_USER_ROL")
	private Set<Rol> rols;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "profileId")
	private UserProfile profile;

	@Column(name = "locked")
	private Boolean isLocked = false;

	public User() {

	}

	public User(String openIDurlToken, String username, String email,
			Set<Rol> rols) {
		this.openIDurlToken = openIDurlToken;
		this.username = username;
		this.email = email;
		this.rols = rols;
		this.isLocked = false;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the openIDurlToken
	 */
	public String getOpenIDurlToken() {
		return openIDurlToken;
	}

	/**
	 * @param openIDurlToken
	 *            the openIDurlToken to set
	 */
	public void setOpenIDurlToken(String openIDurlToken) {
		this.openIDurlToken = openIDurlToken;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the rols
	 */
	public Set<Rol> getRols() {
		return rols;
	}

	/**
	 * @param rols
	 *            the rols to set
	 */
	public void setRols(Set<Rol> rols) {
		this.rols = rols;
	}

	/**
	 * @return the profile
	 */
	public UserProfile getProfile() {
		return profile;
	}

	/**
	 * @param profile
	 *            the profile to set
	 */
	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	/**
	 * @return the isLocked
	 */
	public Boolean getIsLocked() {
		return isLocked;
	}

	/**
	 * @param isLocked
	 *            the isLocked to set
	 */
	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

}
