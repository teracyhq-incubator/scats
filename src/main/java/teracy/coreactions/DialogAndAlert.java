package teracy.coreactions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class DialogAndAlert extends ScenarioSteps {
	PageCore pc;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * switch to alert
	 */
	@Step
	public Alert getAlert(){
		return getDriver().switchTo().alert();
	}
	/**
	 * get message from alert popup
	 * @param element
	 * @return
	 */
	@Step
	public String getAlertMessage(){
		waitForAlert();
		String retMsg=getAlert().getText();
		return retMsg;
	}
	
	/**
	 * get message from alert popup, then accept alert
	 * @param element
	 * @return
	 */
	@Step
	public String getAlertMessageThenAccept(){
		waitForAlert();
		String retMsg=getAlert().getText();
		getAlert().accept();
		return retMsg;
	}

	/**
	 * get message from alert popup, then dismiss alert
	 * @param element
	 * @return
	 */
	@Step
	public String getAlertMessageThenDismiss(){
		waitForAlert();
		String retMsg=getAlert().getText();
		getAlert().dismiss();
		return retMsg;
	}
	
	/**
	 * Accept Alert
	 * @return
	 */
	@Step
	public String verifyAndAcceptAlertWithoutClickElement() {
		waitForAlert();
		String retMsg=getAlert().getText();
		getAlert().accept();
		return retMsg;
	}
	
	/**
	 * Accept Alert
	 * @return
	 */
	@Step
	public String verifyAndDismissAlertWithoutClickElement() {
		waitForAlert();
		String retMsg=getAlert().getText();
		getAlert().dismiss();
		return retMsg;
	}
	
	/**
	 * Accept Alert
	 * @param element
	 */
	@Step
	public void acceptAlert() {
        getAlert().accept();
	}
	
	/**
	 * Dismiss Alert
	 * @param element
	 */
	@Step
	public void dismissAlert() {
		waitForAlert();
		getAlert().dismiss();
	}

	/**
	 * Enter text to alert
	 * @param element
	 * @param text
	 */
	@Step
	public void enterTextToAlert(String text) {
		waitForAlert();
		getAlert().sendKeys(text);
		getAlert().accept();
	}

	/**
	 * Wait for alert
	 */
	@Step
	private void waitForAlert(){
		new WebDriverWait(getDriver(), PageCore.timeout)
        .ignoring(NoAlertPresentException.class)
        .until(ExpectedConditions.alertIsPresent());
	}
	
	/**
	 * verify alert present or not
	 * @return
	 */
	@Step
	public boolean isAlertPresent(){
	    boolean foundAlert = false;
	    WebDriverWait wait = new WebDriverWait(getDriver(),PageCore.timeout);
	    try {
	        wait.until(ExpectedConditions.alertIsPresent());
	        foundAlert = true;
	    } catch (TimeoutException eTO) {
	        foundAlert = false;
	    }
	    return foundAlert;
	}

}
