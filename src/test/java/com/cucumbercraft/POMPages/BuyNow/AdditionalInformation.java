package com.cucumbercraft.POMPages.BuyNow;

import com.cucumbercraft.Models.PurchaseModel;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AdditionalInformation {
    private final By btnContinueToPayment = By.xpath("//button[text()='Continue to payment']");
    private final By txtUserDetails = By.xpath("//div[@class='ec-form__details-row']");
    WebDriver driver;
    WebDriverUtil webUtil;
    boolean CDE_PrimaryFlag, CDE_SecondaryFlag;
    BuyNowContext context = BuyNowContext.getInstance();
    private WebElement primaryCdeElements;
    private WebElement secondaryCdeElements;

    public AdditionalInformation(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);

    }


    public AdditionalInformation manageCde(PurchaseModel data) throws Exception {

        System.out.println("Joint" + context.isJoint());
        CDE_PrimaryFlag = context.isCDEPrimaryFlag();
        CDE_SecondaryFlag = context.isCDESecondaryFlag();


        if (!context.isOnlyPB() && !context.isContainsPBAndPBGift()) {
            handleCDEForNonPB(data.getCDE(), context.isJoint());
        }
        return this;
    }

    private void handleCDEForNonPB(List<String> cde, boolean isJoint) throws Exception {
        if (!isJoint && !CDE_PrimaryFlag) {
            handleSolePurchaseCDE(cde);
        } else if (!CDE_PrimaryFlag && !CDE_SecondaryFlag) {
            handleJointPurchaseCDE(cde);
        } else if (isJoint && !CDE_SecondaryFlag) {
            verifyPrimaryApplicantDetails();
            handleSecondaryApplicantCDE(cde);
        } else if (isJoint && !CDE_PrimaryFlag) {
            handlePrimaryApplicantCDE(cde);
            verifySecondaryApplicantDetails();
        } else {
            handleUnexpectedCDEState();
        }
    }

    private void handleSolePurchaseCDE(List<String> cde) throws Exception {
        waitForCDEPage();
        verifyCDEHeader("Additional information");
        cdeDropdown(cde.get(0), cde.get(1), cde.get(2), cde.get(3), cde.get(4));

        if (webUtil.isElementVisible(By.xpath("(//div[@class='ec-form__section'])[2]//h4"), 5)) {
            throw new ExceptionUtils("Sole Purchase: CDE displayed for secondary applicant");
        }


    }

    private void handleJointPurchaseCDE(List<String> cde) throws Exception {
        waitForCDEPage();
        verifyCDEHeader("Additional information");
        verifyPrimaryApplicantDetails();
        verifySecondaryApplicantDetails();
        cdeDropdown(cde.get(0), cde.get(1), cde.get(2), cde.get(3), cde.get(8));
        cdeDropdownJoint(cde.get(4), cde.get(5), cde.get(6), cde.get(7), cde.get(9));

    }

    private void handlePrimaryApplicantCDE(List<String> cde) throws Exception {
        waitForCDEPage();
        verifyCDEHeader("Additional information");

        verifyPrimaryApplicantDetails();
        cdeDropdown(cde.get(0), cde.get(1), cde.get(2), cde.get(3), cde.get(4));

    }

    private void handleSecondaryApplicantCDE(List<String> cde) throws Exception {
        waitForCDEPage();
        verifyCDEHeader("Additional information");

        verifySecondaryApplicantDetails();
        cdeDropdownJoint(cde.get(0), cde.get(1), cde.get(2), cde.get(3), cde.get(4));

    }

    private void handleUnexpectedCDEState() {
        try {
            waitForCDEPage();
        } catch (TimeoutException e) {
            return; // Ignore timeout and return
        }
        throw new RuntimeException("CDE Page should not exist for CDE Y user");
    }

    private void waitForCDEPage() {
        webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("cde-details"));
    }

    private void verifyCDEHeader(String expectedHeader) {
        By pageHeader = By.xpath("//h3[@class='ec-form__title']");
        webUtil.gettextlog(pageHeader, String::equals, expectedHeader);
    }

    private void verifyPrimaryApplicantDetails() {
        List<WebElement> primaryList = webUtil.getElements(txtUserDetails);
        webUtil.CompareString(StringUtils.normalizeSpace(primaryList.get(0).getText()), String::equals, context.getExpectedPrimaryName());
        webUtil.CompareString(primaryList.get(1).getText(), String::equals, context.getUserMapSole().get("dateOfBirth"));

        boolean value = context.isGuest()
                ? webUtil.CompareString(primaryList.get(2).getText(), String::equals, context.getUserMapSole().get("sscn"))
                : webUtil.CompareString(primaryList.get(2).getText(), String::equals, context.getUserMapSole().get("ppsNumber"));

        webUtil.CompareString(primaryList.get(3).getText(), String::equals, context.getUserMapSole().get("emailAddress"));
    }

    private void verifySecondaryApplicantDetails() {
        List<WebElement> primaryList = webUtil.getElements(txtUserDetails);
        webUtil.CompareString(StringUtils.normalizeSpace(primaryList.get(4).getText()), String::equals, context.getExpectedSecondaryName());
        webUtil.CompareString(primaryList.get(5).getText(), String::equals, context.getUserMapJoint().get("dateOfBirth"));
        webUtil.CompareString(primaryList.get(6).getText(), String::equals, context.getUserMapJoint().get("sscn"));
        webUtil.CompareString(primaryList.get(7).getText(), String::equals, context.getUserMapJoint().get("emailAddress"));
    }

    private void cdeDropdown(String nationality, String cob, String es, String eit, String income) throws InterruptedException {
        if (webUtil.isElementVisible(By.xpath("(//div[@class='ec-form__section'])[1]"), 10)) {

            webUtil.selectDropDown(By.id("ddlNationality"), select -> select.selectByVisibleText(nationality));
            webUtil.selectDropDown(By.id("ddlCountyOfBirth"), select -> select.selectByVisibleText(cob));
            webUtil.selectDropDown(By.id("ddlEmploymentStatus"), select -> select.selectByVisibleText(es));
            webUtil.selectDropDown(By.id("ddlIndustryType"), select -> select.selectByVisibleText(eit));
            webUtil.sendKeys(By.id("netIncome"), income);
        } else {
            throw new ExceptionUtils("Object Not Found");
        }

    }

    private void cdeDropdownJoint(String nationality, String cob, String es, String eit, String income) throws InterruptedException {

        if (webUtil.isElementVisible(By.xpath("(//div[@class='ec-form__section'])[2]"), 10)) {
            webUtil.selectDropDown(By.id("ddlNationalitySecond"), select -> select.selectByVisibleText(nationality));
            webUtil.selectDropDown(By.id("ddlCountyOfBirthSecond"), select -> select.selectByVisibleText(cob));
            webUtil.selectDropDown(By.id("ddlEmploymentStatusSecond"), select -> select.selectByVisibleText(es));
            webUtil.selectDropDown(By.id("ddlIndustryTypeSecond"), select -> select.selectByVisibleText(eit));
            webUtil.sendKeys(By.id("netIncomeSecond"), income);
        } else {
            throw new ExceptionUtils("Object Not Found");
        }
    }

    public AnpostPayment submitCDEdetails() {

        if (!context.isOnlyPB() && !context.isContainsPBAndPBGift()) {
            if (!context.isJoint() && !CDE_PrimaryFlag) {
                webUtil.clickLog(btnContinueToPayment, "Pay Button");
            } else if (!CDE_PrimaryFlag && !CDE_SecondaryFlag) {
                webUtil.clickLog(btnContinueToPayment, "Pay Button");
            } else if (context.isJoint() && !CDE_SecondaryFlag) {
                webUtil.clickLog(btnContinueToPayment, "Pay Button");
            } else if (context.isJoint() && !CDE_PrimaryFlag) {
                webUtil.clickLog(btnContinueToPayment, "Pay Button");
            } else {
                System.out.println("CDE is already added for user");
            }
        }

        return new AnpostPayment(driver);
    }
}
