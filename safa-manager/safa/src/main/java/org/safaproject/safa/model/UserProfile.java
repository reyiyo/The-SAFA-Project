package org.safaproject.safa.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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

	@OneToMany
	@JoinColumn(name = "userId")
	private List<Content> history;

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public Long getProfileId() {
		return profileId;
	}
	
	public void setHistory(List<Content> history) {
		this.history = history;
	}

	public List<Content> getHistory() {
		return history;
	}

}
