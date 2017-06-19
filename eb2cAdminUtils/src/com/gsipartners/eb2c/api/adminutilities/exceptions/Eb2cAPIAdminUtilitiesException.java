package com.gsipartners.eb2c.api.adminutilities.exceptions;

/**
 * The main "Application Exception" class for the GSI eb2c API Test Harness app. 
 * <P>
 * All exceptions in this app are rethrown as an instance of 
 * Eb2cAPIAdminUtilitiesException Exception 
 */
public abstract class Eb2cAPIAdminUtilitiesException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Eb2cAPIAdminUtilitiesException() {

	}

	/**
	 * @param message Simple String to define the Exception content
	 */
	public Eb2cAPIAdminUtilitiesException(String message) {

		super(message);

	}

	/**
	 * @param cause add the Exception that was caught to define the Exception content
	 */
	public Eb2cAPIAdminUtilitiesException(Throwable cause) {

		super(cause);

	}

	/**
	 * @param message add the Simple String to define the Exception content
	 * @param cause the Exception that was caught to define the Exception content
	 * -
	 */
	public Eb2cAPIAdminUtilitiesException(String message, Throwable cause) {

		super(message, cause);

	}

}
