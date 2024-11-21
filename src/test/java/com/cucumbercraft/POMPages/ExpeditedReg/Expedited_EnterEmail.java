package com.cucumbercraft.POMPages.ExpeditedReg;

import com.cucumbercraft.framework.DriverFactory;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.ExpeditedSteps;
import com.cucumbercraft.stepdefinitions.ReinvestSteps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Expedited_EnterEmail {
    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private static final Logger log = LogManager.getLogger(DriverFactory.class);


    private final String expHeaderText = "Enter your email address";
    private final String expPara1Text = "This must be the same email address used on your purchase application";
    private final String expLabel1Text = "Email address";
    private final String expLabel2Text = "Re-enter email address";
    private final String expConsentText = "I consent to the Ireland State Savings Online Terms and Conditions";


    //Enter your email page xpath

    private final By enterEmail = By.xpath("//input[@id='txtEmailAddress']");
    private final By reEnterEmail = By.xpath("//input[@id='txtCompareEmail']");
    private final By checkConsent = By.xpath("//label[@for='terms4']");
    private final By backBtn = By.xpath("//a[@href='/your-savings/register/personal-details']");
    private final By nextBtn = By.xpath("//*[text() = 'Next']");
    private final By consentLink = By.xpath("//a[@href = '../state-savings-online-terms-conditions']");

    private final String content = "p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_ExpeditedRegistrationEmail_%s";
    private final By consentText = By.xpath("//label[contains(text(),'I consent to the ')]");
    private final By emailHeader = By.xpath("//*[@class='m39-registration-form__heading underlined-heading']");
    private final By emailDesc = By.className("m39-registration-form__text");
    private final By lblEmail = By.id("lblEmail");
    private final By lblCompareEmail = By.id("lblCompareEmail");


    public Expedited_EnterEmail(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    public void enterEmailAddress() throws InterruptedException {

        webUtil.sendKeys(enterEmail, ExpeditedSteps.testData.get("EmailAddress"));

    }

    public void reEnterEmailAddress() throws InterruptedException {

        webUtil.sendKeys(reEnterEmail, ExpeditedSteps.testData.get("Re-EnterEmailAddress"));

    }

    public void checkConsent() throws InterruptedException {
        if (!ExpeditedSteps.testData.get("EmailAddress").equalsIgnoreCase("")) {
            if (webUtil.isElementDisplayed(checkConsent, 5)) {
                try {
                    WebElement element = driver.findElement(checkConsent);
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();", element);
//                webUtil.click(checkConsent);
                } catch (Exception e) {
                    throw new ExceptionUtils("Consent not checked");
                }
            } else {
                throw new ExceptionUtils("Consent checkbox xpath may have changed");
            }
        } else {
            log.info("consent not checked");
        }
    }

    public void clickBackBtn() throws Exception {
        if (webUtil.isElementDisplayed(backBtn, 5)) {
            if (webUtil.getText(backBtn).equals("Back")) {
                try {
                    webUtil.click(backBtn);
                } catch (Exception e) {
                    throw new ExceptionUtils("Back button not clicked on Enter email page");
                }
            } else {
                throw new ExceptionUtils("Back button content not matched");
            }
        } else {
            throw new ExceptionUtils("Back button xpath may have changed");
        }
    }

    public void clickNextBtn() throws Exception {
        webUtil.gettextlog(nextBtn, String::equals, "Next");
        webUtil.click(nextBtn);


    }

    public void clickConsentLink() throws Exception {
        if (webUtil.isElementclickable(consentLink, 5)) {
            if (webUtil.getText(consentLink).contains(expConsentText)) {
                try {
                    webUtil.click(consentLink);
                } catch (Exception e) {
                    throw new ExceptionUtils("consent link click Interrupted");
                }
            } else {
                throw new ExceptionUtils("consent link text not matched");
            }
        } else {
            throw new ExceptionUtils("Consent link xpath may have changed");
        }
    }

    public void verifyEmailConst() throws Exception {


        webUtil.gettextlog(emailHeader, String::equals, expHeaderText);
        webUtil.gettextlog(emailDesc, String::equals, expPara1Text);
        webUtil.gettextlog(lblEmail, String::equals, expLabel1Text);
        webUtil.gettextlog(lblCompareEmail, String::equals, expLabel2Text);
        webUtil.gettextlog(consentText, String::equals, expConsentText);


    }

    public void enterEmailAddressforTitle() throws InterruptedException {

        webUtil.sendKeys(enterEmail, ReinvestSteps.getdata.get("EmailAddress"));
        webUtil.sendKeys(reEnterEmail, ReinvestSteps.getdata.get("Re-EnterEmailAddress"));

    }
}

