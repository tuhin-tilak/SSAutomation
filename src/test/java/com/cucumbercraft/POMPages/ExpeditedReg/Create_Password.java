package com.cucumbercraft.POMPages.ExpeditedReg;

import com.cucumbercraft.POMPages.SignInPg;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.ExpeditedSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Create_Password {
    WebDriver driver;
    WebDriverUtil webUtil;
    SignInPg login;

    public Create_Password(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
        login = new SignInPg(driver);
    }


    private final By enterPassword = By.xpath("//input[@id='txtPassword']");
    private final By showPasswordBtn = By.xpath("//input[@id='txtPassword']/following-sibling::span");
    private final By completeBtn = By.xpath("//button[@id='btnSubmit']");
    //Text verify of create password page.
    private final String titleDesc = "//p[@class='m39-registration-form__text']";
    private final String header = "//h2[@class='m39-registration-form__heading underlined-heading']";
    private final String expTitleCont = "Create your password";
    private final String expDesccont = "A strong password is required for Ireland State Savings Online access.";
    private final By passwordLabel = By.xpath("//label[contains(text(),'Enter your new password')]");
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
    private final By congBanner = By.xpath("//p[@class='notification-box__text']");
    private final String expCongBannerCont = "Congratulations! You are now registered for Ireland State Savings Online. Sign in below.";

    public void verifyCongBanner() throws Exception {

        webUtil.gettextlog(congBanner, String::equalsIgnoreCase, expCongBannerCont);

    }

    public void verifyCreatePassword() throws Exception {
        webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("expedited-create-password"));

        webUtil.gettextlog(By.xpath(header), String::equals, expTitleCont);
        webUtil.gettextlog(By.xpath(titleDesc), String::equals, expDesccont);
        webUtil.gettextlog(passwordLabel, String::equals, expLabelCont);
        webUtil.gettextlog(paragraphPassReq, String::equals, expParagraphPassReq);
        webUtil.gettextlog(By.xpath(String.format(listPassReq, "1")), String::equals, expList1cont);
        webUtil.gettextlog(By.xpath(String.format(listPassReq, "2")), String::equals, expList2cont);
        webUtil.gettextlog(By.xpath(String.format(listPassReq, "3")), String::equals, expList3cont);
        webUtil.gettextlog(By.xpath(String.format(listPassReq, "4")), String::equals, expList4cont);
        webUtil.gettextlog(By.xpath(String.format(listPassReq, "5")), String::equals, expList5cont);

    }

    public void enterPassword() throws InterruptedException {
        try {
            webUtil.sendKeys(enterPassword, ExpeditedSteps.testData.get("Password"));
        } catch (Exception e) {
            throw new ExceptionUtils("Password not entered");
        }
    }

    public void clickShowPassword() throws Exception {
        webUtil.click(showPasswordBtn);
    }

    public void clickCompleteBtn() throws InterruptedException {
        webUtil.gettextlog(completeBtn, String::equals, "Complete");
        webUtil.click(completeBtn);
    }


    public void Login(String username, String password, String number) throws Exception {
        login.FirstTimeLogin(username, password, number);
        webUtil.waitForPageLoaded();
    }
}
