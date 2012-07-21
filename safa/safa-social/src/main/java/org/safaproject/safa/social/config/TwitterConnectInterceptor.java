package org.safaproject.safa.social.config;

import org.socialsignin.springsocial.security.signin.SpringSocialSecurityConnectInterceptor;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;

@Component
public class TwitterConnectInterceptor extends
		SpringSocialSecurityConnectInterceptor<Twitter> {

}
