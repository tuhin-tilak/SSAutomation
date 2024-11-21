package com.cucumbercraft.POMPages.BuyNow;

import com.cucumbercraft.Models.PurchaseModel;
import com.cucumbercraft.framework.APIReusuableLibrary;
import com.cucumbercraft.framework.WebDriverUtil;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.FileNotFoundException;
import java.util.Map;


public class YourDetail {

    private final By hdrPersonalDetails = By.xpath("//h4[text()='Personal Details']");
    private final By lblApplicationType = By.cssSelector("#lblUser_ApplicationType");
    private final By lblSole = By.cssSelector("label[for='chkUser_SingleAppType']");
    private final By lblSurname = By.xpath("//input[@id='txtUser_Surname']/preceding-sibling::label");
    private final By lblDOB = By.xpath("//input[@id='DayOfBirth']/preceding-sibling::label");
    private final By btnDOBtooltip = By.cssSelector("button[aria-describedby='lblUser_DoBTooltip']");
    private final By txtDOBtooltip = By.cssSelector("#lblUser_DoBTooltip");
    private final By lblPPSN = By.xpath("//input[@id='txtUser_PPSNumber']/preceding-sibling::label[1]");
    private final By btnPPSNtooltip = By.cssSelector("button[aria-describedby='lblUser_PPSNumberTooltip']");
    private final By txtPPSNtooltip = By.cssSelector("#lblUser_PPSNumberTooltip");
    private final By lblEmail = By.xpath("//input[@id='txtUser_Email']/preceding-sibling::label[1]");
    private final By jointRadio = By.cssSelector("#chkUser_JointAppType");
    private final By lblJoint = By.cssSelector("label[for='chkUser_JointAppType']");
    private final By lblJointSurname = By.xpath("//input[@id='txtUser_Surname2']/preceding-sibling::label");
    private final By lblJointDOB = By.xpath("//input[@id='DayOfBirth2']/preceding-sibling::label");
    private final By btnJointDOB = By.cssSelector("button[aria-describedby='lblUser_DoBTooltip2']");
    private final By txtJointDOBTooltip = By.cssSelector("#lblUser_DoBTooltip2");
    private final By lblJointPPSN = By.xpath("(//input[@id='txtUser_PPSNumber2']/preceding-sibling::label)[2]");
    private final By btnJointPPSNtooltip = By.cssSelector("button[aria-describedby='lblUser_PPSNumberTooltip2']");
    private final By txtJointPPSN = By.id("lblUser_PPSNumberTooltip2");
    private final By lblJointEmail = By.xpath("(//input[@id='txtUser_Email2']/preceding-sibling::label)[3]");
    private final By fldFirstName = By.id("txtGuest_FirstName");
    private final By fldJointFirstName = By.id("txtGuest2_FirstName");
    private final By fldPrimarySurname = By.id("txtGuest_Surname");
    private final By fldSecondarySurname = By.id("txtGuest2_Surname");
    private final By fldSurname = By.id("txtUser_Surname");
    private final By fldJointSurname = By.id("txtUser_Surname2");
    private final By fldDate = By.id("DayOfBirth");
    private final By fldMonth = By.id("MonthOfBirth");
    private final By fldYear = By.id("YearOfBirth");
    private final By fldJointDate = By.id("DayOfBirth2");
    private final By fldJointMonth = By.id("MonthOfBirth2");
    private final By fldJointYear = By.id("YearOfBirth2");
    private final By fldPPSN = By.id("txtUser_PPSNumber");
    private final By fldJointPPSN = By.id("txtUser_PPSNumber2");
    private final By fldEmail = By.id("txtUser_Email");
    private final By fldJointEmail = By.id("txtUser_Email2");
    private final By fldPBEmail = By.id("txtGuest_Email");
    private final By chkAgreeTerms = By.id("chkUser_Terms");
    private final By chkAgreeTermsGuest = By.id("chkGuest_Terms");
    private final By btnContOrder = By.xpath("//button[normalize-space()='Continue to your order']");
    private final String contentDOBTooltip = "A Minor (under 18) may only purchase Ireland State Savings products subject to written consent of parent/guardian. Where the holder is a Minor, download and complete the standard application form evidencing parental/guardian consent.";
    private final String contentPPSNTooltip = "Your Personal Public Service Number (PPSN) can be found on your Public Services Card or on any documentation from Revenue. Your State Savings Customer Number (SSCN) can be found on correspondence you receive from us.";
    private final By fldGuestAddress1 = By.id("txtGuest_Address1");
    private final By county = By.id("ddlGuest_AddressCounty");
    private final By area = By.xpath("// label[text()='Area']/ parent::div/ select");
    private final By chkTermsPB = By.id("chkGuest_Terms");
    private final By btnSubmitPBDetails = By.id("btnSubmitUser");
    private final By btnAddOrder = By.xpath("//button[@id='btnSubmitModalAdd']");
    private final By btnHolderJoint = By.xpath("//label[@for='chkJointHolderType']");
    WebDriver driver;
    WebDriverUtil webUtil;
    String journey;
    APIReusuableLibrary apiReusuableLibrary = new APIReusuableLibrary();
    private By btnJoint;
    private final By fldGuestAddress2 = By.id("txtGuest_Address2");
    private final By ddlGuestTitle = By.id("ddlGuest_Title");


    public YourDetail(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);

    }


    public YourDetail validateContentSoleYourDetails() {
        webUtil.gettextlog(lblApplicationType, String::equals, "Application Type", "Label");
        webUtil.gettextlog(lblSole, String::equals, "Single", "Label");
        webUtil.gettextlog(lblSurname, String::equals, "Surname", "Label");
        webUtil.gettextlog(lblDOB, String::equals, "Date of Birth", "Label");
        webUtil.clickLog(btnDOBtooltip, "Date of Birth Tooltip");
        webUtil.scrollToView(txtDOBtooltip);
        webUtil.gettextlog(txtDOBtooltip, String::equals, contentDOBTooltip, "Date Of Birth Tooltip Content");
        webUtil.scrollToView(btnPPSNtooltip);
        webUtil.gettextlog(lblPPSN, String::equals, "PPSN or SSCN", "Label");
        webUtil.clickLog(btnPPSNtooltip, "PPSNTooltip");
        webUtil.scrollToView(txtPPSNtooltip);
        webUtil.gettextlog(txtPPSNtooltip, String::equals, contentPPSNTooltip, "PPSN Tooltip Content");
        webUtil.gettextlog(lblEmail, String::equals, "Email", "Label");
        return this;
    }

    public YourDetail validateContentJointYourDetails() {
        webUtil.gettextlog(lblApplicationType, String::equals, "Application Type", "Label");
        webUtil.gettextlog(lblJoint, String::equals, "Joint", "Label");
        webUtil.gettextlog(lblJointSurname, String::equals, "Surname", "Label");
        if (!journey.contains("SignIn"))
            webUtil.gettextlog(lblDOB, String::equals, "Date of Birth", "Label");
        else
            webUtil.gettextlog(lblJointDOB, String::equals, "Date of Birth", "Label");
        webUtil.clickLog(btnJointDOB, "Date of Birth Tooltip");
        webUtil.scrollToView(txtJointDOBTooltip);
        webUtil.gettextlog(txtJointDOBTooltip, String::equals, contentDOBTooltip, "Date Of Birth Tooltip Content");
        webUtil.gettextlog(lblJointPPSN, String::equals, "PPSN or SSCN", "Label");
        webUtil.clickLog(btnJointPPSNtooltip, "PPSNTooltip");
        webUtil.scrollToView(txtJointPPSN);
        webUtil.gettextlog(txtJointPPSN, String::equals, contentPPSNTooltip, "PPSN Tooltip Content");
        webUtil.gettextlog(lblJointEmail, String::equals, "Email", "Label");
        return this;
    }

    public YourDetail selectJoint() {
        btnJoint = By.xpath("//label[@for='chkUser_JointAppType']");
        webUtil.click(btnJoint);
        return this;
    }


    public YourDetail withPrimaryFirstname(String surname) {
        webUtil.sendKeys(fldFirstName, surname);
        return this;
    }

    public YourDetail withSecondaryFirstname(String surname) {
        webUtil.scrollToView(fldJointFirstName);
        webUtil.sendKeys(fldJointFirstName, surname);
        return this;
    }

    public YourDetail withPrimarySurname(String surname) {
        webUtil.sendKeys(fldPrimarySurname, surname);
        return this;
    }

    public YourDetail withSecondarySurname(String surname) {
        webUtil.sendKeys(fldSecondarySurname, surname);
        return this;
    }

    public YourDetail withPrimaryApplicantSurname(String surname) {
        webUtil.sendKeys(fldSurname, surname);
        return this;
    }

    public YourDetail withPrimaryApplicantBirthdate(String[] birthdate) {
        if (birthdate != null && birthdate.length == 3) {
            webUtil.sendKeys(fldDate, birthdate[0]);
            webUtil.sendKeys(fldMonth, birthdate[1]);
            webUtil.sendKeys(fldYear, birthdate[2]);
        } else {
            throw new IllegalArgumentException("Birthdate must contain day, month, and year");
        }
        return this;
    }

    public YourDetail withPrimaryApplicantSSCN(String sscn) {
        webUtil.scrollToView(fldPPSN);
        webUtil.sendKeys(fldPPSN, sscn);
        return this;
    }

    public YourDetail withPrimaryApplicantEmail(String email) {
        webUtil.sendKeys(fldEmail, email);
        return this;
    }

    public YourDetail withPrimaryEmail(String email) {
        webUtil.sendKeys(fldPBEmail, email);
        return this;
    }

    public YourDetail withSecondaryApplicantSurname(String surname) {
        webUtil.sendKeys(fldJointSurname, surname);
        return this;
    }

    public YourDetail withSecondaryApplicantBirthdate(String[] birthdate) {
        if (birthdate != null && birthdate.length == 3) {
            webUtil.sendKeys(fldJointDate, birthdate[0]);
            webUtil.sendKeys(fldJointMonth, birthdate[1]);
            webUtil.sendKeys(fldJointYear, birthdate[2]);
        } else {
            throw new IllegalArgumentException("Birthdate must contain day, month, and year");
        }
        return this;
    }

    public YourDetail withSecondaryApplicantSSCN(String sscn) {
        webUtil.sendKeys(fldJointPPSN, sscn);
        return this;
    }

    public YourDetail withSecondaryApplicantEmail(String mail) {
        webUtil.sendKeys(fldJointEmail, mail);
        return this;
    }
@SneakyThrows
    public YourDetail validatePrimaryApplicantDetails(String email)  {
        By txtSurname = By.id("lblUser_Surname");
        By txtBirthdate = By.id("lblUser_DateOfBirth");
        By txtSSCN = By.id("lblUser_PpsNumber");
        By txtEmail = By.id("lblUser_Email");
        Map<String, String> userMap = apiReusuableLibrary.getFormattedUserDetailsByDate(email);
        webUtil.gettextlog(txtSurname, String::equals, userMap.get("lastName"));
        webUtil.gettextlog(txtBirthdate, String::equals, userMap.get("dateOfBirth"));
        webUtil.gettextlog(txtSSCN, String::equals, userMap.get("ppsNumber"));
        webUtil.gettextlog(txtEmail, String::equals, userMap.get("emailAddress"));

        return this;
    }

    public YourDetail validatePBApplicantDetails(String email) throws FileNotFoundException {
        By txtFirstName = By.id("lblFirstnameGuest");
        By txtSurname = By.id("lblSurnameGuest");
        By txtBirthdate = By.id("lblDOBGuest");
        By txtSSCN = By.id("lblPPSNGuest");
        By txtEmail = By.id("lblEmailGuest");
        By txtAddress1 = By.id("lblAddress1Guest");
        By txtCounty = By.id("lblCountyGuest");
        Map<String, String> userMap = apiReusuableLibrary.getFormattedUserDetailsByDate(email);
        webUtil.gettextlog(txtFirstName, String::equals, userMap.get("firstName"));
        webUtil.gettextlog(txtSurname, String::equals, userMap.get("lastName"));
        webUtil.gettextlog(txtBirthdate, String::equals, userMap.get("dateOfBirth"));
        webUtil.gettextlog(txtSSCN, String::equals, userMap.get("ppsNumber"));
        webUtil.gettextlog(txtEmail, String::equals, userMap.get("emailAddress"));
        webUtil.gettextlog(txtAddress1, String::equals, userMap.get("addressLine1"));
        webUtil.gettextlog(txtCounty, String::equals, userMap.get("county"));

        return this;
    }


    public YourDetail agreeTerms() {

        webUtil.javascriptClick(chkAgreeTerms);
        return this;
    }

    public YourDetail withAddress(String address) {
        String[] arr = address.split(",");
        webUtil.sendKeys(fldGuestAddress1, arr[0]);
        webUtil.sendKeys(fldGuestAddress2, arr[1]);
        webUtil.selectDropDown(county, select -> select.selectByVisibleText("Dublin"));
        if (webUtil.isElementVisible(area, 10))
            webUtil.selectDropDown(area, select -> select.selectByVisibleText("Dublin 6"));
        return this;
    }

    public YourDetail agreeTermsPB() {

        webUtil.scrollToView(chkTermsPB);
        webUtil.javascriptClick(By.xpath("//input[@id='chkGuest_Terms']"));
        return this;
    }

    public YourOrder submitDetails() {
        webUtil.clickLog(btnContOrder, "Continue to order button");
        return new YourOrder(driver);
    }

    public YourOrder submitDetailsForPB() {
        webUtil.clickLog(btnSubmitPBDetails, "Continue to Prize Bond details");
        return new YourOrder(driver);
    }

    public YourOrder submitHolderDetails() {
        webUtil.click(btnAddOrder);
        try {
            webUtil.getWebDriverWait().until(ExpectedConditions.attributeToBe(By.xpath("//section[@id='add-gift-modal']"), "class", "add_gift_modal_form ec-form add_gift_modal js-modal"));
        } catch (Exception ignored) {
        }
        return new YourOrder(driver);
    }


    public YourDetail enterPrimaryApplicantDetails(String surname, String[] birthdate, String SSCN, String email) {
        withPrimaryApplicantSurname(surname)
                .withPrimaryApplicantBirthdate(birthdate)
                .withPrimaryApplicantSSCN(SSCN)
                .withPrimaryApplicantEmail(email);
        return this;
    }

    public YourDetail enterSecondaryApplicantDetails(String surname, String[] birthdate, String SSCN, String email) {
        withSecondaryApplicantSurname(surname)
                .withSecondaryApplicantBirthdate(birthdate)
                .withSecondaryApplicantSSCN(SSCN)
                .withSecondaryApplicantEmail(email);
        return this;
    }

    public YourDetail enterPBApplicantDetails(String firstname, String surname, String email, String address) {

        webUtil.selectDropDown(ddlGuestTitle, select -> select.selectByVisibleText("Mr"));
        withPrimaryFirstname(firstname)
                .withPrimarySurname(surname)
                .withPrimaryEmail(email)
                .withAddress(address)
                .agreeTermsPB()
                .submitDetailsForPB();
        return this;
    }

    public YourDetail enterPrimaryHolderDetails(String firstname, String surname, String address) {
        try {
            webUtil.getWebDriverWait().until(ExpectedConditions.attributeToBe(By.xpath("//section[@id='add-gift-modal']"), "class", "add_gift_modal_form ec-form add_gift_modal js-modal active"));
        } catch (Exception ignored) {
        }

        webUtil.selectDropDown(ddlGuestTitle, select -> select.selectByVisibleText("Mr"));
        withPrimaryFirstname(firstname)
                .withPrimarySurname(surname)
                .withAddress(address);


        return this;

    }

    public YourDetail enterSecondaryHolderDetails(String firstname, String surname) {
        try {
            webUtil.getWebDriverWait().until(ExpectedConditions.attributeToBe(By.xpath("//section[@id='add-gift-modal']"), "class", "add_gift_modal_form ec-form add_gift_modal js-modal active"));
        } catch (Exception ignored) {
        }
        By title = By.id("ddlGuest2_Title");
        webUtil.selectDropDown(title, select -> select.selectByVisibleText("Mr"));
        withSecondaryFirstname(firstname)
                .withSecondarySurname(surname);

//        try {
//            webUtil.getWebDriverWait().until(ExpectedConditions.attributeToBe(By.xpath("//section[@id='add-gift-modal']"), "class", "add_gift_modal_form ec-form add_gift_modal js-modal"));
//        }catch (Exception ignored){}
        return this;

    }

    public YourDetail selectJointHolder(String joint) {

        if (joint.equals("Yes")) {
            webUtil.scrollToView(btnHolderJoint);
            webUtil.click(btnHolderJoint);

        }
        return this;
    }

    public Runnable enterPBHolderDetails(String holderType, PurchaseModel data) {
        Runnable pbHolderJoint = data.getProduct().indexOf("Prize Bonds as a Gift") != 0 ?
                () -> {
                    selectJointHolder(data.getPbHolderFlag());
                    enterPrimaryHolderDetails(data.getPbFirstName(),
                            data.getPbSurName(),
                            data.getPbAddress());
                    enterSecondaryHolderDetails(data.getPbFirstName2(),
                            data.getPbSurName2());
                    agreeTermsPB();
                    submitHolderDetails();
                } :
                () -> {
                    selectJointHolder(data.getPbHolderFlag()).
                            enterPrimaryHolderDetails(data.getPbFirstName(),
                                    data.getPbSurName(),
                                    data.getPbAddress());
                    enterSecondaryHolderDetails(data.getPbFirstName2(),
                            data.getPbSurName2());
                    submitHolderDetails();
                };


        Runnable pbHolderSole = data.getProduct().indexOf("Prize Bonds as a Gift") != 0 ?
                () -> {
                    enterPrimaryHolderDetails(data.getPbFirstName(), data.getPbSurName(), data.getPbAddress());
                    agreeTermsPB();
                    submitHolderDetails();
                } :
                () -> {
                    enterPrimaryHolderDetails(
                            data.getPbFirstName(),
                            data.getPbSurName(),
                            data.getPbAddress());
                    submitHolderDetails();
                };

        return holderType.equals("Yes") ? pbHolderJoint : pbHolderSole;

    }


}
