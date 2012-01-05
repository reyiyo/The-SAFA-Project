package org.safaproject.safa.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
 * This class contains the basic data from an user. Most of the data will be
 * provided by the openID profile when the user first log in
 * 
 * @author reyiyo
 * 
 */
@Entity
@Table(name = "USER")
public class User {

	// It will be provided by OpenID
	@Id
	@Column(name = "userId")
	private Long userId;

	@Pattern(regexp = "^[a-zA-Z0-9]+[\\.\\-_a-zA-Z0-9]+$")
	@Column(name = "username")
	private String username;

	@Size(min = 6, max = 20)
	@Column(name = "password")
	private String password;

	@Email
	@Column(name = "email")
	private String email;

	@ManyToMany
	@JoinTable(name = "USER_ROL")
	private Set<Rol> rols;

	@ManyToOne
	@JoinColumn(name = "profileId")
	private UserProfile profile;

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

}
