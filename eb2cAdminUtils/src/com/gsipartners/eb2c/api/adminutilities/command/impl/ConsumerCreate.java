package com.gsipartners.eb2c.api.adminutilities.command.impl;


import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.log4j.Logger;

import com.gsicommerce.xpath.XPathHelper;
import com.gsipartners.eb2c.api.adminutilities.command.CommonServiceCalls;
import com.gsipartners.eb2c.api.adminutilities.command.UtilityCommandConstants;
import com.gsipartners.eb2c.api.adminutilities.dataobjects.Eb2cAPIAdminUtilitiesPropertiesDO;
import com.gsipartners.eb2c.api.adminutilities.dataobjects.ExecutionContext;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIAdminUtilitiesException;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIAdminUtilitiesGeneralException;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIIllegalArgumentsException;
import com.gsipartners.eb2c.api.adminutilities.interfaces.UtilitiesCommand;
import com.gsipartners.eb2c.api.adminutilities.utilities.HTTPConnectionManagerBuilder;
import com.gsipartners.eb2c.api.adminutilities.utilities.ParameterValidations;

/**
 * Implementation class to create a new consumer Id. 
 *
 */
public class ConsumerCreate implements UtilitiesCommand {

	/**
	 * Logger.
	 */
	private static Logger logger = Logger.getLogger(ConsumerCreate.class);
	
	/**
	 * Default Constructor.
	 */
	public ConsumerCreate() {
		
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
        
        //*****************************************************************
        //Call the API to create the consumerId 
		executionContext.ServiceURI = new StringBuffer();

		//create the service call URI
		executionContext.ServiceURI.append(UtilityCommandConstants.COMMAND_CONSUMER_CREATE_URI)
        .append(UtilityCommandConstants.CONTENT_STRING_QUESTIONMARK)
        .append(UtilityCommandConstants.PARAMETER_CONSUMERID).append(UtilityCommandConstants.CONTENT_STRING_EQUALS)
        .append(executionContext.propertiesDO.getConsumerId());

		// Execute the service call
		executionContext = commonServiceCalls.executeServiceCall(executionContext, "Create the Consumer Id on target Key Manager");
        
        


        //*****************************************************************
        //Call the API to approve the consumerId 
		executionContext.ServiceURI = new StringBuffer();

		//create the service call URI
		executionContext.ServiceURI.append(UtilityCommandConstants.COMMAND_CONSUMER_APPROVE_URI)
        .append(UtilityCommandConstants.CONTENT_STRING_QUESTIONMARK)
        .append(UtilityCommandConstants.PARAMETER_CONSUMERID).append(UtilityCommandConstants.CONTENT_STRING_EQUALS).append(executionContext.propertiesDO.getConsumerId());
        
		// Execute the service call
		executionContext = commonServiceCalls.executeServiceCall(executionContext, "Approve the new Consumer Id");

        
		
        //*****************************************************************
        //Register the AppId and get the APIKey added to the Execution Context 
		executionContext.ServiceURI = new StringBuffer();

		//create the service call URI
		executionContext.ServiceURI.append(UtilityCommandConstants.COMMAND_CLIENTAPP_REGISTER_URI)
        .append(UtilityCommandConstants.CONTENT_STRING_QUESTIONMARK)
        .append(UtilityCommandConstants.PARAMETER_CLIENT_APP_NAME).append(UtilityCommandConstants.CONTENT_STRING_EQUALS).append(executionContext.propertiesDO.getClientAppName())        
        .append(UtilityCommandConstants.CONTENT_STRING_AMPERSAND)
        .append(UtilityCommandConstants.PARAMETER_CONSUMERID).append(UtilityCommandConstants.CONTENT_STRING_EQUALS).append(executionContext.propertiesDO.getConsumerId())
        .append(UtilityCommandConstants.CONTENT_STRING_AMPERSAND)
        .append(UtilityCommandConstants.PARAMETER_API_GROUP_NAME).append(UtilityCommandConstants.CONTENT_STRING_EQUALS).append(executionContext.propertiesDO.getApiGroupName());

		// Execute the service call
		executionContext = commonServiceCalls.executeServiceCall(executionContext, "Register the App on target Key Manager");

		//load the Response Body XML and get the Api Key
		XPathHelper xpathHelper = new XPathHelper(executionContext.getLastResponseBody());

		if (xpathHelper.doesNodeExist("//clientapp/consumer/key")) {
			executionContext.setAPIKey(xpathHelper.read("//clientapp/consumer/key"));
			logger.info("-----------------------------------------------------------------------------------------");			
			logger.info("APIKey = " + executionContext.APIKey);
			logger.info("-----------------------------------------------------------------------------------------");			

		} else {
		
			commonServiceCalls.logToErrorandRunLogs("Could not find key in response");

			throw new Eb2cAPIAdminUtilitiesGeneralException("Call to Register App and ConsumerId failed becuase API key was not found in Response");
		}

  
        

        //*****************************************************************
        //Add Entry for the Key in the Key manager Map        
		executionContext.ServiceURI = new StringBuffer();

		//create the service call URI
		executionContext.ServiceURI.append(UtilityCommandConstants.COMMAND_MAP_ENTRY_ADD_URI)
        .append(UtilityCommandConstants.CONTENT_STRING_QUESTIONMARK)
        .append(UtilityCommandConstants.PARAMETER_MAP_NAME).append(UtilityCommandConstants.CONTENT_STRING_EQUALS).append(executionContext.propertiesDO.getMapName())        
        .append(UtilityCommandConstants.CONTENT_STRING_AMPERSAND)
        .append(UtilityCommandConstants.PARAMETER_KEY).append(UtilityCommandConstants.CONTENT_STRING_EQUALS).append(executionContext.getAPIKey())
        .append(UtilityCommandConstants.CONTENT_STRING_AMPERSAND)
        .append(UtilityCommandConstants.PARAMETER_VALUE).append(UtilityCommandConstants.CONTENT_STRING_EQUALS).append(executionContext.propertiesDO.getAllowedStores())
        .append(UtilityCommandConstants.CONTENT_STRING_AMPERSAND)
        .append(UtilityCommandConstants.PARAMETER_VALUE1).append(UtilityCommandConstants.CONTENT_STRING_EQUALS).append(executionContext.propertiesDO.getApiVersion())
        .append(UtilityCommandConstants.CONTENT_STRING_AMPERSAND)
        .append(UtilityCommandConstants.PARAMETER_VALUE2).append(UtilityCommandConstants.CONTENT_STRING_EQUALS).append(executionContext.propertiesDO.getAllowedStores());


		// Execute the service call
		executionContext = commonServiceCalls.executeServiceCall(executionContext, "Register the App on target Key Manager");		
	
        



        
        
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
		ParameterValidations.validateIsNotZeroLengthString("consumerId", pPropertiesDO.getConsumerId());
		ParameterValidations.validateEqualsCaseSensitive("clientAppName", pPropertiesDO.getClientAppName(), "eb2c");
		ParameterValidations.validateEqualsCaseSensitive("apiGroupName", pPropertiesDO.getApiGroupName(), "Production");
		ParameterValidations.validateEqualsCaseSensitive("storesMap", pPropertiesDO.getMapName(), "storesMap");
		ParameterValidations.validateEqualsCaseSensitive("apiVersion", pPropertiesDO.getApiVersion(), "v1.0");
		ParameterValidations.validateIsNotZeroLengthString("allowedStores", pPropertiesDO.getAllowedStores());		
		
	}


}
