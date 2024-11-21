package com.cucumbercraft.POMPages.WebReg;

import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.ExpeditedSteps;
import com.cucumbercraft.stepdefinitions.ReinvestSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Web_CreatePassword {

    private final WebDriver driver;
    private final WebDriverUtil webUtil;

    public Web_CreatePassword(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    //    xpaths
    private final By enterPassword = By.id("txtPassword");
    private final By showPasswordBtn = By.xpath("(//span[@class='toggle-password'])[1]");
    private final By completeBtn = By.id("btnSubmit");
    //Text verify of create password page.
    private final By title = By.xpath("//h2[@class='m39-registration-form__heading underlined-heading']");
    private final String expTitleCont = "Create your password";
    private final By desc = By.xpath("//p[@class='m39-registration-form__text']");
    private final String expDesccont = "A strong password is required for Ireland State Savings Online access.";
    private final By passwordLabel = By.xpath("//label[@class='form-label form-label--password-reset']");
    private final String expLabelCont = "Enter your new password";
    private final By paragraphPassReq = By.xpath("//div[@class='password-checklist']/p");
    private final String expParagraphPassReq = "Password Requirements";
    private final String listPassReq = "//ul[@class='helper-text']/li[%s]/span";
    private final String expList1cont = "Minimum 10 characters";
    private final String expList2cont = "At least 1 uppercase character (A-Z)";
    private final String expList3cont = "At least 1 lowercase character (a-z)";
    private final String expList4cont = "At least 1 digit (0-9)";
    private final String expList5cont = "At least 1 special character\n" +
            "(@$£#!%*&+=_^|[]:;.?`~<,>'(){}\"-)";

    //congratulations banner content
    private final By compWebRegHeader = By.id("title");
    private final String compWebRegHeaderContent = "What happens next?";

    public void VerifyCompWebRegHeader() throws Exception {
        webUtil.gettextlog(compWebRegHeader, String::equalsIgnoreCase, compWebRegHeaderContent);

    }


    public void verifyCreatePassword() throws Exception {
        webUtil.gettextlog(title, String::equalsIgnoreCase, expTitleCont);
        webUtil.gettextlog(desc, String::equalsIgnoreCase, expDesccont);
        webUtil.gettextlog(passwordLabel, String::equalsIgnoreCase, expLabelCont);
        webUtil.gettextlog(paragraphPassReq, String::equalsIgnoreCase, expParagraphPassReq);
        webUtil.gettextlog(By.xpath(String.format(listPassReq, "1")), String::equalsIgnoreCase, expList1cont);
        webUtil.gettextlog(By.xpath(String.format(listPassReq, "2")), String::equalsIgnoreCase, expList2cont);
        webUtil.gettextlog(By.xpath(String.format(listPassReq, "3")), String::equalsIgnoreCase, expList3cont);
        webUtil.gettextlog(By.xpath(String.format(listPassReq, "4")), String::equalsIgnoreCase, expList4cont);
        webUtil.gettextlog(By.xpath(String.format(listPassReq, "5")), String::equalsIgnoreCase, expList5cont);

    }

    public void enterPassword() throws InterruptedException {
        try {
            webUtil.sendKeys(enterPassword, ExpeditedSteps.testData.get("Password"));
//                webUtil.sendKeys(enterPassword, "MyAccount_2021");
        } catch (Exception e) {
            throw new ExceptionUtils("Password not entered");
        }
    }

    public void clickShowPassword() throws Exception {
        webUtil.click(showPasswordBtn);
    }

    public void clickCompleteBtn() throws InterruptedException {
        try {
            webUtil.click(completeBtn);
        } catch (Exception e) {
            throw new ExceptionUtils("complete button clicked Interrupted");
        }

    }

    public void enterPasswordWebTitle() {
        try {
            webUtil.sendKeys(enterPassword, ReinvestSteps.getdata.get("Password1"));

        } catch (Exception e) {
            throw new ExceptionUtils("Password not entered");
        }
    }

}
