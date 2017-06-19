package com.gsipartners.eb2c.api.testharness.dataobjects;



/*	
 * Value Object to store the values for the GSI eb2c API Test Harness Context    
 * 
 * Design Decision: Do not need to implement Serializable...
 * 
*/

/**
 * Value Object to store the values for the GSI eb2c API Test Harness Context.
 * 
 * @author Michael Geiser
 *
 */
public final class Eb2cAPITestHarnessContextDO {

	
	
	/**
	 * Overrides the toString.
	 * 
	 * @return the string of data in the value object
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer returnValue = new StringBuffer();
/*		
		//really paranoid--
		returnValue.append("\nPostalCodeParserPropertiesVO")
			.append("\n FQDN of the API: ").append(apiFQDN) 
			.append("\n Store Id: ").append(storeID)
			.append("\n API Version: ").append(apiVersion)
			.append("\n APIKey: ").append(apiKey);
*/
		return returnValue.toString();
	}

	
	
	
	
	
}
