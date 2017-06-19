import com.gsipartners.eb2c.api.adminutilities.command.UtilityCommandConstants;
import com.gsipartners.eb2c.api.adminutilities.command.UtilityCommandConstants.UTILITY_COMMAND;

import java.lang.IllegalArgumentException;


public class QuickTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		UtilityCommandConstants.UTILITY_COMMAND test = null;



		//catch a potential IllegalArgumentException here and dump the allowed UTILITY COMMANDS	
		try {
			test = UTILITY_COMMAND.valueOf("CREATE_CONSUMER_ ID");
			System.out.println("After: " + test.toString());
		} catch (IllegalArgumentException IAE) {
			System.out.println("Swing and a miss...");


			for (UTILITY_COMMAND  validCommands : UTILITY_COMMAND.values()) {
				System.out.println(validCommands.toString() + ": " + validCommands.name().toString());        
			}

		}


		
		
		
		
		
		
		
		
		
		
		
		
		
/*		
		// TODO Auto-generated method stub

		String test = "<clientapp><consumer><key>47c9355d152a76829bd66cc5b2541263</key><secret>72cb06d3b7685447</secret><loginid>QUIKUS</loginid><clientapplicationname>eb2c</clientapplicationname><apigroupname>Production</apigroupname><serviceprofile>Limited</serviceprofile><status>true</status><created_at>Tue Feb 21 16:28:33 +0000 2012</created_at></consumer></clientapp>";

		String keyValue = null;
		
	       //load the XML
	       XPathHelper xpathHelper = new XPathHelper(test);

	       if (xpathHelper.doesNodeExist("//clientapp/consumer/key")) {
	    	   keyValue = xpathHelper.read("//clientapp/consumer/key");
	       }

		
		
		System.out.println("Key: " + keyValue);
	
		*/
		
	}

}
