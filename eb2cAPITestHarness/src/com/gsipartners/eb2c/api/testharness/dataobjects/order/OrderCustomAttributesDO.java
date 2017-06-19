package com.gsipartners.eb2c.api.testharness.dataobjects.order;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

public class OrderCustomAttributesDO {


    //setup the logger for the application error logs and run logs
    private static Logger logger = Logger.getLogger(OrderPersonDO.class);
       
    //creating a hash map
   public HashMap<String, String> customAttributes = new HashMap<String, String>();
   
   
   //toString()
   //setTestValues
   //getNode()
   //getNodeasXML()
   
   
   
   
   /**
    * Overrides the toString() method.
    * 
    * @return the string of data in the value object
    * @see java.lang.Object#toString()
    */
   public String toString() {
       StringBuffer returnValue = new StringBuffer();
       
       returnValue.append("\nOrderCustomAttributesDO");
       
       String key = null;     
       String value = null;
       
       //Iterate over the data in the HashMap and spit it out 
       Iterator entries = customAttributes.entrySet().iterator(); 
       while (entries.hasNext()) {     
           Map.Entry entry = (Map.Entry) entries.next();
           key = (String)entry.getKey();     
           value = (String)entry.getValue();    
           returnValue.append("\n").append(key).append(": ").append(value);
        } 
       returnValue.append("\n");

       return returnValue.toString();
   }
   
   /**
    * Quick set of test Values that will work with orders
    */
   public void setTestValues() {
       
       customAttributes.put("CustomOrderAttrib1","aaa");
       customAttributes.put("CustomOrderAttrib2","bbb");
   }
   
   
   /**
    * Returns an XML Node for the full Customer ID 
    *   
    * @return An XML Node
    */
   public Node getCustomAttributeNode() throws Eb2cAPITestHarnessException {
 
       String rootNodeName = "CustomAttributes";
       String attributeNodeName = "Attribute";
       String keyNodeName = "Key";
       String valueNodeName = "Value";
       
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
       Element rootNode = doc.createElement(rootNodeName);
       doc.appendChild(rootNode);
       
       //set the name of the Node to the passed parameter  
       Node attributeNode = null;

       
       String key = null;
       String value = null;
       
       //Iterate over the data in the HashMap and spit it out 
       Iterator entries = customAttributes.entrySet().iterator(); 
       while (entries.hasNext()) {     
           Map.Entry entry = (Map.Entry) entries.next();
           key = (String)entry.getKey();     
           value = (String)entry.getValue();    

           //add the BrowserData node  
           attributeNode = doc.createElement(attributeNodeName);
           rootNode.appendChild(attributeNode);
           
           //create the key node
           doc = XMLHelper.addStringNode(keyNodeName,key,attributeNode,doc);
           //create the key node  
           doc = XMLHelper.addStringNode(valueNodeName,value,attributeNode,doc);
           
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
   public String getCustomAttributeNodeAsXML() throws Eb2cAPITestHarnessException {

       //Get the object data as a node
       Node customerNode = getCustomAttributeNode();
       
       //Get a String of this Node 
       return XMLHelper.getNodeAsXML(customerNode);

   }
   
   
   
   
    /**
     * @return the customAttributes
     */
    public HashMap<String, String> getCustomAttributes() {
        return customAttributes;
    }

    /**
     * @param customAttributes the customAttributes to set
     */
    public void setCustomAttributes(HashMap<String, String> customAttributes) {
        this.customAttributes = customAttributes;
    }

    
    
    
    
    //Implement this as a HashMap that stores Key/value pairs and creates a Node/XML like this
    
    /*

<CustomAttributes>
    <Attribute>
        <Key>CustomOrderAttrib1</Key>
        <Value>yyy</Value>
    </Attribute>
    <Attribute>
        <Key>CustomOrderAttrib2</Key>
        <Value>xxx</Value>
    </Attribute>
</CustomAttributes>

     */
    
    
}
