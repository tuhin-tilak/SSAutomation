package com.cucumbercraft.POMPages;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.framework.*;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Properties;

import static com.cucumbercraft.stepdefinitions.ReinvestSteps.getdata;

@Log4j2
public class Security_Page {

    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private final APIReusuableLibrary api = new APIReusuableLibrary();
    Properties prop = Settings.getInstance();

    private final String msgVerify = "Enter the security code we've sent to phone number ending with ";
    private final String otpErrorMsg = "Please enter your verification code.";
    private final String otpErrorMsg1 = "Verification code is invalid or has expired. Please try again.";

    private final By secHeader = By.xpath("//h2[contains(text(),'Veri')]");
    private final By verifyNumber = By.xpath("//div[@class='product-security-form']/p");
    private final By enterOTPLabel = By.xpath("//label[@class='form-label']");
    private final By enterOtp = By.id("txtPin");
    private final By didnotLink = By.xpath("//button[@class='link-blue field-link js-modalTrigger gtm-linkclick' and contains(@data-modal,'resend-security-code') ]");
    private final By cnfmButton = By.xpath("//*[text()='Confirm']");
    private final By cancelBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Security_btnSecuritySubmit']//preceding-sibling::button");
    private final By error = By.xpath("//span[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Security_RFVtxtPIN']");
    private final By lessThansixDigit = By.xpath("//span[@id='CFVtxtPin']");
    private final By wrongError = By.id("p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Security_lblResponse");
    private final By numberXpath = By.xpath("//strong[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Security_lblPhone']");

    public By otp = By.id("securityCodeForm");
    public By confirmbtn = By.xpath("//input[@aria-label='Confirm']");
    public By val2 = By.xpath("//form[@id='verify-form']/../h4");


    public Security_Page(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);

    }

    /**
     * Verify the security page header
     */

    public void vrfySecurityPage() throws Exception {
        if (!webUtil.getText(secHeader).contentEquals("Verification Code"))
            throw new ExceptionUtils("Security page header content is changed");
        String str = webUtil.getText(verifyNumber);
        String str1 = "Please enter the verification code we've sent to the registered mobile phone number ending ~" + getdata.get("Number").substring(getdata.get("Number").length() - 4);
        webUtil.CompareString(str, "equalsIgnoreCase", str1);

        if (!webUtil.getText(enterOTPLabel).equals("Enter verification code"))
            throw new ExceptionUtils("Enter OTP Label not verified");
        log.info("Security page content matched");
    }

    public void error() throws Exception {
        if (getdata.get("OTP_Indicator").equalsIgnoreCase("Blank")) {
            if (webUtil.getText(error).equalsIgnoreCase(otpErrorMsg)) {
                log.info("Error message displayed " + webUtil.getText(error) + " Verified");
                ExtentCucumberAdapter.addTestStepLog("Error message displayed " + webUtil.getText(error) + " Verified");
                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            } else {
                throw new ExceptionUtils("Error message displayed " + webUtil.getText(error) + "not verified!");
            }
        } else if (getdata.get("OTP_Indicator").equalsIgnoreCase("Less")) {
            if (webUtil.getText(lessThansixDigit).equalsIgnoreCase("Please enter the six-digit security code sent to your registered mobile number.")) {
                log.info("Error message displayed " + webUtil.getText(By.xpath("//span[@id='CFVtxtPin']")) + " is verified");
                ExtentCucumberAdapter.addTestStepLog("Error message displayed " + webUtil.getText(By.xpath("//span[@id='CFVtxtPin']")) + " is verified");
                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            } else {
                throw new ExceptionUtils("Error message displayed " + webUtil.getText(lessThansixDigit) + "not verified!");
            }
        } else if (getdata.get("OTP_Indicator").equalsIgnoreCase("Invalid") || getdata.get("OTP_Indicator").equalsIgnoreCase("Expired")) {
            if (webUtil.getText(wrongError).equalsIgnoreCase(otpErrorMsg1)) {
                log.info("Error message displayed " + webUtil.getText(wrongError) + "is verified");
                ExtentCucumberAdapter.addTestStepLog("Error message displayed " + webUtil.getText(wrongError) + "is verified");
                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            } else {
                throw new ExceptionUtils("Error message displayed " + webUtil.getText(wrongError) + "not verified!");

            }
        } else {
            throw new ExceptionUtils("OTP indicator in excel is incorrect");
        }
    }

    /**
     * Enter the Otp and verify the error text
     *
     * @throws Exception
     */
    public void enterOtp() throws Exception {
        String otp = api.getOTP(prop.getProperty("OTPEndPoint"), 200, getdata.get("Number"));
        try {
            webUtil.sendKeys(enterOtp, otp);
//            ExtentCucumberAdapter.addTestStepLog("OTP is entered");
        } catch (Exception e) {
            throw new ExceptionUtils("Unable to enter OTP ".concat(e.getMessage()));
        }
    }

    /**
     * Enter the Otp and verify the error text
     *
     * @throws Exception
     */
    public void otpPageError() throws Exception {
        String otp = api.getOTP(prop.getProperty("OTPEndPoint"), 200, getdata.get("Number"));
        try {
            if (getdata.get("OTP_Indicator").equalsIgnoreCase("Blank")) {
                driver.findElement(enterOtp).sendKeys("", Keys.ENTER);
            } else if (getdata.get("OTP_Indicator").equalsIgnoreCase("Less")) {
                driver.findElement(enterOtp).sendKeys(otp.substring(4), Keys.ENTER);
            } else if (getdata.get("OTP_Indicator").equalsIgnoreCase("Invalid")) {
                driver.findElement(enterOtp).sendKeys("123456", Keys.ENTER);
            } else if (getdata.get("OTP_Indicator").equalsIgnoreCase("Expired")) {
                Thread.sleep(190000);
                driver.findElement(enterOtp).sendKeys(otp, Keys.ENTER);
            } else {
                throw new ExceptionUtils("OTP Indicator in excel is incorrect!");
            }
            error();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * didn't link helper function
     *
     * @return
     * @throws InterruptedException
     */

    public boolean didnotLinkHelper() throws InterruptedException {
        return webUtil.isElementclickable(didnotLink, 20);
    }

    /**
     * Click on didn't link if the otp is not recived
     *
     * @throws InterruptedException
     */
    public void didnotLink() throws Exception {
        if (didnotLinkHelper()) {
            webUtil.click(didnotLink);
            webUtil.waitForPageLoaded();
            enterOtp();
        } else {
            log.info("didn't link is not clicked");
        }
    }

    /**
     * Click on confirm button of security page
     */

    public void clkCnfrmBtn() throws Exception {

        if (!webUtil.getText(cnfmButton).contains("Confirm")) {
            throw new ExceptionUtils("Confirm button on OTP page is not validated");
        }
        webUtil.click(cnfmButton);


    }

    public void clickCancelBtn() throws Exception {
        if (webUtil.isElementclickable(cancelBtn, 20)) {
            if (webUtil.getText(cancelBtn).contains("Cancel")) {
                try {
                    webUtil.click(cancelBtn);
                } catch (Exception e) {
                    throw new ExceptionUtils("OTP Page cancel button click is interrupted");
                }
            } else {
                throw new ExceptionUtils("Cancel button content is changed on OTP page");
            }
        } else {
            throw new ExceptionUtils("OTP Page cancel button x-ptah is changed");
        }
    }

    public void errorCheck(String scenario) {
        switch (scenario) {
            case "Blank":
                driver.findElement(By.id("securityCodeForm")).sendKeys("", Keys.ENTER);
                webUtil.gettextlog(By.id("RFVSecurityCode"), String::equals, "Please enter your verification code");
                break;
            case "Expired":
                driver.findElement(By.id("securityCodeForm")).sendKeys("123456", Keys.ENTER);
                webUtil.gettextlog(By.id("verifyError"), String::equals, "REVSecurityCode");
                break;
            case "Less than six digits":
                driver.findElement(By.id("securityCodeForm")).sendKeys("123456", Keys.ENTER);
                webUtil.gettextlog(By.id("REVSecurityCode"), String::equals, "Please enter the six-digit verification code sent to your registered mobile number");
                break;
            default:
                System.out.println("No error validation on verification code page");
        }
    }


}
