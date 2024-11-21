package com.cucumbercraft.POMPages.WebReg;

import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.ExpeditedSteps;
import org.apache.commons.lang.WordUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckDetailsAndContin {
    WebDriver driver;
    WebDriverUtil webUtil;

    public CheckDetailsAndContin(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    public By beginRegistration1 = By.xpath("//a[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M01YourSavingsMainBanner_lnkButton']");
    public By beginRegistration2 = By.xpath("//a[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M37GreyBlockWithButton_lnkBeginRegistration']");
    public By bannerReg = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M01YourSavingsMainBanner_lblBannerTitle']");

    //Check your details and continue
    private final By AddressLine1 = By.id("spanAddressLine1");
    private final By AddressLine2 = By.id("spanAddressLine2");
    private final By City = By.id("spanTown");
    private final By Country = By.id("spanCounty");
    private final By eMail = By.id("spanEmailAddress");
    private final By phoneNumber = By.id("spanMobileNumber");
    private final By eirCode = By.id("");
    private final By consent = By.id("vgCheck");
    private final By nextBtn = By.id("btnNext");
    private final By editDetails = By.id("btnEdit");


    //Address line1
    public void verifyAddressLine1() throws Exception {
        if (webUtil.isElementVisible(AddressLine1, 20)) {
            System.out.println(ExpeditedSteps.testData.get("addressLine1"));
            System.out.println(webUtil.getText(AddressLine1));
            if (!WordUtils.capitalizeFully(webUtil.getText(AddressLine1)).equals(ExpeditedSteps.testData.get("addressLine1"))) {
                throw new ExceptionUtils("Provided Address-line1 not matched");
            }
        } else {
            throw new ExceptionUtils("Address-Line1 x-path have been changed");
        }
    }

    //Address Line2
    public void verifyAddressLine2() throws Exception {
        if (!(ExpeditedSteps.testData.get("addressLine2").equalsIgnoreCase("null"))) {
            if (webUtil.isElementDisplayed(AddressLine2, 20)) {
                if (!webUtil.getText(AddressLine2).equals(ExpeditedSteps.testData.get("addressLine2"))) {
                    throw new ExceptionUtils("Provided Address-line2 not matched");
                }
            } else {
                throw new ExceptionUtils("Address-Line2 x-path have been changed");
            }
        }
    }

    //City
    public void verifyCity() throws Exception {
        if (webUtil.isElementDisplayed(City, 20)) {
            System.out.println(webUtil.getText(City));
            System.out.println(ExpeditedSteps.testData.get("town"));
            if (!webUtil.getText(City).equals(ExpeditedSteps.testData.get("town").toUpperCase())) {
                throw new ExceptionUtils("Provided town not matched");
            }
        } else {
            throw new ExceptionUtils("City x-path have been changed");
        }
    }

    //Country
    public void verifyCountry() throws Exception {
        if (webUtil.isElementDisplayed(Country, 20)) {
            webUtil.gettextlog(Country, String::equals, ExpeditedSteps.testData.get("Country_State_Region"), "Country");
            if (!webUtil.getText(Country).equals(ExpeditedSteps.testData.get("Country_State_Region"))) {
                throw new ExceptionUtils("Provided Country not matched");
            }
        } else {
            throw new ExceptionUtils("Country x-path have been changed");
        }
    }

    //eirCode
    public void verifyEirCode() throws Exception {
        if (!(ExpeditedSteps.testData.get("eircode").equalsIgnoreCase("null"))) {
            if (webUtil.isElementDisplayed(eirCode, 20)) {
                if (!webUtil.getText(eirCode).equals(ExpeditedSteps.testData.get("eircode"))) {
                    throw new ExceptionUtils("Provided eirCode content not matched");
                }
            } else {
                throw new ExceptionUtils("eirCode x-path have been changed");
            }
        }
    }

    //eMail
    public void verifyEmail() throws Exception {
        if (webUtil.isElementDisplayed(eMail, 20)) {
            if (!webUtil.getText(eMail).equals(ExpeditedSteps.testData.get("EmailAddress"))) {
                throw new ExceptionUtils("Provided mail not matched");
            }
        } else {
            throw new ExceptionUtils("E-Mail x-path have been changed");
        }
    }

    //phoneNumber
    public void verifyNumber() throws Exception {
        if (webUtil.isElementDisplayed(phoneNumber, 20)) {
            System.out.println(webUtil.getText(phoneNumber).replaceAll(" ", ""));
            System.out.println(ExpeditedSteps.testData.get("prefix").concat(ExpeditedSteps.testData.get("MobileNumber")));
            if (!webUtil.getText(phoneNumber).replaceAll(" ", "").contentEquals(ExpeditedSteps.testData.get("prefix").concat(ExpeditedSteps.testData.get("MobileNumber")))) {
                throw new ExceptionUtils("Provided Phone-Number not matched");
            }
        } else {
            throw new ExceptionUtils("Phone-Number x-path have been changed");
        }
    }


    //consent
    public void clickConsent() throws Exception {
        // webUtil.gettextlog(consent,String::equalsIgnoreCase,"I consent to the Terms and Conditions"); {
        WebElement element = driver.findElement(consent);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);

    }

    //nextBtn
    public void clickNextBtn() throws Exception {
        webUtil.gettextlog(nextBtn, String::equalsIgnoreCase, "Next");
        webUtil.click(nextBtn);

    }

    //Edit-Details
    public void clickEditDetails() throws Exception {

        webUtil.gettextlog(editDetails, String::equalsIgnoreCase, "Edit details");
        webUtil.click(editDetails);
    }


}
