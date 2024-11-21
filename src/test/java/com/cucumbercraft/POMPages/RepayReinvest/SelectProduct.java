package com.cucumbercraft.POMPages.RepayReinvest;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.Modals;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.Util;
import com.cucumbercraft.framework.WebDriverUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.cucumbercraft.stepdefinitions.ReinvestSteps.getdata;

public class SelectProduct {

    public static double maturityvalue;
    static Logger log = LogManager.getLogger(SelectProduct.class);
    private static String[] list;
    private static String[] amount1;
    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private final String chsprdct = "(//select[@name='product-select'])[%d]";
    private final String productTiles = "//div[@class='product-review-list--row row']";
    private final String productTileAmount = "//div[@class='product-review-list--row row']/div[2]";
    private final String allocatefullamount = "(//div[@style='display:block']//button[text()='Allocate full amount'])[%d]";
    private final String errXpath = "//span[contains(@id,'p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_rpt_ctl0%d_CV%s')]";
    private final String enterAmount = "(//input[@name='amount-input'])[%d]";
    private final String savingCert = "_txtSavingCert";
    private final String NSB10 = "_txtNSB10Amt";
    private final String NSB4 = "_txtNSB4Amt";
    private final String Book_Based_Deposit_Account = "_txtDepositAmount";
    private final String Prize_Bond = "_txtPBAmount";
    private final String Saving_Bond = "_txtSavingBond";
    private final String lessAmount = "The minimum purchase value for this State Savings Product is €50.";
    private final String greaterAmount = "Invalid amount entered.";
    private final String maxAmount = "The maximum holding limit for this State Savings Product is €120,000 per individual per issue.";
    private final By maxAmountErrorPB = By.xpath("//span[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_rpt_ctl00_CVtxtPBAmount']");
    private final String maxAmountErrorPBMessage = "The maximum holding limit for Prize Bonds is €250,000 per individual.";
    private final By maxAmountError = By.xpath("//span[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_rpt_ctl00_CVtxtSavingBond']");
    private final String pbError = "Please enter amount for investment";
    private final By minAmountPB = By.xpath("//span[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_rpt_ctl00_CVtxtPBAmount']");
    private final String minAmountPBMessage = "The minimum purchase value for Prize Bonds is €25.";
    private final String pbgreaterAmount = "Invalid amount entered.";
    private final String modalContent = "By allocating the full amount, any other selections you have made will be removed. Are you sure you want to proceed?";
    private final By totalamt = By.id("spanAvailableFundCI");
    private final By addAnotherLink = By.id("lnkAddMoreProduct");
    private final By matureAmount = By.xpath("//span[@class='footer__ec-total-amount js-available-funds']");
    private final By confirmbttn = By.id("submitButton");
    private final By cancelbtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_btnReInvestSubmit']//preceding-sibling::button");
    private final By goBack = By.xpath("//a[contains(@class,'gtm-cta button button--secondary  js-closeModal') and contains(text(),'Go back')]");
    private final By reviewPage = By.xpath("//div[@class='small-12 columns']");
    private final By editYourOrder = By.xpath("//a[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_lbEditOrder']");
    private final By reinvestText = By.xpath("//h2[@class='product-options-info__heading' and contains(text(),'Reinvest')]");
    private final By reviewCheckBox = By.id("p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_lblTermCondition");
    private final By reviewConfirmButton = By.xpath("//*[text()='Confirm Transaction']");
    private final By errorMessageReviewPage = By.xpath("//span[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_CVCheck']");
    private final By reviewCancelButton = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_btnConfirmReview']//preceding-sibling::button");
    private final By checkboxdeposit = By.xpath("//span[@class='checkbox js-toggle-existing-account-field']/label");
    private final By acno = By.cssSelector("input[type='text'][maxlength='10']");
    Modals modal;
    private Holdings holdings;
    private int index;

    /**
     * @param driver driver for the access
     */
    public SelectProduct(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
        modal = new Modals(driver);
        holdings = new Holdings(driver);
    }


    /**
     * Creating X-Path for selecting product from the dropdown
     *
     * @param i       the index for X - Path
     * @param product Product which needs to be selected
     */
    public void selectProductXpath(int i, String product) throws Exception {
        i += 1;
        if (product.contentEquals("Deposit Account (POSB - book based)")) {
            try {
                webUtil.selectDropDown(By.xpath(String.format(chsprdct, i)), select -> select.selectByVisibleText(product));
            } catch (Exception e) {
                throw new ExceptionUtils("Product not found!");
            }
            if (!getdata.get("POSB Account Number").contentEquals("null")) {
                webUtil.click(checkboxdeposit);
                webUtil.waitForPageLoaded();
                webUtil.waitUntilElementLocated(acno, 10);
                webUtil.sendKeys(acno, getdata.get("POSB Account Number"));
            }
        } else {
            try {
                webUtil.selectDropDown(By.xpath(String.format(chsprdct, i)), select -> select.selectByVisibleText(product));
            } catch (Exception e) {
                throw new ExceptionUtils("Product not found!");
            }

        }
    }

    /**
     *
     * @param product
     * @return
     * @throws InterruptedException
     */

    /**
     * Choose multiple product helper function for userClicksOnDropdownMenuAndSelect()
     *
     * @param list    All products in array
     * @param amount1 all amount in array
     */

    private void chooseMultipleProduct(String[] list, String[] amount1) throws Exception {
        maturityvalue = Double.parseDouble(webUtil.getText(totalamt).replaceAll("[^0-9.]", ""));
        for (int i = 0; i < list.length; i++) {
            try {
                if (webUtil.isElementDisplayed(By.xpath(String.format(chsprdct, i + 1)), 05)) {
                    selectProductXpath(i, list[i]);
                    userEnterAnAmount(amount1[i], list[i], i);
                    if ((i + 1) < list.length) {
                        webUtil.click(addAnotherLink);
                    }
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    /**
     * Choose single product helper function for userClicksOnDropdownMenuAndSelect()
     *
     * @param productList all the products will be passed as string
     * @param amount      all the amount wil be passed as string
     */

    private void chooseSingleProduct(String productList, String amount) throws Exception {
        maturityvalue = Double.parseDouble(webUtil.getText(totalamt).replaceAll("[^0-9.]", ""));
        try {

            selectProductXpath(0, productList);
            userEnterAnAmount(amount, productList, 0);

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * This function will help in selecting the products from the dropdown
     * and Enter the amount for the product to reinvest
     *
     * @param productList all the products will be passed as string
     * @param amount      all the amount wil be passed as string
     * @throws Throwable
     */
    public void userClicksOnDropdownMenuAndSelect(String productList, String amount) throws Exception {

        if (productList.contains(",")) {
            list = productList.split(",");
            amount1 = amount.split(",");
            chooseMultipleProduct(list, amount1);
        } else {
            chooseSingleProduct(productList, amount);
        }
        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
    }

    /**
     * Helper function for entering the amount
     *
     * @param i       This will help to enter amount on the particular index and will work as an index in X-Path
     * @param amount  The amount we have to enter in the field
     * @param product The poduct will help to create the X-Path
     * @param list    List is user for verifying the error we get after entering the amount
     * @throws Throwable
     */

    public void reuse(int i, String amount, String product, String list) throws Throwable {

        if (product.equals("_txtPBAmount")) {
            webUtil.sendKeys(By.xpath("//div[@style='display: block;']//input[@name='pb-amount-input']"), amount);
            driver.findElement(By.xpath("//div[@style='display: block;']//input[@name='pb-amount-input']")).sendKeys(Keys.TAB);
        } else {
            webUtil.sendKeys(By.xpath(String.format(enterAmount, i)), amount);
            driver.findElement(By.xpath(String.format(enterAmount, i))).sendKeys(Keys.ENTER);
        }

        if (error(amount, list, i)) {
            log.info("Error message is displayed for: ".concat(product));
        }

    }

    /**
     * Main function for entering the amount
     *
     * @param i      This will help to enter amount on the particular index and will work as an index in X-Path
     * @param amount The amount we have to enter in the field
     * @param list   List is user for verifying the error we get after entering the amount
     * @throws Throwable
     */
    public void userEnterAnAmount(String amount, String list, int i) throws Throwable {
        i += 1;
        switch (list) {
            case "Savings Certificate - 5 Year":
                reuse(i, amount, savingCert, list);
                ExtentCucumberAdapter.addTestStepLog("Saving Certificate selected and amount entered: " + amount);
                break;
            case "Prize Bonds":
                reuse(i, amount, Prize_Bond, list);
                ExtentCucumberAdapter.addTestStepLog("Prize Bond selected and amount entered: " + amount);
                break;
            case "National Solidarity Bond - 4 Year":
                reuse(i, amount, NSB4, list);
                ExtentCucumberAdapter.addTestStepLog("National Solidarity Bond - 4 Year selected and amount entered: " + amount);
                break;
            case "National Solidarity Bond - 10 Year":
                reuse(i, amount, NSB10, list);
                ExtentCucumberAdapter.addTestStepLog("National Solidarity Bond - 10 Year selected and amount entered: " + amount);
                break;
            case "Savings Bond - 3 Year":
                reuse(i, amount, Saving_Bond, list);
                ExtentCucumberAdapter.addTestStepLog("Saving Bond selected and amount entered: " + amount);
                break;
            case "Deposit Account":
                reuse(i, amount, Book_Based_Deposit_Account, list);
                ExtentCucumberAdapter.addTestStepLog("POSB selected and amount entered: " + amount);
                break;
            default:
                log.info("Product not selected");
                throw new ExceptionUtils("Product not selected");
        }
    }

    /**
     * Reusable function for creating the X-Path
     *
     * @param list = The product for which we have to create X-Path for
     * @param i    = the index will help us to create the X-Path
     * @return
     */

    private String errorReuse(String list, int i) {
        String result = "";
        switch (list) {
            case "Savings Certificate - 5 Year":
                result = String.format(errXpath, i, "txtSavingCert");
                break;
            case "National Solidarity Bond - 4 Year":
                result = String.format(errXpath, i, "TxtNSB4Amount");
                break;
            case "National Solidarity Bond - 10 Year":
                result = String.format(errXpath, i, "txtNSB10Amt");
                break;
            case "Savings Bond - 3 Year":
                result = String.format(errXpath, i, "txtSavingBond");
                break;
            case "Prize Bonds":
                result = String.format(errXpath, i, "txtPBAmount");
                break;
            case "Deposit Account (POSB - book based)":
                result = String.format(errXpath, i, "txtDepositAmount");
            default:
                System.out.println("Product not selected");
        }
        return result;
    }


    /**
     * For verifying the error message for Prize Bond
     *
     * @param amount the amount for prize bond
     * @param result the result will return
     * @return
     * @throws Exception
     */
    private boolean prizeBondError(double amount, String result) throws Exception {
        if (amount < 25) {
            webUtil.waitForPageLoaded();
            if (amount == 0) {
                if (webUtil.getText(By.xpath(result)).contains(pbError)) {
                    ExtentCucumberAdapter.addTestStepLog("Error Verified: ".concat(pbError));
                    return true;
                }
            } else if (amount > 0) {
                if (webUtil.getText(minAmountPB).trim().contentEquals(minAmountPBMessage)) {
                    ExtentCucumberAdapter.addTestStepLog("Error Verified: ".concat(pbError));
                    return true;
                }
            } else return false;
        } else if (amount > 250000) {
            webUtil.waitForPageLoaded();
            if (webUtil.getText(maxAmountErrorPB).trim().contains(maxAmountErrorPBMessage)) {
                ExtentCucumberAdapter.addTestStepLog("Error Verified: ".concat(pbError));
                return true;
            } else return false;
        }
        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
        return false;
    }

    /**
     * State Saving Product Error will be handeled here
     *
     * @param amount        the amount which was provided
     * @param result        the result will be help in X - Path
     * @param mature_amount the mature amount of the product
     * @return
     * @throws Exception
     */


    private boolean stateSavingError(double amount, String result, double mature_amount) throws Exception {
        if (amount < 50) {

            if (webUtil.getText(By.name("ft-min-amount-error")).contentEquals(lessAmount)) return true;
        } else if (amount > mature_amount && webUtil.getText(By.name("amount-error")).contains(greaterAmount))
            return true;

        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
        return false;
    }

    /**
     * Error method will help us to verify the error messages which
     * we get after entering amount for reinvest
     *
     * @param amount1 The amount will help us to verify the
     * @param product verifying the error for product
     * @return
     * @throws Exception
     */

    public boolean error(String amount1, String product, int i) throws Exception {
        double amount = Double.parseDouble(amount1);
        double mature_amount = holdings.getMaturityValue();
        System.out.println(mature_amount);
        String result = "";
        result = errorReuse(product, i);
        System.out.println(result);
        if (webUtil.isElementDisplayed(By.xpath(result), 05))
            if (product.contains("Prize Bond")) return prizeBondError(amount, result);
            else return stateSavingError(amount, result, mature_amount);

        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
        return false;
    }

    /**
     * allocate full amount helper function
     *
     * @param index the index for x-Path
     */

    private void allocateFulAmountBtn(int index) throws Exception {

        webUtil.click(By.xpath(String.format(allocatefullamount, index)));


    }

    public void confrmButtonHelper() throws InterruptedException {
        if (webUtil.isElementDisplayed(confirmbttn, 20)) {
            webUtil.click(confirmbttn);
        } else {
            log.info("Failed i confrm button");
        }
    }

    /**
     * Reinvest Confirm button
     *
     * @throws Exception
     */
    public void cofrmButton() throws Exception {
        try {
            webUtil.click(confirmbttn);
            Double total_value = 0d;
            double[] amount;
            String[] amo;
            String[] pro = getdata.get("Amount").split(",");
            if (!getdata.get("Amount").contains(","))
                total_value = Double.parseDouble(getdata.get("Amount"));
            else {
                amo = getdata.get("Amount").split(",");
                amount = Stream.of(amo).mapToDouble(Double::parseDouble).toArray();
//                amount = (Double[]) Stream.of(amo).map(Double::parseDouble).toArray();
                total_value = Double.valueOf(Arrays.stream(amount).sum());
                if (BigDecimal.valueOf(total_value).scale() > 2)
                    total_value = Double.parseDouble(String.format("%.2f", total_value));
            }
            if (holdings.getMaturityValue() > total_value && !getdata.get("Product").contentEquals("Prize Bond")) {
                modal.goBackModalFt();
                ExtentCucumberAdapter.addTestStepLog("Less Amount Entered modal content is verified");
            }
            System.out.println(holdings.getMaturityValue());
            if (getdata.get("IBAN_Added_Status").contentEquals("No") && !getdata.get("Prize Amount").contentEquals("null")) {
                if (total_value < Integer.parseInt(getdata.get("Prize Amount")))
                    modal.confirmButtonReinvestIbanNoteAddedClickAddIbanButton();
            } else if (total_value > holdings.getMaturityValue() && !getdata.get("Product").contentEquals("Prize Bond")) {
                modal.clickOnCloseButtonModal();
                ExtentCucumberAdapter.addTestStepLog("More Amount Entered modal content is verified");
            }

            log.info("Confirm button on choose product page is clicked.");
            ExtentCucumberAdapter.addTestStepLog("Confirm button on choose product page is clicked.");
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
        } catch (ExceptionUtils e) {
            throw new ExceptionUtils("Confirm button on choose product page is not clicked ".concat(e.getMessage()));
        }
    }

    public void cancelBtn() throws Exception {
        if (webUtil.isElementclickable(cancelbtn, 20)) {
            webUtil.click(cancelbtn);
        } else {
            throw new ExceptionUtils("Cancel button x-path changed");
        }
    }


    /**
     * allocate full amount function
     * By using this function we can allocate full amount to the particular product
     *
     * @param index used as an index to allocate full amount
     */
    public void allocate_fullamountandclickmodelconfrmbutton(int index) throws Exception {
        this.index = index;
        allocateFulAmountBtn(index);
    }

    public void reviewPage1() throws Exception {
        if (webUtil.isElementDisplayed(By.xpath(productTiles), 20)) {
            if (list[index - 1].contains(webUtil.getText(By.xpath(productTiles + "[" + (1) + "]/div[1]")))) {
                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            } else {
                log.info("Product is not available in review page");
            }
        }
        if (!webUtil.isElementDisplayed(editYourOrder, 20)) {
            Assert.fail();
        } else {
            log.info("Edit link is displayed");

        }
    }

    /**
     * Review page helper function
     *
     * @param list
     * @throws Exception
     */

    private void reviewPageHelper(String[] list) throws Exception {
        if (webUtil.isElementDisplayed(By.xpath(productTiles), 20)) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].contains(webUtil.getText(By.xpath(productTiles + "[" + (i + 1) + "]/div[1]/p[1]")))) {
                    log.info(webUtil.getText(By.xpath(productTiles + "[" + (i + 1) + "]/div[1]/p[1]")));
                } else {
                    log.info("Product is not available in review page");
                }
            }
            if (!webUtil.isElementDisplayed(editYourOrder, 20)) {
                Assert.fail();
            } else {
                log.info("Edit link is displayed");
            }
        }
    }

    /**
     * Review page is verified
     *
     * @param productList the products which was paased from feature file
     * @param amount      the amount which was passed from the feature file
     * @param ID          the Id of the product from which we're doing the reinvest
     * @throws Exception
     */
    public void reviewPage(String productList, String amount, String ID) throws Exception {
        if (productList.contains(",")) {
            list = productList.split(",");
            amount1 = amount.split(",");
        } else {
            list = new String[1];
            amount1 = new String[1];
            list[0] = productList;
            amount1[0] = amount;
        }
        if (webUtil.isElementDisplayed(reviewPage, 20)) {
            if (webUtil.getText(reviewPage).contains(ID)) {
                log.info("Review Page is Displayed");
                reviewPageHelper(list);
            } else {
                log.info("Review page is not displayed");
            }
        } else {
            log.info("Review Page not displayed");
            Assert.fail();
        }
    }

    /**
     * When click on edit your order from the review page
     *
     * @throws Exception
     */
    public void editYourOrder() throws Exception {
        if (webUtil.isElementDisplayed(editYourOrder, 20)) {
            webUtil.click(editYourOrder);
            if (webUtil.isElementDisplayed(reinvestText, 20)) {
                if (webUtil.getText(reinvestText).equalsIgnoreCase("Reinvest")) {
                    log.info("You're again on reinvest page...");
                } else {
                    log.info("You're not on reinvest page...");
                }
            }
        }
    }

    public void clickReviewCheckBox() throws Exception {
        String str = "I accept that the prevailing General and Specific Terms and Conditions for State Savings on product Registration Date will be applicable" +
                " to all purchases made using full or partial Maturing Total, or if making a lodgement to a Deposit Account - such lodgement is subject to " +
                "the Post Office Savings Bank Regulations 1921 (as amended). All such purchases/lodgements may be subject to the applicable product/account" +
                " maximum purchase/balance/holding limits";
        if (webUtil.isElementclickable(reviewCheckBox, 20)) {
            webUtil.scrollToView(reviewCheckBox);
            if (webUtil.getText(reviewCheckBox).contains(str)) {
                log.info("Check Box Content is similar");
                try {
                    webUtil.click(reviewCheckBox);
                } catch (Exception e) {
                    throw new ExceptionUtils("Review Page check click is interrupted");
                }
                webUtil.waitForPageLoaded();
            } else {
                throw new ExceptionUtils("Check box content is not similar");
            }
        }
    }

    public void verifyErrorMessageReviewPage() throws Exception {
        if (webUtil.isElementDisplayed(errorMessageReviewPage, 20)) {
            if (webUtil.getText(errorMessageReviewPage).trim().contentEquals("You must tick the box to proceed.")) {
                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
                throw new ExceptionUtils("Error message verified on review page " + webUtil.getText(errorMessageReviewPage).trim() + " with " + "You must tick the box to proceed.");
            }
        }
    }

    public void clickReviewCancelButton() throws Exception {
        if (webUtil.isElementDisplayed(reviewCancelButton, 20)) {
            if (webUtil.getText(reviewCancelButton).contains("Cancel")) {
                webUtil.click(reviewCancelButton);
            } else {
                throw new ExceptionUtils("Review page cancel button content not verified");
            }
        } else {
            throw new ExceptionUtils("Review page cancel button x-path not found!");
        }
    }

    public void clickReviewConfirmButton() throws Exception {
        if (webUtil.isElementDisplayed(reviewConfirmButton, 20)) {
            if (webUtil.getText(reviewConfirmButton).contentEquals("Confirm Transaction")) {
                log.info("Review page Confirm button content is similar");
            } else {
                throw new ExceptionUtils("Review page Confirm button content validation failed");
            }
            try {
                webUtil.click(reviewConfirmButton);
                log.info("Review page confirm button is clicked");
            } catch (Exception e) {
                throw new ExceptionUtils("Review page confirm button is not clicked");
            }
        }
    }


}
