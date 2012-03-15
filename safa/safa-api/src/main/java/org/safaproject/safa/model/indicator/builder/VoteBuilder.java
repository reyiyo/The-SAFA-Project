package org.safaproject.safa.model.indicator.builder;

import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.indicator.Indicator;
import org.safaproject.safa.model.indicator.Vote;
import org.safaproject.safa.model.user.User;

import com.google.common.base.Preconditions;

public class VoteBuilder {
	
	private User user;

	private Indicator indicator;

	private Content content;

	private Integer value;
	
	public VoteBuilder withUser(User user) {
		this.user = user;
		return this;
	}
	
	public VoteBuilder withIndicator(Indicator indicator) {
		this.indicator = indicator;
		return this;
	}
	
	public VoteBuilder withContent(Content content) {
		this.content = content;
		return this;
	}
	
	public VoteBuilder withValue(Integer value) {
		this.value = value;
		return this;
	}
	
	public Vote build() {
		Preconditions.checkNotNull(user);
		Preconditions.checkNotNull(indicator);
		Preconditions.checkNotNull(content);
		Preconditions.checkNotNull(value);
		
		return new Vote(user, indicator, content, value);
	}
}
