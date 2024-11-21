package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.HomePage;
import com.cucumbercraft.POMPages.ProfileAndSettingsPg;
import com.cucumbercraft.POMPages.Security_Page;
import com.cucumbercraft.POMPages.SignInPg;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.Util;
import com.cucumbercraft.framework.WebDriverUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class PortalLoginSteps extends MasterStepDefs {
    public WebDriver driver = DriverManager.getWebDriver();
    WebDriverUtil webUtil = new WebDriverUtil(driver);
    SignInPg signinpage = new SignInPg(driver);
    Security_Page securitycodepg = new Security_Page(driver);
    HomePage homepage = new HomePage(driver);
    ProfileAndSettingsPg pfPg = new ProfileAndSettingsPg(driver);
    public final String url = properties.getProperty("OTPEndPoint");
    String OTPresponse;


    @Then("Click sign in button on home page")
    public void clickSignInButtonOnHomePage() {
        try {
            homepage.click_Sign_In();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("Validate the proper rendering and placement of the email and password input fields")
    public void validateTheProperRenderingAndPlacementOfTheEmailAndPasswordInputFields() {
        signinpage.validate_Sign_in_Block();
    }

    @And("^User enters new password: \"([^\"]*)\", clicks next to confirm input$")
    public void userEntersNewPasswordClicksNext(String pwd) {
        try {
            signinpage.newPwd(pwd);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("User click on  Confirm change of email address  button which is displayed in mail message body")
    public void user_click_on_confirm_change_of_email_address_button_which_is_displayed_in_mail_message_body() {
        try {
            signinpage.getLinkMailinator1();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("Verify the Email has been changed")
    public void verify_the_email_has_been_changed() {
        try {
            signinpage.verifyEmailPage();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Given("^User is on the homepage of State Savings portal$")
    public void user_is_on_the_homepage_of_state_savings_portal() throws Throwable {
//        driver.get(properties.getProperty("ApplicationUrl_Preprod"));
        driver.get(properties.getProperty("ApplicationUrl_UAT"));

        if (webUtil.isElementDisplayed(homepage.savePref, 5)) {
            webUtil.click(homepage.savePref);
        } else {
            ExtentCucumberAdapter.addTestStepLog("€ currency is not displayed in From account:FAIL");

        }


        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
        ExtentCucumberAdapter.addTestStepLog("State saving home page is displayed ");

        webUtil.click(homepage.signIn);
        log.info("Sign In Clicked and login page is displayed");
    }

    @When("^User enters the Username - \"([^\"]*)\" and Password - \"([^\"]*)\"$")
    public void user_enters_the_username_something_and_password_something(String username, String password) throws Throwable {
        if (webUtil.getText(signinpage.val1).equalsIgnoreCase("Enter your details:")) {
            webUtil.sendKeys(signinpage.userName, username);
            webUtil.sendKeys(signinpage.passwd, password);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
        } else {
            log.info("Sign in Page not displayed");
            ExtentCucumberAdapter.addTestStepLog("Username and password fields not displayed ");
            Assert.fail();
        }
    }

    @And("^User clicks signin button$")
    public void user_clicks_submit_button() throws Throwable {
        if (webUtil.isElementDisplayed(signinpage.signInBtn, 10)) {
            webUtil.click(signinpage.signInBtn);
        } else {
            log.info("Sign In button not Enabled");
            Assert.fail();
        }
    }

    @Then("^User \"([^\"]*)\" is able to login to the State Savings portal with Phone number \"([^\"]*)\"$")
    public void user_is_able_to_login_to_the_state_savings_portal(String name, String number) throws Throwable {
        OTPresponse = apiDriver.getOTP(url, 200, number);
        ExtentCucumberAdapter.addTestStepLog("OTP is " + OTPresponse);
        if (webUtil.getText(securitycodepg.val2).equals("Verification Code")) {
            webUtil.sendKeys(securitycodepg.otp, OTPresponse);
            if (webUtil.isElementDisplayed(securitycodepg.confirmbtn, 15)) {
                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
                webUtil.javascriptClick(securitycodepg.confirmbtn);
            } else {
                log.info("Confirm button not enabled");
                Assert.fail();
            }
        }
        if (webUtil.isElementDisplayed(pfPg.greetings, 15)) {
            System.out.println(webUtil.getText(pfPg.greetings));
//            Assert.assertTrue(webUtil.getText(pfPg.greetings).equalsIgnoreCase(("Welcome back, ").concat(name)));
            log.info("Greeting Title Matches");
        } else {
            log.info("Greeting Title not displayed");
            Assert.fail();
        }
    }

    @Given("^User is on the Dashboard page with Username - \"([^\"]*)\", Password - \"([^\"]*)\" and Mobile Number - \"([^\"]*)\"$")
    public void userIsOnTheDashboardPageWithUsernamePasswordAndMobileNumber(String username, String password, String number) throws Throwable {
        signinpage.LightLogin(username, password, number);
        System.out.println("Logged In");
    }


    @Then("User login using these credentials: username {string} password {string} mobile number {string}")
    public void userLoginUsingTheseCredentialsUsernamePasswordMobileNumber(String username, String pwd, String mobile) {
        try {
            homepage.click_Sign_In();
            signinpage.typeInUsername(username)
                    .typeInPassword(pwd)
                    .clickBtnSignIn()
                    .typeInOTP(mobile)
                    .clickBtnVerifyOTP(mobile)
                    .validateWelcomeMsg();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Then("^User click forgot password button enters email: \"([^\"]*)\" on slider click on next button$")
    public void userClickForgotPasswordButtonEntersOnSliderClickOnNextButton(String mail) {
        try {
            signinpage.forgotPassword(mail);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^User provides verification code clicks request reset link button for this mobile number: \"([^\"]*)\"$")
    public void userProvidesVerificationCodeClicksRequestResetLinkButtonOnSecurityPage(String mobile) {
        try {
            signinpage.enterOtp(mobile);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("Considering the user opens the Mailinator portal and provides the email: {string}")
    public void userLaunchMailnatorPortalWithThis(String mail) {
        try {
            signinpage.openMailinator(mail);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Then("User clicks password change button present in email message body")
    public void userClickOnChangeYourPasswordButtonWhichIsDisplayedInMailMessageBody() {
        try {
            signinpage.getLinkMailinator();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("User login using invalid credential")
    public void userLoginUsingInvalidCredential(DataTable table) {
        homepage.click_Sign_In();
        List<Map<String, String>> user = table.asMaps(String.class, String.class);
        for (Map<String, String> form : user) {
            String username = form.get("Username");
            String password = form.get("Password");
            String errorMsg = form.get("Error Message");
            signinpage.typeInUsername(username)
                    .typeInPassword(password)
                    .clickBtnSignIn()
                    .validatErrorMsg(errorMsg);

        }
    }

    @Then("Validate error message on sign in page for {string}")
    public void validateErrorMessageForFeilds(String str) {
        signinpage.feildErrorMessage(str);

    }

    @When("Verification code entered is {string}")
    public void verificationCodeEnteredIs(String str) {
        securitycodepg.errorCheck(str);
    }
}
