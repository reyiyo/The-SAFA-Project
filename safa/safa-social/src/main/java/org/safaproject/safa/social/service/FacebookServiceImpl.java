package org.safaproject.safa.social.service;

import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.user.SocialUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.social.facebook.api.Facebook;

@Service("facebookService")
public class FacebookServiceImpl implements FacebookService {

	@Autowired
	private Facebook facebook;
	
	@Autowired
	private URLFactory urlFactory;
	
	
	@Override
	public void addLikeToContent(SocialUser user, Content content) {		
		facebook.likeOperations().like(urlFactory.generateAbsoluteContentURL(content.getId()));
		// TODO: Generate indicators :)
	}

	@Override
	public void postContentToWall(SocialUser user, Content content) {
		facebook.feedOperations().updateStatus(urlFactory.generateAbsoluteContentURL(content.getId()));
	}
}
