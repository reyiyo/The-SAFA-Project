package org.safaproject.safa.model.content;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * This class describes the type for a Resource.
 * 
 * @author reyiyo
 * 
 */
@Entity
public class ResourceType {

	@Id
	private String name;

	@ManyToOne
	@JoinColumn(name = "resourceId", nullable = true)
	private Resource icon;

	@OneToMany
	@JoinColumn(name = "resourceType")
	private Set<Resource> resources;

	public ResourceType() {
		// Constructor for hibernate
	}

	public ResourceType(String name, Resource icon) {

		super();
		this.name = name;
		this.icon = icon;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the icon
	 */
	public Resource getIcon() {
		return icon;
	}

	/**
	 * @param icon
	 *            the icon to set
	 */
	public void setIcon(Resource icon) {
		this.icon = icon;
	}

	/**
	 * @return the resources
	 */
	public Set<Resource> getResources() {
		return resources;
	}

	/**
	 * @param resources
	 *            the resources to set
	 */
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

}
