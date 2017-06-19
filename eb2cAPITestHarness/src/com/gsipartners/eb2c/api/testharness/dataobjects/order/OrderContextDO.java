package com.gsipartners.eb2c.api.testharness.dataobjects.order;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.gsipartners.eb2c.api.testharness.exceptions.Eb2cAPITestHarnessException;
import com.gsipartners.eb2c.api.testharness.utilities.Eb2cAPITestHarnessUtilities;
import com.gsipartners.eb2c.api.testharness.utilities.XMLHelper;

/**
 * Data Object and output methods for the Context Node of a GSI eb2c Order 
 * 
 *
 */
public class OrderContextDO {

    //setup the logger for the application error logs and run logs
    private static Logger logger = Logger.getLogger(OrderContextDO.class);

    private String browserHostName = null;
    private String browserIPAddress = null;
    private String browserSessionId = null;
    private String browserUserAgent = null;
    private String browserConnection = null;
    private String browserCookies = null;
    private String browserJavaScriptData = null;
    private String browserHTTPReferer = null;
    private String httpAcceptDataContentTypes = null;  
    private String httpAcceptDataEncoding = null;      
    private String httpAcceptDataLanguage = null;
    private String httpAcceptDataCharSet = null;
    private String TdlOrderTimestamp = null;
    private String sessionTimeSpentOnSite = null; 
    private String sessionLastLogin = null;
    private String sessionUserPassword = null;
    private int sessionAuthorizationAttempts;
    
    /**
     * Default constructor 
     */
    public OrderContextDO () {
    }
  
  
    /**
     * Populates the object with default but valid test data  
     */
    public void setTestValues() {

    this.browserHostName = "gsi-13873.gsiccorp.net";
    this.browserIPAddress = "192.168.25.10";
    this.browserSessionId = Eb2cAPITestHarnessUtilities.getCleanUUID();
    this.browserUserAgent = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)";
    this.browserConnection = "Keep-Alive";
    this.browserCookies = "AuthenticationStateToken-B52sFfGWGBQAAAE0Zt0l9wAJ=3a3bfd6794ad08afd9f363ff6c1d4298";
    this.browserJavaScriptData = "TF1;015;5;8;17562;6%2C1%2C7601%2C17514;6%2C1%2C7601%2C17514";
    this.browserHTTPReferer = "http://tmsnae.gsipartners.com/products/12345";
    this.httpAcceptDataContentTypes = "image/jpeg, application/x-ms-application, image/gif, application/xaml+xml, image/pjpeg, application/x-ms-xbap, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*";
    this.httpAcceptDataEncoding = "gzip, deflate";
    this.httpAcceptDataLanguage = "en-US";
    this.httpAcceptDataCharSet = "UTF-8";
    this.TdlOrderTimestamp = Eb2cAPITestHarnessUtilities.getFormatedNewDateTime();
    this.sessionTimeSpentOnSite = Eb2cAPITestHarnessUtilities.getFormatedSessionTime(2500);
    this.sessionLastLogin = Eb2cAPITestHarnessUtilities.getFormatedNewDateTime();
    this.sessionUserPassword = Eb2cAPITestHarnessUtilities.getCleanUUID();
    this.sessionAuthorizationAttempts = 2;

    }
    
    
    
    /**
     * Overrides the toString() method.
     * 
     * @return the string of data in the value object
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer returnValue = new StringBuffer();

        returnValue.append("\n\nOrderContextDO")
        .append("\n BrowserData-HostName: ").append(browserHostName) 
        .append("\n BrowserData-IPAddress: ").append(browserIPAddress)
        .append("\n BrowserData-UserAgent: ").append(browserUserAgent)
        .append("\n BrowserData-Connection: ").append(browserConnection)
        .append("\n BrowserData-Cookies: ").append(browserCookies)
        .append("\n BrowserData-JavaScriptdata: ").append(browserJavaScriptData)
        .append("\n BrowserData-Referrer: ").append(browserHTTPReferer)
        .append("\n BrowserData-HTTPAcceptdata-ContentTypes: ").append(httpAcceptDataContentTypes)
        .append("\n BrowserData-HTTPAcceptdata-Encoding: ").append(httpAcceptDataEncoding)
        .append("\n BrowserData-HTTPAcceptdata-Language: ").append(httpAcceptDataLanguage)
        .append("\n BrowserData-HTTPAcceptdata-CharSet: ").append(httpAcceptDataCharSet)
        .append("\n TdlOrderTimestamp: ").append(TdlOrderTimestamp)            
        .append("\n SessionData-TimeSpentOnSite: ").append(sessionTimeSpentOnSite)
        .append("\n SessionData-lastLogin: ").append(sessionLastLogin)
        .append("\n SessionData-UserPassword: ").append(sessionUserPassword)
        .append("\n SessionData-AuthorizationAttempts: ").append(sessionAuthorizationAttempts)
        .append("\n");

        return returnValue.toString();
    }
    
    public Node getContextNode() throws Eb2cAPITestHarnessException {
        String nodeName = "Context";

        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;

        try {
            docBuilder = dbfac.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.info("ParserConfigurationException Exception caught when trying to create a new DocBuilder.", e);
            logger.error("ParserConfigurationException Exception caught when trying to create a new DocBuilder.", e);
            throw new Eb2cAPITestHarnessException("ParserConfigurationException when attempting to create a DocBuilder. " + e);
        }

        Document doc = docBuilder.newDocument();
        //Creating the XML tree

        //create the root element and add it to the document
        Element rootNode = doc.createElement(nodeName);
        doc.appendChild(rootNode);
        
        //add the BrowserData node  
        Node broswerData = doc.createElement("BrowserData");
        rootNode.appendChild(broswerData);

        doc = XMLHelper.addStringNode("TdlOrderTimestamp",this.TdlOrderTimestamp,rootNode,doc);
        
        //add the BrowserData node  
        Node sessionInfo = doc.createElement("SessionInfo");
        rootNode.appendChild(sessionInfo);
        

        // add the data from this object to the specified Node IF there is data in the property 

        
        doc = XMLHelper.addStringNode("HostName",this.browserHostName,broswerData,doc);
        doc = XMLHelper.addStringNode("IPAddress",this.browserIPAddress,broswerData,doc);
        doc = XMLHelper.addStringNode("SessionId",this.browserSessionId,broswerData,doc);     
        doc = XMLHelper.addStringNode("UserAgent",this.browserUserAgent,broswerData,doc);
        doc = XMLHelper.addStringNode("Connection",this.browserConnection,broswerData,doc);
        doc = XMLHelper.addStringNode("Cookies",this.browserCookies,broswerData,doc);
        doc = XMLHelper.addStringNode("JavascriptData",this.browserJavaScriptData,broswerData,doc);
        doc = XMLHelper.addStringNode("Referrer",this.browserHTTPReferer,broswerData,doc);
        
        //add the HTTPAcceptData Node
        Node httpAcceptData = doc.createElement("HTTPAcceptData");
        broswerData.appendChild(httpAcceptData); 
        doc = XMLHelper.addStringNode("ContentTypes",this.httpAcceptDataContentTypes,httpAcceptData,doc);
        doc = XMLHelper.addStringNode("Encoding",this.httpAcceptDataEncoding,httpAcceptData,doc);
        doc = XMLHelper.addStringNode("Language",this.httpAcceptDataLanguage,httpAcceptData,doc);
        doc = XMLHelper.addStringNode("CharSet",this.httpAcceptDataCharSet,httpAcceptData,doc);
        
        doc = XMLHelper.addStringNode("TimeSpentOnSite",this.sessionTimeSpentOnSite,sessionInfo,doc);
        doc = XMLHelper.addStringNode("Lastlogin",this.sessionLastLogin,sessionInfo,doc);
        doc = XMLHelper.addStringNode("UserPassword",this.sessionUserPassword,sessionInfo,doc);
        doc = XMLHelper.addStringNode("AuthorizationAttempts",String.valueOf(this.sessionAuthorizationAttempts),sessionInfo,doc);
        
/*
        if (this.taxExemptFlag == true) {
            doc = XMLHelper.addStringNode("TaxExemptFlag",Eb2cAPIServiceCallConstants.VALUE_TRUE,rootNode,doc);
        } else {
            doc = XMLHelper.addStringNode("TaxExemptFlag",Eb2cAPIServiceCallConstants.VALUE_FALSE,rootNode,doc);
        }
*/
        return rootNode;

    }


    /**
     * 
     * Gets the data from the Object as a well formed valid XML string
     *  
     * @return A string of the XML
     * @throws Eb2cAPITestHarnessException
     */
    public String getContextNodeAsXML() throws Eb2cAPITestHarnessException {

        //Get the object data as a node
        Node customerNode = getContextNode();
        
        //Get a String of this Node 
        return XMLHelper.getNodeAsXML(customerNode);

    }


    /**
     * @return the browserHostName
     */
    public String getBrowserHostName() {
        return browserHostName;
    }


    /**
     * @param browserHostName the browserHostName to set
     */
    public void setBrowserHostName(String browserHostName) {
        this.browserHostName = browserHostName;
    }


    /**
     * @return the browserIPAddress
     */
    public String getBrowserIPAddress() {
        return browserIPAddress;
    }


    /**
     * @param browserIPAddress the browserIPAddress to set
     */
    public void setBrowserIPAddress(String browserIPAddress) {
        this.browserIPAddress = browserIPAddress;
    }


    /**
     * @return the browserSessionId
     */
    public String getBrowserSessionId() {
        return browserSessionId;
    }


    /**
     * @param browserSessionId the browserSessionId to set
     */
    public void setBrowserSessionId(String browserSessionId) {
        this.browserSessionId = browserSessionId;
    }


    /**
     * @return the browserUserAgent
     */
    public String getBrowserUserAgent() {
        return browserUserAgent;
    }


    /**
     * @param browserUserAgent the browserUserAgent to set
     */
    public void setBrowserUserAgent(String browserUserAgent) {
        this.browserUserAgent = browserUserAgent;
    }


    /**
     * @return the browserConnection
     */
    public String getBrowserConnection() {
        return browserConnection;
    }


    /**
     * @param browserConnection the browserConnection to set
     */
    public void setBrowserConnection(String browserConnection) {
        this.browserConnection = browserConnection;
    }


    /**
     * @return the browserCookies
     */
    public String getBrowserCookies() {
        return browserCookies;
    }


    /**
     * @param browserCookies the browserCookies to set
     */
    public void setBrowserCookies(String browserCookies) {
        this.browserCookies = browserCookies;
    }


    /**
     * @return the browserJavaScriptData
     */
    public String getBrowserJavaScriptData() {
        return browserJavaScriptData;
    }


    /**
     * @param browserJavaScriptData the browserJavaScriptData to set
     */
    public void setBrowserJavaScriptData(String browserJavaScriptData) {
        this.browserJavaScriptData = browserJavaScriptData;
    }


    /**
     * @return the browserHTTPReferer
     */
    public String getBrowserHTTPReferer() {
        return browserHTTPReferer;
    }


    /**
     * @param browserHTTPReferer the browserHTTPReferer to set
     */
    public void setBrowserHTTPReferer(String browserHTTPReferer) {
        this.browserHTTPReferer = browserHTTPReferer;
    }


    /**
     * @return the httpAcceptDatabrowserContentTypes
     */
    public String getHttpAcceptDatabrowserContentTypes() {
        return httpAcceptDataContentTypes;
    }


    /**
     * @param httpAcceptDatabrowserContentTypes the httpAcceptDatabrowserContentTypes to set
     */
    public void setHttpAcceptDatabrowserContentTypes(
            String httpAcceptDatabrowserContentTypes) {
        this.httpAcceptDataContentTypes = httpAcceptDatabrowserContentTypes;
    }


    /**
     * @return the httpAcceptDataEncoding
     */
    public String getHttpAcceptDataEncoding() {
        return httpAcceptDataEncoding;
    }


    /**
     * @param httpAcceptDataEncoding the httpAcceptDataEncoding to set
     */
    public void setHttpAcceptDataEncoding(String httpAcceptDataEncoding) {
        this.httpAcceptDataEncoding = httpAcceptDataEncoding;
    }


    /**
     * @return the httpAcceptDataLanguage
     */
    public String getHttpAcceptDataLanguage() {
        return httpAcceptDataLanguage;
    }


    /**
     * @param httpAcceptDataLanguage the httpAcceptDataLanguage to set
     */
    public void setHttpAcceptDataLanguage(String httpAcceptDataLanguage) {
        this.httpAcceptDataLanguage = httpAcceptDataLanguage;
    }


    /**
     * @return the httpAcceptDataCharSet
     */
    public String getHttpAcceptDataCharSet() {
        return httpAcceptDataCharSet;
    }


    /**
     * @param httpAcceptDataCharSet the httpAcceptDataCharSet to set
     */
    public void setHttpAcceptDataCharSet(String httpAcceptDataCharSet) {
        this.httpAcceptDataCharSet = httpAcceptDataCharSet;
    }


    /**
     * @return the tdlOrderTimestamp
     */
    public String getTdlOrderTimestamp() {
        return TdlOrderTimestamp;
    }


    /**
     * @param tdlOrderTimestamp the tdlOrderTimestamp to set
     */
    public void setTdlOrderTimestamp(String tdlOrderTimestamp) {
        TdlOrderTimestamp = tdlOrderTimestamp;
    }


    /**
     * @return the sessionTimeSpentOnSite
     */
    public String getSessionTimeSpentOnSite() {
        return sessionTimeSpentOnSite;
    }


    /**
     * @param sessionTimeSpentOnSite the sessionTimeSpentOnSite to set
     */
    public void setSessionTimeSpentOnSite(String sessionTimeSpentOnSite) {
        this.sessionTimeSpentOnSite = sessionTimeSpentOnSite;
    }


    /**
     * @return the sessionLastLogin
     */
    public String getSessionLastLogin() {
        return sessionLastLogin;
    }


    /**
     * @param sessionLastLogin the sessionLastLogin to set
     */
    public void setSessionLastLogin(String sessionLastLogin) {
        this.sessionLastLogin = sessionLastLogin;
    }


    /**
     * @return the sessionUserPassword
     */
    public String getSessionUserPassword() {
        return sessionUserPassword;
    }


    /**
     * @param sessionUserPassword the sessionUserPassword to set
     */
    public void setSessionUserPassword(String sessionUserPassword) {
        this.sessionUserPassword = sessionUserPassword;
    }


    /**
     * @return the sessionAuthorizationAttempts
     */
    public int getSessionAuthorizationAttempts() {
        return sessionAuthorizationAttempts;
    }


    /**
     * @param sessionAuthorizationAttempts the sessionAuthorizationAttempts to set
     */
    public void setSessionAuthorizationAttempts(int sessionAuthorizationAttempts) {
        this.sessionAuthorizationAttempts = sessionAuthorizationAttempts;
    }
    
    

    
}
