package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.HomePage;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.Util;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomeSteps extends MasterStepDefs {

    static Logger log = LogManager.getLogger(HomeSteps.class);
    WebDriver driver = DriverManager.getWebDriver();
    HomePage home = new HomePage(driver);
    String status;


    @Given("Launch the url")
    public void launch_the_url() {
        try {
//            switch (properties.getProperty("Environment")) {
//                case "preprod":
//                case "PreProd":
//                case "Preprod":
//                case "PREPROD":
//                    ExtentCucumberAdapter.addTestStepLog("Execution on <b>PreProd</b> Environment");
//                    driver.get(properties.getProperty("ApplicationUrl_Preprod"));
////
//                    home.userpermission();
//                    break;
//                case "uat":
//                case "Uat":
//                    ExtentCucumberAdapter.addTestStepLog("Execution on <b>UAT</b> Environment");
//                    driver.get(properties.getProperty("K13"));
//                    home.userpermission();
////
//                    break;
//                case "UAT":
//                    ExtentCucumberAdapter.addTestStepLog("Execution on <b>UAT</b> Environment");
//                    driver.get(properties.getProperty("ApplicationUrl_UAT"));
//                    ExtentCucumberAdapter.addTestStepLog("Application Title:- \n<b>" + driver.getCurrentUrl() + "\n" + driver.getTitle() + "</b>");
//                    home.userpermission();
//                    break;
//                case "sit":
//                case "Sit":
//                case "SIT":
//                    ExtentCucumberAdapter.addTestStepLog("Execution on <b>SIT</b> Environment");
//                    driver.get(properties.getProperty("ApplicationUrl_Sit"));
//                    ExtentCucumberAdapter.addTestStepLog("Application Title:- \n<b>" + driver.getCurrentUrl() + "\n" + driver.getTitle() + "</b>");
//                    home.userpermission();
//                    break;
//                case "pdf":
//                    ExtentCucumberAdapter.addTestStepLog("Execution on <b>PreProd Environment");
//                    driver.get(properties.getProperty("ApplicationUrl_Pdf"));
//                    home.userpermission();
//                    break;
//                case "Prod":
//                case "PROD":
//                    ExtentCucumberAdapter.addTestStepLog("Execution on <b>Prod</b> Environment");
//                    driver.get(properties.getProperty("ApplicationUrl_PROD"));
//                    ExtentCucumberAdapter.addTestStepLog("Application Title:- \n<b>" + driver.getCurrentUrl() + "\n" + driver.getTitle() + "</b>");
//                    home.userpermission();
//                    break;
//            }
//
//            log.info("url launch successful");
//
            driver.get(config.getUrl());
            home.userpermission();
        } catch (Exception e) {
            Assert.fail("launchTheUrl: ", e);

        }
    }

    @Given("^Launch the application$")
    public void launchTheapplication() throws Throwable {
        try {
            switch (properties.getProperty("Environment")) {
                case "preprod":
                case "PreProd":
                case "Preprod":
                case "PREPROD":
                    ExtentCucumberAdapter.addTestStepLog("Execution on <b>PreProd</b> Environment");
                    driver.get(properties.getProperty("ApplicationUrl_Preprod"));
                    ExtentCucumberAdapter.addTestStepLog("Application Title:- \n<b>" + driver.getCurrentUrl() + "\n" + driver.getTitle() + "</b>");
                    home.userpermission();
                    break;
                case "uat":
                case "Uat":
                case "UAT":
                    ExtentCucumberAdapter.addTestStepLog("Execution on <b>UAT</b> Environment");
                    driver.get(properties.getProperty("K13"));
                    ExtentCucumberAdapter.addTestStepLog("Application Title:- \n<b>" + driver.getCurrentUrl() + "\n" + driver.getTitle() + "</b>");
                    home.userpermission();
                    break;
                case "sit":
                case "Sit":
                case "SIT":
                    ExtentCucumberAdapter.addTestStepLog("Execution on <b>SIT</b> Environment");
                    driver.get(properties.getProperty("ApplicationUrl_Sit"));
                    ExtentCucumberAdapter.addTestStepLog("Application Title:- \n<b>" + driver.getCurrentUrl() + "\n" + driver.getTitle() + "</b>");
                    break;
                case "pdf":
                    ExtentCucumberAdapter.addTestStepLog("Execution on <b>PreProd Environment");
                    driver.get(properties.getProperty("ApplicationUrl_Pdf"));

                    //ExtentCucumberAdapter.addTestStepLog("Application Title:- <b>"+driver.getTitle()+"</b>");
                    break;
                case "Prod":
                case "PROD":
                    ExtentCucumberAdapter.addTestStepLog("Execution on <b>Prod</b> Environment");
                    driver.get(properties.getProperty("ApplicationUrl_PROD"));
                    ExtentCucumberAdapter.addTestStepLog("Application Title:- \n<b>" + driver.getCurrentUrl() + "\n" + driver.getTitle() + "</b>");
                    break;
            }
            log.info("url launch successful");

        } catch (Exception e) {
            currentScenario.attach(Util.takeScreenshot(driver), "image/png", "Url");
            Assert.fail("launchTheUrl: ", e);
        }
    }


}
