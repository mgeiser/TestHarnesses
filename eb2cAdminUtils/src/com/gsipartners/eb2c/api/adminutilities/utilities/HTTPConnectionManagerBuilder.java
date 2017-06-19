/**
 * 
 */
package com.gsipartners.eb2c.api.adminutilities.utilities;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.log4j.Logger;


import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIAdminUtilitiesException;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIAdminUtilitiesGeneralException;

/**
 * @author geiserm
 *
 */
public class HTTPConnectionManagerBuilder {

	
	//setup the logger for the application error logs and run logs
	private static Logger logger = Logger.getLogger(HTTPConnectionManagerBuilder.class);	
	

	
	public static ThreadSafeClientConnManager createThreadSafeConnectionManager() throws Eb2cAPIAdminUtilitiesException{
	
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
            schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));  
            
            
            //We're done here...
            return ccm;
            

    	
    	} catch(Exception e) {
    		
    		logger.info("-----------------------------------------------------------------------------------------");			
			logger.info("Unknown/unclassified Exception caught in HTTPConnectionManagerBuilder.createThreadSafeConnectionManager().", e);
			logger.info("-----------------------------------------------------------------------------------------");			
			
			logger.error("-----------------------------------------------------------------------------------------");			
			logger.error("Unknown/unclassified Exception caught in createThreadSafeConnectionManager().", e);
			logger.error("-----------------------------------------------------------------------------------------");		

			throw new Eb2cAPIAdminUtilitiesGeneralException("Unknown/unclassified Exception caught in createThreadSafeConnectionManager().", e);
			
    	}
		
		
		
	}
	
	
}
