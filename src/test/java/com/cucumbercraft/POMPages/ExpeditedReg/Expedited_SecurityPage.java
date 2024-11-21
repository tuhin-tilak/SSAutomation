package com.cucumbercraft.POMPages.ExpeditedReg;


import com.cucumbercraft.framework.APIReusuableLibrary;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.ExpeditedSteps;
import com.cucumbercraft.stepdefinitions.MasterStepDefs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Expedited_SecurityPage extends MasterStepDefs {
    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    APIReusuableLibrary apiUtil = new APIReusuableLibrary();
    String otpUrl = properties.getProperty("OTPEndPoint");


    private final By enterSecurityCode = By.id("securityCode");
    private final By cancelBtn = By.xpath("//a[@href='/your-savings/register/expedited-mobile']");
    private final By verifyBtn = By.xpath("//button[@id='btnVerify']");
    private final By didNotLink = By.xpath("//button[contains(text(),'receive the code?')]");
    private final String expDidNotLinkCont = "Didn't receive the code?";
    //Content for Security page
    private final String titleDescCont = "p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_ExpeditedMobileSecurity_%s";
    private final String expTitleCont = "Verification Code";
    private final String expDescCont = "Enter the verification code we've sent to the mobile number ending ~%s";
    private final By securityLblCont = By.xpath("//label[@for='securityCode']");
    private final String expSecurityLblCont = "Enter verification code";
    //After clicking didn't link pop-up
    private final By popupHeader = By.xpath("//section[@class='dashboard-modal__content js-modal active']//h4");
    private final String popupHeaderContent = "Didn't get your security code?";
    private final String paragraph = "//section[@class='dashboard-modal__content js-modal active']/p[%s]";
    private final String firstParagraphContent = "A security code was sent to your registered mobile number ending ~";
    private final String secondParagraphContent = "Before requesting the code is resent please check the following:";
    private final By popupResendBtn = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_ExpeditedMobileSecurity_btnResend");
    private final By popupCancelBtn = By.xpath("//input[@id='p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_ExpeditedMobileSecurity_btnResend']/preceding-sibling::button");
    private final String listCont = "//section[@class='dashboard-modal__content js-modal active']/ul/li[%s]";
    private final String list1Cont = "Allow 1-2 minutes for the code to be sent";
    private final String list2Cont = "You are using the correct mobile phone registered with your online account";
    private final String list3Cont = "There are no network issues in your area";

    public Expedited_SecurityPage(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    private void verifySecurityPage() throws Exception {

        // webUtil.gettextlog(By.className("m39-registration-form__heading underlined-heading"), "equalsIgnoreCase", expTitleCont);
        String mobileNumber = ExpeditedSteps.testData.get("MobileNumber");
        webUtil.gettextlog(By.xpath("//p[@class='m39-registration-form__text']"), String::equalsIgnoreCase, (String.format(expDescCont, (mobileNumber.substring(mobileNumber.length() - 4)))));
        webUtil.gettextlog(securityLblCont, String::equalsIgnoreCase, expSecurityLblCont);
        webUtil.gettextlog(didNotLink, String::equalsIgnoreCase, expDidNotLinkCont);

    }

    public void enterSecurityCode() throws Exception {
        String otp = apiUtil.getOTP(otpUrl, 200, "0".concat(ExpeditedSteps.testData.get("otpNumber")));
        String indicator = ExpeditedSteps.testData.get("otpIndicator");
        switch (indicator) {
            case "valid":
                enterOtp(otp);
                break;
            case "Incorrect":
                enterOtp("123456");
                break;
            case "LessThan6":
                enterOtp("123");
                break;
            //this is for blank case
            case "Blank":
                enterOtp("");
                break;
            case "Expired":
                webUtil.waitFor(190000);
                enterOtp(otp);
                break;
            default:
                throw new ExceptionUtils("Please check the otpIndicator value");
        }
    }

    public void enterOtp(String otp) throws InterruptedException {
        if (webUtil.isElementDisplayed(enterSecurityCode, 5)) {
            try {
                webUtil.sendKeys(enterSecurityCode, otp);
            } catch (Exception e) {
                throw new ExceptionUtils("Security code not entered");
            }
        } else {
            throw new ExceptionUtils("Security code text box xpath may have changed");
        }
    }

    public void clickBackBtn() throws Exception {
        verifySecurityPage();
        if (webUtil.isElementclickable(cancelBtn, 5)) {
            if (webUtil.getText(cancelBtn).equals("Cancel")) {
                webUtil.click(cancelBtn);
            } else {
                throw new ExceptionUtils("Cancel button clicked Interrupted");
            }
        } else {
            throw new ExceptionUtils("Cancel button xpath may have changed");
        }
    }

    public void clickVerifyBtn() throws Exception {
        verifySecurityPage();

        String str = webUtil.getText(verifyBtn);
        System.out.println(str + "Buttton displayed");
        if (webUtil.getText(verifyBtn).equals("Confirm")) {

            webUtil.click(verifyBtn);
        } else {
            throw new ExceptionUtils("Verify button clicked Interrupted");
        }

    }

    public void clickDidNotLink() throws Exception {
        if (webUtil.isElementclickable(didNotLink, 5)) {
            if (webUtil.getText(didNotLink).equals(expDidNotLinkCont)) {
                webUtil.click(didNotLink);
            } else {
                throw new ExceptionUtils("Didn't receive code link clicked Interrupted");
            }
        } else {
            throw new ExceptionUtils("Didn't receive code Link xpath may have changed");
        }
    }

    //After click on didn't receive the code link
    private void verifyDidnotPopup() throws Exception {
        if (webUtil.isElementDisplayed(popupHeader, 5)) {
            if (!webUtil.getText(popupHeader).equals(popupHeaderContent)) {
                throw new ExceptionUtils("Popup Header content not matched");
            }
            if (!webUtil.getText(By.xpath(String.format(paragraph, "1"))).equals(firstParagraphContent.concat(ExpeditedSteps.testData.get("MobileNumber").substring(ExpeditedSteps.testData.get("MobileNumber").length() - 4)).concat("."))) {
                throw new ExceptionUtils("Paragraph 1 content not matched");
            }
            if (!webUtil.getText(By.xpath(String.format(paragraph, "2"))).equals(secondParagraphContent)) {
                throw new ExceptionUtils("Paragraph 2 content not matched");
            }
            if (!webUtil.getText(By.xpath(String.format(listCont, "1"))).equals(list1Cont)) {
                throw new ExceptionUtils("list 1 Content not matched");
            }
            if (!webUtil.getText(By.xpath(String.format(listCont, "2"))).equals(list2Cont)) {
                throw new ExceptionUtils("List 2 content not matched");
            }
            if (!webUtil.getText(By.xpath(String.format(listCont, "3"))).equals(list3Cont)) {
                throw new ExceptionUtils("List 3 content not matched");
            }
        } else {
            throw new ExceptionUtils("PopupHeader xpath may have changed");
        }
    }

    public void clickCancelPopupBtn() throws Exception {
        verifyDidnotPopup();
        if (webUtil.isElementclickable(popupCancelBtn, 5)) {
            if (webUtil.getText(popupCancelBtn).equals("Cancel")) {
                webUtil.click(popupCancelBtn);
            } else {
                throw new ExceptionUtils("Cancel button clicked Interrupted");
            }
        } else {
            throw new ExceptionUtils("Popup Cancel button xpath may have changed");
        }
    }

    public void clickResendPopupBtn() throws Exception {
        verifyDidnotPopup();
        if (webUtil.isElementclickable(popupResendBtn, 5)) {
            if (driver.findElement(popupResendBtn).getAttribute("value").equals("Resend Code")) {
                webUtil.click(popupResendBtn);
            } else {
                throw new ExceptionUtils("Resend Code button clicked Interrupted");
            }
        } else {
            throw new ExceptionUtils("Resend Code button xpath may have changed");
        }
    }

}
