package com.cucumbercraft.POMPages.WebReg;

import com.cucumbercraft.framework.DriverFactory;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.ExpeditedSteps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmMobileNumber {
    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private static final Logger log = LogManager.getLogger(DriverFactory.class);

    public ConfirmMobileNumber(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    private final By header = By.id("title");
    private final By description = By.id("description");
    private final By mobileTitle = By.id("mobileReviewTitle");
    private final By mobileNumber = By.id("customerMobileNumber");
    private final By editDetailsBtn = By.id("btnEditDetails");
    private final By sendCodeBtn = By.id("btnNext");

    //Expected content
    private final String headerCont = "Confirm your mobile number";
    private final String descriptionCont = "Make sure you have your mobile to hand before clicking 'Send Code' – we will send a verification code via text message to the mobile phone below.";
    private final String mobileTitleCont = "Mobile phone number";


    private final String actualMobileNumber = ExpeditedSteps.testData.get("Prefix re-enter").concat(" ").concat(ExpeditedSteps.testData.get("MobileNumber"));
//    private String actualMobileNumber="+353 209000001";

    private void verifyContent() throws Exception {
        webUtil.gettextlog(description, String::equalsIgnoreCase, descriptionCont);
        webUtil.gettextlog(mobileTitle, String::equalsIgnoreCase, mobileTitleCont);
        webUtil.gettextlog(mobileNumber, String::equalsIgnoreCase, actualMobileNumber);
        webUtil.gettextlog(header, String::equalsIgnoreCase, headerCont);

    }

    public void clickEditDetailsBtn() throws Exception {
        verifyContent();
        webUtil.gettextlog(editDetailsBtn, String::equalsIgnoreCase, "Edit Details");
        try {
            webUtil.click(editDetailsBtn);
            log.info("Edit Details button Clicked on confirm Mobile number");
        } catch (Exception e) {
            throw new ExceptionUtils("Edit Details button click Interrupted");
        }
    }

    public void clickSendCodeBtn() throws Exception {
        verifyContent();
        try {
            webUtil.click(sendCodeBtn);
            log.info("Send Code button Clicked on confirm Mobile number");
        } catch (Exception e) {
            throw new ExceptionUtils("Send Code button click Interrupted");
        }
    }


}
