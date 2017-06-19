package com.gsipartners.eb2c.api.testharness.dataobjects.order;

import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.gsipartners.eb2c.api.testharness.Eb2cAPIServiceCallConstants;
import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;
import com.gsipartners.eb2c.api.testharness.utilities.XMLHelper;

public class OrderPersonDO {

    //setup the logger for the application error logs and run logs
    private static Logger logger = Logger.getLogger(OrderPersonDO.class);

    private String honorific = null;
    private String firstName = null;
    private String middleName = null;
    private String lastName = null;
    private String gender = null;
    private String dateOfBirth = null;
    private String customerId = null;
    private String emailAddress = null;
    private String customerTaxId = null;
    private boolean taxExemptFlag = false;
    

    

    
    /**
     * Default constructor
     */
    public OrderPersonDO() {
        
    }

    
    /**
     * 
     * @param pHonorific (Optional)
     * @param pFirstName (Required)
     * @param pMiddleName (Optional)
     * @param pLastName (Required)
     * @param pCustomerId (Required)
     * @param pEmailAddress (Required)
     * @param pCustomerTaxId (Optional????) 
     * @param pTaxExemptFlag (Required) Cannot be null
     */
    public OrderPersonDO(String pHonorific, String pFirstName, String pMiddleName, String pLastName, 
            String pCustomerId, String pGender, String pDateOfBirth, 
            String pEmailAddress, 
            String pCustomerTaxId, boolean pTaxExemptFlag ) {
     
        //TODO needs input validation here
        
        this.honorific = pHonorific;
        this.firstName = pFirstName;
        this.middleName = pMiddleName;
        this.lastName = pLastName;
        this.gender = pGender;
        this.dateOfBirth = pDateOfBirth;
        this.customerId = pCustomerId;
        this.emailAddress = pEmailAddress;
        this.customerTaxId = pCustomerTaxId;
        this.taxExemptFlag = pTaxExemptFlag;
        
    }

    
    /**
     * Overrides the toString() method.
     * 
     * @return the string of data in the value object
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer returnValue = new StringBuffer();
        
        returnValue.append("\n\nOrderPersonDO")
            .append("\n Honorific: ").append(honorific)
            .append("\n First name: ").append(firstName) 
            .append("\n Middle name: ").append(middleName)
            .append("\n Last Name: ").append(lastName)
            .append("\n CustomerId: ").append(customerId) 
            .append("\n Gender: ").append(gender)
            .append("\n Date of Birth: ").append(dateOfBirth)
            .append("\n EmailAddress: ").append(emailAddress)
            .append("\n CustomerTaxId: ").append(customerTaxId)
            .append("\n TaxExemptFlag: ").append(taxExemptFlag)            
            .append("\n");

        return returnValue.toString();
    }
    
    /**
     * Quick set of test Values that will work with orders
     */
    public void setTestValues() {
        this.honorific = "Mr.";
        this.firstName = "Joe";
        this.middleName = "O";
        this.lastName = "Minimum";
        this.gender = "M";
        this.dateOfBirth = "1970-01-01";
        this.customerId = "12345";
        this.emailAddress = "jminimum@test.com";
        this.customerTaxId = "1234567890";
        this.taxExemptFlag = false;
    }

    
    /**
     * Returns an XML Node for the full Customer ID 
     *   
     * @return An XML Node
     */
    public Node getCustomerNode() throws Eb2cAPITestHarnessException {
  
        String nodeName = "Customer";
        
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
        rootNode.setAttribute("customerId", this.customerId);

        
        //set the name of the Node to the passed parameter  
        Node nameNode = null;
        nameNode = doc.createElement("Name");
        rootNode.appendChild(nameNode);
        
        // add the data from this object to the Node IF 
        //there is data in the property 
        doc = XMLHelper.addStringNode("Honorific",this.honorific,nameNode,doc);
        doc = XMLHelper.addStringNode("LastName",this.lastName,nameNode,doc);
        doc = XMLHelper.addStringNode("MiddleName",this.middleName,nameNode,doc);
        doc = XMLHelper.addStringNode("FirstName",this.firstName,nameNode,doc);     
        doc = XMLHelper.addStringNode("Gender",this.gender,rootNode,doc);
        doc = XMLHelper.addStringNode("DateOFBirth",this.dateOfBirth,rootNode,doc);
        doc = XMLHelper.addStringNode("EmailAddress",this.emailAddress,rootNode,doc);
        doc = XMLHelper.addStringNode("CustomerTaxId",this.customerTaxId,rootNode,doc);

        if (this.taxExemptFlag == true) {
            doc = XMLHelper.addStringNode("TaxExemptFlag",Eb2cAPIServiceCallConstants.VALUE_TRUE,rootNode,doc);
        } else {
            doc = XMLHelper.addStringNode("TaxExemptFlag",Eb2cAPIServiceCallConstants.VALUE_FALSE,rootNode,doc);
        }
        
        return rootNode;
  }    
    
    
    /**
     * Returns an XML Node for the full Customer ID 
     *   
     * @return An XML Node
     */
    public Node getNameNode(String pNodeName) throws Eb2cAPITestHarnessException {
  
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
        Element rootNode = doc.createElement(pNodeName);
        doc.appendChild(rootNode);
                
        // add the data from this object to the Node IF 
        //there is data in the property 
        doc = XMLHelper.addStringNode("Honorific",this.honorific,rootNode,doc);
        doc = XMLHelper.addStringNode("LastName",this.lastName,rootNode,doc);
        doc = XMLHelper.addStringNode("MiddleName",this.middleName,rootNode,doc);
        doc = XMLHelper.addStringNode("FirstName",this.firstName,rootNode,doc);     
       
        return rootNode;
  }     
    
    
    
    /**
     * 
     * Gets the data from the Object as a well formed valid XML string
     *  
     * @return A string of the XML
     * @throws Eb2cAPITestHarnessException
     */
    public String getCustomerNodeAsXML() throws Eb2cAPITestHarnessException {

        //Get the object data as a node
        Node customerNode = getCustomerNode();
        
        //Get a String of this Node 
        return XMLHelper.getNodeAsXML(customerNode);
    }

    
    
    /**
     * @return the honorific
     */
    public String getHonorific() {
        return honorific;
    }


    /**
     * @param honorific the honorific to set
     */
    public void setHonorific(String honorific) {
        this.honorific = honorific;
    }


     
    /**
     * 
     * Gets the just the Name Node from the data from the Object as a well formed valid XML string
     *  
     * @return A string of the XML
     * @throws Eb2cAPITestHarnessException
     */
    public String getNameNodeAsXML(String pNodeName) throws Eb2cAPITestHarnessException {

        //Get the object data as a node
        Node nameNode = getNameNode(pNodeName);
        
        //Get a String of this Node 
        return XMLHelper.getNodeAsXML(nameNode);

    }


    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }


    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }


    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }


    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }


    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }


    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    /**
     * @return the customerTaxId
     */
    public String getCustomerTaxId() {
        return customerTaxId;
    }


    /**
     * @param customerTaxId the customerTaxId to set
     */
    public void setCustomerTaxId(String customerTaxId) {
        this.customerTaxId = customerTaxId;
    }


    /**
     * @return the taxExemptFlag
     */
    public boolean isTaxExemptFlag() {
        return taxExemptFlag;
    }


    /**
     * @param taxExemptFlag the taxExemptFlag to set
     */
    public void setTaxExemptFlag(boolean taxExemptFlag) {
        this.taxExemptFlag = taxExemptFlag;
    }


    /**
     * @return the dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }


    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }


    /**
     * Sets the Gender.  If the user sets other then "M" or "F", 
     * the value is set to "M"
     * 
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        if ( gender.equals("M") || gender.equals("F") ) { 
            this.gender = gender;
        } else {
            this.gender = "M";
        }
    }

 
    
    
    
}
