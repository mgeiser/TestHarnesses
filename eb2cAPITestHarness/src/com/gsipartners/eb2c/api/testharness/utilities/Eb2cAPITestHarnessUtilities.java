package com.gsipartners.eb2c.api.testharness.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Eb2cAPITestHarnessUtilities {




    
    /**
     * Get a UUID
     * 
     * @return String of the UUID
     */
    public static String getUUID() {

        final String uuid = UUID.randomUUID().toString();
        
        return uuid;
        
    }
    
    /**
     * Get a UUID without any dashes
     * 
     * @return String of the UUID without dashes  
     */
    public static String getCleanUUID() {
        /*
        String id = getUUID();
        id.replaceAll("-", "");
        */
        final String uuid = UUID.randomUUID().toString().replaceAll("-", ""); 

        return uuid;
    }
    

    public static String getFormatedSessionTime(int sessionTimeInSeconds) {
        int hours, minutes, seconds;
        hours = sessionTimeInSeconds / 3600;
        sessionTimeInSeconds = sessionTimeInSeconds - (hours * 3600);
        minutes = sessionTimeInSeconds / 60;
        sessionTimeInSeconds = sessionTimeInSeconds - (minutes * 60);
        seconds = sessionTimeInSeconds;
        
        return hours + ":" + minutes + ":" + seconds;
    }

    //yyyy-MM-dd'T'HH:mm:ssz
    

    public static String getFormatedNewDateTime(){
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date()).toString();
    }

    public static String getFormatedDateTime(Date pDate){
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(pDate).toString();
    }

    public static String getFormatedDate(Date pDate){
        return new SimpleDateFormat("yyyy-MM-dd").format(pDate).toString();
    }
    
    
    
}



/*
 * 
 * http://www.mkyong.com/java/how-to-modify-date-time-date-manipulation-java/ 
 * 
 * 
*/