package com.gsipartners.eb2c.api.adminutilities.utilities;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIAdminUtilitiesException;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIAdminUtilitiesGeneralException;
import com.gsipartners.eb2c.api.adminutilities.dataobjects.Eb2cAPIAdminUtilitiesPropertiesDO;

/**
 * Utility class to handle reading and writing the properties file for the Postal Code Parser application.
 * 
 * <b>DESIGN NOTE:</b>
 * I understand there are many ways to read the values of the property file into a HashMap 
 * and there are many other less tedious ways to create the DO or even skip the DO entirely 
 * and just read the properties directly.  
 * 
 * This is not egregously bad, the DO rarely changes nor is it dynamic and I like the clarity   
 * 
 */
public class FilePropertyManager {

	public final static String UTILITY_COMMAND_VALUE = "utilityCommand";
	public final static String PROTOCOL = "protocol";
	public final static String TARGET_KEY_MANAGER_FQDN = "targetKeyManagerFQDN";
	public final static String TARGET_KEY_MANAGER_PORT = "targetKeyManagerPort";
	public final static String KEY_MANAGER_USER_ID = "keyManagerUserId";
	public final static String KEY_MANAGER_PASSWORD = "keyManagerPassword";
	public final static String CONSUMER_ID = "consumerId"; 
	public final static String CN = "cn";
	public final static String SN = "sn";
	public final static String EMAIL_ADDRESS = "emailAdrdess";
	public final static String GIVEN_NAME = "givenName";
	public final static String C_ATTRIBUTE_1 = "cAttribute1";
	public final static String C_ATTRIBUTE_2 = "cAttribute2";
	public final static String C_ATTRIBUTE_3 = "cAttribute3";
	public final static String CLIENT_APP_NAME = "clientAppName";
	public final static String API_GROUP_NAME = "apiGroupName";
	public final static String STORES_MAP = "storesMap";
	public final static String API_VERSION = "apiVersion";
	public final static String ALLOWED_STORES = "allowedStores";
	public final static String KEY_EXIRATION_DATE = "keyExpirationDate";
	public final static String API_KEY = "apiKey";
	
	
	//setup the logger for the application error logs and run logs
	/**
	 * logger reference.
	 */
	private static Logger logger = Logger.getLogger(FilePropertyManager.class);	
	
    /**
     * Creates or updates the property files based on data in the VO. 
     * 
     * @param pProperties Eb2cTestHarnessPropertiesDO containing the values for the property file
     * @param pPropertiesFilePathAndName The full path and file name of the properties file to write
     * @throws Eb2cAPIAdminUtilitiesException The generic application Exception
     */
    public static void writeProperties(Eb2cAPIAdminUtilitiesPropertiesDO pProperties, String pPropertiesFilePathAndName) throws Eb2cAPIAdminUtilitiesException {
    	
    	Properties propertiesFileData = new Properties();
 
    	try {

    		//set the properties value
    		propertiesFileData.setProperty(UTILITY_COMMAND_VALUE, pProperties.getUtilityCommand());
    		propertiesFileData.setProperty(PROTOCOL, pProperties.getProtocol());
    		propertiesFileData.setProperty(TARGET_KEY_MANAGER_FQDN, pProperties.getTargetKeyManagerFQDN());
    		propertiesFileData.setProperty(TARGET_KEY_MANAGER_PORT, pProperties.getTargetKeyManagerPort());
    		propertiesFileData.setProperty(KEY_MANAGER_USER_ID, pProperties.getKeyManagerUserId());
    		propertiesFileData.setProperty(KEY_MANAGER_PASSWORD, pProperties.getKeyManagerPassword());
    		propertiesFileData.setProperty(CONSUMER_ID, pProperties.getConsumerId());
    		propertiesFileData.setProperty(CN, pProperties.getCn());
    		propertiesFileData.setProperty(SN, pProperties.getSn());
    		propertiesFileData.setProperty(EMAIL_ADDRESS, pProperties.getEmailAddress());
    		propertiesFileData.setProperty(GIVEN_NAME, pProperties.getGivenName());
    		propertiesFileData.setProperty(C_ATTRIBUTE_1, pProperties.getcAttribute1());
    		propertiesFileData.setProperty(C_ATTRIBUTE_2, pProperties.getcAttribute2());
    		propertiesFileData.setProperty(C_ATTRIBUTE_3, pProperties.getcAttribute3());
    		propertiesFileData.setProperty(CLIENT_APP_NAME, pProperties.getClientAppName());
    		propertiesFileData.setProperty(API_GROUP_NAME, pProperties.getApiGroupName());
    		propertiesFileData.setProperty(STORES_MAP, pProperties.getMapName());    		
    		propertiesFileData.setProperty(API_VERSION, pProperties.getApiVersion());
    		propertiesFileData.setProperty(ALLOWED_STORES, pProperties.getAllowedStores());
    		propertiesFileData.setProperty(KEY_EXIRATION_DATE, pProperties.getKeyExpirationDate());
    		propertiesFileData.setProperty(API_KEY, pProperties.getApiKey());
    		
    		/*
    		devang - item master, OTF
    		rob - oms
    		patrick - order service 
    		 */ 		
    		
    		//save properties to project root folder
    		propertiesFileData.store(new FileOutputStream(pPropertiesFilePathAndName), null);
 
    	} catch (IOException ex) {
    		
    		logger.error("IOException when attempting to write out the new properties file.  File name: " + pPropertiesFilePathAndName);
			throw new Eb2cAPIAdminUtilitiesGeneralException("IOException when attempting to write out the new properties file.  File name: " + pPropertiesFilePathAndName);
    		
        }
    }

    /**
     * Reads the values for the keys from a defined format properties file and populates a value object.
     * 
     *  This was not implemented as HashMap or other collection since this is unlikely to grow in the future. 
     * 
     * @param pPropertiesFilePathAndName The full path and file name of the properties file to write
     * @return Eb2cTestHarnessPropertiesDO containing the values for the property file
     * @throws Eb2cAPIAdminUtilitiesException The generic application Exception
     */
    public static Eb2cAPIAdminUtilitiesPropertiesDO readProperties(String pPropertiesFilePathAndName) throws Eb2cAPIAdminUtilitiesException {
    	
    	Properties pProperties = new Properties();
    	Eb2cAPIAdminUtilitiesPropertiesDO proprertiesFromFile = new Eb2cAPIAdminUtilitiesPropertiesDO();
    	
    	try {
    		//load a properties file
    		pProperties.load(new FileInputStream(pPropertiesFilePathAndName));
 
            //get the property value and print it out
    		proprertiesFromFile.setUtilityCommand(pProperties.getProperty(UTILITY_COMMAND_VALUE).trim());
    		proprertiesFromFile.setProtocol(pProperties.getProperty(PROTOCOL).trim());
    		proprertiesFromFile.setTargetKeyManagerFQDN(pProperties.getProperty(TARGET_KEY_MANAGER_FQDN).trim());
    		proprertiesFromFile.setTargetKeyManagerPort(pProperties.getProperty(TARGET_KEY_MANAGER_PORT).trim());
    		proprertiesFromFile.setKeyManagerUserId(pProperties.getProperty(KEY_MANAGER_USER_ID).trim());
    		proprertiesFromFile.setKeyManagerPassword(pProperties.getProperty(KEY_MANAGER_PASSWORD).trim());
    		proprertiesFromFile.setConsumerId(pProperties.getProperty(CONSUMER_ID).trim());
    		proprertiesFromFile.setCn(pProperties.getProperty(CN).trim());
    		proprertiesFromFile.setSn(pProperties.getProperty(SN).trim());
    		proprertiesFromFile.setEmailAddress(pProperties.getProperty(EMAIL_ADDRESS).trim());
    		proprertiesFromFile.setGivenName(pProperties.getProperty(GIVEN_NAME).trim());
    		proprertiesFromFile.setcAttribute1(pProperties.getProperty(C_ATTRIBUTE_1).trim());
    		proprertiesFromFile.setcAttribute2(pProperties.getProperty(C_ATTRIBUTE_2).trim());
    		proprertiesFromFile.setcAttribute3(pProperties.getProperty(C_ATTRIBUTE_3).trim());
    		proprertiesFromFile.setClientAppName(pProperties.getProperty(CLIENT_APP_NAME).trim());
    		proprertiesFromFile.setApiGroupName(pProperties.getProperty(API_GROUP_NAME).trim());
    		proprertiesFromFile.setMapName(pProperties.getProperty(STORES_MAP).trim());
    		proprertiesFromFile.setApiVersion(pProperties.getProperty(API_VERSION).trim());
    		proprertiesFromFile.setAllowedStores(pProperties.getProperty(ALLOWED_STORES).trim());
    		proprertiesFromFile.setKeyExpirationDate(pProperties.getProperty(KEY_EXIRATION_DATE).trim());
    		proprertiesFromFile.setApiKey(pProperties.getProperty(API_KEY).trim());
    		
    	} catch (IOException ex) {
    		
    		logger.error("IOException when attempting to read and assign the data from the properties file.  File name: " + pPropertiesFilePathAndName);
			throw new Eb2cAPIAdminUtilitiesGeneralException("IOException when attempting to read and assign the data from the properties file.  File name: " + pPropertiesFilePathAndName);
        }
    	
    	return proprertiesFromFile; 
    	
    }

	
}
