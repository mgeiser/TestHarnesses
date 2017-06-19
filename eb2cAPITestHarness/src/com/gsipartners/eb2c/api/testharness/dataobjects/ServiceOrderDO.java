package com.gsipartners.eb2c.api.testharness.dataobjects;

import org.apache.log4j.Logger;

import com.gsipartners.eb2c.api.testharness.dataobjects.order.OrderPersonDO;

public class ServiceOrderDO {

    //setup the logger for the application error logs and run logs
    private static Logger logger = Logger.getLogger(ServiceOrderDO.class);

    public static enum LevelOfService {
        REGULAR("REGULAR"), 
        RUSH("RUSH");
        
        private String stringValue;
        
        LevelOfService(String s) { 
            stringValue = s; 
        }    
        
        public String toString() { 
            //allows you to do this LevelOfService.REGULAR.toString(); and get the value REGULAR instead of an index
            
            return stringValue;
        }   
    }
    
    public static enum SourceId {
        CHANNEL("CHANNEL"), 
        STORE("STORE"),
        SELLER("SELLER");
        
        private String stringValue;
        
        SourceId(String s) { 
            stringValue = s; 
        }    
        
        public String toString() { 
            //allows you to do this LevelOfService.REGULAR.toString(); and get the value REGULAR instead of an index
            
            return stringValue;
        }   
    }
    
    public static enum OrderType {
        SALES("SALES"), 
        RETURN("RETURN"),
        PURCHASE("PURCHASE"),
        TRANSFER("TRANSFER");
        
        private String stringValue;
        
        OrderType(String s) { 
            stringValue = s; 
        }    
        
        public String toString() { 
            //allows you to do this LevelOfService.REGULAR.toString(); and get the value REGULAR instead of an index
            
            return stringValue;
        }   
    }   
    
    
    
    /*

<XSD:Sequence>
    customerOrderId String (number?)
    Customer   R             OrderCustomerDO
    CreateTime  R            Date
    OrderItems   R           List of OrderItemDO
    Destinations  R          List of OrderDestinationDO 
    Payment        R         OrderPaymentDO
ShopRunnerMessage O          String
    Currency    R            String (enum later??)
    Associate    O           AssociateDO
    TaxHeader     O          TaxHeaderDO
    PrintedCatalogCode O     String
    Locale         O         String (enum later??)
Relationships    O           ???
    
    DashboardRepId    O      String (number?)
    Source           R       String  - must be  "EB2C"
OrderSource     O
Holds       O
    CustomAttributes O 
    OrderHistoryUrl  O       String
</XSD:Sequence>

VATInclusivePricing     O
levelOfService          O enum


    
    */
    
    
    
 
}
