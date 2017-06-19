package com.gsipartners.eb2c.api.testharness.dataobjects.order;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.gsipartners.eb2c.api.testharness.Eb2cAPIServiceCallConstants;
import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;
import com.gsipartners.eb2c.api.testharness.utilities.XMLHelper;

public class TaxHeaderDO {

    //setup the logger for the application error logs and run logs
    private static Logger logger = Logger.getLogger(TaxHeaderDO.class);

    private boolean hasErrorFlag = true;
    
    /**
     * Default constructor
     */
    public TaxHeaderDO() {
        
    }
    
    /**
     * Overrides the toString() method.
     * 
     * @return the string of data in the value object
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer returnValue = new StringBuffer();

        returnValue.append("\n\nTaxHeaderDO")
            .append("\n Error: ").append(hasErrorFlag) 
            .append("\n");

        return returnValue.toString();
    }
    
  
    /**
     * Returns an XML Node for the full Customer ID 
     *   
     * @return An XML Node
     */
    public Node getTaxHeaderNode() throws Eb2cAPITestHarnessException {
  
        String nodeName = "TaxHeader";
        
        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;

        try {
            docBuilder = dbfac.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.info("ParserConfigurationException Exception caught when trying to create a new DocBuilder.", e);
            logger.error("ParserConfigurationException Exception caught when trying to create a new DocBuilder.", e);
            throw new Eb2cAPITestHarnessException("ParserConfigurationException when attempting to create a DocBuilder. " + e);
        }
      
        Document doc = docBuilder.newDocument();
        //Creating the XML tree
        
        //create the root element and add it to the document
        Element rootNode = doc.createElement(nodeName);
        doc.appendChild(rootNode);

        
        if (this.hasErrorFlag == true) {
            doc = XMLHelper.addStringNode("Error",Eb2cAPIServiceCallConstants.VALUE_TRUE,rootNode,doc);
        } else {
            doc = XMLHelper.addStringNode("Error",Eb2cAPIServiceCallConstants.VALUE_FALSE,rootNode,doc);
        }
        
        return rootNode;
  }    
    
    /**
     * 
     * Gets the data from the Object as a well formed valid XML string
     *  
     * @return A string of the XML
     * @throws Eb2cAPITestHarnessException
     */
    public String getTaxHeaderNodeAsXML() throws Eb2cAPITestHarnessException {

        //Get the object data as a node
        Node taxHeaderNode = getTaxHeaderNode();
        
        //Get a String of this Node 
        return XMLHelper.getNodeAsXML(taxHeaderNode);

    }

    /**
     * @return the hasErrorFlag
     */
    public boolean isHasErrorFlag() {
        return hasErrorFlag;
    }

    /**
     * @param hasErrorFlag the hasErrorFlag to set
     */
    public void setHasErrorFlag(boolean hasErrorFlag) {
        this.hasErrorFlag = hasErrorFlag;
    }
    
    
    
    
    
    
    
    
    
    
    
    /*
     * 
    
    
    
            if (this.taxExemptFlag == true) {
            doc = XMLHelper.addStringNode("TaxExemptFlag",Eb2cAPIServiceCallConstants.VALUE_TRUE,rootNode,doc);
        } else {
            doc = XMLHelper.addStringNode("TaxExemptFlag",Eb2cAPIServiceCallConstants.VALUE_FALSE,rootNode,doc);
        }
        
     * 
     * 
     */
    
    
    
    
    
    
    /*
    
    Error String
    ???
    
    
    */
    
    
}
