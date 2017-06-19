
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.security.auth.login.AccountException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;

public class TestADAuth {

	private static final String CONTEXT_FACTORY_CLASS = "com.sun.jndi.ldap.LdapCtxFactory";

	private String ldapServerUrls[];

	private int lastLdapUrlIndex;

	private final String domainName;
	
	
	//Property keys 
	//MarketLink DB Releated
	private static final String PROPERTY_AD_SERVER1 = "adserver1";
	private static final String PROPERTY_AD_SERVER2 = "adserver2";
	private static final String PROPERTY_AD_SERVER3 = "adserver3";
	private static final String PROPERTY_AD_SERVER4 = "adserver4";
	private static final String PROPERTY_TARGET_ENVIRONMENT = "target_environment";
	private static final String PROPERTY_DB_USER_PASSWORD_KEY = "wso2_admin_name";
	private static final String PROPERTY_DB_HOSTNAME_KEY = "wso2_admin_password";
	private static final String PROPERTY_DB_PORT_KEY = "wso2_api_endpoint";
	private static final String PROPERTY_DB_DATABASE_NAME_KEY = "wso2_service_provider_id";
	
	

	public TestADAuth(String domainName) {
		this.domainName = domainName.toUpperCase();

		try {
			//ldapServerUrls = nsLookup(domainName);
			ldapServerUrls = makeADList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		lastLdapUrlIndex = 0;
	}

	public boolean authenticate(String username, String password) throws LoginException {
		
		//validate required info and fail fast if not all are present
		//AD servers were found at class instantiation.  If non were found, bail...
		if (ldapServerUrls == null || ldapServerUrls.length == 0) {
			throw new AccountException("Unable to find ldap servers");
		}
		
		//validate method input
		if (username == null || password == null || username.trim().length() == 0 || password.trim().length() == 0) {
			throw new FailedLoginException("Username or password is empty");
		}
		

		
		int retryCount = 0;
		int currentLdapUrlIndex = lastLdapUrlIndex;
		do {
			retryCount++;
			try {
				//authenticate and get a context object for the user
				Hashtable<Object, Object> env = new Hashtable<Object, Object>();
				env.put(Context.INITIAL_CONTEXT_FACTORY, CONTEXT_FACTORY_CLASS);
				env.put(Context.PROVIDER_URL, ldapServerUrls[currentLdapUrlIndex]);
				env.put(Context.SECURITY_PRINCIPAL, username + "@" + domainName);
				env.put(Context.SECURITY_CREDENTIALS, password);
				DirContext ctx = new InitialDirContext(env);
				ctx.close();
				lastLdapUrlIndex = currentLdapUrlIndex;
				return true;
			} catch (CommunicationException exp) {
				// TODO you can replace with log4j or slf4j API
				exp.printStackTrace();
				// if the exception of type communication we can assume the AD
				// is not reachable; retry with next AD
				if (retryCount < ldapServerUrls.length) {
					currentLdapUrlIndex++;
					if (currentLdapUrlIndex == ldapServerUrls.length) {
						currentLdapUrlIndex = 0;
					}
					continue;
				}
				return false;
			} catch (Throwable throwable) {
				//probably should be Exception,RuntimeException; Throwable catches all manner of things  
				throwable.printStackTrace();
				return false;
			}
		} while (true);
	}

	
	private static String[] nsLookup(String argDomain) throws Exception {
		
		try {
			Hashtable<Object, Object> env = new Hashtable<Object, Object>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
			env.put("java.naming.provider.url", "dns:");
			DirContext ctx = new InitialDirContext(env);
			Attributes attributes = ctx.getAttributes(String.format("_ldap._tcp.%s", argDomain), new String[] { "srv" });

			// try to get the KDC servers 3 times before throwing an Exception 
			for (int i = 0; i < 3; i++) {
				Attribute a = attributes.get("srv");
				
				if (a != null) {
					List<String> domainServers = new ArrayList<String>();
					NamingEnumeration<?> enumeration = a.getAll();

					while (enumeration.hasMoreElements()) {
						String srvAttr = (String) enumeration.next();
							/* the value are in space separated 
							0) priority 
							1) weight
							2) port
							3) server
							*/
						String values[] = srvAttr.toString().split(" ");
						domainServers.add(String.format("ldap://%s:%s", values[3], values[2]));
						System.out.println(values[0] + ";:"+ values[1] + "  --- "+ values[3] + " : " + values[2]);
					}
					
					String domainServersArray[] = new String[domainServers.size()];
					domainServers.toArray(domainServersArray);
					return domainServersArray;
				}
			}
			throw new Exception("Unable to find srv attribute for the domain " + argDomain);
		} catch (NamingException exp) {
			throw new Exception("Error while performing nslookup. Root Cause: " + exp.getMessage(), exp);
		}
	}
	
	private static String[] makeADList() throws Exception {
		
		List<String> domainServers = new ArrayList<String>();
		domainServers.add(String.format("ldap://%s:%s", "dc02.domain.ad.", "389"));
		domainServers.add(String.format("ldap://%s:%s", "dc03.domain.ad.", "389"));
		domainServers.add(String.format("ldap://%s:%s", "uskop11dc01.domain.ad.", "389"));		
		domainServers.add(String.format("ldap://%s:%s", "uskop11dc02.domain.ad.", "389"));
		
		String domainServersArray[] = new String[domainServers.size()];
		domainServers.toArray(domainServersArray);
		return domainServersArray;
		
	}	
	
	
}
