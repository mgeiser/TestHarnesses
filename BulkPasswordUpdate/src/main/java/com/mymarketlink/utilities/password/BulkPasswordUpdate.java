package com.mymarketlink.utilities.password;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.wso2.carbon.user.core.UserStoreManager;

import com.mymarketlink.utilities.password.ChangeWSO2PasswordMgr;
import com.opencsv.CSVReader;




public class BulkPasswordUpdate {

	/**
	 * setup the logger for the application error logs and run logs.
	 * validate what is the best constructor to use here 
	 */
	private static Logger logger = Logger.getLogger(BulkPasswordUpdate.class);
	
	private static Logger progressLogger = Logger.getLogger("progress");
	
	
	
	public static void main(String[] args) {

		CSVReader csvFileReader = null;
	    String [] bulkPasswordDataLine;
		
	    String npvClientId = null;
	    String mlClientId = null;
	    String loginName = null;
	    String wso2Username = null;  
		String wso2NewPassword = null;
		boolean runAuthenticationTest;
		
	    long runStartTime = 0;
	    long runEndTime = 0;
		
		//validate command line startup arguments
		if( !areArgumentsValid( args ) ) {
            logger.fatal("Usage: PasswordRest must have two arguments; the properties file and the datafile");
            //exit as gracefully as we can under the circumstances
            System.exit( -1 );
        } 
		
		//
		String proprtiesFileName = args[0];
		String dataFileName = args[1];

		//use the java.util.Properties class to get the properties files 
		Properties properties = new Properties();
    	try {
			properties = getProperties(proprtiesFileName);
		} catch (IOException iox) {
    		logger.error("There was an issue parsing the Properies file " + proprtiesFileName +" -- Message: "+ iox.getMessage());
    		iox.printStackTrace();
            //exit as gracefully as we can under the circumstances
            System.exit( -1 );
   		
		}

    	//check the content of the properties file and exit if it doesn't pass some really basic validation checks
    	//bail if it fails, continue on otherwise
    	if (!doesPropertiesFileDataMeetMinimumRequirements(properties)) {
            logger.fatal("The property file " + proprtiesFileName + "did not pass validation checks.  Please correct and rerun");
    		System.exit( -1 );
    	}

    	
    	//get data
		try {
			csvFileReader = getDataFileReader(dataFileName);
		} catch (IOException iox) {
    		logger.error("There was an issue opening the Data file " + dataFileName +" -- Message: "+ iox.getMessage());
    		iox.printStackTrace();
            //exit as gracefully as we can under the circumstances
            System.exit( -1 );
		}


		runStartTime = System.nanoTime();
	
		//get the property to determine of an authentication check should be done after the password is reset  
		runAuthenticationTest = Boolean.parseBoolean(properties.getProperty(BulkPasswordResetConstants.PROPERTY_RUN_AUTHENTICATION_TEST));
		
		
		//get an instance of the UserStoreManager and reuse for all calls
		UserStoreManager storeManager = null;		
		ChangeWSO2PasswordMgr changeWSO2PasswordMgr = new ChangeWSO2PasswordMgr();		
		try {
			storeManager = changeWSO2PasswordMgr.getAPIConnection(properties);
			//nned to test that storemanager is not null
			
		} catch (Exception e) {
			//doesn't matter what the exception is, we die if this fails
			logger.error("Was unable to get the UserStoreManager Connection");
			logger.error("Exception: " + e.getMessage() +"\n\n\n\n");
			logger.error (e.getStackTrace());
			System.exit(-1);
		}

		
		//get DBConnection
		Connection mysqlConnection = null;
		mysqlConnection = SetUserInitStatusByClient.getDBConnection(properties);

				
		
		

		int counter = 0; 
		int resultSetCounter = 0;
		boolean isChangePasswordSuccessful = false;
		boolean isTestAuthenticationSuccessful = false;
		
		//get rid of header row
		try {
			bulkPasswordDataLine = csvFileReader.readNext();
		} catch (IOException iox) {
			logger.error("There was an issue reader or parsing the data file " + dataFileName +" -- Message: "+ iox.getMessage());
			iox.printStackTrace();
			//exit as gracefully as we can under the circumstances
			System.exit( -1 );
		}
		
		
		
		
		
		
		//iterate over the list of the users to reset passwords  
		try {
			while ((bulkPasswordDataLine = csvFileReader.readNext()) != null) {
				
				counter++;
			    
				npvClientId = bulkPasswordDataLine[0];
				mlClientId = bulkPasswordDataLine[1];
			    loginName = bulkPasswordDataLine[2];
			    ResultSet resultSet = null;
			    
			    // derive the username
				wso2Username = loginName + "_" + mlClientId;  
				wso2NewPassword = bulkPasswordDataLine[3];

				isChangePasswordSuccessful = false;
				isTestAuthenticationSuccessful = false;

				
				resultSet = SetUserInitStatusByClient.getUserInfo(mysqlConnection, npvClientId, mlClientId, loginName); 
				
				
				

				
				
				
				//calc new password based on data from DB
				
				
				//change the password
				try {
					//isChangePasswordSuccessful = changeWSO2PasswordMgr.changePasswordbyAdmin(storeManager, wso2Username, wso2NewPassword);	
					isChangePasswordSuccessful = true;
					System.out.println("Skipping the actual WSO2 API call");
					System.out.println("Password for user " + wso2Username + " set to "+ wso2NewPassword);

					if (!isChangePasswordSuccessful) {
						System.out.println("*************Password Change failed for user " + wso2Username + " being set to "+ wso2NewPassword);
					}

				} catch (Exception e) {
					logger.error("Encountered an Exception when trying to change the Password");
					logger.error (e.getStackTrace());
					e.printStackTrace();

				}				
				
				// if the API says the Password change was successful, test it
				// We don't **really** need this step; I just like it as an example...
 
				if ((isChangePasswordSuccessful) && runAuthenticationTest ) {
					try {
						isTestAuthenticationSuccessful = changeWSO2PasswordMgr.canUserAuthenticate(storeManager, wso2Username, wso2NewPassword);	

						if (isTestAuthenticationSuccessful) {
							System.out.println("Authentication test success for user " + wso2Username + " with password "+ wso2NewPassword);
						} else {
							System.out.println("*************Authentication test failed for user " + wso2Username + " with password "+ wso2NewPassword);
						}

					} catch (Exception e) {
						logger.error("Encountered an Exception when trying to authenticate user " + wso2Username);
						logger.error (e.getStackTrace());
						e.printStackTrace();
					}				

				}			


			}
		} catch (IOException iox) {
			logger.error("There was an issue reader or parsing the data file " + dataFileName +" -- Message: "+ iox.getMessage());
			iox.printStackTrace();
			//exit as gracefully as we can under the circumstances
			System.exit( -1 );

		}
		
	


	    
	//final summary item
	runEndTime = System.nanoTime();
	//determine the elapsed time in seconds 
	long elapsedRunTimeInSeconds = (runEndTime-runStartTime) / 1000000000;
	
	System.out.println("Processed " + counter + " users and reset their passwords in " + elapsedRunTimeInSeconds + " seconds.");	
		
		
		
	}

	
	
	
	
//--------------Utility methods ------------------	
	
	
    /**
     * Validates the number of arguments is exactly 2 and both are existing files.    
     * @return true if args.length == 2 && both are existing files
     */
    private static final boolean areArgumentsValid( String [] args ) {
    	
    	//defaults to not valid and has to be validated  
    	boolean isValid = false;
    	
    	// there should be exactly one parameter
    	//btw; args is never null
    	if  (args.length == 2) {
    		isValid = true;
    	}
    	
    	//check if file in arg[0], the properties file, exists
    	try {
    		
    		File fileOfListToTest = new File(args[0]);
    		if (fileOfListToTest.exists() && !fileOfListToTest.isDirectory()) {
       			// The file exists and is not a directory, then the input validation has passed this test
    			isValid = true;
    		}    		
    	} catch (Exception e) {
    		logger.error("Properties file " + args[0] + " does not exist");
    		//if there are any exceptions, then input validation fails as well
    		isValid = false;
    	}
    	
    	//check if file in arg[1], the datafile of passwords to change, exists
    	try {
    		
    		File fileOfListToTest = new File(args[1]);
    		if (fileOfListToTest.exists() && !fileOfListToTest.isDirectory()) {
       			// The file exists and is not a directory, then the input validation has passed this test
    			isValid = true;
    		}    		
    	} catch (Exception e) {
    		logger.error("The data file, " + args[0] + ", of usernames and passwords to reset does not exist");
    		//if there are any exceptions, then input validation fails as well
    		isValid = false;
    	}
    	
    	return isValid;
    }
	

    /**
     * Get the Properties file and return as an instance of java.util.Properties;
     * 
     * @param String dataFile
     * @return com.opencsv.CSVReader
     * @throws IOException 
     */
    private static CSVReader getDataFileReader(String dataFile) throws IOException {
    	
    	//instantiate openCSV reader 
    	return new CSVReader(new FileReader(dataFile));

      }
    	
    
    
    /**
     * Get the Properties file and return as an instance of java.util.Properties;
     * 
     * @param String propertiesFile
     * @return java.util.Properties
     * @throws IOException 
     */
    private static Properties getProperties(String propertiesFile) throws IOException {
    	
    	Properties fileProperties = new Properties();
    	InputStream input = null;

    	//get the file contents as a java.util.Properties instance
    	input = new FileInputStream(propertiesFile);

    	// load a properties file
    	fileProperties.load(input);

    	if (input != null) {
    		try {
    			input.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
 
    	return fileProperties;
      }
    	

    
	
    /**
     * Validates the content of the properties file.<br />
     *  Other runtime errors could exist that lead to potential runtime errors because the file may be read but does not have the valid values<br /> 
     *  This is not a huge issue since it can be rerun and is idempodent <br />
     *     
     * @return true if the properties file will not cause a runtime exception.  
     * 
     */
    private static final boolean doesPropertiesFileDataMeetMinimumRequirements( Properties testProperties ) {
    	//TODO for example, make tests more precise 
    	//Run will exit if false  
    	boolean isValid = true;
    	
    	String propertyValue = null;
    	
	    propertyValue = testProperties.getProperty(BulkPasswordResetConstants.PROPERTY_TARGET_ENVIRONMENT);    	
	    // check the environment
	    // one of the values must match
	    if ( (propertyValue.equals(BulkPasswordResetConstants.TARGET_ENVIRONMENT_DEV)     || 
	    		propertyValue.equals(BulkPasswordResetConstants.TARGET_ENVIRONMENT_QA)    ||
	    		propertyValue.equals(BulkPasswordResetConstants.TARGET_ENVIRONMENT_SETUP) ||
	    		propertyValue.equals(BulkPasswordResetConstants.TARGET_ENVIRONMENT_PROD)) == false ) {
        	logger.fatal(BulkPasswordResetConstants.PROPERTY_TARGET_ENVIRONMENT + " set as " + propertyValue);
	    	logger.fatal("target_environment was not in the allowed value range of 'dev', 'qa', 'setup' or 'prod'.  Nice going, Einstein..." );
	    	isValid = false;
	    }


	    propertyValue = testProperties.getProperty(BulkPasswordResetConstants.PROPERTY_RUN_AUTHENTICATION_TEST);
	    // Must be true or false
	    if ( (propertyValue.equals("true") || propertyValue.equals("false") ) == false ) {
        	logger.fatal(BulkPasswordResetConstants.PROPERTY_RUN_AUTHENTICATION_TEST + " set as " + propertyValue);
	    	logger.fatal("run_authentication_test was not in the allowed value range of 'true' || 'false' .  Nice going, Einstein..." );
	    	isValid = false;
	    }


	    propertyValue = testProperties.getProperty(BulkPasswordResetConstants.PROPERTY_WSO2_ADMIN_NAME);
        if (propertyValue == null) {
        	logger.fatal(BulkPasswordResetConstants.PROPERTY_WSO2_ADMIN_NAME + " set as " + propertyValue);
        	logger.fatal("The run ABENDed because " + BulkPasswordResetConstants.PROPERTY_WSO2_ADMIN_NAME +" didn't exist in the properties file.  Nice going, Einstein...");
            isValid = false;
        }

        propertyValue = testProperties.getProperty(BulkPasswordResetConstants.PROPERTY_WSO2_ADMIN_PASSWORD);
        if (propertyValue == null) {
        	logger.fatal(BulkPasswordResetConstants.PROPERTY_WSO2_ADMIN_PASSWORD + " set as " + propertyValue);
        	logger.fatal("The run ABENDed because " + BulkPasswordResetConstants.PROPERTY_WSO2_ADMIN_PASSWORD +" didn't exist in the properties file.  Nice going, Einstein...");
            isValid = false;
        }
        
        propertyValue = testProperties.getProperty(BulkPasswordResetConstants.PROPERTY_WSO2_API_ENDPOINT);
        if (propertyValue == null) {
        	logger.fatal(BulkPasswordResetConstants.PROPERTY_WSO2_API_ENDPOINT + " set as " + propertyValue);
        	logger.fatal("The run ABENDed because " + BulkPasswordResetConstants.PROPERTY_WSO2_API_ENDPOINT +" didn't exist in the properties file.  Nice going, Einstein...");
            isValid = false;
        }
        
        

        propertyValue = testProperties.getProperty(BulkPasswordResetConstants.PROPERTY_WSO2_API_SERVICE_PROVIDER_ID);
        if (propertyValue == null) {
        	logger.fatal(BulkPasswordResetConstants.PROPERTY_WSO2_API_SERVICE_PROVIDER_ID + " set as " + propertyValue);
        	logger.fatal("The run ABENDed because " + BulkPasswordResetConstants.PROPERTY_WSO2_API_SERVICE_PROVIDER_ID +" didn't exist in the properties file.  Nice going, Einstein...");
            isValid = false;
        }
        
        	return isValid;

    }
    	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
