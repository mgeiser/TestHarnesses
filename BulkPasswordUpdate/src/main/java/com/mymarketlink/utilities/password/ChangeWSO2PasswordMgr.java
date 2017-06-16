package com.mymarketlink.utilities.password;

import java.util.HashMap; 
import java.util.Map;
import java.util.Properties;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.log4j.Logger;
import org.wso2.carbon.authenticator.stub.AuthenticationAdminStub;
import org.wso2.carbon.um.ws.api.WSRealmBuilder;
import org.wso2.carbon.user.core.UserRealm;
import org.wso2.carbon.user.core.UserStoreException;
import org.wso2.carbon.user.core.UserStoreManager;

import com.mymarketlink.utilities.password.BulkPasswordResetConstants;


public class ChangeWSO2PasswordMgr {

	//These are class specific constants
	private static final String TARGET_ENVIRONMENT_JKS_DEV = "jks_dev.jks";
	private static final String TARGET_ENVIRONMENT_JKS_QA = "jks_qa.jks";
	private static final String TARGET_ENVIRONMENT_JKS_SETUP = "jks_ps.jks";
	private static final String TARGET_ENVIRONMENT_JKS_PROD = "jks_prod.jks";

	/**
	 * setup the logger for the application error logs and run logs.
	 * validate what is the best constructor to use here 
	 */
	private static Logger logger = Logger.getLogger(ChangeWSO2PasswordMgr.class);

	
	public UserStoreManager getAPIConnection(Properties properties) {
		UserStoreManager storeManager = null;

		//constants
	    String adminUserLoginName = properties.getProperty(BulkPasswordResetConstants.PROPERTY_WSO2_ADMIN_NAME  );
	    String adminUserPassword = properties.getProperty(BulkPasswordResetConstants.PROPERTY_WSO2_ADMIN_PASSWORD);
	    String clientAPIServerURL = properties.getProperty(BulkPasswordResetConstants.PROPERTY_WSO2_API_ENDPOINT);
	    String serviceProviderId = properties.getProperty(BulkPasswordResetConstants.PROPERTY_WSO2_API_SERVICE_PROVIDER_ID);

	    // Determine the environment and determine the correct keystore and password to use 
	    String targetEnvironment = properties.getProperty(BulkPasswordResetConstants.PROPERTY_TARGET_ENVIRONMENT);
	    if (targetEnvironment.equals(BulkPasswordResetConstants.TARGET_ENVIRONMENT_DEV)) {
			System.setProperty("javax.net.ssl.trustStore", TARGET_ENVIRONMENT_JKS_DEV);
	    } else if  (targetEnvironment.equals(BulkPasswordResetConstants.TARGET_ENVIRONMENT_QA)) {
			System.setProperty("javax.net.ssl.trustStore", TARGET_ENVIRONMENT_JKS_QA);
	    } else if  (targetEnvironment.equals(BulkPasswordResetConstants.TARGET_ENVIRONMENT_SETUP)) {
			System.setProperty("javax.net.ssl.trustStore", TARGET_ENVIRONMENT_JKS_SETUP);
	    } else if  (targetEnvironment.equals(BulkPasswordResetConstants.TARGET_ENVIRONMENT_PROD)) {		
			System.setProperty("javax.net.ssl.trustStore", TARGET_ENVIRONMENT_JKS_PROD);
	    }
	    //set Password for keystore
	    //in this case, all four have the 
	    //TODO combine the keystores
	    System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
	    
	    
	    
	    
	    //setup objects to make the WSO2 call
	    //need to initialize to null now b/c of try/catches 
	    AuthenticationAdminStub authstub = null;
		ConfigurationContext configContext = null;
		String cookie = null;

		try {
			//null null, null work too?
			// configContext = ConfigurationContextFactory.createConfigurationContextFromFileSystem(null, null);
			configContext = ConfigurationContextFactory.createConfigurationContextFromFileSystem("repo", "repo/conf/client.axis2.xml");
			authstub = new AuthenticationAdminStub(configContext, clientAPIServerURL + "AuthenticationAdmin");


			authstub.login(adminUserLoginName, adminUserPassword, serviceProviderId);
			//may need cookie for future functionality
			cookie = (String) authstub._getServiceClient().getServiceContext().getProperty(HTTPConstants.COOKIE_STRING);

			//instantiate Realm concept
			UserRealm realm = WSRealmBuilder.createWSRealm(clientAPIServerURL, cookie, configContext);

			// The storeManager is controller for the Client API
			// The Client API is actually SOAP based but abstracts that complexity from the consumer
			// the trade-off for the encapsulation is the loss of some flexibility that we get from REST calls    
			storeManager = realm.getUserStoreManager();

		} catch (Exception e) {
			
			logger.error ("Something bad happened");
			logger.error (e.getStackTrace());
			e.printStackTrace();
		}	

		return storeManager;
	}
	
	
	
	
	
	/**
	 * Test that the user can authenticate
	 * 
	 * @param UserStoreManager storeManager
	 * @param String wso2Username
	 * @param String userPassword
	 * @return boolean successful authentication or not
	 */
	public boolean canUserAuthenticate(UserStoreManager storeManager, String wso2Username, String userPassword) {
		//setup the return  		
		boolean passwordChangeResult = false;
		
		try {
			passwordChangeResult = storeManager.authenticate(wso2Username, userPassword);
		} catch (UserStoreException e) {
			logger.info("\nFAIL-The user " + wso2Username + " was not able to authenticate with newly set password " + userPassword);
			logger.error ("Authentication test fail");
			logger.error (e.getStackTrace());
			e.printStackTrace();
		}
		
		return passwordChangeResult;
	}	
		
	
	
	
	
	/**
	 * Use the Admin API to change the target user's password 
	 * 
	 * @param properties  	The properties file info
	 * @param userName		The WSO2 username for the password Result 
	 * @param newPassword	The new password 
	 * @return  			true/false boolean of success of the Password Change 
	 */
	public boolean changePasswordbyAdmin(UserStoreManager storeManager, String wso2Username, String newUserPassword) {
		//setup the return  		
		boolean passwordChangeResult = false;
	
		
		//  try/catch blocks should be much more fine-grained in real code. I'm almost embarrassed by this.

		try {
				
				//check if the user exists in the User Store 
				if (storeManager.isExistingUser(wso2Username)) {

					try {
						storeManager.updateCredentialByAdmin(wso2Username, newUserPassword);
						//storeManager.updateCredentialByAdmin(wso2Username, "Password3!");
						logger.info("\nThe password for " + wso2Username + "was successfuly changed to " + newUserPassword);
						passwordChangeResult = true;
					} catch (Exception e) {
						logger.info("\nFAIL-The password for " + wso2Username + "was not changed");
						logger.error ("Password update fail");
						logger.error (e.getStackTrace());
						e.printStackTrace();
					}

				} else {
					logger.error("\nThe user password for " + wso2Username + "was not found in WSO2 so no Password update");
					//We'll want to check and update attributes as needed in this case
				}
				

		} catch (Exception e) {
			
			logger.error ("Something bad happened");
			logger.error (e.getStackTrace());
			e.printStackTrace();
		}	
		
		
		
		
		return passwordChangeResult; 
	}
	
	

	
	
	
	
	
}
