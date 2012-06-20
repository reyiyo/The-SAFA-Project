package org.safaproject.safa.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class will describe the profile data of a user. DATA STILL TO BE DEFINED
 * 
 * @author reyiyo
 * 
 */
@Entity
@Table(name = "USER_PROFILE")
public class UserProfile {

	// TODO: To be defined

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "profileId")
	private Long id;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
