package com.cucumbercraft.POMPages.WebReg;

import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Web_Check_Your_Inbox {
    private final WebDriver driver;
    private final WebDriverUtil webUtil;

    public Web_Check_Your_Inbox(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    //    xpaths
    private final By header = By.id("title");
    private final String headerContent = "Check your inbox";
    private final By description = By.id("description");
    private final String descriptionContent = "We have sent you a confirmation email to your email address. The verification link will remain active for 8 hours. If you don’t click the link within this time you’ll need to start registration again.";

    private final By didnotRecieveMail = By.id("btnResendEmail");
    private final String didnotRecieveMailContent = "Didn't receive our email?";

    //didn't get email page
    //popUP
    private final By popupHeader = By.xpath("//section[@class='dashboard-modal__content js-modal active']/h4");
    private final String popupHeaderContent = "Didn't get an email?";
    private final By label = By.xpath("//section[@class='dashboard-modal__content js-modal active']/p[1]");
    private final String labelContent = "Before requesting the email is resent please check the following.";
    private final String points = "//section[@class='dashboard-modal__content js-modal active']/ul/li[%s]";
    private final String firstPoint = "Allow 1-2 minutes for the email to be sent.";
    private final String secondPoint = "Check your spam folder.";
    private final String thirdPoint = "Check that your device is online and that there are no network issues in your area.";
    private final By paragraph = By.xpath("//section[@class='dashboard-modal__content js-modal active']/p[2]");
    private final String paragraphcont = "If you still haven't received the email, you may have entered your email address incorrectly,\n" +
            "in which case you will not be able to proceed with the registration at this time. \n" +
            "You may restart the process once our systems have refreshed. Please allow 8 hours after your initial registration attempt before registering again.";
    private final By resendEmail = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_RegistrationModals_btnResendForgetPasswordEmail");
    private final By closeBtn = By.xpath("//div[@class='dashboard-modal-btn--container']/button");
    private final By resendSuccessfully = By.xpath("//div[contains(text(),'Verification email resent successfully.')]");

    public void verifyContent() throws Exception {
        webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("check-email"));

        webUtil.gettextlog(header, String::equalsIgnoreCase, headerContent);
        webUtil.gettextlog(description, String::equalsIgnoreCase, descriptionContent);
        webUtil.gettextlog(didnotRecieveMail, String::equalsIgnoreCase, didnotRecieveMailContent);
    }

    public void clickDidNotEmailLink() throws InterruptedException {
        try {
            webUtil.click(didnotRecieveMail);
        } catch (Exception e) {
            throw new ExceptionUtils("Did'nt receive email link clicked Interrupted");
        }
    }


    //Resend email page
    private void verifyPopupContent() throws Exception {

        webUtil.gettextlog(popupHeader, String::equalsIgnoreCase, popupHeaderContent);
        webUtil.gettextlog(label, String::equalsIgnoreCase, labelContent);
        webUtil.gettextlog((By.xpath(String.format(points, "1"))), String::equalsIgnoreCase, firstPoint);
        webUtil.gettextlog((By.xpath(String.format(points, "2"))), String::equalsIgnoreCase, secondPoint);
        webUtil.gettextlog((By.xpath(String.format(points, "3"))), String::equalsIgnoreCase, thirdPoint);
        webUtil.gettextlog(paragraph, String::equalsIgnoreCase, paragraphcont);

    }

    public void clickResendBtnPopUp() throws Exception {
        verifyPopupContent();
        webUtil.gettextlog(resendEmail, String::equalsIgnoreCase, "Resend email");
        webUtil.click(resendEmail);
        verifyResendBanner();
    }

    public void clickCloseBtnPopUp() throws Exception {
        verifyPopupContent();
        webUtil.gettextlog(closeBtn, String::equalsIgnoreCase, "Close");
        webUtil.click(closeBtn);
    }

    private void verifyResendBanner() throws Exception {
        webUtil.gettextlog(resendSuccessfully, String::equalsIgnoreCase, "Verification email resent successfully.");
    }

}
