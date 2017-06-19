package com.gsipartners.eb2c.api.adminutilities.dataobjects;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.protocol.BasicHttpContext;

import com.gsipartners.eb2c.api.adminutilities.command.UtilityCommandConstants;
import com.gsipartners.eb2c.api.adminutilities.dataobjects.Eb2cAPIAdminUtilitiesPropertiesDO;



public class ExecutionContext {
	
	public UtilityCommandConstants.UTILITY_COMMAND utilityCommand = null;
	
	public Eb2cAPIAdminUtilitiesPropertiesDO propertiesDO = null;
	
	//Will hold a Connection Manager that works with via HTTPS and is thread safe
	public ThreadSafeClientConnManager httpsConnectionManager = null;

	public HttpHost targetHost = null;

	public BasicHttpContext localcontext = new BasicHttpContext();

	public DefaultHttpClient httpClient = null;

	public StringBuffer ServiceURI = null;
	
	public String APIKey = null;
	
	public int lastResponseStatusCode = 0;
	
	public String lastResponseBody = null; 

	
	
	
	public ExecutionContext() {
		// nothing to do in the constructor	
	}
	

	/**
	 * @return the utilityCommand
	 */
	public UtilityCommandConstants.UTILITY_COMMAND getUtilityCommand() {
		return utilityCommand;
	}


	/**
	 * @param utilityCommand the utilityCommand to set
	 */
	public void setUtilityCommand(
			UtilityCommandConstants.UTILITY_COMMAND utilityCommand) {
		this.utilityCommand = utilityCommand;
	}

	/**
	 * @return the propertiesDO
	 */
	public Eb2cAPIAdminUtilitiesPropertiesDO getPropertiesDO() {
		return propertiesDO;
	}

	/**
	 * @param propertiesDO the propertiesDO to set
	 */
	public void setPropertiesDO(Eb2cAPIAdminUtilitiesPropertiesDO propertiesDO) {
		this.propertiesDO = propertiesDO;
	}

	/**
	 * @return the httpsConnectionManager
	 */
	public ThreadSafeClientConnManager getHttpsConnectionManager() {
		return httpsConnectionManager;
	}

	/**
	 * @param httpsConnectionManager the httpsConnectionManager to set
	 */
	public void setHttpsConnectionManager(
			ThreadSafeClientConnManager httpsConnectionManager) {
		this.httpsConnectionManager = httpsConnectionManager;
	}

	/**
	 * @return the targetHost
	 */
	public HttpHost getTargetHost() {
		return targetHost;
	}

	/**
	 * @param targetHost the targetHost to set
	 */
	public void setTargetHost(HttpHost targetHost) {
		this.targetHost = targetHost;
	}

	/**
	 * @return the localcontext
	 */
	public BasicHttpContext getLocalcontext() {
		return localcontext;
	}

	/**
	 * @param localcontext the localcontext to set
	 */
	public void setLocalcontext(BasicHttpContext localcontext) {
		this.localcontext = localcontext;
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
	 * @return the testServiceURI
	 */
	public StringBuffer getTestServiceURI() {
		return ServiceURI;
	}

	/**
	 * @param testServiceURI the testServiceURI to set
	 */
	public void setTestServiceURI(StringBuffer testServiceURI) {
		this.ServiceURI = testServiceURI;
	}

	/**
	 * @return the aPIKey
	 */
	public String getAPIKey() {
		return APIKey;
	}

	/**
	 * @param aPIKey the aPIKey to set
	 */
	public void setAPIKey(String aPIKey) {
		APIKey = aPIKey;
	}

	/**
	 * @return the lastResponseStatusCode
	 */
	public int getLastResponseStatusCode() {
		return lastResponseStatusCode;
	}

	/**
	 * @param lastResponseStatusCode the lastResponseStatusCode to set
	 */
	public void setLastResponseStatusCode(int lastResponseStatusCode) {
		this.lastResponseStatusCode = lastResponseStatusCode;
	}

	/**
	 * @return the lastResponseBody
	 */
	public String getLastResponseBody() {
		return lastResponseBody;
	}

	/**
	 * @param lastResponseBody the lastResponseBody to set
	 */
	public void setLastResponseBody(String lastResponseBody) {
		this.lastResponseBody = lastResponseBody;
	}
	
	
	
	
}
