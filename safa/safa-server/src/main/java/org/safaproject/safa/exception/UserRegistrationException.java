package org.safaproject.safa.exception;

public class UserRegistrationException extends Exception {

	private static final long serialVersionUID = -3145572783094417128L;

	public UserRegistrationException(Throwable t) {
		super("An error occurred while trying to register a user.", t);
	}

}
