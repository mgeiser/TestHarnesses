package com.gsipartners.eb2c.api.adminutilities.command;



/**
 * All constants needed for the Postal Code Parser app.
 * <P>
 * Almost all constants are used with the properties files 
 */
public class UtilityCommandConstants {
	
	public static final String LOG_FILE_SEPARATOR = "-----------------------------------------------------------------------------------------";
	
	public static final String COMMAND_CONSUMER_CREATE_URI = "/consumer/create";
	public static final String COMMAND_CONSUMER_DELETE_URI = "/consumer/delete";
	public static final String COMMAND_CONSUMER_APPROVE_URI = "/consumer/approve";
	public static final String COMMAND_CONSUMER_LIST_URI = "/consumer/list";
	public static final String COMMAND_CONSUMER_REVOKE_URI = "/consumer/revoke";
	public static final String COMMAND_CONSUMER_UPDATE_URI = "/consumer/update";
	public static final String COMMAND_CONSUMER_ATTRIBUTES_URI = "/consumer/attributes";
	public static final String COMMAND_CLIENTAPP_REGISTER_URI = "/consumer/clientapp/register";
	public static final String COMMAND_MAP_ENTRY_ADD_URI = "/map/entry/add";
	public static final String COMMAND_MAP_ENTRY_DELETE_URI = "/map/entry/delete";
	
	//not yet implemented
	public static final String COMMAND_MAP_ENTRY_ATTRIBUTES_URI = "/map/entry/attributes";
	public static final String COMMAND_MAP_ADD_URI = "/map/add";

	
	
	
	public static final String PARAMETER_CONSUMERID ="consumerid";
	public static final String PARAMETER_CLIENT_APP_NAME ="client_app_name";
	public static final String PARAMETER_API_GROUP_NAME ="api_group_name";
	public static final String PARAMETER_MAP_NAME ="map_name";
	public static final String PARAMETER_KEY ="key";
	public static final String PARAMETER_VALUE ="value";
	public static final String PARAMETER_VALUE1 ="value1";
	public static final String PARAMETER_VALUE2 ="value2";
	public static final String PARAMETER_CATTRIBUTE1 ="c_attribute1";
	public static final String PARAMETER_CATTRIBUTE2 ="c_attribute2";
	public static final String PARAMETER_CATTRIBUTE3 ="c_attribute3";
	
	
	
	// Use to test the response body from the approve command
	public static final String CONSUMER_STATUS_APPROVED = "<status>approved</status>";

	public static final String CONTENT_STRING_QUESTIONMARK = "?";
	public static final String CONTENT_STRING_EQUALS = "=";
	public static final String CONTENT_STRING_AMPERSAND = "&";
	 
	 
	//public static String PARAMETER_ ="";


	public static enum UTILITY_COMMAND {
		CREATE_CONSUMER_ID("Create Consumer Id"),
		DELETE_CONSUMER_ID("Delete Consumer Id"),
		LIST_CONSUMER_IDS("List Consumer Ids"),
		REVOKE_CONSUMER_BY_ID("Revoke the specified Consumer Id"),
		GET_CONSUMER_ATTRIBUTES_BY_ID("Get the attributes of the specified Consumer Id"),
		UPDATE_EXPIRATION_DATE_BY_CONSUMER_ID("Update the Expiration date (c_attribute1) of the specified Consumer Id"),
//		LIMIT_RATE_BY_APIKEY("Limit rate by API Key"),
//		BLOCK_ALL_ACTIVITY_BY_APIKEY("Block all activity by API Key"),
		MAP_ENTRY_DELETE("Delete an entry in a Key Manager Map");		
		

		private String stringValue;

		UTILITY_COMMAND(String s) { 
			stringValue = s; 
		}    

		public String toString() { 
			//allows you to do use UTILITY_COMMAND.CREATE_KEY.toString(); to get the text value instead of an index

			return stringValue;
		}   
	}
	
	
}

