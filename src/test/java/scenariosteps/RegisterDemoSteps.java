package scenariosteps;

import org.teracy.scats.core.steps.BaseSteps;
import org.teracy.scats.core.steps.MainActionSteps;

import cuke4duke.annotation.I18n.EN.When;
import cuke4duke.StepMother;
import net.thucydides.core.annotations.Steps;

public class RegisterDemoSteps extends cuke4duke.Steps{
	public RegisterDemoSteps(StepMother stepMother) {
       super(stepMother);
    }
	
	@Steps
	MainActionSteps mainActionSteps;
	
	@Steps
	BaseSteps baseSteps;
	
	@When("^register demo step$")
	public void register_demo() throws Throwable {
		When("^fill value \"{!auto,s46}\" to the field \"demo.firstname\"$");
		When("^fill value \"{!auto,s46}\" to the field \"demo.lastname\"$");
		When("^fill value \"{!auto,s46}\" to the field \"demo.password\"$");
		When("^fill value \"{!auto,s46}\" to the field \"demo.confirmpassword\"$");
		
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
