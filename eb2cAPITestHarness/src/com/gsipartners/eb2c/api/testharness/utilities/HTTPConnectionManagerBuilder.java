/**
 * 
 */
package com.gsipartners.eb2c.api.testharness.utilities;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.log4j.Logger;

import com.gsipartners.eb2c.api.testharness.Eb2cAPITestHarness;
import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;

/**
 * @author geiserm
 *
 */
public class HTTPConnectionManagerBuilder {

	
	//setup the logger for the application error logs and run logs
	private static Logger logger = Logger.getLogger(Eb2cAPITestHarness.class);	
	
	public static ThreadSafeClientConnManager createThreadSafeConnectionManager() throws Eb2cAPITestHarnessException{
	
	   	try {
	   		// First create a trust manager that won't care about any issue; expired, untrusted root authority, etc.
			// this is an example, not production code
// TODO - modify this to support a keystore so this isn't crazy insecure
			
			
			// alittle innerclass magic to override the SSL Trust manager since I do not want to 
			// actually deal with using a real cert store...   
    		X509TrustManager trustManager = new X509TrustManager() {
    			public void checkClientTrusted(X509Certificate[] chain, String authType)
    			throws CertificateException {
    				// Don't do anything.
    			}
     
    			public void checkServerTrusted(X509Certificate[] chain, String authType)
    			throws CertificateException	{
    				// Don't do anything.
    			}
     
    			public X509Certificate[] getAcceptedIssuers() {
    				// Don't do anything.
    				return null;
    			}
    		};
     
            //Deal with all that SSL nastiness for the protocol and Trust Manager...
    		//I know...don't use a string literal here...it's OK in this case
    		SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[] { (TrustManager) trustManager }, null);

            SSLSocketFactory socketFactory = new SSLSocketFactory(sslcontext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            SchemeRegistry schemeRegistry = new SchemeRegistry();
            ThreadSafeClientConnManager ccm = new ThreadSafeClientConnManager(schemeRegistry);
          //I know...don't use a string literal here...it's OK in this case
            schemeRegistry.register(new Scheme("https", 443, socketFactory));
            
            //We're done here...
            return ccm;
            

    	
    	} catch(Exception e) {
    		
    		logger.info("-----------------------------------------------------------------------------------------");			
			logger.info("Unknown/unclassified Exception caught in HTTPConnectionManagerBuilder.createThreadSafeConnectionManager().", e);
			logger.info("-----------------------------------------------------------------------------------------");			
			
			logger.error("-----------------------------------------------------------------------------------------");			
			logger.error("Unknown/unclassified Exception caught in createThreadSafeConnectionManager().", e);
			logger.error("-----------------------------------------------------------------------------------------");		

			throw new Eb2cAPITestHarnessException("Unknown/unclassified Exception caught in createThreadSafeConnectionManager().", e);
			
    	}
		
		
		
	}
	
	
}
