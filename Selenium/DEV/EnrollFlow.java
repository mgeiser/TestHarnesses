package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class EnrollFlow {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.mml-dev.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testEnrollFlow() throws Exception {
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("enroll-button-0")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-back")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.id("benefits-navigation-next")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForCondition | selenium.browserbot.getUserWindow().angular.element('body').injector().get('$http').pendingRequests.length == 0 | 30000]]
    driver.findElement(By.linkText("Log Out")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
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
