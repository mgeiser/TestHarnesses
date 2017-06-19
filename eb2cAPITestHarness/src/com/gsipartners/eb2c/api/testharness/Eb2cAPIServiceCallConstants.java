/**
 * 
 */
package com.gsipartners.eb2c.api.testharness;

/**
 * @author Michael Geiser
 *
 */
public class Eb2cAPIServiceCallConstants {

    //output values for booeans in Nodes
    public final static String VALUE_FALSE = "false";
    public final static String VALUE_TRUE = "true";
	
	public static enum SERVICE_CALLS {
		SERVICE_PAYMENTS_CREDITCARD_AUTH,
		SERVICE_PAYMENTS_STOREDVALUE_REDEEM,
		SERVICE_PAYMENTS_STOREDVALUE_BALANCE,
		SERVICE_PAYMENTS_STOREDVALUE_VOID,
		SERVICE_PAYMENTS_PAYPAL_SETEXPRESS,
		SERVICE_PAYMENTS_PAYPAL_GETEXPRESS,
		SERVICE_PAYMENTS_PAYPAL_DOEXPRESS,
		SERVICE_PAYMENTS_PAYPAL_DOAUTH,
		SERVICE_PAYMENTS_PAYPAL_VOID,
		SERVICE_ORDERS_CREATE,
		SERVICE_ORDERS_CANCEL,
		SERVICE_ORDERS_GET,
		SERVICE_ORDERS_RELATEDORDERS_GET,
		SERVICE_CUSTOMERS_ORDERS_GET,
		SERVICE_INVENTORY_QUANTITY_GET,
		SERVICE_INVENTORY_DETAILS_GET,
		SERVICE_INVENTORY_ALLOCATIONS_CREATE,
		SERVICE_INVENTORY_ALLOCATIONS_DELETE,
		SERVICE_TAXES_QUOTE,
		SERVICE_ADDRESS_VALIDATE
	}
	
	
	public static enum SERVICE_CALL_FORMATS {
		XML,
		JSON
	}
	
	//use only the HTTPS protocol
	public final static String API_SERVICE_PROTOCOL = "https://";

	//Slash for URI Builder
	public final static String API_SERVICE_URI_SLASH = "/";

	
	//period separator for file type identifier
	public final static String API_FILE_TYPE_SEPARATOR = ".";
	
	//API Stores channel
	public final static String API_CHANNEL_STORES = "stores";

	//Allowed formats
	public final static String FORMAT_XML = "xml";
	public final static String FORMAT_JSON = "json";
	
	// API version URI prefix
	public final static String API_VERSION_URI_PREFIX = "v";
	
	//the apikey Request Header constant
	public final static String REQUEST_HEADER_APIKEY = "apikey";
	
	
	//payment Types
	public final static String PAYMENT_TYPE_VISA = "VC";
	public final static String PAYMENT_TYPE_MASERCARD = "MC";
	//public final static String PAYMENT_TYPE_DISCOVER  = "DC";
	public final static String PAYMENT_TYPE_STOREDVALUE_SVS = "SV";
	
	
	public final static String SERVICE_PAYMENTS_CREDITCARD_AUTH = "payments/creditcard/auth/";
	public final static String SERVICE_PAYMENTS_STOREDVALUE_REDEEM = "payments/storedvalue/redeem/";
	public final static String SERVICE_PAYMENTS_STOREDVALUE_BALANCE = "payments/storedvalue/balance/";
	public final static String SERVICE_PAYMENTS_STOREDVALUE_VOID = "payments/storedvalue/redeemvoid/";
	public final static String SERVICE_PAYMENTS_PAYPAL_SETEXPRESS = "payments/paypal/setExpress";
	public final static String SERVICE_PAYMENTS_PAYPAL_GETEXPRESS = "payments/paypal/getExpress";
	public final static String SERVICE_PAYMENTS_PAYPAL_DOEXPRESS = "payments/paypal/doExpress";
	public final static String SERVICE_PAYMENTS_PAYPAL_DOAUTH = "payments/paypal/doAuth";
	public final static String SERVICE_PAYMENTS_PAYPAL_VOID = "payments/paypal/void";

	public final static String SERVICE_ORDERS_CREATE = "orders/create";
	public final static String SERVICE_ORDERS_CANCEL = "orders/cancel";
	public final static String SERVICE_ORDERS_GET = "orders/get";
	public final static String SERVICE_ORDERS_RELATEDORDERS_GET = "orders/relatedOrders/get";
	public final static String SERVICE_CUSTOMERS_ORDERS_GET = "customers/orders/get";

	public final static String SERVICE_INVENTORY_QUANTITY_GET = "inventory/quantity/get";
	public final static String SERVICE_INVENTORY_DETAILS_GET = "inventory/details/get";
	public final static String SERVICE_INVENTORY_ALLOCATIONS_CREATE = "inventory/allocations/create";
	public final static String SERVICE_INVENTORY_ALLOCATIONS_DELETE = "inventory/allocations/delete";

	public final static String SERVICE_TAXES_QUOTE = "taxes/quote";

	public final static String SERVICE_ADDRESS_VALIDATE = "address/validate";

	
	
}
