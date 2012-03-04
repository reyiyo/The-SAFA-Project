package org.safaproject.safa.model.content.builder;

import org.safaproject.safa.model.content.Resource;
import org.safaproject.safa.model.content.ResourceType;

/**
 * Builder for the ResourceType class. Must be reviewed if the object is built
 * at the end of the process (build method), or at the beggining and then just
 * fill the fields with setters. (This solution needs an empty constructor for
 * ResourceType)
 * 
 * @author reyiyo
 * 
 */
public class ResourceTypeBuilder {

	private Resource icon;

	private String name;

	public ResourceTypeBuilder withIcon(Resource icon) {
		this.icon = icon;
		return this;
	}

	public ResourceTypeBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public ResourceType build() {
		return new ResourceType(name, icon);
	}

}
