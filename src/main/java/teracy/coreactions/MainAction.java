package teracy.coreactions;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import teracy.utils.TestLogger;

public class MainAction extends ScenarioSteps {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PageCore pc;

	/**
	 * close browserName
	 */
	@Step
	public void closeBrowser() {
		getDriver().quit();
	}

	/**
	 * forward page
	 * 
	 * @throws Throwable
	 */
	@Step
	public void moveForwardPage() throws Throwable {
		getDriver().navigate().forward();
	}

	/**
	 * backward page
	 * 
	 * @throws Throwable
	 */
	@Step
	public void moveBackwardPage() throws Throwable {
		getDriver().navigate().back();
	}

	/**
	 * redirect to url
	 * 
	 * @param url
	 */
	@Step
	public void redirectToUrl(String url) {
		TestLogger.info(pc.getActualString(url));
		getDriver().navigate().to(pc.getActualString(url));
	}

	/**
	 * switch to iframe
	 * 
	 * @param iframeName
	 */
	@Step
	public void switchToIFrame(String iframeName) {
		WebElement webElement = pc.waitForGetElementPresent(iframeName);
		getDriver().switchTo().frame(webElement);
		pc.pause(2000);
	}

	/**
	 * switch back to paren frame
	 */
	@Step
	public void swichBackToParentFrame() {
		getDriver().switchTo().parentFrame();
	}

	/**
	 * switch back to main frame
	 */
	@Step
	public void swichBackToMainFrame() {
		getDriver().switchTo().defaultContent();
	}
	
	/**
	 * upload file using javascript
	 * @param element
	 * @param fileLocation
	 * @throws InterruptedException
	 */
	@Step
	public void uploadFileUsingJavascript(String target, String fileLocation) throws InterruptedException{
		TestLogger.info("Attach a file");
		String fs = File.separator;
		WebElement elem= pc.waitForGetElementPresent(target);
		fileLocation=System.getProperty("user.dir")+fileLocation.replace("/", fs).replace("\\", fs);
		TestLogger.info(fileLocation);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.display = 'block';", elem);
		elem.sendKeys(fileLocation);
	}

	/**
	 * right click on element
	 * 
	 * @param target
	 * @param param
	 */
	@Step
	public void rightClick(String target, String... param) {
		WebElement onElement = pc.waitForGetElementPresent(target, param);
		Actions action = new Actions(getDriver());
		action.moveToElement(onElement).contextClick().perform();
	}

	/**
	 * double click on element
	 * 
	 * @param target
	 * @param param
	 */
	@Step
	public void doubleClick(String target, String... param) {
		WebElement onElement = pc.waitForGetElementPresent(target, param);
		Actions action = new Actions(getDriver());
		action.moveToElement(onElement).doubleClick().perform();
	}

	/**
	 * click by javascript
	 */
	public void clickByJavascript(WebElement onElement) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", onElement);
	}

	/**
	 * click on element
	 * 
	 * @param target
	 * @param param
	 */
	@Step
	public void click(String target, String... param) {
		WebElement onElement = pc.waitForGetElementPresent(target, param);
		if (PageCore.platform != null && PageCore.platform != "")
			if (PageCore.platform.toLowerCase().contains("linux") && onElement.isDisplayed())
				TestLogger.info("Current platform is linux");
			else
				((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", onElement);
		else
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", onElement);
		if (!onElement.isEnabled()) {
			TestLogger.info("click by javascript");
			clickByJavascript(onElement);
		} else
			onElement.click();
	}

	/**
	 * send text into textbox
	 * 
	 * @param target
	 * @param text
	 */
	@Step
	public String sendKey(String target, String text) {
		text = pc.getActualString(text);
		WebElement elem = pc.waitForGetElementPresent(target);
		elem.clear();
		elem.sendKeys(text);
		return text;
	}

	/**
	 * mouse hover on element
	 * 
	 * @param target
	 */
	@Step
	public void mouseHover(String target, String... param) {
		WebElement onElement = pc.waitForGetElementPresent(target, param);
		Actions action = new Actions(getDriver());
		action.moveToElement(onElement).build().perform();
	}

	/**
	 * scroll up
	 */
	@Step
	public void scrollUp() {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, 0)");
	}

	/**
	 * scroll down
	 */
	@Step
	public void scrollDown() {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/**
	 * select by value
	 * 
	 * @param value
	 * @param target
	 * @param param
	 */
	@Step
	public void selectByValueDropDown(String value, String target, String... param) {
		WebElement onElement = pc.waitForGetElementPresent(target, param);
		Select mySelect = new Select(onElement);
		mySelect.selectByValue(value);
	}

	/**
	 * select by text
	 * 
	 * @param value
	 * @param target
	 * @param param
	 */
	@Step
	public void selectByTextDropDown(String value, String target, String... param) {
		WebElement onElement = pc.waitForGetElementPresent(target, param);
		Select mySelect = new Select(onElement);
		mySelect.selectByVisibleText(value);
	}

	/**
	 * select by index
	 * 
	 * @param value
	 * @param target
	 * @param param
	 */
	@Step
	public void selectByIndexDropDown(int value, String target, String... param) {
		WebElement onElement = pc.waitForGetElementPresent(target, param);
		Select mySelect = new Select(onElement);
		mySelect.selectByIndex(value);
	}
	
	/**
	 * press down arrow key
	 * @param number
	 * @throws Throwable
	 */
	@Step
	public void pressDownArrowKey(int number) throws Throwable {
		for (int i = 0; i < number; i++) {
			Actions action = new Actions(getDriver());
			action.sendKeys(Keys.ARROW_DOWN).perform();
			action.release();
		}
	}

	/**
	 * press up key
	 * @param number
	 * @throws Throwable
	 */
	@Step
	public void pressUpArrowKey(int number) throws Throwable {
		for (int i = 0; i < number; i++) {
			Actions action = new Actions(getDriver());
			action.sendKeys(Keys.ARROW_UP).perform();
			action.release();
		}
	}

	/**
	 * press enter key
	 * @param number
	 * @throws Throwable
	 */
	@Step
	public void pressEnterKey(int number) throws Throwable {
		for (int i = 0; i < number; i++) {
			if (PageCore.browserName.equalsIgnoreCase("chrome")){
				Actions action=new Actions(getDriver());
				action.sendKeys(Keys.RETURN).perform();
				action.release();
			}else{
				pc.waitForGetElementPresent("body").sendKeys(Keys.ENTER);
			}
		}
	}

	/**
	 * press tab key
	 * @param number
	 * @throws Throwable
	 */
	@Step
	public void pressTabKey(int number) throws Throwable {
		for(int i = 0; i<number; i++){
			Actions action=new Actions(getDriver());
			action.sendKeys(Keys.TAB).perform();
			action.release();
		}
	}
}
