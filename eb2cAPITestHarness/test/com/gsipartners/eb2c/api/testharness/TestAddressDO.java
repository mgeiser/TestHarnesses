package com.gsipartners.eb2c.api.testharness;
import org.w3c.dom.Node;

import com.gsipartners.eb2c.api.testharness.dataobjects.ServiceAddressDO;
import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;
import com.gsipartners.eb2c.api.testharness.utilities.FileManager;


public class TestAddressDO {

    /**
     * @param args
     */
    public static void main(String[] args) {

        String addressXML = null;
        String addressNodeName = "shippingaddress";
        Node addressNode = null;
        

        ServiceAddressDO test=null;
        try {
            test = new ServiceAddressDO("C:\\Users\\geiserm\\EclipseTestHarnesses\\eb2cAPITestHarness\\resources\\test\\Eb2cAddressDO.xml", FileManager.ALLOWED_FILE_ENCODING.UTF_8 ,"shippingaddress" );
        } catch (Eb2cAPITestHarnessException e2) {
            e2.printStackTrace();
        }

        System.out.println(test.toString());
        
        System.out.println("\n\n----------------------\n\n");
        
        
        ServiceAddressDO test1 = new ServiceAddressDO(
         "680 Sunnyside Ave", null, null, null,  
         "Audubon", "PA", "US", "19403");
        
        try {
            addressXML = test1.getAddressAsXML(addressNodeName);
            System.out.println("Test XML: " + addressXML);
            
        } catch (Eb2cAPITestHarnessException e) {
            e.printStackTrace();
        }
        
        
        System.out.println("\n\n----------------------\n\n");
        
        ServiceAddressDO test2 = new ServiceAddressDO(addressXML,addressNodeName);

        try {
            addressXML = test2.getAddressAsXML(addressNodeName);
            System.out.println("Test2 XML: " + addressXML);
            
        } catch (Eb2cAPITestHarnessException e) {
            e.printStackTrace();
        }

        System.out.println("\n\n----------------------\n\n");
        
        try {
            addressNode = test2.getAddressAsNode(addressNodeName);
        } catch (Eb2cAPITestHarnessException e1) {
            e1.printStackTrace();
        }

        ServiceAddressDO test3 = null;
        try {
            test3 = new ServiceAddressDO(addressNode,addressNodeName);
        } catch (Eb2cAPITestHarnessException e) {
            e.printStackTrace();
        }

        System.out.println(test3.toString());
        
    }

}
