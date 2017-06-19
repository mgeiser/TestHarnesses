package com.gsipartners.eb2c.api.adminutilities.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIAdminUtilitiesException;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIAdminUtilitiesGeneralException;


/**
 * Utility class that handles all opening, reading, writing and closing functions 
 * for the input and output data files used.
 * <P> 
 * All files need to be UTF-8 compliant; an early revision of this class switched to 
 * UTF-8 readers and writers
 * 
 */
public class FileManager {
	
    
    public static enum ALLOWED_FILE_ENCODING {
        ANSI,
        UTF_8        
    }
    
    public static final String FILE_FORMAT_UTF8 = "UTF-8";
    public static final String FILE_FORMAT_ANSI = "ANSI";
    
	//setup the logger for the application error logs and run logs
	/**
	 * logger reference.
	 */
	private static Logger logger = Logger.getLogger(FileManager.class);	
	
	
	/**
	 * Does a quick quick that the config file specified exists.
	 *  
	 * @param pFilePathAndName The Path and file name of the file to check if it exists 
	 * @return boolean - does the file exist or not?
	 * @throws Eb2cAPIAdminUtilitiesException The default application Exception
	 */
	public static boolean doesFileExist(String pFilePathAndName) throws Eb2cAPIAdminUtilitiesException {
	    
		File findFile = null;
		
		try {
		
			findFile = new File(pFilePathAndName);
		
			logger.info("Check if the file " + pFilePathAndName + " exists: " + findFile.isFile());
	    
		} catch (RuntimeException rt) {

			logger.error("Error trying to determine if file " + pFilePathAndName + " exists", rt);
			throw new Eb2cAPIAdminUtilitiesGeneralException("Error trying to determien if file " + pFilePathAndName + " exists", rt);
			
		}
	    
	    return findFile.isFile();
	    
	  } 

	
	
	
	/**
	 * Opens an OutputStreamWriter for creating the Enfinity import feed file .
	 * 
	 * @param pOutputFileName Full path and file name
	 * @return An OutputStreamWriter object
	 * @throws Eb2cAPIAdminUtilitiesException The default application Exception
	 */
	public static OutputStreamWriter openOutputFile(String pOutputFileName) throws Eb2cAPIAdminUtilitiesException {
		/*
		 *  pOutputFileName cannot be null or not a valid file 
		 *  	
		 */
	
        File outFile = new File(pOutputFileName);
        OutputStreamWriter outputStreamWriter = null;

        try {

        	//create the file, overwrite any existing file
        	outputStreamWriter = new OutputStreamWriter(new FileOutputStream(outFile), FILE_FORMAT_UTF8);

        } catch (FileNotFoundException e) {

        	logger.error("FileNotFoundException Exception opening output file: " + pOutputFileName, e);
			throw new Eb2cAPIAdminUtilitiesGeneralException("FileNotFoundException Exception opening output file: " + pOutputFileName, e);

		} catch (UnsupportedEncodingException e) {
        	
			logger.error("UnsupportedEncodingException Exception opening output file: " + pOutputFileName, e);
			throw new Eb2cAPIAdminUtilitiesGeneralException("UnsupportedEncodingException Exception opening output file: " + pOutputFileName, e);
			
		}

		return outputStreamWriter;

	}

	
	
	
	/**
	 * Writes a line to the passed in OutputStreamWriter reference.
	 * 
	 * @param pOutputFileName  Name of file; needed for logging and Exception Handling since it is not available from the OutputStreamWriter object
	 * @param pOutputStreamWriter The OutputStreamWriter Object
	 * @param pOutputData The data to write to the file
	 * @throws Eb2cAPIAdminUtilitiesException The default application Exception
	 */
	public static void writeToOutputFile(String pOutputFileName, OutputStreamWriter pOutputStreamWriter, String pOutputData) throws Eb2cAPIAdminUtilitiesException {
		/*
		 *  pOutputFileName cannot be null or not a valid file 
		 *  pDataOutputStream cannot be null
		 *  pOutputData should not be null or not a valid file
		 *  	
		 */		
		
      try {
    	  
    	  //write the data to the file
    	  pOutputStreamWriter.write(pOutputData + "\n");
    	  
      } catch (IOException e) {

    	  logger.error("Error with writing line: " + pOutputData + " in file: " + pOutputFileName, e);
    	  throw new Eb2cAPIAdminUtilitiesGeneralException("Error with writing line: " + pOutputData + " in file: " + pOutputFileName, e);

      } 
		
	}

	
	/**
	 * Closes the OutputStreamWriter.
	 * 
	 * @param pOutputFileName  Name of file; needed for logging and Exception Handling since it is not available from the OutputStreamWriter object
	 * @param pOutputStreamWriter The OutputStreamWriter Object
	 * @throws Eb2cAPIAdminUtilitiesException The default application Exception
	 */
	public static void closeOutputFile(String pOutputFileName, OutputStreamWriter pOutputStreamWriter) throws Eb2cAPIAdminUtilitiesException {
		// no input validation needed
		
		try {
		
			//flush the file object
			pOutputStreamWriter.flush();
			
			//Close the file object
			pOutputStreamWriter.close();
			
		} catch (IOException e) {

			logger.error("Error closing the file " + pOutputFileName, e);
			throw new Eb2cAPIAdminUtilitiesGeneralException("Error closing the file " + pOutputFileName, e);

		}
	
	}



	/**
	 * Private method to create to create a BuffredReader for the input file to process. 
	 * 
	 * @param pInputFileName The full Path and file name of the file to read
	 * @return BufferedReader The BufferedReader Object
	 * @throws Eb2cAPIAdminUtilitiesException The default application Exception
	 */
	public static BufferedReader createBufferedReader(String pInputFileName) throws Eb2cAPIAdminUtilitiesException {
		/*
		 *  	something like this to ensure the file exists
		 *		File file = new File(fileName);
		 *		return file.exists();	
		 */
 		
		BufferedReader localBufferedReader = null;

		try {
			//Create a UTF-8 Buffered Reader for the specified file - older non UTF-8 cmpliant code
			localBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(pInputFileName), FILE_FORMAT_UTF8));

		} catch (FileNotFoundException e) {
			
			logger.error("FileNotFoundException Exception while openning the BufferedReader for file " + pInputFileName, e);
			throw new Eb2cAPIAdminUtilitiesGeneralException("FileNotFoundException Exception while openning the BufferedReader for file " + pInputFileName, e);
			
		} catch (UnsupportedEncodingException e) {

			logger.error("UnsupportedEncodingException Exception while openning the BufferedReader for file " + pInputFileName, e);
			throw new Eb2cAPIAdminUtilitiesGeneralException("UnsupportedEncodingException Exception while openning the BufferedReader for file " + pInputFileName, e);
			
		}

		return localBufferedReader;
	}

	
	
	/**
	 * Reads the content of file as UTF-8 into a string.</br>
	 * Strictly speaking, this doesn't have to be an XML file, 
	 * since there are no XML checks, it just loads an UTF-8 
	 * file info a string.  
	 * <P>
	 * I'll get around to a more flexible file reader to string 
	 * fxn if needed later  
	 * <P>
	 * @param pXMLFileName
	 * @return
	 * @throws Eb2cAPIAdminUtilitiesException
	 */
	public static String readXMLFiletoString(String pXMLFileName, FileManager.ALLOWED_FILE_ENCODING pFileEncoding) throws Eb2cAPIAdminUtilitiesException {
	    
	    File xmlFile = null;
	    String xmlFromFile = null;


	    try {
	        xmlFile = new File(pXMLFileName);

	        if (pFileEncoding == FileManager.ALLOWED_FILE_ENCODING.UTF_8) {
	            xmlFromFile = FileUtils.readFileToString(xmlFile , FILE_FORMAT_UTF8);
	        } else if (pFileEncoding == FileManager.ALLOWED_FILE_ENCODING.UTF_8) {
	            xmlFromFile = FileUtils.readFileToString(xmlFile , FILE_FORMAT_ANSI);
	        } else {
	            logger.info("Unhandled FileManager.ALLOWED_FILE_ENCODING in FileManager.readXMLFiletoString when trying to load file " + pXMLFileName);
	            logger.error("Unhandled FileManager.ALLOWED_FILE_ENCODING when trying to load file " + pXMLFileName);
	            throw new Eb2cAPIAdminUtilitiesGeneralException ("Unhandled FileManager.ALLOWED_FILE_ENCODING when trying to load file " + pXMLFileName);
	        }

	    } catch (NullPointerException npe) {
	        logger.info("NullPointerException Exception caught in FileManager.readXMLFiletoString when trying to load file " + pXMLFileName, npe);
	        logger.error("NullPointerException Exception caught in FileManager.readXMLFiletoString when trying to load file " + pXMLFileName, npe);
	        throw new Eb2cAPIAdminUtilitiesGeneralException ("File not found - " + pXMLFileName, npe);
	    } catch (IOException e) {
	        logger.info("IOException Exception caught in FileManager.readXMLFiletoString. Error reading the File " + pXMLFileName + " to a String", e);
	        logger.error("IOException Exception caught in FileManager.readXMLFiletoString. Error reading the File " + pXMLFileName + " to a String", e);
	        throw new Eb2cAPIAdminUtilitiesGeneralException ("Error reading the File " + pXMLFileName + " to a String", e);   
	    }

	    return xmlFromFile;
	}
	

	/**
	 * Private method to get the next line from the file.
	 * 
	 * @param pBufferedReader The active BufferedReader Object
	 * @param pInputFileName The full path and name of the input file
	 * @return The next line read from the file
	 * @throws Eb2cAPIAdminUtilitiesException The default application Exception
	 */
	public static String readLineFromFile(BufferedReader pBufferedReader, String pInputFileName) throws Eb2cAPIAdminUtilitiesException {
		/*
		 *  pBufferedReader cannot be null
		 *  pInputFileName should not be zero length or null
		 *  	
		 */
		
		String nextLineFromFile = null;

		try {
		
			nextLineFromFile = pBufferedReader.readLine();
			 
		} catch (IOException e) {
			logger.error("IOException Exception while reading next line via a BufferedReader for file " + pInputFileName, e);
			throw new Eb2cAPIAdminUtilitiesGeneralException("IOException Exception while reading next line via a BufferedReader for file " + pInputFileName, e);

		}
		return nextLineFromFile;

	}
	
	

	/**
	 * Close the BufferedReader.
	 * 
	 * @param pInputFileName The full path and name of the input file
	 * @param pBufferedReader  The active BufferedReader Object
	 * @throws Eb2cAPIAdminUtilitiesException  The default application Exception
	 */
	public static void closeBufferedReader(String pInputFileName, BufferedReader pBufferedReader) throws Eb2cAPIAdminUtilitiesException {
		// no input validation needed here 
		
		try {

			pBufferedReader.close();

		} catch (IOException e) {
			
			logger.error("Error closing the file " + pInputFileName, e);
			throw new Eb2cAPIAdminUtilitiesGeneralException("Error closing the file " + pInputFileName, e); 

		}
		
	}
	
	
	
}
