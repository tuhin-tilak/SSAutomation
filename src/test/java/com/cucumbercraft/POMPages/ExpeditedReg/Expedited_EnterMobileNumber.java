package com.cucumbercraft.POMPages.ExpeditedReg;

import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.ExpeditedSteps;
import com.cucumbercraft.stepdefinitions.ReinvestSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Expedited_EnterMobileNumber {
    private final WebDriver driver;
    private final WebDriverUtil webUtil;

    private final By enterMobileNumber = By.xpath("//input[@id='txtPhoneNo']");
    private final By reEnterMobile = By.xpath("//input[@id='txtPhoneNoB']");
    private final By enterMobilePrefix = By.xpath("//select[@id='ddl_PhonePrefixA']");
    private final By reEnterMobilePrefix = By.xpath("//input[@id='txtPhonePrefixB-selectized']");
    private final By cancelBtn = By.xpath("//a[@href='javascript:void(0);']");
    private final By continueBtn = By.id("btnSubmit");
    //text validation
    private final String titleDescCont = "p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_ExpeditedRegistrationMobile_%s";
    private final String expTitleCont = "Enter your mobile number";
    private final String expDescCont = "This must be the same mobile number you supplied on your purchase application.";
    private final By header1Cont = By.xpath("//h4[contains(text(),'Enter mobile number')]");
    private final String expHeader1Cont = "Enter mobile number";
    private final By header2Cont = By.xpath("//h4[contains(text(),'Re-enter mobile number')]");
    private final String expHeader2Cont = "Re-enter mobile number";
    private final By para1Cont = By.xpath("//p[contains(text(),'We will need your mobile')]");
    private final String expPara1Cont = "We will need your mobile number later so we can associate it with your Ireland State Savings Online service";
    private final By prefix1Cont = By.id("lblPhonePrefixA");
    private final String expPrefixCont = "Prefix";
    private final By mobile1LblCont = By.xpath("//input[@id='txtPhoneNo']/preceding-sibling::label");
    private final String expMobileLblCont = "Mobile Phone Number";
    private final By prefix2Cont = By.id("lblPhonePrefixB");
    private final By mobile2LblCont = By.xpath("//input[@id='txtPhoneNoB']/preceding-sibling::label");


    public Expedited_EnterMobileNumber(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    private void verifyMobileContent() {


        webUtil.gettextlog(By.id("title"), String::equals, expTitleCont);
        webUtil.gettextlog(By.id("description"), String::equals, expDescCont);
        webUtil.gettextlog(header1Cont, String::equals, expHeader1Cont);
        webUtil.gettextlog(header2Cont, String::equals, expHeader2Cont);
        webUtil.gettextlog(para1Cont, String::equals, expPara1Cont);
        webUtil.gettextlog(prefix1Cont, String::equals, expPrefixCont);
        webUtil.gettextlog(mobile1LblCont, String::equals, expMobileLblCont);
        webUtil.gettextlog(prefix2Cont, String::equals, expPrefixCont);
        webUtil.gettextlog(mobile2LblCont, String::equals, expMobileLblCont);


    }

    public void enterMobileNumber() throws Exception {
        if (webUtil.isElementDisplayed(enterMobileNumber, 10)) {
            try {
                webUtil.sendKeys(enterMobileNumber, ExpeditedSteps.testData.get("MobileNumber"));
            } catch (Exception e) {
                throw new ExceptionUtils("Mobile number not Entered");
            }
        } else {
            throw new ExceptionUtils("Enter Mobile number xpath may have changed");
        }
    }

    public void reEnterMobileNumber() throws Exception {
        if (webUtil.isElementDisplayed(reEnterMobile, 10)) {
            try {
                webUtil.sendKeys(reEnterMobile, ExpeditedSteps.testData.get("Re-EnterMobileNumber"));
            } catch (Exception e) {
                throw new ExceptionUtils("Mobile number not Re-entered");
            }
        } else {
            throw new ExceptionUtils("Re-Enter mobile number Xpath may have changed");
        }
    }

    public void EnterMobileNumberdetails(String number1, String number2) throws Exception {
        if (webUtil.isElementDisplayed(enterMobileNumber, 10)) {
            try {
                webUtil.sendKeys(enterMobileNumber, number1);
                webUtil.sendKeys(reEnterMobile, number2);
            } catch (Exception e) {
                throw new ExceptionUtils("Mobile number not entered");
            }
        } else {
            throw new ExceptionUtils("Enter mobile number Xpath may have changed");
        }
    }


    public void selectMobilePrefix() throws Exception {
        if (webUtil.isElementDisplayed(enterMobilePrefix, 10)) {
            try {
                webUtil.selectDropDown(enterMobilePrefix, select -> select.selectByVisibleText(ExpeditedSteps.testData.get("prefix")));
            } catch (Exception e) {
                throw new ExceptionUtils("Mobile Prefix not selected");
            }
        } else {
            throw new ExceptionUtils("Mobile Prefix Dropdown xpath may have changed");
        }
    }

    public void selectReMobilePrefix() throws Exception {
        if (webUtil.isElementDisplayed(reEnterMobilePrefix, 10)) {
            try {
                webUtil.sendKeys(reEnterMobilePrefix, (ExpeditedSteps.testData.get("Prefix re-enter")));
                //webUtil.selectDropDown(reEnterMobilePrefix, select -> select.selectByVisibleText(Expedited.testData.get("Prefix re-enter")));
            } catch (Exception e) {
                throw new ExceptionUtils("Re-Enter Mobile Prefix not selected");
            }
        } else {
            throw new ExceptionUtils("Re-Enter Mobile Prefix Dropdown xpath may have changed");
        }
    }

    public void clickCancelBtn() throws Exception {
        verifyMobileContent();
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

    public void clickContinueBtn() throws Exception {
        verifyMobileContent();
        webUtil.gettextlog(continueBtn, String::equals, "Continue");
        webUtil.click(continueBtn);

    }


    public void enterMobileNumberdetailsforTitle() throws Exception {
        if (webUtil.isElementDisplayed(enterMobileNumber, 10)) {
            try {
                webUtil.sendKeys(enterMobileNumber, ReinvestSteps.getdata.get("MobileNumber"));
                webUtil.sendKeys(reEnterMobile, ReinvestSteps.getdata.get("Re-EnterMobileNumber"));

            } catch (Exception e) {
                throw new ExceptionUtils("Mobile number not Entered");
            }
        } else {
            throw new ExceptionUtils("Enter Mobile number xpath may have changed");
        }
    }
}
