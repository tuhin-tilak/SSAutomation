package com.cucumbercraft.POMPages.RepayReinvest;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.Modals;
import com.cucumbercraft.framework.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static com.cucumbercraft.stepdefinitions.ReinvestSteps.getdata;

public class Prize_Bond {

    WebDriver driver;
    WebDriverUtil webUtil;

    Modals modal;

    private String total_amount;
    public static double prize_amount = 0;
    private static final Logger log = LogManager.getLogger(Prize_Bond.class);
    private final By reinvestOption = By.xpath("//div[@class='product-summary-menu']/button");
    private final By reinvest = By.xpath("//a[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_LinkButton2']");
    private final By totalAmount = By.id("p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_lblTotalAmt");
    private final By goBackBtn = By.id("p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_lnk_GoBack");
    private final By enterAmount = By.xpath("//input[@id='pbamount1']");
    private final By allocateFullAmountBtn = By.id("btnAllocateFull");
    private final By message = By.xpath("//div[@class='notification-max-amount']/div");
    private final By messageTooltip1 = By.xpath("//div[@class='notification-max-amount']/div/span/button");
    private final By messageTooltip2 = By.xpath("//*[normalize-space(text())='Amount (€)']/span/button");
    //    private By messageTooltip3 = By.xpath("//span[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Amount_maxAvailableTooltip']//preceding-sibling::button");
    //First Tooltip
    private final String messageTooltipMessage1 = "The minimum amount that can be reinvested is €25 (Prize Bonds) and €50 (other Ireland State Savings Products).";
    //second tooltip
    private final String messageTooltipMessage2 = "The reinvestment will be taken from your oldest Bond Range(s). " +
            "If the reinvestment amount is less than the full value of a Bond Range the reinvestment will be taken from " +
            "the start of the Bond Range. To choose a specific Bond Range for reinvestment please select the ‘Choose Bond Ranges’ option.";
    //Third Tooltip
    private final String messageTooltipMessage3 = "The reinvestment amount is calculated by unit value. Prize Bonds " +
            "purchased before 2002 have a unit value of €6.35 and Prize Bonds purchased after 2002 have unit value is €6.25";

    //Cashin Tooltip Content

    //First
    private final String repaymentMessageTooltipMessage1 = "The maximum available for online repayment excludes any Prize Bonds purchased within the last 90 days or previously selected for repayment or transfer.";
    //Second
    private final String repaymentMessageTooltipMessage2 = "The repayment will be taken from your oldest Bond Range(s). If the repayment amount is less than the full value of a Bond Range the repayment will be taken from the start of the Bond Range. To choose a specific Bond Range for repayment please select the ‘Choose Bond Ranges’ option.";
    //Third
    private final String repaymentMessageTooltipMessage3 = "The repayment amount is calculated by unit value. Prize Bonds purchased before 2002 have a unit value of €6.35 and Prize Bonds purchased after 2002 have unit value is €6.25";


    private final By label = By.xpath("//div[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Amount_pnlAmtSection']/label");
    private final By confirmBtn = By.xpath("//button[@id='btnSubmitAmount']");
    private final By cancelBtn = By.xpath("//input[@id='btnSubmitAmount']/preceding-sibling::button");
    private final By BlankAmountError = By.xpath("//span[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Amount_RFVPBAmount']");
    private final String lessAmountErrorMessage = "Enter an amount or select 'Allocate full amount'.";
    private final By moreThanAvailableAmountError = By.xpath("//span[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Amount_CVPBAmount']");
    private final String moreAmountErrorMessage = "Entered amount is greater than total amount.";
    private final By dobSort = By.xpath("//button[normalize-space()='Date of Issue']");
    private final By prizeBondRanges = By.xpath("//div[@class='m36-product-table m36-product-table--4-cols m36-product-table--holdings']/div[1]/div/div[@class='td td--range']");
    private final String prizeBondRangeCheck = ".//label[@for='%s']";
    private final String prizeBondRangeAmount = "//label[@for='%s']/parent::div/parent::div/parent::div/div[@class='td td--value']";
    private final By allPbRange = By.xpath("//label[contains(text(),'All')]");
    private final By bondRangeConfirmBtn = By.xpath("//button[@id='btnSubmitRange']");
    private final By bondRangeCancelBtn = By.xpath("//button[@id='btnSubmitRange']/preceding-sibling::button");
    private final By prizeBondRangeWindow = By.xpath("//a[@id='tab2']/span");
    private final String prizeBondRangeWindowVerify = "//div[@id='tab-two']//p[%s]";
    private final By PrizeBondRangeWindowToolTip = By.xpath("//div[@id='tab-two']//p[1]/span/button");
    private final By prizeBondRangeWindowToolTipContent = By.id("bondRangesTooltip");
    private final By prizeBondRangeWindowError = By.xpath("//div[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Amount_pnlRangeError']/span");
    private final String messa = "A confirmation email has been sent to your email address.\n" +
            "Please note that it will take 7-10 days for your request to appear in your online account";
    private final By footmessage = By.xpath("//p[@class='m38-cash-in-reinvest-journey__contact-text']");
    private final String m = "If you have any queries please contact us at 1850 30 50 60 / + 353 1 705 7200 quoting your transaction reference number.";
    private final By backSavBtn = By.xpath("//a[@id='p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_RepayReInvestThankYou_lnkBack']");
    private final By LeftPrizeBonds = By.xpath("//div[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Amount_bondsList']/div/div[1]/div[@class='tr']/div[@class='td td--range']");
    private final By gobackBtn = By.id("p_lt_WebPartZone3_zoneContent_pageplaceholder_p_lt_ctl00_RepayReInvestThankYou_lnkBack");
    private final By cashinOption = By.id("p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_LinkButton3");
    private final By nearestEligibleAmountLabel = By.xpath("//strong[contains(text(),'Showing')]");
    private final By nearestEligibleAmount = By.xpath("//span[@class='js-nearest-eligable-amount']");
    private final By nearestEligibletooltip = By.xpath("//span[@id='maxAvailableTooltip1']/parent::span");
    private final By nearestEligibleContent = By.xpath("//span[@id='maxAvailableTooltip1']");
    private final By prizeBondSettings = By.xpath("//*[@id='PBLink']");
    private final By prizeBondSettingsHeader = By.xpath("//*[@id='prizeBondsTitle']");

    private final By pageheader = By.xpath("//*[@id='prizeBondsTitle']");
    private final String PPOpt = "//label[@for='%s']/descendant::span[@class='switchbox__switch']";
    private final By autoReinvestheading = By.id("prizeBondsAutoreinvestTitle");
    private final By autoReinvestDescription = By.id("prizeBondsAutoreinvestDescription");
    private final By transferHeading = By.id("prizeBondsTransferTitle");
    private final By transferDescription = By.id("prizeBondsTransferDescription");
    private final By prizepaymodal = By.xpath("//*[@id='confirm-notification-change-modal']");
    private final By notificationEle = By.xpath("//div[@class=\"notification-box notification-box--info icon-success alert show\"");
    private final By notificationMsg = By.xpath("//*[@id='alertMessage']");
    private final By modalAddBankButton = By.xpath(".//button[@data-modal=\"add-bank-modal\"]");
    private final By ibanModal = By.xpath("//div[@class=\"ec-form dashboard-modal active\"]");
    private final By enterIBAN = By.xpath("//input[@placeholder='eg IE55PSTS99030112345678']");
    private final By clickCheckbox = By.xpath("//*[@for=\"checkTermsAndCondition\"]");
    private final By verifyBankbutton = By.xpath("//*[@id='btnConfirmChangeIban']");
    private final By ibanError = By.xpath("//*[@id='iban-required-error']");
    private final By checkboxError = By.xpath("//*[@id='iban-conform-error']");
    private final By invalidIBANError = By.xpath("//*[@id='iban-custom-error']");
    private final By otpFeild = By.xpath("//input[@id='securityCodeText']");
    private final By otpConfirm = By.xpath("//*[@id='btnVerifyCode']");
    APIReusuableLibrary api = new APIReusuableLibrary();


    private List<String> prizeBond;
    private List<String> selectPrizeBonds;
    private List<String> leftPrizeBonds;
    private String prizeOption;
    private By maxavailTooltipMsg;
    private By amountTooltipMsg;
    private By btnAllocateFull;
    private By btnSubmitAmount;
    private By btnCancel;
    private By btnNext;
    private By bondRangeName;


    public Prize_Bond(WebDriver driver) {
        this.driver = driver;
        this.webUtil = new WebDriverUtil(driver);
        this.modal = new Modals(driver);
    }


    public void reinvestOption() throws Exception {

        if (webUtil.isElementVisible(reinvestOption, 10)) {
            webUtil.scrollToView(reinvestOption);
            webUtil.click(reinvestOption);
        } else {
            throw new ExceptionUtils("Reinvest option button now clicked");
        }
    }

    public void checkReinvestLink() throws Exception {
        if (!webUtil.isElementclickable(reinvestOption, 20)) {
            log.info("Repay-Reinvest link not displayed");
        } else {
            throw new ExceptionUtils("Repay-Reinvest link is displayed");
        }
    }

    public void chooseReinvest() throws Exception {
        if (webUtil.isElementclickable(reinvest, 20)) {
            webUtil.click(reinvest);
        } else {
            throw new ExceptionUtils("Reinvest X-Path changed");
        }
    }

    public void chooseCashin() throws Exception {
        if (webUtil.isElementclickable(cashinOption, 20)) {
            webUtil.click(cashinOption);
        }
    }

    public void totalAmountReinvestPage() throws Exception {
        if (webUtil.isElementDisplayed(totalAmount, 10)) {
            total_amount = webUtil.getText(totalAmount);
            total_amount.replaceAll("[^A-Za-z€]", "");
            System.out.println(total_amount);
        }
    }

    public void enterAmount() {


        if (getdata.get("Prize Amount").contentEquals("blank"))
            driver.findElement(enterAmount).sendKeys("");
        else {

            webUtil.sendKeys(enterAmount, getdata.get("Prize Amount"));
        }


    }

    public void error() throws Exception {
        if (webUtil.isElementDisplayed(moreThanAvailableAmountError, 5)) {
            if (webUtil.getText(moreThanAvailableAmountError).contentEquals("Enter an amount or select 'Allocate full amount'."))
                log.info("Error message displayed " + webUtil.getText(moreThanAvailableAmountError) + " is verified");
            else
                throw new ExceptionUtils("Error message displayed " + webUtil.getText(moreThanAvailableAmountError) + " is not verified");
        } else if (webUtil.isElementDisplayed(BlankAmountError, 5)) {
            if (webUtil.getText(BlankAmountError).contentEquals("Enter an amount or select 'Allocate full amount'."))
                log.info("Error message displayed " + webUtil.getText(BlankAmountError) + " is verified");
            else
                throw new ExceptionUtils("Error message displayed " + webUtil.getText(BlankAmountError) + " is not verified");
        } else {
            throw new ExceptionUtils("More amount error message x-path not found!");
        }
    }

    public void contentVerify()  {


        Modals.amount = webUtil.getText(By.id("maxAmt")).replaceAll("[^0-9.,]", "");
        System.out.println(webUtil.getText(message));
        System.out.println(Modals.amount);
        var expected = "Maximum available for online %s: €" + Modals.amount + " i";
        var maxAvailTooltipMsg = By.xpath("//label[@id='maxAvailableTooltip11' and @style='display: block;']");
        var amountTooltipMsg = By.xpath("//label[@id='maxAvailableTooltipRange' and @style='display: block;']");
        btnAllocateFull = By.id("btnAllocateFull");
        btnSubmitAmount = By.id("btnSubmitAmount");
        btnCancel = By.xpath("//button[@id='btnSubmitAmount']/preceding-sibling::button");
        switch (getdata.get("MethodType")) {
            case "Reinvest":
                var expectedMessage = String.format(expected, "reinvest").replaceAll("online ", "");
                webUtil.gettextlog(message, String::equals, expectedMessage);
                webUtil.click(messageTooltip1);
                webUtil.gettextlog(maxAvailTooltipMsg, String::equals, messageTooltipMessage1);
                webUtil.click(messageTooltip2);
                webUtil.gettextlog(amountTooltipMsg, String::equals, messageTooltipMessage2);
                webUtil.gettextlog(btnAllocateFull, String::equals, "Allocate full amount");
                webUtil.gettextlog(btnSubmitAmount, String::equals, "Confirm");
                webUtil.gettextlog(btnCancel, String::equals, "Cancel");
                break;
            case "Cash-In":
                webUtil.gettextlog(message, String::equals, String.format(expected, "repayment"));
                webUtil.click(messageTooltip1);
                webUtil.gettextlog(maxAvailTooltipMsg, String::equals, repaymentMessageTooltipMessage1);
                webUtil.click(messageTooltip2);
                webUtil.gettextlog(amountTooltipMsg, String::equals, repaymentMessageTooltipMessage2);
                webUtil.gettextlog(btnAllocateFull, String::equals, "Allocate full amount");
                webUtil.gettextlog(btnSubmitAmount, String::equals, "Confirm");
                webUtil.gettextlog(btnCancel, String::equals, "Cancel");
        }


    }

    public void clickConfirmButton() throws Exception {



        webUtil.click(confirmBtn);

        if (Integer.parseInt(getdata.get("Prize Amount")) % 6.25 != 0 || Integer.parseInt(getdata.get("Prize Amount")) % 6.35 != 0) {
            if (webUtil.isElementDisplayed(nearestEligibleAmountLabel, 20)) {
                if (webUtil.getText(nearestEligibleAmountLabel).contentEquals("Showing nearest eligible amount:")) {
                    int value = (int) (Integer.parseInt(getdata.get("Prize Amount")) / 6.25);
                    int value1 = (int) (Integer.parseInt(getdata.get("Prize Amount")) / 6.35);
                    double amount = 6.25 * value;
                    double amount1 = 6.35 * value1;
                    System.out.println(amount1);
                    System.out.println(webUtil.getText(nearestEligibleAmount));
                    System.out.println("€".concat(String.format("%,.2f", amount)));
                    if (webUtil.getText(nearestEligibleAmount).contentEquals(String.format("€%,.2f", amount)) ||
                            webUtil.getText(nearestEligibleAmount).contentEquals(String.format("€%,.2f", amount1))) {
                        try {
                            webUtil.waitUntilElementLocated(nearestEligibletooltip, 10);
                            webUtil.click(nearestEligibletooltip);
                            webUtil.waitUntilElementLocated(nearestEligibleContent, 10);
                            if (webUtil.getText(nearestEligibleContent).contentEquals("The reinvestment amount is calculated by unit value. " +
                                    "Prize Bonds purchased before 2002 have a unit value of €6.35 and Prize Bonds purchased after " +
                                    "2002 have unit value is €6.25")) {
                                webUtil.click(confirmBtn);
                            } else if (webUtil.getText(nearestEligibleContent).contentEquals("The repayment amount is calculated by unit value. " +
                                    "Prize Bonds purchased before 2002 have a unit value of €6.35 and Prize Bonds purchased after " +
                                    "2002 have unit value is €6.25")) {
                                webUtil.click(confirmBtn);
                            } else {
                                throw new ExceptionUtils("Nearest Eligible Amount Tooltip content not verified");
                            }
                        } catch (Exception e) {
                            throw new ExceptionUtils("Nearest Eligible Amount Tooltip not clicked");
                        }
                    } else {
                        throw new ExceptionUtils("Nearest Eligible Amount value not matched");
                    }
                } else {
                    throw new ExceptionUtils("Nearest Eleigible Amount label content not matched");
                }
            }
        }
//        }
    }

    public void clickCancelButton() throws InterruptedException {
        if (webUtil.isElementclickable(cancelBtn, 20)) {
            webUtil.click(cancelBtn);
        }
    }

    public void clickAllocateFullAmount() throws Exception {
        if (webUtil.isElementclickable(allocateFullAmountBtn, 20)) {
            webUtil.click(allocateFullAmountBtn);
        }
    }

    /**
     * Sort the holding in Desc order
     *
     * @throws Exception
     */
    public void sort() throws Exception {
        if (webUtil.isElementclickable(dobSort, 10)) {
            webUtil.click(dobSort);
        }
        getPrizeBondRanges();
    }

    /**
     * Get all the prize bonds from the holding page
     */
    public void getPrizeBondRanges() throws Exception {
        List<WebElement> list = driver.findElements(prizeBondRanges);
        prizeBond = new ArrayList<>();
        int i = 0;
        for (WebElement web : list) {
            if (i == 0) {
                i++;
                continue;
            }
            if (i % 10 == 0) {
                webUtil.click(By.xpath("//button[4]"));
            }
            i++;
            prizeBond.add(web.getText());
        }
    }

    public void goBackHomepage() {
        try {
            webUtil.click(gobackBtn);
            webUtil.waitForPageLoaded();
        } catch (Exception e) {
            throw new ExceptionUtils("Go back home page button not working");
        }
    }

    /**
     * Select prize bonds from the given list
     *
     * @param prizeBonds
     * @throws Exception
     */
    public void SelectPrizeBonds(String prizeBonds){
        btnNext = By.xpath("//button[@class='next']");
        selectPrizeBonds = prizeBonds.contains(",") ? new ArrayList<>(Arrays.asList(prizeBonds.split(","))) : new ArrayList<>(Arrays.asList(prizeBonds));

        webUtil.waitUntilElementVisible(By.xpath("//div[@class='tr']"), 10);
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='tr']"));
        String expected;
        int i = 0;
        for (WebElement list1 : list.subList(1, list.size())) {
            for (String str : selectPrizeBonds) {

                bondRangeName = By.xpath(".//*[@class='td td--range']");
                if (list1.findElement(bondRangeName).getText().contains(str)) {
                    expected = list1.findElement(bondRangeName).getText();
                    System.out.println("Found the bond range");
                    webUtil.scrollToView(By.xpath(String.format(prizeBondRangeCheck, str)));
                    list1.findElement(By.xpath(String.format(prizeBondRangeCheck, str))).click();
                    prize_amount += Double.parseDouble(list1.findElement(By.xpath(".//div[@class='td td--value']")).getText().replaceAll("[^0-9.]", "").trim());
                    System.out.println(selectPrizeBonds);
                    System.out.println(expected);
                    selectPrizeBonds.remove(str);
                    break;
                }
                i++;
                if (i % 10 == 0) {

                    webUtil.scrollToView(btnNext);
                    webUtil.click(btnNext);
                }
            }
        }
    }

    public void selectAllPBRange() {
        webUtil.waitUntilElementVisible(allPbRange, 20);
        try {
            webUtil.click(allPbRange);

        } catch (Exception e) {
            throw new ExceptionUtils("Prize Bond Range All checkbox click interrupted!");
        }
    }

    /**
     * Click on the prize bond range confirm button
     *
     * @throws Exception
     */
    public void clickPrizeBondConfirmBtn()  {

        webUtil.click(bondRangeConfirmBtn);

    }

    /**
     * Click on choose bond range
     *
     * @throws Exception
     */
    public void clickBondRangeWindow(){
        webUtil.waitFor(10000);
        webUtil.click(prizeBondRangeWindow);

    }

    public void verifyBondRangeWindowError() throws Exception {
        if (webUtil.isElementDisplayed(prizeBondRangeWindowError, 20)) {
            if (!webUtil.getText(prizeBondRangeWindowError).trim().contentEquals("Please select the bond range(s) from the list below.")) {
                throw new ExceptionUtils("Error message not verified!");
            }
        }
    }

    public void verifyBondRangeWindow() {
        String toottipContent = "The maximum available for online %s excludes any Prize Bonds purchased within the last 90 days or previously selected for repayment or transfer.";
        String expectedTooltipContent = getdata.get("MethodType").equals("Cash-In") ? String.format(toottipContent, "repayment") : String.format(toottipContent, "reinvestment");

        var pbContent1 = By.xpath(String.format(prizeBondRangeWindowVerify, "1").trim());
        webUtil.gettextlog(pbContent1, String::equals, "Select the bond ranges from the list below i");
        webUtil.click(PrizeBondRangeWindowToolTip);
        webUtil.gettextlog(prizeBondRangeWindowToolTipContent, String::equals, expectedTooltipContent);





    }

    /**
     * choose if the prize bond range is still visible in reinvest prize bond range
     *
     * @return
     *
     */
    public boolean chooseBondRangesVerify() {
        List<WebElement> list = driver.findElements(LeftPrizeBonds);
        leftPrizeBonds = new ArrayList<>();
        int i = 0;
        for (WebElement web : list) {
            if (i % 10 == 0) {
                webUtil.click(By.xpath("//button[4]"));
            }
            i++;
            leftPrizeBonds.add(web.getText());
        }
        return leftPrizeBonds.containsAll(prizeBond);
    }

    public void clickPrizeBondSettings() {
        String expectedPageHeader = "Prize Bond winnings payment option";
        String PBsetting = "Prize Bond Settings";
        webUtil.gettextlog(prizeBondSettings, String::equals, PBsetting);
        webUtil.click(prizeBondSettings);
        webUtil.gettextlog(prizeBondSettingsHeader, String::equals, expectedPageHeader);
    }

    /**
     * @param option
     */
    public void clickPrizePaymentOption(String option) throws Exception {
        prizeOption = option;
        if (option.equalsIgnoreCase("autoreinvest")) {
            autoReinvestOption();
        } else {
            transferOption();
        }

    }

    public void autoReinvestOption() throws Exception {
        String header = "Auto reinvest winnings";
        String description = "Your Prize Bond winnings will automatically be reinvested into new Prize Bonds.";
        webUtil.gettextlog(autoReinvestheading, String::equals, header);
        webUtil.gettextlog(autoReinvestDescription, String::equals, description);

        webUtil.scrollToView(By.xpath(String.format(PPOpt, "checkAutoReinvest")));
        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
        webUtil.click(By.xpath(String.format(PPOpt, "checkAutoReinvest")));
        webUtil.waitForPageLoaded();
        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
        ExtentCucumberAdapter.addTestStepLog("Prize Payment Option:Auto Reinvest is clicked");
    }

    public void transferOption() throws Exception {
        String header = "Transfer winnings";
        String description = "Your Prize Bond winnings will be transferred to your nominated bank account.";
        webUtil.gettextlog(transferHeading, String::equals, header);
        webUtil.gettextlog(transferDescription, String::equals, description);

        String enabled = webUtil.getDriver().findElement(By.xpath("//input[@type='checkbox' and @id='transferwinnings']")).getAttribute("checked");
        if (webUtil.isElementDisplayed(By.xpath(String.format(PPOpt, "transferwinnings")), 5)) {
            webUtil.scrollToView(By.xpath(String.format(PPOpt, "transferwinnings")));
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            webUtil.click(By.xpath(String.format(PPOpt, "transferwinnings")));
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            ExtentCucumberAdapter.addTestStepLog("Prize Payment Option:Transfer winnings is clicked");
        }

    }

    public void prizePaymentModal() throws Exception {
        WebElement ppModal = webUtil.getDriver().findElement(prizepaymodal);
        String modalHeader = "Confirm Changes";
        String modalDescription = "You are about to update your Prize Payment option. Please select ‘Confirm’ to save this change or ‘Cancel’ if you do not wish to proceed with the update.";
        By modalConfirmbutton = By.id("btnConfirmChanges");

        if (ppModal.isDisplayed()) {
            webUtil.gettextlog(By.xpath("//*[@id='confirm-notification-change-modal']//h2"), String::equals, modalHeader);
            webUtil.gettextlog(By.xpath("//*[@id='confirm-notification-change-modal']//p"), String::equals, modalDescription);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            ExtentCucumberAdapter.addTestStepLog("Prize Payment Modal content verified");
        }
        ppModal.findElement(modalConfirmbutton).click();
        webUtil.waitForPageLoaded();
        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
    }

    public void validatePPoption() {
        switch (prizeOption) {
            case "Autoreinvest":
                checkTransfer();
                break;
            case "Transfer":
                checkAutoreinvest();
                break;
            default:
                log.info("First user");

        }


    }

    public void validateNotification(){
        String expectedMsg = "Thank you, your prize bond settings have been updated";
        webUtil.gettextlog(notificationMsg, String::equals, expectedMsg);

    }

    public void checkTransfer() {
        String enabled = webUtil.getDriver().findElement(By.xpath("//input[@type='checkbox' and @id='transferwinnings']")).getAttribute("checked");
        webUtil.scrollToView(By.xpath("//input[@type='checkbox' and @id='transferwinnings']"));
        if (enabled == null)
            log.info("Transfer Winnings button is disabled");
        else
            log.info("Transfer Winnings button status: " + enabled);

    }

    public void checkAutoreinvest() {
        String enabled = webUtil.getDriver().findElement(By.xpath("//input[@type='checkbox' and @id='checkAutoReinvest']")).getAttribute("checked");
        if (enabled == null)
            log.info("Auto Reinvest button is disabled");
        else
            log.info("Auto Reinvest button status: " + enabled);
    }

    public void addBankDetailsModal() throws Exception {
        String header = "Add bank details";
        String desc = "You must add your bank details to enable this option.";
        WebElement element = webUtil.getDriver().findElement(By.id("must-add-iban-modal"));
        if (element.isDisplayed()) {

            if (element.findElement(modalAddBankButton).isDisplayed())
                element.findElement(modalAddBankButton).click();
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));

            webUtil.waitForPageLoaded();

        }
    }


    public Prize_Bond inputIBAN(String IBAN) throws Exception {
        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
//        if(webUtil.isElementDisplayed(ibanModal,5))
        webUtil.sendKeys(enterIBAN, IBAN);
        return this;

    }

    public Prize_Bond clickIBANCheckbox() throws InterruptedException {
        if (webUtil.isElementDisplayed(clickCheckbox, 5))
            webUtil.click(clickCheckbox);
        return this;
    }

    public Prize_Bond clickVerifyBank() throws InterruptedException {
        if (webUtil.isElementDisplayed(verifyBankbutton, 5))
            webUtil.click(verifyBankbutton);
        return this;
    }

    public void enterOTP(String number) throws Exception {
        webUtil.waitForPageLoaded();
        String otp = null;
        if (webUtil.isElementDisplayed(otpFeild, 5)) {
            Properties prop = Settings.getInstance();
            String url = prop.getProperty("OTPEndPoint");
            otp = api.getOTP(url, 200, number);
            webUtil.sendKeys(otpFeild, otp);
        }
        if (webUtil.isElementDisplayed(otpConfirm, 5))
            webUtil.click(otpConfirm);
        webUtil.waitForPageLoaded();
        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));


    }

    public void addBankJourney(String journey, String IBAN, String number) throws Exception {
        String error = "This is not a valid IBAN. A valid IBAN consists of a two-letter country code, followed by two check digits, and up to thirty-five alphanumeric characters.";
        switch (journey) {
            case "Blank":
                clickVerifyBank();
                webUtil.gettextlog(ibanError, String::equals, "Please enter a value for your IBAN.");
                webUtil.gettextlog(checkboxError, String::equals, "Please confirm");
                break;
            case "InvalidIBAN":
                inputIBAN(IBAN).clickIBANCheckbox().clickVerifyBank();
                webUtil.gettextlog(invalidIBANError, String::equals, error);
                break;
            case "Yes":
                webUtil.waitFor(6000);
                checkTransfer();
                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));

        }

    }


}
