import java.util.Date;

import com.gsipartners.eb2c.api.adminutilities.dataobjects.Eb2cAPIAdminUtilitiesPropertiesDO;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIIllegalArgumentsException;
import com.gsipartners.eb2c.api.adminutilities.utilities.ParameterValidations;


public class TestParameterValidations {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Eb2cAPIAdminUtilitiesPropertiesDO pPropertiesDO = new Eb2cAPIAdminUtilitiesPropertiesDO();
		
		//Test hasKeyPassedExpirationDate()
		System.out.println("Testing hasKeyPassedExpirationDate()");
		try {
			pPropertiesDO.setKeyExpirationDate("2012-10-15");
			
			System.out.println("Not expired (false expected): " + ParameterValidations.hasKeyPassedExpirationDate((pPropertiesDO), new Date()));
			
			pPropertiesDO.setKeyExpirationDate("2012-01-02");
			System.out.println("Expired (true expected): " + ParameterValidations.hasKeyPassedExpirationDate((pPropertiesDO), new Date()));

			pPropertiesDO.setKeyExpirationDate("2012-01-32");
			ParameterValidations.hasKeyPassedExpirationDate((pPropertiesDO), new Date());
			
		} catch (Eb2cAPIIllegalArgumentsException e) {
			e.printStackTrace();

		}
		
		
		
		
		
		//Test KeyExpirationDate
		System.out.println("\n\nTesting date");
		try {
			pPropertiesDO.setKeyExpirationDate("2012-10-15");
			ParameterValidations.validateExpirationDate(pPropertiesDO);
			pPropertiesDO.setKeyExpirationDate("2012-13-42");
			ParameterValidations.validateExpirationDate(pPropertiesDO);

		} catch (Eb2cAPIIllegalArgumentsException e) {
			e.printStackTrace();

		}
		
		
		
		
		//Test Protocol
		try {
			pPropertiesDO.setProtocol("http");
			ParameterValidations.validateProtocol(pPropertiesDO);
			pPropertiesDO.setProtocol("https");
			ParameterValidations.validateProtocol(pPropertiesDO);
			pPropertiesDO.setProtocol("httpx");
			ParameterValidations.validateProtocol(pPropertiesDO);
		} catch (Eb2cAPIIllegalArgumentsException e) {
			e.printStackTrace();

		}


		//TargetKeyManagerFQDN
		try {
			pPropertiesDO.setTargetKeyManagerFQDN("sectstapg03.us.gspt.net");
			ParameterValidations.validateTargetKeyManagerFQDN(pPropertiesDO);
			pPropertiesDO.setTargetKeyManagerFQDN("10.25.85.112");
			ParameterValidations.validateTargetKeyManagerFQDN(pPropertiesDO);
			pPropertiesDO.setTargetKeyManagerFQDN("www.google.com");
			ParameterValidations.validateTargetKeyManagerFQDN(pPropertiesDO);
		} catch (Eb2cAPIIllegalArgumentsException e) {
			e.printStackTrace();

		}		
		

		//TargetKeyManagerFQDN
		try {
			pPropertiesDO.setTargetKeyManagerPort("80");
			ParameterValidations.validateTargetKeyManagerPort(pPropertiesDO);
			pPropertiesDO.setTargetKeyManagerPort("443");
			ParameterValidations.validateTargetKeyManagerPort(pPropertiesDO);
			pPropertiesDO.setTargetKeyManagerPort("8080");
			ParameterValidations.validateTargetKeyManagerPort(pPropertiesDO);
			pPropertiesDO.setTargetKeyManagerPort("");
			ParameterValidations.validateTargetKeyManagerPort(pPropertiesDO);

		} catch (Eb2cAPIIllegalArgumentsException e) {
			e.printStackTrace();

		}		

		//TargetKeyManagerFQDN
		try {
			pPropertiesDO.setTargetKeyManagerPort("-80");
			ParameterValidations.validateTargetKeyManagerPort(pPropertiesDO);
		} catch (Eb2cAPIIllegalArgumentsException e) {
			e.printStackTrace();

		}		

		//Strings
		try {
			ParameterValidations.validateIsNotZeroLengthString("test", "test");
			ParameterValidations.validateIsNotZeroLengthString("Mars", "");
		} catch (Eb2cAPIIllegalArgumentsException e) {
			e.printStackTrace();

		}		

		
		//Strings
		try {
			ParameterValidations.validateEqualsCaseSensitive("Test", "TeST", "test");
			ParameterValidations.validateEqualsCaseSensitive("Test", "TeST", "testq");
		} catch (Eb2cAPIIllegalArgumentsException e) {
			e.printStackTrace();

		}	
		

		try {
			ParameterValidations.validateEqualsIgnoreCase("Test", "Test", "test");
			ParameterValidations.validateEqualsIgnoreCase("Test", "TeST", "test");
		} catch (Eb2cAPIIllegalArgumentsException e) {
			e.printStackTrace();

		}	

		
	
	} //main
} //class
