package com.hopes.connect.model.exception;

/**
 * @author SaNNy - Sep 8, 2018
 */

public class RegistrationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	private static final String ERROR_MESSAGE = "REGISTRATION_EXCEPTION: ";

	/**
	 * 
	 * @param message
	 * 
	 * Handle invalid data from request when registering new clients or users 
	 */
	public RegistrationException(String message) {
		super(message);
	}

}
