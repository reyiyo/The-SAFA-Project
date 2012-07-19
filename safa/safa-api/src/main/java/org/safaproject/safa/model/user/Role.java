package org.safaproject.safa.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.safaproject.safa.model.BaseEntity;

/**
 * This class defines a Role for a User.
 * 
 * @author reyiyo
 * 
 */
@Entity
@Table(name = "ROLE")
public class Role extends BaseEntity{

	@Column(name = "name", unique = true, nullable = false)
	@Enumerated(EnumType.STRING)
	private Roles name;

	public Role() {
		// Hibernate constructor
	}

	public Role(Roles name) {
		this.name = name;
	}

	public void setName(Roles name) {
		this.name = name;
	}

	public Roles getName() {
		return name;
	}

}
