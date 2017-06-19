package com.gsipartners.eb2c.api.adminutilities.exceptions;

/**
 * The "Illegal Arguments Exception" class for the GSI eb2c API Admin Utilities app. 
 * <P>
 */
public class Eb2cAPIServiceCall403StatusException extends Eb2cAPIServiceCallException {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Eb2cAPIServiceCall403StatusException() {

	}

	/**
	 * @param message Simple String to define the Exception content
	 */
	public Eb2cAPIServiceCall403StatusException(String message) {

		super(message);

	}

	/**
	 * @param cause add the Exception that was caught to define the Exception content
	 */
	public Eb2cAPIServiceCall403StatusException(Throwable cause) {

		super(cause);

	}

	/**
	 * @param message add the Simple String to define the Exception content
	 * @param cause the Exception that was caught to define the Exception content
	 * -
	 */
	public Eb2cAPIServiceCall403StatusException(String message, Throwable cause) {

		super(message, cause);

	}

}
