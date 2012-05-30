package org.safaproject.safa.web.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.safaproject.safa.model.user.Role;
import org.safaproject.safa.model.user.SocialUser;
import org.safaproject.safa.social.exception.UserNotFoundException;
import org.safaproject.safa.social.service.SocialUserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

//TODO: This class needs to be refactored :)
public class SignInAdapterImpl implements SignInAdapter {

	protected static Logger logger = Logger.getLogger("SignInAdapter");

	@Inject
	private SocialUserService socialUserService;

	private final RequestCache requestCache;

	@Inject
	public SignInAdapterImpl(RequestCache requestCache) {
		this.requestCache = requestCache;
	}

	@Override
	public String signIn(String userId, Connection<?> connection,
			NativeWebRequest request) {

		SocialUser user = null;

		try {
			user = socialUserService.getUserByUserIdAndConnection(userId,
					connection);
			SecurityContextHolder.getContext().setAuthentication(
					this.getAuthentication(user));

			return extractOriginalUrl(request);
		
		} catch (UserNotFoundException e) {
			logger.fatal("This should not have EVER happened :(", e);
			return null;
		}

	}

	private String extractOriginalUrl(NativeWebRequest request) {

		HttpServletRequest nativeReq = request
				.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse nativeRes = request
				.getNativeResponse(HttpServletResponse.class);

		SavedRequest saved = requestCache.getRequest(nativeReq, nativeRes);
		if (saved == null) {
			return null;
		}
		requestCache.removeRequest(nativeReq, nativeRes);
		removeAutheticationAttributes(nativeReq.getSession(false));
		return saved.getRedirectUrl();
	}

	private void removeAutheticationAttributes(HttpSession session) {
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	private Authentication getAuthentication(SocialUser user) {
		return new UsernamePasswordAuthenticationToken(
				createSpringSecurityUser(user), "", getGrantedAuthorities(user));
	}

	private User createSpringSecurityUser(SocialUser user) {
		return new User(user.getUserId(), "", user.getIsLocked(),
				user.getIsLocked(), user.getIsLocked(), user.getIsLocked(),
				getGrantedAuthorities(user));
	}

	/**
	 * Wraps {@link org.safaproject.safa.model.user.Role} roles to
	 * {@link SimpleGrantedAuthority} objects
	 * 
	 * @param roleswith
	 *            {@link String} of roles
	 * @return list of granted authorities
	 */
	private List<GrantedAuthority> getGrantedAuthorities(SocialUser user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

}
