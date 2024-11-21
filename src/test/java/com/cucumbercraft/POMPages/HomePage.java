package com.cucumbercraft.POMPages;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.BuyNow.YourOrder;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.Context;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage {

    public By savePref = By.xpath("//*[contains(@class,'save')]/button");
    public By signIn = By.xpath("//*[contains(@id,'signInBlock1')]/a");
    public By stateSavingtabbtn = By.xpath(".//*[@title='Need a secure home for your savings?']");
    public By hdr = By.xpath("//*[@class='m01-banner--title']");
    public By forgotPwd = By.xpath("//button[contains(@id,'Forgot') or contains(text(),'Forgot')]");


    //Home page);
    public By registration = By.xpath("//*[@id='registerBlock1']/a");
    private final By btnBuyNow = By.xpath("//li[contains(@id,'buyMoreBlock')]/a");
    public By englishLanguage = By.xpath("//ul[@class='language-toggle--list']/li/a[@title='English']");
    public By gaeilgeLanguage = By.xpath("//ul[@class='language-toggle--list']/li/a[@title='Gaeilge']");
    public By home = By.xpath("//a[contains(text(),'Home')]");
    public By ourProducts = By.xpath("//a[@title='Our Products']");
    public By prizeBonds = By.xpath("//a[@title='Prize Bonds']");
    public By aboutStateSaving = By.xpath("//a[@title='About State Savings']");
    public By helpAndSupport = By.xpath("//a[@title='Help and Support']");
    public By allProducts = By.xpath("//a[@title='View all products']");

    //    footer of homepage
    public By aboutUs = By.xpath("//a[@title='About Us']");
    public By contactUs = By.xpath("//a[@title='Contact Us']");
    public By siteUseTermAndConditions = By.xpath("//a[contains(text(),'Site Use Terms and Conditions')]");
    public By termAndConditions = By.xpath("//a[contains(text(),'Terms & Conditions')]");
    public By accessibility = By.xpath("//a[@title='Accessibility']");
    public By dataProtection = By.xpath("//a[@title='Data Protection']");
    public By cookiesPolicy = By.xpath("//a[@title='Cookie Policy']");
    public By cookiesWeUse = By.xpath("//a[@title='Cookies We Use']");
    public By NTMA = By.xpath("//a[contains(text(),'NTMA')]");
    public By anPost = By.xpath("//a[contains(text(),'An Post')]");
    public By prizeBondCompany = By.xpath("//a[contains(text(),'Prize Bond Company')]");
    public By footerLogo = By.xpath("//a[@title='Go back to homepage']");
    WebDriver driver;
    public By lblpreference = By.xpath("//*[contains(text(),'Privacy Preference Centre')]");
    public By btnAllowall = By.xpath("//*[contains(text(),'Allow All')]");

    private final WebDriverUtil webUtil;
    Context testContext;

    public HomePage(WebDriver driver) {

        this.driver = driver;
        webUtil = new WebDriverUtil(driver);

    }


    public void userpermission() {
        try {

            if (webUtil.isElementVisible(lblpreference, 10)) {
                ExtentCucumberAdapter.addTestStepLog("Privacy Preferences displayed");

                if (webUtil.isElementVisible(By.xpath("(//*[contains(text(),'Allow All')])[2]"), 5))
                    driver.findElement(By.xpath("(//*[contains(text(),'Allow All')])[2]")).click();
                else
                    driver.findElement(By.xpath("//*[contains(text(),'Allow All')]")).click();




            }
        } catch (Exception ignored) {
            ExtentCucumberAdapter.addTestStepLog("Preferences permission enabled already");

        }
    }


    public void click_Sign_In() {
        webUtil.clickLog(signIn, "Sign in button");
    }

    public YourOrder clickBuyNowOnHomePage() {
        webUtil.clickLog(btnBuyNow, "Buy Now Button on Homepage");
        return new YourOrder(driver);
    }

}


