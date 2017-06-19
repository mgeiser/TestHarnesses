package com.gsipartners.eb2c.api.testharness.dataobjects.order;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.gsipartners.eb2c.api.testharness.Eb2cAPITestHarness;
import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;
import com.gsipartners.eb2c.api.testharness.utilities.XMLHelper;

public class AssociateDO {

    private String name = null;
    private String number = null;
    private String store = null;
    
    //setup the logger for the application error logs and run logs
    private static Logger logger = Logger.getLogger(Eb2cAPITestHarness.class);

    
    
    /**
     * Default constructor 
     */
    public AssociateDO () {
    }

    
    
    /**
     * Constructor that sets values
     * 
     * @param pName
     * @param pNumber
     * @param pStore
     */
    public AssociateDO (String pName, String pNumber, String pStore) {

            this.name = pName;
            this.number = pNumber;
            this.store = pStore;
    }
    
    
    /**
     * Overrides the toString() method.
     * 
     * @return the string of data in the value object
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer returnValue = new StringBuffer();
        
        //really paranoid--
        returnValue.append("\n\nAssociateDO")
            .append("\n Name: ").append(name) 
            .append("\n Number: ").append(number)
            .append("\n Store: ").append(store).append("\n");

        return returnValue.toString();
    }
    
    /**
     * Returns an XML Node for the Associatedata in the object
     * 
     * @return An XML Node with the address information
     */
    public Node getNode() throws Eb2cAPITestHarnessException {
  
        String nodeName = "Associate";
        
        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        Node returnNode = null;
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
        //Element root = doc.createElement("associates");
        //doc.appendChild(root);

        //set the name of the Node to the passed parameter  
        returnNode = doc.createElement(nodeName);
        //root.appendChild(returnNode);
        doc.appendChild(returnNode);
        
        // add the data from this object to the Node IF 
        //there is data in the property 
        doc = XMLHelper.addStringNode("Name",name,returnNode,doc);
        doc = XMLHelper.addStringNode("Number",number,returnNode,doc);
        doc = XMLHelper.addStringNode("Store",store,returnNode,doc);     
  
        return returnNode;
  }

  
    /**
     * 
     * Gets the data from the Object as a well formed valid XML string
     *  
     * @param pNodeName The name of the node to extract
     * @return A string of the XML
     * @throws Eb2cAPITestHarnessException
     */
    public String getNodeAsXML() throws Eb2cAPITestHarnessException {

        //Get the object data as a node
        Node associateNode = getNode();
        
        //Get a String of this Node 
        return XMLHelper.getNodeAsXML(associateNode);

    }



    /**
     * @return the name
     */
    public String getName() {
        return name;
    }



    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }



    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }



    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }



    /**
     * @return the store
     */
    public String getStore() {
        return store;
    }



    /**
     * @param store the store to set
     */
    public void setStore(String store) {
        this.store = store;
    }


    
    
    
}
