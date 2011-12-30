package org.safaproject.safa.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
 * This class contains the basic data from an user. Most of the data will be
 * provided by the openID profile when the user firs log in
 * 
 * @author reyiyo
 * 
 */
@Entity
public class User {

	// It will be provided by OpenID
	@Id
	private Long userId;

	@Pattern(regexp = "^[a-zA-Z0-9]+[\\.\\-_a-zA-Z0-9]+$")
	private String username;

	@Size(min = 6, max = 20)
	private String password;

	@Email
	private String email;

	@ManyToMany(cascade = CascadeType.ALL)
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
