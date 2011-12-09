package org.safaproject.safa.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author reyiyo
 * 
 */
@Entity
public class Content {

	@Id
	@GeneratedValue
	private String contentId;

	private String title;

	private String description;

	private Date uploadDate;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@ManyToMany
	private Set<Tag> tags;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Indicator> indicators;

	private boolean available = true;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "contentId")
	private Set<Resource> resources;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idThumbnailResource")
	private Resource thumbnail;

	private boolean reviewed = false;

	/**
	 * @return the contentId
	 */
	public String getContentId() {
		return contentId;
	}

	/**
	 * @param contentId
	 *            the contentId to set
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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

	/**
	 * @return the uploadDate
	 */
	public Date getUploadDate() {
		return uploadDate;
	}

	/**
	 * @param uploadDate
	 *            the uploadDate to set
	 */
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the tags
	 */
	public Set<Tag> getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * @return the indicadores
	 */
	public Set<Indicator> getIndicators() {
		return indicators;
	}

	/**
	 * @param indicadores
	 *            the indicadores to set
	 */
	public void setIndicators(Set<Indicator> indicators) {
		this.indicators = indicators;
	}

	/**
	 * @return the available
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * @param available
	 *            the available to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
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

	/**
	 * @return the thumbnail
	 */
	public Resource getThumbnail() {
		return thumbnail;
	}

	/**
	 * @param thumbnail
	 *            the thumbnail to set
	 */
	public void setThumbnail(Resource thumbnail) {
		this.thumbnail = thumbnail;
	}

	/**
	 * @return the reviewed
	 */
	public boolean isReviewed() {
		return reviewed;
	}

	/**
	 * @param reviewed
	 *            the reviewed to set
	 */
	public void setReviewed(boolean reviewed) {
		this.reviewed = reviewed;
	}

}
