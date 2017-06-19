package com.gsipartners.eb2c.api.adminutilities.command;

import org.apache.log4j.Logger;

import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIAdminUtilitiesException;
import com.gsipartners.eb2c.api.adminutilities.exceptions.Eb2cAPIAdminUtilitiesGeneralException;
import com.gsipartners.eb2c.api.adminutilities.interfaces.UtilitiesCommand;
import com.gsipartners.eb2c.api.adminutilities.command.UtilityCommandConstants;

// since these are in the same package they are implicit imports...but I \
// like to see them explicitly and individually imported 
import com.gsipartners.eb2c.api.adminutilities.command.impl.ConsumerAttributes;
import com.gsipartners.eb2c.api.adminutilities.command.impl.ConsumerCreate;
import com.gsipartners.eb2c.api.adminutilities.command.impl.ConsumerDelete;
import com.gsipartners.eb2c.api.adminutilities.command.impl.ConsumerExpirationDateUpdate;
import com.gsipartners.eb2c.api.adminutilities.command.impl.ConsumerList;
import com.gsipartners.eb2c.api.adminutilities.command.impl.ConsumerRevoke;
import com.gsipartners.eb2c.api.adminutilities.command.impl.MapEntryDelete;



/** 
 * Factory returns an implementation of the PostalCodeDataParser Interface definition.
 * 
 */
public class UtilityCommandFactory {

	/**
	 * Logger.
	 */
	private static Logger logger = Logger.getLogger(UtilityCommandFactory.class);
	
	

	public static UtilitiesCommand getUtilityCommandImpl(UtilityCommandConstants.UTILITY_COMMAND pCommand) throws Eb2cAPIAdminUtilitiesException {
		// I could use a case/switch here with the enum...

		if (pCommand == UtilityCommandConstants.UTILITY_COMMAND.CREATE_CONSUMER_ID) {

			logger.debug("UtilityCommandFactory returning and instance of the CreateConsumerID impl class");

			return new ConsumerCreate();

		} else if (pCommand == UtilityCommandConstants.UTILITY_COMMAND.DELETE_CONSUMER_ID) {

			logger.debug("UtilityCommandFactory returning and instance of the DeleteConsumerID impl class");

			return new ConsumerDelete();

		} else if (pCommand == UtilityCommandConstants.UTILITY_COMMAND.MAP_ENTRY_DELETE) {

			logger.debug("UtilityCommandFactory returning and instance of the MapEntryDelete impl class");

			return new MapEntryDelete();

		} else if (pCommand == UtilityCommandConstants.UTILITY_COMMAND.LIST_CONSUMER_IDS) {

			logger.debug("UtilityCommandFactory returning and instance of the List Comsumer Ids impl class");

			return new ConsumerList();
			
		} else if (pCommand == UtilityCommandConstants.UTILITY_COMMAND.REVOKE_CONSUMER_BY_ID) {

			logger.debug("UtilityCommandFactory returning and instance of the ConsumerRevoke impl class");

			return new ConsumerRevoke();
		
		} else if (pCommand == UtilityCommandConstants.UTILITY_COMMAND.GET_CONSUMER_ATTRIBUTES_BY_ID) {

			logger.debug("UtilityCommandFactory returning and instance of the ConsumerAttributes impl class");

			return new ConsumerAttributes();
		
		} else if (pCommand == UtilityCommandConstants.UTILITY_COMMAND.UPDATE_EXPIRATION_DATE_BY_CONSUMER_ID) {

			logger.debug("UtilityCommandFactory returning and instance of the ConsumerExpirationDateUpdate impl class");

			return new ConsumerExpirationDateUpdate();
			
		} else {

			logger.debug("Implementation of requested UtilitiesCommand class not found: requested implementation = " + pCommand.toString());
			logger.error("Implementation of requested UtilitiesCommand class not found: requested implementation = " + pCommand.toString());

			throw new Eb2cAPIAdminUtilitiesGeneralException("Implementation of requested UtilitiesCommand class not found: requested implementation = " + pCommand.toString()); 
		}

	}




}
