package org.safaproject.safa.social.exception;

public class UserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message, Throwable e) {
		super(message, e);
	}

}
