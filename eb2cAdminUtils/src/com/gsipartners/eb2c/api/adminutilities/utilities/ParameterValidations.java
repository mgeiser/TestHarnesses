package com.gsipartners.eb2c.api.adminutilities.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.gsipartners.eb2c.api.adminutilities.dataobjects.Eb2cAPIAdminUtilitiesPropertiesDO;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIIllegalArgumentsException;;



public class ParameterValidations {
	
/*	
	**protocol = "http"						//http or https
	**targetKeyServerFQDN = "sectstapg03.us.gspt.net"  // sectstapg03.us.gspt.net or secdevapg03.us.gspt.net
	**targetKeyServerPort=8080				//port for Key Manager 
	**keyManagerUserId = "apigee";
	**keyManagerPassword = "apigee123";
	**consumerId = "QUIKUS"; 					//Name of the client that will appear in Analytics and reports 
	clientAppName = "eb2c";					// "eb2c" is only allowed value
	apiGroupName = "Production";			// "Production" is only allowed value
	storesMap = "storesMap";				// Must match Stores map defined in Key Manager exactly
	apiVersion = "v1.0";					// Must match exactly the API Version value; used for authorization in Apigee     
	**allowedStores = "QUKUS,RXYUS";			// Single or Comma separated values of the store codes that will be in the URI; used for authorization in Apigee
	**apiKey									// An API key used in commands needing an API Key...    
*/
	
	private static String EXPIRATION_DATE_FORMAT = "yyyy-MM-dd";
	
	private static Logger logger = Logger.getLogger(ParameterValidations.class);

	

	public static boolean hasKeyPassedExpirationDate(Eb2cAPIAdminUtilitiesPropertiesDO pPropertiesDO, Date testExpirationDate) throws Eb2cAPIIllegalArgumentsException {

		//set default return to  
		boolean hasKeyExpired = true;
		
		// Pull the pPropertiesDO.getKeyExpirationDate() into a must be a valid date matching the format YYYY-MM
		
		SimpleDateFormat sdf = new SimpleDateFormat(EXPIRATION_DATE_FORMAT);
		//use setLenient(false) so as to not 'rollover' dates on parse.  If we use setLenient(true), 2012-12-0 is converted to 2012-11-30 
		sdf.setLenient(false); 
		
	    Date keyExpirationDate = null;

	    // we will now try to parse the string into date form
	    try {

	    	keyExpirationDate = sdf.parse(pPropertiesDO.getKeyExpirationDate());
	    	
	    	//if the keyExpiration date is after the passed date, the key is still valid
	    	if (keyExpirationDate.after(testExpirationDate)) {
	    		hasKeyExpired = false;
	    	}
	    	
	    } catch (ParseException e) {

	    	logToErrorandRunLogs("Validation error: Expiration Date value " + pPropertiesDO.getKeyExpirationDate() + " is not a valid date");
			
			throw new Eb2cAPIIllegalArgumentsException("Validation error: Expiration Date value " + pPropertiesDO.getKeyExpirationDate() + " is not a valid date");	
	    }
		
		return hasKeyExpired;
	}
	
	public static void validateExpirationDate(Eb2cAPIAdminUtilitiesPropertiesDO pPropertiesDO) throws Eb2cAPIIllegalArgumentsException {
		// must be a valid date matching the format YYYY-MM
		
		SimpleDateFormat sdf = new SimpleDateFormat(EXPIRATION_DATE_FORMAT);
		//use setLenient(false) so as to not 'rollover' dates on parse.  If we use setLenient(true), 2012-12-0 is converted to 2012-11-30 
		sdf.setLenient(false); 
		
	    Date testDate = null;

	    // we will now try to parse the string into date form
	    try {

	    	testDate = sdf.parse(pPropertiesDO.getKeyExpirationDate());
	    	
	    } catch (ParseException e) {

	    	logToErrorandRunLogs("Validation error: Expiration Date value " + pPropertiesDO.getKeyExpirationDate() + " is not a valid date");
			
			throw new Eb2cAPIIllegalArgumentsException("Validation error: Expiration Date value " + pPropertiesDO.getKeyExpirationDate() + " is not a valid date");	
	    }

	}

	
	
	public static void validateEqualsCaseSensitive(String propertyName, String propertyValue, String testValue ) throws Eb2cAPIIllegalArgumentsException {
		// must be a non-zero length string

		if ( !propertyValue.equals(testValue) ) {

			logToErrorandRunLogs("Validation error: " + propertyName + " value " + propertyValue + " must equal (this test is case sensitive) to " + testValue);
			
			throw new Eb2cAPIIllegalArgumentsException("Validation error: " + propertyName + " value " + propertyValue + " must equal (this test is case sensitive) to " + testValue);	
		}
	}


	public static void validateEqualsIgnoreCase(String propertyName, String propertyValue, String testValue ) throws Eb2cAPIIllegalArgumentsException {
		// must be a non-zero length string

		if ( !propertyValue.equalsIgnoreCase(testValue) ) {
			
			logToErrorandRunLogs("Validation error: " + propertyName + " value " + propertyValue + " must equal (not case sensitive) to " + testValue);

			throw new Eb2cAPIIllegalArgumentsException("Validation error: " + propertyName + " value " + propertyValue + " must equal (not case sensitive) to " + testValue);	
		}
	}

	public static void validateIsNotZeroLengthString(String propertyName, String propertyValue ) throws Eb2cAPIIllegalArgumentsException {
		// must be a non-zero length string

		if ( propertyValue.length() == 0 ) {

			logToErrorandRunLogs("Validation error: " + propertyName + " must be a non-zero length string");
			
			throw new Eb2cAPIIllegalArgumentsException( "Validation error: " + propertyName + " must be a non-zero length string");	
		}
	}


	public static void validateTargetKeyManagerPort(Eb2cAPIAdminUtilitiesPropertiesDO pPropertiesDO) throws Eb2cAPIIllegalArgumentsException {
		// allowed must be an integer greater than 0
		boolean isValid = true;

		try {
			int targetKeyManagerPort = Integer.parseInt(pPropertiesDO.getTargetKeyManagerPort());
			if (targetKeyManagerPort <= 0) {
				isValid = false;
			}

		} catch (NumberFormatException NFE) {
			isValid = false;
		}

		if ( isValid == false ) {

			logToErrorandRunLogs("TargetKeyManagerPort validation error: " + pPropertiesDO.getTargetKeyManagerPort() + " is not an allowed value");

			throw new Eb2cAPIIllegalArgumentsException("TargetKeyManagerPort validation error:" + pPropertiesDO.getTargetKeyManagerPort() + " is not an allowed value");	
		}
	}


	public static void validateTargetKeyManagerFQDN(Eb2cAPIAdminUtilitiesPropertiesDO pPropertiesDO) throws Eb2cAPIIllegalArgumentsException {

		//get the value of the targetKeyManagerFQDN
		String targetKeyManagerFQDN = pPropertiesDO.getTargetKeyManagerFQDN().toLowerCase();

		Pattern pattern;
		Matcher matcher;

		String IPADDRESS_PATTERN = 
				"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
						"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
						"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
						"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

		pattern = Pattern.compile(IPADDRESS_PATTERN);
		matcher = pattern.matcher(targetKeyManagerFQDN);

		// the targetKeyManagerFQDN must either contain "gspt.net" or be an IP Address 
		if (!((targetKeyManagerFQDN.contains("gspt.net")) || (matcher.matches()) == true))  {

			logToErrorandRunLogs("targetKeyManagerFQDN validation error: " + targetKeyManagerFQDN + " is not an allowed value.  It must either contain 'gspt.net' or be an IP Address");

			throw new Eb2cAPIIllegalArgumentsException("targetKeyManagerFQDN validation error:" + targetKeyManagerFQDN + " is not an allowed value.  It must either contain 'gspt.net' or be an IP Address");	
		}
	}



	public static void validateProtocol(Eb2cAPIAdminUtilitiesPropertiesDO pPropertiesDO) throws Eb2cAPIIllegalArgumentsException {
		// allowed values are are "http" and "https" only 
		if ( !(pPropertiesDO.getProtocol().equalsIgnoreCase("http")) && !(pPropertiesDO.getProtocol().equalsIgnoreCase("https")) ) {

			logToErrorandRunLogs("Protocol validation error: " + pPropertiesDO.getProtocol() + " is not an allowed value");

			throw new Eb2cAPIIllegalArgumentsException("Protocol validation error:" + pPropertiesDO.getProtocol() + " is not an allowed value");	
		}

	}


	private static void logToErrorandRunLogs(String pMessage, Exception pException) {

		logger.info("-----------------------------------------------------------------------------------------");			
		logger.info(pMessage, pException);
		logger.info("-----------------------------------------------------------------------------------------");			

		logger.error("-----------------------------------------------------------------------------------------");			
		logger.error(pMessage, pException);
		logger.error("-----------------------------------------------------------------------------------------");		

	}


	private static void logToErrorandRunLogs(String pMessage) {

		logger.info("-----------------------------------------------------------------------------------------");			
		logger.info(pMessage);
		logger.info("-----------------------------------------------------------------------------------------");			

		logger.error("-----------------------------------------------------------------------------------------");			
		logger.error(pMessage);
		logger.error("-----------------------------------------------------------------------------------------");		

	}
	
}
