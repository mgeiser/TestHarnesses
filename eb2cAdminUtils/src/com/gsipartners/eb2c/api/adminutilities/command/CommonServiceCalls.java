package com.gsipartners.eb2c.api.adminutilities.command;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.gsipartners.eb2c.api.adminutilities.dataobjects.ExecutionContext;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIAdminUtilitiesException;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIAdminUtilitiesGeneralException;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIServiceCall300StatusException;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIServiceCall400StatusException;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIServiceCall401StatusException;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIServiceCall403StatusException;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIServiceCall500StatusException;



/**
 * Service calls and common functions used in the API. 
 * <P>
 *
 */
public class CommonServiceCalls {

	/**
	 * The logger.
	 */
	private static Logger logger = Logger.getLogger(CommonServiceCalls.class);

	private static final int HTTP_STATUS_CODE_300 = 300;
	private static final int HTTP_STATUS_CODE_400 = 400;
	private static final int HTTP_STATUS_CODE_401 = 401;
	private static final int HTTP_STATUS_CODE_403 = 403;
	private static final int HTTP_STATUS_CODE_500 = 400;
	private static final int HTTP_STATUS_CODE_599 = 599;
	private static final String NEW_LINE = "\n";
	
	/**
	 * Default constructor.
	 * 
	 * @throws Eb2cAPIAdminUtilitiesException Generic
	 */
	public CommonServiceCalls()  throws Eb2cAPIAdminUtilitiesException {
		logger.info(UtilityCommandConstants.LOG_FILE_SEPARATOR);
		logger.info("Executing Constructor in CommonServiceCalls");
		logger.info(UtilityCommandConstants.LOG_FILE_SEPARATOR);
	}

	/*
	
	Create Map (This is only done when creating a new Map)
	http://secdevapg03.us.gspt.net:8080/map/add?map_name=storesMap 

	Get Attributes of an entry in a map
	http://secdevapg03.us.gspt.net:8080/map/entry/attributes?map_name=storesMap&key=3ce14631755eb84a0c7770ff50af3d59
	
	*/


	/**
	 * Run Logging function.
	 * @param pMessage The message to log
	 */
	public void logToRunLog(String pMessage) {

		logger.info(UtilityCommandConstants.LOG_FILE_SEPARATOR);			
		logger.info(pMessage);
		logger.info(UtilityCommandConstants.LOG_FILE_SEPARATOR + NEW_LINE);			

	}
	

	/**
	 * Logging function.
	 * @param pMessage The message to log
	 * @param pException The Exception to log
	 */
	public void logToErrorandRunLogs(String pMessage, Exception pException) {

		logger.info(UtilityCommandConstants.LOG_FILE_SEPARATOR);			
		logger.info(pMessage, pException);
		logger.info(UtilityCommandConstants.LOG_FILE_SEPARATOR + NEW_LINE);			

		logger.error(UtilityCommandConstants.LOG_FILE_SEPARATOR);			
		logger.error(pMessage, pException);
		logger.error(UtilityCommandConstants.LOG_FILE_SEPARATOR + NEW_LINE);		

	}


	/**
	 * Logging function.
	 * @param pMessage The message to log
	 */
	public void logToErrorandRunLogs(String pMessage) {
		
		//I **could** cut out a few lines here by calling logToErrorandRunLogs(pMessage, null) but...
		
		logger.info(UtilityCommandConstants.LOG_FILE_SEPARATOR);			
		logger.info(pMessage);
		logger.info(UtilityCommandConstants.LOG_FILE_SEPARATOR + NEW_LINE);			

		logger.error(UtilityCommandConstants.LOG_FILE_SEPARATOR);			
		logger.error(pMessage);
		logger.error(UtilityCommandConstants.LOG_FILE_SEPARATOR + NEW_LINE);		

	}

	
	
	/**
	 * 
	 * 
	 * 
	 * @param executionContext The Execution Context 
	 * @return The Execution Context
	 * @throws Eb2cAPIAdminUtilitiesException Generic Exception
	 */
	public ExecutionContext setupHttpClient(ExecutionContext executionContext) throws Eb2cAPIAdminUtilitiesException {
	
		//Initialize targetKeyManagerPort 
        int targetKeyManagerPort = 0;
        
        try {
        	targetKeyManagerPort = Integer.parseInt(executionContext.propertiesDO.getTargetKeyManagerPort());
        } catch (NumberFormatException nfe) {
    		
        	logToErrorandRunLogs("targetKeyManagerPort from Properties file was not an int.", nfe);

			throw new Eb2cAPIAdminUtilitiesGeneralException("targetKeyManagerPort from Properties file was not an int.", nfe);
        }
        
        executionContext.targetHost = new HttpHost(executionContext.propertiesDO.getTargetKeyManagerFQDN(), targetKeyManagerPort, executionContext.propertiesDO.getProtocol());
        
        //setup Basic Auth
        executionContext.httpClient.getCredentialsProvider().setCredentials(
        		new AuthScope(executionContext.targetHost.getHostName(), executionContext.targetHost.getPort()), 
        		new UsernamePasswordCredentials(executionContext.propertiesDO.getKeyManagerUserId(), executionContext.propertiesDO.getKeyManagerPassword()));
        // Create AuthCache instance
        AuthCache authCache = new BasicAuthCache();
        // Generate BASIC scheme object and add it to the local auth cache
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(executionContext.targetHost, basicAuth);

        // Add AuthCache to the execution context
        executionContext.localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);
        
        return executionContext;
	}
	

	
	/**

	 * @param executionContext The Execution Context
	 * @param functionNameUsedInLogsMessage Print friendly name to use in the logs
	 * @return The Execution Context
	 * @throws Eb2cAPIAdminUtilitiesException Generic Exception
	 */
	public ExecutionContext executeServiceCall(ExecutionContext executionContext, String functionNameUsedInLogsMessage)
			throws Eb2cAPIAdminUtilitiesException {
		//set the service URI to access
        HttpGet httpGet = new HttpGet(executionContext.ServiceURI.toString());

        //Log the Map Entry Delete request
        logger.info("");
        logger.info(UtilityCommandConstants.LOG_FILE_SEPARATOR);
        logger.info(functionNameUsedInLogsMessage);
        logger.info(executionContext.targetHost + " " + executionContext.ServiceURI.toString());
        logger.info(UtilityCommandConstants.LOG_FILE_SEPARATOR);
         
        HttpResponse response = null;
    	HttpEntity entity = null;
    	
        //send the request
        try {
        	response = executionContext.httpClient.execute(executionContext.targetHost, httpGet, executionContext.localcontext);

        	//set up to process the response
        	entity = response.getEntity();
        	String responseTest = null;
        	//dump the response to the console and the log
        	if (entity != null) {
        		byte[] bytes = EntityUtils.toByteArray(entity);
        		responseTest = new String(bytes);
        		executionContext.setLastResponseBody(responseTest);
        		//dump the result and content to the run log
        		logger.info("");
        		logger.info(UtilityCommandConstants.LOG_FILE_SEPARATOR);
        		logger.info("Status Code of the GET : " + response.getStatusLine());
        		logger.info("");            
        		logger.info("Response:");
        		logger.info(bytes.length + " bytes read");
        		logger.info(responseTest);
        		logger.info(UtilityCommandConstants.LOG_FILE_SEPARATOR + NEW_LINE + NEW_LINE);
        		
        		executionContext.setLastResponseStatusCode(response.getStatusLine().getStatusCode());
        		
        		int serviceCallHTTPStatusCode = executionContext.getLastResponseStatusCode();
        		
        		// Check for Authentication Failure - special case of 400 Series
        		if (serviceCallHTTPStatusCode == HTTP_STATUS_CODE_401) {

        			logToErrorandRunLogs("Call to " + functionNameUsedInLogsMessage + " failed Authentication with a status code of " + executionContext.getLastResponseStatusCode());
        	
        			throw new Eb2cAPIServiceCall401StatusException("Call to " + functionNameUsedInLogsMessage + " failed Authentication with a status code of " + executionContext.getLastResponseStatusCode());
        		}
        		
        		// Check for Authorization errors
        		if (serviceCallHTTPStatusCode == HTTP_STATUS_CODE_403) {

        			logToErrorandRunLogs("Call to " + functionNameUsedInLogsMessage + " failed Authorization for the URI with a status code of " + executionContext.getLastResponseStatusCode());
        	
        			throw new Eb2cAPIServiceCall403StatusException("Call to " + functionNameUsedInLogsMessage + " failed Authorization for the URI with a status code of " + executionContext.getLastResponseStatusCode());
        		}
        		

        		// Check for other 400 series errors
        		if ((serviceCallHTTPStatusCode >= HTTP_STATUS_CODE_400) && (serviceCallHTTPStatusCode < HTTP_STATUS_CODE_500)) {

        			logToErrorandRunLogs("Call to " + functionNameUsedInLogsMessage + " failed with a status code of " + executionContext.getLastResponseStatusCode());
        	
        			throw new Eb2cAPIServiceCall400StatusException("Call to " + functionNameUsedInLogsMessage + " failed with a status code of " + executionContext.getLastResponseStatusCode());
        		}
        		
        		
        		//if we get a 300 series error, the request has not successfully completed
        		if ((serviceCallHTTPStatusCode >= HTTP_STATUS_CODE_300) && (serviceCallHTTPStatusCode < HTTP_STATUS_CODE_400)) {

        			logToErrorandRunLogs("Call to " + functionNameUsedInLogsMessage + " failed with a status code of " + executionContext.getLastResponseStatusCode());
        	
        			throw new Eb2cAPIServiceCall300StatusException("Call to " + functionNameUsedInLogsMessage + " failed with a status code of " + executionContext.getLastResponseStatusCode());
        		}
        		
        		
        		// Check for 500 Series errors
        		if ((serviceCallHTTPStatusCode >= HTTP_STATUS_CODE_500) && (serviceCallHTTPStatusCode <= HTTP_STATUS_CODE_599)) {

        			logToErrorandRunLogs("Call to " + functionNameUsedInLogsMessage + " failed with a status code of " + executionContext.getLastResponseStatusCode());
        	
        			throw new Eb2cAPIServiceCall500StatusException("Call to " + functionNameUsedInLogsMessage + " failed with a status code of " + executionContext.getLastResponseStatusCode());
        		}
        		
        	} else {
        		//WTF to do here?
        		//under what conditions would "entity" be null?
        	}

        	//tidy up
        	EntityUtils.consume(entity);
        	
        } catch (ClientProtocolException e) {
        	
        	logToErrorandRunLogs("ClientProtocolException", e);
        	throw new Eb2cAPIAdminUtilitiesGeneralException("ClientProtocolException", e);
        	
        } catch (IOException e) {
        	
        	logToErrorandRunLogs("IOException", e);
        	throw new Eb2cAPIAdminUtilitiesGeneralException("IOException", e);
        	
        }
        
        return executionContext;
	}
	
	//end common methods used by all service calls
	//--------------------------------------------------------------------------------------
	
	
}
