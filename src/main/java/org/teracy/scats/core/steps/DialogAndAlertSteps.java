package org.teracy.scats.core.steps;

import org.junit.Assert;
import org.teracy.scats.core.actions.DialogAndAlert;

import cuke4duke.annotation.I18n.EN.When;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class DialogAndAlertSteps {
	@Steps
	DialogAndAlert dialogAndAlert;
	
	@When("^[a|A]lert popup should be message \"(.*?)\" then accept alert$")
	@Step("^[a|A]lert popup should be message \"(.*?)\" then accept alert$")
	public void verifyAlertThenacceptAlert(String msg) throws Throwable {
		String strAlert=dialogAndAlert.getAlertMessage();
		Assert.assertEquals(strAlert + " is not equal to "+msg, strAlert, msg);
		dialogAndAlert.acceptAlert();
		
	}
	
	@When("^[a|A]lert popup should be message \"(.*?)\" then dismiss alert$")
	@Step("^[a|A]lert popup should be message \"(.*?)\" then dismiss alert$")
	public void verifyAlertThendismissAlert(String msg) throws Throwable {
		String strAlert=dialogAndAlert.getAlertMessage();
		Assert.assertEquals(strAlert + " is not equal to "+msg, strAlert, msg);
		dialogAndAlert.dismissAlert();
	}
	
	@When("^[a|A]lert popup should be message \"(.*?)\"$")
	@Step("^[a|A]lert popup should be message \"(.*?)\"$")
	public void verifyAlert(String msg) throws Throwable {
		String strAlert=dialogAndAlert.getAlertMessage();
		Assert.assertEquals(strAlert + " is not equal to "+msg, strAlert, msg);
	}
	
	@When("^[c|C]lick to accept alert$")
	@Step("^[c|C]lick to accept alert$")
	public void acceptAlert() throws Throwable {
		dialogAndAlert.acceptAlert();
	}
	
	@When("^[c|C]lick to dismiss alert$")
	@Step("^[c|C]lick to dismiss alert$")
	public void dismissAlert() throws Throwable {
		dialogAndAlert.dismissAlert();
	}
}
