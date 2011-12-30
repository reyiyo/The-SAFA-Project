package org.safaproject.safa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This class will describe the profile data of a user. DATA STILL TO BE DEFINED
 * 
 * @author reyiyo
 * 
 */
@Entity
public class UserProfile {

	// TODO: To be defined

	@Id
	@GeneratedValue
	private Long profileId;

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public Long getProfileId() {
		return profileId;
	}
}
