package com.gsipartners.eb2c.api.testharness.exceptions;

/**
 * The main "Application Exception" class for the GSI eb2c API Test Harness app. 
 * <P>
 * All exceptions in this app are rethrown as an instance of 
 * DataProviderParserException Exception except for 
 * DataProviderParserImplNotFoundException which is a special case for 
 * a missing implementation class
 */
public class Eb2cAPITestHarnessException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Eb2cAPITestHarnessException() {

	}

	/**
	 * @param message Simple String to define the Exception content
	 */
	public Eb2cAPITestHarnessException(String message) {

		super(message);

	}

	/**
	 * @param cause add the Exception that was caught to define the Exception content
	 */
	public Eb2cAPITestHarnessException(Throwable cause) {

		super(cause);

	}

	/**
	 * @param message add the Simple String to define the Exception content
	 * @param cause the Exception that was caught to define the Exception content
	 * -
	 */
	public Eb2cAPITestHarnessException(String message, Throwable cause) {

		super(message, cause);

	}

}
