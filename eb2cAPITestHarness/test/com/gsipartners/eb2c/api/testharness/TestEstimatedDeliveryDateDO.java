package com.gsipartners.eb2c.api.testharness;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gsipartners.eb2c.api.testharness.dataobjects.orderitem.EstimatedDeliveryDateDO;
import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;

public class TestEstimatedDeliveryDateDO {

    /**
     * @param args
     */
    public static void main(String[] args) {
     

        EstimatedDeliveryDateDO test1 = new EstimatedDeliveryDateDO();
        test1.setTestValues();
        System.out.println(test1.toString());
        
        
        try {
            System.out.println(test1.getEstimatedDeliveryDateNodeAsXML());
        } catch (Eb2cAPITestHarnessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     
        
        
        
        
        
    }

}
