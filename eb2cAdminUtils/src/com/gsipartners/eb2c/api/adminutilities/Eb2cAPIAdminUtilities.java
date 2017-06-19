package com.gsipartners.eb2c.api.adminutilities;

/*
 * Developer notes
 * <P>
 * RESTful Test Harness to use the GSI Commerce eb2c API to create an example order 
 * 
 * 
 * BIGGEST ISSUE WITH THIS CODE
 * This code was designed to accept **ANY** SSL Cert.  This would be a problem if we were using SSL for these calls. <br>
 * The properties can be configured to use HTTPS.  Since this is an internal app, there is little risk but setting 
 * up a valid cert key store would be a nice idea sooner or later   
 * 

 *  
 * OTHER WRANINGS
 * This code was not designed for scalability 
 * 
 * There is no assertion that this code is thread safe
 *  
 * 
 */

import com.gsipartners.eb2c.api.adminutilities.command.UtilityCommandConstants;
import com.gsipartners.eb2c.api.adminutilities.command.UtilityCommandConstants.UTILITY_COMMAND;
import com.gsipartners.eb2c.api.adminutilities.command.UtilityCommandFactory;
import com.gsipartners.eb2c.api.adminutilities.dataobjects.Eb2cAPIAdminUtilitiesPropertiesDO;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIAdminUtilitiesException;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIAdminUtilitiesGeneralException;
import com.gsipartners.eb2c.api.adminutilities.interfaces.UtilitiesCommand;
import com.gsipartners.eb2c.api.adminutilities.utilities.FileManager;
import com.gsipartners.eb2c.api.adminutilities.utilities.FilePropertyManager;

import org.apache.log4j.Logger;


/**
 * Running various commands.
 * <P>
 *<B> Design Notes:</B>
 *<BR>
 *If you wanted to use this in a web based application, you'd:<br>
 *<ul>
 *<li>populate the Eb2cAPIAdminUtilitiesPropertiesDO object directly from form input instead of reading from a file </li> 
 *<li>Determine the UTILITY_COMMAND to use</li>
 *<li>Get an implementation of UtilitiesCommand via UtilityCommandFactory.getUtilityCommandImpl() for the UTILITY_COMMAND desired</li>
 *<li>call utilityCommand.runCommand() passing the populated properties object</li>
 *</ul>
 *All implementations of UtilitiesCommand validate the passed in Eb2cAPIAdminUtilitiesPropertiesDO
 * 
 */
public class Eb2cAPIAdminUtilities {

	/**
	 * setup the logger for the application error logs and run logs.
	 */
	private static Logger logger = Logger.getLogger(Eb2cAPIAdminUtilities.class);
	
	/**
	 * Default constructor.
	 */
	public Eb2cAPIAdminUtilities() {
		
	}

	/**
	 * 
	 * 
	 * @param pPropertiesFilePath Where the property file is located.
	 */
	public static void runEb2cServiceCallChain(String pPropertiesFilePath) {
        logger.info("");
        logger.info("-----------------------------------------------------------------------------------------");
        logger.info("Starting Service call run");
        logger.info("-----------------------------------------------------------------------------------------");

        Eb2cAPIAdminUtilitiesPropertiesDO adminUtilitiesProperties = new Eb2cAPIAdminUtilitiesPropertiesDO();
        
        UtilityCommandConstants.UTILITY_COMMAND specifiedUtilityCommand = null;
        //validate the utility command in the properties file is valid and set it to properties context
        
        //low resolution timer - no need to use nanoTime(); the around ~10ms resolution of System.currentTimeMillis() is good enough  
        long startTime = System.currentTimeMillis();

        try {
                    
            // read the properties file and persist
            adminUtilitiesProperties = getParametersFromFile(pPropertiesFilePath);
            logger.info("Properties file successfully parsed: " + pPropertiesFilePath);

        	specifiedUtilityCommand = UTILITY_COMMAND.valueOf(adminUtilitiesProperties.getUtilityCommand());
            
            //get the command to run from the factory 
            UtilitiesCommand utilityCommand = UtilityCommandFactory.getUtilityCommandImpl(specifiedUtilityCommand);
            
            //call runCommand() on the Command Impl
            utilityCommand.runCommand(adminUtilitiesProperties);
      
            //An IllegalArgumentException here means the value in the properties file was not good 
        	//if  this is the case, dump the allowed UTILITY COMMANDS and bail	
        } catch (IllegalArgumentException IAE) {
        	
        	//dump data to the run log
            logger.info("");
            logger.info("-----------------------------------------------------------------------------------------");
            logger.info("The value for the utilityCommand specified in the eb2cAdminUtils.properties file (" + adminUtilitiesProperties.getUtilityCommand() + ") was not valid.");
            logger.info("The following are the allowed values for utilityCommand:");
            System.out.println("The value for the utilityCommand specified in the eb2cAdminUtils.properties file (" + adminUtilitiesProperties.getUtilityCommand() + ") was not valid.");
            System.out.println("The following are the allowed values for utilityCommand:");

        	for (UTILITY_COMMAND  validCommands : UTILITY_COMMAND.values()) {
        		logger.info(validCommands.toString() + ": " + validCommands.name().toString());
        		System.out.println(validCommands.toString() + ": " + validCommands.name().toString());
        	}
        	
        	logger.info("-----------------------------------------------------------------------------------------");
        	logger.info("Ending the run");
        	System.exit(1);
            
        } catch (Exception e) {
            
            logger.info("-----------------------------------------------------------------------------------------");           
            logger.info("Unknown/unclassified Exception caught in main().", e);
            logger.info("-----------------------------------------------------------------------------------------");           
            
            logger.error("-----------------------------------------------------------------------------------------");          
            logger.error("Unknown/unclassified Exception caught in main().", e);
            logger.error("-----------------------------------------------------------------------------------------");      

            
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            // even in the event of an Exception
            //httpsConnectionManager.shutdown();
        }
        logger.info("");       
        logger.info("-----------------------------------------------------------------------------------------");
        logger.info("Ending run");
        logger.info("Elapsed Time of run in ms: " + (System.currentTimeMillis() - startTime));
        logger.info("-----------------------------------------------------------------------------------------");
        logger.info("");
//drop this at some point
        System.out.println("Run completed");	    
	    
	}
	


	

	/**
	 * Get the parameters from the specified file.
	 * 
	 * @param pPropertiesFilePath file for for the properties
	 * @return Do with the properties
	 * @throws Eb2cAPIAdminUtilitiesException Generic exception
	 */
	private static Eb2cAPIAdminUtilitiesPropertiesDO getParametersFromFile(String pPropertiesFilePath) throws Eb2cAPIAdminUtilitiesException {
		
	
		// determine if the properties file specified exists 
		// terminate execution if the properties file is not found
		if (!FileManager.doesFileExist(pPropertiesFilePath)) {
			
			throw new Eb2cAPIAdminUtilitiesGeneralException("The specified property file " + pPropertiesFilePath + " does not exist.  Program exiting; please rerun and specify the correct filename");
			
		}
		
		Eb2cAPIAdminUtilitiesPropertiesDO propertiesData = new Eb2cAPIAdminUtilitiesPropertiesDO();
				
		propertiesData = FilePropertyManager.readProperties(pPropertiesFilePath);
		
			
		logger.info("-----------------------------------------------------------------------------------------");
		logger.info(propertiesData.toString());
		logger.info("-----------------------------------------------------------------------------------------");
		
		return propertiesData;
		
	}
	
    
}
