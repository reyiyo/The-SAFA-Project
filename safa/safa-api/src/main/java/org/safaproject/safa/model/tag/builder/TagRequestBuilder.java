package org.safaproject.safa.model.tag.builder;

import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.tag.TagRequest;
import org.safaproject.safa.model.user.User;

import com.google.common.base.Preconditions;

public class TagRequestBuilder {

	private User user;

	private String tagName;

	private String tagValue;

	private String reason;

	private Content content;
	
	public TagRequestBuilder withUser(User user) {
		this.user = user;
		return this;
	}
	
	public TagRequestBuilder withTagName(String tagName) {
		this.tagName = tagName;
		return this;
	}
	
	public TagRequestBuilder withTagValue(String tagValue) {
		this.tagValue = tagValue;
		return this;
	}
	
	public TagRequestBuilder withReason(String reason) {
		this.reason = reason;
		return this;
	}
	
	public TagRequestBuilder withContent(Content content) {
		this.content = content;
		return this;
	}
	
	public TagRequest build() {
		Preconditions.checkNotNull(user);
		Preconditions.checkNotNull(tagName);
		Preconditions.checkNotNull(tagValue);
		Preconditions.checkNotNull(reason);
		Preconditions.checkNotNull(content);
		
		return new TagRequest(user, tagName, tagValue, reason, content);
	}
}
