package com.cucumbercraft.POMPages.ExpeditedReg;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.HomePage;
import com.cucumbercraft.POMPages.WebReg.BeginRegistration;
import com.cucumbercraft.framework.DriverFactory;
import com.cucumbercraft.framework.Util;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.Context;
import com.cucumbercraft.stepdefinitions.MasterStepDefs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Expedited_Registration extends MasterStepDefs {
    WebDriver driver;
    WebDriverUtil webUtil;
    Context testContext;

    public Expedited_Registration(WebDriver driver) {
        this.driver = driver;
        this.webUtil = new WebDriverUtil(driver);
    }

    private static final Logger log = LogManager.getLogger(DriverFactory.class);

    //xpaths pages
    BeginRegistration beginReg = new BeginRegistration(testContext.getDriver());
    HomePage homepage = new HomePage(driver);

    public void Begin_RegistrationBtn() throws InterruptedException {
        webUtil.click(beginReg.beginRegistration1);
        webUtil.waitForPageLoaded();
    }

    public void Homepage_RegisterBtn() throws Exception {
//        driver.get(properties.getProperty("ApplicationUrl_Preprod"));
        driver.get(properties.getProperty("ApplicationUrl_UAT"));

        try {

            if (webUtil.isElementVisible(By.xpath("(//*[contains(text(),'Allow All')])[2]"), 5))
                driver.findElement(By.xpath("(//*[contains(text(),'Allow All')])[2]")).click();
            else
                driver.findElement(By.xpath("//*[contains(text(),'Allow All')]")).click();

        } catch (Exception e) {

        }

        if ((webUtil.isElementDisplayed(homepage.hdr, 10)) &&
                (webUtil.getText(homepage.hdr).equals("Welcome to Ireland State Savings Online"))) {
            log.info("Application launched successfully");
        } else {
            log.info("Application launched Failed");
        }
        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
        ExtentCucumberAdapter.addTestStepLog("State saving home page is displayed ");
        webUtil.click(homepage.registration);
    }

    public void RegistrationBtn() throws InterruptedException {
        webUtil.waitForPageLoaded();
        webUtil.click(homepage.registration);
        webUtil.waitForPageLoaded();
    }
}
