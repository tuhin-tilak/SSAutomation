package com.cucumbercraft.POMPages.WebReg;

import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Postal_Address_Page {
    WebDriver driver;
    WebDriverUtil webUtil;

    public Postal_Address_Page(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    //X-Paths
    private final By header = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_title");
    private final String headerContent = "Postal address and contact details";
    private final By label = By.xpath("//h4[@id='p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_firstFormTitle']/preceding-sibling::p");
    private final String labelContent = "Please enter your address and contact details.";
    private final By postalAddress = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_firstFormTitle");
    private final String postalAddressContent = "Postal address";
    private final By postalAddressBelow = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_firstFormDescription");
    private final String postalAddressBelowContent = "This must be the same registered postal address you provided to State Savings.";
    private final By countryLabel = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_lblCountry");
    private final String countryLabelContent = "Country";
    private final By countryEnter = By.xpath("//label[@id='p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_lblCountry']/following-sibling::select");
    private final By addressLineLabel = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_lblAddressLine1");
    private final String addressLineLabelContent = "Address Line 1";
    private final By addressLineEnter = By.xpath("//input[@id='p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_txtAddressLine1']");
    private final By addressLineLabel1 = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_lblAddressLine2");
    private final String addressLineLabelContent1 = "Address Line 2 (optional)";
    private final By addressLine1Enter = By.xpath("//input[@id='p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_txtAddressLine2']");
    private final By townLabel = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_lblTown");
    private final String townLabelContent = "Town";
    private final By townEnter = By.xpath("//input[@id='p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_txtTown']");
    private final By countryStateRegionLabel = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_lblCounty");
    private final String countryStateRegionLabelContent = "County/State/Region";
    private final By countryStateRegionEnter = By.xpath("//select[@id='ddl_Counties']");
    private final By eirCodeLabel = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_lblEircode");
    private final String eirCodeLabelContent = "Eircode (optional)";
    private final By eirCodeEnter = By.xpath("//input[@id='p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_txtEircode']");
    private final By contactDetailHeader = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_secondFormTitle");
    private final String contactDetailHeaderContent = "Contact details";
    private final By emailAddress = By.xpath("//label[@id='p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_lblEmail']");
    private final By emailAddressEnter = By.xpath("//label[@id='p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_lblEmail']/following-sibling::input");
    private final By contactDetailParagraph = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_lblEmailDescription");
    private final String contactDetailParagraphContent = "We will send you a confirmation email and you will use it to log in later when Registration is validated.";
    private final By prefix = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_lblPrefix");
    private final String prefixContent = "Prefix";
    private final By prefixValue = By.xpath("//label[@id='p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_lblPrefix']//following-sibling::select");
    private final By mobileNumber = By.xpath("//label[@id='p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_lblPhone']");
    private final String mobileNumberContent = "Mobile Phone Number";
    private final By mobileNumberEnter = By.xpath("//input[@id='p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_txtPhone']");
    private final By contactDetailBottom = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_lblPhoneDescription");
    private final String contactDetailBottomContent = "We will need your mobile phone number to associate it with your State Savings Online service.";
    private final By warning = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_notificationAlertMessage");
    private final String warningContent = "Please ensure you have this mobile to hand in order to complete registration.";
    private final By backBtn = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_btnBack");
    private final By confirmBtn = By.xpath("//input[@id='p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_M35RegistrationContactDetails_btnBack']/following-sibling::input");

    public void verifyContent() throws Exception {
        if (webUtil.isElementDisplayed(header, 10)) {
            if (!webUtil.getText(header).equals(headerContent)) {
                throw new ExceptionUtils("Header content may have been changed");
            }
            if (!webUtil.getText(label).equals(labelContent)) {
                throw new ExceptionUtils("Header below label may have been changed");
            }
            if (!webUtil.getText(postalAddress).equals(postalAddressContent)) {
                throw new ExceptionUtils("Postal Header content may have been changed");
            }
            if (!webUtil.getText(postalAddressBelow).equals(postalAddressBelowContent)) {
                throw new ExceptionUtils("Postal Header below content may have been changed");
            }
            if (!webUtil.getText(countryLabel).equals(countryLabelContent)) {
                throw new ExceptionUtils("Country Label content may have been changed");
            }
            if (!webUtil.getText(addressLineLabel).equals(addressLineLabelContent)) {
                throw new ExceptionUtils("Address1 Label content may have been changed");
            }
            if (!webUtil.getText(addressLineLabel1).equals(addressLineLabelContent1)) {
                throw new ExceptionUtils("address2 Label content may have been changed");
            }
            if (!webUtil.getText(townLabel).equals(townLabelContent)) {
                throw new ExceptionUtils("Town Label content may have been changed");
            }
            if (!webUtil.getText(postalAddress).equals(postalAddressContent)) {
                throw new ExceptionUtils("Postal Header content may have been changed");
            }
            if (!webUtil.getText(countryStateRegionLabel).equals(countryStateRegionLabelContent)) {
                throw new ExceptionUtils("Country/State/Region Label content may have been changed");
            }
            if (!webUtil.getText(eirCodeLabel).equals(eirCodeLabelContent)) {
                throw new ExceptionUtils("Eir Code Label content may have been changed");
            }
            if (!webUtil.getText(contactDetailHeader).equals(contactDetailHeaderContent)) {
                throw new ExceptionUtils("Contact Detail Header content may have been changed");
            }
            if (!webUtil.getText(emailAddress).equals("Email address")) {
                throw new ExceptionUtils("Email Address Label content may have been changed");
            }

            if (!webUtil.getText(contactDetailParagraph).equals(contactDetailParagraphContent)) {
                throw new ExceptionUtils("Contact detail paragraph content may have been changed");
            }
            if (!webUtil.getText(prefix).equals(prefixContent)) {
                throw new ExceptionUtils("Prefix content may have been changed");
            }
            if (!webUtil.getText(mobileNumber).equals(mobileNumberContent)) {
                throw new ExceptionUtils("Mobile Number content may have been changed");
            }
            if (!webUtil.getText(contactDetailBottom).equals(contactDetailBottomContent)) {
                throw new ExceptionUtils("Contact Detail Bottom content may have been changed");
            }
            if (!webUtil.getText(warning).equals(warningContent)) {
                throw new ExceptionUtils("Warning content may have been changed");
            }
        }
    }

    public void clickNextBtn() throws Exception {
        verifyContent();
        if (webUtil.isElementclickable(confirmBtn, 10)) {
            if (driver.findElement(confirmBtn).getAttribute("value").equals("Next")) {
                try {
                    webUtil.click(confirmBtn);
                } catch (Exception e) {
                    throw new ExceptionUtils("Confirm button not clicked");
                }
            } else {
                throw new ExceptionUtils("Next button content is not similar");
            }
        } else {
            throw new ExceptionUtils("Confirm button X-Path may have been changed");
        }
    }

    public void clickBackbtn() throws Exception {
        verifyContent();
        if (webUtil.isElementclickable(backBtn, 10)) {
            if (driver.findElement(backBtn).getAttribute("value").equals("Back")) {
                try {
                    webUtil.click(confirmBtn);
                } catch (Exception e) {
                    throw new ExceptionUtils("Back button not clicked");
                }
            } else {
                throw new ExceptionUtils("Back button content is not similar");
            }
        } else {
            throw new ExceptionUtils("Back button X-Path may have been changed");
        }
    }
}