package com.gsipartners.eb2c.api.adminutilities.command.impl;


import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.log4j.Logger;

import com.gsipartners.eb2c.api.adminutilities.command.CommonServiceCalls;
import com.gsipartners.eb2c.api.adminutilities.command.UtilityCommandConstants;
import com.gsipartners.eb2c.api.adminutilities.dataobjects.Eb2cAPIAdminUtilitiesPropertiesDO;
import com.gsipartners.eb2c.api.adminutilities.dataobjects.ExecutionContext;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIAdminUtilitiesException;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIIllegalArgumentsException;
import com.gsipartners.eb2c.api.adminutilities.interfaces.UtilitiesCommand;
import com.gsipartners.eb2c.api.adminutilities.utilities.HTTPConnectionManagerBuilder;
import com.gsipartners.eb2c.api.adminutilities.utilities.ParameterValidations;

public class MapEntryDelete implements UtilitiesCommand {

	/**
	 * Logger.
	 */
	private static Logger logger = Logger.getLogger(MapEntryDelete.class);
	
	@Override
	public void runCommand(Eb2cAPIAdminUtilitiesPropertiesDO pPropertiesDO) throws Eb2cAPIAdminUtilitiesException {
		
        logger.info("-----------------------------------------------------------------------------------------");
        logger.info("Beginning execution of MapEntryDelete.runCommand()");
        logger.info("-----------------------------------------------------------------------------------------");
        
        // need to validate inputs for this function
        validateProperites(pPropertiesDO);
        
        
        ExecutionContext executionContext = new ExecutionContext();
        
        CommonServiceCalls commonServiceCalls = new CommonServiceCalls();
        
        executionContext.setPropertiesDO(pPropertiesDO);
        
		//get the connection manager
        executionContext.setHttpsConnectionManager((ThreadSafeClientConnManager) HTTPConnectionManagerBuilder.createThreadSafeConnectionManager());

		//instantiate a HTTPClient
        executionContext.setHttpClient(new DefaultHttpClient(executionContext.getHttpsConnectionManager())); 

        //configure the HttpClient for this usage
        executionContext = commonServiceCalls.setupHttpClient(executionContext);  

        
		executionContext.ServiceURI = new StringBuffer();
		
		//create the service call URI
		executionContext.ServiceURI.append(UtilityCommandConstants.COMMAND_MAP_ENTRY_DELETE_URI)
        .append(UtilityCommandConstants.CONTENT_STRING_QUESTIONMARK)
        .append(UtilityCommandConstants.PARAMETER_MAP_NAME).append(UtilityCommandConstants.CONTENT_STRING_EQUALS).append(executionContext.propertiesDO.getMapName())        
        .append(UtilityCommandConstants.CONTENT_STRING_AMPERSAND)
        .append(UtilityCommandConstants.PARAMETER_KEY).append(UtilityCommandConstants.CONTENT_STRING_EQUALS).append(executionContext.propertiesDO.getApiKey());
		
		// Execute the service call
		executionContext = commonServiceCalls.executeServiceCall(executionContext, "Delete the Map Entry Data on target Key Manager");

        
        logger.info("-----------------------------------------------------------------------------------------");
        logger.info("Completed execution of MapEntryDelete.runCommand()");
        logger.info("-----------------------------------------------------------------------------------------");

        
	}

	
	
	/**
	 * validator.
	 * 
	 * @param pPropertiesDO the setup properties of the run
	 * @throws Eb2cAPIIllegalArgumentsException general exception 
	 */
	@Override
	public void validateProperites(Eb2cAPIAdminUtilitiesPropertiesDO pPropertiesDO)	throws Eb2cAPIIllegalArgumentsException {

		ParameterValidations.validateProtocol(pPropertiesDO);
		ParameterValidations.validateTargetKeyManagerFQDN(pPropertiesDO);
		ParameterValidations.validateTargetKeyManagerPort(pPropertiesDO);
		ParameterValidations.validateIsNotZeroLengthString("keyManagerUserId", pPropertiesDO.getKeyManagerUserId());
		ParameterValidations.validateIsNotZeroLengthString("keyManagerPassword", pPropertiesDO.getKeyManagerPassword());
		ParameterValidations.validateIsNotZeroLengthString("MapName", pPropertiesDO.getMapName());
		ParameterValidations.validateIsNotZeroLengthString("API Key", pPropertiesDO.getApiKey());
		
	}


}
