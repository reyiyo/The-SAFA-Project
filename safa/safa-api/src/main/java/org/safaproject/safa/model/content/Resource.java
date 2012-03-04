package org.safaproject.safa.model.content;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.URL;

/**
 * This class defines a Resource. A Resource describes an url metadata, like its
 * size and description.
 * 
 * @author reyiyo
 * 
 */
@Entity
@Table(name = "RESOURCE")
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "resourceId")
	private Long resourceId;

	@Column(name = "url", nullable = false)
	@URL(message = "{Resource.url.Url}")
	private String url;

	@ManyToOne
	@JoinColumn(name = "resourceType", nullable = false)
	private ResourceType resourceType;

	@Column(name = "size", nullable = false)
	@Min(value = 0, message = "{Resource.size.Min}")
	private Long size;

	@Column(name = "description")
	private String description;

	public Resource() {
		// Constructor for hibernate
	}

	public Resource(String url, ResourceType resourceType, Long size,
			String description) {

		super();
		this.url = url;
		this.resourceType = resourceType;
		this.size = size;
		this.description = description;

	}

	/**
	 * @return the resourceId
	 */
	public Long getResourceId() {
		return resourceId;
	}

	/**
	 * @param resourceId
	 *            the resourceId to set
	 */
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the resourceType
	 */
	public ResourceType getResourceType() {
		return resourceType;
	}

	/**
	 * @param resourceType
	 *            the resourceType to set
	 */
	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * @return the size
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(Long size) {
		this.size = size;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
