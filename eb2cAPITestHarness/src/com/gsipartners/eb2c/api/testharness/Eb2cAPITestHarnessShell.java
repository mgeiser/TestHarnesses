package com.gsipartners.eb2c.api.testharness;


import org.apache.log4j.Logger;
import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;
import com.gsipartners.eb2c.api.testharness.Eb2cAPITestHarness;

/**
 * 
 * This is a command line shell to run the Test Harness
 *  
 * @author Michael Geiser
 *
 */
public class Eb2cAPITestHarnessShell {

    
    //Name of the properties file
    private static String propertiesFileName = null;
    
    //setup the logger for the application error logs and run logs
    private static Logger logger = Logger.getLogger(Eb2cAPITestHarnessShell.class);
    
    
    /**
     * @param args
     * @throws Eb2cAPITestHarnessException 
     */
    public static void main(String[] args) throws Eb2cAPITestHarnessException {
        //parse the startup args 

        parseCommandLineArgs(args);
        logger.info("Command Line Args successfully parsed: " + propertiesFileName);
    
        //call the APITestHarness
        Eb2cAPITestHarness.runEb2cServiceCallChain(propertiesFileName);
        
    }

    
    
    /**
     * Parse the command line args from  the run.
     * 
     *  Allows one and only one command line arg
     * 
     * @param args the command line arguments
     * @throws Eb2cAPITestHarnessException The generic application Exception is used here
     */
    private static void parseCommandLineArgs(String[] args) throws Eb2cAPITestHarnessException {        
        
        // get the location of the properties file form the args 
        //there are no other args 
        if (args.length == 1) {
            
            propertiesFileName = args[0];
        
        } else {
            // record problem in run log
            logger.info("");
            logger.info("-----------------------------------------------------------------------------------------");
            logger.info("Error in the command like args; expected: 1  found: " + args.length);
            logger.info("-----------------------------------------------------------------------------------------");
            
            // record problem in error log
            logger.error("Error in the command like args; expected: 1  found: " + args.length);
            throw new Eb2cAPITestHarnessException("Incorrect number of command line parameters.  Length or args: " + args.length);  
            
        }
    }
    
    
    
    
}
