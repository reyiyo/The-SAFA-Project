package org.safaproject.safa.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.safaproject.safa.model.user.User;
import org.safaproject.safa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.NativeWebRequest;

public class SignInAdapterImpl implements SignInAdapter {

	@Autowired
	private UserService userService;

	@Autowired
	private RequestCache requestCache;

	@Autowired
	private TokenBasedRememberMeServices tokenBasedRememberMeServices;

	@Transactional
	public String signIn(String userId, Connection<?> connection,
			NativeWebRequest request) {
		User user = userService.findByLogin(userId);
		Authentication authentication = SecurityUtil.signInUser(user);

		HttpServletRequest nativeRequest = request
				.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse nativeResponse = request
				.getNativeResponse(HttpServletResponse.class);

		// set remember-me cookie
		tokenBasedRememberMeServices.onLoginSuccess(nativeRequest,
				nativeResponse, authentication);

		return extractOriginalUrl(nativeRequest, nativeResponse);
	}

	private String extractOriginalUrl(HttpServletRequest request,
			HttpServletResponse response) {
		SavedRequest saved = requestCache.getRequest(request, response);
		if (saved == null) {
			return null;
		}
		requestCache.removeRequest(request, response);
		removeAutheticationAttributes(request.getSession(false));
		return saved.getRedirectUrl();
	}

	private void removeAutheticationAttributes(HttpSession session) {
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}

}
