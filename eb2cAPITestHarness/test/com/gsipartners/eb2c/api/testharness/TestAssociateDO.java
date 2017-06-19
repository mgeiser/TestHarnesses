package com.gsipartners.eb2c.api.testharness;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;

import com.gsipartners.eb2c.api.testharness.dataobjects.order.AssociateDO;
import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;
import com.gsipartners.eb2c.api.testharness.utilities.FileManager;

public class TestAssociateDO {
    
    public static void main(String[] args) {
        String associateXML = null;
        Node associateNode = null;

        AssociateDO test = null;
        test = new AssociateDO("Michael J Geiser", "12345", "8347");

        System.out.println(test.toString());
        
        System.out.println("\n\n----------------------\n\n");

        AssociateDO test2 = new AssociateDO();
        test2.setName("Michael 2 Geiser");
        test2.setNumber("emp2");
        test2.setStore("store2");
        
        try {
            System.out.println(test2.getNodeAsXML());
        } catch (Eb2cAPITestHarnessException e) {
            e.printStackTrace();
        }
        
        
        System.out.println("\n\n----------------------\n\n");

        AssociateDO test3 = new AssociateDO();
        test3.setName("Michael 3 Geiser");
        test3.setNumber("emp3");
        test3.setStore("store3");
        Node testNode = null;
        
        try {
            testNode = test3.getNode();
        } catch (Eb2cAPITestHarnessException e) {
            e.printStackTrace();
        }

        
        //Get the object data as a node
        Node addressNode = null;

        //initialize a transformer 
        Transformer transformer = null;

        //return String
        String xmlString = null;
        
        try {
            
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "no");

            //initialize StreamResult with File object to save to file
            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(test3.getNode());
            transformer.transform(source, result);
            xmlString = result.getWriter().toString();
            
            System.out.println("\n\n -----the xml of this associate ----- \n" + xmlString + "\n\n");
            
        } catch (TransformerException e) {
            System.out.println("TransformerException Exception caught when trying to create XML Output.");
            e.printStackTrace();

        } catch (TransformerFactoryConfigurationError e1) {
            System.out.println("TransformerFactoryConfigurationError Exception caught when trying to create XML Output.");
            e1.printStackTrace();

        } catch (Eb2cAPITestHarnessException e) {
            e.printStackTrace();
        }
        
        
    }
    
    
}
