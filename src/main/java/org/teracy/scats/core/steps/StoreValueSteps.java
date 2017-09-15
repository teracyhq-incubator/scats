package org.teracy.scats.core.steps;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.teracy.scats.core.actions.PageCore;
import org.teracy.scats.utils.DateTime;
import org.teracy.scats.utils.RandomData;
import org.teracy.scats.utils.TestLogger;

import cuke4duke.annotation.I18n.EN.When;
import net.thucydides.core.annotations.Step;

public class StoreValueSteps extends PageCore {
	@When("^[s|S]tore the text of the element \"([^\"]*)\" in variable \"([^\"]*)\"$")
	@Step("^[s|S]tore the text of the element \"([^\"]*)\" in variable \"([^\"]*)\"$")
	public void storeTheTextOfElementInVariable(String element, String var) {
		String value = waitForGetElementPresent(element).getText();
		TestLogger.info(value);
		listVar.put(var, value);
		TestLogger.info(listVar.get(var));
	}

	@When("^[s|S]tore the value of the element \"([^\"]*)\" in variable \"([^\"]*)\"$")
	@Step("^[s|S]tore the value of the element \"([^\"]*)\" in variable \"([^\"]*)\"$")
	public void storeTheValueOfElementInVariable(String element, String var) {
		String value = waitForGetElementPresent(element).getAttribute("value");
		listVar.put(var, value);
		TestLogger.info(listVar.get(var));
	}

	@When("^[s|S]tore the status of checkbox element \"([^\"]*)\" in variable \"([^\"]*)\"$")
	@Step("^[s|S]tore the status of checkbox element \"([^\"]*)\" in variable \"([^\"]*)\"$")
	public void storeTheStatusOfCheckboxElementInVariable(String element, String var) {
		String value = waitForGetElementPresent(element).getAttribute("checked");
		if (value == null)
			;
		value = "null";
		listVar.put(var, value);
		TestLogger.info(listVar.get(var));
	}

	@When("^[s|S]tore result of operation \"([^\"]*)\" between variable \"([^\"]*)\" and variable \"([^\"]*)\" into variable \"([^\"]*)\"$")
	@Step("^[s|S]tore result of operation \"([^\"]*)\" between variable \"([^\"]*)\" and variable \"([^\"]*)\" into variable \"([^\"]*)\"$")
	public void storeResultOfOperationVariables(String str, String var1, String var2, String var3)
			throws Throwable {
		String value = "";
		if (str.equals("+"))
			value = listVar.get(var1) + listVar.get(var2);
		else if (str.equals("-"))
			value = String.valueOf(Double.valueOf(listVar.get(var1)) - Double.valueOf(listVar.get(var2)));
		else if (str.equals("*"))
			value = String.valueOf(Double.valueOf(listVar.get(var1)) * Double.valueOf(listVar.get(var2)));
		else //(str.equals("/"))
			value = String.valueOf(Double.valueOf(listVar.get(var1)) / Double.valueOf(listVar.get(var2)));
		listVar.put(var3, value);
		TestLogger.info("var2 is " + listVar.get(var2));
	}

	@When("^[s|S]tore the current date in variable \"([^\"]*)\"$")
	@Step("^[s|S]tore the current date in variable \"([^\"]*)\"$")
	public void storeTheCurrentDate(String var) {
		Date date = new Date();
		String value = new SimpleDateFormat("yyyy-MM-dd").format(date);
		listVar.put(var, value);
		TestLogger.info(listVar.get(var));
	}

	@When("^[s|S]tore the current date of next year in variable \"([^\"]*)\"$")
	@Step("^[s|S]tore the current date of next year in variable \"([^\"]*)\"$")
	public void storeTheCurrentDateOfNextYear(String var) {
		DateTime dt = new DateTime();
		String value = dt.getDayOfNextYear("yyyy-MM-dd", 1);
		listVar.put(var, value);
		TestLogger.info(listVar.get(var));
	}

	@When("^[s|S]tore the text \"([^\"]*)\" in variable \"([^\"]*)\"$")
	@Step("^[s|S]tore the text \"([^\"]*)\" in variable \"([^\"]*)\"$")
	public void storeTheTextInVariable(String text, String var) {
		String value = RandomData.getRandomString(text);
		TestLogger.info(value);
		listVar.put(var, value);
		TestLogger.info(listVar.get(var));
	}
}
