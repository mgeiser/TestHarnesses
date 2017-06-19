package com.gsipartners.eb2c.api.testharness.utilities;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.gsipartners.eb2c.api.testharness.dataobjects.Eb2cAPITestHarnessPropertiesDO;
import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;
import com.gsipartners.eb2c.api.testharness.utilities.FileManager;

/**
 * Utility class to handle reading and writing the properties file for the Postal Code Parser application.
 * 
 * <b>DESIGN NOTE:</b>
 * I understand there are many ways to read the values of the property files into a HashMap 
 * and there are many other less tedious ways to create the DO or even skip the DO entirely 
 * and just the properties directly.  
 * 
 * I though it might add to the overall clarity of the exmaple to do it this way 
 *   
 * 
 */
public class FilePropertyManager {

	public final static String API_FQDN = "apiFQDN";
	public final static String STORE_ID = "storeID";
	public final static String API_VERSION = "apiVersion"; 
	public final static String API_KEY = "apiKey"; 

	 
	
	//setup the logger for the application error logs and run logs
	/**
	 * logger reference.
	 */
	private static Logger logger = Logger.getLogger(FileManager.class);	
	
    /**
     * Creates or updates the property files based on data in the VO. 
     * 
     * @param pProperties Eb2cTestHarnessPropertiesDO containing the values for the property file
     * @param pPropertiesFilePathAndName The full path and file name of the properties file to write
     * @throws Eb2cAPITestHarnessException The generic application Exception
     */
    public static void writeProperties(Eb2cAPITestHarnessPropertiesDO pProperties, String pPropertiesFilePathAndName) throws Eb2cAPITestHarnessException {
    	
    	Properties propertiesFileData = new Properties();
 
    	try {

    		//set the properties value
    		propertiesFileData.setProperty(API_FQDN, pProperties.getApiFQDN());
    		propertiesFileData.setProperty(STORE_ID, pProperties.getStoreID());
    		propertiesFileData.setProperty(API_VERSION, pProperties.getApiVersion());
    		propertiesFileData.setProperty(API_KEY, pProperties.getApiKey());
 
    		//save properties to project root folder
    		propertiesFileData.store(new FileOutputStream(pPropertiesFilePathAndName), null);
 
    	} catch (IOException ex) {
    		
    		logger.error("IOException when attempting to write out the new properties file.  File name: " + pPropertiesFilePathAndName);
			throw new Eb2cAPITestHarnessException("IOException when attempting to write out the new properties file.  File name: " + pPropertiesFilePathAndName);
    		
        }
    }

    /**
     * Reads the values for the keys from a defined format properties file and populates a value object.
     * 
     *  This was not implemented as HashMap or other collection since this is unlikely to grow in the future. 
     * 
     * @param pPropertiesFilePathAndName The full path and file name of the properties file to write
     * @return Eb2cTestHarnessPropertiesDO containing the values for the property file
     * @throws Eb2cAPITestHarnessException The generic application Exception
     */
    public static Eb2cAPITestHarnessPropertiesDO readProperties(String pPropertiesFilePathAndName) throws Eb2cAPITestHarnessException {
    	
    	Properties pProperties = new Properties();
    	Eb2cAPITestHarnessPropertiesDO proprertiesFromFile = new Eb2cAPITestHarnessPropertiesDO();
    	
    	try {
    		//load a properties file
    		pProperties.load(new FileInputStream(pPropertiesFilePathAndName));
 
            //get the property value and print it out
    		proprertiesFromFile.setApiFQDN(pProperties.getProperty(API_FQDN));
    		proprertiesFromFile.setStoreID(pProperties.getProperty(STORE_ID));
    		proprertiesFromFile.setApiVersion(pProperties.getProperty(API_VERSION));
    		proprertiesFromFile.setApiKey(pProperties.getProperty(API_KEY));
 
    	} catch (IOException ex) {
    		
    		logger.error("IOException when attempting to read and assign the data from the properties file.  File name: " + pPropertiesFilePathAndName);
			throw new Eb2cAPITestHarnessException("IOException when attempting to read and assign the data from the properties file.  File name: " + pPropertiesFilePathAndName);
        }
    	
    	return proprertiesFromFile; 
    	
    }

	
}
