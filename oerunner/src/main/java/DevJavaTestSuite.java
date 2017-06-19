import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;






import com.opencsv.CSVReader;

import junit.framework.Test;
import junit.framework.TestSuite;



public class DevJavaTestSuite {


	/**
	 * setup the logger for the application error log.
	 */
	private static Logger logger = Logger.getLogger(DevJavaTestSuite.class);

	
	/**
	 * setup the logger for the application run log.
	 */
	private static Logger progressLogger = Logger.getLogger("RunLog");
	
	

	
  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(Login.class);
    return suite;
  }

  public static void main(String[] args) {
	
	//validate command line startup arguments
	if( !areArgumentsValid( args ) ) {
      logger.fatal("Usage: PasswordRest must have two arguments; the properties file and the datafile");
      //exit as gracefully as we can under the circumstances.  This will also flag any caller that this did not end well.
      System.exit( -1 );
	}    

	//I have to pass startup arguments into the test cases via a System Property since the JUni TestCase Interface isn't setup for that 
	System.setProperty("TestconfigFile",args[0]);
	
	//run the tests
    junit.textui.TestRunner.run(suite());	
      
  } 

  
  

  
  
  
	   /**
     * Validates the number of arguments is exactly 1 and is an existing file.    
     * @return true if args.length == 1 && is an existing file
     */
    private static final boolean areArgumentsValid( String [] args ) {
    	
    	//defaults to not valid and has to be validated  
    	boolean isValid = false;
    	
    	// there should be exactly one parameter and not nullable
    	if  (args.length == 1) {
    		isValid = true;
    	}
    	
    	//check if file in arg[0], the properties file, exists
    	try {
    		
    		File fileOfListToTest = new File(args[0]);
    		if (fileOfListToTest.exists() && !fileOfListToTest.isDirectory()) {
       			// The file exists and is not a directory, then the input validation has passed this test
    			isValid = true;
    			progressLogger.info("The Test Configuration file " + args[0] + " exists");
    		}    		
    	} catch (Exception e) {
    		logger.error("The Test Configuration file " + args[0] + " does not exist");
    		//if there are any exceptions, then input validation fails as well
    		isValid = false;
    	}
    	
    	return isValid;
    }
	
	
}
