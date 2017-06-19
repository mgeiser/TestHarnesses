package com.gsipartners.eb2c.api.testharness;

import java.util.Iterator;
import java.util.Map;

import com.gsipartners.eb2c.api.testharness.dataobjects.order.OrderCustomAttributesDO;
import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;

public class TestOrderCustomeAttributesDO {

    /**
     * @param args
     */
    public static void main(String[] args) {

        OrderCustomAttributesDO testCADO = new OrderCustomAttributesDO();
        testCADO.customAttributes.put("CustomOrderAttrib1","yyy");
        testCADO.customAttributes.put("CustomOrderAttrib2","xxx");
        
        
        System.out.println("\n----------------------");
        System.out.println("Iterate over the Hashmap manually");
        Iterator entries = testCADO.customAttributes.entrySet().iterator(); 
        while (entries.hasNext()) {     
            Map.Entry entry = (Map.Entry) entries.next();     
            String key = (String)entry.getKey();     
            String value = (String)entry.getValue();    
            System.out.println("Key = " + key + ", Value = " + value); 
         } 
        
        System.out.println("\n----------------------");
        System.out.println("Call toString() to get the values stored in the HashMap");        
        System.out.println(testCADO.toString());        
        

        testCADO.setTestValues();
        
        try {
            System.out.println("Get just the Name node: \n" + testCADO.getCustomAttributeNodeAsXML());
            System.out.println("\nSince we got the correct Name XML, the Node is right too. \n");
        } catch (Eb2cAPITestHarnessException e) {
            e.printStackTrace();
        }


    }

}
