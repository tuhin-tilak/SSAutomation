package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.IBAN.Add_IBAN;
import com.cucumbercraft.POMPages.IBAN.Add_IBAN_Profile_And_Setting;
import com.cucumbercraft.POMPages.IBAN.Change_IBAN;
import com.cucumbercraft.POMPages.RepayReinvest.Holdings;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


import static com.cucumbercraft.stepdefinitions.ReinvestSteps.getdata;

public class IBANSteps extends MasterStepDefs {
    WebDriver driver = DriverManager.getWebDriver();
    Change_IBAN change = new Change_IBAN(driver);
    Add_IBAN add = new Add_IBAN(driver);
    Add_IBAN_Profile_And_Setting addiban = new Add_IBAN_Profile_And_Setting(driver);
    Holdings holdings = new Holdings(driver);
    String number1;
    String details;
    String journey;



    @When("^Verify the prompt message is on dashboard$")
    public void VerifyThePromptMessageIsOnDashboard() {
        try {
            if (getdata.get("visible").equalsIgnoreCase("yes")) {
                add.verifyCompleteHeader();
            } else ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^User click on add your bank details now link$")
    public void userClickOnAddYourBankDetailsNowLink() {

        try {
            add.addBankDetailLink();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^verify the add iban modal$")
    public void verifyTheAddIbanModal() {
        try {
            add.verifyIbanpageHeader();
            add.verifyibanPageContent();
            add.verifyIbanLabel();
            add.verifyToolTipContent();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^user Enter the \"([^\"]*)\" \"([^\"]*)\" and tick the checkbox$")
    public void userEnterTheAndTickTheCheckbox(String ibanDetail, String iban) {

        try {
            add.enterIbanField(iban);
            add.clickCheckBox();
            this.details = ibanDetail;
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^user Enter the \"([^\"]*)\" \"([^\"]*)\" and not tick the checkbox$")
    public void userEnterTheAndNotTickTheCheckbox(String ibanDetail, String iban) {
        try {
            add.enterIbanField(iban);
            this.details = ibanDetail;
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @And("^Verify the checkbox error message$")
    public void userClickOnVerifyBankDetailButtonAndVerifyTheCheckboxErrorMessage() {

        try {
            add.checkBoxErrorFun();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


    @And("^user click on cancel button$")
    public void userClickOnCancelButton() {

        try {
            add.cancelBtnClick();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on verify bank detail button$")
    public void userClickOnVerifyBankDetailButton() {
        try {
            add.cnfrmBtnClick();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @And("^verify the otp page$")
    public void verifyTheOtpPage() {
        try {
            add.otpPageContentVerify();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User navigates to profile and setting$")
    public void UserNavigatesToProfileAndSetting() {
        try {
            add.navigateToProfileSetting();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("^User click on your Savings Tab$")
    public void userClickOnYourSavingsTab() {
        try {
            change.clickYourSavings();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify the profile and setting page content$")
    public void VerifyTheProfileAndSettingPageContent() {
        try {
            add.changeNotificationHeaderVerify();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @And("^user click on the add now iban button$")
    public void userClickOnTheAddNowIbanButton() {
        try {
            add.clickAddNowBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }



    @And("^verify the error message displayed$")
    public void verifyTheErrorMessageDisplayed() {
        try {
            add.OtpError();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify the thank you prompt message displayed$")
    public void VerifyTheThankYouPromptMessageDisplayed() {
        try {
            add.thankYouPromptMessageDisplayed();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify the thank you prompt message$")
    public void VerifyTheThankYouPromptMessage() {
        try {
            add.thanksPromptMessage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^user click on the change button$")
    public void userClickOnTheChangeButton() {
        try {
            change.clickChangeButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^verify the add iban modal displayed$")
    public void verifyTheAddIbanModalDisplayed() {
        try {
            change.verifyContentEnterIban();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^user Enter the \"([^\"]*)\" \"([^\"]*)\"$")
    public void userEnterThe(String ibanDetail, String iban) throws Throwable {
        try {
            this.details = ibanDetail;
            add.iban = iban;
            change.enterIban(iban, this.details);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^tick the checkbox$")
    public void tickTheCheckbox() {
        try {
            change.clickCheckBox();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^user click on didn't receive link$")
    public void userClickOnDidnTReceiveLink() {
        try {
            add.clickDidntReciveLink();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^user clicks on cancel button$")
    public void userClicksOnCancelButton() {
        try {
            change.clickcnclBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^user clicks on otp page cancel button$")
    public void userClicksOnOtpPageCancelButton() {
        try {
            change.clickcancelBtnOtpPage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^verify the iban is not updated$")
    public void verifyTheIbanIsNotUpdated() {
        try {
            add.verifyCurrentIbanNewIban();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^Verify the error message displayed on IBAN page$")
    public void verifyTheErrorMessageDisplayedOnIBANPage() {
        try {
            change.error("Invalid");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^user clicks on the verify bank detail \"([^\"]*)\" button and verify the error message displayed$")
    public void userClicksOnTheVerifyBankDetailButtonAndVerifyTheErrorMessageDisplayed(String detailIban) {
        try {
            change.clickConfrmBtn();
            change.error(detailIban);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^verify the otp page content$")
    public void verifyTheOtpPageContent() throws Throwable {
        try {
            change.otpPageContent();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^user enters the expired otp and click on the confirm button$")
    public void userEntersTheExpiredOtpAndClickOnTheConfirmButton() {
        try {
            change.number1 = number1;
            change.otpPageEnterOtpExpired();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^user clicks on the didn't receive link$")
    public void userClicksOnTheDidnTReceiveLink() {
        try {
            change.didntreciveCodeOtpPage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify iban is displayed in your bank detail$")
    public void VerifyIbanIsDisplayedInYourBankDetail() {
        try{
        add.verifyCurrentIbanNewIban();
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on notification tab$")
    public void UserClickOnNotificationTab() {
        try {
            add.clickOnNotificationTab();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify the iban is updated$")
    public void VerifyTheIbanIsUpdated() {
        try {
            add.clickAlert();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^user enters otp on \"([^\"]*)\" and click on the confirm button$")
    public void userEntersOtpOnAndClickOnTheConfirmButton(String number) {
        try {
            change.otpPageEnterOtp(number);
            change.clickVerBtnOtpPage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^user clicks on the cancel button$")
    public void userClicksOnTheCancelButton() {
        try {
            change.clickcancelBtnOtpPage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @When("^User click on Journey$")
    public void UserClickOn()  {
        try {
            if (getdata.get("Journey").contentEquals("Add_IBAN-Prompt")) {
                this.journey = "Add_IBAN-Prompt";
                add.addBankDetailLink();
            } else {
                this.journey = getdata.get("Journey");
                add.navigateToProfileSetting();
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify the content on the page$")
    public void VerifyTheContentOnThePage() {
        try {
            if (journey.contentEquals("Add_IBAN-Prompt")) {
                add.verifyCompleteHeader();
            } else {
                add.changeNotificationHeaderVerify();
                change.clickChangeButton();
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^Verify add iban modal displayed$")
    public void VerifyAddIbanModalDisplayed(){
        try {
            if (journey.contentEquals("Add_IBAN-Prompt")) {
                addiban.contentValidation();
            } else if (journey.contentEquals("Add_IBAN-Profile_And_Setting")) {
                addiban.contentValidationChangeIban();
            } else {
                change.verifyContentEnterIban();
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User enter the iban$")
    public void UserEntersTheAnd() throws Throwable {
//        try{
        String ibanDetails = getdata.get("IbanDetails");
        String iban = getdata.get("IBAN_Number");
        if (journey.contentEquals("Add_IBAN-Prompt")) {
            add.enterIbanField(getdata.get("IBAN_Number"));
            this.details = ibanDetails;
        } else if (journey.contentEquals("Add_IBAN-Profile_And_Setting")) {
            addiban.enterIbanField(iban);
            this.details = ibanDetails;
        } else {
            this.details = ibanDetails;
            add.iban = iban;
            change.enterIban(iban, ibanDetails);
        }
//        }catch (Exception e){
//            Assert.fail(e.getMessage());
//        }
    }

    @And("^Tick the checkbox on the iban page$")
    public void TickTheCheckboxOnTheIbanPage() {
        try {
            if (journey.contentEquals("Add_IBAN-Prompt")) {
                add.clickCheckBox();
            } else if (journey.contentEquals("Add_IBAN-Profile_And_Setting")) {
                addiban.clickCheckBox();
            } else {
                change.clickCheckBox();
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on the verify bank button$")
    public void UserClickOnTheVerifyBankButton() {
        try {
            if (journey.contentEquals("Add_IBAN-Prompt")) {
                add.cnfrmBtnClick();
            } else if (journey.contentEquals("Add_IBAN-Profile_And_Setting")) {
                addiban.cnfrmBtnClick();
            } else {
                change.clickConfrmBtn();
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Then("^User enters the otp received$")
    public void UserEntersTheOtpRecivedOn()  {
        try {
            if (journey.contentEquals("Add_IBAN-Prompt")) {
                add.enterOtp(getdata.get("Number"));
            } else if (journey.contentEquals("Add_IBAN-Profile_And_Setting")) {
                addiban.enterOtp(getdata.get("Number"));
            } else {
                change.otpPageEnterOtp(getdata.get("Number"));
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on confirm button$")
    public void UserClickOnConfirmButton() {
        try {
            if (journey.contentEquals("Add_IBAN-Prompt")) {
                add.clickCnfrmOtp();
            } else if (journey.contentEquals("Add_IBAN-Profile_And_Setting")) {
                addiban.clickCnfrmOtp();
            } else {
                change.clickVerBtnOtpPage();
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User clicks on otp cancel button$")
    public void UserClicksOnOtpCancelButton() {
        try {
            if (journey.contentEquals("Add_IBAN-Prompt")) {
                add.clickCancelOtp();
            } else if (journey.contentEquals("Add_IBAN-Profile_And_Setting")) {
                addiban.clickCancelOtp();
            } else {
                change.clickcancelBtnOtpPage();
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User clicks on didn't receive link$")
    public void UserClicksOnDidnTReceiveLink() {
        try {
            if (journey.contentEquals("Add_IBAN-Prompt")) {
                add.clickDidntReciveLink();
            } else if (journey.contentEquals("Add_IBAN-Profile_And_Setting")) {
                addiban.clickDidntReciveLink();
            } else {
                change.didntreciveCodeOtpPage();
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify error message displayed$")
    public void VerifyErrorMessageDisplayed() {
        try {
            if (journey.contentEquals("Add_IBAN-Prompt")) {
                if (this.details.contains("Invalid") || this.details.contains("No Iban"))
                    add.IbanError(this.details);
            } else if (journey.contentEquals("Add_IBAN-Profile_And_Setting")) {
                if (this.details.contains("Invalid") || this.details.contains("No Iban")) {
                    addiban.IbanError(this.details);
                }
            } else {
                change.error(this.details);
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify the otp page content$")
    public void VerifyTheOtpPageContents() throws Throwable {
        try {
            if (journey.contentEquals("Add_IBAN-Prompt")) {
                add.otpPageContentVerify();
            } else if (journey.contentEquals("Add_IBAN-Profile_And_Setting")) {
                addiban.otpPageContentVerify(getdata.get("Number"));
            } else {
                change.otpPageContent(getdata.get("Number"));
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on the cancel button$")
    public void UserClickOnTheCancelButton() {
        try {
            if (journey.contentEquals("Add_IBAN-Prompt")) {
                add.cancelBtnClick();
            } else if (journey.contentEquals("Add_IBAN-Profile_And_Setting")) {
                addiban.cancelBtnClick();
            } else {
                change.clickcnclBtn();
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify error message is displayed$")
    public void VerifyErrorMessageIsDisplayed() {
        try {
            add.OtpError();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User enters the expired otp and click on confirm button$")
    public void UserEntersTheExpiredOtpAndClickOnConfirmButton() {

        try {
            if (journey.contentEquals("Add_IBAN-Prompt")) {
                add.enterExpiredOtp(getdata.get("Number"));
                add.clickCnfrmOtp();
            } else if (journey.contentEquals("Add_IBAN-Profile_And_Setting")) {
                addiban.enterExpiredOtp(this.number1);
                addiban.clickCnfrmOtp();
            } else {
                change.otpPageEnterOtpExpired();
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User selects the Option$")
    public void userSelectsTheOption() {
        try {
            holdings.selectOption(getdata.get("MethodType"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
