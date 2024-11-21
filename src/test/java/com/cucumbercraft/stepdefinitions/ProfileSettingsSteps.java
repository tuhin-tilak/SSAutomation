package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.*;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.Util;
import com.cucumbercraft.framework.WebDriverUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ProfileSettingsSteps extends MasterStepDefs {
    static Logger log = LogManager.getLogger("Profileandsetting.class");
    String url = properties.getProperty("OTPEndPoint");
    String OTPresponse;
    Context testContext;
    WebDriver driver = DriverManager.getWebDriver();
    WebDriverUtil webUtil = new WebDriverUtil(driver);
    SignInPg signinpage = new SignInPg(driver);
    Security_Page securitycodepg = new Security_Page(driver);
    HomePage homepage = new HomePage(driver);
    ProfileAndSettingsPg pfPg = new ProfileAndSettingsPg(driver);
    Dash_Board dashboard = new Dash_Board(driver);


    @And("^User navigates to Password Change section on the Profile and Settings tab for \"([^\"]*)\"$")
    public void PasswordChangetab(String number) {
        try {
//            webUtil.waitUntilElementVisible(dashboard.profileAndSettingstab, 10);
            webUtil.click(dashboard.profileAndSettingstab);
//            dashboard.clickNavTab("Profile");
            ExtentCucumberAdapter.addTestStepLog("Profile and setting page is displayed ");
            webUtil.waitUntilElementVisible(pfPg.changePasswordbutton, 10);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            webUtil.click(pfPg.changePasswordbutton);
            OTPresponse = apiDriver.getOTP(url, 200, number);
        } catch (Exception e) {
            System.out.println("failed to click on change password button");
            e.printStackTrace();
        }
    }

    @And("^User is able to change the \"([^\"]*)\" to \"([^\"]*)\"$")
    public void Changepasswordto(String oldpwd, String password2) throws Throwable {
        try {
            webUtil.waitUntilElementVisible(pfPg.pwdOldPwdTxt, 10);
            webUtil.sendKeys(pfPg.pwdOldPwdTxt, oldpwd);
            webUtil.waitUntilElementVisible(pfPg.pwdChangePwdTxt, 20);
            webUtil.sendKeys(pfPg.pwdChangePwdTxt, password2);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
//        webUtil.scrollToView(pfPg.pwdChangeConfirmbutton);
            webUtil.waitUntilElementVisible(pfPg.pwdChangeConfirmbutton, 20);
            webUtil.click(pfPg.pwdChangeConfirmbutton);
            webUtil.waitUntilElementVisible(pfPg.pwdEnterSecCodeText, 10);
            webUtil.sendKeys(pfPg.pwdEnterSecCodeText, OTPresponse);
            webUtil.waitUntilElementVisible(pfPg.pwdVerifyButton, 10);
            webUtil.click(pfPg.pwdVerifyButton);
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("entered new password" + password2);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            if (webUtil.getText(pfPg.headerYourPersonalDetails).equalsIgnoreCase("Your Personal Details")) {
                webUtil.waitUntilElementVisible(pfPg.successPwd, 20);
                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
                ExtentCucumberAdapter.addTestStepLog("Password changed successfully");
                System.out.println(driver.findElement(pfPg.successPwd).getText());
            }
        } catch (Exception e) {
            System.out.println("failed to change password ");
            e.printStackTrace();
        }

    }

    @And("^User navigates to Email address Change section on the Profile and Settings tab for \"([^\"]*)\"$")
    public void userNavigatesToEmailAddressChangeSectionOnTheProfileAndSettingsTabFor(String number) throws Throwable {
        try {

            webUtil.click(dashboard.profileAndSettingstab);
            webUtil.click(pfPg.changeEmailbutton);
            OTPresponse = apiDriver.getOTP(url, 200, number);

        } catch (Exception e) {
            System.out.println("failed to change password ");
            e.printStackTrace();
        }
    }

    @And("^User is able to change the Email to \"([^\"]*)\"$")
    public void userIsAbleToChangeTheEmailTo(String email2) throws Throwable {
        try {
            webUtil.waitForPageLoaded();
            webUtil.waitUntilElementVisible(pfPg.newEmailtxt, 20);
            webUtil.sendKeys(pfPg.newEmailtxt, email2);
            webUtil.waitUntilElementVisible(pfPg.confirmbutton, 20);
            webUtil.click(pfPg.confirmbutton);
            webUtil.waitUntilElementVisible(pfPg.EmailEnterSecCodeText, 20);
            webUtil.sendKeys(pfPg.EmailEnterSecCodeText, OTPresponse);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            webUtil.waitUntilElementVisible(pfPg.EmailVerifyButton, 20);
            webUtil.click(pfPg.EmailVerifyButton);
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            System.out.println("we are on this email change  page");
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));

            ExtentCucumberAdapter.addTestStepLog("Successfully changed email");
            System.out.println("we clicked on button");
        } catch (Exception e) {
            System.out.println("failed to change email ");
            ExtentCucumberAdapter.addTestStepLog("\"failed to change email \"");
            e.printStackTrace();
        }
    }


    @And("^User navigates to Full name Change section on the Profile and Settings tab and click on request an update  button$")
    public void userNavigatesToFullNameChangeSectionOnTheProfileAndSettingsTabAndClickOnCloseButton() throws Exception {
        try {
            webUtil.waitUntilElementVisible(dashboard.profileAndSettingstab, 20);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            webUtil.click(dashboard.profileAndSettingstab);
            webUtil.waitForPageLoaded();
            log.info("Profile and setting page is displayed ");
            webUtil.waitUntilElementVisible(pfPg.reqUpdatelink, 20);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            webUtil.click(pfPg.reqUpdatelink);
            webUtil.waitForPageLoaded();
            if (webUtil.getText(pfPg.updatenamelabl).equalsIgnoreCase("Update your full name")) {
                System.out.println("Title is matching hence closing the pop up");
                webUtil.waitUntilElementVisible(pfPg.updateClosepopup, 20);
                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
                webUtil.click(pfPg.updateClosepopup);
                ExtentCucumberAdapter.addTestStepLog("Request raised successfully for name change");
            }

        } catch (Exception e) {
            System.out.println("failed to change full name ");
            ExtentCucumberAdapter.addTestStepLog("failed to change full name");
            e.printStackTrace();
        }

    }


    @And("^User navigates to Your mobile number Change section on the Profile and Settings tab and click on request an update button$")
    public void userNavigatesToYourMobileNumberChangeSectionOnTheProfileAndSettingsTabAndClickOnRequestAnUpdateButton() throws Exception {
        try {
            webUtil.waitUntilElementVisible(dashboard.profileAndSettingstab, 20);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            webUtil.click(dashboard.profileAndSettingstab);
            webUtil.waitForPageLoaded();
            log.info("Profile and setting page is displayed ");
            if (webUtil.getText(pfPg.mobnumlabel).equalsIgnoreCase("Your Mobile Number")) {
                System.out.println("label is matching");
                webUtil.waitUntilElementVisible(pfPg.requstbtn, 20);
                webUtil.click(pfPg.requstbtn);
                webUtil.waitForPageLoaded();
                if (webUtil.getText(pfPg.updatemblnumtitle).equalsIgnoreCase("Update your mobile number")) {
                    System.out.println("Mobile number change pop up is displayed");
                    webUtil.waitUntilElementVisible(pfPg.closbutn, 20);
                    ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
                    webUtil.click(pfPg.closbutn);
                } else {
                    Assert.fail("Mobile number popup is not displayed");
                }

            }

        } catch (Exception e) {
            System.out.println("failed to change mobile number ");
            ExtentCucumberAdapter.addTestStepLog("failed to change mobile number ");
            e.printStackTrace();
        }
    }


    @And("^User navigates to Your Change your address section on the Profile and Settings tab and click on request an update button$")
    public void userNavigatesToYourChangeYourAddressSectionOnTheProfileAndSettingsTabAndClickOnRequestAnUpdateButton() throws Exception {
        try {
            webUtil.waitUntilElementVisible(dashboard.profileAndSettingstab, 20);
            webUtil.click(dashboard.profileAndSettingstab);
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            webUtil.waitUntilElementVisible(pfPg.requestbutn, 20);
            webUtil.click(pfPg.requestbutn);
            webUtil.waitForPageLoaded();

            if (webUtil.getText(pfPg.updateadstitle).equalsIgnoreCase("Update your address")) {
                System.out.println("Change addres pop up is displayed");
                webUtil.waitUntilElementVisible(pfPg.closebtn, 20);
                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
                webUtil.click(pfPg.closebtn);
            }
        } catch (Exception e) {
            System.out.println("failed to change your address ");
            ExtentCucumberAdapter.addTestStepLog("failed to change your address");
            e.printStackTrace();
        }
    }

    @And("^User navigates to Your account detail section on the Profile and Settings tab and click on show SSCN button$")
    public void userNavigatesToYourAccountDetailSectionOnTheProfileAndSettingsTabAndClickOnShowSSCNButton() throws Exception {
        try {
            webUtil.waitUntilElementVisible(dashboard.profileAndSettingstab, 20);
            webUtil.click(dashboard.profileAndSettingstab);
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            if (webUtil.getText(pfPg.accntdetaillble).equalsIgnoreCase("Your Account Details")) {
                webUtil.waitUntilElementVisible(pfPg.showsscnbtn, 20);
                webUtil.click(pfPg.showsscnbtn);
                if (webUtil.getText(pfPg.yourSSNlabl).equalsIgnoreCase("Your SSCN Code")) {
                    System.out.println("we are on Show SSCN pop up");
                    webUtil.waitUntilElementVisible(pfPg.dwnldbtn, 20);
                    ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
                    webUtil.click(pfPg.dwnldbtn);
                    webUtil.waitFor(5000);
                    ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
                    System.out.println("we downloaded now closing it");
                    webUtil.waitUntilElementVisible(pfPg.cancelbtn, 20);
                    webUtil.click(pfPg.cancelbtn);
                }
            }
        } catch (Exception e) {
            System.out.println("failed to change your address ");
            ExtentCucumberAdapter.addTestStepLog("failed to change your address");
            e.printStackTrace();
        }
    }


    @Then("^User verifies \"([^\"]*)\" on profile and setting tab$")
    public void userVerifiesSSCNOnProfileAndSettingTab(String sscn) {


        try {
            webUtil.click(dashboard.profileAndSettingstab);
            if (webUtil.isElementDisplayed(pfPg.SSCNtext, 10)) {
                if (webUtil.getText(pfPg.SSCNtext).contentEquals("State Savings Customer Number (SSCN)"))
                    log.info("SSCN header verified");
                if (webUtil.getText(pfPg.sscn).contentEquals(sscn))
                    log.info("SSCN  verified");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//-----------------------------Negative Scenarios---------------------------------------------------

    @And("User navigates to Email address Change section on the Profile and Settings tab One")
    public void user_navigates_to_email_address_change_section_on_the_profile_and_settings_tab_one() {
        try {
            webUtil.waitUntilElementVisible(dashboard.profileAndSettingstab, 20);
            webUtil.click(dashboard.profileAndSettingstab);
            webUtil.waitForPageLoaded();
            webUtil.waitUntilElementVisible(pfPg.changeEmailbutton, 20);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotFile(driver));
            webUtil.click(pfPg.changeEmailbutton);
        } catch (Exception e) {
            System.out.println("failed to change password ");
            e.printStackTrace();
        }
    }


    @And("User Validates the Email Address {string}")
    public void user_validates_the_email_address(String username) {
        try {
            webUtil.waitUntilElementVisible(pfPg.newEmailtxt, 20);
            webUtil.sendKeys(pfPg.newEmailtxt, username);
            webUtil.waitUntilElementVisible(pfPg.confirmbutton, 20);
            webUtil.click(pfPg.confirmbutton);
            webUtil.gettextlog(pfPg.sameEmailPopup, String::equalsIgnoreCase, "The email address needs to be different from your current email address");
            log.info(webUtil.getText(pfPg.sameEmailPopup));
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotFile(driver));
            ExtentCucumberAdapter.addTestStepLog(webUtil.getText(pfPg.sameEmailPopup));


        } catch (Exception e) {
            System.out.println("failed to change password ");
            e.printStackTrace();
        }
    }


    @And("User Validates the Invalid Email Address {string}")
    public void user_validates_the_invalid_email_address(String Email4) {
        try {
            webUtil.waitUntilElementVisible(pfPg.newEmailtxt, 10);
            webUtil.sendKeys(pfPg.newEmailtxt, Email4);
            webUtil.waitUntilElementVisible(pfPg.confirmbutton, 10);
            webUtil.click(pfPg.confirmbutton);
            webUtil.gettextlog(pfPg.invalidEmailPopup, String::equalsIgnoreCase, "Please enter a valid email");
            log.info(webUtil.getText(pfPg.invalidEmailPopup));
            ExtentCucumberAdapter.addTestStepLog(webUtil.getText(pfPg.invalidEmailPopup));

        } catch (Exception e) {
            System.out.println("failed to change password ");
            e.printStackTrace();
        }

    }

    @And("User Validates the Registered Email Address {string}")
    public void user_validates_the_registered_email_address(String Email4) {
        try {
            webUtil.waitUntilElementVisible(pfPg.newEmailtxt, 20);
            webUtil.sendKeys(pfPg.newEmailtxt, Email4);
            webUtil.waitUntilElementVisible(pfPg.confirmbutton, 20);
            webUtil.click(pfPg.confirmbutton);
            webUtil.gettextlog(pfPg.existingEmail, String::equalsIgnoreCase, "Change of email cannot be completed using the email address you have entered. Please use another one.");
            log.info(webUtil.getText(pfPg.existingEmail));
            ExtentCucumberAdapter.addTestStepLog(webUtil.getText(pfPg.existingEmail));
        } catch (Exception e) {
            System.out.println("failed to change password ");
            e.printStackTrace();
        }

    }

    @And("User Validates the null Email Address {string}")
    public void user_validates_the_null_email_address(String Email4) {
        try {
            webUtil.waitUntilElementVisible(pfPg.newEmailtxt, 20);
            webUtil.sendKeys(pfPg.newEmailtxt, Email4);
            webUtil.waitUntilElementVisible(pfPg.confirmbutton, 20);
            webUtil.click(pfPg.confirmbutton);
            webUtil.gettextlog(pfPg.emptyEmail, String::equalsIgnoreCase, "Please enter your new email address");
            log.info(webUtil.getText(pfPg.invalidEmailPopup));
            ExtentCucumberAdapter.addTestStepLog(webUtil.getText(pfPg.invalidEmailPopup));


        } catch (Exception e) {
            System.out.println("failed to change password ");
            e.printStackTrace();
        }

    }

    @Then("User is validation on same password {string}{string}")
    public void user_is_validation_on_same_password(String password, String password1) {
        try {
            webUtil.waitUntilElementVisible(pfPg.pwdOldPwdTxt, 10);
            webUtil.sendKeys(pfPg.pwdOldPwdTxt, password);
            webUtil.waitUntilElementVisible(pfPg.pwdChangePwdTxt, 20);
            webUtil.sendKeys(pfPg.pwdChangePwdTxt, password1);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotFile(driver));
            webUtil.waitUntilElementVisible(pfPg.pwdChangeConfirmbutton, 20);
            webUtil.click(pfPg.pwdChangeConfirmbutton);
            webUtil.gettextlog(pfPg.samePwd, String::equalsIgnoreCase, "Please choose a password that you haven't used before.");
            log.info(webUtil.getText(pfPg.samePwd));
            ExtentCucumberAdapter.addTestStepLog(webUtil.getText(pfPg.samePwd));

        } catch (Exception e) {
            System.out.println("failed to change password ");
            e.printStackTrace();
        }
    }

    @Then("User is validation on password Requirement one {string}{string}")
    public void user_is_validation_on_password_requirement_one(String password, String password1) {
        try {
            webUtil.waitUntilElementVisible(pfPg.pwdOldPwdTxt, 10);
            webUtil.sendKeys(pfPg.pwdOldPwdTxt, password);
            webUtil.waitUntilElementVisible(pfPg.pwdChangePwdTxt, 20);
            webUtil.sendKeys(pfPg.pwdChangePwdTxt, password1);
//            webUtil.enterKey();
            driver.findElement(pfPg.pwdChangePwdTxt).sendKeys(password1, Keys.TAB);

            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotFile(driver));
//            webUtil.scrollToView(pfPg.pwdChangeConfirmbutton);
//            webUtil.click(pfPg.pwdChangeConfirmbutton);
            webUtil.gettextlog(pfPg.requiredPwd, String::equalsIgnoreCase, "Please enter a valid password");
            log.info(webUtil.getText(pfPg.requiredPwd));
            ExtentCucumberAdapter.addTestStepLog(webUtil.getText(pfPg.requiredPwd));
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotFile(driver));

        } catch (Exception e) {
            System.out.println("failed to change password");
            e.printStackTrace();
        }
    }

    @Then("Validate error message when the user enters an invalid {string} and valid {string}")
    public void User_is_validation_on_current_wrong_password_one(String password, String password1) {
        try {
            webUtil.waitUntilElementVisible(pfPg.pwdOldPwdTxt, 10);
            webUtil.sendKeys(pfPg.pwdOldPwdTxt, "Dummy!2023");
            webUtil.waitUntilElementVisible(pfPg.pwdChangePwdTxt, 20);
            webUtil.sendKeys(pfPg.pwdChangePwdTxt, password1);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotFile(driver));
            webUtil.waitUntilElementVisible(pfPg.pwdChangeConfirmbutton, 20);
            webUtil.click(pfPg.pwdChangeConfirmbutton);
            webUtil.gettextlog(pfPg.wrongPwd, String::equalsIgnoreCase, "You have entered your details incorrectly once. Your account will be locked after three failed attempts.");
            log.info(webUtil.getText(pfPg.wrongPwd));
            ExtentCucumberAdapter.addTestStepLog(webUtil.getText(pfPg.wrongPwd));


        } catch (Exception e) {
            System.out.println("failed to change password ");
            e.printStackTrace();
        }
    }

    @Then("User is validation on current password null {string}{string}")
    public void User_is_validation_on_current_password_null(String password, String password1) {
        try {
            webUtil.waitUntilElementVisible(pfPg.pwdOldPwdTxt, 10);
            webUtil.sendKeys(pfPg.pwdOldPwdTxt, "");
            webUtil.waitUntilElementVisible(pfPg.pwdChangePwdTxt, 20);
            webUtil.sendKeys(pfPg.pwdChangePwdTxt, password1);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotFile(driver));
            webUtil.waitUntilElementVisible(pfPg.pwdChangeConfirmbutton, 20);
            webUtil.click(pfPg.pwdChangeConfirmbutton);
            webUtil.gettextlog(pfPg.nullPwd, String::equalsIgnoreCase, "Please enter your old password");
            log.info(webUtil.getText(pfPg.nullPwd));
            ExtentCucumberAdapter.addTestStepLog(webUtil.getText(pfPg.nullPwd));

        } catch (Exception e) {
            System.out.println("failed to change password");
            e.printStackTrace();
        }

    }

    @Then("Validate the error message on non registered email")
    public void validate_the_error_message_on_non_registered_email() {
        try {
            webUtil.gettextlog(pfPg.nonRegisteredEmail, String::equalsIgnoreCase, "The details you have entered do not match our records. Please try again.");
            log.info(webUtil.getText(pfPg.nonRegisteredEmail));
            ExtentCucumberAdapter.addTestStepLog(webUtil.getText(pfPg.nonRegisteredEmail));


        } catch (Exception e) {
            System.out.println("failed to change password ");
            e.printStackTrace();
        }
    }

    @Then("Validate the error message on null email")
    public void validate_the_error_message_on_null_email() {
        try {
            webUtil.gettextlog(pfPg.nullEmail, String::equalsIgnoreCase, "Please enter your email address");
            log.info(webUtil.getText(pfPg.nullEmail));
            ExtentCucumberAdapter.addTestStepLog(webUtil.getText(pfPg.nullEmail));

        } catch (Exception e) {
            System.out.println("failed to change password ");
            e.printStackTrace();
        }
    }

    @Then("Validate the error message on invalid email")
    public void validate_the_error_message_on_invalid_email() {
        try {
            webUtil.gettextlog(pfPg.invalidEmail, String::equalsIgnoreCase, "Please enter a valid email address");
            log.info(webUtil.getText(pfPg.invalidEmail));
            ExtentCucumberAdapter.addTestStepLog(webUtil.getText(pfPg.invalidEmail));


        } catch (Exception e) {
            System.out.println("failed to change password ");
            e.printStackTrace();
        }
    }

}