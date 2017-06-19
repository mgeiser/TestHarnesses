package com.gsipartners.eb2c.api.adminutilities.exceptions;

/**
 * The "Illegal Arguments Exception" class for the GSI eb2c API Admin Utilities app. 
 * <P>
 */
public class Eb2cAPIIllegalArgumentsException extends Eb2cAPIAdminUtilitiesException {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Eb2cAPIIllegalArgumentsException() {

	}

	/**
	 * @param message Simple String to define the Exception content
	 */
	public Eb2cAPIIllegalArgumentsException(String message) {

		super(message);

	}

	/**
	 * @param cause add the Exception that was caught to define the Exception content
	 */
	public Eb2cAPIIllegalArgumentsException(Throwable cause) {

		super(cause);

	}

	/**
	 * @param message add the Simple String to define the Exception content
	 * @param cause the Exception that was caught to define the Exception content
	 * -
	 */
	public Eb2cAPIIllegalArgumentsException(String message, Throwable cause) {

		super(message, cause);

	}

}
