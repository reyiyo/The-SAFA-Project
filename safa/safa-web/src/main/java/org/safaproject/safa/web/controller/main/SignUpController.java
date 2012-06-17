package org.safaproject.safa.web.controller.main;

import org.apache.log4j.Logger;
import org.safaproject.safa.exception.UserRegistrationException;
import org.safaproject.safa.model.user.User;
import org.safaproject.safa.service.UserService;
import org.safaproject.safa.web.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Controller for handling the sign up (aka registration) process.
 * 
 * Also handles when a new user signs in via Spring Social. The Spring Social
 * ProviderSignInController will redirect the new user to the GET handler. In
 * this app, a local account will be automatically created for the new user and
 * they will be signed in to the Spring Security SecurityContext.
 */
@Controller
@Transactional
@RequestMapping("/signup")
public class SignUpController {

	private static Logger logger = Logger.getLogger("SignUpController");

	private static final String FORM_VIEW = "signup";
	private static final String SUCCESS_VIEW = "redirect:/";

	@Autowired
	private UserService userService;

	@Autowired
	private SignInAdapter signInAdapter;

	@RequestMapping(method = RequestMethod.GET)
	public String getForm(NativeWebRequest request, @ModelAttribute User user) {
		String view = FORM_VIEW;

		// check if this is a new user signing in via Spring Social
		Connection<?> connection = ProviderSignInUtils.getConnection(request);
		if (connection != null) {
			// populate new User from social connection user profile
			UserProfile userProfile = connection.fetchUserProfile();
			user.setUsername(userProfile.getUsername());

			// TODO: perform validation on the User object here
			// to check if we are able to get all the data your app requires
			// from the social connection user profile
			// (some providers may not provide email address, for example)
			// if the User is not valid we will need to send the
			// user a sign up form with some notice that more data is required

			try {
				userService.registerUser(user);
			} catch (UserRegistrationException e) {
				logger.error("Error while trying to register a user: ", e);
			}

			// finish social signup/login
			ProviderSignInUtils.handlePostSignUp(user.getUsername(), request);

			// sign the user in and send them to the user home page
			signInAdapter.signIn(user.getUsername(), connection, request);
			view = SUCCESS_VIEW;
		}

		return view;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerUser(@ModelAttribute User user)
			throws UserRegistrationException {
		// user signing up via signup form

		// register user
		userService.registerUser(user);

		// sign user in
		SecurityUtil.signInUser(user);

		// send to user home page
		return SUCCESS_VIEW;
	}
}
