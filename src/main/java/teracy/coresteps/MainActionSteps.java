package teracy.coresteps;

import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import teracy.coreactions.MainAction;
import teracy.coreactions.PageCore;
import teracy.utils.TestLogger;

public class MainActionSteps extends PageCore {
	@Steps
	MainAction mainAction;
	
	/************************* upload file ************************************************/
	/************************* press keyboard ************************************************/
	@When("^[p|P]ress down arrow key (\\d+) time$")
	@Step("^[p|P]ress down arrow key (\\d+) time$")
	public void pressDownArrowKey(int number) throws Throwable {
		mainAction.pressDownArrowKey(number);
	}

	@When("^[p|P]ress up arrow key (\\d+) time$")
	@Step("^[p|P]ress up arrow key (\\d+) time$")
	public void pressDownUpKey(int number) throws Throwable {
		mainAction.pressUpArrowKey(number);
	}

	@When("^[p|P]ress enter key (\\d+) time$")
	@Step("^[p|P]ress enter key (\\d+) time$")
	public void pressEnterKey(int number) throws Throwable {
		mainAction.pressEnterKey(number);
	}

	@When("^[p|P]ress tab key (\\d+) times$")
	@Step("^[p|P]ress tab key (\\d+) times$")
	public void pressTabKey(int number) throws Throwable {
		mainAction.pressTabKey(number);
	}
	/************************* press keyboard ************************************************/
	/************************* button ************************************************/
	@When("^[d|D]ouble-click on the \"([^\"]*)\"$")
	@Step("^[d|D]ouble-click on the \"([^\"]*)\"$")
	public void doubleClickOn(String element) throws Throwable {
		mainAction.doubleClick(element);
	}

	@When("^[r|R]ight click on the \"([^\"]*)\"$")
	@Step("^[r|R]ight click on the \"([^\"]*)\"$")
	public void rightClickOn(String element) throws Throwable {
		mainAction.rightClick(element);
	}
	
	@When("^[c|C]lick on the \"([^\"]*)\"$")
	@Step("^[c|C]lick on the \"([^\"]*)\"$")
	public void clickOn(String element) throws Throwable {
		mainAction.click(element);
	}
	
	@When("^[c|C]lick on the \"([^\"]*)\" and switch to the second tab$")
	@Step("^[c|C]lick on the \"([^\"]*)\" and switch to the second tab$")
	public void clickSwitchToSecondTab(String element) throws Throwable {
		mainAction.click(element);
		pause(2000);
		String base = getDriver().getWindowHandle();
		Set<String> set = getDriver().getWindowHandles();
		set.remove(base);
		getDriver().switchTo().window((String) set.toArray()[1]);
	}

	@When("^[c|C]lick on the \"([^\"]*)\" and switch to the first tab$")
	@Step("^[c|C]lick on the \"([^\"]*)\" and switch to the first tab$")
	public void clickSwitchToFirstTab(String element) throws Throwable {
		mainAction.click(element);
		pause(2000);
		String base = getDriver().getWindowHandle();
		Set<String> set = getDriver().getWindowHandles();
		set.remove(base);
		getDriver().switchTo().window((String) set.toArray()[0]);
	}
	
	@When("^[h|H]over the mouse on the \"([^\"]*)\"$")
	@Step("^[h|H]over the mouse on the \"([^\"]*)\"$")
	public void hoverOn(String element){
		WebElement moveToElement = waitForGetElementPresent(element);
		Actions builder = new Actions(getDriver());
		Action dragAndDrop = builder.moveToElement(moveToElement).release(moveToElement).build();
		dragAndDrop.perform();
	}
	/************************* button ************************************************/
	
	/************************* textbox ************************************************/
	@When("^[f|F]ill value \"([^\"]*)\" to the field \"([^\"]*)\"$")
	@Step("^[f|F]ill value \"([^\"]*)\" to the field \"([^\"]*)\"$")
	public void enterIntoFieldWithValue(String text,String element) throws Throwable {
		mainAction.sendKey(element,text);
	}
	
	@When("^[f|F]ill value \"([^\"]*)\" to the field \"([^\"]*)\" and store value into variable \"([^\"]*)\"$")
	@Step("^[f|F]ill value \"([^\"]*)\" to the field \"([^\"]*)\" and store value into variable \"([^\"]*)\"$")
	public void enterTntoTheFieldWithValueAndStoreVariable(String value,String element, String var) {
		value=mainAction.sendKey(element,value);
		listVar.put(var, value);
		TestLogger.info(listVar.get(var));
	}
	/************************* textbox ************************************************/
	
	/************************* checkbox ************************************************/
	@When("^[u|U]ncheck on the \"([^\"]*)\"$")
	@Step("^[u|U]ncheck on the \"([^\"]*)\"$")
	public void uncheckOn(String element) throws Throwable {
		if(isSelected(element))
			mainAction.click(element);
	}

	@When("^[c|C]heck on the \"([^\"]*)\"$")
	@Step("^[c|C]heck on the \"([^\"]*)\"$")
	public void checkOn(String element) throws Throwable {
		if(!isSelected(element))
			mainAction.click(element);
	}
	/************************* checkbox ************************************************/
	/************************* combobox/listbox ************************************************/
	@When("^[s|S]elect value \"([^\"]*)\" from the drop down \"([^\"]*)\"$")
	@Step("^[s|S]elect value \"([^\"]*)\" from the drop down \"([^\"]*)\"$")
	public void selectByValueFromDropDown(String value, String element)throws Throwable {
		mainAction.selectByValueDropDown(value, element);
	}

	@When("^[s|S]elect text \"([^\"]*)\" from the drop down \"([^\"]*)\"$")
	@Step("^[s|S]elect text \"([^\"]*)\" from the drop down \"([^\"]*)\"$")
	public void selectByTextFromDropDown(String value, String element)throws Throwable {
		mainAction.selectByTextDropDown(value, element);
	}

	@When("^[s|S]elect the index (\\d+) from the drop down \"([^\"]*)\"$")
	@Step("^[s|S]elect the index (\\d+) from the drop down \"([^\"]*)\"$")
	public void selectByIndexFromDropDown(int indexOption, String element) throws Throwable {
		mainAction.selectByIndexDropDown(indexOption, element);
	}

	/************************* combobox/listbox ************************************************/

}


