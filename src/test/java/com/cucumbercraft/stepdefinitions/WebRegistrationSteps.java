package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.POMPages.ExpeditedReg.ExpeditedModals;
import com.cucumbercraft.POMPages.WebReg.*;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.WebDriverUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class WebRegistrationSteps extends MasterStepDefs {
    private final WebDriver driver = DriverManager.getWebDriver();
    private final WebDriverUtil webUtil = new WebDriverUtil(driver);

    WeMatchDetails matchDetials = new WeMatchDetails(driver);
    CheckDetailsAndContin checkDetails = new CheckDetailsAndContin(driver);
    ConfirmMobileNumber confirmMobile = new ConfirmMobileNumber(driver);
    WebSecurityCode securityCode = new WebSecurityCode(driver);
    Web_Check_Your_Inbox checkInbox = new Web_Check_Your_Inbox(driver);
    Web_CreatePassword createPassword = new Web_CreatePassword(driver);
    ExpeditedModals modals = new ExpeditedModals(driver);
    Postal_Address_Page postal = new Postal_Address_Page(driver);


    //Pin Expired modal
    @And("^Click on Continue button of Pin No Longer Valid modal$")
    public void clickOnContinueButtonOfPinNoLongerValidModal() {
        try {
            modals.clickContinueBtnModal();
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @And("^Click on Back to SSA button of Pin No Longer Valid modal$")
    public void clickOnBacktoSSAButtonOfPinNoLongerValidModal() {
        try {
            modals.clickBackToSSABtnModal();
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }


    //we matched your detials page or Postal address page
    @Then("^Enter all address details for Web Register user$")
    public void enterAllAddressDetailsForWebRegisterUser() {
        try {
            matchDetials.selectCountry();
            matchDetials.enterAddressLine1();
            matchDetials.enterAddressLine2();
            matchDetials.enterTown();
            matchDetials.selectCountry_Region_State();
            matchDetials.enterEirCode();
            matchDetials.enterEmailAddress();
//            matchDetials.selectNumberPrefix();
            matchDetials.enterNumber();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on back button of We Matched your details page$")
    public void clickOnBackButtonOfWeMatchedYourDetailsPage() {
        try {
            matchDetials.clickBackBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on next button of We Matched your details page$")
    public void clickOnNextButtonOfWeMatchedYourDetailsPage() {
        try {
            matchDetials.clickNextBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //-------buttons of Postal address page-------
    @And("^Click on back button of Postal address page$")
    public void clickOnBackButtonOfPostalAddressPage() {
        try {
            postal.clickBackbtn();
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @And("^Click on next button of Postal address page$")
    public void clickOnNextButtonOfPostalAddressPage() {
        try {
            postal.clickNextBtn();
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    //Check details and continue page start
    @And("^Verify details of Check Details and continue page$")
    public void verifyDetailsOfCheckDetailsAndContinuePage() {
        try {
            checkDetails.verifyAddressLine1();
            checkDetails.verifyAddressLine2();
            checkDetails.verifyCity();
            checkDetails.verifyCountry();
            checkDetails.verifyEirCode();
            checkDetails.verifyEmail();
            checkDetails.verifyNumber();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on consent of check Details and continue page$")
    public void clickOnConsentOfCheckDetailsAndContinuePage() {
        try {
            checkDetails.clickConsent();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^click on edit details of check Details and continue page$")
    public void clickOnEditDetailsOfCheckDetailsAndContinuePage() {
        try {
            checkDetails.clickEditDetails();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^click on next button of check Details and continue page$")
    public void clickOnNextButtonOfCheckDetailsAndContinuePage() {
        try {
            checkDetails.clickNextBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^click on edit details of confirm your mobile phone page$")
    public void clickOnEditDetailsOfConfirmYourMobilePhonePage() {
        try {
            confirmMobile.clickEditDetailsBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @And("^click on send code of confirm your mobile phone page$")
    public void clickOnSendCodeOfConfirmYourMobilePhonePage() {
        try {
            confirmMobile.clickSendCodeBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //  Security code page
    @And("^Enter the security code for web Registration$")
    public void enterTheSecurityCodeForWebRegistration() {
        try {
            securityCode.enterSecuritycode();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @And("^Click on Confirm button of security code for web Registration$")
    public void clickOnConfirmButtonOfSecurityCodeForWebRegistration() {
        try {
            securityCode.clickConfirmBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //Did not get security code page
    @And("^Click on did not receive code link$")
    public void clickOnDidNotReceiveCodeLink() {
        try {
            securityCode.clickDidntLink();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @And("^click on cancel button of did'nt get the security code page$")
    public void clickOnCancelButtonOfDidNtGetTheSecurityCodePage() {
        try {
            securityCode.clickPopCancelBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^click on Resend code button of did'nt get the security code page$")
    public void clickOnResendCodeButtonOfDidNtGetTheSecurityCodePage() {
        try {
            securityCode.clickpopResendCodeBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^verify the check your inbox page$")
    public void verifyTheCheckYourInboxPage() {
        try {
            checkInbox.verifyContent();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^click on didn't receive email link$")
    public void clickOnDidnTReceiveEmailLink() {
        try {
            checkInbox.clickDidNotEmailLink();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on close button of Didn't get an email page$")
    public void clickOnCloseButtonOfDidnTGetAnEmailPage() {
        try {
            checkInbox.clickCloseBtnPopUp();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on Resend Email button of Didn't get an email page$")
    public void clickOnResendEmailButtonOfDidnTGetAnEmailPage() {
        try {
            checkInbox.clickResendBtnPopUp();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^verify Email address Verified page$")
    public void verifyEmailAddressVerifiedPage() {
        try {
            createPassword.verifyCreatePassword();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Enter password for web Registration$")
    public void enterPasswordForWebRegistration() {
        try {
            createPassword.enterPassword();
            createPassword.clickShowPassword();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on Confirm button of Email address Verified page$")
    public void clickOnConfirmButtonOfEmailAddressVerifiedPage() {
        try {
            createPassword.clickCompleteBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify what happens next page displayed$")
    public void verifyWhatHappensNextPageDisplayed() {
        try {
            createPassword.VerifyCompWebRegHeader();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


}
