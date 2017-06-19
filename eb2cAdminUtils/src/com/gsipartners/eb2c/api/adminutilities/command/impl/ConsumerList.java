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

/**
 * Implementation class to create a new consumer Id. 
 *
 */
public class ConsumerList implements UtilitiesCommand {

	/**
	 * Logger.
	 */
	private static Logger logger = Logger.getLogger(ConsumerList.class);
	
	/**
	 * Default Constructor.
	 */
	public ConsumerList() {
		
	}
	
	@Override
	public void runCommand(Eb2cAPIAdminUtilitiesPropertiesDO pPropertiesDO) throws Eb2cAPIAdminUtilitiesException {

        logger.info("-----------------------------------------------------------------------------------------");
        logger.info("Beginning execution of CreateConsumerId.runCommand()");
        logger.info("-----------------------------------------------------------------------------------------");

        // validate the inputs in pPropertyDO meet the requirements if this Command
        // log the issue and throw and Eb2cAPIIllegalArgumentsException if there are issues
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
		executionContext.ServiceURI.append(UtilityCommandConstants.COMMAND_CONSUMER_LIST_URI);
	
		
		// Execute the service call
		executionContext = commonServiceCalls.executeServiceCall(executionContext, "List all the Consumers provisioned in Apigee on target Key Manager");

        
        
        logger.info("-----------------------------------------------------------------------------------------");
        logger.info("Completed execution of CreateConsumerId.runCommand()");
        logger.info("-----------------------------------------------------------------------------------------");

        
	}

	/**
	 * The validate properties looks at the data passed in via the properties files and validates
	 * Some of the checks are hard coded right now and may need to be dynamic, 
	 * but for right now they are good.
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
	}


}
