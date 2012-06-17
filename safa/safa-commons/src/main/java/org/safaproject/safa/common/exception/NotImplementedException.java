package org.safaproject.safa.common.exception;

/**
 * This exception should be used when a method should be implemented in the
 * future.
 * 
 * @author reyiyo
 * 
 */
public class NotImplementedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1421098609759463328L;

	public NotImplementedException() {
		super("This method is not implemented yet.");
	}
	
	public NotImplementedException(String message) {
		super(message);
	}

}
