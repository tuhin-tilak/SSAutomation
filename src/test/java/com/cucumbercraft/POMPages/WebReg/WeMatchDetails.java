package com.cucumbercraft.POMPages.WebReg;

import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.ExpeditedSteps;
import groovy.util.logging.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j
public class WeMatchDetails {
    private final WebDriver driver;
    private final WebDriverUtil webUtil;

    public WeMatchDetails(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    private final By header = By.id("title");
    private final String headerContent = "We've matched your details";
    private final By headerBelowPara = By.id("description");
    private final String headerBelowContent = "Please enter your address and contact details.";
    private final By postalAddress = By.id("firstFormTitle");
    private final String postalAddressContent = "Postal address";
    private final By postalAddressBelow = By.id("firstFormDescription");
    private final String postalAddressBelowContent = "Your Registration Form will be sent to the address registered with Ireland State Savings.";
    private final String countryLabel = "lblCountry";
    private final String country = "ddl_Counties";
    private final By country12 = By.xpath("//div[@class='medium-8 columns input-group']/select");

    private final String addressLine1Label = "lblAddressLine1";
    private final String addressLine2Label = "lblAddressLine2";
    private final String townLabel = "lblTown";
    private final String addressLine = "txtAddressLine1";
    private final String addressLine2 = "txtAddressLine2";
    private final String country_state_region_Label = "lblCounty";
    private final String eirCodeLabel = "lblEircode";
    //contact details
    private final By contactDetailsHeader = By.id("secondFormTitle");
    private final By emailAddressLabel = By.id("lblEmail");
    private final By emailAddress = By.id("txtEmailAddress");
    private final By contactDetailsBelowPara = By.id("lblEmailDescription");
    private final String town = "txtTown";
    private final By numberPre = By.id("lblPrefix");
    private final By phoneNumber = By.id("lblPhone");
    private final By phoneNumberBelow = By.id("lblPhoneDescription");
    private final By mobileNumberEnter = By.id("txtPhone");
    private final By prefixValue = By.xpath("//*[@id='txtPhonePrefix-selectized']");

    private final By warning = By.id("notificationAlertMessage");
    private final By backBtn = By.id("btnBack");
    private final By nextBtn = By.id("btnNext");

    //Verify content on the page
    private void verifyHeader() throws Exception {
        webUtil.gettextlog(header, String::equalsIgnoreCase, headerContent);
    }

    private void verifyHeaderBelow() throws Exception {
        webUtil.gettextlog(headerBelowPara, String::equalsIgnoreCase, headerBelowContent);
    }

    private void verifyPostalAddress() throws Exception {
        webUtil.gettextlog(postalAddress, String::equalsIgnoreCase, postalAddressContent);

    }

    private void verifyPostalAddressBelow() throws Exception {
        webUtil.gettextlog(postalAddressBelow, String::equalsIgnoreCase, postalAddressBelowContent);

    }

    private void verifyCountryLabel() throws Exception {
        webUtil.gettextlog(By.id(countryLabel), String::equalsIgnoreCase, "Country");
    }

    private void verifyAddressLine1() throws Exception {
        webUtil.gettextlog(By.id(addressLine1Label), String::equalsIgnoreCase, "Address Line 1");

    }

    private void verifyAddressLine2() throws Exception {
        webUtil.gettextlog(By.id(addressLine2Label), String::equalsIgnoreCase, "Address Line 2 (optional)");

    }

    private void verifyTown() throws Exception {
        webUtil.gettextlog((By.id(townLabel)), String::equalsIgnoreCase, "Town");
    }

    private void verifyCountry_Region_State() throws Exception {
        webUtil.gettextlog((By.id(country_state_region_Label)), String::equalsIgnoreCase, "County/State/Region");

    }

    private void verifyEirCode() throws Exception {
        webUtil.gettextlog((By.id(eirCodeLabel)), String::equalsIgnoreCase, "Eircode (optional)");

    }

    private void verifyContactDetailsHeader() throws Exception {
        webUtil.gettextlog(contactDetailsHeader, String::equalsIgnoreCase, "Contact details");

    }

    private void verifyContactDetailsHeaderBelowPara() throws Exception {
        webUtil.gettextlog(contactDetailsBelowPara, String::equalsIgnoreCase, "We will send you a confirmation email that you can use to sign in once your Ireland State Savings Online registration is validated.");

    }

    private void verifyPreLabel() throws Exception {
        webUtil.gettextlog(numberPre, String::equalsIgnoreCase, "Prefix");


    }

    private void verifyNumberLabel() throws Exception {
        webUtil.gettextlog(phoneNumber, String::equalsIgnoreCase, "Mobile Number");


    }

    private void verifyNumberBelowPara() throws Exception {
        webUtil.gettextlog(phoneNumberBelow, String::equalsIgnoreCase, "We will need your mobile number to associate with your Ireland State Savings Online service.");

    }

    private void verifyWarning() throws Exception {

        webUtil.gettextlog(warning, String::equalsIgnoreCase, "Please ensure you have this mobile to hand in order to complete registration.");

    }

    private void verifyEmailAddressLabel() throws Exception {
        webUtil.gettextlog(emailAddressLabel, String::equalsIgnoreCase, "Email address");

    }

    private void verifyContentOnPage() throws Exception {
        verifyHeader();
        verifyHeaderBelow();
        verifyPostalAddress();
        verifyPostalAddressBelow();
        verifyCountryLabel();
        verifyAddressLine1();
        verifyAddressLine2();
        verifyTown();
        verifyCountry_Region_State();
        verifyEirCode();
        verifyContactDetailsHeader();
        verifyContactDetailsHeaderBelowPara();
        verifyPreLabel();
        verifyNumberLabel();
        verifyNumberBelowPara();
        verifyWarning();
        verifyEmailAddressLabel();
    }

    //Enter/Select functions
    public void selectCountry() throws Exception {
        try {
            String country1 = ExpeditedSteps.testData.get("country");
            if (country1.contains("null")) return;
            else webUtil.selectDropDown(country12, select -> select.selectByVisibleText(country1));
        } catch (Exception e) {
            throw new ExceptionUtils("Country is not Selected");
        }
    }

    public void enterNumber() throws Exception {
        try {
            String phone = ExpeditedSteps.testData.get("MobileNumber");
            if (phone.contains("null")) return;
            else webUtil.sendKeys(mobileNumberEnter, phone);
        } catch (Exception e) {
            throw new ExceptionUtils("Number is not entered");
        }
    }

    public void selectNumberPrefix() throws Exception {
        try {
            String numberPre1 = ExpeditedSteps.testData.get("prefix");
            // if (numberPre1.contains("null")) return;

            // Actions act= new Actions(driver);
            //act.moveToElement(driver.findElement(prefixValue)).perform();

            webUtil.click(prefixValue);
            webUtil.sendKeys(prefixValue, numberPre1);


            // webUtil.selectDropDown(prefixValue,  select -> select.selectByVisibleText(numberPre1));
        } catch (Exception e) {
            throw new ExceptionUtils("Number prefix is not Selected");
        }
    }

    public void enterEirCode() throws Exception {
        try {
            String eirCode = ExpeditedSteps.testData.get("eircode");
            if (eirCode.contains("null")) return;
            else webUtil.sendKeys(By.id(String.format(addressLine, eirCodeLabel)), eirCode);
        } catch (Exception e) {
            throw new ExceptionUtils("town is not entered");
        }
    }

    public void enterTown() throws Exception {
        try {
            String townvalue = ExpeditedSteps.testData.get("town");
            if (town.contains("null")) return;
            else webUtil.sendKeys(By.id(String.format(town, townLabel)), townvalue);
        } catch (Exception e) {
            throw new ExceptionUtils("town is not entered");
        }
    }

    public void enterAddressLine1() throws Exception {
        try {
            String addressLine1 = ExpeditedSteps.testData.get("addressLine1");
            if (addressLine1.contains("null")) return;
            else webUtil.sendKeys(By.id(String.format(addressLine, addressLine1Label)), addressLine1);
        } catch (Exception e) {
            throw new ExceptionUtils("AddressLine 1 is not entered");
        }
    }

    public void enterAddressLine2() throws Exception {
        try {
            String addressLine2 = ExpeditedSteps.testData.get("addressLine2");
            if (addressLine2.contains("null")) return;
            else webUtil.sendKeys(By.id(String.format(addressLine2, addressLine2Label)), addressLine2);
        } catch (Exception e) {
            throw new ExceptionUtils("AddressLine 2 is not entered");
        }
    }

    public void selectCountry_Region_State() throws Exception {
        try {
            String country_state_region = ExpeditedSteps.testData.get("Country_State_Region");
            if (country.contains("null")) return;
            else
                webUtil.selectDropDown(By.id(String.format(country, country_state_region_Label)), select -> select.selectByVisibleText(country_state_region));
        } catch (Exception e) {
            throw new ExceptionUtils("Country_state_region is not Selected");
        }
    }

    public void enterEmailAddress() throws Exception {
        try {
            String email = ExpeditedSteps.testData.get("EmailAddress");
            if (email.contains("null")) return;
            else webUtil.sendKeys(emailAddress, email);
        } catch (Exception e) {
            throw new ExceptionUtils("email is not entered");
        }
    }

    //click button functions
    public void clickBackBtn() throws Exception {
        verifyContentOnPage();
        webUtil.gettextlog(backBtn, String::equalsIgnoreCase, "Back");
        webUtil.click(backBtn);

    }

    public void clickNextBtn() throws Exception {
        verifyContentOnPage();
        webUtil.gettextlog(nextBtn, String::equalsIgnoreCase, "Next");
        webUtil.click(nextBtn);


    }


}

