package com.mymarketlink.utilities.password;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.wso2.carbon.user.core.UserStoreManager;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


/**
 * Update the InitStatusChange.properties before running
 * 
 * 
 * @author mgeiser
 *
 */
public class SetUserInitStatusByClient {

	//Property keys 
	//MarketLink DB Releated
	private static final String DB_USER_NAME_KEY = "db_user_name";
	private static final String DB_USER_PASSWORD_KEY = "db_user_password";
	private static final String DB_HOSTNAME_KEY = "db_hostname";
	private static final String DB_PORT_KEY = "db_port";
	private static final String DB_DATABASE_NAME_KEY = "db_database_name";
	private static final String NPV_CLIENT_ID_KEY = "npv_client_id";

	// these will get refactored out and into an implementation along with the actual SQL Statement creation
	// ...eventually
	private static final String ML_EMPLOYEE_ID_KEY = "ml_employee_id";
	private static final String SUBSCRIBER_SSN_KEY = "subscriber_ssn";
	private static final String TPV_USERNAME_KEY = "username";
	private static final String ML_CLIENT_ID_KEY = "ml_client_id";
	
	
	/**
	 * setup the logger for the application error logs and run logs.
	 * validate what is the best constructor to use here 
	 */
	private static Logger logger = Logger.getLogger(SetUserInitStatusByClient.class);
	
	private static Logger progressLogger = Logger.getLogger("progress");
	
	
	public static Connection getDBConnection(Properties properties ) {


    	//get a MySQL DataSource instance  
    	MysqlDataSource dataSource = new MysqlDataSource();

		// set required info for the DataSource
		dataSource.setUser(properties.getProperty(DB_USER_NAME_KEY));
		dataSource.setPassword(properties.getProperty(DB_USER_PASSWORD_KEY));
		dataSource.setServerName(properties.getProperty(DB_HOSTNAME_KEY));
		dataSource.setPort(Integer.parseInt(properties.getProperty(DB_PORT_KEY)));
		dataSource.setDatabaseName(properties.getProperty(DB_DATABASE_NAME_KEY));
		//String npvClientId =  properties.getProperty(NPV_CLIENT_ID_KEY);
		
				
		//initialize variables because the scoping in try/catch blocks will give a "variable may not be initialized" otherwise
		Connection mysqlConnection = null;

		
		try {
			/*
			Personally, I'd prefer fine grained Exception handling and try/catch each separately 
			Even though they all Throw SQLException (for different reasons) if I could handle the exception.  
			In this case, any Exception here will likely need an update to the properties file and a re-run 
			so more course grained Exception Handling is fine
			*/    
			mysqlConnection = dataSource.getConnection();


		} catch (SQLException e) {
	    	//doesn't matter what the exception is, we die if this fails
			logger.error("Encountered a SQLException when trying to get the the RecordSet");
			logger.error("SQLException: " + e.getMessage() +"\n\n\n\n");
			System.out.println (e.getStackTrace());
			System.exit(-1);
		}

		return mysqlConnection;
		
	}

	
	public static ResultSet getUserInfo(Connection mysqlConnection, String npvClientId, String mlClientId, String userLoginName) {
		ResultSet sqlResultSet = null;
		
		Statement mysqlStatement = null;		
		String getSQLStatement = null;
		
		
		try {
			mysqlStatement = mysqlConnection.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}	
		
		
		//ww
		getSQLStatement = getSqlStatement_UserInformation(npvClientId, mlClientId, userLoginName);		
		
		
		//run it 
		try {
			sqlResultSet = mysqlStatement.executeQuery(getSQLStatement);


			try {
				
				while (sqlResultSet.next()) {
					System.out.print(sqlResultSet.getString(ML_EMPLOYEE_ID_KEY+" "));
					System.out.print(sqlResultSet.getString(SUBSCRIBER_SSN_KEY+" "));
					System.out.print(sqlResultSet.getString(TPV_USERNAME_KEY+" "));
					System.out.println();						
				}
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
				
		/*
		try {
			//clean up
			mysqlStatement.close();
		} catch (SQLException e) {
			//doesn't matter what the exception is, we die if this fails
			logger.error("Something went wrong trying to clean up and close the SQL connection");
			logger.error("Exception: " + e.getMessage() +"\n\n\n\n");
			logger.error (e.getStackTrace());
			System.exit(-1);
		}
		
		*/
		return sqlResultSet;
	}
	
	
	public void updateInitStatus(Connection mysqlConnection, String npvClientId, String userLoginName) {	
 
		Statement mysqlStatementUpdate = null;
		ResultSet sqlResultSet = null;

		String updateSQLStatement = null;

		try {

			mysqlStatementUpdate = mysqlConnection.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}		

		//initial to false
		boolean isChangePasswordSuccessful = false;
		
		//initializing
		String updateInitStatusSQLStatement = null;
		
		try {

			int counter = 0;

			//iterate over the ResultSet and update the password for each user in the
			while (sqlResultSet.next()) {
			    
				counter++;

				String mlEmployeeId = sqlResultSet.getString(ML_EMPLOYEE_ID_KEY);
			    String subscriberSSN = sqlResultSet.getString(SUBSCRIBER_SSN_KEY);
			    String tpvUsername = sqlResultSet.getString(TPV_USERNAME_KEY);
			    String mlClientId = sqlResultSet.getString(ML_CLIENT_ID_KEY);
 
			    

				//'CHANGE_PASSWORD','CREATE_CHALLENGE_QUESTIONS','CHOOSE_AVATAR','FORCE_CHANGE_PASSWORD','COMPLETE'
			    //get the INSERT/UPDATE SQL Statement to execute
			    updateInitStatusSQLStatement = getSqlStatement_updateUserInitStatus(mlEmployeeId, "FORCE_CHANGE_PASSWORD");
			    
//System.out.println("Row: " + counter + " --- ml_employee_id: " + mlEmployeeId);
//System.out.println(updateInitStatusSQLStatement + "\n");

				isChangePasswordSuccessful = false;
				
			    try {

			    	//run the UPDATE/INSERT
			    	int recordsUpdated = mysqlStatementUpdate.executeUpdate(updateInitStatusSQLStatement);
			    	logger.info(mlEmployeeId + ":"+ recordsUpdated);
			    	System.out.println("Update-Row: " + counter + " --- ml_employee_id: " + mlEmployeeId);
			    	
			    	//pause every 50
			    	if (counter%50 ==0) {
				    	System.out.println("Pause at" +counter );
			    	}
			    	
			    	
			    } catch (Exception e) {
					logger.error("Encountered an Exception when trying to change the Password");
					e.getStackTrace();
			    }

			}

			
		} catch (SQLException e1) {
		    System.out.println("Encountered a SQLException when trying to iterate of the RecordSet");
		    logger.error("Encountered a SQLException when trying to iterate of the RecordSet");
		    logger.error(e1.getMessage());
		    
		} finally {


			
			
		}
		
	}

	
	
//*************************************************************************************************
/* Utility methods */	
	
	
	private static String getSqlStatement_UserInformation(String npvClientId, String mlClientId, String userLoginName){
		
		//create the statement to get the record set
		String sqlStatement = "select username, ml_employee_id, birthdate, subscriber_ssn from tpv_employee where username = '"+ userLoginName + "' and tpv_client_id = " + npvClientId + ";";

		String sqlStatement2 = 
		"Select userinfo.*, mui.user_init_status, ifnull( (SELECT value1 FROM ml_client_flag where ml_client_id = '" + mlClientId + "' and ml_list_definition_code = 'passwordscheme' LIMIT 1),'BDATE_PASSWORD_SCHEME') as 'scheme'" +
	    "FROM (select username, ml_employee_id, birthdate, subscriber_ssn from tpv_employee where username = '" + userLoginName + "' and tpv_client_id = " + npvClientId + ") userinfo, ml_user_init mui WHERE userinfo.ml_employee_id = mui.ml_employee_id;";
		
		
		return sqlStatement2;
	}

	

	

	
	
	
	private static String getSqlStatement_updateUserInitStatus(String ml_employee_id, String userInitStatus){
		
		//create the statement to get the record set
		String sqlStatement =
				"INSERT INTO ml_user_init (ml_employee_id,user_init_status) VALUES ('" + ml_employee_id +"','" + userInitStatus +"')" +
				"ON DUPLICATE KEY UPDATE user_init_status='" + userInitStatus +"';";
		//really should update the password_change_date field 
		return sqlStatement;
	}
	
	
	
    /**
     * Validates the number of arguments is exactly 1 and is an existing file.    
     * @return true is args.length == 1 && args[1] is an existing file
     */
    private static final boolean isInvalidInput( String [] args ) {
    	
    	//defaults to not valid and has to be validated  
    	boolean isValid = false;
    	
    	// there should be exactly one parameter
    	//btw; args is never null
    	if  (args.length == 1) {
    		isValid = true;
    	}
    	
    	//check if file in arg[0] exists
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
    	
    	return isValid;
    }
	

    
    private static Properties getProperties(String propertiesFile) {
    	
    	
    	Properties fileProperties = new Properties();
    	InputStream input = null;

    	try {

    		input = new FileInputStream(propertiesFile);

    		// load a properties file
    		fileProperties.load(input);

    	} catch (IOException ex) {
    		ex.printStackTrace();
    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
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
    	//Run will exit if false  
    	boolean isValid = true;
    	
	
    	//TODO
    	/*
    	 * target_environment must be in dev, qa, setup, prod
    	 */
    	
    	
    	
    	String propertyValue = null;
        propertyValue = testProperties.getProperty(DB_USER_NAME_KEY);
        if (propertyValue == null) {
        	logger.fatal("DB_USER_NAME = " + DB_USER_NAME_KEY);
        	logger.fatal("The run ABENDed because db_user_name didn't exist in the properties file.  Nice going, Einstein...");
            isValid = false;
        }


        propertyValue = testProperties.getProperty(DB_USER_PASSWORD_KEY);
        if (propertyValue == null) {
        	logger.fatal("DB_USER_Password = " + DB_USER_PASSWORD_KEY);            
        	logger.fatal("The run ABENDed because db_user_password didn't exist in the properties file.  Nice going, Einstein...");
            isValid = false;
        }
        
        
        propertyValue = testProperties.getProperty(DB_HOSTNAME_KEY);
        if (propertyValue == null) {
        	logger.fatal("DB_HOSTNAME = " + DB_HOSTNAME_KEY);            
        	logger.fatal("The run ABENDed because db_hostname didn't exist in the properties file.  Nice going, Einstein...");
            isValid = false;
        }


        propertyValue = testProperties.getProperty(DB_PORT_KEY);
        if (propertyValue == null) {
        	logger.fatal("DB_PORT = " + DB_PORT_KEY);            
        	logger.fatal("The run ABENDed because db_port didn't exist in the properties file.  Nice going, Einstein...");
            isValid = false;
        }

        
        int testPort = 0;
        //in addition to existing, the port has to be an int 
        try {
        	testPort = Integer.parseInt(propertyValue);
        } catch (NumberFormatException e){
        	logger.fatal("The run ABENDed because db_port specified in the properties file could not be parsed to an int.  Nice going, Einstein...");
            isValid = false;
        }
        
        //and the port had to between 1 and 65535  
        if ( (testPort < 1) || (testPort > 65535)) {
            logger.fatal("The run ABENDed because db_port wasn't between 1 and 65535.  Nice going, Einstein...");
            isValid = false;
        }

        
        propertyValue = testProperties.getProperty(DB_DATABASE_NAME_KEY);
        if (propertyValue == null) {
        	logger.fatal("DB_DATABASE_NAME = " + DB_DATABASE_NAME_KEY);            
        	logger.fatal("The run ABENDed because db_database_name didn't exist in the properties file.  Nice going, Einstein...");
            isValid = false;
        }
        
        
        
        
        	return isValid;

    }
    	
    
 }    
