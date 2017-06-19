package com.gsipartners.eb2c.api.testharness.dataobjects;

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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;
import com.gsipartners.eb2c.api.testharness.utilities.FileManager;
import com.gsipartners.eb2c.api.testharness.utilities.XMLHelper;
import com.gsicommerce.xpath.XPathHelper;



/**
 * 
 * Data Object to store an address for orders
 * corresponds to PhysicalAddressType from Checkout-Datatypes-1.0.xsd
 * <P>
 * Not implementing an isValid() method; that business logic is defined 
 * in the XSD and the logic should not be duplicated here (maintainability, "CAP" code smell etc...
 * maybe I'll validate against the XSDs at some point, just not today
 * 
 * @author Michael Geiser
 *
 */
public class ServiceAddressDO {

   private String Line1 = null; 
   private String Line2 = null;         // minoccurs 0
   private String Line3 = null;         // minoccurs 0
   private String Line4 = null;         // minoccurs 0
   private String City = null;          // 1-35
   private String MainDivision = null;  // 1-35 minoccurs =0
   private String CountryCode = null;   // 2-40
   private String PostalCode = null;    // 1-15 minoccurs =0

   //setup the logger for the application error logs and run logs
   private static Logger logger = Logger.getLogger(ServiceAddressDO.class);

   
   /**
    *  Default constructors, sets no values
    */
   public ServiceAddressDO () {
       
   }
   

   /**
    * Constructor that takes an XML Node and populates the DO 
    * 
    * Assumes the XML looks like this:
    * <shippingaddress><Line1>680 Sunnyside Ave</Line1><City>Audubon</City><MainDivision>PA</MainDivision><CountryCode>US</CountryCode><PostalCode>19403</PostalCode></shippingaddress>
    * 
    * @param pAddress Node containing address information
    * @throws Eb2cAPITestHarnessException 
    */
   public ServiceAddressDO (Node pAddressNode, String pParentNodeName) throws Eb2cAPITestHarnessException {
       
       XPath xpath = XPathFactory.newInstance().newXPath();
       try {
           this.Line1 = xpath.evaluate("//" + pParentNodeName + "/Line1", pAddressNode);
           this.Line2 = xpath.evaluate("//" + pParentNodeName + "/Line2", pAddressNode);
           this.Line3 = xpath.evaluate("//" + pParentNodeName + "/Line3", pAddressNode);
           this.Line4 = xpath.evaluate("//" + pParentNodeName + "/Line4", pAddressNode);
           this.City = xpath.evaluate("//" + pParentNodeName + "/City", pAddressNode);
           this.MainDivision = xpath.evaluate("//" + pParentNodeName + "/MainDivision", pAddressNode);
           this.CountryCode = xpath.evaluate("//" + pParentNodeName + "/CountryCode", pAddressNode);
           this.PostalCode = xpath.evaluate("//" + pParentNodeName + "/PostalCode", pAddressNode);
           
           
       } catch (XPathExpressionException e) {
           logger.info("XPathExpressionException Exception caught in Eb2cAddressDO contructor", e);

           logger.error("XPathExpressionException Exception caught in Eb2cAddressDO contructor", e);
           throw new Eb2cAPITestHarnessException("XPathExpressionException caught in Eb2cAddressDO contructor. " + e);
       }

   }
   
   /**
    * Constructor that takes an XML string of an address 
    * and populates the DO 
    * 
    * Assumes the XML looks like this:
    * <shippingaddress><Line1>680 Sunnyside Ave</Line1><City>Audubon</City><MainDivision>PA</MainDivision><CountryCode>US</CountryCode><PostalCode>19403</PostalCode></shippingaddress>
    * 
    * @param pAddress
   */
   public ServiceAddressDO (String pAddressNodeXML, String addressNodeName) {

       populateDOfromXMLData(pAddressNodeXML, addressNodeName);
     
   }

   /**
    * @param pAddressNodeXML
    * @param addressNodeName
    */
   private void populateDOfromXMLData(String pAddressNodeXML,
           String addressNodeName) {
       //load the XML
       XPathHelper xpathHelper = new XPathHelper(pAddressNodeXML);

       if (xpathHelper.doesNodeExist("//" + addressNodeName + "/Line1")) {
           this.Line1 = xpathHelper.read("//" + addressNodeName + "/Line1");
       }
       if (xpathHelper.doesNodeExist("//" + addressNodeName + "/Line2")) {
           this.Line2 = xpathHelper.read("//" + addressNodeName + "/Line2");
       }
       if (xpathHelper.doesNodeExist("//" + addressNodeName + "/Line3")) {
           this.Line3 = xpathHelper.read("//" + addressNodeName + "/Line3");
       }
       if (xpathHelper.doesNodeExist("//" + addressNodeName + "/Line4")) {
           this.Line4 = xpathHelper.read("//" + addressNodeName + "/Line4");
       }
       if (xpathHelper.doesNodeExist("//" + addressNodeName + "/City")) {
           this.City = xpathHelper.read("//" + addressNodeName + "/City");
       }
       if (xpathHelper.doesNodeExist("//" + addressNodeName + "/MainDivision")) {
           this.MainDivision = xpathHelper.read("//" + addressNodeName + "/MainDivision");
       }
       if (xpathHelper.doesNodeExist("//" + addressNodeName + "/CountryCode")) {
           this.CountryCode = xpathHelper.read("//" + addressNodeName + "/CountryCode");
       }
       if (xpathHelper.doesNodeExist("//" + addressNodeName + "/PostalCode")) {
           this.PostalCode = xpathHelper.read("//" + addressNodeName + "/PostalCode");
       }
   }

   /**
    * Constructor that takes an XML Node and populates the DO 
    * 
    * Assumes the XML looks like this:<P>
    * <shippingaddress><Line1>680 Sunnyside Ave</Line1><City>Audubon</City><MainDivision>PA</MainDivision><CountryCode>US</CountryCode><PostalCode>19403</PostalCode></shippingaddress>
    * <P>
    * caller needs to supply the Parent Node name ("shippingaddress" in this example) as a parameter. 
    * 
    * 
    * @param pXMLFileName Full path and file name of XML file to load 
    * @param pFileEncoding File Encoding type, an Enum defined in the FileManager utility class
    * @param pParentNodeName The parent node name 
    * @throws Eb2cAPITestHarnessException
    */
   public ServiceAddressDO (String pXMLFileName, FileManager.ALLOWED_FILE_ENCODING pFileEncoding, String pParentNodeName) throws Eb2cAPITestHarnessException {

       String xmlFileContents = null;
       
       // get the contents of the specified file 
       xmlFileContents =  FileManager.readXMLFiletoString(pXMLFileName, pFileEncoding);

       //set the attributes of the DO based on the 
       populateDOfromXMLData(xmlFileContents, pParentNodeName);

   }
   
   
   /**
    * constructor that sets values
    * 
    * @param pLine1 (Required) the first line of the address
    * @param pLine2 (Optional) the  line of the address
    * @param pLine3 (Optional) the  line of the address
    * @param pLine4 (Optional) the  line of the address
    * @param pCity (Required)
    * @param pMainDivision The State/Province/Prefecture of the address, 
    * @param pCountryCode (Required)
    * @param pPostalCode (Required)
    */
   public ServiceAddressDO (
           String pLine1, String pLine2,
           String pLine3, String pLine4,
           String pCity, String pMainDivision,
           String pCountryCode, String pPostalCode) {

           this.Line1 = pLine1;
           this.Line2 = pLine2;
           this.Line3 = pLine3;
           this.Line4 = pLine4;
           this.City = pCity;
           this.MainDivision = pMainDivision;
           this.CountryCode = pCountryCode;
           this.PostalCode = pPostalCode;
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
       returnValue.append("\n\nEb2cAddressDO")
           .append("\n Line1: ").append(Line1) 
           .append("\n Line2: ").append(Line2)
           .append("\n Line3: ").append(Line3)
           .append("\n Line4: ").append(Line4)
           .append("\n City: ").append(City)           
           .append("\n MainDivision (a.k.a state): ").append(MainDivision)
           .append("\n CountryCode: ").append(CountryCode)                      
           .append("\n PostalCode: ").append(PostalCode).append("\n");

       return returnValue.toString();
   }
   
 
   
   /**
    * Returns an XML Node for and order using the address data in the object
    * The Node Name is passed as the parameter 
    *   
    * @param pNodeName The name needed for the address block
    * @return An XML Node with the address information
    */
   public Node getAddressAsNode(String pNodeName) throws Eb2cAPITestHarnessException {
 
       DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
       DocumentBuilder docBuilder = null;
       Node addressNode = null;
       try {
           docBuilder = dbfac.newDocumentBuilder();
       } catch (ParserConfigurationException e) {

           logger.info("ParserConfigurationException Exception caught in Eb2cAddressDO.getAddressAsXMLNode() when trying to create a new DocBuilder.", e);

           logger.error(e);
           throw new Eb2cAPITestHarnessException("ParserConfigurationException when attempting to create a DocBuilder. " + e);
       }
     
       Document doc = docBuilder.newDocument();
       //Creating the XML tree

       //create the root element and add it to the document
       Element root = doc.createElement("addresses");
       doc.appendChild(root);

       //set the name of the Node to the passed parameter  
       addressNode = doc.createElement(pNodeName);
       root.appendChild(addressNode);
       
       // add the data from this object to the Node IF 
       //there is data in the property 
       doc = XMLHelper.addStringNode("Line1",Line1,addressNode,doc);
       doc = XMLHelper.addStringNode("Line2",Line2,addressNode,doc);
       doc = XMLHelper.addStringNode("Line3",Line3,addressNode,doc);     
       doc = XMLHelper.addStringNode("Line4",Line4,addressNode,doc);
       doc = XMLHelper.addStringNode("City",City,addressNode,doc);     
       doc = XMLHelper.addStringNode("MainDivision",MainDivision,addressNode,doc);
       doc = XMLHelper.addStringNode("CountryCode",CountryCode,addressNode,doc);
       doc = XMLHelper.addStringNode("PostalCode",PostalCode,addressNode,doc);
 
       return addressNode;
 }


   /**
    * 
    * Gets the data from the Object as a wellformed valid XML string
    *  
    * @param pNodeName The name of the node to extract
    * @return A string of the XML
    * @throws Eb2cAPITestHarnessException
    */
   public String getAddressAsXML(String pNodeName) throws Eb2cAPITestHarnessException {

       //Get the object data as a node
       Node addressNode = getAddressAsNode(pNodeName);

       //initialize a transformer 
       Transformer transformer = null;
       
       //return String
       String xmlString = null;
       
       try {
           
           transformer = TransformerFactory.newInstance().newTransformer();
           transformer.setOutputProperty(OutputKeys.INDENT, "no");

           //initialize StreamResult with File object to save to file
           StreamResult result = new StreamResult(new StringWriter());
           DOMSource source = new DOMSource(addressNode);
           transformer.transform(source, result);
           xmlString = result.getWriter().toString();
           
           logger.info("\n\n -----the xml of this address ----- \n" + xmlString + "\n\n");
           
       } catch (TransformerException e) {
           logger.info("TransformerException Exception caught in Eb2cAddressDO.getAddressAsXML() when trying to create XML Output.", e);

           logger.error(e);
           throw new Eb2cAPITestHarnessException("ParserConfigurationException when attempting to create XML Output. " + e);

       } catch (TransformerFactoryConfigurationError e1) {
           logger.info("TransformerFactoryConfigurationError Exception caught in Eb2cAddressDO.getAddressAsXML() when trying to create XML Output.", e1);

           logger.error(e1);
           throw new Eb2cAPITestHarnessException("TransformerFactoryConfigurationError when attempting to create XML Output. " + e1);

       }

       return xmlString;
   }




   /**
    * @return the line1
    */
   public String getLine1() {
       return Line1;
   }


   /**
    * @param line1 the line1 to set
    */
   public void setLine1(String line1) {
       Line1 = line1;
   }


   /**
    * @return the line2
    */
   public String getLine2() {
       return Line2;
   }


   /**
    * @param line2 the line2 to set
    */
   public void setLine2(String line2) {
       Line2 = line2;
   }


   /**
    * @return the line3
    */
   public String getLine3() {
       return Line3;
   }


   /**
    * @param line3 the line3 to set
    */
   public void setLine3(String line3) {
       Line3 = line3;
   }


   /**
    * @return the line4
    */
   public String getLine4() {
       return Line4;
   }


   /**
    * @param line4 the line4 to set
    */
   public void setLine4(String line4) {
       Line4 = line4;
   }


   /**
    * @return the city
    */
   public String getCity() {
       return City;
   }


   /**
    * @param city the city to set
    */
   public void setCity(String city) {
       City = city;
   }


   /**
    * @return the mainDivision
    */
   public String getMainDivision() {
       return MainDivision;
   }


   /**
    * @param mainDivision the mainDivision to set
    */
   public void setMainDivision(String mainDivision) {
       MainDivision = mainDivision;
   }


   /**
    * @return the countryCode
    */
   public String getCountryCode() {
       return CountryCode;
   }


   /**
    * @param countryCode the countryCode to set
    */
   public void setCountryCode(String countryCode) {
       CountryCode = countryCode;
   }


   /**
    * @return the postalCode
    */
   public String getPostalCode() {
       return PostalCode;
   }


   /**
    * @param postalCode the postalCode to set
    */
   public void setPostalCode(String postalCode) {
       PostalCode = postalCode;
   }


   
   
   
}
