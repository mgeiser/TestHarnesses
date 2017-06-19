package com.gsipartners.eb2c.api.testharness;

import com.gsipartners.eb2c.api.testharness.dataobjects.order.TaxHeaderDO;
import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;

public class TestTaxHeaderDO {

    public static void main(String[] args) {
        String taxHeaderXML = null;
        
        
        TaxHeaderDO test1 = new TaxHeaderDO();
        try {
            System.out.println(test1.getTaxHeaderNodeAsXML());
        } catch (Eb2cAPITestHarnessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        System.out.println("----------------------");
        System.out.println(test1.toString());
        System.out.println("----------------------");
        System.out.println("Setting the Error property to false");
        test1.setHasErrorFlag(false);
        System.out.println(test1.toString());
    
    
    }
    
    
    
}
