package junk;

public class zTaxDO {

   /*
    taxType                         String
        "SALES"
        "SELLER_USE"
        "CONSUMER_USE"
        "VAT"
        "IMPORT_VAT"
    taxability
    Situs                   R       String
    Jurisdiction            O       String  Are there any restrictions? example is Pennsylvania.  Are there any data integrity constraints with juridictionLevel or juridictionId  
      juridictionLevel      O       String  What are the Allowed range of values? example is STATE but no range is defined; is it an enum?
      juridictionId         O       String  What are the Allowed range of values? example is PA but orders are rejected if this is not a number. Apparently there are no data integrity validation constraints but does it fail later processing   
    Imposition              R       String  What are the Allowed range of values? enum?
      impositionType        O       String  What are the Allowed range of values? enum?
    EffectiveRate           R       decimal is there an allowed range?  I guess the rate could be more than 1.00 (100%)
    TaxableAmount           O       
        TaxableAmountremainder O 
    CalculatedTax           R
        CalculatedTaxremiander O
    SellerRegistrationId    O       String
    


    */
    
    
}
