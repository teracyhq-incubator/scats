package org.teracy.scats.core.actions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.teracy.scats.utils.DateTime;
import org.teracy.scats.utils.LocatorMap;
import org.teracy.scats.utils.RandomData;
import org.teracy.scats.utils.TestLogger;

import cucumber.api.Scenario;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;

public class PageCore extends PageObject {
	protected static int WAIT_INTERVAL = 50; // milliseconds
	public static Properties config = new Properties();
	public static Properties elements = new Properties();
	public static String browserName;
	public static String browserVersion;
	public static String platform;
	public static String baseUrl;
	public static Integer timeout;
	public static String hubUrl;
	public static Boolean failfast;
	public static String serverMail;
	public static Boolean isUsingServerMail;
	String defaultWindow = "";
	public static HashMap<String, String> listVar = new HashMap<String, String>();

	/**
	 * maximize window
	 * 
	 * @throws Throwable
	 */
	public void maximizeTheWindow() {
		getDriver().manage().window().maximize();
	}

	/**
	 * switch to iframe
	 * 
	 * @param iframeName
	 * @throws Throwable 
	 */
	public void switchToIFrame(String iframeName) throws Throwable {
		WebElement webElement = waitForGetElementPresent(iframeName);
		getDriver().switchTo().frame(webElement);
		pause(2000);
	}

	/**
	 * switch back to paren frame
	 */
	public void swichBackToParentFrame() {
		getDriver().switchTo().parentFrame();
	}
	/**
	 * open the page
	 * 
	 * @throws IOException
	 */
	public void openThePage() {
		open();
		failfast = Boolean.valueOf(getConfig("failfast"));
		elements = LocatorMap.loadingObjectPropertiesFromPackage("elements");
		config = LocatorMap.loadConfigSys();
		timeout = Integer.valueOf(getConfig("timeout"));
		serverMail = getConfig("servermail");
		baseUrl = getConfig("webdriver.base.url");
		isUsingServerMail = Boolean.valueOf(getConfig("isusingservermail"));
		if (!getConfig("webdriver.driver").contains("appium"))
			maximizeTheWindow();
	}

	/**
	 * Take screen shot
	 * 
	 * @param scenario
	 */
	public void takeScreenshot(Scenario scenario) {
		final byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
		scenario.embed(screenshot, "image/png");
	}

	/**
	 * takeSnapShot
	 */

	public void takeSnapShot() {
		DateTime dateTime = new DateTime();
		TakesScreenshot scrShot = ((TakesScreenshot) getDriver());
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(dateTime.getDateByTextFormat("ddmmyyyyhhmmss") + ".png");
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * wait for page loading
	 */
	@Step
	public void waitForPageToLoad() {
		String pageLoadStatus = null;
		int count = 1;
		do {
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			pageLoadStatus = (String) js.executeScript("return document.readyState");
			TestLogger.info(".");
			pause(1000);
			count++;
			if (count == 30) {
				getDriver().navigate().refresh();
			}
		} while (!pageLoadStatus.equals("complete"));
		TestLogger.info("Page Loaded.");
	}

	/**
	 * Gets system config else serenity.properties
	 *
	 * @param key
	 *            config key
	 * @return config value
	 */
	static public String getConfig(String key) {
		Properties config = LocatorMap.loadConfigSys();
		String retConfig = (System.getProperty(key) != null ? System.getProperty(key) : config.getProperty(key));
		TestLogger.info(key + ": " + retConfig);
		return retConfig;
	}

	/**
	 * get locator from string
	 * 
	 * @param locator
	 * @return
	 */
	public By getObject(String locator) {
		By by = null;
		try {
			if (locator.startsWith("id=")) {
				locator = locator.substring(3);
				by = By.id(locator);
			} else if (locator.startsWith("name=")) {
				locator = locator.substring(5);
				by = By.name(locator);
			} else if (locator.startsWith("css=")) {
				locator = locator.substring(4);
				by = By.cssSelector(locator);
			} else if (locator.startsWith("linkText=")) {
				locator = locator.substring(9);
				by = By.linkText(locator);
			} else if (locator.startsWith("xpath=")) {
				locator = locator.substring(6);
				by = By.xpath(locator);
			} else if (locator.startsWith("cssSelector=")) {
				locator = locator.substring(12);
				by = By.xpath(locator);
			} else if (locator.startsWith("partialLinkText=")) {
				locator = locator.substring(16);
				by = By.xpath(locator);
			} else if (locator.startsWith("tagName=")) {
				locator = locator.substring(8);
				by = By.xpath(locator);
			} else {
				by = By.xpath(locator);
			}

			return by;
		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}

	/**
	 * wait
	 * 
	 * @param millis
	 */
	public void pause(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// eat the exception
		}
	}

	/**
	 * wait for element not present
	 * 
	 * @param target
	 * @param param
	 */
	public void waitForElementNotPresent(String target, String... param) {
		String opParam = param.length > 0 ? param[0] : "";
		String locator = LocatorMap.getActualValueFromElementList(target.replace(" ", "").toLowerCase()).toString()
				.replace("$param", opParam);
		Boolean isPresent = true;
		for (int tick = 0; tick < timeout; tick++) {
			List<WebElement> elems = getDriver().findElements(getObject(locator));
			if (elems.isEmpty()) {
				isPresent = false;
				break;
			}
			pause(WAIT_INTERVAL);
		}
		TestLogger.info(isPresent.toString());
		Assert.assertTrue("Timeout after " + timeout + "ms waiting for element not present: " + getObject(locator),
				(isPresent == false));
	}

	/**
	 * get element
	 * 
	 * @param target
	 * @param param
	 * @return null if element is not present
	 */
	public WebElement waitForGetElementPresent(String target, String... param) {
		String locator = null;
		locator = LocatorMap.getActualValueFromElementList(target.replace(" ", "").toLowerCase()).toString();
		if (param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				TestLogger.info(target + ": " + locator);
				locator = locator.replace("$param" + String.valueOf(i + 1), param[i]);
			}
		}
		TestLogger.info(target + ": " + locator);
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
			wait.until(ExpectedConditions.presenceOfElementLocated(getObject(locator)));
			return getDriver().findElement(getObject(locator));
		} catch (Exception e) {
			e.getMessage();
		}
		TestLogger.warn("Can not find element " + getObject(locator));
		return null;
	}
	
	/**
	 * get element
	 * 
	 * @param target
	 * @param param
	 * @return null if element is not present
	 */
	public Integer waitForGetElementsPresent(String target, String... param) {
		String locator = null;
		locator = LocatorMap.getActualValueFromElementList(target.replace(" ", "").toLowerCase()).toString();
		if (param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				TestLogger.info(target + ": " + locator);
				locator = locator.replace("$param" + String.valueOf(i + 1), param[i]);
			}
		}
		TestLogger.info(target + ": " + locator);
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
			wait.until(ExpectedConditions.presenceOfElementLocated(getObject(locator)));
			return getDriver().findElements(getObject(locator)).size();
		} catch (Exception e) {
			e.getMessage();
		}
		TestLogger.warn("Can not find element " + getObject(locator));
		return 0;
	}

	/**
	 * get visiblity of element
	 * 
	 * @param target
	 * @param param
	 * @return null if element is not visible
	 */
	public WebElement waitForGetElementVisible(String target, String... param) {
		String opParam = param.length > 0 ? param[0] : "";
		String locator = LocatorMap.getActualValueFromElementList(target.replace(" ", "").toLowerCase()).toString()
				.replace("$param", opParam);
		TestLogger.info(target + ": " + locator);
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(getObject(locator)));
			return getDriver().findElement(getObject(locator));
		} catch (Exception e) {
			e.getMessage();
		}
		TestLogger.warn("Can not find element " + getObject(locator));
		return null;
	}

	/**
	 * get clickable element
	 * 
	 * @param target
	 * @param param
	 * @return null if element is not clickable
	 */
	public WebElement waitForClickableElement(String target, String... param) {
		String opParam = param.length > 0 ? param[0] : "";
		String locator = LocatorMap.getActualValueFromElementList(target.replace(" ", "").toLowerCase()).toString()
				.replace("$param", opParam);
		TestLogger.info(target + ": " + locator);
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
			wait.until(ExpectedConditions.elementToBeClickable(getObject(locator)));
			return getDriver().findElement(getObject(locator));
		} catch (Exception e) {
			e.getMessage();
		}
		TestLogger.warn("Can not find element " + getObject(locator));
		return null;
	}

	/**
	 * verify if inputData is number or not
	 * 
	 * @param inputData
	 * @return
	 */
	public boolean isNumeric(String inputData) {
		return inputData.matches("[-+]?\\d+(\\.\\d+)?");
	}

	/**
	 * runJquery
	 * 
	 * @param jquery
	 * @return
	 */
	public String runJquery(String jquery) {
		JavascriptExecutor jse = (JavascriptExecutor) getDriver();
		TestLogger.info(jquery);
		return jse.executeScript(jquery).toString();
	}

	/**
	 * Get actual string after processing randomdata
	 * 
	 * @param text
	 * @return
	 */
	public String getActualString(String text) {
		text = RandomData.getRandomString(text);
		return text;
	}

	/**
	 * verify element is enable to click or not
	 * 
	 * @param target
	 * @return
	 */
	public boolean isEnabled(String target, String...param) {
		return waitForGetElementPresent(target,param).isEnabled();
	}

	/**
	 * verify checkbox element is checked or not
	 * 
	 * @param target
	 * @return
	 */
	public boolean isSelected(String target,String...param) {
		return waitForGetElementPresent(target,param).isSelected();
	}

	/**
	 * verify element is displayed or not
	 * 
	 * @param target
	 * @return
	 */
	public boolean isDisplay(String target,String...param) {
		return waitForGetElementPresent(target,param).isDisplayed();
	}

	/**
	 * verify element is present or not
	 * 
	 * @param target
	 * @return
	 */
	public boolean isPresent(String target, String... param) {
		return (waitForGetElementPresent(target, param) != null);
	}

	/**
	 * verify element is present or not
	 * 
	 * @param target
	 * @return
	 */
	public boolean isVisible(String target, String... param) {
		return (waitForGetElementVisible(target, param) != null);
	}

	/**
	 * verify element is present or not
	 * 
	 * @param target
	 * @return
	 */
	public boolean isEnable(String target, String... param) {
		return waitForGetElementPresent(target, param).isEnabled();
	}

	/**
	 * get text on page
	 * 
	 * @param text
	 * @return null if text is not vivible
	 */
	public Boolean getTextOnPage(String text) {
		TestLogger.info(text + ": " + LocatorMap.getActualValueFromElementList(text).toString());
		TestLogger.info("//*[contains(text(), '" + LocatorMap.getActualValueFromElementList(text).toString() + "')]");
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), PageCore.timeout);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"//*[contains(text(), \"" + LocatorMap.getActualValueFromElementList(text).toString() + "\")]")));
			return true;
		} catch (Exception e) {
			e.getMessage();
		}
		TestLogger.warn("Can not find text " + LocatorMap.getActualValueFromElementList(text).toString());
		return false;
	}
	
	/**
	 * get current url
	 * @return
	 */
	public String getCurrentUrl(){
		TestLogger.info(getDriver().getCurrentUrl());
		return getDriver().getCurrentUrl();
	}
}
