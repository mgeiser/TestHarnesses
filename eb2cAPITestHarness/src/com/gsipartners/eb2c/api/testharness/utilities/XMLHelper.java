package com.gsipartners.eb2c.api.testharness.utilities;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.gsipartners.eb2c.api.testharness.Eb2cAPITestHarness;
import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;

public class XMLHelper {

    //setup the logger for the application error logs and run logs
    private static Logger logger = Logger.getLogger(Eb2cAPITestHarness.class);

    
    /**
     * @param pNodeName
     * @param pNodeValue
     * @param pParentNode
     * @param pDoc
     */
    public static Document addStringNode(String pNodeName, String pNodeValue, Node pParentNode, Document pDoc){

           // do nothing if the value of the property is null or zero length
           //set the name of the Node to the passed parameter  
           if ((pNodeValue != null) && (pNodeValue.length() >= 1)) {
               Node tempNode = pDoc.createElement(pNodeName);
               tempNode.setTextContent(pNodeValue);
               pParentNode.appendChild(tempNode);
           }
           
           return pDoc;
           
       }

    
    /**
     * 
     * Gets the data from the Object as a well formed valid XML string
     *  
     * @param pNodeName The name of the node to extract
     * @return A string of the XML
     * @throws Eb2cAPITestHarnessException
     */
    public static String getNodeAsXML(Node pInputNode) throws Eb2cAPITestHarnessException {

        //initialize a transformer 
        Transformer transformer = null;
        
        //return String
        String xmlString = null;
        
        try {
            
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            //initialize StreamResult with File object to save to file
            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(pInputNode);
            transformer.transform(source, result);
            xmlString = result.getWriter().toString();
            
            logger.info("\n\n -----the xml of this node ----- \n" + xmlString + "\n\n");
            
        } catch (TransformerException e) {
            logger.info("TransformerException Exception caught when trying to create XML Output.", e);

            logger.error(e);
            throw new Eb2cAPITestHarnessException("ParserConfigurationException when attempting to create XML Output. " + e);

        } catch (TransformerFactoryConfigurationError e1) {
            logger.info("TransformerFactoryConfigurationError Exception caught when trying to create XML Output.", e1);
            logger.error(e1);
            throw new Eb2cAPITestHarnessException("TransformerFactoryConfigurationError when attempting to create XML Output. " + e1);

        }

        return xmlString;
    }
    
    
}
