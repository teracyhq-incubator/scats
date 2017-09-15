package org.teracy.scats.core.steps;

import org.teracy.scats.core.actions.MainAction;
import org.teracy.scats.core.actions.PageCore;
import org.teracy.scats.utils.TestLogger;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cuke4duke.annotation.I18n.EN.When;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class BaseSteps extends PageCore{
	public static boolean wantsToQuit = false;
	public static boolean tempQuit = false;
	@Steps
	MainAction mainAction;
	
	@Before
	public void setUp(Scenario scenario) throws Exception{
		if(tempQuit){
			System.exit(-1);
		}
		if (wantsToQuit){
			tempQuit=true;
		}
		else{
			TestLogger.info("=====================");
			TestLogger.info("=====TEST STARTED====");
			TestLogger.info("=====================");
			openThePage();
		}
	}

	@After
	public void tearDown(Scenario scenario) {
		if(wantsToQuit==false){
			if(!scenario.isFailed()){
				TestLogger.info("=====================");
				TestLogger.info("======TEST PASS======");
			}
			else //if(scenario.getStatus().equalsIgnoreCase("failed"))
			{
				TestLogger.info("=====================");
				TestLogger.info("======TEST FAI=======");
				TestLogger.info(scenario.toString());
				if(failfast){
					wantsToQuit = scenario.isFailed();
				} 
			}
			mainAction.closeBrowser();
		}
	}
	
	@When("^[o|O]pen the home page$")
	@Step("^[o|O]pen the home page$")
	
	public void openTheHomePage() {
		openThePage();
	}
	
	@When("^[r|R]eload the page$")
	@Step("^[r|R]eload the page$")
	public void reloadPage() throws Throwable {
		getDriver().navigate().refresh();
	}
	
	@When("^[c|C]lose the browser$")
	@Step("^[c|C]lose the browser$")
	public void closeBrowser() throws Throwable {
		getDriver().close();
	}
	
	@When("[c|C]lear session")
	@Step("[c|C]lear session")
	public void clearSession() {
		getDriver().manage().deleteAllCookies();
	}
	
	@When("^[w|W]ait for (\\d+) seconds$")
	@Step("^[w|W]ait for (\\d+) seconds$")
	public void waitForTime(int time) throws Throwable {
		pause(time*1000);
	}
	
	@When("^[s|S]croll down$")
	@Step("^[s|S]croll down$")
	public void scrollDown() throws Throwable {
		mainAction.scrollDown();
	}

	@When("^[s|S]croll up$")
	@Step("^[s|S]croll up$")
	public void scrollUp() throws Throwable {
		mainAction.scrollUp();
	}
	
	@When("^[n|N]avigate to \"(.*?)\"$")
	@Step("^[n|N]avigate to \"(.*?)\"$")
    public void navigateToUrl(String url) throws Throwable {
		mainAction.redirectToUrl(url);
    }
    
	@When("^[m|M]ove forward one page$")
	@Step("^[m|M]ove forward one page$")
    public void moveForwardOnePage() throws Throwable {
    	mainAction.moveForwardPage();
    }

	@When("^[m|M]ove backward one page$")
	@Step("^[m|M]ove backward one page$")
    public void moveBackwardOnePage() throws Throwable {
    	mainAction.moveBackwardPage();
    }
    
	@When("^[s|S]witch to \"([^\"]*)\" frame$")
	@Step("^[s|S]witch to \"([^\"]*)\" frame$")
	public void switchToFrame(String iframeName) throws Throwable {
    	mainAction.switchToIFrame(iframeName);
		pause(2000);
	}

	@When("^[s|S]witch back to parent frame$")
	@Step("^[s|S]witch back to parent frame$")
	public void switchBackToParentFrame() throws Throwable {
		mainAction.swichBackToParentFrame();
	}

	@When("^[s|S]switch back to main frame$")
	@Step("^[s|S]switch back to main frame$")
	public void switchBackToMainFrame() throws Throwable {
		mainAction.swichBackToMainFrame();
	}
	
	@When("^upload on the \"([^\"]*)\" file \"([^\"]*)\"$")
	@Step("^upload on the \"([^\"]*)\" file \"([^\"]*)\"$")
	public void uploadFile(String element, String pathFile) throws Throwable {
		mainAction.uploadFileUsingJavascript(element,pathFile);
	}
}
