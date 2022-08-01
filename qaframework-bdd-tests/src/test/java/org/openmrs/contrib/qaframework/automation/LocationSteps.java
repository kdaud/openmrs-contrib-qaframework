/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.automation;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.helper.RestClient;
import org.openmrs.contrib.qaframework.helper.TestData;
import org.openmrs.contrib.qaframework.page.AddEditLocationPage;
import org.openmrs.contrib.qaframework.page.ManageLocationsPage;

public class LocationSteps extends Steps {

    private AddEditLocationPage addEditLocationPage;
    private ManageLocationsPage manageLocationsPage;

    private String locationName;
    private String locationUuid = new TestData.TestLocation(locationName).create();

    @Before(RunTest.HOOK.SELENIUM_LOCATION)
    public void visitHomePage() {
        initiateWithLogin();      
    }

    @After(RunTest.HOOK.SELENIUM_LOCATION)
    public void tearDown() {
        RestClient.delete("location/" + locationUuid, true);
        quit();
    }

    @Given("a user clicks on configure metadata app")
    public void clickOnConfigureMetadataPage() {
        addEditLocationPage = homePage.goToConfigureMetadata().goToManageLocations().goToAddLocation();
    }

    @Then("the system saves the new location")
    public void systemSavesLocation() {
        addEditLocationPage.save();
        assertThat(addEditLocationPage.getValidationErrors(), hasItem("This field is required."));

        locationName = TestData.randomSuffix();
        addEditLocationPage.enterName(locationName);
        addEditLocationPage.selectFirstTag();
        manageLocationsPage = addEditLocationPage.save();

        assertThat(manageLocationsPage, is(notNullValue()));
    }

    @And("the user clicks on delete location button")
    public void clickOnDeletelocationButton() {
        manageLocationsPage.purgeLocation(locationName);
    }

    @Then("the system deletes the location")
    public void systemDeletesLocation() {
        assertThat(pageContent(), not(containsString(locationName)));
    }

    @When("a clicks on metadata app from the home page")
    public void clickOnMetadataApp() {
        manageLocationsPage = homePage.goToConfigureMetadata().goToManageLocations();
    }

    @Then("the system loads the manage location page")
    public void systemLoadsManageLocationPage() {
        assertTrue(textExists(" "));
    }

    @And("the user clicks on retire location button")
    public void clickOnRetireLocationButton() {
        manageLocationsPage.retireLocation(locationName);
    }

    @Then("the system retires the location")
    public void systemRetiresLocation() {
        manageLocationsPage.assertRetired(locationName);
    }
}
