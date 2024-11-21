package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.Workflow;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.ExceptionUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

public class WorkFlowSteps extends MasterStepDefs {
    private final WebDriver driver = DriverManager.getWebDriver();
    private final Workflow wkflow = new Workflow(driver);


    @Given("^Launch Workflow$")
    public void launchWorkflow() {
        wkflow.launchWrkflow();
    }

    @Then("^Validate purchase page$")
    public void validatePurchasePage() {
        try {
            wkflow.validatePurchase();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Given("User launch google")
    public void userLaunchGoogle() {
        driver.get("https://www.google.com");
        ExtentCucumberAdapter.addTestStepLog("Launched Google");


    }

    @Then("Enter some text")
    public void enterSomeText() {
        System.out.println("INFO");
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "Launched Google");
    }

    @Then("enter open first link")
    public void enterOpenFirstLink() {
        System.out.println("FAIL");
        ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, "Launched Google");
    }

    @Then("Validate all links")
    public void validateAllLinks() {
        System.out.println("WARNING");
        ExtentCucumberAdapter.getCurrentStep().log(Status.WARNING, "Launched Google");
    }

    @Then("Validate footer")
    public void validateFooter() {
        System.out.println("SKIP");
        ExtentCucumberAdapter.getCurrentStep().log(Status.SKIP, "Launched Google");
    }

    @Then("Validate headers")
    public void validateHeaders() {
        driver.findElement(By.name("j"));

    }

    @And("Click administration forms select process: {string} and action: {string}")
    public void clickAdminstrationFormsSelectProcessAndActionOnPage(String process, String action) {

        wkflow.getOption(process, action);

    }

    @Then("Validate case details page is displayed for this holding id: {string}")
    public void validateCaseDetailsPageIsDisplayed(String holding) {
        try {
            wkflow.validate_Repayment_Search("4018039816");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Given("Launch admin portal")
    public void launchAdminPortal() {
        driver.get("https://Gulves:HotelHand2024@app-anpost-sswbepbadminportal-q-ne01.azurewebsites.net/Orders/Orders.aspx");
        try {
            Assertions.assertThat(driver.findElement(By.xpath("//h1")).getText().trim())
                    .as("Admin Portal heading")
                    .isEqualTo("State Savings Administration Home");
            ExtentCucumberAdapter.getCurrentStep().pass("Admin portal is up and running");
        } catch (AssertionError e) {
            ExtentCucumberAdapter.getCurrentStep().fail("Admin portal heading not validated");
            throw new ExceptionUtils(e.getMessage());
        }

    }
}
