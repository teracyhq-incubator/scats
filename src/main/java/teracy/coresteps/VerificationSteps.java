package teracy.coresteps;

import java.util.Set;

import org.junit.Assert;

import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import teracy.coreactions.MainAction;
import teracy.coreactions.PageCore;
import teracy.utils.TestLogger;

public class VerificationSteps extends PageCore {
	@Steps
	MainAction mainAction;
	
	@When("^[t|T]he \"([^\"]*)\" should be disabled$")
	@Step("^[t|T]he \"([^\"]*)\" should be disabled$")
	public void theElementShouldBeDisabled(String element) throws Throwable {
		Assert.assertEquals(element + " is enabled ", isEnabled(element), false);
	}

	@When("^[t|T]he \"([^\"]*)\" should be enabled$")
	@Step("^[t|T]he \"([^\"]*)\" should be enabled$")
	public void theElementShouldBeEnabled(String element) throws Throwable {
		pause(1000);
		Assert.assertEquals(element + " is disabled ", isEnabled(element), true);
	}

	@When("^[t|T]he \"([^\"]*)\" checkbox should be unchecked$")
	@Step("^[t|T]he \"([^\"]*)\" checkbox should be unchecked$")
	public void theCheckboxShouldBeUnchecked(String element) throws Throwable {
		Assert.assertEquals(element + " is checked ", isSelected(element), false);
	}

	@When("^[t|T]he \"([^\"]*)\" checkbox should be checked$")
	@Step("^[t|T]he \"([^\"]*)\" checkbox should be checked$")
	public void theCheckboxShouldBeChecked(String element) throws Throwable {
		Assert.assertEquals(element + " is unchecked ", isSelected(element), true);
	}

	@When("^[t|T]he text of element \"(.*?)\" should be \"(.*?)\"$")
	@Step("^[t|T]he text of element \"(.*?)\" should be \"(.*?)\"$")
	public void theTextElementShouldBeText(String target, String value) throws Throwable {
		Assert.assertTrue(waitForGetElementPresent(target).getText() + " is not equal " + value,
				waitForGetElementPresent(target).getText().equals(value));
	}

	@When("^[t|T]he text of element \"(.*?)\" should not be \"(.*?)\"$")
	@Step("^[t|T]he text of element \"(.*?)\" should not be \"(.*?)\"$")
	public void theTextElementShouldNotBeValue(String target, String value) throws Throwable {
		Assert.assertFalse(waitForGetElementPresent(target).getText() + " is equal " + value,
				waitForGetElementPresent(target).getText().equals(value));
	}

	@When("^[t|T]he text of element \"(.*?)\" should contain \"(.*?)\"$")
	@Step("^[t|T]he text of element \"(.*?)\" should contain \"(.*?)\"$")
	public void theTextElementShouldContainValue(String target, String value) throws Throwable {
		Assert.assertTrue(waitForGetElementPresent(target).getText() + " does not contain " + value,
				waitForGetElementPresent(target).getText().contains(value));
	}

	@When("^[t|T]he text of element \"(.*?)\" should not contain \"(.*?)\"$")
	@Step("^[t|T]he text of element \"(.*?)\" should not contain \"(.*?)\"$")
	public void theTextElementShouldNotContainValue(String target, String value) throws Throwable {
		Assert.assertFalse(waitForGetElementPresent(target).getText() + " contain " + value,
				waitForGetElementPresent(target).getText().contains(value));
	}

	@When("^[t|T]he text \"([^\"]*)\" should be shown$")
	@Step("^[t|T]he text \"([^\"]*)\" should be shown$")
	public void theTextShouldBeShown(String arg1) throws Throwable {
		Assert.assertTrue("The text " + arg1 + " is not shown on page", getTextOnPage(arg1));
	}

	@When("^[t|T]he text \"([^\"]*)\" should not be shown$")
	@Step("^[t|T]he text \"([^\"]*)\" should not be shown$")
	public void theTextShouldNotBeShown(String arg1) throws Throwable {
		Assert.assertFalse("The text " + arg1 + " is shown on page", getTextOnPage(arg1));
	}

	@When("^[t|T]he element \"([^\"]*)\" should be shown$")
	@Step("^[t|T]he element \"([^\"]*)\" should be shown$")
	public void theElementShouldBeShown(String element) throws Throwable {
		Assert.assertNotNull("The element " + element + " is not shown on page", waitForGetElementPresent(element));
	}

	@When("^[t|T]he element \"([^\"]*)\" should not be shown$")
	@Step("^[t|T]he element \"([^\"]*)\" should not be shown$")
	public void theElementShouldNotBeShown(String element) throws Throwable {
		Assert.assertNull("The element " + element + " is shown on page", waitForGetElementPresent(element));
	}

	@When("^[p|P]age should be redirected to the \"([^\"]*)\"(?: page| screen)?$")
	@Step("^[p|P]age should be redirected to the \"([^\"]*)\"(?: page| screen)?$")
	public void iShouldBeRedirectedToPage(String arg1) throws Throwable {
		String currentUrl = getDriver().getCurrentUrl().toLowerCase().trim();
		String expectedUrl = (baseUrl.substring(baseUrl.indexOf("://") + 3)
				+ ("/" + arg1.replace(" ", "").toLowerCase()).replace("//", "/")).trim();
		TestLogger.info("currentUrl is " + currentUrl);
		TestLogger.info("expectedUrl is " + expectedUrl);
		Assert.assertEquals(currentUrl + " does not contain " + expectedUrl, currentUrl.contains(expectedUrl), true);

	}
	
	@When("^[p|P]age \"([^\"]*)\" should be redirected in new tab$")
	@Step("^[p|P]age \"([^\"]*)\" should be redirected in new tab$")
	public void iShouldBeRedirectedToPageInNewTab(String arg1) throws Throwable {
		String base = getDriver().getWindowHandle();
		boolean check = false;
		int count = 0;
		while (!check) {
			try {
				Set<String> winHandle = getDriver().getWindowHandles();
				if (winHandle.size() > 1) {
					check = true;
					winHandle.remove(base);
					getDriver().switchTo().window((String) winHandle.toArray()[0]);
					pause(2000);
					TestLogger.info(getDriver().getCurrentUrl());
					TestLogger.info(arg1);
					Assert.assertEquals(getDriver().getCurrentUrl() + "does not contain " + (arg1.toLowerCase()).trim(),
							getDriver().getCurrentUrl().contains((arg1.toLowerCase()).trim()), true);
					// close the window and switch back to the base tab
					getDriver().close();
					getDriver().switchTo().window(base);
				}
				pause(1000);
				count++;
				if (count > timeout) {
					TestLogger.error("Can not switch to new tab");
				}
			} catch (Exception e) {
			}
		}
	}
	
	@When("^[t|T]he variable \"([^\"]*)\" should be equal variable \"([^\"]*)\" minus variable \"([^\"]*)\"$")
	@Step("^[t|T]he variable \"([^\"]*)\" should be equal variable \"([^\"]*)\" minus variable \"([^\"]*)\"$")
	public void theVariableShouldBeEqualVariableMinus(String var1, String var2, String var3) throws Throwable {
		TestLogger.info("var1 is " + listVar.get(var1));
		TestLogger.info("var2 is " + listVar.get(var2));
		TestLogger.info("var2 is " + listVar.get(var3));
		Assert.assertEquals(
				listVar.get(var1) + " is not equal " + Double.valueOf(listVar.get(var2)) + " - "
						+ Double.valueOf(listVar.get(var3)),
				listVar.get(var1), Double.valueOf(listVar.get(var2)) - Double.valueOf(listVar.get(var3)));
	}

	@When("^[t|T]he variable \"([^\"]*)\" should be equal variable \"([^\"]*)\" plus variable \"([^\"]*)\"$")
	@Step("^[t|T]he variable \"([^\"]*)\" should be equal variable \"([^\"]*)\" plus variable \"([^\"]*)\"$")
	public void theVariableShouldBeEqualVariablePlus(String var1, String var2, String var3) throws Throwable {
		TestLogger.info("var1 is " + listVar.get(var1));
		TestLogger.info("var2 is " + listVar.get(var2));
		TestLogger.info("var2 is " + listVar.get(var3));
		if (isNumeric(listVar.get(var1)) && isNumeric(listVar.get(var2)))
			Assert.assertEquals(
					listVar.get(var1) + " is not equal " + Double.valueOf(listVar.get(var2)) + " + "
							+ Double.valueOf(listVar.get(var3)),
					listVar.get(var1), Double.valueOf(listVar.get(var2)) + Double.valueOf(listVar.get(var3)));
		else
			Assert.assertEquals(listVar.get(var1) + " is not equal " + listVar.get(var2) + " + " + listVar.get(var3),
					listVar.get(var1), listVar.get(var2) + listVar.get(var3));
	}

	@When("^[t|T]he variable \"([^\"]*)\" should be equal variable \"([^\"]*)\" plus \"([^\"]*)\"$")
	@Step("^[t|T]he variable \"([^\"]*)\" should be equal variable \"([^\"]*)\" plus \"([^\"]*)\"$")
	public void theVariableShouldEeEqualVariablePlusValue(String var1, String var2, String str)
			throws Throwable {
		TestLogger.info("var1 is " + listVar.get(var1));
		TestLogger.info("var2 is " + listVar.get(var2));
		if (isNumeric(listVar.get(var1)) && isNumeric(listVar.get(var2)))
			Assert.assertEquals(
					listVar.get(var1) + "is not equal " + Double.valueOf(listVar.get(var2)) + " + "
							+ Double.valueOf(str),
					listVar.get(var1), Double.valueOf(listVar.get(var2)) + Double.valueOf(str));
		else
			Assert.assertEquals(listVar.get(var1) + "is not equal " + listVar.get(var2) + " + " + str,
					listVar.get(var1), listVar.get(var2) + str);
	}

	@When("^[t|T]he variable \"([^\"]*)\" should be smaller than variable \"([^\"]*)\"$")
	@Step("^[t|T]he variable \"([^\"]*)\" should be smaller than variable \"([^\"]*)\"$")
	public void theVariableShouldBeSmallerThanVariable(String var1, String var2) throws Throwable {
		TestLogger.info("var1 is " + listVar.get(var1));
		TestLogger.info("var2 is " + listVar.get(var2));
		Assert.assertTrue(Double.valueOf(listVar.get(var1)) + "<" + Double.valueOf(listVar.get(var2)),
				Double.valueOf(listVar.get(var1)) < Double.valueOf(listVar.get(var2)));
	}
	
	@When("^[t|T]he variable \"([^\"]*)\" should be equal to variable \"([^\"]*)\"$")
	@Step("^[t|T]he variable \"([^\"]*)\" should be equal to variable \"([^\"]*)\"$")
	public void theVariable1EqualVariable2(String var1, String var2) {
		TestLogger.info("var1 is " + listVar.get(var1));
		TestLogger.info("var2 is " + listVar.get(var2));
		if(isNumeric(listVar.get(var1))&&isNumeric(listVar.get(var2)))
			Assert.assertEquals(Double.valueOf(listVar.get(var1))+" is not equal "+Double.valueOf(listVar.get(var2)),Double.valueOf(listVar.get(var1)), Double.valueOf(listVar.get(var2)));
		else
			Assert.assertEquals(listVar.get(var1)+" is not equal "+listVar.get(var2),listVar.get(var1), listVar.get(var2));
	}
}
