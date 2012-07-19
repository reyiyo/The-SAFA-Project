package org.safaproject.safa.model.user;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.safaproject.safa.model.BaseEntity;

/**
 * This class contains the basic data from an user in our system. It has the
 * username in the system, the roles and whether the account is locked or not.
 * This values are global for all the SocialUsers connected to the user.
 * 
 * @author reyiyo
 * 
 */
@Entity
@Table(name = "SAFA_USER")
public class User extends BaseEntity {

	@Pattern(regexp = "^[a-zA-Z0-9]+[\\.\\-_a-zA-Z0-9]+$")
	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "password")
	private String password;

	@ManyToMany
	@JoinTable(name = "SAFA_USER_ROLE")
	private Set<Role> roles;

	@Column(name = "locked")
	private Boolean isLocked = false;

	@OneToMany
	@JoinColumn(name = "safaUser")
	private Set<SocialUser> connectedSocialProfiles;

	public User() {
		// Constructor for hibernate
	}

	public User(String username, String password, Set<Role> roles,
			Boolean isLocked, Set<SocialUser> connectedSocialProfiles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.isLocked = isLocked;
		this.connectedSocialProfiles = connectedSocialProfiles;
	}

	public void addSocialProfile(SocialUser socialUser) {
		this.connectedSocialProfiles.add(socialUser);
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

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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

	public void setConnectedSocialProfiles(
			Set<SocialUser> connectedSocialProfiles) {
		this.connectedSocialProfiles = connectedSocialProfiles;
	}

	public Set<SocialUser> getConnectedSocialProfiles() {
		return connectedSocialProfiles;
	}

}
