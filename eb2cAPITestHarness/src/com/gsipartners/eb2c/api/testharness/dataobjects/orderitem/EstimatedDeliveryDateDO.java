package com.gsipartners.eb2c.api.testharness.dataobjects.orderitem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.gsipartners.eb2c.api.testharness.Eb2cAPIServiceCallConstants;
import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;
import com.gsipartners.eb2c.api.testharness.utilities.Eb2cAPITestHarnessUtilities;
import com.gsipartners.eb2c.api.testharness.utilities.XMLHelper;

/**
 * Estimated Delivery Date Data Object
 * <P>
 * <B>Design Note</B>:  Chose not to model a "Delivery Date" DO for the From and To dates<br>
 * It is repeated three times here<br>
 * It isn't used elsewhere<br>
 * The flat structure conveys intent and function just as well<br> 
 * A DeliveryDateDO would add complexity but deliver no real benefits (IMHO)   
 * <P>
 * You'll have to deal with it if you're a purist...
 * 
 *
 */
public class EstimatedDeliveryDateDO {

    

    //setup the logger for the application error logs and run logs
    private static Logger logger = Logger.getLogger(EstimatedDeliveryDateDO.class);
    
    
    public static enum DeliveryEstimateMode {
        CALIBRATION("CALIBRATION"), 
        ENABLED("ENABLED"),
        LEGACY("LEGACY");
        
        private String stringValue;
        
        DeliveryEstimateMode(String s) { 
            stringValue = s; 
        }    
        
        public String toString() { 
            //allows you to do this DeliveryEstimateMode.ENABLED.toString();
            
            return stringValue;
        } 
    }
    
    private Date deliveryWindowFrom = null; 
    private Date deliveryWindowTo = null;
    private Date shippingWindowFrom = null; 
    private Date shippingWindowTo = null;
    private DeliveryEstimateMode mode = null;
    private Date creationTime = null;
    private boolean display = false;
    private String message = null;
    private String template = null;
    private Date originalExpectedShipmentDateFrom = null;
    private Date originalExpectedShipmentDateTo = null;


    
    /**
     * Quick set of test Values that will work with orders
     */
    public void setTestValues() {
        
        try {
            this.deliveryWindowFrom = new SimpleDateFormat("MM-dd-yyyyy").parse("06-01-2012");
            this.deliveryWindowTo = new SimpleDateFormat("MM-dd-yyyyy").parse("06-10-2012");
            this.shippingWindowFrom = new SimpleDateFormat("MM-dd-yyyyy").parse("05-25-2012");
            this.shippingWindowTo = new SimpleDateFormat("MM-dd-yyyyy").parse("05-30-2012");
            this.mode = DeliveryEstimateMode.ENABLED;
            this.creationTime = new SimpleDateFormat("MM-dd-yyyyy hh:mm:ss").parse("05-20-2012 13:13:13");
            this.display = true;
            this.message = "Expected Delivery";
            this.template = "HTML";
            this.originalExpectedShipmentDateFrom = new SimpleDateFormat("MM-dd-yyyyy").parse("05-21-2012");
            this.originalExpectedShipmentDateTo = new SimpleDateFormat("MM-dd-yyyyy").parse("05-21-2012");
        } catch (ParseException e) {
            // We'll just eat this exception...it won't catch one here unless someone is screwing with the data
            e.printStackTrace();
        }
    }
    
    
    
    
    /**
     * Overrides the toString() method.
     * 
     * @return the string of data in the value object
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer returnValue = new StringBuffer();
        
        returnValue.append("\n\n EstimatedDeliveryDateDO")
            .append("\n DeliveryWindowFrom: ").append(Eb2cAPITestHarnessUtilities.getFormatedDateTime(this.deliveryWindowFrom))
            .append("\n DeliveryWindowTo: ").append(Eb2cAPITestHarnessUtilities.getFormatedDateTime(this.deliveryWindowTo))
            .append("\n ShippingWindowFrom: ").append(Eb2cAPITestHarnessUtilities.getFormatedDateTime(this.shippingWindowFrom))
            .append("\n ShippingWindowTo: ").append(Eb2cAPITestHarnessUtilities.getFormatedDateTime(this.shippingWindowTo))
            .append("\n Mode: ").append(this.mode.toString())
            .append("\n CreationTime: ").append(Eb2cAPITestHarnessUtilities.getFormatedDateTime(this.creationTime))
            .append("\n Display: ").append(this.display)
            .append("\n Message: ").append(this.message)
            .append("\n Template: ").append(this.template)
            .append("\n OriginalExpectedShipmentDateFrom: ").append(Eb2cAPITestHarnessUtilities.getFormatedDateTime(originalExpectedShipmentDateFrom))
            .append("\n OriginalExpectedShipmentDateTo: ").append(Eb2cAPITestHarnessUtilities.getFormatedDateTime(originalExpectedShipmentDateTo))
            .append("\n");

        return returnValue.toString();
    }
 
    
    /**
     * Returns an XML Node for the full Customer ID 
     *   
     * @return An XML Node
     */
    public Node getEstimatedDeliveryDateNode() throws Eb2cAPITestHarnessException {
  
        String EDDName = "EstimatedDeliveryDate";
        String deliveryWindow = "DeliveryWindow";
        String shippingWindow = "ShippingWindow";
        String originalExpectedShipmentDate = "OriginalExpectedShipmentDate";
        
        
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
        Element rootNode = doc.createElement(EDDName);
        doc.appendChild(rootNode);

        //create the deliveryWindow element and add it to the document
        Element deliveryWindowNode = doc.createElement(deliveryWindow);
        rootNode.appendChild(deliveryWindowNode);
        doc = XMLHelper.addStringNode("From",Eb2cAPITestHarnessUtilities.getFormatedDate(this.deliveryWindowFrom),deliveryWindowNode,doc);
        doc = XMLHelper.addStringNode("To",Eb2cAPITestHarnessUtilities.getFormatedDate(this.deliveryWindowTo),deliveryWindowNode,doc);

        //create the deliveryWindow element and add it to the document
        Element shippingWindowNode = doc.createElement(shippingWindow);
        rootNode.appendChild(shippingWindowNode);
        doc = XMLHelper.addStringNode("From",Eb2cAPITestHarnessUtilities.getFormatedDate(this.shippingWindowFrom),shippingWindowNode,doc);
        doc = XMLHelper.addStringNode("To",Eb2cAPITestHarnessUtilities.getFormatedDate(this.shippingWindowTo),shippingWindowNode,doc);

        doc = XMLHelper.addStringNode("mode",this.mode.toString(),rootNode,doc);
        doc = XMLHelper.addStringNode("CreationTime",Eb2cAPITestHarnessUtilities.getFormatedDateTime(this.creationTime),rootNode,doc);
        doc = XMLHelper.addStringNode("Message",this.message,rootNode,doc);
        doc = XMLHelper.addStringNode("Template",this.template,rootNode,doc);
        //originalExpectedShipmentDate
        //create the deliveryWindow element and add it to the document
        Element originalExpectedShipmentDateNode = doc.createElement(originalExpectedShipmentDate);
        rootNode.appendChild(originalExpectedShipmentDateNode);
        doc = XMLHelper.addStringNode("From",Eb2cAPITestHarnessUtilities.getFormatedDate(this.originalExpectedShipmentDateFrom),originalExpectedShipmentDateNode,doc);
        doc = XMLHelper.addStringNode("To",Eb2cAPITestHarnessUtilities.getFormatedDate(this.originalExpectedShipmentDateTo),originalExpectedShipmentDateNode,doc);
        
        
        return rootNode;
  }    
    
    
    /**
     * 
     * Gets the just the Name Node from the data from the Object as a well formed valid XML string
     *  
     * @return A string of the XML
     * @throws Eb2cAPITestHarnessException
     */
    public String getEstimatedDeliveryDateNodeAsXML() throws Eb2cAPITestHarnessException {

        //Get the object data as a node
        Node nameNode = getEstimatedDeliveryDateNode();
        
        //Get a String of this Node 
        return XMLHelper.getNodeAsXML(nameNode);

    }




    /**
     * @return the deliveryWindowFrom
     */
    public Date getDeliveryWindowFrom() {
        return deliveryWindowFrom;
    }




    /**
     * @param deliveryWindowFrom the deliveryWindowFrom to set
     */
    public void setDeliveryWindowFrom(Date deliveryWindowFrom) {
        this.deliveryWindowFrom = deliveryWindowFrom;
    }




    /**
     * @return the deliveryWindowTo
     */
    public Date getDeliveryWindowTo() {
        return deliveryWindowTo;
    }




    /**
     * @param deliveryWindowTo the deliveryWindowTo to set
     */
    public void setDeliveryWindowTo(Date deliveryWindowTo) {
        this.deliveryWindowTo = deliveryWindowTo;
    }




    /**
     * @return the shippingWindowFrom
     */
    public Date getShippingWindowFrom() {
        return shippingWindowFrom;
    }




    /**
     * @param shippingWindowFrom the shippingWindowFrom to set
     */
    public void setShippingWindowFrom(Date shippingWindowFrom) {
        this.shippingWindowFrom = shippingWindowFrom;
    }




    /**
     * @return the shippingWindowTo
     */
    public Date getShippingWindowTo() {
        return shippingWindowTo;
    }




    /**
     * @param shippingWindowTo the shippingWindowTo to set
     */
    public void setShippingWindowTo(Date shippingWindowTo) {
        this.shippingWindowTo = shippingWindowTo;
    }




    /**
     * @return the mode
     */
    public DeliveryEstimateMode getMode() {
        return mode;
    }




    /**
     * @param mode the mode to set
     */
    public void setMode(DeliveryEstimateMode mode) {
        this.mode = mode;
    }




    /**
     * @return the creationTime
     */
    public Date getCreationTime() {
        return creationTime;
    }




    /**
     * @param creationTime the creationTime to set
     */
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }




    /**
     * @return the display
     */
    public boolean isDisplay() {
        return display;
    }




    /**
     * @param display the display to set
     */
    public void setDisplay(boolean display) {
        this.display = display;
    }




    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }




    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }




    /**
     * @return the template
     */
    public String getTemplate() {
        return template;
    }




    /**
     * @param template the template to set
     */
    public void setTemplate(String template) {
        this.template = template;
    }




    /**
     * @return the originalExpectedShipmentDateFrom
     */
    public Date getOriginalExpectedShipmentDateFrom() {
        return originalExpectedShipmentDateFrom;
    }




    /**
     * @param originalExpectedShipmentDateFrom the originalExpectedShipmentDateFrom to set
     */
    public void setOriginalExpectedShipmentDateFrom(
            Date originalExpectedShipmentDateFrom) {
        this.originalExpectedShipmentDateFrom = originalExpectedShipmentDateFrom;
    }




    /**
     * @return the originalExpectedShipmentDateTo
     */
    public Date getOriginalExpectedShipmentDateTo() {
        return originalExpectedShipmentDateTo;
    }




    /**
     * @param originalExpectedShipmentDateTo the originalExpectedShipmentDateTo to set
     */
    public void setOriginalExpectedShipmentDateTo(
            Date originalExpectedShipmentDateTo) {
        this.originalExpectedShipmentDateTo = originalExpectedShipmentDateTo;
    }
    
    
    
    
}



/*

<xsd:element name="DeliveryWindow" type="DateRange" minOccurs="0">
    <xsd:element name="From" type="xsd:date"/>
    <xsd:element name="To" type="xsd:date"/>
<xsd:element name="ShippingWindow" type="DateRange" minOccurs="0">
    <xsd:element name="From" type="xsd:date"/>
    <xsd:element name="To" type="xsd:date"/>
<xsd:element name="Mode" type="DeliveryEstimateMode" minOccurs="0"/>
<xsd:element name="CreationTime" type="xsd:dateTime" minOccurs="0"/>
<xsd:element name="Display" type="xsd:boolean" minOccurs="0"/>  
<xsd:element name="Message" type="DeliveryType" minOccurs="0"> String
<xsd:element name="Template" type="DeliveryType" minOccurs="0"> String
<xsd:element name="OriginalExpectedShipmentDate" type="xsd:date" minOccurs="0">
    <xsd:element name="From" type="xsd:date"/>
    <xsd:element name="To" type="xsd:date"/>




*/