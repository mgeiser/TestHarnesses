package com.gsipartners.eb2c.api.adminutilities.interfaces;

import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIAdminUtilitiesException;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIIllegalArgumentsException;
import com.gsipartners.eb2c.api.adminutilities.dataobjects.Eb2cAPIAdminUtilitiesPropertiesDO;

/**
 * Interface definition for implementation classes that parse data from various Postal Code data suppliers.
 *
 */
public interface UtilitiesCommand {
	

	/**
	 * Method that runs the Command implemented in the impl.
	 * 
	 * @param pPropertiesDO the setup properties of the run
	 * @throws Eb2cAPIAdminUtilitiesException general exception 
	 */
	public void runCommand(Eb2cAPIAdminUtilitiesPropertiesDO pPropertiesDO) throws Eb2cAPIAdminUtilitiesException;
	
	/**
	 * Private method to run validation of input parameters.
	 * 
	 * @param pPropertiesDO the setup properties of the run
	 * @throws Eb2cAPIIllegalArgumentsException general exception 
	 */
	public void validateProperites(Eb2cAPIAdminUtilitiesPropertiesDO pPropertiesDO) throws Eb2cAPIIllegalArgumentsException;
}
