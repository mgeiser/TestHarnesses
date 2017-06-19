package com.gsipartners.eb2c.api.adminutilities.dataobjects;



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
public final class Eb2cAPIAdminUtilitiesPropertiesDO {

	
	private String utilityCommand = "";
	private String protocol = "";
	private String targetKeyManagerFQDN = "";
	private String targetKeyManagerPort = "";
	private String keyManagerUserId = "";
	private String keyManagerPassword = "";
	private String consumerId = "";
	private String cn = "";
	private String sn = "";
	private String emailAddress = "";
	private String givenName = "";
	private String cAttribute1 = "";
	private String cAttribute2 = "";
	private String cAttribute3 = "";
	private String clientAppName = "";
	private String apiGroupName = "";
	private String mapName = "";
	private String apiVersion = "";
	private String allowedStores = "";
	private String keyExpirationDate = "";
	private String apiKey = "";

	/*
		utilityCommand = "";					see UtilityCommandConstants.UTILITY_COMMANDS
		protocol = "http"						//http or https
		targetKeyServerFQDN = "sectstapg03.us.gspt.net"  // sectstapg03.us.gspt.net:8080 or secdevapg03.us.gspt.net:8080
		targetKeyServerPort=8080				//port for Key Manager 
		keyManagerUserId = "apigee";
		keyManagerPassword = "apigee123";
		consumerId = "QUIKUS"; 					//Name of the client that will appear in Analytics and reports 
		cn = "";								//? (optional)
		sn = "";								//? (optional)
		emailAdrdess = "";						// email address (optional) 
		givenName = "";							// Given Name; not used for clients(optional)
		cAttribute1 = "";						// ? (optional) 
		cAttribute2 = "";						// ? (optional)
		cAttribute3 = "";						// ? (optional)
		clientAppName = "eb2c";					// "eb2c" is only allowed value
		apiGroupName = "Production";			// "Production" is only allowed value
		storesMap = "storesMap";				// Must match Stores map defined in Key Manager exactly
		apiVersion = "v1.0";					// Must match exactly the API Version value; used for authorization in Apigee     
		allowedStores = "QUKUS,RXYUS";			// Single or Comma separated values of the store codes that will be in the URI; used for authorization in Apigee
		keyExpirationDate
		apiKey									// An API key used in commands needing an API Key...    
	*/

	
	
	/**
	 * Overrides the toString.
	 * 
	 * @return the string of data in the value object
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer returnValue = new StringBuffer();
		
		returnValue.append("\n\nEb2cAPIAdminUtilitiesPropertiesDO")
		.append("\n Specified Utility Command: ").append(utilityCommand)
		.append("\n Protocol: ").append(keyManagerUserId)
		.append("\n Target Key Manager FQDN: ").append(targetKeyManagerFQDN)
		.append("\n Target Key Manager Port: ").append(targetKeyManagerPort)
		.append("\n Key Manager User Id: ").append(keyManagerUserId)
		.append("\n Key Manager Password: ").append("*********")
		.append("\n Consumer Id: ").append(consumerId)
		.append("\n cn: ").append(cn)
		.append("\n sn: ").append(sn)
		.append("\n Email Address: ").append(emailAddress)
		.append("\n Given Name: ").append(givenName)
		.append("\n c_attribute1: ").append(cAttribute1)
		.append("\n c_attribute2: ").append(cAttribute2)
		.append("\n c_attribute3: ").append(cAttribute3)
		.append("\n Client App Name: ").append(clientAppName)
		.append("\n API Group Name: ").append(apiGroupName)
		.append("\n Map Name: ").append(mapName)
		.append("\n Allowed API Version: ").append(apiVersion)
		.append("\n Allowed Stores: ").append(allowedStores)
		.append("\n API Key: ").append(apiKey)
		.append("\n");

		return returnValue.toString();
	}

	
	
	
	
	/**
	 * @return the protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * @return the targetKeyServer
	 */
	public String getTargetKeyManagerFQDN() {
		return targetKeyManagerFQDN;
	}

	/**
	 * @param targetKeyServer the targetKeyServer to set
	 */
	public void setTargetKeyManagerFQDN(String targetKeyManagerFQDN) {
		this.targetKeyManagerFQDN = targetKeyManagerFQDN;
	}

	/**
	 * @return the targetKeyManagerPort
	 */
	public String getTargetKeyManagerPort() {
		return targetKeyManagerPort;
	}

	/**
	 * @param targetKeyManagerPort the targetKeyManagerPort to set
	 */
	public void setTargetKeyManagerPort(String targetKeyManagerPort) {
		this.targetKeyManagerPort = targetKeyManagerPort;
	}


	/**
	 * @return the keyManagerUserId
	 */
	public String getKeyManagerUserId() {
		return keyManagerUserId;
	}

	/**
	 * @param keyManagerUserId the keyManagerUserId to set
	 */
	public void setKeyManagerUserId(String keyManagerUserId) {
		this.keyManagerUserId = keyManagerUserId;
	}

	/**
	 * @return the keyManagerPassword
	 */
	public String getKeyManagerPassword() {
		return keyManagerPassword;
	}

	/**
	 * @param keyManagerPassword the keyManagerPassword to set
	 */
	public void setKeyManagerPassword(String keyManagerPassword) {
		this.keyManagerPassword = keyManagerPassword;
	}

	/**
	 * @return the consumerId
	 */
	public String getConsumerId() {
		return consumerId;
	}

	/**
	 * @param consumerId the consumerId to set
	 */
	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	/**
	 * @return the cn
	 */
	public String getCn() {
		return cn;
	}

	/**
	 * @param cn the cn to set
	 */
	public void setCn(String cn) {
		this.cn = cn;
	}

	/**
	 * @return the sn
	 */
	public String getSn() {
		return sn;
	}

	/**
	 * @param sn the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @return the cAttribute1
	 */
	public String getcAttribute1() {
		return cAttribute1;
	}

	/**
	 * @param cAttribute1 the cAttribute1 to set
	 */
	public void setcAttribute1(String cAttribute1) {
		this.cAttribute1 = cAttribute1;
	}

	/**
	 * @return the cAttribute2
	 */
	public String getcAttribute2() {
		return cAttribute2;
	}

	/**
	 * @param cAttribute2 the cAttribute2 to set
	 */
	public void setcAttribute2(String cAttribute2) {
		this.cAttribute2 = cAttribute2;
	}

	/**
	 * @return the cAttribute3
	 */
	public String getcAttribute3() {
		return cAttribute3;
	}

	/**
	 * @param cAttribute3 the cAttribute3 to set
	 */
	public void setcAttribute3(String cAttribute3) {
		this.cAttribute3 = cAttribute3;
	}

	/**
	 * @return the clientAppName
	 */
	public String getClientAppName() {
		return clientAppName;
	}

	/**
	 * @param clientAppName the clientAppName to set
	 */
	public void setClientAppName(String clientAppName) {
		this.clientAppName = clientAppName;
	}

	/**
	 * @return the apiGroupName
	 */
	public String getApiGroupName() {
		return apiGroupName;
	}

	/**
	 * @param apiGroupName the apiGroupName to set
	 */
	public void setApiGroupName(String apiGroupName) {
		this.apiGroupName = apiGroupName;
	}

	/**
	 * @return the storesMap
	 */
	public String getMapName() {
		return mapName;
	}

	/**
	 * @param storesMap the storesMap to set
	 */
	public void setMapName(String mapName) {
		this.mapName = mapName;
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
	 * @return the allowedStores
	 */
	public String getAllowedStores() {
		return allowedStores;
	}

	/**
	 * @param allowedStores the allowedStores to set
	 */
	public void setAllowedStores(String allowedStores) {
		this.allowedStores = allowedStores;
	}

	/**
	 * @return the utilityCommand
	 */
	public String getUtilityCommand() {
		return utilityCommand;
	}

	/**
	 * @param utilityCommand the utilityCommand to set
	 */
	public void setUtilityCommand(String utilityCommand) {
		this.utilityCommand = utilityCommand;
	}
	
	/**
	 * @return the keyExpirationDate
	 */
	public String getKeyExpirationDate() {
		return keyExpirationDate;
	}

	/**
	 * @param keyExpirationDate the keyExpirationDate to set
	 */
	public void setKeyExpirationDate(String keyExpirationDate) {
		this.keyExpirationDate = keyExpirationDate;
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
