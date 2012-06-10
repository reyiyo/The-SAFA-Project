package org.safaproject.safa.social.service;

import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.user.SocialUser;

public interface FacebookService {

	public void addLikeToContent(SocialUser user, Content content);
	
	public void postContentToWall(SocialUser user, Content content);
	
}
