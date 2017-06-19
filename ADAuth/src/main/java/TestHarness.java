import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.security.auth.login.LoginException;


/**
 * @author mgeiser
 *
 */
public class TestHarness {

	public static void main(String[] args) {
		//validate input parameters
		//there should be one and it has to be a valid file

	
		String fileToReadName = "Passwordchange.properties";

		//use the java.util.Properties class to get the properties files 
		Properties properties = new Properties();
    	properties = getProperties(fileToReadName);
    	

    	
		TestADAuth authentication = new TestADAuth("domain.ad");
	    try {
	        boolean authResult = authentication.authenticate("mgeiser", "CSb&b123456");
	        System.out.print("Auth: " + authResult);
	    } catch (LoginException e) {
	        e.printStackTrace();
	    }

	}

	
	
	
	
	
	
    /**
     * @param propertiesFile
     * @return
     */
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
	
	
	
	
}
