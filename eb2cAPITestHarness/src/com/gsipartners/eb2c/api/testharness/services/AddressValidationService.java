package com.gsipartners.eb2c.api.testharness.services;

import org.apache.http.impl.client.DefaultHttpClient;

import com.gsipartners.eb2c.api.testharness.Eb2cAPIServiceCallConstants.SERVICE_CALLS;
import com.gsipartners.eb2c.api.testharness.dataobjects.Eb2cAPITestHarnessContextDO;
import com.gsipartners.eb2c.api.testharness.dataobjects.Eb2cAPITestHarnessPropertiesDO;
import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;

public class AddressValidationService extends ApiService {

    /**
     * Implementation of the Address Validation Service Class 
     * 
     * What's the right mix of required constructor parameters vs stuff you can add later?
     * Are they all or just some nullable in the constructor 
     * 
     * @param pServiceCall
     * @param pServiceContext
     * @param pserviceProperties
     * @param pHttpClient
     * @throws Eb2cAPITestHarnessException
     */
    public AddressValidationService(SERVICE_CALLS pServiceCall,
            Eb2cAPITestHarnessContextDO pServiceContext,
            Eb2cAPITestHarnessPropertiesDO pserviceProperties,
            DefaultHttpClient pHttpClient) throws Eb2cAPITestHarnessException {

        super(pServiceCall, pServiceContext, pserviceProperties, pHttpClient);

    }

    @Override
    public Eb2cAPITestHarnessContextDO callService() throws Eb2cAPITestHarnessException {
        
        System.out.println(this.serviceCall);        
        // TODO Auto-generated method stub
       
        
        
        
        return null;
    }

}
