package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.POMPages.RepayReinvest.Thank_You_Page;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.cucumbercraft.framework.DriverManager.getWebDriver;

public class ThankYouSteps {

    private final WebDriver driver = getWebDriver();

    private final Thank_You_Page thank = new Thank_You_Page(driver);

    @Then("validate Thank You Page is displayed")
    public void validateThankYouPageIsDisplayed() {
        try {
            thank.verifyHeaderThankYouPage();
            thank.clickBackBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }


    }
}
