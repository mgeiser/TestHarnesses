


import java.util.HashMap; 
import java.util.Map;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.transport.http.HTTPConstants;
import org.wso2.carbon.authenticator.stub.AuthenticationAdminStub;
import org.wso2.carbon.um.ws.api.WSRealmBuilder;
import org.wso2.carbon.user.core.UserRealm;
import org.wso2.carbon.user.core.UserStoreManager;


public class AdminChangeUserPassword {

	//admin user login
	private final static String ADMIN_USER_LOGIN_NAME = "mgeiser";
	//admin user password
	private final static String ADMIN_USER_PASSWORD = "Password1!";
	
	//Server URL for the Client API (non REST) end point
	//private final static String CLIENT_API_SERVER_URL = "https://ec2-54-166-105-123.compute-1.amazonaws.com:9443/services/";
	private final static String CLIENT_API_SERVER_URL = "https://idp.mml-dev.com/services/";
	//Server URL for the REST client User end point
	//private final static String REST_API_USER_SERVER_URL = "https://ec2-54-166-105-123.compute-1.amazonaws.com:9443/wso2/scim/Users";
	private final static String REST_API_USER_SERVER_URL = "https://idp.mml-dev.com/wso2/scim/Users";
	
	//Server URL for the REST client Group end point 
	//private final static String REST_API_GROUP_SERVER_URL = "https://ec2-54-166-105-123.compute-1.amazonaws.com:9443/wso2/scim/Groups";
	private final static String REST_API_GROUP_SERVER_URL = "https://idp.mml-dev.com/wso2/scim/Groups";
	
	// The Service Provider used for this function
	// The account will be made system wide and not limited to the context of this SP
	private final static String SERVICE_PROVIDER_ID = "marketlink.com";
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		AuthenticationAdminStub authstub = null;
		ConfigurationContext configContext = null;
		String cookie = null;
		
		//UserName will be unique within the client but enforced via login
		String newUserName = "newuser";
		//client id
		String clientId = "wawa";
		
		//Login Name must be unique across all clients
		//if the login name is mutable for a user, then we will want to use a uuid for the unique user name and 
		//authenticate against an attribute that is the newUserLoginName
		//it would be better to implement this way to future proof requirements
		//doing this now is minimal work and changing later will be a major effort with horrendous testing needed
		//also, this approach will handle M&A or simple re-branding or name changes 
		String newUserLoginName = newUserName.toLowerCase() + "_" + clientId.toLowerCase();
		
		
		//Must meet Password Policy or API will throw Exception and User Create will fail
		//There is no override for special Admin rights or calling via the API; Security is not negotiable. 
		String newUserPassword = "Password1!";
		
		
		//must be setup but failures to define these correctly do not affect user creation
		//@todo need to look at this closer to verify if there are situations where it would be an impact
		System.setProperty("javax.net.ssl.trustStore", "jssecacerts");
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

		
		//  try/catch blocks should be much more fine-grained in real code. I'm almost embarrassed by this.
		try {
						
			/*Again, must be setup but failures to define these correctly do not affect user creation
			 * 
			 * This is what wa logged to the console in this case
			 * 15/03/15 21:26:03 INFO deployment.DeploymentEngine: No services directory was found under D:\WSO2-test-apps\eclipse\org.wso2.identity.um.sample\repo.
			 * 15/03/15 21:26:03 INFO deployment.DeploymentEngine: No modules directory was found under D:\WSO2-test-apps\eclipse\org.wso2.identity.um.sample\repo. 
			 * 
			 * The issue here is that the call to ConfigurationContextFactory.createConfigurationContextFromFileSystem() is not creating the correct path
			 * 
			 */
			configContext = ConfigurationContextFactory.createConfigurationContextFromFileSystem("repo", "repo/conf/client.axis2.xml");
			authstub = new AuthenticationAdminStub(configContext, CLIENT_API_SERVER_URL + "AuthenticationAdmin");

			// Authenticates as a user (an admin) that has rights to add users.
			if (authstub.login(ADMIN_USER_LOGIN_NAME, ADMIN_USER_PASSWORD, SERVICE_PROVIDER_ID)) {
				cookie = (String) authstub._getServiceClient().getServiceContext().getProperty(HTTPConstants.COOKIE_STRING);

				//instantiate Realm concept
				UserRealm realm = WSRealmBuilder.createWSRealm(CLIENT_API_SERVER_URL, cookie, configContext);
				
				// The storeManager is controller for the Client API
				// The Client API is actually SOAP based but abstracts that complexity from the consumer
				// the trade-off for the encapsulation is the loss of some flexibility that we get from REST calls    
				UserStoreManager storeManager = realm.getUserStoreManager();
				
				//check if the user exists in the User Store 
				if (!storeManager.isExistingUser(newUserLoginName)) {
					// Create user with needed attributes since it doesn't exist
					
				/*  Populate the values for the user through the claims
					The String literals here would be replaced with variables from the PlanSource feed or would be derived from the PS feed
					This information will go into the shadow DB as well, but the users must be provisioned in WSO2 when the feeds are processed
					After the user is provisioned in WSO2, a uuid called the scimID must be fetched and stored in the Shaddow DB.  
					The scimID is the true unique identifier for the user the other vendor specific IDs for SSO must be tied to   
					
					The transaction boundaries will be ugly and transaction rollbacks will certainly include actual 
					rollbacks of transactions as well as compensating transactions 
				*/					
					Map<String, String> newUserClaims = new HashMap<String, String>();
					newUserClaims.put("http://wso2.org/claims/identity/loginname", newUserName.toLowerCase() );
					newUserClaims.put("http://wso2.org/claims/identity/username", newUserName.toLowerCase() );
					newUserClaims.put("http://wso2.org/claims/clientOrgName", "Wawa");
					newUserClaims.put("http://wso2.org/claims/givenname", "Michael");	
					newUserClaims.put("http://wso2.org/claims/lastname", "Geiser");

					//newUserClaims.put("http://wso2.org/identity/uid", newUserLoginName);  //User Id
					//newUserClaims.put("http://wso2.org/identity/userid", newUserLoginName);
				 	//http://wso2.org/claims/identity/userid
					//need a client login name claim				
					
					//create a string array that holds the **display names** (not uuids) of the roles to add to the new user
					//Client API uses Display Names, REST API uses UUIDs of Groups and Users
					String[] newUserRoles = new String[] { "marketlink_admin", "marketlink_user" };
					
					//Dumping some of the values for the new user to the console for the sample
					System.out.println("user: " + newUserLoginName);
					System.out.println("Password: " + newUserPassword);
					System.out.println("Claims: " + newUserClaims.toString());
					
					// You want to pass null for the profile (the fifth parameter) so the default profile is used when the user is created
					storeManager.addUser(newUserLoginName, newUserPassword, newUserRoles, newUserClaims, null);

					storeManager.updateCredentialByAdmin(newUserLoginName, "Password2!");
					
					System.out.println("\n\nThe user was successfully added to the system.");
				} else {
					System.out.println("\n\nThe user exists in the system.  The ETL process will likely update attributes in this case");
					//We'll want to check and updated attributes as needed in this case
					
				}

				
				
				// verify that the either the new user [newUser] was successfully added to the BenAdmin_Employee role or the user has this roll
				//This is making an API call 
				String[] userRoles = storeManager.getRoleListOfUser(newUserLoginName);
			
				//Dump the list of roles from WSO2 for this user
				System.out.println("\n\nThe user " + newUserLoginName + " has the following roles: ");
				if (userRoles != null) {
					for (int i = 0; i < userRoles.length; i++) {
							System.out.println(i + ". " + userRoles[i]);
					}
				}
				
				
				

/*
 * There are two user attributes management systems, one that the client API uses for creating and managing users 
 * and the other that has a more security oriented focus.
 * 
 * The Client API is very well suited to provisioning users and groups
 * The REST API gives is JSON responses to user attributes queries
 * 
 * The best most efficient approach for our use is a mix of the two APIs 
 * set all needed data and get the scimID we need in the Shadow DB 
 * 				

				
// Now get the user info via the REST API to get the uuid in the scimID (which is not currently available in the Client API.
// we want to use the scimID because it is a surrogate key.  the login name is a changeable natural key.
				

curl -v -k --user admin:admin https://192.168.88.138:9443/wso2/scim/Users?filter=userNameEqnewuser1_wawa




{"id":"8d9b7efc-dc5a-44b3-9853-878b7d790add","schemas":["urn:scim:schemas:core:1.0"],"profileUrl":"19ab9615-2c29-4644-9f5c-ed7d70947cfb","ims":["0"],"name":{"familyName":"Geiser","givenName":"Michael"},"userName":"mgeiser","emails":["mgeiser@mgeiser.net"],"phoneNumbers":[{"value":"1059140","type":"mobile"}],"addresses":[{"value":"680 Sunnyside ave","type":"streetAddress"},{"value":"US","type":"country"}],"meta":{"lastModified":"2015-02-27T22:32:57","created":"2015-02-06T22:10:20"},"groups":[{"value":"a1f128da-4968-4c58-9373-0a224e5e877c","display":"CobraPoint"},{"value":"f82b7f17-5b9f-431d-a97d-bc3f12655660","display":"FSA"},{"value":"07c52e67-3667-4899-a9af-1ddd33e23148","display":"BenAdmin"}]}
				
				
 */				
				
				
				
			}
		} catch (Exception e) {
			//gawd, it is painful to do this even in an example.
			e.printStackTrace();
		}
	}
	
	
	
}
