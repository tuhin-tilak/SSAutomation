package com.cucumbercraft.POMPages.IBAN;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.framework.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.cucumbercraft.stepdefinitions.ReinvestSteps.getdata;

public class Add_IBAN_Profile_And_Setting {
    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private static final Logger log = LogManager.getLogger(DriverFactory.class);
    private final APIReusuableLibrary api = new APIReusuableLibrary();
    Properties properties = Settings.getInstance();
    private final String url = "https://anpostssqa.servicebus.windows.net/myaccount-qa/api/otp/get/";

    private By header = By.xpath("//section[@id='sectionConfirmChangeIban']/h4");
    private final By changeJourneyHeader = By.xpath("//div[@class='medium-10 columns form-element']/h4");
    private final String str = "Add your bank details";
    private final String str1 = "This IBAN will be saved and used for all of your future Ireland State Savings sole payments (online and/or offline) and the transfer of any sole Prize Bond winnings to your bank account, if you have previously instructed us to process prize payments in this way.";
    private final String str2 = "Important Note: If you have previously provided IBAN details to Ireland State Savings, the IBAN you provided here will replace existing details on our records.";
    private final String str3 = "With Ireland State Savings Online, you may cash in your sole holdings, and request that repayment is transferred to your personal bank account. In order to do this, you need to provide your International Bank Account Number (IBAN).";
    private final String str4 = "We support payment transfers to bank accounts located within the Single Euro Payments Area (SEPA) only. SEPA payments can only be processed in euro.";
    private final String str5 = "For International (non SEPA) payment transfers, please contact the Ireland State Savings team.";
    private String cont1 = "//section[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M40DashboardCards_addIbanModal']/p[%d]";
    private By label = By.xpath("//label[for='profile-settings-iban']");
    private final String labelString = "IBAN i";
    private final By ibanEnter = By.xpath("//*[@placeholder='eg IE55PSTS99030112345678']");
    private final By checkBoxlabel = By.xpath("//*[@for='checkTermsAndCondition']");
    private final By cancelBtn = By.xpath("//input[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_cancelAddBankDetails']");
    private final By confirmBtn = By.xpath("//*[@id='btnConfirmChangeIban']");
    private final By errorMessageInvalidIban = By.xpath("//span[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_lblIBANError']");
    //Otp Page
    private final By otpPageHeader = By.xpath("//div[@class='modal_close js-closeModal js-one-time-code-close']/following::h4[1]");
    private final By numberEnding = By.id("p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_modalResetPasswordSecurityForResendOTPDescriptionFirstTime");
    private final By enterOtpHeader = By.xpath("//label[@for='txtOneTimeSecurityCodePin']");
    private final String enterOtpHeaderContent = "Enter Security Code";
    private final By enterOtpField = By.xpath("//input[@id='securityCodeText']");
    private final By didntReciveCode = By.xpath("//*[@onclick='ShowResendCode()']");
    private final By otpCancelBtn = By.xpath("//input[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_btnVerifySecurityCodePin']/preceding-sibling::button");
    private final By otpConfirmBtn = By.xpath("//*[@id='btnVerifyCode']");
    private final By noOtp = By.xpath("//span[@id='RFVOneTimeCodePin']");
    private final String noOtpContent = "Please enter the onetime SMS code sent to your registered mobile number";
    private final By wrongOtp = By.xpath("//span[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_lblOTPCheckError']");
    private final String wrongOtpContent = "Security code is invalid or has expired. Please try again.";
    private String number;
    private final By prizeBondSettings = By.xpath("//a[@id=\"p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_PBLink\"]");
    private final By pageheader = By.id("p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_prizeBondsTitle");


    public Add_IBAN_Profile_And_Setting(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    /**
     * Function will help in verify the iban page header on add page/modal
     */
    public void verifyIbanpageHeader() throws Exception {
        if (getdata.get("Journey").contentEquals("Add_IBAN-Prompt")) {
            webUtil.gettextlog(header, String::equals, str);

        } else if (getdata.get("Journey").contentEquals("Add_IBAN-Profile_And_Setting")) {
            header = By.xpath("//section[@id='sectionConfirmChangeIban']/h4");
            webUtil.gettextlog(header, String::equals, str);
        } else {
            webUtil.gettextlog(changeJourneyHeader, String::equals, str);
        }
    }


    /**
     * Function will help in verify the iban page content on add page/modal
     */
    public void verifyibanPageContent() throws Exception {
        if (getdata.get("Journey").contentEquals("Add_IBAN-Prompt")) {
            cont1 = "//section[@id='sectionConfirmChangeIban']/p[%d]";
        }
        if (getdata.get("Journey").contentEquals("Add_IBAN-Profile_And_Setting")) {
            cont1 = "//section[@id='sectionConfirmChangeIban']/p[%d]";
        }
        webUtil.gettextlog(By.xpath(String.format(cont1, 1)), String::equals, str1);
        webUtil.gettextlog(By.xpath(String.format(cont1, 2)), String::equals, str2);
        webUtil.gettextlog(By.xpath(String.format(cont1, 3)), String::equals, str3);
        webUtil.gettextlog(By.xpath(String.format(cont1, 4)), String::equals, str4);
        webUtil.gettextlog(By.xpath(String.format(cont1, 5)), String::equals, str5);

    }

    /**
     * Verify the label above the enter iban field on add page/modal
     *
     * @throws Exception
     */
    public void verifyIbanLabel() throws Exception {
        if (getdata.get("Journey").contentEquals("Add_IBAN-Prompt")) {
            label = By.xpath("//input[@id='ibanText']/preceding-sibling::label");
        }
        if (getdata.get("Journey").contentEquals("Add_IBAN-Profile_And_Setting")) {
            label = By.xpath("//input[@id='ibanText']/preceding-sibling::label");
        }
        webUtil.gettextlog(label, String::equals, labelString);
    }

    /**
     * Enter the iban in the iban field on add page/modal
     *
     * @param IBAN the iban which will be updated
     * @throws Exception
     */
    public void enterIbanField(String IBAN) throws Exception {
        webUtil.scrollToView(ibanEnter);
        webUtil.waitFor(3000);
        webUtil.click(ibanEnter);
        webUtil.sendKeys(ibanEnter, IBAN);
        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
    }

    /**
     * Click on checkbox add iban page/modal
     *
     * @throws Exception
     */
    public void clickCheckBox() throws Exception {
        webUtil.gettextlog(checkBoxlabel, String::equals, "I confirm this is a bank account in my name.");
        webUtil.click(checkBoxlabel);
        log.info("Add Iban page check box is clicked");
    }

    public void contentValidationChangeIban() throws Exception {
        verifyIbanpageHeader();
        verifyibanPageContent();
        verifyIbanLabel();
        Add_IBAN add = new Add_IBAN(driver);
        add.verifyToolTipContent();
    }

    public void contentValidation() throws Exception {
        try {
            verifyIbanpageHeader();
            verifyibanPageContent();
            verifyIbanLabel();
            Add_IBAN add = new Add_IBAN(driver);
            add.verifyToolTipContent();
            log.info("Content on change iban page is verified");
        } catch (ExceptionUtils e) {
            throw new ExceptionUtils("Change Iban content is changed:" + e.getMessage());
        }
    }

    /**
     * Click on cancel button add iban page/modal
     *
     * @throws Exception
     */
    public void cancelBtnClick() throws Exception {
        webUtil.gettextlog(cancelBtn, String::equals, "Cancel");
        webUtil.click(cancelBtn);
    }

    /**
     * click on confrm button add iban page/modal
     *
     * @throws Exception
     */
    public void cnfrmBtnClick() throws Exception {
        webUtil.click(confirmBtn);
    }

    /**
     * Verify the error when the iban is Blank/Invalid
     *
     * @param details whether the provided iban is blank or invalid
     * @throws Exception
     */
    public void IbanError(String details) throws Exception {

        if (details.equalsIgnoreCase("Invalid") || details.contentEquals("No Iban")) {
            webUtil.gettextlog(errorMessageInvalidIban, String::equals, "The Account Number entered is not a valid IBAN.");
        }
    }

    /**
     * Verify the content present on the otp page
     *
     * @throws Exception
     */
    public void otpPageContentVerify(String number) throws Exception {
        this.number = number;
        webUtil.gettextlog(otpPageHeader, String::equals, "Security Code");
        webUtil.gettextlog(enterOtpHeader, String::equals, enterOtpHeaderContent);
    }

    /**
     * Errors after entering wrong/blank otp
     *
     * @param details whether the otp is blank or wrong
     * @throws Exception
     */
    public void OtpError(String details) throws Exception {
        if (details.contains("NoOTP")) {
            if (webUtil.isElementDisplayed(noOtp, 20)) {
                if (webUtil.getText(noOtp).equalsIgnoreCase(noOtpContent)) {
                    log.info("Otp is not entered content is same");
                } else {
                    log.info("Otp is not entered content is not same");
                }
            }
        } else if (details.contains("Invalid")) {
            if (webUtil.isElementDisplayed(wrongOtp, 20)) {
                if (webUtil.getText(wrongOtp).equalsIgnoreCase(wrongOtpContent)) {
                    log.info("Wrong otp content is similar");
                } else {
                    log.info("Wrong otp content is not similar");
                }
            }
        }
    }

    /**
     * click on the didn't receive link present on the OTP page
     *
     * @throws Exception
     */
    public void clickDidntReciveLink() throws Exception {
        webUtil.gettextlog(didntReciveCode, String::equals, "Didn't receive the code?");
        webUtil.click(didntReciveCode);
    }

    /**
     * click on the confirm button of OTP Page
     *
     * @throws Exception
     */
    public void clickCnfrmOtp() throws Exception {
        webUtil.gettextlog(otpConfirmBtn, String::equals, "Confirm");
        webUtil.click(otpConfirmBtn);
    }

    /**
     * click on the cancel button of OTP Page
     *
     * @throws Exception
     */
    public void clickCancelOtp() throws Exception {
        webUtil.gettextlog(otpCancelBtn, String::equals, "Cancel");
        webUtil.click(otpCancelBtn);
    }

    /**
     * Enter the expired otp in the text field
     *
     * @throws Exception
     */
    public void enterExpiredOtp(String number) throws Exception {
        String otp = api.getOTP(url, 200, number);
        TimeUnit.MINUTES.sleep(4);
        if (webUtil.isElementclickable(enterOtpField, 20)) {
            webUtil.click(enterOtpField);
            webUtil.sendKeys(enterOtpField, otp);
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
        try {
            webUtil.click(enterOtpField);
            webUtil.sendKeys(enterOtpField, otp);
            ExtentCucumberAdapter.addTestStepLog("OTP entered on OTP page: ".concat(otp));
        } catch (Exception e) {
            throw new ExceptionUtils("OTP is not entered on OTP Page");
        }
    }


}

