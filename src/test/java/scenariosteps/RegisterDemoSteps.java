package scenariosteps;

import org.teracy.scats.core.steps.BaseSteps;
import org.teracy.scats.core.steps.MainActionSteps;

import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class RegisterDemoSteps {
	@Steps
	MainActionSteps mainActionSteps;
	
	@Steps
	BaseSteps baseSteps;
	
	@When("^register demo step$")
	public void register_demo() throws Throwable {
		mainActionSteps.enterIntoFieldWithValue("{!auto,s46}","demo.firstname");
		mainActionSteps.enterIntoFieldWithValue("{!auto,s46}","demo.lastname");
		mainActionSteps.enterIntoFieldWithValue("{!auto,s46}","demo.password");
		mainActionSteps.enterIntoFieldWithValue("{!auto,s46}","demo.confirmpassword");
		mainActionSteps.enterIntoFieldWithValue("{N10}","demo.phone");
		mainActionSteps.enterIntoFieldWithValue("{!auto,s3,N8}}","demo.username");
		mainActionSteps.enterIntoFieldWithValue("{!auto,s5,!@,s4,!.,s4}","demo.email");
		
		//radio button
		mainActionSteps.checkOn("demo.single");
		mainActionSteps.checkOn("demo.divorced");
		
		//checkbox
		mainActionSteps.clickOn("demo.cricket");
		mainActionSteps.checkOn("demo.reading");
		mainActionSteps.uncheckOn("demo.cricket");
		
		//dropdownbox
		mainActionSteps.selectByTextFromDropDown("Albania","demo.country");
		mainActionSteps.selectByValueFromDropDown("Bahrain","demo.country");
		mainActionSteps.selectByIndexFromDropDown(0,"demo.country");
	
		
		//upload file
		baseSteps.uploadFile("demo.file", "/src/test/resources/DataTest/jpg.jpg");
		
		//button
		mainActionSteps.clickOn("demo.submit");
	}
}
