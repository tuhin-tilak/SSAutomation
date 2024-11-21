package com.cucumbercraft.POMPages.RepayReinvest;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.Dash_Board;
import com.cucumbercraft.POMPages.Modals;
import com.cucumbercraft.framework.*;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

import static com.cucumbercraft.POMPages.Dash_Board.getProductName;
import static com.cucumbercraft.POMPages.RepayReinvest.Prize_Bond.prize_amount;
import static com.cucumbercraft.POMPages.RepayReinvest.Cashin_Reinvest.las4digits;
import static com.cucumbercraft.POMPages.RepayReinvest.Cashin_Reinvest.maturity_val;
import static com.cucumbercraft.POMPages.RepayReinvest.Repayment.avilableAmount;
import static com.cucumbercraft.POMPages.RepayReinvest.Repayment.ibanNumber;
import static com.cucumbercraft.POMPages.RepayReinvest.SelectProduct.maturityvalue;
import static com.cucumbercraft.framework.FrameworkLogger.log;
import static com.cucumbercraft.stepdefinitions.ReinvestSteps.getdata;


public class ReviewTransactionPage {
    private WebDriver driver;
    private final WebDriverUtil webUtil;
    private boolean chekboxflag;
    List<WebElement> prdlst;
    List<WebElement> amntlst;
    private String[] products;
    private String[] amounts;
    private String[] reviewProduct;
    private String[] reviewAmount;
    private final Holdings holdings = new Holdings(driver);
    private final Dash_Board dashBoard = new Dash_Board(driver);
    private final Cashin_Reinvest cashinReinvest;
    private final APIReusuableLibrary api = new APIReusuableLibrary();
    final Map<String, String> mapforReview = new HashMap<>();
    static final Logger log = LogManager.getLogger(ReviewTransactionPage.class);
    private String getamount;
    private double sum;


    private final By reviewPageID = By.xpath("//div[@class='product-options-info row']//p");
    private final By reviewtcCheckbox = By.xpath("//label[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_lblTermCondition']");
    private final By reviewConfirm = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_btnConfirmReview']");
    private final By productXpath = By.xpath("//p[@class='product-review-list--title']");
    private final By amountXpath = By.xpath("//div[@class='small-4 columns product-review-list__amount']");
    private final String productTiles = "//div[@class='product-review-list--row row']";
    private final By evaluateCashin = By.xpath("//*[@id=\"p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_pnl_CashInReInvest\"]//div[@class='small-8 columns']");
    private final By evaluateCashin1 = By.xpath("//p[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_ibanInfo']");
    private final By rviewCashinamount = By.xpath("(//div[@class='small-4 columns product-review-list__amount'])[last()]");
    private final By reviewCashinAmount = By.xpath("//div[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_cashInAmt']");
    private final By rpIBAN = By.id("p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_ibanInfo");
    private final By delayCheckBox = By.xpath("//label[@for='terms1']");

    private final By cashinIBAN = By.xpath("//p[@class='product-review-list--description']");
    private final By cashinIBANLabel = By.xpath("//p[@class='product-review-list--title']");
    private final By cashinIbanLabelBelowMessage = By.id("p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_ibanInfo");
    private final By cashinAmount = By.xpath("//div[contains(@class,'product-review-list__amount')]");
    private final By termsAndCondition = By.id("p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_CashInTC");
    private final By cashinTerms = By.xpath("//div[contains(@class,'cta__edit')]/following-sibling::p");
    private final By headerBelowMessage = By.xpath("//h2/following-sibling::p");
    private final By header = By.xpath("//h2");
    private final By reviewTextVerify = By.xpath("//label[@for='terms4']");

    //Error on review page pb
    private final By noIbanAddedError = By.id("p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_errorMessage");
    private final By checkBoxerror = By.xpath("//span[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_CVCheck']");

    //Review Page Reinvest
    private final By productName = By.xpath("//*[@class='product-review-list--title']");
    private final By amount = By.xpath("//*[@class='small-4 columns product-review-list__amount']");


    public ReviewTransactionPage(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
        cashinReinvest = new Cashin_Reinvest(driver);
    }


    /**
     * Function validates details available on
     * review page, which previously user has enteredl̥
     *
     * @param product - State saving product
     * @param amount  - amount entered for selected products
     * @param counter
     * @throws Exception
     */
    public void reviewPagedetails(String ID, String product, String amount, String counter) throws Exception {

        getamount = amount;
        try {
            webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("review"));
        } catch (Exception e) {
            throw new ExceptionUtils("Review page not displayed");
        }

        System.out.println(webUtil.getText(reviewPageID));
        System.out.println(webUtil.getText(reviewPageID).contains(ID));
        if (webUtil.getText(reviewPageID).contains(ID) || webUtil.getText(reviewPageID).contains("Prize Bond")) {
            if (!Objects.equals(counter, "cashIn")) {
                reviewUtile(product, amount);
                getReviewValidation(counter);
                ExtentCucumberAdapter.addTestStepLog("Selected product and amount is matched on review page");
            } else {
                reviewAllocatedIn();
                ExtentCucumberAdapter.addTestStepLog("Allocated full amount product and amount is matched on review page");
            }
            contentvalidation();
//            webUtil.click(reviewConfirm);
        } else {
            throw new ExceptionUtils("Page Not Found");
        }

    }

    /**
     * Function used reveiew page content validation
     *
     * @throws Exception
     */
    public void contentvalidation() throws Exception {

        String reviewText = "I accept that all purchases using full or partial Maturing Total will be subject, as appropriate, to the prevailing General and Specific Terms and Conditions for Ireland State Savings on product Registration Date and/or to the Post Office Savings Regulations 1921 (as amended). All such purchases/lodgements may be subject to maximum holding/deposit limits.\n" +
                "\n" +
                "Please note: No further interest, bonus or other amount shall accrue in respect of a Product on or after the Maturity Date. The product Registration Date of your new Product(s) will be confirmed once the registration process has been completed.";
        String cashinReviewContent = "All transactions are subject to Ireland State Savings Terms and Conditions.";


        if (getdata.get("MethodType").contentEquals("Reinvest") || getdata.get("MethodType").contentEquals("Reinvest-Repay")) {
            webUtil.gettextlog(reviewTextVerify, String::equals, reviewText);
            webUtil.click(reviewTextVerify);
        } else webUtil.gettextlog(reviewTextVerify, String::equals, cashinReviewContent);


    }

    /**
     * Function used to validate product in review page
     * contains multiple or single product
     *
     * @param product
     * @param amount
     */
    public void reviewUtile(String product, String amount) {
        prdlst = webUtil.getElements(productXpath);
        amntlst = webUtil.getElements(amountXpath);
        if (product.contains(",")) {
            products = product.split(",");
            amounts = amount.split(",");
        } else {
            products = new String[1];
            amounts = new String[1];
            products[0] = product;
            amounts[0] = amount;
        }
    }

    public void productVerify(int i) {
        System.out.println(Arrays.toString(products));
        System.out.println(Arrays.toString(amounts));
        System.out.println(prdlst.get(i).getText());
        System.out.println(amntlst.get(i).getText().replaceAll("[^0-9.]", ""));
        if (prdlst.get(i).toString().contentEquals(products[i])) {
            if (amntlst.get(i).getText().replaceAll("[^0-9.]", "").contentEquals(amounts[i])) {
                log.info(prdlst.get(i).getText() + " matched with " + products[i]);
                log.info(amntlst.get(i).getText().replaceAll("[^0-9.]", "") + " matched with " + amounts[i]);
            } else {
                log.info(amntlst.get(i).getText().replaceAll("[^0-9.]", "") + " Not matched with " + amounts[i]);
            }
        } else {
            log.info(prdlst.get(i).getText() + " Not matched with " + products[i]);
        }
    }

    public void reviewPBReinvest() throws Exception {
        reviewUtile(getdata.get("Products"), getdata.get("Amount"));
        for (int i = 0; i < prdlst.size(); i++) {
            productVerify(i);
        }
        contentvalidation();
    }


    /**
     * Function used to validate user input is same on review page
     *
     * @param counter
     * @throws Exception
     */
    public void getReviewValidation(String counter) throws Exception {
        if (webUtil.isElementDisplayed(By.xpath(productTiles), 20)) {
            reviewProduct = new String[prdlst.size()];
            reviewAmount = new String[amntlst.size()];
            if (counter.contains("null")) {
                revutils(counter);
            } else {
                allocateReview(counter);
            }
        } else
            log.info("Product not displayed on review page");
    }

    public void revutils(String counter) throws Exception {
        for (int i = 0; i < products.length; i++) {
            reviewProduct[i] = prdlst.get(i).getText().replaceAll("[\r\n]", " ");
            reviewAmount[i] = amntlst.get(i).getText().replaceAll("[^0-9.]", "");
            if (reviewProduct[i].contains("Prize Bonds")) {
                getPBValidation(i, counter);
            } else
                normalValidation(i, counter);
        }
        if (!getProductName().contains("Prize Bond") && holdings.getMethodType().contains("Reinvest-Repay")) {
            reviewCashing();
        } else
            reviewCashinPB();

    }


    /**
     * @param i
     * @param counter
     */
    public void getPBValidation(int i, String counter) {

        double liquid = Double.parseDouble(amounts[i]) / 6.25;
        String val = (getMapforReview().get(products[i]) + (int) liquid).concat(" ".concat("units"));
        if (reviewProduct[i].contains(val) && reviewAmount[i].contains(amounts[i])) {
            log.info(reviewProduct[i] + " " + "product matched with" + " " + val);
            log.info(reviewAmount[i] + " " + "amount matched with" + " " + amounts[i]);
        }

    }


    /**
     * @param i
     * @param counter
     * @throws Exception
     */
    public void normalValidation(int i, String counter) {
        webUtil.CompareString(reviewProduct[i], String::equals, products[i]);
        webUtil.CompareString(reviewAmount[i], String::equals, String.format("%.2f", Double.parseDouble(amounts[i])));


    }

    /**
     * @param
     * @param counter
     */
    public void allocateReview(String counter) throws Exception {
        int countr = Integer.parseInt(counter) - 1;
        if (!products[countr].contains("Prize Bonds")) {
            String revproduct = webUtil.getText(productXpath).replaceAll("[\r\n]+", " ");
            Double revamount = Double.parseDouble(webUtil.getText(amountXpath).replaceAll("[^0-9.]", ""));
            if (revproduct.contentEquals(getMapforReview().get(products[countr]))) {
                System.out.println(amounts[countr]);
                System.out.println(revamount == Double.parseDouble(amounts[countr]));
                if (revamount == holdings.getMaturityValue() || revamount == Double.parseDouble(amounts[countr])) {
                    log.info(revproduct + " " + "product matched with" + " " + getMapforReview().get(products[countr]));
                    if (holdings.getMaturityValue() == revamount)
                        log.info(revamount + " " + "amount matched with" + " " + holdings.getMaturityValue());
                    else log.info(revamount + " " + "amount matched with" + " " + Double.parseDouble(amounts[countr]));
                }
            } else
                log.info("product not available allocate");
        } else
            allocatePBreview(counter);
    }

    /**
     * @param counter
     */
    public void allocatePBreview(String counter) throws Exception {
        String revproduct = webUtil.getText(productXpath).replaceAll("[\r\n]+", " ").replaceFirst("[^A-Za-z0-9 ]", "");
        String revamount = webUtil.getText(amountXpath).replaceAll("[^0-9.]", "");
        int countr = Integer.parseInt(counter) - 1;
        double liquid = holdings.getMaturityValue() / 6.25;
        String val = getMapforReview().get(products[countr]).concat(String.valueOf((int) liquid).concat(" ".concat("units")));
        String total_Amount = Double.toString(holdings.getMaturityValue());
        if (holdings.getMaturityValue() % 6.25 == 0) {
            if (revproduct.contains(val) && revamount.contains(total_Amount)) {
                log.info(revproduct + " " + "product matched with" + " " + val);
                log.info(revamount + " " + "amount matched with" + " " + total_Amount);
            } else
                log.info("product not available hello");
        } else {
            double cash = (int) liquid * 6.25;
            double restamount = BigDecimal.valueOf(holdings.getMaturityValue()).subtract(BigDecimal.valueOf(cash)).doubleValue();
            if (revproduct.contains(val) && revamount.contains(Double.toString(cash))) {
                log.info(revproduct + " " + "product matched with" + " " + val);
                log.info(revamount + " " + "amount matched with" + " " + cash);
                if (webUtil.isElementDisplayed(evaluateCashin, 10)) {
                    if (webUtil.getText(evaluateCashin).replaceAll("[^0-9]", "").equals(las4digits)) {
                        log.info("Iban Matched" + " " + las4digits);
                        String ramt = webUtil.getText(rviewCashinamount).replaceAll("[^0-9.]", "");
                        if (ramt.contains(Double.toString(restamount))) {
                            log.info(restamount + " " + "cashin amount matched" + " " + ramt);
                        } else
                            log.info("amount not matched");
                    } else
                        throw new ExceptionUtils("IBAN not matched");
                } else
                    log.info("product not available");

            }
        }
    }


    /**
     * @return
     */
    public Map<String, String> getMapforReview() {
        mapforReview.put("Savings Bond - 3 Year", "Savings Bond");
        mapforReview.put("National Solidarity Bond - 4 Year", "National Solidarity Bond - 4 Year");
        mapforReview.put("Savings Certificate - 5 Year", "Savings Certificate");
        mapforReview.put("National Solidarity Bond - 10 Year", "National Solidarity Bond - 10 Year");
        mapforReview.put("Deposit Account (POSB - book based)", "Deposit Account (POSB - book based) Account number:");
        mapforReview.put("Prize Bonds", "Prize Bonds ");
        return mapforReview;
    }

    /**
     * @throws Exception
     */
    public void reviewCashing() throws Exception {
        By label = By.xpath("(//p[@class='product-review-list--title'])[last()]");
        By expectedIban = By.xpath("(//p[@class='product-review-list--description'])[last()]");
        webUtil.gettextlog(label, String::equals, "Cash in to bank account", "Cashin Label Text");
        String IBAN = api.getUserDetails(getdata.get("Username")).get("bankAccountIban").replaceAll("[^0-9]", "");
        webUtil.gettextlog(expectedIban, String::equals, "IBAN ~" + IBAN, "IBAN Text");
        sum = getamount.contains(",") ? Arrays.stream(getamount.split(",")).mapToDouble(Double::parseDouble).sum() : Double.parseDouble(getamount);
        String amount = BigDecimal.valueOf(maturity_val).subtract(BigDecimal.valueOf(sum)).stripTrailingZeros().toPlainString();
        if (sum < maturity_val) {
            String actual = webUtil.getText(rviewCashinamount).replaceAll("[^.0-9]", "");
            actual = new BigDecimal(actual).stripTrailingZeros().toPlainString();
            webUtil.CompareString(actual, String::equals, amount);

        }


    }

    public void bondRangeReviewPage() throws Exception {
        webUtil.gettextlog(header, String::equals, "Transaction Review");
        webUtil.gettextlog(headerBelowMessage, String::equals, "Review your transaction details for Prize Bonds");
        webUtil.gettextlog(cashinIBANLabel, String::equals, "Cash in to bank account");
        var IBAN = api.getUserDetails(getdata.get("Username")).get("bankAccountIban");
        var expectedIBAN = IBAN.substring(IBAN.length() - 4);
        webUtil.gettextlog(cashinIBAN, String::equals, "IBAN ~" + expectedIBAN);

        webUtil.gettextlog(cashinTerms, String::equals, "All transactions are subject to Ireland State Savings Terms and Conditions.");

        System.out.println("Review Page Amount: " + webUtil.getText(cashinAmount).trim().replaceAll("[^0-9.]", ""));
        System.out.println("Amount: " + prize_amount);
        String expectedAmnt = webUtil.getText(cashinAmount).trim().replaceAll("[^0-9.]", "");
        expectedAmnt = expectedAmnt.endsWith(".00") ? String.format("%.1f", Double.parseDouble(expectedAmnt)) : expectedAmnt;
        if (Double.parseDouble(expectedAmnt) == prize_amount) {
            log(LogType.EXTENTANDCONSOLEPASS, "Prize Amount  matched: " + expectedAmnt + " with " + prize_amount);
        } else {
            log(LogType.EXTENTANDCONSOLEFAIL, "Prize Amount not matched: " + expectedAmnt + " with " + prize_amount);
        }


    }

    public void cashInReviewPB() throws Exception {
        webUtil.gettextlog(header, String::equals, "Transaction Review");
        webUtil.gettextlog(headerBelowMessage, String::equals, "Review your transaction details for Prize Bonds");
        webUtil.gettextlog(cashinIBANLabel, String::equals, "Cash in to bank account");
        var IBAN = api.getUserDetails(getdata.get("Username")).get("bankAccountIban");
        var expectedIBAN = IBAN.substring(IBAN.length() - 4);
        webUtil.gettextlog(cashinIBAN, String::equals, "IBAN ~" + expectedIBAN);

        if (getdata.get("Allocate Full Amount Index").equals("null")) {
            Double liquid = Double.parseDouble(webUtil.getText(cashinAmount).replaceAll("[^0-9.]", ""));
            double value = Double.valueOf(getdata.get("Prize Amount")) / 6.25;
            double value1 = Double.valueOf(getdata.get("Prize Amount")) / 6.35;
            double liquid1 = 6.25 * value;
            double liquid2 = 6.35 * value1;
            if (liquid.toString().equals(String.valueOf(liquid1))) {
                log(LogType.EXTENTANDCONSOLEPASS, "Amount matched" + liquid + " with amount entered: " + liquid1);
            } else if (liquid.toString().equals(String.valueOf(liquid2))) {
                log(LogType.EXTENTANDCONSOLEPASS, "Amount matched" + liquid + " with amount entered: " + liquid2);
            } else {
                throw new ExceptionUtils("Amount not matched");
            }
        } else {
            String liquid = (Modals.amount.replaceAll("[^0-9.]", ""));
            String liquid1 = webUtil.getText(cashinAmount).replaceAll("[^0-9.]", "");

            if (!liquid.contentEquals(liquid1)) {
                throw new ExceptionUtils("Amount not matched");
            }

        }
        log.info("Amount on transaction page matched");
        webUtil.gettextlog(cashinTerms, String::equals, "All transactions are subject to Ireland State Savings Terms and Conditions.");

    }

    public void reviewCashinPB() throws Exception {
        webUtil.waitForPageLoaded();
        if (webUtil.isElementDisplayed(evaluateCashin, 10)) {

            if (getamount.contains(",")) {
                amounts = getamount.split(",");
                int[] amo = Stream.of(amounts).mapToInt(Integer::parseInt).toArray();
                sum = Arrays.stream(amo).sum();
            } else {
                sum = Integer.parseInt(getamount);
            }
            double amount = BigDecimal.valueOf(maturityvalue).subtract(BigDecimal.valueOf(sum)).doubleValue();
            if (sum < maturityvalue) {
                String ramt = webUtil.getText(rviewCashinamount).replaceAll("[^0-9.]", "");
                String recurring = Double.toString(amount);
                if (ramt.contains(recurring)) {
                    log.info(recurring + " " + "cashin amount matched" + " " + ramt);
                }
            } else {
                log.info("Allocated full amount to" + Arrays.toString(reviewProduct));
            }

        }
    }


    /**
     * @throws Exception
     */
    public void reviewAllocatedIn() throws Exception {
        if (webUtil.isElementDisplayed(evaluateCashin, 10)) {
            if (webUtil.getText(evaluateCashin).replaceAll("[^0-9]", "").equals(las4digits)) {
                log.info("Iban Matched");
                String ramt = webUtil.getText(rviewCashinamount).replaceAll("€", "");
                String maturityValue = Double.toString(holdings.getMaturityValue());
                if (ramt.contains(maturityValue)) {
                    log.info(ramt + " " + "cashin amount matched" + " " + maturityValue);
                }
            }
        }
    }


    public void reviewAllocated() throws Exception {
        webUtil.waitForPageLoaded();
        if (webUtil.isElementDisplayed(evaluateCashin1, 20)) {
            if (!webUtil.getText(evaluateCashin1).replaceAll("[^0-9]", "").equals(ibanNumber)) {
                throw new ExceptionUtils("Iban not matched in review page");
            } else {
                log.info("IBAN Matched: " + webUtil.getText(evaluateCashin1) + " And " + ibanNumber);
            }
            String ramt = webUtil.getText(reviewCashinAmount).replaceAll("[^0-9.]", "");
            String maturityValue = Double.toString(holdings.getMaturityValue());
            if (!ramt.contains(maturityValue) || !ramt.contains(getdata.get("Amount"))) {
                throw new ExceptionUtils("Matured Amount: " + maturityValue + " and entered amount not matched: " + ramt);
            } else {
                log.info("Amount matched");
                chekboxflag = true;
            }
            try {
                contentvalidation();
            } catch (Exception e) {
                log.info("Content is not verified on the review page");
                throw new ExceptionUtils("Review page content is not verified");
            }
        }
    }


    public void reviewAllocatedCashin() throws Exception {
        if (webUtil.isElementDisplayed(evaluateCashin1, 5)) {
            System.out.println(webUtil.getText(evaluateCashin1));
            String IBAN = StringUtils.right(getdata.get("IBAN_Number").trim(), 4);

            String ramt = webUtil.getText(reviewCashinAmount).replaceAll("[^0-9.]", "");
            String maturityValue = Double.toString(holdings.getMaturityValue());

            System.out.println(avilableAmount);
            if (getdata.get("MethodType").contentEquals("null")) {
                if (ramt.contentEquals(String.valueOf(avilableAmount))) {
                    log.info("Amount matched");
                } else if (Double.parseDouble(ramt) == Double.parseDouble(getdata.get("Amount"))) {
                    log.info("Amount matched");
                } else {
                    throw new ExceptionUtils("Amount not matched on the review page");
                }
            } else {
                if (ramt.contains(String.valueOf(holdings.getMaturityValue())) || ramt.contentEquals(getdata.get("Amount"))) {
                    log.info("Amount matched");
                } else {
                    throw new ExceptionUtils("Amount not matched on the review page");
                }
            }
            if (getdata.get("Product").contentEquals("Prize Bond")) {
                try {
                    contentvalidation();
                } catch (Exception e) {
                    log.info("Content is not verified on the review page");
                    throw new ExceptionUtils("Review page content is not verified");
                }
            }
        }
    }


    public void delayCheckbox() throws Exception {
        String delayCheckBoxContent = "Delay the cash in transaction if additional interest is due in the next 30 days.";
        webUtil.gettextlog(delayCheckBox, String::equals, delayCheckBoxContent);

        try {
            webUtil.click(delayCheckBox);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
        } catch (Exception e) {
            throw new ExceptionUtils("Delay check box click is interrupted");
        }

    }


    public void errorOnReviewPage() throws Exception {
        log.info("Review Page error");
        if (webUtil.isElementDisplayed(noIbanAddedError, 05)) {
            if (webUtil.getText(noIbanAddedError).contentEquals("Error Message - There is no IBAN associated with this account"))
                throw new ExceptionUtils("Error message displayed " + webUtil.getText(noIbanAddedError) + " is verified");
            else
                throw new ExceptionUtils("Error message displayed " + webUtil.getText(noIbanAddedError) + " is not verified");
        } else if (webUtil.isElementDisplayed(checkBoxerror, 05)) {
            if (webUtil.getText(checkBoxerror).contentEquals("Please accept term & conditions."))
                throw new ExceptionUtils("Error message displayed " + webUtil.getText(checkBoxerror) + " is verified");
            else
                throw new ExceptionUtils("Error message displayed " + webUtil.getText(checkBoxerror) + " is not verified");
        } else {
            throw new ExceptionUtils("More amount error message x-path not found!");
        }
    }

    public void clickEditOrder() throws Exception {
        By edit = By.xpath("//*[text()='Edit your order']");

        webUtil.click(edit);


    }


}
