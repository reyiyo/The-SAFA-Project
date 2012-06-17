package org.safaproject.safa.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.safaproject.safa.model.user.User;
import org.safaproject.safa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.NativeWebRequest;

public class SignInAdapterImpl implements SignInAdapter {

	@Autowired
	private UserService userService;

	@Autowired
	private TokenBasedRememberMeServices tokenBasedRememberMeServices;

	@Transactional
	public String signIn(String userId, Connection<?> connection,
			NativeWebRequest request) {
		User user = userService.findByLogin(userId);
		Authentication authentication = SecurityUtil.signInUser(user);

		// set remember-me cookie
		tokenBasedRememberMeServices.onLoginSuccess(
				request.getNativeRequest(HttpServletRequest.class),
				request.getNativeResponse(HttpServletResponse.class),
				authentication);

		return null;
	}
}
