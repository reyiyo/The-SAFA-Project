package org.safaproject.safa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This class defines a Rol for a User.
 * 
 * @author reyiyo
 * 
 */
@Entity
public class Rol {

	@Id
	@GeneratedValue
	private Long rolId;

	@Column(unique = true, nullable = false)
	private String name;

	public Long getRolId() {
		return rolId;
	}

	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
