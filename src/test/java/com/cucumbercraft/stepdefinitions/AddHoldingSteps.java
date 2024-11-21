package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.ReqToAddHoldings;
import com.cucumbercraft.POMPages.SignInPg;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.Util;
import com.cucumbercraft.framework.WebDriverUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddHoldingSteps extends MasterStepDefs {
    WebDriver driver = DriverManager.getWebDriver();
    WebDriverUtil webUtil = new WebDriverUtil(driver);
    ReqToAddHoldings reqToAddHold = new ReqToAddHoldings(driver);
    Context testContext;

    SignInPg signInPg = new SignInPg(driver);


    @Given("User launches the State Savings portal URL and login to portal sucessfully with  {string} ,{string} and {string}")
    public void userLaunchesTheStateSavingsPortalURLAndLoginToPortalSucessfullyWithAnd(String username, String password, String number) throws Throwable {
        signInPg.LightLogin(username, password, number);
        System.out.println("User login sucessfull with username and password" + " " + username + " " + password);
    }

    @And("^User clicks on add holding url and add different holding \"([^\"]*)\"$")
    public void userClicksOnAddHoldingUrlAndAddDifferentHolding(String userProduct) throws Throwable {
        try {
            webUtil.click(By.xpath("//button[@id='requestToAddHolding']"));

            if (userProduct.equalsIgnoreCase("Prize Bonds")) {
                if (webUtil.isElementVisible(reqToAddHold.selectProduct1, 20)) {

                    webUtil.selectListItem(reqToAddHold.selectProduct1, select -> select.selectByVisibleText(userProduct));

                    System.out.println("Selected the product from dropdown" + ": " + userProduct);
                    webUtil.isElementVisible(reqToAddHold.bondNumber, 10);
                    webUtil.sendKeys(reqToAddHold.bondNumber, "584698");
                    webUtil.sendKeys(reqToAddHold.commenttextbox1, "this is my bond");
                }
            } else {
                if (webUtil.isElementVisible(reqToAddHold.selectProduct1, 10)) {
                    webUtil.selectListItem(reqToAddHold.selectProduct1, s -> s.selectByVisibleText(userProduct));
                    System.out.println("Selected the product from dropdown" + ": " + userProduct);
                }
                if (webUtil.isElementVisible(reqToAddHold.accountNumber1, 10)) {
                    webUtil.sendKeys(reqToAddHold.accountNumber1, "748596");
                    webUtil.sendKeys(reqToAddHold.commenttextbox1, "this is my bond");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @And("User clicks on add holding url and add different {string}")
    public void user_clicks_on_add_holding_url_and_add_different(String userProduct) {
        try {

            webUtil.click(By.xpath("//button[@class='js-modalTrigger icon-link icon-link--add gtm-linkclick bn-link']"));

            if (userProduct.equalsIgnoreCase("Prize Bonds")) {
                if (webUtil.isElementVisible(reqToAddHold.selectProduct1, 20)) {

                    webUtil.selectListItem(reqToAddHold.selectProduct1, select -> select.selectByVisibleText(userProduct));

                    System.out.println("Selected the product from dropdown" + ": " + userProduct);
                    webUtil.isElementVisible(reqToAddHold.bondNumber, 20);
                    webUtil.sendKeys(reqToAddHold.bondNumber, "584698");
                    webUtil.sendKeys(reqToAddHold.commenttextbox1, "this is my bond");
                    ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
                }
            } else {
                if (webUtil.isElementVisible(reqToAddHold.selectProduct1, 20)) {
                    webUtil.selectListItem(reqToAddHold.selectProduct1, s -> s.selectByVisibleText(userProduct));
                    System.out.println("Selected the product from dropdown" + ": " + userProduct);
                    ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
                }
                if (webUtil.isElementVisible(reqToAddHold.accountNumber1, 20)) {
                    webUtil.sendKeys(reqToAddHold.accountNumber1, "748596");
                    webUtil.sendKeys(reqToAddHold.commenttextbox1, "this is my bond");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("^User clicks on add another product \"([^\"]*)\"$")
    public void userClicksOnAddAnotherProduct(String userAnotherProduct) throws Throwable {

        if (webUtil.isElementVisible(reqToAddHold.addAnotherProductLink, 10)) {
            webUtil.click(reqToAddHold.addAnotherProductLink);
            log.info("Add another product link is clicked ");
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            webUtil.selectListItem(reqToAddHold.selectProduct2, select -> select.selectByVisibleText(userAnotherProduct));
            System.out.println("Selected the product from dropdown" + ": " + userAnotherProduct);
        }
        if (webUtil.isElementVisible(reqToAddHold.accountNumber2, 10)) {
            webUtil.sendKeys(reqToAddHold.accountNumber2, "748596");
            webUtil.sendKeys(reqToAddHold.commenttextbox2, "this is my bond");
        }

//            userClicksOnAddHoldingUrlAndAddDifferentHolding(userAnotherProduct);

    }

    @And("^User removes another product \"([^\"]*)\"$")
    public void userRemovesAnotherProduct(String userAnotherProduct) throws Throwable {

        if (webUtil.isElementVisible(reqToAddHold.removeProduct1, 20)) {
            webUtil.click(reqToAddHold.removeProduct1);
            log.info("remove product link is clicked ");
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            System.out.println("Deleted the product selected previously" + ": " + userAnotherProduct);
        }
//            userClicksOnAddHoldingUrlAndAddDifferentHolding(userAnotherProduct);

    }

    @Then("^User clicks on download button and SuccessFul message is displayed$")
    public void userClicksOnDownloadButtonAndSuccessFulMessageIsDisplayed() {
        try {
            if (webUtil.isElementclickable(reqToAddHold.confirmDownloadForm, 10)) {
                webUtil.click(reqToAddHold.confirmDownloadForm);
                System.out.println("Download button is clicked");
            } else {

                System.out.println("Error Occured couldn't click on the download button");
            }
            if (webUtil.isElementVisible(reqToAddHold.thanksContent, 10)) {
                Assert.assertTrue(webUtil.getText(reqToAddHold.thanksContent).contains("Thank you, your form is downloading"));
                System.out.println(webUtil.getText(reqToAddHold.thanksContent));
            }

            if (webUtil.isElementclickable(reqToAddHold.thanksFormCloseButton, 10)) {
                webUtil.click(reqToAddHold.thanksFormCloseButton);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
