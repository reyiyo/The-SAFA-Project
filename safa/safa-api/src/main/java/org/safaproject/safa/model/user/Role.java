package org.safaproject.safa.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class defines a Role for a User.
 * 
 * @author reyiyo
 * 
 */
@Entity
@Table(name = "ROLE")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "roleId")
	private Long id;

	@Column(name = "name", unique = true, nullable = false)
	@Enumerated(EnumType.STRING)
	private Roles name;
	
	@Column(name="version")
	private Long version;

	public Role() {
		// Hibernate constructor
	}

	public Role(Roles name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(Roles name) {
		this.name = name;
	}

	public Roles getName() {
		return name;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
