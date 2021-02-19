package org.openmrs.contrib.qaframework.automation;

import org.openmrs.reference.ReferenceApplicationTestBase;
import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.reference.page.FindPatientPage;
import org.openmrs.uitestframework.page.LoginPage;
import org.openmrs.uitestframework.page.TestProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.fail;

public class Steps extends ReferenceApplicationTestBase {
	protected TestProperties testProperties = TestProperties.instance();
	protected LoginPage loginPage;
	protected FindPatientPage findPatientPage;
	protected String firstPatientIdentifier;
	protected ClinicianFacingPatientDashboardPage dashboardPage;
	protected By patientHeaderId = By.cssSelector("div.identifiers span");

	public Steps() {
		try {
			startWebDriver();
			loginPage = getLoginPage();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	protected void quit() {
		quitBrowser();
	}

	protected void elementClickOn(By elementBy) {
		driver.findElement(elementBy).click();
	}

	protected WebElement getElement(By elementBy) {
		try {
			return driver.findElement(elementBy);
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	private void quitBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

	protected boolean textExists(String text) {
		return driver.findElements(
				By.xpath("//*[contains(text(),'" + text + "')]")).size() > 0;
	}

	protected void configuredUserLogin() {
		goToLoginPage().login(testProperties.getUsername(),
				testProperties.getPassword(), testProperties.getLocation());
	}
}
