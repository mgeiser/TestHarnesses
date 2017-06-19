//import java.util.regex.Pattern;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.opencsv.CSVReader;


/**
 * @author Michael Geiser
 *
 */
public class Login extends TestCase{

	
	//various 
	private static final String ELEMENT_TYPE_ID = "ID";
	private static final String ELEMENT_TYPE_LINK = "LINK";
	
	private static final String ID_lOGINNAME = "loginname";
	private static final String ID_PASSWORD = "password";
	private static final String ID_SUBMIT_BUTTON = "submitButton";
	private static final String ID_ENROLL_BUTTON_0 = "enroll-button-0";
	private static final String ID_BENEFITS_NAVIGATION_NEXT = "benefits-navigation-next";
	private static final String ID_LOG_OUT = "Log Out";

	private static final String INITIAL_URL = "INITIALURL";
	private static final String LOGIN_CREDENTIALS = "LOGINCREDENTIALS";
	
	//need this to determine which WebDriver to user
	private static String osName = System.getProperty("os.name").toLowerCase();
	
	
		
	//the Driver is the main work context of the application, FireFox in this case
	private WebDriver driver;
	private FirefoxBinary firefoxBinary;
	//private HtmlUnitDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	/**
	 * setup the logger for the application error log.
	 */
	private static Logger logger = Logger.getLogger(Login.class);

	
	/**
	 * setup the logger for the application run log.
	 */
	private static Logger progressLogger = Logger.getLogger("RunLog");

	
	@Before
	public void setUp() throws Exception {

		
		if (isWindows(osName)) {
			//we could have a factory to give different drivers 
			driver = new FirefoxDriver();
		} else {

			String Xport = System.getProperty("lmportal.xvfb.id", ":1");		
			firefoxBinary = new FirefoxBinary(); 
			firefoxBinary.setEnvironmentProperty("DISPLAY", Xport);
			driver = new FirefoxDriver(firefoxBinary, null);
		}

		//WebDriver driver2 = new InternetExplorerDriver();
		//WebDriver driver3 = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	
	@Test
	public void testLogin() throws Exception {

		//CSV Reader object
		CSVReader csvFileReader = null;
		String dataFileName = System.getProperty("TestconfigFile");
		
		
	    String [] OETestDataLine;  
		
		//get data
		try {
			csvFileReader = getDataFileReader(dataFileName);
			progressLogger.info("The Test Configuration file " + dataFileName + " was successfully opened");
		} catch (IOException iox) {
			logger.error("There was an issue opening the Data file " + dataFileName +" -- Message: "+ iox.getMessage());
			iox.printStackTrace();
	        //exit as gracefully as we can under the circumstances
	        System.exit( -1 );
		}
		

		//iterate over the list of command and execute them with the goal of an end-to-end enrollment
		try {
			while ((OETestDataLine = csvFileReader.readNext()) != null) {

				String lineType = OETestDataLine[0].trim();
				
				//if the first character of the line is a #, it is a comment and skip the line
				//blank lines are not allowed
				if (lineType.indexOf("#")==0) {
					continue;
				}

				if (lineType.equalsIgnoreCase(INITIAL_URL) ) {
					
					baseUrl = OETestDataLine[1].trim();
					driver.get(baseUrl);
					progressLogger.info("the baseUrl is " + baseUrl + " and is set.");
					continue;
				}

				if (lineType.equalsIgnoreCase(LOGIN_CREDENTIALS) ) {

					String employeeUsername = OETestDataLine[1].trim();
					String employeePassword = OETestDataLine[2].trim();
					progressLogger.info("Employee UserName and Password set; running siteLogin()");
					
					siteLogin(employeeUsername,employeePassword);
					continue;
				}


				if (lineType.equalsIgnoreCase(ELEMENT_TYPE_ID) || lineType.equalsIgnoreCase(ELEMENT_TYPE_LINK) ) {

					String elementIdentifier = OETestDataLine[1].trim();
					String pageLabel = OETestDataLine[2].trim();
					
					nextStep(lineType, elementIdentifier, pageLabel);
					
					progressLogger.info("Employee UserName and Password set; running siteLogin()");
					continue;
				}

			}
		} catch (IOException iox) {
			logger.error("There was an issue reader or parsing the data file " + dataFileName +" -- Message: "+ iox.getMessage());
			iox.printStackTrace();
			//exit as gracefully as we can under the circumstances
			System.exit( -1 );

		}
  
  }

	
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  
  
	private static boolean isWindows(String OS) {
		return (OS.indexOf("win") >= 0);
	}
	
	private static boolean isLinux(String OS) {
		return (OS.indexOf("nix") >= 0);
	}
  

  /**
   * Get the Properties file and return as an instance of java.util.Properties;
   * 
   * @param String dataFile
   * @return com.opencsv.CSVReader
   * @throws IOException 
   */
  private static CSVReader getDataFileReader(String dataFile) throws IOException {
  	
  	//instantiate openCSV reader 
  	return new CSVReader(new FileReader(dataFile));

    }
  	
  
  
  /**
 * Executes a login
 * 
 * @param pEmployeeUserName
 * @param pEmployeePassword
 *  
 */
private void siteLogin(String pEmployeeUserName, String pEmployeePassword) {
	    
	  long pageWaitStart = 0;
	  long pageWaitEnd = 0;
	  long pageWaitElapsedMills = 0;
	  
	  //building this out
	 // assertTrue(driver.getTitle().equals("My MarketLink Login"));
	  
	  pageWaitStart = System.currentTimeMillis();
	  //login
	  driver.findElement(By.id(ID_lOGINNAME)).clear();
	  driver.findElement(By.id(ID_lOGINNAME)).sendKeys(pEmployeeUserName);
	  driver.findElement(By.id(ID_PASSWORD)).clear();
	  driver.findElement(By.id(ID_PASSWORD)).sendKeys(pEmployeePassword);
	  driver.findElement(By.id(ID_SUBMIT_BUTTON)).click();
	  pageWaitEnd = System.currentTimeMillis();
	  pageWaitElapsedMills = pageWaitEnd - pageWaitStart;

	  
	  //need to log this too
	  System.out.println("Site Login : " + pageWaitElapsedMills);
	  
  }
  
  
  
  /**
 * Handles the clicksteam config from the file by finding the element to click and processing
 * 
 * @param elementType
 * @param elementIdentifier
 * @param pageLabel
 */
public void nextStep (String elementType, String elementIdentifier, String pageLabel) {

	  long preWaitStart = 0;
	  long preWaitend = 0;
	  long preElapsedMills = 0;
	  
	  long pageWaitStart = 0;
	  long pageWaitEnd = 0;
	  long pageWaitElapsedMills = 0;
	  
	  long postWaitStart = 0;
	  long postWaitend = 0;
	  long postElapsedMills = 0;
	  
	  
	  
	  //in case this gets called before 
	  //usually execution will not pause here
	  preWaitStart = System.currentTimeMillis();
	  waitForPageLoaded(driver);
	  preWaitend = System.currentTimeMillis();
	  preElapsedMills = preWaitend - preWaitStart;


	  
	  pageWaitStart = System.currentTimeMillis();
	  if ( elementType.equals(ELEMENT_TYPE_ID) ) {

		  driver.findElement(By.id(elementIdentifier)).click();

	  } else if ( elementType.equals(ELEMENT_TYPE_LINK) ) {
		  
		  driver.findElement(By.linkText(elementIdentifier)).click();
		  
	  } else {

		  //something bad happened...
		  //log it and System.exit(-1) 
		  
	  }
	  pageWaitEnd = System.currentTimeMillis();
	  pageWaitElapsedMills = pageWaitEnd - pageWaitStart;
	  
	  //if this is the last page with the logout link, This isn't something we should do and therefore we should eat the Exception 
	  if ( !elementIdentifier.equals("Log Out")) {
		  postWaitStart = System.currentTimeMillis();
		  waitForPageLoaded(driver);
		  postWaitend = System.currentTimeMillis();
		  postElapsedMills = postWaitend - postWaitStart;
	  } else {
		  postElapsedMills = 0;
	  }
	  
	  //need to log this too
	  System.out.println(pageLabel + " : " + preElapsedMills + " : " + pageWaitElapsedMills + " : " + postElapsedMills);
	  

	  
	  
  }
  
  
  
  /**
 * Watches the queue of AJAX calls
 * 
 * It would be better to have a flag that isLoaded that is set true when all calls are done; this could fire at the wrong times but is good enough for here 
 * 
 * @param driver
 */
public void waitForPageLoaded(WebDriver driver)
  {
      ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
          public Boolean apply(WebDriver driver) {
              return (Boolean)((JavascriptExecutor)driver).executeScript("return angular.element('body').injector().get('$http').pendingRequests.length == 0");
          }
      };
      
      WebDriverWait wait = new WebDriverWait(driver,30);
      
      try {
          wait.until(expectation);
      } catch(Throwable error) {
          error.printStackTrace();
    	  assertFalse("Timeout waiting for Page Load Request to complete (Or someother issue, but mostly likely a timeout.",true);
      }
  }
  
  
  
  /**
 * Need to delete this...this was a test to figure out what to look at to create the ExpectedCondition in waitForPageLoaded()
 * 
 * 
 */
private void chill() {
	  JavascriptExecutor js = (JavascriptExecutor) driver;
      
	  Boolean test = false;
	  while (!test) {      
		      test = (Boolean) js.executeScript("return angular.element('body').injector().get('$http').pendingRequests.length == 0");
		      //System.out.println("test: " + test);
	  }
	  
  }
  
  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
