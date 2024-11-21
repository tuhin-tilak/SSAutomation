package com.cucumbercraft.POMPages.IBAN;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.framework.*;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.cucumbercraft.stepdefinitions.ReinvestSteps.getdata;
@Log4j2
public class Add_IBAN {



    static boolean Add_Iban_flag = false;
    public final String url = Settings.getInstance().getProperty("OTPEndPoint");
    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private final Change_IBAN change = new Change_IBAN(DriverManager.getWebDriver());
    private final APIReusuableLibrary api = new APIReusuableLibrary();
    private final By thankYouMessage = By.xpath("//strong[@id='alertMessage']");
    private final By thankYouMessagePrompt = By.xpath("//div[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_pnlIBANSuccess']//strong");
    private final By completeLabel = By.id("ibanNotificationBox");
    private final By addLink = By.id("addBankDetailText");
    private final By closeBtnAddLink = By.xpath("//div[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M40DashboardCards_ibanNotificationBox']/div[2]");
    //Add Iban page
    private final By content = By.xpath("//section[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M40DashboardCards_addIbanModal']/h4");
    private final String con = "Add your bank details to your State Savings Online";
    private final String content1 = "//section[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M40DashboardCards_addIbanModal']/p[%d]";
    private final String string = "With State Savings Online, you can cash in your sole holdings and request that repayment is transferred to your personal bank account. In order to do this, you need to provide us with your International Bank Account Number (IBAN). Your IBAN is printed on your bank statement. You can also request it directly from your bank. NOTE: This IBAN will be associated with all of your State Savings Online products including Prize Bonds";
    private final String string1 = "Please note: We support payment transfers to bank accounts located within the Single Euro Payments Area (SEPA) only. SEPA payments can only be processed in euro.";
    private final String string2 = "For International (Non SEPA) payment transfers, please contact the State Savings team.";
    private final By ibanLabel = By.xpath("//label[@for='profile-settings-iban']");
    private final String Ibanlevel = "IBAN number";
    private final By enterIban = By.id("ibanText");
    private final By checkBox = By.xpath("//input[@id='checkTermsAndCondition']/following-sibling::label");
    private final String checkBoxContent = "I confirm this is a bank account in my name";
    private final By cancelBtn = By.xpath("//div[@class='dashboard-modal-btn--container dashboard-modal-btn--container-margin-medium']//button[@class='gtm-cta button button--secondary button--alt js-closeModal'][normalize-space()='Cancel']");
    private final By currentIban = By.xpath("//*[@id='profileBankDetails']");
    private final By cnfrmBtn = By.id("btnConfirmChangeIban");
    private final By changeCnfrmBtn = By.id("p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_btnChangeBankDetails");
    private final String cancelString = "Cancel";
    private final String cnfrmString = "Verify bank details";
    private final By enterOtpLabel = By.id("securityCodeTxt");
    private final By enterOtpField = By.id("securityCodeText");
    private final By didntReciveCode = By.xpath("//*[@class='gtm-linkclick']");
    private final By otpCancelBtn = By.xpath("//section[@data-name='one-time-security-code-pin-request-modal']//div//button[@onclick='modals.closeModal()']");
    private final By otpConfirmBtn = By.id("btnVerifyCode");
    //Otp page Error
    private final By noOtp = By.xpath("//*[@id='bank-security-pin']//span[1]");
    private final String noOtpContent = "Please enter your security code.";
    private final By wrongOtp = By.xpath("//*[@class='error-validation lblErrorResponse']");
    private final String wrongOtpContent = "Security code is invalid or has expired. Please try again.";
    private final By lessOtpLenght = By.xpath("//*[@id='bank-security-pin']//span[2]");
    private final String lessOtpLengthContent = "Please enter the six-digit security code sent to your registered mobile number.";
    //Iban Page Error
    private final By checkBoxError = By.xpath("//span[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M40DashboardCards_CVCheckTermsAndConditions']");
    private final String checkBoxErrorContent = "Please confirm";
    private final By noIbanError = By.xpath("//span[@id='RFVIbanRequired']");
    private final String noIbanErrorContent = "Please enter a value for your IBAN.";
    private final By invalidIbanError = By.xpath("//span[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M40DashboardCards_lblIBANError']");
    private final String invalidErrorContent = "This is not a valid IBAN.A valid IBAN consists of a two-letter country code,followed by two check digits,and up to thirty-five alphanumeric characters.";
    private final By notificationTabNotification = By.xpath("//a[@class='gtm-nav header__nav-link header__nav-link--dashboard' and @title='Notifications']");
    private final By selectNotification = By.xpath("//*[@id='notificationTypeFilter']");
    private final By listNotification = By.xpath("//*[@class='m41-notifications__item m41-notifications__item--alert m41-notifications__item--read js-notification']");
    private final By ibanUpdateMessage = By.xpath("//body//div//div//section//div//div//div//div//div//div//div//div//p");
    private final By showBtn = By.xpath("//div[@id='notification_'][1]/.//button[@aria-expanded='false']");
    public String iban;
    APIReusuableLibrary apiDriver = new APIReusuableLibrary();
    Properties properties = Settings.getInstance();
    private String number;
    private By tooltip = By.xpath("//label[@for='profile-settings-iban']/span");
    private String tooltipContent = "Your IBAN starts with two letters to denote the SEPA country where the account is based, e.g. IE for Ireland. Your IBAN is printed on your bank statement. You may also request it directly from your bank.";
    //Otp Page
    private By otpPageHeader = By.id("securityCode");
    private By numberEnding = By.id("verifyCodeDescription");
    private final String enterOtpLabelContent = "Enter Verification Code";

    public Add_IBAN(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);

    }



    /**
     * Verify the header of notification page
     */
    public void changeNotificationHeaderVerify() throws Exception {
        change.verifyProfile();
    }

    /**
     * Click on profile and setting tab
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public void navigateToProfileSetting() throws IOException, InterruptedException {
        change.clickProfileAndSetting();
    }

    /**
     * click add now btn from profile and setting
     *
     * @throws InterruptedException
     */
    public void clickAddNowBtn() throws InterruptedException {
        change.clickChangeButton();
    }


    /**
     * Click on the add now bank details link
     */
    public void addBankDetailLink() {


        webUtil.click(addLink);

    }

    /**
     * This function will help in closing the prompt message from the dashboard for add now bank details
     */
    public void closePromptMessage() {
        try {
            webUtil.click(closeBtnAddLink);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Function will help in verify the iban page header on add page/modal
     */
    public void verifyIbanpageHeader() {
        try {
            webUtil.gettextlog(content, String::equals, con);

        } catch (Exception e) {
            log.info("Header is not verified on the iban page");
        }
    }

    /**
     * Function will help in verify the iban page content on add page/modal
     */
    public void verifyibanPageContent() {
        webUtil.gettextlog(By.xpath(String.format(content1, 1)), String::equals, string);
        webUtil.gettextlog(By.xpath(String.format(content1, 2)), String::equals, string1);
        webUtil.gettextlog(By.xpath(String.format(content1, 3)), String::equals, string2);
    }

    /**
     * Verify the label above the enter iban field on add page/modal
     *
     * @throws Exception
     */
    public void verifyIbanLabel() throws Exception {
        webUtil.gettextlog(ibanLabel, String::equals, Ibanlevel);
    }

    /**
     * Tooltip content verify on add page/modal
     *
     * @throws Exception
     */
    public void verifyToolTipContent() {
        By buttonTooltip = By.xpath("//span[@id='IBAN-tooltip']/preceding-sibling::button");

        if (getdata.get("Journey").contentEquals("Add_IBAN-Prompt")) {
            tooltip = By.id("ibanToolTip");
            tooltipContent = "Your IBAN starts with two letters to denote the SEPA country where the account is based, e.g. IE for Ireland. Your IBAN is printed on your bank statement. You may also request it directly from your bank.";
        }
        webUtil.click(buttonTooltip);
        webUtil.gettextlog(tooltip, String::equals, tooltipContent);
        log.info("ToolTip content is verified");
        webUtil.click(buttonTooltip);

    }

    /**
     * Enter the iban in the iban field on add page/modal
     *
     * @param IBAN the iban which will be updated
     * @throws Exception
     */
    public void enterIbanField(String IBAN) throws Exception {
        this.iban = IBAN;
        webUtil.scrollToView(enterIban);
//                webUtil.click(enterIban);
        webUtil.sendKeys(enterIban, IBAN);
        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
        ExtentCucumberAdapter.addTestStepLog("Iban entered: " + IBAN);
        log.info("Iban is entered");

    }

    /**
     * Click on checkbox add iban page/modal
     *
     * @throws Exception
     */
    public void clickCheckBox() throws Exception {

        webUtil.gettextlog(checkBox, String::equals, checkBoxContent);
        webUtil.scrollToView(checkBox);
        webUtil.click(checkBox);

    }

    /**
     * Click on cancel button add iban page/modal
     */
    public void cancelBtnClick() {
        webUtil.waitFor(2000);
        webUtil.scrollToView(cancelBtn);
        webUtil.click(cancelBtn);
    }

    /**
     * click on confrm button add iban page/modal
     */
    public void cnfrmBtnClick() {
        webUtil.gettextlog(cnfrmBtn, String::equals, "Verify bank details");
        webUtil.click(cnfrmBtn);

    }

    /**
     * Verify the content present on the otp page
     */
    public void otpPageContentVerify() {
        if (getdata.get("Journey").contentEquals("Change_IBAN-Profile_And_Setting")) {
            otpPageHeader = By.xpath("//section[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_oneTimeSecurityCodePinModal']/h4");
            numberEnding = By.xpath("//p[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_modalResetPasswordSecurityForResendOTPDescriptionFirstTime']");
        }
        webUtil.gettextlog(otpPageHeader, String::equals, "Verification Code");
        String num = getdata.get("Number").substring(getdata.get("Number").length() - 4);
        webUtil.gettextlog(numberEnding, String::equals, "Enter the verification code we've sent to the registered mobile phone number ending with ~" + num);
        webUtil.gettextlog(enterOtpLabel, String::equals, enterOtpLabelContent);

    }

    /**
     * Errors after entering wrong/blank otp
     */
    public void OtpError() throws Exception {

        if (webUtil.isElementDisplayed(noOtp, 20)) {
            webUtil.gettextlog(noOtp, String::equals, noOtpContent);
        } else if (webUtil.isElementDisplayed(wrongOtp, 20)) {
            webUtil.gettextlog(wrongOtp, String::equals, wrongOtpContent);
        } else if (webUtil.isElementDisplayed(lessOtpLenght, 20)) {
            webUtil.gettextlog(lessOtpLenght, String::equals, lessOtpLengthContent);
        }
    }

    /**
     * click on the didn't receive link present on the OTP page
     */
    public void clickDidntReciveLink() throws Exception {
        webUtil.gettextlog(didntReciveCode, String::equals, "Didn't receive the code?");
        if (webUtil.isElementclickable(didntReciveCode, 10)) {
            webUtil.click(didntReciveCode);
        }
    }

    /**
     * click on the confirm button of OTP Page
     */
    public void clickCnfrmOtp() {
        webUtil.gettextlog(otpConfirmBtn, String::equals, "Confirm");
        webUtil.click(otpConfirmBtn);
        log.info("OTP Page confirm button is clicked");
        ExtentCucumberAdapter.addTestStepLog("OTP Page confirm button is clicked");

    }

    /**
     * click on the cancel button of OTP Page
     */
    public void clickCancelOtp() {
        webUtil.gettextlog(otpCancelBtn, String::equals, "Cancel");
        webUtil.click(otpCancelBtn);
    }

    /**
     * Enter the expired otp in the text field
     */
    public void enterExpiredOtp(String number) {
        try {
            String otp = apiDriver.getOTP(url, 200, number);
            System.out.println(otp);
            TimeUnit.MINUTES.sleep(4);
            if (webUtil.isElementclickable(enterOtpField, 20)) {
                webUtil.click(enterOtpField);
                webUtil.sendKeys(enterOtpField, otp);
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * if the number is already provided in the enter otp
     *
     * @param number
     * @throws Exception
     */
    public void enterOtp(String number) throws Exception {
        String otp = api.getOTP(properties.getProperty("OTPEndPoint"), 200, number);

//                webUtil.click(enterOtpField);
        webUtil.sendKeys(enterOtpField, otp);
        ExtentCucumberAdapter.addTestStepLog("OTP entered on OTP page: ".concat(otp));

    }

    /**
     * Verify the header of the add now bank details link
     */
    public void verifyCompleteHeader() throws Exception {


    }

    /**
     * Verify the error when checkbox is not clicked
     */
    public void checkBoxErrorFun() {
        webUtil.gettextlog(checkBoxError, String::equals, checkBoxErrorContent);
    }

    /**
     * Verify the error when the iban is Blank/Invalid
     *
     * @param details whether the provided iban is blank or invalid
     * @throws Exception
     */
    public void IbanError(String details) throws Exception {

        if (webUtil.isElementDisplayed(noIbanError, 20)) {
            webUtil.gettextlog(noIbanError, String::equals, noIbanErrorContent);
        } else if (webUtil.isElementDisplayed(invalidIbanError, 20)) {
            webUtil.gettextlog(invalidIbanError, String::equals, invalidErrorContent);
        }
    }

    public void thankYouPromptMessageDisplayed() {
        webUtil.gettextlog(thankYouMessagePrompt, String::equals, "Thank you, your Bank Account details have " + "been added to your profile");
        log.info("Prompt thank you message content is verified");
        Add_Iban_flag = true;
        ExtentCucumberAdapter.addTestStepLog("Prompt thank you message content is verified");
    }

    public void thanksPromptMessage() {
        webUtil.gettextlog(thankYouMessage, String::equals, "Thank you, your Bank Account details have " + "been added to your profile");
    }

    public void verifyCurrentIbanNewIban() {
        String iban = getdata.get("IBAN_Number");
        webUtil.scrollToView(currentIban);
        webUtil.waitFor(5000);
        String currentiban = webUtil.getText(currentIban);
        String newiban = iban.substring(iban.length() - 4);
        currentiban = currentiban.substring(currentiban.length() - 4);

        System.out.println(currentiban + " new Iban" + newiban);
        if (!newiban.contains(currentiban)) {
            throw new ExceptionUtils("New Iban is not updated");
        }
    }

    public void clickOnNotificationTab() {

        try {
            webUtil.click(notificationTabNotification);
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Notification tab is clicked");
//                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotFile(driver));
        } catch (Exception e) {
            throw new ExceptionUtils("Notification tab is not clicked");
        }
    }

    public void clickAlert() {
        webUtil.selectDropDown(selectNotification, select -> select.selectByVisibleText("Account Notifications"));
        if (webUtil.getText(selectNotification).contains("Account Notifications")) {


            webUtil.click(showBtn);
            webUtil.waitUntilElementVisible(ibanUpdateMessage, 20);
            webUtil.gettextlog(ibanUpdateMessage, String::equals, "Your IBAN has been updated");

        } else {
            throw new ExceptionUtils("Notification Alert X-path not found!");
        }
    }


}
