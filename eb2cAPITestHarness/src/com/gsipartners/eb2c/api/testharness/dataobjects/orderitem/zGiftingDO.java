package com.gsipartners.eb2c.api.testharness.dataobjects.orderitem;

import org.apache.log4j.Logger;

import com.gsipartners.eb2c.api.testharness.dataobjects.order.OrderPersonDO;

public class zGiftingDO {
    
    //setup the logger for the application error logs and run logs
    private static Logger logger = Logger.getLogger(OrderPersonDO.class);
    
    
    /*
    Gift
        ItemId
        Pricing**** Order-Datatypes-Common-1.0.xsd PriceGroupBaseType
            amount                  int
            amountRemainder         int
            PromotionalDiscounts    List of PromotionalDiscountSet 
                        PromotionalDiscountSet is a List of PromoDiscount
                            PromoDiscount
                                Id
                                Code
                                Amount
                                Description
                                EffectType
                                TaxdataDO
                                
             
            
        Message
            <xsd:sequence>
            <xsd:element name="To" type="InstructionType"> String 4000
            <xsd:element name="From" type="InstructionType">
            <xsd:element name="Message" type="InstructionType">
            </xsd:sequence>
    
    
    Packslip
            Message
            <xsd:sequence>
            <xsd:element name="To" type="InstructionType"> String 4000
            <xsd:element name="From" type="InstructionType">
            <xsd:element name="Message" type="InstructionType">
            </xsd:sequence>
    
    */
    
}
