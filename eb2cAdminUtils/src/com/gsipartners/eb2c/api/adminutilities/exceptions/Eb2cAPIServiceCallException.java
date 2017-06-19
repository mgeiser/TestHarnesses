package com.gsipartners.eb2c.api.adminutilities.exceptions;

/**
 * The "Illegal Arguments Exception" class for the GSI eb2c API Admin Utilities app. 
 * <P>
 */
public class Eb2cAPIServiceCallException extends Eb2cAPIAdminUtilitiesException {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Eb2cAPIServiceCallException() {

	}

	/**
	 * @param message Simple String to define the Exception content
	 */
	public Eb2cAPIServiceCallException(String message) {

		super(message);

	}

	/**
	 * @param cause add the Exception that was caught to define the Exception content
	 */
	public Eb2cAPIServiceCallException(Throwable cause) {

		super(cause);

	}

	/**
	 * @param message add the Simple String to define the Exception content
	 * @param cause the Exception that was caught to define the Exception content
	 * -
	 */
	public Eb2cAPIServiceCallException(String message, Throwable cause) {

		super(message, cause);

	}

}
