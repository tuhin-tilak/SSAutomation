package com.cucumbercraft.POMPages.IBAN;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.SignInPg;
import com.cucumbercraft.framework.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.cucumbercraft.stepdefinitions.ReinvestSteps.getdata;

public class Change_IBAN {

    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private final SignInPg login;
    private static final Logger log = LogManager.getLogger(DriverFactory.class);
    private final Properties proper = new Properties();
    private final APIReusuableLibrary api = new APIReusuableLibrary();
    APIReusuableLibrary apiDriver = new APIReusuableLibrary();

    private final String string1 = "This IBAN will be saved and used for all of your future Ireland State Savings sole payments, online and offline, and for the transfer of any sole Prize Bond winnings to your bank account, if you have previously instructed us to process prize payments this way.";
    private final String string2 = "Important Note:\n" +
            "If you have previously provided IBAN details to Ireland State Savings, the IBAN you provided here will replace existing details on our records.";
    private final String string3 = "We support payment transfers to bank accounts located within the Single Euro Payments Area (SEPA) only. SEPA payments can only be processed in euro.";
    private final String string4 = "For International (non SEPA) payment transfers, please contact the Ireland State Savings team.";
    private final String string5 = "Current IBAN ending ~";
    private final String labelContent = "Enter your new IBAN i";
    private final String tooltipContent = "Your IBAN starts with two letters to denote the SEPA country where the account is based, e.g. IE for Ireland. Your IBAN is printed on your bank statement. You may also request it directly from your bank.";
    private final String text = "//*[@id='sectionConfirmChangeIban']/p[%s]";
    private final String url = "https://apim-anpost-statesavings.dev-anpost.com/ss-otp-get/api/Otp/get/";
    private String details;
    public String number1;

    private final By currentIban = By.id("p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_profileBankDetails");
    private final By IbanUpdated = By.xpath("//div[@id='personalDetails']//strong[@id='alertMessage']");
    private final By profileAndSettingTab = By.xpath("//*[text()='Profile and Settings']");
    private final By yourSavingsTab = By.xpath("//ul[@class='primary-nav__list']/li[1]/a");
    private final By headerNotificationTab = By.xpath("//*[text()='Your Personal Details']");
    private final By addNowButtonNotification = By.xpath("//*[@id='btnChangeIban']");
    private final By label = By.xpath("//*[@for='profile-settings-iban']");
    private final By tooltip = By.xpath("//button[contains(@aria-describedby,'IBAN-tooltip')]");
    private final By consentCheck = By.xpath("//*[@for='checkTermsAndCondition']");
    private final By cancel = By.xpath("//*[@class='modal_close js-closeModal js-one-time-code-close']");
    private final By confirmBankDetailsChange = By.id("btnConfirmChangeIban");
    private final By newIban = By.xpath("//*[@id='ibanText']");
    private final By close = By.xpath("//section[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_sectionChangeBankDetails']//div[@class='modal_close js-closeModal']");
    private final By currentIBAN = By.xpath("//p[@class='dashboard-modal__info-text']");
    private final By toolTip = By.xpath("//*[@class='tooltip']/button");
    private final By toolTipContent = By.xpath("//*[@id='ibanToolTip']");
    //    error validation
    private By enterValueForIBAN = By.xpath("//span[@id='RFVIbanAccount']");
    private By confirmConsent = By.xpath("//span[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_CVCheckTermsAndConditionsTwo']");
    private By invalidIBAN = By.xpath("//span[@Id='iban-custom-error']");
    //    Securtity page
    private final By securityHeader = By.xpath("//*[@id='securityCode']");
    private final By close1 = By.xpath("//div[@class='modal_close js-closeModal js-one-time-code-close']");
    private final By mobileNumberEnding = By.xpath("//*[@id='verifyCodeDescription']");
    private final By securityCodeEnter = By.xpath("//*[@id='securityCodeText']");
    private final By didNotReceiveCode = By.xpath("//a[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_lnkOpenChangeBankDetailsResendCode']");
    private final By cancel1 = By.xpath("//section[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_sectionChangeBankDetailsSecurityCode']//div//button[@class='button button--secondary button--alt js-closeModal js-one-time-code-close']");
    private final By verifyBtn = By.xpath("//*[@id='btnVerifyCode']");
    private final By changeIbanBtn = By.xpath("//*[@id='btnChangeIban']");
    private By headerChangeBankDetails;
    private By ibanToolTipContent;

    public Change_IBAN(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
        login = new SignInPg(driver);
    }


    private String number;



    /**
     * Click on the profile and setting tab
     *
     * @throws InterruptedException
     * @throws IOException
     */
    public void clickProfileAndSetting() throws InterruptedException, IOException {
        try {
            webUtil.clickLog(profileAndSettingTab, "Profile and setting tab");
//                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotFile(driver));
        } catch (Exception e) {
            throw new ExceptionUtils("Profile and setting tab is not clicked");
        }
    }

    public void clickYourSavings() throws InterruptedException, IOException {

        try {
            webUtil.clickLog(yourSavingsTab, "Your saving tab");
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotFile(driver));
        } catch (Exception e) {
            throw new ExceptionUtils("Your Savings tab is not clicked");
        }
    }


    public void clickChangeButton() throws InterruptedException {
        webUtil.clickLog(addNowButtonNotification, "Add now button");

    }

    /**
     * Verify the profile page
     */

    public void verifyProfile() throws Exception {
        webUtil.gettextlog(headerNotificationTab, String::equals, "Your Personal Details");
    }

    /**
     * Verify the content of the enter iban modal page
     *
     * @throws Exception
     */

    public void verifyContentEnterIban() throws Exception {

        webUtil.scrollToView(currentIBAN);
        String lastFourDigit = webUtil.getText(currentIBAN);
        lastFourDigit = lastFourDigit.substring(lastFourDigit.length() - 4);
        System.out.println(lastFourDigit);
        headerChangeBankDetails = By.xpath("//*[@id='sectionConfirmChangeIban']/h4");
        webUtil.gettextlog(headerChangeBankDetails, String::equals, "Change your bank details");
        webUtil.gettextlog((By.xpath(String.format(text, 1))), String::equals, string1);
        webUtil.gettextlog((By.xpath(String.format(text, 2))), String::equals, string2);
        webUtil.gettextlog((By.xpath(String.format(text, 3))), String::equals, string3);
        webUtil.gettextlog((By.xpath(String.format(text, 4))), String::equals, string4);
        webUtil.gettextlog((By.xpath(String.format(text, 5))), String::equals, string5 + lastFourDigit);
        webUtil.scrollToView(label);
        webUtil.gettextlog(label, String::equals, labelContent);
        webUtil.click(toolTip);
        webUtil.waitFor(2000);
        ibanToolTipContent = By.id("ibanToolTip");
        webUtil.gettextlog(ibanToolTipContent, String::equals, tooltipContent);
        webUtil.click(toolTip);
    }

    /**
     * Click on the confirm button of the add Iban modal
     *
     * @throws InterruptedException
     * @throws IOException
     */

    public void clickConfrmBtn() throws Exception {

        try {
            webUtil.clickLog(confirmBankDetailsChange, "confirm button");
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    /**
     * Click on cancel button of the add Iban modal
     *
     * @throws InterruptedException
     * @throws IOException
     */
    public void clickcnclBtn() throws Exception {
        webUtil.gettextlog(cancel, String::equals, "Cancel");
        webUtil.click(cancel);
        webUtil.waitForPageLoaded();
        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
    }

    /**
     * All the error for not enter ting the iban and invalid iban and consent checkbox
     *
     * @param ibanType
     * @throws Exception
     */
    public void error(String ibanType) throws Exception {
        if (ibanType.contains("Blank")) {
            if (getdata.get("Journey").contentEquals("Add_IBAN-Prompt")) {
                enterValueForIBAN = By.xpath("//span[@id='RFVIbanRequired']");
            }
            webUtil.gettextlog(enterValueForIBAN, String::equals, "Please enter a value for your IBAN.");

        } else if (ibanType.contains("Invalid")) {
//            if (getdata.get("Journey").contentEquals("Add_IBAN-Profile_And_Setting")) {
//                invalidIBAN = By.xpath("//span[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_lblIBANError']");
//            } else if(getdata.get("Journey").contentEquals("Add_IBAN-Prompt")) {
//                invalidIBAN = By.xpath("//*[@class='error-validation lblIBANError']");
//            }
            webUtil.gettextlog(invalidIBAN, String::equals, "This is not a valid IBAN. A valid IBAN consists of a two-letter country code, followed by two check digits, and up to thirty-five alphanumeric characters.");

        } else if (ibanType.contains("Non-Sepa")) {
            if (getdata.get("Journey").contentEquals("Add_IBAN-Profile_And_Setting")) {
                invalidIBAN = By.xpath("//span[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_lblIBANError']");
            } else if (getdata.get("Journey").contentEquals("Add_IBAN-Prompt")) {
                invalidIBAN = By.xpath("//*[@class='error-validation lblIBANError']");
            }
            webUtil.gettextlog(invalidIBAN, String::equals, "The IBAN entered is a not a SEPA IBAN. Only SEPA IBAN can be used for cash in from State Savings. Please contact the State Savings Team to arrange international payment");
        } else if (ibanType.contains("Consent Error")) {
            if (getdata.get("Journey").contentEquals("Add_IBAN-Prompt")) {
                confirmConsent = By.xpath("//*[@id='iban-conform-error']");
            } else if (getdata.get("Journey").contentEquals("Add_IBAN-Profile_And_Setting")) {
                confirmConsent = By.xpath("//span[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_CVCheckTermsAndConditionsOne']");
            }
            webUtil.gettextlog(confirmConsent, String::equals, "Please confirm");
        }

    }

    /**
     * User click on checkbox consent after entering the iban
     *
     * @throws InterruptedException
     */
    public void clickCheckBox() throws InterruptedException {
        try {
            webUtil.clickLog(consentCheck, "Check box");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * User enter the iban and also verify the content on the page
     *
     * @param Iban
     * @param Details
     * @throws Throwable
     */
    public void enterIban(String Iban, String Details) throws Throwable {
        try {
            webUtil.scrollToView(newIban);
            webUtil.click(newIban);
            webUtil.sendKeys(newIban, Iban);
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepLog("Iban entered: ".concat(Iban));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    public void clickCloseBtnOtpPage() throws InterruptedException {

        webUtil.clickLog(close1, "close button");

    }

    public void clickVerBtnOtpPage() {
        try {
            webUtil.clickLog(verifyBtn, "Verify button");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void clickcancelBtnOtpPage() throws Exception {
        webUtil.gettextlog(cancel1, String::equals, "Cancel");

        webUtil.clickLog(cancel1, "cancel button");
    }

    public void didntreciveCodeOtpPage() throws InterruptedException {
        webUtil.clickLog(didNotReceiveCode, "did Not Receive Code");
    }

    /**
     * verify the content on the otp page
     *
     * @throws Throwable
     */
    public void otpPageContent() throws Throwable {
//        String num = getdata.get("Number").substring(getdata.get("Number").length() - 4);
//        System.out.println(num);
        webUtil.gettextlog(securityHeader, String::equals, "Verification Code");
        webUtil.gettextlog(mobileNumberEnding, String::equals, "Enter the security code we've sent to the registered mobile phone number ending with ~");

    }

    /**
     * OTP PAGE Content verify
     *
     * @param number the number last 4 digit is needed for verifying the number
     * @throws Throwable
     */
    public void otpPageContent(String number) throws Throwable {
        String num = number.substring(number.length() - 4);
        webUtil.gettextlog(securityHeader, String::equals, "Verification Code");
        webUtil.gettextlog(mobileNumberEnding, String::equals, "Enter the verification code we've sent to the registered mobile phone number ending with ~" + num);
    }

    /**
     * User enter the Otp in text field
     *
     * @throws Exception
     */
    public void otpPageEnterOtp(String number) throws Exception {
        String otp = apiDriver.getOTP(url, 200, number);
        try {
//                webUtil.click(securityCodeEnter);
            webUtil.sendKeys(securityCodeEnter, otp);
            ExtentCucumberAdapter.addTestStepLog("OTP Entered on OTP Page: ".concat(otp));
        } catch (Exception e) {
            throw new ExceptionUtils("OTP is not entered on OTP Page");
        }
    }

    /**
     * Enetring the expired otp
     *
     * @throws Exception
     */
    public void otpPageEnterOtpExpired() throws Exception {
        webUtil.waitForPageLoaded();
        try {
            if (webUtil.isElementclickable(securityCodeEnter, 20)) {
                webUtil.click(securityCodeEnter);
                String otp = api.getOTP(url, 200, number1);
                TimeUnit.MINUTES.sleep(4);
                webUtil.sendKeys(securityCodeEnter, otp);
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    /**
     * After updating Iban the Thank message for updating the iban
     */
    public void vrfyUpdatedIban() throws Exception {

        webUtil.getText(IbanUpdated).equalsIgnoreCase("Thank you, your Bank Account details have been added to your profile");
        log.info("The updated thank you message is visible");
        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));

    }

}
