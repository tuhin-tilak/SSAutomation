package com.cucumbercraft.POMPages.ExpeditedReg;


import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.ExpeditedSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Expedited_EnterPIN {
    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private final By enterPIN = By.id("regCode");
    private final By backBtn = By.xpath("//a[@href = '/your-savings/register/expedited-email' ]");
    private final By confirmBtn = By.id("btnVerifyEmailCode");
    // PINCont is common for header,para,label content.
    private final By header = By.xpath("//*[@class='m39-registration-form__heading underlined-heading']");
    private final String headerText = "Enter your PIN";
    private final By textFieldAboveLabel = By.className("m39-registration-form__text");
    private final String textFieldAboveLabelText = "Please enter your PIN below";
    private final By textFieldBelowLabel = By.className("form-helper");
    private final String textFieldBelowLabelText = "Find your PIN included in the letter you received from us inviting you to register for Ireland State Savings Online.";
    private final By didnotLink = By.xpath("//a[contains(text(),'get your PIN?')]");
    private final String didnotLinkText = "Didn't get your PIN?";

    //After clicking didn't link pop-up
    private final By popupHeader = By.xpath("//section[@class='dashboard-modal__content js-modal active']//h4");
    private final String popupHeaderContent = "Your State Savings Online PIN";
    private final String paragraph = "//section[@class='dashboard-modal__content js-modal active']//p[%s]";
    private final String firstParagraphContent = "Please allow 2-3 weeks from the date you purchased your State Savings Product for your PIN to arrive.";
    private final String secondParagraphContent = "If after this time you have still not received your PIN, select 'Resend PIN' below.";
    private final By resendBtn = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_ExpeditedEmailCodeVerify_lbResendPIN");
    private final By cancelBtn = By.xpath("//a[@id='p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_ExpeditedEmailCodeVerify_lbResendPIN']/preceding-sibling::button");

    //Reissue PIN
    private final By ReissueThankHeader = By.xpath("//section[@class='dashboard-modal__content js-modal active']/h4");
    private final String expReissueThankHeader = "Thank you";
    private final By ReissueThankPara = By.xpath("//section[@class='dashboard-modal__content js-modal active']/p");
    private final String expReissueThankPara = "Your new PIN has been requested. Please allow 7-10 days for it to arrive.";
    //Buttons
    private final By ReissueThankCloseBtn = By.xpath("//div[@class='dashboard-modal-btn--container']/a[contains(text(),'Close')]");
    private final By ReissueThankGotoState = By.xpath("//div[@class='dashboard-modal-btn--container']/a[contains(text(),'Go to statesavings.ie ')]");

    public Expedited_EnterPIN(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    private void verifyPopUpContent() {
        webUtil.waitForPageLoaded();
        if (!webUtil.getText(popupHeader).equals(popupHeaderContent)) {
            throw new ExceptionUtils("Pop-Up header content is not similar");
        }
        if (!webUtil.getText(By.xpath(String.format(paragraph, "1"))).equals(firstParagraphContent)) {
            throw new ExceptionUtils("Pop-Up First content is not similar");
        }
        if (!webUtil.getText(By.xpath(String.format(paragraph, "2"))).equals(secondParagraphContent)) {
            throw new ExceptionUtils("Pop-Up Second content is not similar");
        }
    }

    public void popupClickResendBtn() throws Exception {
        verifyPopUpContent();
        if (webUtil.isElementDisplayed(resendBtn, 20)) {
            if (webUtil.getText(resendBtn).equals("Resend PIN")) {
                webUtil.click(resendBtn);
            } else {
                throw new ExceptionUtils("Resend button content may have been changed");
            }
        } else {
            throw new ExceptionUtils("Resend button x-path may have been changed");
        }
    }

    public void popupClickCancelBtn() throws Exception {
        if (webUtil.isElementDisplayed(cancelBtn, 20)) {
            if (webUtil.getText(cancelBtn).equals("Cancel"))
                webUtil.click(cancelBtn);
            else
                throw new ExceptionUtils("Cancel button content may have been changed");
        } else {
            throw new ExceptionUtils("Cancel button x-path may have been changed");
        }
    }

    private void verifyContent() {

        webUtil.gettextlog(header, String::equals, headerText);
        webUtil.gettextlog(textFieldAboveLabel, String::equals, textFieldAboveLabelText);
        webUtil.gettextlog(textFieldBelowLabel, String::equals, textFieldBelowLabelText);
        webUtil.gettextlog(didnotLink, String::equals, didnotLinkText);
    }

    public void clickConfirmBtn() {
        verifyContent();
        webUtil.gettextlog(confirmBtn, String::equals, "Continue");
        webUtil.click(confirmBtn);

    }

    public void clickBackBtn() throws Exception {
        verifyContent();
        if (webUtil.isElementclickable(backBtn, 10)) {
            if (driver.findElement(backBtn).getAttribute("value").equals("Back")) {
                webUtil.click(backBtn);
            } else {
                throw new ExceptionUtils("Back button content is changed");
            }
        } else {
            throw new ExceptionUtils("Back button X-Path may have changed");
        }
    }

    public void clickDidnotLink() throws Exception {
        if (webUtil.isElementDisplayed(didnotLink, 10)) {
            webUtil.click(didnotLink);
        } else {
            throw new ExceptionUtils("didn't link x-path may have been changed");
        }
    }

    public void enterPin(String pin) throws Exception {
        if (webUtil.isElementDisplayed(enterPIN, 10)) {
            try {
//                webUtil.sendKeys(enterPIN, Expedited.testData.get("PIN"));
                webUtil.sendKeys(enterPIN, pin);
            } catch (Exception e) {
                throw new ExceptionUtils("Pin is not entered");
            }
        } else {
            throw new ExceptionUtils("Enter Pin X-Path may have been changed");
        }
    }

    public void enterPin() throws Exception {
        if (webUtil.isElementDisplayed(enterPIN, 10)) {
            try {
                webUtil.sendKeys(enterPIN, ExpeditedSteps.testData.get("PIN"));

            } catch (Exception e) {
                throw new ExceptionUtils("Pin is not entered");
            }
        } else {
            throw new ExceptionUtils("Enter Pin X-Path may have been changed");
        }
    }

    //thank you page after reissue of PIN
    private void validateReissuePINThankYouPage() {
        webUtil.waitForPageLoaded();
        if (!webUtil.getText(ReissueThankHeader).equals(expReissueThankHeader)) {
            throw new ExceptionUtils("Reissue Thank you page header content is not similar");
        }
        if (!webUtil.getText(ReissueThankPara).equals(expReissueThankPara)) {
            throw new ExceptionUtils("Reissue Thank you page paragraph content not matched");
        }
        if (!webUtil.getText(ReissueThankCloseBtn).equals("Close")) {
            throw new ExceptionUtils("Reissue Thank you page Close button content not matched");
        }
        if (!webUtil.getText((ReissueThankGotoState)).equals("Go to statesavings.ie")) {
            throw new ExceptionUtils("Reissue Thank you page GoToStateSaving button content not matched");
        }
    }

    public void clickCloseBtnReissueThanksPage() throws Exception {
        validateReissuePINThankYouPage();
        if (webUtil.isElementDisplayed(ReissueThankCloseBtn, 10)) {
            webUtil.click(ReissueThankCloseBtn);
        } else {
            throw new ExceptionUtils("close button of Reissue Thank you page xpath may have changed");
        }
    }

    public void clickGoToStateBtnReissueThanksPage() throws Exception {
        validateReissuePINThankYouPage();
        if (webUtil.isElementDisplayed(ReissueThankGotoState, 10)) {
            webUtil.click(ReissueThankGotoState);
        } else {
            throw new ExceptionUtils("GotoStateSaving button of Reissue Thank you page xpath may have changed");
        }
    }

}
