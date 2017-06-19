package com.gsipartners.eb2c.api.testharness.dataobjects.order;

import org.apache.log4j.Logger;

import com.gsipartners.eb2c.api.testharness.dataobjects.orderitem.EstimatedDeliveryDateDO;

public class OrderItemDO {

    
    //setup the logger for the application error logs and run logs
    private static Logger logger = Logger.getLogger(OrderPersonDO.class);

    public static enum FulfillmentChannel {
        SHIP_TO_STORE("SHIP_TO_STORE"), 
        STORE_PICK_UP("STORE_PICK_UP"),
        SHIP_TO_HOME("SHIP_TO_HOME");
        
        private String stringValue;
        
        FulfillmentChannel(String s) { 
            stringValue = s; 
        }    
        
        public String toString() { 
            //allows you to do this FulfillmentChannel.STORE_PICK_UP.toString();
            
            return stringValue;
        }   

    
    private String itemId = null;
    private String description = null;
    private String descriptionColor = null;
    private String descriptionSize = null;
    private String department = null;
    //pricing
    private String shippingProgram =null;
    private FulfillmentChannel fulfillmentChannel = null; 
    private EstimatedDeliveryDateDO estimatedDeliveryDate = null;
    private String deliveryInstructions = null;
    private String vendorId = null;
    private String vendorName = null;
    //gifting
    private String shopRunnerMessage = null;
    //customization
    private String serialNumber = null;
    private OrderCustomAttributesDO customAtttributes = null;
    private String giftRegistryCancelUrl = null;
    private String reservationId = null;
    

    }
    
    
    // ref the OrderItemRequestType
    
    /*
    Attributes
        id
        webLineId
        isHiddenGift
    
    Sequence        
        *<xsd:element name="ItemId" type="ItemId"/>
        *<xsd:element name="Quantity" type="xsd:decimal"/>
        *<xsd:element name="Description" type="ProductDescriptionRequestType" minOccurs="0"/>
            <xsd:element name="Color" type="ColorRequestType" minOccurs="0">
            <xsd:element name="Size" type="SizeRequestType" minOccurs="0">
        *<xsd:element name="Department" type="xsd:string" minOccurs="0"/>
        
        <xsd:element name="Pricing" type="OrderItemPricingRequest"/>
            <xsd:element name="Merchandise" type="PriceGroupRequestType"/>
            <xsd:element name="Shipping" type="PriceGroupRequestType" minOccurs="0"/>
            <xsd:element name="Duty" type="PriceGroupRequestType" minOccurs="0"/>
        
        *<xsd:element name="ShippingProgram" minOccurs="0">
        *<xsd:element name="ShippingMethod" type="ShippingMethodType" minOccurs="0"/>
        * <xsd:element name="FulfillmentChannel" type="FulfillmentType" minOccurs="0"/>
        *<xsd:element name="EstimatedDeliveryDate" type="EstimatedDeliveryDateType" minOccurs="0"/>
        *<xsd:element name="DeliveryInstructions" type="xsd:normalizedString" minOccurs="0">
        *<xsd:element name="VendorId" type="xsd:string" minOccurs="0">
        *<xsd:element name="VendorName" type="xsd:string" minOccurs="0">
        <xsd:element name="Gifting" type="GiftRequestType" minOccurs="0"/>
        *<xsd:element name="ShopRunnerMessage" type="ShopRunnerMessageType" minOccurs="0"/>
        <xsd:element name="Customization" type="CustomizationServiceRequestType" minOccurs="0"/>
        *<xsd:element name="SerialNumber" type="xsd:string" minOccurs="0">
        <xsd:element name="CustomAttributes" type="CustomAttributesType" minOccurs="0" maxOccurs="1"/>    
        <xsd:element name="GiftRegistryCancelUrl" type="xsd:string" minOccurs="0">    
        <xsd:element name="ReservationId" type="ReservationId" minOccurs="0">    
    Sequence  
    
    
    */
    
    
    
    
}


/*



            <OrderItem id="item_1" webLineId="1">
                <ItemId>18-SMOKE6</ItemId>
                <Quantity>1</Quantity>
                <Description>
                    <Description>adidas Essentials Short Sleeve Tee</Description>
                    <Color id="red">Red</Color>
                    <Size id="10">10</Size>
                </Description>
                <Pricing>
                    <Merchandise>
                        <Amount>99.99</Amount>
                        <PromotionalDiscounts>
                            <Discount>
                                <Id>Promo3</Id>
                                <Code>PromoCode3</Code>
                                <Amount>4.99</Amount>
                                <Description>Item Discount for Loyalty</Description>
                                <EffectType>Value</EffectType>
                            </Discount>
                            <Discount>
                                <Id>Promo4</Id>
                                <Code>PromoCode4</Code>
                                <Amount>3.99</Amount>
                                <Description>Item Discount for Holidays</Description>
                            </Discount>
                        </PromotionalDiscounts>
                        <TaxData>
                            <TaxClass>890000</TaxClass>
                            <Taxes>
                                <Tax taxType="SALES" taxability="TAXABLE">
                                    <Situs>DESTINATION</Situs>
                                    <Jurisdiction jurisdictionLevel="STATE" jurisdictionId="PA">Pennsylvania</Jurisdiction>
                                    <Imposition ImpositionType="imp">imposition</Imposition>
                                    <EffectiveRate>0.06</EffectiveRate>
                                    <TaxableAmount>99.99</TaxableAmount>
                                    <CalculatedTax>5.99</CalculatedTax>
                                    <SellerRegistrationId>suresh123</SellerRegistrationId>
                                </Tax>
                                <Tax taxType="VAT" taxability="NONTAXABLE">
                                    <Situs>DESTINATION</Situs>
                                    <Jurisdiction jurisdictionLevel="STATE" jurisdictionId="PA">Pennsylvania</Jurisdiction>
                                    <Imposition ImpositionType="imp">imposition</Imposition>
                                    <EffectiveRate>0.06</EffectiveRate>
                                    <TaxableAmount>30.00</TaxableAmount>
                                    <CalculatedTax>1.80</CalculatedTax>
                                </Tax>
                            </Taxes>
                        </TaxData>
                        <UnitPrice>99.99</UnitPrice>
                    </Merchandise>
                    <Shipping>
                        <Amount>9.99</Amount>
                        <PromotionalDiscounts>
                            <Discount>
                                <Id>Promo1</Id>
                                <Code>PromoCode1</Code>
                                <Amount>1.99</Amount>
                                <Description>Shipping Discount for Loyalty</Description>
                            </Discount>
                            <Discount>
                                <Id>Promo2</Id>
                                <Code>PromoCode2</Code>
                                <Amount>2.99</Amount>
                                <Description>Shipping Discount for Holidays</Description>
                            </Discount>
                        </PromotionalDiscounts>
                        <TaxData>
                            <TaxClass>890000</TaxClass>
                            <Taxes>
                                <Tax taxType="SALES" taxability="EXEMPT">
                                    <Situs>SHIP_DESTINATION</Situs>
                                    <Jurisdiction jurisdictionLevel="STATE" jurisdictionId="PA">Pennsylvania
                                    </Jurisdiction>
                                    <Imposition ImpositionType="imp">ship tax imposition</Imposition>
                                    <EffectiveRate>0.06</EffectiveRate>
                                    <TaxableAmount>99.99</TaxableAmount>
                                    <CalculatedTax>5.99</CalculatedTax>
                                </Tax>
                            </Taxes>
                        </TaxData>
                        <UnitPrice>9.99</UnitPrice>
                    </Shipping>
                    <Duty>
                        <Amount>1.99</Amount>
                        <TaxData>
                            <TaxClass>890000</TaxClass>
                            <Taxes>
                                <Tax taxType="SALES" taxability="EXEMPT">
                                    <Situs>Duty_DESTINATION</Situs>
                                    <Jurisdiction jurisdictionLevel="STATE" jurisdictionId="PA">Pennsylvania</Jurisdiction>
                                    <Imposition ImpositionType="imp">duty tax imposition</Imposition>
                                    <EffectiveRate>0.02</EffectiveRate>
                                    <TaxableAmount>9.99</TaxableAmount>
                                    <CalculatedTax>2.99</CalculatedTax>
                                </Tax>
                            </Taxes>
                        </TaxData>
                        <UnitPrice>1.98</UnitPrice>
                    </Duty>
                </Pricing>
                <ShippingMethod>ANY_EXPR</ShippingMethod>
                <EstimatedDeliveryDate>
                     <DeliveryWindow>
                         <From>2012-02-12</From>
                         <To>2012-02-13</To>
                     </DeliveryWindow>
                     <ShippingWindow>
                         <From>2012-02-12</From>
                         <To>2012-02-13</To>
                     </ShippingWindow>
                     <Mode>ENABLED</Mode>
                     <Message>Expected Delivery</Message>
                </EstimatedDeliveryDate>
                <VendorId>Vendor1</VendorId>
                <VendorName>Broad Street's vendor</VendorName>
                <SerialNumber>123</SerialNumber>
                <CustomAttributes>
                    <Attribute>
                        <Key>CustomOrderItemAttrib1</Key>
                        <Value>BRMC</Value>
                    </Attribute>
                    <Attribute>
                        <Key>CustomOrderItemAttrib2</Key>
                        <Value>BJM</Value>
                    </Attribute>
                </CustomAttributes>
                <GiftRegistryCancelUrl>http://www.fogdog.com/giftregistry/cancel</GiftRegistryCancelUrl>
                <ReservationId>MJG-Res-0202-4</ReservationId>
            </OrderItem>



*/