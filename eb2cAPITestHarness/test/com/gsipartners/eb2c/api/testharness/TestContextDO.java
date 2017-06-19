package com.gsipartners.eb2c.api.testharness;

import com.gsipartners.eb2c.api.testharness.dataobjects.order.OrderContextDO;
import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;

public class TestContextDO {

    public static void main(String[] args) {
        String contextXML = null;
        
        OrderContextDO test1 = new OrderContextDO();
        test1.setTestValues();
        try {
            System.out.println(test1.getContextNodeAsXML());
        } catch (Eb2cAPITestHarnessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        System.out.println("\n----------------------\n");
        System.out.println(test1.toString());
        
        
        

    }

}
