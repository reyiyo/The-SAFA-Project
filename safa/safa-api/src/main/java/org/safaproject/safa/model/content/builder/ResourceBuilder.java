package org.safaproject.safa.model.content.builder;

import org.safaproject.safa.model.content.Resource;
import org.safaproject.safa.model.tag.Tag;

/**
 * Builder for the Resource class. Must be reviewed if the object is built at
 * the end of the process (build method), or at the beggining and then just fill
 * the fields with setters. (This solution needs an empty constructor for
 * Resource)
 * 
 * @author reyiyo
 * 
 */
public class ResourceBuilder {

	private String url;

	private Tag resourceType;

	private Long size;

	private String description;

	public ResourceBuilder withUrl(String url) {
		this.url = url;
		return this;
	}

	public ResourceBuilder withResourceType(Tag resourceType) {
		this.resourceType = resourceType;
		return this;
	}

	public ResourceBuilder withSize(Long size) {
		this.size = size;
		return this;
	}

	public ResourceBuilder withDescription(String description) {
		this.description = description;
		return this;
	}

	public Resource build() {
		return new Resource(url, resourceType, size, description);
	}

}
