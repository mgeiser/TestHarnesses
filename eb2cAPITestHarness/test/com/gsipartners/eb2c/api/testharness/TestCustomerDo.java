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

import com.gsipartners.eb2c.api.testharness.dataobjects.order.OrderPersonDO;
import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;

public class TestCustomerDo {

    
    
    public static void main(String[] args) {
        String customerXML = null;
        String nameXML = null;
        Node customerNode = null;
        Node nameNode = null;

        
        OrderPersonDO test1 = new OrderPersonDO();
        test1.setTestValues();
        try {
            System.out.println(test1.toString());
            System.out.println(test1.getCustomerNodeAsXML());
        } catch (Eb2cAPITestHarnessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        System.out.println("\n----------------------");
        
        
        OrderPersonDO test2 = new OrderPersonDO("Mr.","Michael", "J", "Geiser","M", "1970-01-01", "12345789", "mgeiser@ebay.com", "194-49-0001", false);

        System.out.println(test2.toString());

        try {
            customerNode = test2.getCustomerNode();
        } catch (Eb2cAPITestHarnessException e) {
            e.printStackTrace();
        }
        
        //initialize a transformer 
        Transformer transformer = null;

        //return String
        String xmlString = null;
        
        try {
            
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "no");

            //initialize StreamResult with File object to save to file
            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(test2.getCustomerNode());
            transformer.transform(source, result);
            xmlString = result.getWriter().toString();
            
            System.out.println("\n -----the xml of this Customer ----- \n" + xmlString + "\n");
            
        } catch (TransformerException e) {
            System.out.println("TransformerException Exception caught when trying to create XML Output.");
            e.printStackTrace();

        } catch (TransformerFactoryConfigurationError e1) {
            System.out.println("TransformerFactoryConfigurationError Exception caught when trying to create XML Output.");
            e1.printStackTrace();

        } catch (Eb2cAPITestHarnessException e) {
            e.printStackTrace();
        }

        
        System.out.println("\n\n----------------------\n\n");
        
        try {
            System.out.println("Get just the Name node: \n" + test2.getNameNodeAsXML("ParentName"));
            System.out.println("\nSince we got the correct Name XML, the Node is right too. \n");
        } catch (Eb2cAPITestHarnessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        test2.setHonorific(null);

        
        try {
            System.out.println("Get just the Name node: \n" + test2.getNameNodeAsXML("Name"));
            System.out.println("\nSince we got the correct Name XML, the Node is right too. \n");
        } catch (Eb2cAPITestHarnessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }

    
    
    
}
