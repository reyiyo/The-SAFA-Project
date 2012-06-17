package org.safaproject.safa.common.function;

import org.safaproject.safa.model.user.SocialUser;

import com.google.common.base.Function;

public class SocialUserToUserId implements Function<SocialUser, String> {

	@Override
	public String apply(SocialUser socialUser) {
		return socialUser.getSocialUserId();
	}

}
