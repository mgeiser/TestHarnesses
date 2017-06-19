package com.gsipartners.eb2c.api.testharness.utilities;

import org.apache.log4j.Logger;

import com.gsipartners.eb2c.api.testharness.Eb2cAPIServiceCallConstants;
import com.gsipartners.eb2c.api.testharness.Eb2cAPITestHarness;
import com.gsipartners.eb2c.api.testharness.dataobjects.Eb2cAPITestHarnessPropertiesDO;
import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;


public class Eb2cApiUriBuilder {

	
	
	//setup the logger for the application error logs and run logs
	private static Logger logger = Logger.getLogger(Eb2cApiUriBuilder.class);

	
	public static String buildServiceURI(Eb2cAPIServiceCallConstants.SERVICE_CALLS pServiceType, 
			Eb2cAPITestHarnessPropertiesDO pPropertiesFile, 
			Eb2cAPIServiceCallConstants.SERVICE_CALL_FORMATS pFileType) throws Eb2cAPITestHarnessException {
		
		StringBuilder serviceURI = new StringBuilder();

		//create the URL
		serviceURI.append(Eb2cAPIServiceCallConstants.API_SERVICE_PROTOCOL)
		.append(pPropertiesFile.getApiFQDN())
		.append(Eb2cAPIServiceCallConstants.API_SERVICE_URI_SLASH)
		.append(Eb2cAPIServiceCallConstants.API_VERSION_URI_PREFIX)
		.append(pPropertiesFile.getApiVersion())
		.append(Eb2cAPIServiceCallConstants.API_SERVICE_URI_SLASH)
		.append(Eb2cAPIServiceCallConstants.API_CHANNEL_STORES)
		.append(Eb2cAPIServiceCallConstants.API_SERVICE_URI_SLASH)
		.append(pPropertiesFile.getStoreID())
		.append(Eb2cAPIServiceCallConstants.API_SERVICE_URI_SLASH);
		
		//Add the correct service call URI based on the enum specified as a parameter 
		switch(pServiceType) {
		
			case SERVICE_ADDRESS_VALIDATE: 
				serviceURI.append(Eb2cAPIServiceCallConstants.SERVICE_ADDRESS_VALIDATE);
				break;
				
//TODO
//add 20 more service call CASEs here				
				
			default:
				//this is bad...throw an exception
				logger.error("Unsupported Eb2cAPIServiceCallConstants.SERVICE_CALLS enum used: "+ pServiceType);
				throw new Eb2cAPITestHarnessException("Unsupported Eb2cAPIServiceCallConstants.SERVICE_CALLS enum used: "+ pServiceType);
		
		}
		
		//add the period for the file type specifier
		serviceURI.append(Eb2cAPIServiceCallConstants.API_FILE_TYPE_SEPARATOR);
		
		//Add the correct service call filetype extension to the URI based on the enum specified as a parameter
		switch(pFileType) {
		
		case XML: 
			serviceURI.append(Eb2cAPIServiceCallConstants.FORMAT_XML);
			break;
			
		case JSON: 
			serviceURI.append(Eb2cAPIServiceCallConstants.FORMAT_JSON);
			break;
	
		default:
			//this is bad...throw an exception
			logger.error("Unsupported Eb2cAPIServiceCallConstants.SERVICE_CALL_FORMATS enum used: "+ pFileType);
			throw new Eb2cAPITestHarnessException("Unsupported Eb2cAPIServiceCallConstants.SERVICE_CALL_FORMATS enum used: "+ pFileType);

	}
		
		//log the URL created
		logger.info("");
		logger.info("-----------------------------------------------------------------------------------------");
		logger.info("Service call URI: "+ serviceURI.toString());
		logger.info("-----------------------------------------------------------------------------------------");
		
		
		
		
		
		
		return serviceURI.toString();
	}
	
	
	
	
}
