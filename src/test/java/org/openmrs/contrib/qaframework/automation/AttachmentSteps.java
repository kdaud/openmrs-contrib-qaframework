package org.openmrs.contrib.qaframework.automation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openmrs.contrib.qaframework.page.AttachmentsPage;
import org.openmrs.reference.page.HomePage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AttachmentSteps extends Steps {
	
	private AttachmentsPage attachmentPage;
	
	@Given("a user logs in, searches a patient and visits Attachment page")
	public void loadPatientDashboard() {
		goToLoginPage().login(testProperties.getUsername(), testProperties.getPassword(), testProperties.getLocation());
		homePage = new HomePage(loginPage);
		findPatientPage = homePage.goToFindPatientRecord();
		//TODO Get a way of user selecting a patient
		findPatientPage.clickOnFirstPatient();
		dashboardPage = findPatientPage.clickOnFirstPatient();
	}
	
	@And("a user clicks on Attachment link")
	public void loadAttachmentPage() {
		attachmentPage = new AttachmentsPage(dashboardPage);
		attachmentPage.clickOnAttachmentLink();
	}
	
	@Then("the system loads Attachment page")
	public void systemLoadsAttachmentPage() {
		assertEquals(getElement(patientHeaderId).getText(), getElement(patientHeaderId).getText());
		assertTrue(textExists("Attachments"));
	}
	
	@And("a user attaches a file")
	public void userAttachingFile() {
		attachmentPage.clickOrDropFile();
		
	}
	
	@And("a user enters a caption")
	public void userEntersCaption() {
		attachmentPage.addCaption();
	}
	
	@Then("the system enables upload button")
	public void the_system_enables_upload_button() {
		//TODO 
		//assertTrue();
		
	}
	
	@When("a user clicks the upload link button")
	public void a_user_clicks_the_upload_link_button() {
		// Write code here that turns the phrase above into concrete actions
		
	}
	
	@Then("the system uploads the attached file")
	public void the_system_uploads_the_attached_file() {
		// Write code here that turns the phrase above into concrete actions
		
	}
	
	

	@And("a user clicks clear forms button")
	public void a_user_clicks_clear_forms_button() {
		// Write code here that turns the phrase above into concrete actions
		
	}
	
	@Then("the system clears the form")
	public void the_system_clears_the_form() {
		// Write code here that turns the phrase above into concrete actions
		
	}
	
	@And("quit browser")
	public void closebrowser() {
		quitBrowser();
	}

}
