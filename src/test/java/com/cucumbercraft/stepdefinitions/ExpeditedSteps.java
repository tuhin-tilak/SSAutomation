package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.POMPages.ExpeditedReg.*;
import com.cucumbercraft.POMPages.WebReg.Postal_Address_Page;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.ExcelReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class ExpeditedSteps extends MasterStepDefs {
    private final WebDriver driver = DriverManager.getWebDriver();

    Expedited_Registration expreg = new Expedited_Registration(driver);
    ExpeditedLetsGetStarted letGS = new ExpeditedLetsGetStarted(driver);
    ExpeditedModals modals = new ExpeditedModals(driver);
    Expedited_EnterEmail email = new Expedited_EnterEmail(driver);
    Expedited_EnterPIN pin = new Expedited_EnterPIN(driver);
    Postal_Address_Page postal = new Postal_Address_Page(driver);
    Check_Your_Inbox inbox = new Check_Your_Inbox(driver);
    Expedited_EnterMobileNumber mobile = new Expedited_EnterMobileNumber(driver);
    Expedited_SecurityPage security = new Expedited_SecurityPage(driver);
    Create_Password password = new Create_Password(driver);
    public static ExcelReader excel = new ExcelReader();
    public static Map<String, String> testData = new HashMap<>();
    String workBook = System.getProperty("user.dir") + properties.getProperty("ExcelPath");
    Expedited_ErrorMessage errorMessage = new Expedited_ErrorMessage(driver);

    @Given("^user launch the State Savings portal and click on Register$")
    public void userLaunchesTheStateSavingsPortalAndClickOnRegister() {
        try {
            expreg.Homepage_RegisterBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @When("^click on Register$")
    public void clickOnRegister() {
        try {
            expreg.RegistrationBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @When("^click on Begin Registration and verify let's get started page$")
    public void clickOnBeginRegistrationAndVerifyLetSGetStartedPage() {
        try {
            expreg.Begin_RegistrationBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^user enter the details as per \"([^\"]*)\"$")
    public void userEnterTheDetailsAsPer(String Scenario) {
        try {
            testData = excel.reqExceldata(workBook, "Expedited", Scenario);
            updateTestDataConverter(testData);
            letGS.enterFirstName();
            letGS.enterSurName();
            letGS.enterDOB();
            letGS.enterSSCN();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Validate the PPSN_SSCN tooltip$")
    public void validateThePPSN_SSCNTooltip() {
        try {
            letGS.click_SSCN_Tooltip();
            letGS.verify_SSCN_TooltipCont();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^user click on back button of lets get stared page$")
    public void userClickOnBackButtonOfLetsGetStaredPage() {
        try {
            letGS.clickBackBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^user click on next button of lets get stared page$")
    public void userClickOnNextButtonOfLetsGetStaredPage() {
        try {
            letGS.clickNextBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^Validate the Continue Registration modal$")
    public void validateTheContinueRegistrationModal() {
        try {
            modals.Verify_ContRegCont();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^click on back button of continue registration modal$")
    public void clickOnBackButtonOfContinueRegistrationModal() {
        try {
            modals.clickConRegBackBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on close button of Continue registration modal$")
    public void clickOnCloseButtonOfContinueRegistrationModal() {
        try {
            modals.clickConRegCloseBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on I have my PIN button$")
    public void clickOnIHaveMyPINButton() {
        try {
            modals.clickIHavePinBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^verify Enter your Email address page$")
    public void verifyEnterYourEmailAddressPage() {
        try {
            email.verifyEmailConst();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^Enter and Re enter email$")
    public void enterAndReEnterEmail() {
        try {
            email.enterEmailAddress();
            email.reEnterEmailAddress();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^check the consent on Email page$")
    public void checkTheConsentOnEmailPage() {
        try {
            email.checkConsent();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on next button of email page$")
    public void clickOnNextButtonOfEmailPage() {
        try {
            email.clickNextBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on Back button of email page$")
    public void clickOnBackButtonOfEmailPage() {
        try {
            email.clickBackBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^enter your PIN$")
    public void enterYourPIN() {
        try {
            pin.enterPin();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^enter your PIN \"([^\"]*)\"$")
    public void enterYourPIN(String Pin) {
        try {
            pin.enterPin(Pin);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^Click on continue button of PIN page$")
    public void clickOnContinueButtonOfPINPage() {
        try {
            pin.clickConfirmBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^Click on back button of PIN page$")
    public void clickOnBackButtonOfPINPage() {
        try {
            pin.clickBackBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^Click on Didn't get PIN link$")
    public void clickOnDidnTGetPINLink() {
        try {
            pin.clickDidnotLink();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on Resend PIN button$")
    public void clickOnResendPINButton() {
        try {
            pin.popupClickResendBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on Cancel button of Resend PIN page$")
    public void clickOnCancelButtonOfResendPINPage() {
        try {
            pin.popupClickCancelBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //Reissue Pin Thank you page

    @And("^click on Close Button of Reissue PIN thankyou Page$")
    public void clickOnCloseButtonOfReissuePINThankyouPage() {
        try {
            pin.clickCloseBtnReissueThanksPage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^click on GotoStateSaving button of Reissue PIN Thankyou page$")
    public void clickOnGotoStateSavingButtonOfReissuePINThankyouPage() {
        try {
            pin.clickGoToStateBtnReissueThanksPage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^verify check your inbox page$")
    public void verifyCheckYourInboxPage() {
        try {
            inbox.verifyContent();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on Didn't receive email link$")
    public void clickOnDidnTReceiveEmailLink() {
        try {
            inbox.clickDidNotEmailLink();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on Resend email button$")
    public void clickOnResendEmailButton() {
        try {
            inbox.clickResendBtnPopUp();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on Close button of Resend Email$")
    public void clickOnCloseButtonOfResendEmail() {
        try {
            inbox.clickCloseBtnPopUp();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^open Email and click on email verification link$")
    public void openEmailAndClickOnEmailVerificationLink() {
        try {
            System.out.println(testData.get("EmailAddress"));
            String token = inbox.emailVerificationToken(ExpeditedSteps.testData.get("EmailAddress"));
            inbox.launchEmailToken(token);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^open Email and delete an email")
    public void openEmailAndDeleteOnEmailVerificationLink() {
        try {
            System.out.println(testData.get("EmailAddress"));
            String token = inbox.emailVerificationToken(ExpeditedSteps.testData.get("EmailAddress"));
            System.out.println(testData.get(token));

            // inbox.launchEmailToken(token);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Given("^open Email and click on email verification link for \"([^\"]*)\"$")
    public void openEmailAndClickOnEmailVerificationLinkFor(String Scenario) throws Throwable {
        try {
            testData = excel.reqExceldata(workBook, "Expedited", Scenario);
            inbox.launchEmailToken(testData.get("Token"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Enter all the details for mobile number page$")
    public void enteralltheDetailsForMobileNumberPage() {
        try {
//            mobile.selectMobilePrefix();
            mobile.enterMobileNumber();
            // mobile.selectReMobilePrefix();
            mobile.reEnterMobileNumber();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Enter invalid details for mobile number page \"([^\"]*)\"$")
    public void enterinvalidDetailsForMobileNumberPage(String number1, String number2) {
        try {
            mobile.EnterMobileNumberdetails(number1, number2);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Then("^Click on Cancel button of mobile page$")
    public void clickOnCancelButtonOfMobilePage() {
        try {
            mobile.clickCancelBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^Click on continue button of mobile page$")
    public void clickOnContinueButtonOfMobilePage() {
        try {
            mobile.clickContinueBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^click on goBack button of modal$")
    public void clickOnGoBackButtonOfModal() {
        try {
            modals.clickGoBackBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^click on cancel Registration button of modal$")
    public void clickOnCancelRegistrationButtonOfModal() {
        try {
            modals.clickCancelRegistrationBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Enter the security code$")
    public void enterTheSecurityCode() {
        try {
            security.enterSecurityCode();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^Click on back button of security code$")
    public void clickOnbackButtonOfSecurityCode() {
        try {
            security.clickBackBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^Click on verify button of security code$")
    public void clickOnVerifyButtonOfSecurityCode() {
        try {
            security.clickVerifyBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^click on Didn't receive code$")
    public void clickOnDidnTReceiveCode() {
        try {
            security.clickDidNotLink();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^click on Resend Code button of Didn't receive code page$")
    public void clickOnResendCodeButtonOfDidnTReceiveCodePage() {
        try {
            security.clickResendPopupBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^click on cancel button of Didn't receive code page$")
    public void clickOnCancelButtonOfDidnTReceiveCodePage() {
        try {
            security.clickCancelPopupBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^verify the postal address page$")
    public void verifyThePostalAddressPage() {
        try {
            postal.verifyContent();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^click on cancel button of Reissue Exceeded modal$")
    public void clickOnCancelButtonOfReissueExceededModal() {
        try {
            modals.clickReissueCancelBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on Continue Registration button of Reissue Exceeded modal$")
    public void clickOnContinueRegistrationButtonOfReissueExceededModal() {
        try {
            modals.clickReissueContiRegBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^validate the create password page$")
    public void validateTheCreatePasswordPage() {
        try {
            password.verifyCreatePassword();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Enter the password$")
    public void enterThePassword() {
        try {
            password.enterPassword();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^click on Show password button$")
    public void clickOnShowPasswordButton() {
        try {
            password.clickShowPassword();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on complete button of password page$")
    public void clickOnCompleteButtonOfPasswordPage() {
        try {
            password.clickCompleteBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Validate the congratulations message$")
    public void validateTheCongratulationsMessage() {
        try {
            password.verifyCongBanner();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^First time login using ECR User$")
    public void firstTimeLoginUsingECRUser() {

        try {
            password.Login(testData.get("EmailAddress"), testData.get("Password"), "0".concat(ExpeditedSteps.testData.get("otpNumber")));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    // Error messaged steps
    //Already Registered user
    @And("^Verify Already Registered details page displayed$")
    public void verifyAlreadyRegisteredDetailsPageDisplayed() {
        try {
            errorMessage.alreadyRegisterPage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //Lets get started page
    @And("^verify the error message displayed on Lets get started page for \"([^\"]*)\"$")
    public void verifyTheErrorMessageDisplayedOnLetsGetStartedPageFor(String Indicator) {
        try {
            errorMessage.errorLetGetStarted(Indicator);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //Enter Email
    @And("^verify the error message displayed on Email page for \"([^\"]*)\"$")
    public void verifyTheErrorMessageDisplayedOnEmailPageFor(String Indicator) {
        try {
            errorMessage.errorEmailPage(Indicator);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //Pin page
    @And("^Verify the error message displayed on Pin Page for \"([^\"]*)\"$")
    public void verifyTheErrorMessageDisplayedOnPinPageFor(String PinIndicator) {
        try {
            errorMessage.errorPINPage(PinIndicator);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //Enter Mobile Number page
    @And("^verify the error message displayed on Mobile number page for \"([^\"]*)\"$")
    public void verifyTheErrorMessageDisplayedOnMobileNumberPageFor(String MobileIndicator) {
        try {
            errorMessage.errorMobilePage(MobileIndicator);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //Security
    @And("^verify the error message displayed on Security code page for \"([^\"]*)\"$")
    public void verifyTheErrorMessageDisplayedOnSecurityCodePageFor(String Indicator) {
        try {
            errorMessage.errorSecurityPage(Indicator);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //Password
    @And("^verify the error message displayed on Password page for \"([^\"]*)\"$")
    public void verifyTheErrorMessageDisplayedOnPasswordPageFor(String Indicator) {
        try {
            errorMessage.errorPasswordPage(Indicator);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public Map<String, String> updateTestDataConverter(Map<String, String> updatedTestData) {
        // looping over keys
        for (String name : testData.keySet()) {
            // search  for value
            String url = testData.get(name);
            if (url == null || url.equalsIgnoreCase("<Blank>")) {
                updatedTestData.replace(name, url, "");
            }
        }
        return updatedTestData;
    }


}
