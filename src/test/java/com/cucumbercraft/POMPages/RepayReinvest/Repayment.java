package com.cucumbercraft.POMPages.RepayReinvest;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.Security_Page;
import com.cucumbercraft.framework.APIReusuableLibrary;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.PortalLoginSteps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.cucumbercraft.stepdefinitions.ReinvestSteps.getdata;

public class Repayment {
    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private final Holdings holdings;
    public Security_Page sp;
    static final Logger log = LogManager.getLogger(Repayment.class);
    private final PortalLoginSteps portal = new PortalLoginSteps();
    private final APIReusuableLibrary api = new APIReusuableLibrary();

    public Repayment(WebDriver driver) {
        this.driver = driver;
        this.webUtil = new WebDriverUtil(driver);
        holdings = new Holdings(driver);
    }

    public int matureAmount;
    public static double avilableAmount;
    public By verifyCashInPage = By.xpath("//div[@class='product-options-info__heading']/h2");
    public By verifyPartialNumb = By.xpath("//p[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_para_Title']");
    public By cashInEnterAmount = By.xpath("//input[@id='txtCashAmount']");
    public By fullAmount = By.xpath("//button[@class='button button--secondary button--alt js-allocate-full-amount']");
    public By cnfrmButton = By.xpath("//input[@id='btnSubmitAmount']");
    public By cashinConfirmbtn = By.id("submitButton");
    private final By cancelButton = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_btn_ConfirmNew']//preceding-sibling::button");
    public By goBackBtn = By.xpath("//a[@class='gtm-cta button button--secondary  js-closeModal' and contains(text(),'Go back')]");

    public By cnfrmBtnModal = By.id("p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_lbCashInAllocateFull");
    public By cancelBtnModal = By.xpath("//button[@class='gtm-cta button button--primary  js-confirm-allocate-all']/following-sibling::a");
    public By matureAmountXpath = By.xpath("//span[@class='footer__ec-total-amount js-available-funds']");
    public By editYourAmount = By.xpath("//a[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_lbEditOrder']");
    public By cancelBtn = By.xpath("//a[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_lnk_Cancel']");

    public By cnfrmTransactionBtn = By.xpath("//a[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_lnk_Cancel']/following-sibling::input");
    public By confirmTransactionReview = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_btnConfirmReview']");

    public By checkboxTermCondition = By.xpath("//label[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_lblTermCondition']");
    public By IBAN = By.id("p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_para_Title");
    private By enterIban = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_txtIbanAccount']");
    private final By enterIban1 = By.xpath("//*[@id=\"txtIbanAccount\"]");
    private By ibanLabel = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_txtIbanAccount']/parent::div/label");
    private final By ibanToolTip = By.xpath("//span//button[contains(text(),'i')]");
    private final By ibanToolTipContent = By.xpath("//span//button[contains(text(),'i')]//following-sibling::span");
    private By ibanHeader = By.xpath("//section[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_addIbanModal']//h4");
    private By ibanCheckboxContent = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_checkTermsAndCondition']/parent::span/label");
    private By ibanCheckbox = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_checkTermsAndCondition']");
    private By ibanCancelBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_btnAddIban']/preceding-sibling::button");
    private By ibanConfirmBtn = By.id("btnConfirmChangeIban");
    private final By ibanInvalidError = By.xpath("//span[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_CVCheckTermsAndConditions']");
    private final By ibanError = By.xpath("//span[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_CVIsIbanValid']");
    private final By ibanerror = By.xpath("//span[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_lblIBANError']");
    private final By checkboxError = By.xpath("//span[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_CVCheckTermsAndConditions']");
    private String ibanContent = "//section[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_addIbanModal']//p[%d]";
    static public String ibanNumber;
    private By addIbanBtn = By.xpath("//div[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_block_Reinvest1']//following-sibling::div[1]//a[contains(text(),'Add your bank details')]");
    private final String str1 = "This IBAN will be saved and used for all of your future State Savings sole payments (online and/or offline) and the transfer of any sole Prize Bond winnings to your bank account, if you have previously instructed us to process prize payments in this way.";
    private String str2 = "Important Note: If you have previously provided IBAN details to State Savings, the IBAN you provided here will replace existing details on our records.";
    private final String str3 = "With State Savings Online, you may cash in your sole holdings, and request that repayment is transferred to your personal bank account. In order to do this, you need to provide your International Bank Account Number (IBAN).";
    private final String str4 = "We support payment transfers to bank accounts located within the Single Euro Payments Area (SEPA) only. SEPA payments can only be processed in euro.";
    private final String str5 = "For International (non SEPA) payment transfers, please contact the State Savings.";
    private final By noAmountError = By.id("cashInRequiredAmountError");
    private final By moreAmountError = By.id("cashInAmountError");
    private final String ibanTooltipContent = "Your IBAN starts with two letters to denote the SEPA country where the account is based, e.g. IE for Ireland. Your IBAN is printed on your bank statement. You may also request it directly from your bank.";
    private final By commonallocateFullAmount = By.xpath("//button[contains(@class,\"allocate-full-amount\")]");

    //Security page when iban adding jouney
    By header = By.xpath("//section[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_oneTimeSecurityCodePinModal']/h4");
    By Securityheader = By.xpath("//section[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_oneTimeSecurityCodePinModal']/h4");
    By para = By.xpath("//section[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_oneTimeSecurityCodePinModal']/p");
    String paraContent = "Enter the verification code we've sent to the registered mobile phone number ending with ~";
    By label = By.xpath("//label[@for='txtOneTimeSecurityCodePin']");
    By enterOTPField = By.xpath("//*[@id='securityCodeText']");
    By didnotreciveLink = By.xpath("//a[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_lnkResetPinCode']");
    By confirmBtn = By.id("btnVerifyCode");
    By cancelBtn1 = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_btnVerifySecurityCodePin']/preceding-sibling::button");
    By thankYouMessage = By.xpath("//strong[contains(text(),'Thank you')]");


    /**
     * verify the content on cashin page
     *
     * @throws Exception
     */
    public void verifyCashPage() throws Exception {
        if (webUtil.isElementDisplayed(verifyCashInPage, 20)) {
            if (webUtil.getText(verifyCashInPage).equals("Cash In")) {
//                if (webUtil.isElementDisplayed(IBAN, 20)) {
//                 String  iban= getdata.get("IBAN_Number");
//                    String lstFourDigit=iban.substring()
//                    String actual = webUtil.getText(By.xpath("//p[contains(@id,'Repay_ReInvest_Detail_para')]")).replaceAll("\\n"," ");
//                    String expected = "All repayments of a State Savings product are subject to Terms and Conditions, requiring 7 business days’ notice. In the case of a maturing account the repayment will not be verified before the maturity date.  When your repayment has been verified you will be issued with a Payment Advice Notice by email. The repayment value will be transferred to the bank account ending x5678 within 5 days of you receiving the Payment Advice Notice.";
//                    Assertions.assertThat(actual).contains(expected);
//                    ibanNumber = webUtil.getText(IBAN).replaceAll("[^0-9]", "");
//                }
            }
        }
    }

    /**
     * function used for verifying number on
     *
     * @param number
     * @throws Exception
     */
    public void verifyNumber(String number) throws Exception {
        if (webUtil.isElementDisplayed(verifyPartialNumb, 20)) {
            if (!webUtil.getText(verifyPartialNumb).contains(number.substring(number.length() - 4))) {
                System.out.println("Phone number same nahi hai...");
            }
        }
    }

    /**
     * function used for entering amount in the cashin page
     *
     * @throws InterruptedException
     */
    public void enterAmount() throws InterruptedException {
        if (webUtil.isElementDisplayed(cashInEnterAmount, 20)) {
            webUtil.scrollToView(cashInEnterAmount);
            if (getdata.get("Amount").equalsIgnoreCase("Blank")) {
                driver.findElement(cashInEnterAmount).sendKeys("", Keys.TAB);
            } else {
                driver.findElement(cashInEnterAmount).sendKeys(getdata.get("Amount"), Keys.TAB);
            }
        }
    }

    /**
     * function used for entering amount in the cashin page
     *
     * @throws InterruptedException
     */
    public void enterAmount(String amount) throws InterruptedException {
        if (webUtil.isElementDisplayed(cashInEnterAmount, 20)) {
            webUtil.scrollToView(cashInEnterAmount);
            if (getdata.get("Amount").equalsIgnoreCase("Blank")) {
                driver.findElement(cashInEnterAmount).sendKeys("", Keys.TAB);
            } else {
                driver.findElement(cashInEnterAmount).sendKeys(amount, Keys.TAB);
            }
        }
    }

    public void pbVerify() throws Exception {
        String pbParagraph2 = "//section[@class='dashboard-modal__content js-modal active']//p[%d]";
        webUtil.waitUntilElementVisible(By.xpath(String.format(pbParagraph2, 1)), 20);
        if (webUtil.getText(By.xpath(String.format(pbParagraph2, 1))).contentEquals("You have selected to allocate all available funds to Prize Bonds.")) {
            throw new ExceptionUtils("Prize bond paragraph 1 content not verified");
        }
        if (!webUtil.getText(By.xpath(String.format(pbParagraph2, 1))).contentEquals("Each prize bond has a value of €6.25 so the amount will be rounded down to the nearest multiple of €6.25. Any remaining " +
                "funds will be transferred to your bank account ending ~".concat(getdata.get("IBAN_Number").substring(getdata.get("IBAN_Number").length() - 4)))) {
            throw new ExceptionUtils("Prize bond paragraph 2 content not verified");
        }
    }

    /**
     * function used for clicking on allocating full amount to cashin
     *
     * @param var1
     * @throws InterruptedException
     */
    public void clickFullAmountandConfirm(String var1) throws Exception {
        String str = "//button[@class='button button--secondary button--alt js-allocate-full-amount' and @data-current-id='%s']";
        avilableAmount = Double.parseDouble(webUtil.getText(By.id("spanAvailableFundCI")).replaceAll("[^0-9.]", ""));
        List<String> pro = new ArrayList<String>(Arrays.asList(getdata.get("Products").split(",")));
        boolean result = pro.stream().anyMatch(i -> i.contains("Prize Bonds"));
        if (getdata.get("MethodType").contentEquals("Reinvest")) {
            if (result) {
                if (pro.get(Integer.parseInt(getdata.get("Allocate Full Amount Index"))).contentEquals("Prize Bonds")) {
                    webUtil.click(By.xpath(String.format(str, getdata.get("Allocate Full Amount Index"))));
                    pbVerify();
                }
            } else {
                webUtil.click(By.xpath(String.format(str, "1")));
                if (result)
                    pbVerify();
                else {

                }
            }
        } else {
            if (webUtil.isElementDisplayed(commonallocateFullAmount, 20)) {
                if (webUtil.getText(commonallocateFullAmount).contentEquals("Allocate full amount")) {
                    webUtil.scrollToView(commonallocateFullAmount);
                    webUtil.click(commonallocateFullAmount);
                    log.info("Allocate full amount button clicked!");
                } else
                    throw new ExceptionUtils("Allocate full amount content not verified!");
            } else {
                throw new ExceptionUtils("Allocate full amount x-path not found!");
            }
        }
    }

    public void Error() throws Exception {
        if (webUtil.isElementVisible(noAmountError, 10)) {
            if (!webUtil.getText(noAmountError).equalsIgnoreCase("Enter an amount or select 'Allocate full amount'.")) {
                log.info("No amount error message displayed not verified");
                ExtentCucumberAdapter.addTestStepLog("No amount error message displayed not verified");
            } else {
                log.info("No amount error message displayed verified");
                ExtentCucumberAdapter.addTestStepLog("No amount error message displayed verified");
            }
        } else if (webUtil.isAllElementVisible(moreAmountError, 10)) {
            if (webUtil.getText(moreAmountError).equalsIgnoreCase("Invalid amount entered.")) {
                log.info("More amount message error displayed verified");
                ExtentCucumberAdapter.addTestStepLog("More amount message error displayed verified");
            } else {
                log.info("More amount message error not verified");
                ExtentCucumberAdapter.addTestStepLog("More amount message error displayed not verified");
            }
        } else {
            throw new ExceptionUtils("Error message x-path not found!");
        }
    }

    /**
     * function used for clicking confirm button on cash in
     *
     * @throws InterruptedException
     */
    public void clickCnfrmButton() throws InterruptedException {
        try {
            webUtil.waitForPageLoaded();
            if (getdata.get("Product").equals("Prize Bond"))
                reuse(cnfrmButton);
            else
                reuse(cashinConfirmbtn);
        } catch (Exception e) {
            throw new ExceptionUtils("Confirm button not clicked");
        }
    }

    /**
     * function user for clicking cancel button on cash in
     *
     * @throws Exception
     */
    public void clickCancelButton() throws Exception {
        try {
            reuse(cancelButton);
        } catch (Exception e) {
            throw new ExceptionUtils("Cancel button no clicked");
        }
    }

    /**
     * function used for clicking cancel button on cashin
     *
     * @throws InterruptedException
     */
    public void clickBackButton() throws InterruptedException {
        reuse(goBackBtn);
    }

    /**
     * function used for clicking on the allocate full amount modal confirm button
     *
     * @return
     * @throws InterruptedException
     */
    public Repayment cnfrmButtonModal() throws Exception {
        if (webUtil.isElementclickable(cnfrmBtnModal, 20)) {
            webUtil.click(cnfrmBtnModal);
            ExtentCucumberAdapter.addTestStepLog("Allocate full amount Yes I'm sure button is clicked");
            webUtil.waitForPageLoaded();
        }
        return this;
    }

    /**
     * function used for clicking on the allocate full amount modal cancel button
     *
     * @throws InterruptedException
     */
    public void cancelButtonModal() throws InterruptedException {
        reuse(cancelBtnModal);
    }

    /**
     * function used for getting the mature amount from cashin page
     *
     * @throws Exception
     */
    public void matureAmount() throws Exception {
        if (webUtil.isElementDisplayed(matureAmountXpath, 20)) {
            String value = webUtil.getText(matureAmountXpath);
            matureAmount = Integer.parseInt(value.substring(value.length() - 1));
        }
    }


    public void clickCancelBtn() throws InterruptedException {
        reuse(cancelBtn);
    }

    public void clickCnfrmBtn() throws Exception {
        //reuse(checkboxInterestDelay);
        reuse(checkboxTermCondition);
        reuse(confirmTransactionReview);
    }

    /**
     * function used to verify the content on security page
     *
     * @throws Exception
     */
    public void verifySecurityPageInfo() throws Exception {
        //sp.vrfySecurityPage();
        sp.enterOtp();
        sp.clkCnfrmBtn();
    }

    /**
     * reuseable function to click on the allocate full amount
     *
     * @param locator
     * @throws InterruptedException
     */
    public void reuse(By locator) throws InterruptedException {
        if (webUtil.isElementclickable(locator, 20)) {
            webUtil.scrollToView(locator);
            try {
                webUtil.click(locator);
            } catch (Exception e) {
                throw new ExceptionUtils("Enter Amount page confirm button click is not clicked");
            }
        } else {
            throw new ExceptionUtils("Confirm button x-path not found!");
        }
    }

    /**
     * function used for clicking the edit your order
     *
     * @throws InterruptedException
     */
    public void editYourOrder() throws InterruptedException {
        reuse(editYourAmount);
    }
//    public void remainingAmount(String amount) throws InterruptedException {
//       int remainingAmount=holdings.maturity_value - Integer.parseInt(amount);
//       enterAmount(Integer.toString(remainingAmount));
//    }


    public void pbReinvest() {
        ibanHeader = By.xpath("//section[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_addIbanModal']//h4");
        ibanContent = "//section[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_addIbanModal']//p[%d]";
        ibanLabel = By.xpath("//input[@id='txtIbanAccount']/parent::div/label");
        ibanCheckbox = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_checkTermsAndCondition']/parent::span/label");
        ibanConfirmBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_btnAddIban']");
        ibanCancelBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_btnAddIban']/preceding-sibling::button");
    }

    public void pbRepay() {

    }

    public void verifyIbanPagePBReinvest() throws Exception {
        pbReinvest();
//        webUtil.waitForPageLoaded();
        if (!webUtil.getText(ibanHeader).trim().equalsIgnoreCase("Add your bank details")) {
            throw new ExceptionUtils("Iban page header content is not same");
        }
        if (!webUtil.getText(By.xpath(String.format(ibanContent, 1))).trim().equalsIgnoreCase(str1)) {
            System.out.println(webUtil.getText(By.xpath(String.format(ibanContent, 1))).trim());
            throw new ExceptionUtils("Iban Page 1st paragraph content is not same");
        }
        if (!webUtil.getText(By.xpath(String.format(ibanContent, 2))).equalsIgnoreCase(str2)) {
            System.out.println(webUtil.getText(By.xpath(String.format(ibanContent, 2))));
            throw new ExceptionUtils("Iban Page 2nd paragraph content is not same");
        }
        if (!webUtil.getText(By.xpath(String.format(ibanContent, 3))).trim().equalsIgnoreCase(str3)) {
            System.out.println(webUtil.getText(By.xpath(String.format(ibanContent, 3))).trim());
            throw new ExceptionUtils("Iban Page 3rd paragraph content is not same");
        }
        if (!webUtil.getText(By.xpath(String.format(ibanContent, 4))).trim().equalsIgnoreCase(str4)) {
            System.out.println(webUtil.getText(By.xpath(String.format(ibanContent, 4))).trim());
            throw new ExceptionUtils("Iban Page 4th paragraph content is not same");
        }
        if (!webUtil.getText(By.xpath(String.format(ibanContent, 5))).trim().equalsIgnoreCase(str5)) {
            System.out.println(webUtil.getText(By.xpath(String.format(ibanContent, 5))).trim());
            throw new ExceptionUtils("Iban Page 4th paragraph content is not same");
        }
        if (!webUtil.getText(ibanLabel).trim().contains("IBAN")) {
            System.out.println(webUtil.getText(ibanLabel).trim());
            throw new ExceptionUtils("Iban text field label content is not same");
        }
        //Tooltip need to ask
        if (webUtil.isElementDisplayed(ibanToolTip, 20)) {
            String tooltip = "Your IBAN starts with two letters to denote the SEPA country where the account is based, e.g. IE for Ireland. Your IBAN is printed on your bank statement. You may also request it directly from your bank.";
//               webUtil.click(ibanToolTip);
//               if (!webUtil.getText(ibanToolTipContent).trim().contentEquals(tooltip)) {
//                   throw new ExceptionUtils("Iban page tooltip content is changed");
//               }
        }
        log.info("Content on the iban page is same and tooltip content is also same");
    }

    public void verifyIbanPage() throws Exception {
        webUtil.waitForPageLoaded();
        if (!webUtil.getText(ibanHeader).equalsIgnoreCase("Add your bank details to State Savings Online")) {
            throw new ExceptionUtils("Iban page header content is not same");
        }
        if (!webUtil.getText(By.xpath(String.format(ibanContent, 1))).trim().equalsIgnoreCase(str1)) {
            throw new ExceptionUtils("Iban Page 1st paragraph content is not same");
        }
        if (!webUtil.getText(By.xpath(String.format(ibanContent, 2))).equalsIgnoreCase(str2)) {
            throw new ExceptionUtils("Iban Page 2nd paragraph content is not same");
        }
        if (!webUtil.getText(By.xpath(String.format(ibanContent, 3))).trim().equalsIgnoreCase(str3)) {
            throw new ExceptionUtils("Iban Page 3rd paragraph content is not same");
        }
        if (!webUtil.getText(By.xpath(String.format(ibanContent, 4))).trim().equalsIgnoreCase(str4)) {
            throw new ExceptionUtils("Iban Page 4th paragraph content is not same");
        }
        if (!webUtil.getText(ibanLabel).trim().contains("IBAN")) {
            throw new ExceptionUtils("Iban text field label content is not same");
        }
        //Tooltip need to ask
        if (webUtil.isElementDisplayed(ibanToolTip, 20)) {
            String tooltip = "Your IBAN starts with two letters to denote the SEPA country where the account is based, e.g. IE for Ireland. Your IBAN is printed on your bank statement. You may also request it directly from your bank.";
            webUtil.click(ibanToolTip);
            if (!webUtil.getText(ibanToolTipContent).trim().contentEquals(tooltip)) {
                throw new ExceptionUtils("Iban page tooltip content is changed");
            }
        }
        log.info("Content on the iban page is same and tooltip content is also same");
        //Iban enter
    }

    public void enterIbanReuse(By enterIban) throws Exception {
        webUtil.scrollToView(enterIban);
        driver.findElement(enterIban).sendKeys(getdata.get("IBAN_Number"), Keys.TAB);
    }

    public void enterIban() throws Exception {
        webUtil.sendKeys(By.id("ibanText"), getdata.get("IBAN_Number"));
        driver.findElement(By.id("ibanText")).sendKeys(Keys.TAB);

    }

    public void clickCheckbox() throws Exception {
        //CheckBox click and confirm
        if (getdata.get("MethodType").contentEquals("null")) {
            verifyIbanPageNonMatured();
        } else if (getdata.get("MethodType").contentEquals("Cash-In")) {
            verifyIbanPageMatured();
        }
        System.out.println(webUtil.getText(ibanCheckboxContent));
        if (webUtil.getText(ibanCheckboxContent).trim().contentEquals("I confirm this is a bank account in my name")) {
            webUtil.scrollToView(ibanCheckbox);
            webUtil.click(ibanCheckboxContent);
        } else {
            throw new ExceptionUtils("Iban page checkbox content is not same");
        }
    }

    public void clickCheckboxIBANNotadded() throws Exception {
        //CheckBox click and confirm
        ibanCheckboxContent = By.xpath("//label[@for='checkTermsAndCondition']");
        System.out.println(webUtil.getText(ibanCheckboxContent));
//        webUtil.click(ibanCheckboxContent);
        if (webUtil.getText(ibanCheckboxContent).trim().contentEquals("I confirm this is a bank account in my name")) {
            webUtil.scrollToView(ibanCheckboxContent);
            webUtil.click(ibanCheckboxContent);
        } else {
            throw new ExceptionUtils("Iban page checkbox content is not same");
        }
    }

    public void ibanClickConfirm() throws Exception {

        webUtil.click(ibanConfirmBtn);
//            if (!checkboxError()) {
//                throw new ExceptionUtils("Checkbox is not clicked");
//            }
//            errorCheck();

    }

    public void ibanClickCancel() throws Exception {
        if (webUtil.isElementDisplayed(ibanCancelBtn, 20) && webUtil.getText(ibanCancelBtn).equalsIgnoreCase("Cancel")) {
            try {
                webUtil.scrollToView(ibanCancelBtn);
                webUtil.click(ibanCancelBtn);
            } catch (Exception e) {
                throw new ExceptionUtils("Click button click is interrupted");
            }
        } else {
            throw new ExceptionUtils("Cancel button content is not same");
        }
    }

    public void errorCheck() throws Exception {
        //invalid iban provided
        if (webUtil.getText(ibanInvalidError).contentEquals("Please enter a value for your IBAN.")) {
            throw new ExceptionUtils("In-valid iban content is verified");
        } else if (webUtil.getText(ibanerror).contentEquals("This is not a valid IBAN. A valid IBAN consists of a two-letter country code, followed by two check digits, and up to thirty-five alphanumeric characters.")) {
            throw new ExceptionUtils("Iban is non-sepa iban content is verified");
        } else {
            log.info("IBAN is valid");
        }
    }

    public boolean checkboxError() throws Exception {
        return !webUtil.getText(checkboxError).equalsIgnoreCase("Please confirm");
    }


    public void verifyIbanPageAddIban() throws Exception {
//        if (getdata.get("MethodType").trim().contentEquals("null")) {
//            verifyIbanPageNonMatured();
//        } else {
//            verifyIbanPageMatured();
//        }
        if (webUtil.isElementDisplayed(By.xpath("//section[contains(@class,'modal active')]"), 10)) {
            String tooltip = "Your IBAN starts with two letters to denote the SEPA country where the account is based, e.g. IE for Ireland. Your IBAN is printed on your bank statement. You may also request it directly from your bank.";

            WebElement ibanSlider = driver.findElement(By.xpath("//section[contains(@class,'modal active')]"));
            WebElement header = ibanSlider.findElement(By.xpath(".//h4"));
            WebElement para1 = ibanSlider.findElement(By.xpath(".//p[1]"));
            WebElement para2 = ibanSlider.findElement(By.xpath(".//p[2]"));
            WebElement para3 = ibanSlider.findElement(By.xpath(".//p[3]"));
            WebElement para4 = ibanSlider.findElement(By.xpath(".//p[4]"));
            WebElement para5 = ibanSlider.findElement(By.xpath(".//p[5]"));
            WebElement ibanLabel = ibanSlider.findElement(By.xpath("//label[@for='profile-settings-iban']"));
            WebElement ibanCheckBoxContent = ibanSlider.findElement(By.xpath("//label[@for='checkTermsAndCondition']"));
            webUtil.CompareString(header.getText().trim(), String::equals, "Add your bank details");
            webUtil.CompareString(para1.getText().trim(), String::equals, str1);
            webUtil.CompareString(para2.getText().trim(), String::equals, str2);
            webUtil.CompareString(para3.getText().trim(), String::equals, str3);
            webUtil.CompareString(para4.getText().trim(), String::equals, str4);
            webUtil.CompareString(para5.getText().trim(), String::equals, str5);
            webUtil.CompareString(ibanLabel.getText().trim(), String::contains, "IBAN");
            webUtil.CompareString(ibanCheckBoxContent.getText().trim(), String::contains, "I confirm this is a bank account in my name");
            webUtil.click(ibanToolTip);
            webUtil.gettextlog(By.id("IBAN-tooltip"), String::equals, tooltip);
            webUtil.click(ibanToolTip);
        } else {
            throw new ExceptionUtils("IBAN Slider element not found");
        }


    }

    public void verifyIbanPageMatured() {
        ibanHeader = By.xpath("//section[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_addIbanModal']//h4");
        ibanContent = "//section[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_addIbanModal']//p[%d]";
        ibanLabel = By.xpath("//input[@id='txtIbanAccount']/parent::div/label");
        ibanCheckbox = By.xpath("//input[@id='checkTermsAndCondition']");
        ibanCheckboxContent = By.xpath("//input[@id=\"checkTermsAndCondition\"]/following::label");
        ibanCancelBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_btnAddIban']/preceding-sibling::button");
        ibanConfirmBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_btnAddIban']");
        enterIban = By.xpath("//input[@id='txtIbanAccount']");
        str2 = "Important Note: If you have previously provided IBAN details to State Savings,".concat("\n").concat(" the IBAN you provided here will replace existing details on our records.");
    }

    public void verifyIbanPageNonMatured() {
        ibanHeader = By.xpath("//section[contains(@class,'modal active')]/h4");
        ibanContent = "//section[contains(@class,'modal active')]/p[%d]";
        ibanLabel = By.xpath("//label[@for='profile-settings-iban']");
        ibanCheckbox = By.xpath("//label[@for='checkTermsAndCondition']");
        ibanCheckboxContent = By.xpath("//label[@for='checkTermsAndCondition']");
        ibanCancelBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_btnAddIban']/preceding-sibling::button");
        ibanConfirmBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_btnAddIban']");
    }

    public void clickIbanAddButton() throws Exception {
        if (getdata.get("MethodType").contentEquals("null")) {
            addIbanBtn = By.xpath("//button[@id='btnCashInAddBankDetail']");
        } else if (getdata.get("Product").contentEquals("Prize Bond")) {
            addIbanBtn = By.xpath("//div[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_NoIBANCashInBlock']//following-sibling::div[1]//a[contains(text(),'Add your bank details')]");
        }
        if (webUtil.isElementDisplayed(addIbanBtn, 20)) {
            if (getdata.get("MethodType").contentEquals("null")) {
                if (!webUtil.getText(addIbanBtn).equals("Add bank details")) {
                    throw new ExceptionUtils("Add iban link button content is changed");
                }
            } else if (!webUtil.getText(addIbanBtn).equals("Add your bank details")) {
                throw new ExceptionUtils("Add iban link button content is changed");
            }
            try {
                webUtil.scrollToView(addIbanBtn);
                webUtil.clickLocator(addIbanBtn);
            } catch (Exception e) {
                throw new ExceptionUtils("Add iban link button click is interrupted");
            }
        } else {
            throw new ExceptionUtils("Add iban button x-path may have been changed");
        }
    }

    public void nonMaturedSecurityPage() {
        Securityheader = By.xpath("//section[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_oneTimeSecurityCodePinModal']/h4");
        para = By.xpath("//section[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_oneTimeSecurityCodePinModal']/p");
        enterOTPField = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_txtOneTimeSecurityCodePin']");
        didnotreciveLink = By.xpath("//a[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_lnkResetPinCode']");
        confirmBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_btnVerifySecurityCodePin']");
        cancelBtn1 = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_btnVerifySecurityCodePin']/preceding-sibling::button");
    }

    public void maturedSecurityPage() {
        Securityheader = By.xpath("//section[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_oneTimeSecurityCodePinModal']/h4");
        para = By.xpath("//section[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_oneTimeSecurityCodePinModal']/p");
        label = By.xpath("//label[@for='txtOneTimeSecurityCodePin']");
        enterOTPField = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_txtOneTimeSecurityCodePin']");
        didnotreciveLink = By.xpath("//a[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_lnkResetPinCode']");
        confirmBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_btnVerifySecurityCodePin']");
        cancelBtn1 = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_btnVerifySecurityCodePin']/preceding-sibling::button");
    }

    public void verifySecurityPage() throws Exception {
        webUtil.waitForPageLoaded();
        if (getdata.get("MethodType").contentEquals("null")) {
            nonMaturedSecurityPage();
        } else if (getdata.get("Product").contentEquals("Prize Bond") && getdata.get("MethodType").contentEquals("Reinvest"))
            nonMaturedSecurityPage();
        if (!webUtil.getText(Securityheader).trim().contentEquals("Verification Code")) {
            System.out.println(webUtil.getText(Securityheader));
            throw new ExceptionUtils("Security code page header content not verified");
        }
        if (!webUtil.getText(para).trim().contentEquals(paraContent + getdata.get("Number").substring(getdata.get("Number").length() - 4))) {
            throw new ExceptionUtils("Security code page number paragraph not verified");
        }
        if (!webUtil.getText(label).trim().contentEquals("Enter verification code")) {
            throw new ExceptionUtils("Security code page label content not verified");
        }
        if (!webUtil.getText(didnotreciveLink).trim().contentEquals("Didn't receive the code?")) {
            throw new ExceptionUtils("Security code page didn't link content not verified");
        }
        if (!webUtil.getText(cancelBtn1).trim().contentEquals("Cancel")) {
            throw new ExceptionUtils("Security code page cancel button content not verified");
        }
        if (!driver.findElement(confirmBtn).getAttribute("value").contentEquals("Confirm")) {
            throw new ExceptionUtils("Security code page confirm button content not verified");
        }
    }

    public void enterSecurityCode() throws Exception {
        String otp = api.getOTP(portal.url, 200, getdata.get("Number"));
        try {
            webUtil.sendKeys(enterOTPField, otp);
            ExtentCucumberAdapter.addTestStepLog("OTP is entered");
        } catch (Exception e) {
            throw new ExceptionUtils("Unable to enter OTP ".concat(e.getMessage()));
        }
    }


    public void clickConfirmBtnOtpPage() throws Exception {
        if (webUtil.isElementclickable(confirmBtn, 20)) {
            webUtil.click(confirmBtn);
        } else {
            throw new ExceptionUtils("Confirm button x-path not found!");
        }
    }

    public void clickCancelBtnOtpPage() throws Exception {
        if (webUtil.isElementclickable(cancelBtn, 20)) {
            webUtil.click(cancelBtn);
        } else {
            throw new ExceptionUtils("Cancel button x-path not found!");
        }
    }

    public void verifyThankYouMessage() throws Exception {
        if (!webUtil.getText(thankYouMessage).contentEquals("Thank you, your Bank Account details have been added to your profile.")) {
            throw new ExceptionUtils("Thank you message not verified!");
        }
    }

}
