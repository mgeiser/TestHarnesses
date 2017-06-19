package com.gsipartners.eb2c.api.testharness.dataobjects;



/*	
 * Value Object to store the values from the properties files    
 * 
 * Design Decision: Do not need to implement Serializable...
 * 
*/

/**
 * Value Object to store the values from the Property file for the GSI eb2c API Test Harness.
 * 
 * @author Michael Geiser
 *
 */
public final class Eb2cAPITestHarnessPropertiesDO {

	
	private String apiFQDN = "";
	private String storeID = "";
	private String apiVersion = "";
	private String apiKey = "";

	/*
	apiFQDN=developer-na.gsipartners.com
	storeID=RXYUS
	apiversion=1.0
	apikey=6582acf75e8360f63f144032e8910e8c
	*/

	
	/**
	 * Overrides the toString.
	 * 
	 * @return the string of data in the value object
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer returnValue = new StringBuffer();
		
		//really paranoid--
		returnValue.append("\n\nPostalCodeParserPropertiesVO")
			.append("\n FQDN of the API: ").append(apiFQDN) 
			.append("\n Store Id: ").append(storeID)
			.append("\n API Version: ").append(apiVersion)
			.append("\n APIKey: ").append(apiKey).append("\n");

		return returnValue.toString();
	}

	/**
	 * @return the apiFQDN
	 */
	public String getApiFQDN() {
		return apiFQDN;
	}

	/**
	 * @param apiFQDN the apiFQDN to set
	 */
	public void setApiFQDN(String apiFQDN) {
		this.apiFQDN = apiFQDN;
	}

	/**
	 * @return the storeID
	 */
	public String getStoreID() {
		return storeID;
	}

	/**
	 * @param storeID the storeID to set
	 */
	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

	/**
	 * @return the apiVersion
	 */
	public String getApiVersion() {
		return apiVersion;
	}

	/**
	 * @param apiVersion the apiVersion to set
	 */
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	/**
	 * @return the apiKey
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * @param apiKey the apiKey to set
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	
	
	
	
}
