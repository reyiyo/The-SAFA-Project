package org.safaproject.safa.model.content.builder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.safaproject.safa.model.content.Comment;
import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.content.Resource;
import org.safaproject.safa.model.indicator.Indicator;
import org.safaproject.safa.model.tag.Tag;
import org.safaproject.safa.model.user.User;

import com.google.common.base.Preconditions;

public class ContentBuilder {

	private String title;

	private String description;

	private Date uploadDate;

	private User user;

	private Set<Tag> tags = new HashSet<Tag>();

	private Set<Indicator> indicators = new HashSet<Indicator>();

	private boolean available = true;

	private Set<Resource> resources = new HashSet<Resource>();

	private Resource thumbnail;

	private boolean reviewed = false;

	private Set<Comment> comments = new HashSet<Comment>();
	
	public ContentBuilder withTitle(String title) {
		this.title = title;
		return this;
	}
	public ContentBuilder withDescription(String desc) {
		this.description = desc;
		return this;
	}
	public ContentBuilder withUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
		return this;
	}
	public ContentBuilder withUser(User user) {
		this.user = user;
		return this;
	}
	public ContentBuilder withTags(Set<Tag> tags) {
		this.tags = tags;
		return this;
	}
	public ContentBuilder withIndicators(Set<Indicator> indicators) {
		this.indicators = indicators;
		return this;
	}
	public ContentBuilder withAvailable(boolean available) {
		this.available = available;
		return this;
	}
	public ContentBuilder withResources(Set<Resource> resources) {
		this.resources = resources;
		return this;
	}
	public ContentBuilder withThumbnail(Resource thumbnail) {
		this.thumbnail = thumbnail;
		return this;
	}
	public ContentBuilder withReviewed(boolean reviewed) {
		this.reviewed = reviewed;
		return this;
	}
	public ContentBuilder withComments(Set<Comment> comments) {
		this.comments = comments;
		return this;
	}
	
	public Content build() {
		Preconditions.checkNotNull(title);
		Preconditions.checkNotNull(description);
		Preconditions.checkNotNull(uploadDate);
		
		return new Content(title,description,uploadDate,user,tags,indicators,available,resources,thumbnail,reviewed,comments);
	}
	
}
