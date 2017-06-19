package com.gsipartners.eb2c.api.testharness.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gsipartners.eb2c.api.testharness.utilities.Eb2cAPITestHarnessUtilities;


public class TestEb2CTestarnessUtilities {

    
    
    public static void main(String[] args) {
        
        System.out.println("Demonstrations of the Eb2cAPITestHarnessUtilities methods"); 
        System.out.println("----------------------------------------");
        System.out.print("get a UUID: ");
        System.out.println(Eb2cAPITestHarnessUtilities.getUUID());
        
        System.out.println("\n----------------------------------------");
        System.out.print("get a UUID without the dashes (a 'clean' UUID): ");
        System.out.println(Eb2cAPITestHarnessUtilities.getCleanUUID());
        
        
        System.out.println("\n----------------------------------------");
        int numberOfSeconds = 2500; 
        System.out.print("Format a number of seconds to for use in the Session Time on the Context: " + numberOfSeconds + " seconds is ");
        System.out.println(Eb2cAPITestHarnessUtilities.getFormatedSessionTime(numberOfSeconds));
      
        System.out.println("\n----------------------------------------");
        System.out.print("Formated date for tests: " );
        System.out.println(Eb2cAPITestHarnessUtilities.getFormatedNewDateTime());
  

    }
        

    
    //getdate
    //getYearMonth
    
    
    
}
