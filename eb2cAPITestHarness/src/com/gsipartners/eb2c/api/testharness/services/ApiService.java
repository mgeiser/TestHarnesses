package com.gsipartners.eb2c.api.testharness.services;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import com.gsipartners.eb2c.api.testharness.Eb2cAPIServiceCallConstants;
import com.gsipartners.eb2c.api.testharness.Eb2cAPITestHarness;
import com.gsipartners.eb2c.api.testharness.dataobjects.Eb2cAPITestHarnessPropertiesDO;
import com.gsipartners.eb2c.api.testharness.dataobjects.Eb2cAPITestHarnessContextDO;
import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;

/**
 * Abstract class for implementation classes that run create the XMl and run the service call.
 *
 */
abstract class ApiService {
	
	public Eb2cAPIServiceCallConstants.SERVICE_CALLS serviceCall = null;
    public Eb2cAPITestHarnessContextDO serviceContext = null;
	public Eb2cAPITestHarnessPropertiesDO serviceProperties = null;
	public DefaultHttpClient httpClient = null;
	public String serviceURI = null;
	
	//setup the logger for the application error logs and run logs
	private static Logger logger = Logger.getLogger(ApiService.class);


	//default constructor
	/**
	 *
	 *
	 * @param pServiceContext Service Request Context object
	 * @param pserviceProperties Properties file for the run
	 * @param pHttpClient The DefaultHttpClient to use
	 * @throws Eb2cAPITestHarnessException
	 */
	public ApiService(Eb2cAPIServiceCallConstants.SERVICE_CALLS pServiceCall,
	        Eb2cAPITestHarnessContextDO pServiceContext, 
				Eb2cAPITestHarnessPropertiesDO pserviceProperties,
				DefaultHttpClient pHttpClient) throws Eb2cAPITestHarnessException {

		//validate inputs

       if (pServiceCall == null) {
            logger.error("Error during input validation; pServiceCall was null");
            throw new Eb2cAPITestHarnessException("Error during input validation; pServiceCall was null");
       }
	       
	   if (pServiceContext == null) {
	       logger.error("Error during input validation; pServiceContext was null");
	       throw new Eb2cAPITestHarnessException("Error during input validation; pServiceContext was null");
	   }

	   if (pserviceProperties == null) {
	       logger.error("Error during input validation; pserviceProperties was null");
	       throw new Eb2cAPITestHarnessException("Error during input validation; pserviceProperties was null");
	   }

	   if (pHttpClient == null) {
	       logger.error("Error during input validation; pHttpClient was null");
	       throw new Eb2cAPITestHarnessException("Error during input validation; pHttpClient was null");
	   }

		//set parameters
		this.serviceCall = pServiceCall;
		this.serviceContext = pServiceContext;
		this.serviceProperties = pserviceProperties;
		this.httpClient = pHttpClient;
	}


	/**
	 * Definition of method use by Factory class to return a consistent interface for any number of data providers.
	 * <P>
	 * @param pServiceContext the String from the data file to parse
	 * @return Eb2cAPITestHarnessContextDO
	 * @throws Eb2cAPITestHarnessException The generic Application Exception
	 */
	public abstract Eb2cAPITestHarnessContextDO callService() throws Eb2cAPITestHarnessException;


    /**
     * @return the serviceCall
     */
    public Eb2cAPIServiceCallConstants.SERVICE_CALLS getServiceCall() {
        return serviceCall;
    }

    //no setter for serviceCall
    
    /**
     * @return the serviceContext
     */
    public Eb2cAPITestHarnessContextDO getServiceContext() {
        return serviceContext;
    }


    /**
     * @param serviceContext the serviceContext to set
     */
    public void setServiceContext(Eb2cAPITestHarnessContextDO serviceContext) {
        this.serviceContext = serviceContext;
    }


    /**
     * @return the serviceProperties
     */
    public Eb2cAPITestHarnessPropertiesDO getServiceProperties() {
        return serviceProperties;
    }


    /**
     * @param serviceProperties the serviceProperties to set
     */
    public void setServiceProperties(
            Eb2cAPITestHarnessPropertiesDO serviceProperties) {
        this.serviceProperties = serviceProperties;
    }


    /**
     * @return the httpClient
     */
    public DefaultHttpClient getHttpClient() {
        return httpClient;
    }


    /**
     * @param httpClient the httpClient to set
     */
    public void setHttpClient(DefaultHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    /**
     * @return the serviceURI
     */
    public String getServiceURI() {
        return serviceURI;
    }


    /**
     * @param serviceURI the serviceURI to set
     */
    public void setServiceURI(String serviceURI) {
        this.serviceURI = serviceURI;
    }

	
	
}
