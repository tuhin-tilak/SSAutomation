package com.cucumbercraft.POMPages.WebReg;

import com.cucumbercraft.framework.APIReusuableLibrary;
import com.cucumbercraft.framework.DriverFactory;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.ExpeditedSteps;
import com.cucumbercraft.stepdefinitions.MasterStepDefs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebSecurityCode extends MasterStepDefs {
    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    APIReusuableLibrary apiUtil = new APIReusuableLibrary();
    String otpUrl = properties.getProperty("OTPEndPoint");
    private static final Logger log = LogManager.getLogger(DriverFactory.class);

    public WebSecurityCode(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    //xpath
    String commanpath = "p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationConfirmMobileNumber_";
    private final By header = By.id("title");
    String headerCont = "Confirm your mobile number";
    private final By description = By.id("description");
    String descriptionCont = "Enter the verification code in the box below and click confirm.";
    private final By mobileLable = By.id("securityCodeTitle");
    String mobileLabelCont = "Enter verification code";
    private final By enterCode = By.id("securityCode");
    private final By didnotlink = By.id("linkResendCode");
    String didnotLinkCont = "Didn't receive the code?";
    private final By confirmBtn = By.id("btnNext");

    //did not receive code link
    private final By popHeader = By.xpath("//section[@class='dashboard-modal__content js-modal active']/h4");
    private final String popHeaderCont = "Didn't get your Security Code?";
    private final By popCancelBtn = By.xpath("//section[@class='dashboard-modal__content js-modal active']/div/button");
    private final By popResendcodeBtn = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationConfirmMobileNumber_btnResendSecurityCode");

    private void verifyPopUpcontent() throws Exception {
        webUtil.gettextlog(popHeader, String::equalsIgnoreCase, popHeaderCont);
    }

    public void clickPopCancelBtn() throws Exception {
        verifyPopUpcontent();
        webUtil.gettextlog(popCancelBtn, String::equalsIgnoreCase, "Cancel");
        webUtil.click(popCancelBtn);

    }

    public void clickpopResendCodeBtn() throws Exception {
        verifyPopUpcontent();
        webUtil.gettextlog(popResendcodeBtn, String::equalsIgnoreCase, "Resend code");
        webUtil.click(popResendcodeBtn);

    }

    private void verifycontentSecurity() throws Exception {

        webUtil.gettextlog(header, String::equalsIgnoreCase, headerCont);
        webUtil.gettextlog(description, String::equalsIgnoreCase, descriptionCont);
        webUtil.gettextlog(mobileLable, String::equalsIgnoreCase, mobileLabelCont);
        webUtil.gettextlog(didnotlink, String::equalsIgnoreCase, didnotLinkCont);

    }

    public void enterSecuritycode() throws Exception {
        String otp = apiUtil.getOTP(otpUrl, 200, "0".concat(ExpeditedSteps.testData.get("otpNumber")));
        try {
            webUtil.sendKeys(enterCode, otp);
        } catch (Exception e) {
            throw new ExceptionUtils("Security code not entered");
        }
    }

    public void clickConfirmBtn() throws Exception {
        verifycontentSecurity();
        try {
            webUtil.click(confirmBtn);
        } catch (Exception e) {
            throw new ExceptionUtils("Confirm button click interrupted");
        }
    }

    // Did not get security code link
    public void clickDidntLink() throws Exception {
        try {
            webUtil.click(didnotlink);
        } catch (Exception e) {
            throw new ExceptionUtils("Did not receive link clicked interrrupted");
        }
    }

}


