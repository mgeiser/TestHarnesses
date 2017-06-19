package com.gsipartners.eb2c.api.testharness;

/*	
 * Developer notes
 * <P>
 * RESTful Test Harness to use the GSI Commerce eb2c API to create an example order 
 * 
 * 
 * BIGGEST ISSUE WITH THIS CODE
 * This code was designed to accept **ANY** SSL Cert.  
 * Using this code could result in falling prey to a man-in-the-middle attack or other issues
 * It is an example of how use the GSI Commerce eb2c code and NOT a guide for implementing the 
 * server side code to support using the API
 * 
 * REPEAT: This code is a developer's test harness and NOT server code
 *  
 * OTHER WRANINGS
 * This code was not designed for scalability 
 * 
 * There is no assertion that this code is thread safe
 *  
 * 
 */
	

import com.gsipartners.eb2c.api.testharness.Eb2cAPIServiceCallConstants.SERVICE_CALLS;
import com.gsipartners.eb2c.api.testharness.dataobjects.Eb2cAPITestHarnessPropertiesDO;
import com.gsipartners.eb2c.api.testharness.dataobjects.Eb2cAPITestHarnessContextDO;
import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;
import com.gsipartners.eb2c.api.testharness.utilities.Eb2cApiUriBuilder;
import com.gsipartners.eb2c.api.testharness.utilities.FileManager;
import com.gsipartners.eb2c.api.testharness.utilities.FilePropertyManager;
import com.gsipartners.eb2c.api.testharness.utilities.HTTPConnectionManagerBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

/**
 * Example ONLY program to show how to access and use the GSI Commerce V11 eb2c APIs.
 * <P>
 *<B> Design Notes:</B>
 *<BR>
 *TBD
 * 
 */
public class Eb2cAPITestHarness {
	
	//setup the logger for the application error logs and run logs
	private static Logger logger = Logger.getLogger(Eb2cAPITestHarness.class);
	
	//Will hold a Connection Manager that works with via HTTPS and is thread safe
	private static ThreadSafeClientConnManager httpsConnectionManager= null;

	
	
	public static void runEb2cServiceCallChain(String pPropertiesFilePath ) {

        logger.info("");
        logger.info("-----------------------------------------------------------------------------------------");
        logger.info("Starting Service call run");
        logger.info("-----------------------------------------------------------------------------------------");

        Eb2cAPITestHarnessPropertiesDO eb2cProperties = new Eb2cAPITestHarnessPropertiesDO();
        
        //DOM object
        //Document xmlDoc;

        //low resolution timer - no need to use nanoTime(); the around ~10ms resolution of System.currentTimeMillis() is good enough  
        long startTime = System.currentTimeMillis();

        try {
                    
            // read the properties file and persist
            eb2cProperties = getParametersFromFile(pPropertiesFilePath);
            logger.info("Properties File successfully parsed: " + pPropertiesFilePath);

            httpsConnectionManager = (ThreadSafeClientConnManager) HTTPConnectionManagerBuilder.createThreadSafeConnectionManager(); 
            
//test code...this goes in an IMPL class
String testServiceURI = null;

testServiceURI = Eb2cApiUriBuilder.buildServiceURI(Eb2cAPIServiceCallConstants.SERVICE_CALLS.SERVICE_ADDRESS_VALIDATE, 
        eb2cProperties, Eb2cAPIServiceCallConstants.SERVICE_CALL_FORMATS.XML);

System.out.println(testServiceURI);

            //instantiate a HTTPClient
            //TODO - should the PARAMs be handled differently??
            DefaultHttpClient httpClient = new DefaultHttpClient(httpsConnectionManager);
/*
            AddressValidationService addressValidationService = new AddressValidationService(
                    Eb2cAPIServiceCallConstants.SERVICE_CALLS.SERVICE_ADDRESS_VALIDATE,
                    Eb2cAPITestHarnessContextDO pServiceContext,
                    eb2cProperties,
                    httpClient
                    );
*/
            
            
            HttpResponse response = null;
            HttpEntity entity = null;
            
            //set the service URI to access
            HttpPost httpPost = new HttpPost(testServiceURI);

            //set a request header for the API Key 
            httpPost.setHeader(Eb2cAPIServiceCallConstants.REQUEST_HEADER_APIKEY , eb2cProperties.getApiKey());

            String xmlRequest = getXMLDoc();
            
            //Log the request
            logger.info("");
            logger.info("-----------------------------------------------------------------------------------------");
            logger.info("XML Request:");
            logger.info("\n\n" + xmlRequest + "\n");
            logger.info("-----------------------------------------------------------------------------------------");
            
            //create the xml for the request
            StringEntity postBody = new StringEntity(xmlRequest);   
            
            //add it to the client
            httpPost.setEntity(postBody);      
            
            //send the request
            response = httpClient.execute(httpPost);
            
            //set up to process the response
            entity = response.getEntity();
            
            //dump the response to the console and the log
            if (entity != null) {
                byte[] bytes = EntityUtils.toByteArray(entity);
                //dump the result and content to the run log
                logger.info("");
                logger.info("-----------------------------------------------------------------------------------------");
                logger.info("Form POST result: " + response.getStatusLine());
                logger.info("");            
                logger.info("XML Response:");
                logger.info(bytes.length + " bytes read");
                logger.info("\n\n" + new String(bytes) + "\n");
                logger.info("-----------------------------------------------------------------------------------------");
            }
            
            EntityUtils.consume(entity);
            
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
        logger.info("-----------------------------------------------------------------------------------------");
        logger.info("");
        System.out.println("Run completed");	    
	    
	}
	


	
	/**
	 * Creates a test valid Request for the Address Validation Service
	 * <P>
	 * This will generalized; this is fine for the test 
	 * 
	 */    
    private static String getXMLDoc() {
    	
    	StringBuffer XMLDoc = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?><AddressValidationRequest xmlns=\"http://api.gsicommerce.com/schema/checkout/1.0\">") 
    	.append("<Header><MaxAddressSuggestions>5</MaxAddressSuggestions></Header>") 
    	.append("<Address><Line1>680 Sunnyside Ave</Line1><City>Audubon</City><MainDivision>PA</MainDivision><CountryCode>US</CountryCode><PostalCode>19403</PostalCode></Address>") 
    	.append("</AddressValidationRequest>"); 
    	
    	return XMLDoc.toString();
    	
    }
    

	
	/**
	 * Gets and parses the values from the specified properties file.
	 * 
	 * @throws Eb2cAPITestHarnessException The generic application Exception is used here
	 */
	private static Eb2cAPITestHarnessPropertiesDO getParametersFromFile(String pPropertiesFilePath) throws Eb2cAPITestHarnessException {
		
		String delimiterValuefromPropertiesFile = "";
		
		// determine if the properties file specified exists 
		// terminate execution if the properties file is not found
		if (FileManager.doesFileExist(pPropertiesFilePath) == false) {
			
			throw new Eb2cAPITestHarnessException("The specified property file " + pPropertiesFilePath + " does not exist.  Program exiting; please rerun and specify the correct filename");
			
		}
		
		Eb2cAPITestHarnessPropertiesDO propertiesData = new Eb2cAPITestHarnessPropertiesDO();
				
		propertiesData = FilePropertyManager.readProperties(pPropertiesFilePath);
		
			
		logger.info("-----------------------------------------------------------------------------------------");
		logger.info(propertiesData);
		logger.info("-----------------------------------------------------------------------------------------");
		
		return propertiesData;
		
	}
	
    
}
