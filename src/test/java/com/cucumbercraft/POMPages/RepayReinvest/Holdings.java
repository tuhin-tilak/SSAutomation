package com.cucumbercraft.POMPages.RepayReinvest;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.framework.APIReusuableLibrary;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.Util;
import com.cucumbercraft.framework.WebDriverUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.List;

import static com.cucumbercraft.POMPages.Dash_Board.getProductName;
import static com.cucumbercraft.stepdefinitions.ReinvestSteps.getdata;

public class Holdings {

    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private static double maturity_value;
    private final By reinvestCashinButton = By.xpath(".//a[contains(text(),'Reinvest / Cash In') or contains(text(),'Cash In')]");
    private final By maturityValueText = By.xpath(".//strong");
    private WebElement available;
    private static final Logger log = LogManager.getLogger(Holdings.class);
    private final By allProductList = By.xpath("//div[@class='product-summary-info product-summary-info--card']");
    private final By installmentAllProductLit = By.xpath("//div[@class='product-summary-section product-summary-section--instalments']/div");
    private final By reinvestAndCashin = By.xpath("//*[contains(@id,'ReInvestCashInBlock')]/div[3]");
    private final String reinvestcashincontent = "//div[contains(@id,'p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_block_CashInReinvestWith%sIban')]";
    private final By goBack = By.xpath("//a[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Choose_Option_lnk_GoBack']");
    private final By donor = By.xpath("//div[@class=\"product-summary-info product-summary-info--card product-summary-info--card--no-bg\"]");
    public static String lastFourDigit;
    private static String methodType;
    private String string;
    APIReusuableLibrary api = new APIReusuableLibrary();


    public Holdings(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    /**
     * @param option
     * @param ID
     * @throws Exception
     */
    private void userSelectsMatureProductAndClicksOnButton(String option, String ID) throws Exception {
        methodType = option;
        String val;
        String str;
        if (getProductName().equals("Installment Savings") || getProductName().equals("Childcare Plus")) {
            if (webUtil.isElementDisplayed(installmentAllProductLit, 20)) {
                List<WebElement> list = driver.findElements(installmentAllProductLit);
                for (int i = 1; i <= list.size(); i++) {
//                    System.out.println(list.get(i - 1).getText());
                    if (list.get(i - 1).getText().contains(ID)) {
                        if (webUtil.getText(By.xpath("//div[@class='product-summary-section product-summary-section--instalments']/div[" + i + "]")).contains(getdata.get("Holding ID"))) {
                            str = webUtil.getText(By.xpath("//div[@class='product-summary-section product-summary-section--instalments']/div[" + i + "]//strong"));
                            str = str.replaceAll("[€,]", "");
//                            System.out.println(str);
                            if (!str.equals(""))
                                maturity_value = Double.parseDouble(str);
//                            avilableAmount = Double.parseDouble(webUtil.getText(availableamount).trim().replaceAll("[^0-9.]",""));
//                            log.info(maturity_value);
                            try {
                                webUtil.scrollToView(By.xpath("//div[@class='product-summary-section product-summary-section--instalments']/div[" + i + "]//a[1]"));
                                webUtil.click(By.xpath("//div[@class='product-summary-section product-summary-section--instalments']/div[" + i + "]//a[1]"));
                                ExtentCucumberAdapter.addTestStepLog("Reinvest / Cash In button is clicked");
                            } catch (Exception e) {
                                throw new ExceptionUtils("Reinvest / CashIn button is not clicked " + e.getMessage());
                            }
                        } else {
                            throw new ExceptionUtils("Repay-Reinvest x path not found!");
                        }
                        if (!str.equals(""))
                            selectOption(option);
                        break;
                    }
                }
            } else {
                throw new ExceptionUtils("Installment/Childcare/State savings Holdings not available");
            }
        } else if (getProductName().equals("Prize Bond")) {
            try {
                selectOption(option);
            } catch (Exception e) {
                throw new ExceptionUtils("Prize Bond Reinvest is not clicked");
            }
        } else {
            if (webUtil.isElementDisplayed(allProductList, 20)) {
                List<WebElement> list = driver.findElements(allProductList);
                list.stream()
                        .peek(webUtil::scroll)
                        .filter(webElement -> webElement.getText().contains(ID))
                        .findFirst()
                        .ifPresent(element -> {
                            if (!getdata.get("Product").contentEquals("SSA")) {
                                string = element.findElement(maturityValueText).getText().replaceAll("[^0-9.]", "");
                                maturity_value = Double.parseDouble(string);
                            }

                            element.findElement(reinvestCashinButton).click();
                        });
//                !option.contentEquals("null")
                if (!option.equalsIgnoreCase("null") && getdata.get("NSB Repay_Reinvest_Indicator").contentEquals("null"))
                    selectOption(option);

//                for (WebElement web : list) {
//                    val = web.getText();
//                    if (val.contains(ID)) {
//                        clickRepRein(ID);
//                        if (!getdata.get("MethodType").contentEquals("null") && getdata.get("NSB Repay_Reinvest_Indicator").contentEquals("null"))
//                            selectOption(option);
//                        break;
//                    }
//                }
            } else {
                throw new ExceptionUtils("Holdings not available");
            }
        }
    }

    /**
     * @param option
     * @param ID
     * @throws Exception
     */
    public void userSelectsMatureProductAndClickOnButton(String option, String ID) throws Exception {
        methodType = option;
        String val;
        try {
            List<WebElement> list = webUtil
                    .getWebDriverWait()
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(donor));
            WebElement donorholding = list
                    .stream()
                    .filter(element -> element.getText().contains(ID))
                    .findFirst()
                    .orElseThrow(() -> new ExceptionUtils("Donor holding not found"));
            maturity_value = Double.parseDouble(donorholding.findElement(By.xpath(".//strong")).getText().replaceAll("[^0-9.]", ""));
            webUtil.scroll(donorholding);
            donorholding.findElement(By.xpath(".//a")).click();
        } catch (Exception e) {
            throw new ExceptionUtils("Holdings not available");
        }
        selectOption(option);
        webUtil.waitForPageLoaded();
    }


    /**
     * Function for selecting option on choose option page
     *
     * @param option
     * @throws Exception
     */
    public Holdings selectOption(String option) throws Exception {
        methodType = option;
        if (option.equalsIgnoreCase("Reinvest")) {
            try {
                clickReinvest();
                log.info("Reinvest is selected");
                ExtentCucumberAdapter.addTestStepLog("Reinvest option is selected");
            } catch (Exception e) {
                throw new ExceptionUtils("Reinvest button is not clicked ".concat(e.getMessage()));
            }

        } else if (option.equalsIgnoreCase("Cash-In")) {
            try {
                clickCashInOption();
                log.info("Cash in is selected");
                ExtentCucumberAdapter.addTestStepLog("Cash In option is selected");
            } catch (Exception e) {
                throw new ExceptionUtils("Cash In button is not clicked ".concat(e.getMessage()));
            }
        } else if (option.equalsIgnoreCase("Reinvest-Repay")) {
            try {
                clickReinvestAndCashIn();
                log.info("Repay-Reinvest is selected");
                ExtentCucumberAdapter.addTestStepLog("Repay-Reinvest option is selected");
            } catch (Exception e) {
                throw new ExceptionUtils("Repay-Reinvest button is not clicked ".concat(e.getMessage()));
            }
        } else if (option.equalsIgnoreCase("null")) {
            clickCashInOption();
            log.info("Cash In option is selected");
            ExtentCucumberAdapter.addTestStepLog("Cash In option is selected");
        } else {
            throw new ExceptionUtils(getdata.get("MethodType").concat(" not selected"));
        }
        return this;
    }

    public void checkRepReinvestLink() throws Exception {
        String str = "//div[@class='product-summary-info product-summary-info--card']";
        String str1 = "//child::a";
        List<WebElement> web = driver.findElements(By.xpath(str));
        for (int i = 0; i < web.size(); i++) {
            if (webUtil.getText(By.xpath(str + "[" + (i + 1) + "]")).contains(getdata.get("Holding ID"))) {
                if (webUtil.isElementDisplayed(By.xpath(str + "[" + (i + 1) + "]" + str1), 20)) {
                    webUtil.scrollToView(By.xpath(str + "[" + (i + 1) + "]" + str1));
                    if (webUtil.isElementDisplayed(By.xpath("//div[@class='product-summary-section product-summary-section--instalments']/div[" + i + "]//a[contains(text(),'Reinvest / Cash in')]"), 20)) {
                        throw new ExceptionUtils("Repay-Reinvest link is displayed!");
                    } else {
                        log.info("Repay-Reinvest link is not displayed!");
                    }
                }
            }
        }
    }

    /**
     * Function for clicking on reinvest cashin option
     *
     * @param ID
     * @throws Exception
     */
    public void clickRepRein(String ID) throws Exception {
        String str = "//div[@class='product-summary-info product-summary-info--card']";
        String str1;
        String str2 = "//strong";
        webUtil.waitForPageLoaded();
        if (getdata.get("Product").contains("NSB")) {
            str1 = "//child::a[contains(text(),'Cash In')]";
        } else {
            str1 = "//child::a";
        }
        List<WebElement> web = driver.findElements(By.xpath(str));
        for (int i = 0; i < web.size(); i++) {
            if (webUtil.getText(By.xpath(str + "[" + (i + 1) + "]")).contains(ID)) {
                if (!getdata.get("Product").contentEquals("SSA"))
                    maturity_value = Double.parseDouble(webUtil.getText(By.xpath(str + "[" + (i + 1) + "]" + str2)).replaceAll("[^0-9.]", ""));
                log.info(Double.toString(maturity_value));
                if (getdata.get("NSB Repay_Reinvest_Indicator").equalsIgnoreCase("yes")) {
                    if (webUtil.isElementDisplayed(By.xpath(str + "[" + (i + 1) + "]" + str1), 20)) {
                        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
                        throw new ExceptionUtils("For NSB 1,2,3 Issue Reinvest / Cash In link is displayed");
                    } else {
                        ExtentCucumberAdapter.addTestStepLog("For NSB 1,2,3 Issue Reinvest / Cash In link is not displayed");
                        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
                    }
                } else {
                    webUtil.scrollToView(By.xpath(str + "[" + (i + 1) + "]" + str1));
                    try {
                        webUtil.click(By.xpath(str + "[" + (i + 1) + "]" + str1));
                    } catch (Exception e) {
                        throw new ExceptionUtils("Reinvest / Cash In link is not clicked");
                    }
                    webUtil.waitForPageLoaded();
                    break;
                }
            }
        }
    }

    public Holdings repayReinvest_Click(String ID) throws Exception {
        String str = "//div[@class='product-summary-info product-summary-info--card']";
        String str1 = "//child::a";
        String str2 = "//strong";
        webUtil.waitForPageLoaded();
        List<WebElement> web = driver.findElements(By.xpath(str));
        available = web.stream().peek(webUtil::scroll).filter(webElement -> webElement.getText().contains(ID)).findFirst().orElseThrow(() -> new ExceptionUtils("Holdings id not available"));
        maturity_value = Double.parseDouble(available.findElement(By.xpath(".//strong")).getText().replaceAll("[^0-9.]", ""));
        log.info("Maturity Value of current holding: " + maturity_value);
        webUtil.scroll(available);
        if (available.getText().contains("Reinvest / Cash In")) {
            try {
                available.findElement(By.xpath(".//a[contains(text(),'Reinvest / Cash In')]")).click();
                ExtentCucumberAdapter.addTestStepLog("Reinvest/Cashin Button clicked on holding page");
                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            } catch (Exception e) {
                throw new ExceptionUtils("Reinvest/Cashin button amended in DOM");
            }
        } else {
            if (available.getText().contains("Pending transaction")) {
                log.info("Current holding has Pending Transaction flag");
            }
        }
        return this;
    }

    private void clickReinvest() throws InterruptedException {
        By btnReinvest = By.xpath("//*[contains(@id,'block_Reinvest')]");
        webUtil.click(btnReinvest);

    }

    private void clickCashInOption() throws InterruptedException {
//        if (!getdata.get("Product").equals("Prize Bond")) {
//            if (getdata.get("IBAN_Added_Status").contentEquals("Yes") || Add_Iban_flag) {
//                try {
//                    webUtil.click(cashInOption);
//                    log.info("Cash In option is selected");
//                } catch (Exception e) {
//                    throw new ExceptionUtils("Cash-In option is not clicked for ".concat(getdata.get("Product").concat(" Holding ID: ").concat(getdata.get("Holding ID")).concat(e.getMessage())));
//                }
//            } else if (getdata.get("IBAN_Added_Status").contentEquals("No")) {
//                try {
//                    webUtil.clickLocator(cashInOptionIban);
//                    log.info("Cash In option is selected");
//                } catch (Exception e) {
//                    throw new ExceptionUtils("Cash in option with iban click is interrupted ".concat(getdata.get("Product").concat(" Holding ID: ").concat(getdata.get("Holding ID")).concat(e.getMessage())));
//                }
//            } else {
//                throw new ExceptionUtils("Cash in-Option x-path have been changed");
//            }
//        } else {
//            if (getdata.get("IBAN_Added_Status").contentEquals("Yes") || Add_Iban_flag) {
//                try {
//                    webUtil.click(PBCashinOption);
//                } catch (Exception e) {
//                    throw new ExceptionUtils("for ".concat(getdata.get("Product").concat(" Holding ID: ").concat(getdata.get("Holding ID")).concat(e.getMessage())));
//                }
//            } else if (getdata.get("IBAN_Added_Status").contentEquals("No")) {
//                try {
//                    webUtil.click(cashInOptionIban);
//                } catch (Exception e) {
//                    throw new ExceptionUtils("Cash in option with iban click is interrupted ".concat(getdata.get("Product").concat(" Holding ID: ").concat(getdata.get("Holding ID")).concat(e.getMessage())));
//                }
//            } else {
//                throw new ExceptionUtils("Cash in-Option x-path may have been changed");
//            }
//        }
        By btnCashIn = By.xpath("//*[text()='Cash In']/parent::div");
        webUtil.click(btnCashIn);
    }

    private void clickReinvestAndCashIn() throws Exception {


//        webUtil.isElementDisplayedLog(reinvestAndCashin, 10, "Reinvest & CashIn");
        webUtil.click(reinvestAndCashin);


//        if (Cashin_Reinvest.flag)
//            try {
//                webUtil.clickLocator(By.xpath(String.format(reinvestAndCashin, "out")));
//                log.info("Reinvest and Cash In option is Clicked");
//            } catch (Exception e) {
//                throw new ExceptionUtils("Reinvest and Cash in link click may have been interrupted ".concat(e.getMessage()));
//            }
//        else {
//            try {
//                WebElement element = driver.findElement(By.xpath(String.format(reinvestAndCashin, "")));
////                JavascriptExecutor js = (JavascriptExecutor) driver;
////                js.executeScript("arguments[0].click();", element);
//                webUtil.clickLocator(By.xpath(String.format(reinvestAndCashin, "")));
//                log.info("Reinvest and Cash In option is Clicked");
//            } catch (Exception e) {
//                throw new ExceptionUtils("Reinvest and Cash in link click may have interrupted");
//            }
//        }
    }

    public Holdings reinvest_cashincontent() throws Exception {
        if (Cashin_Reinvest.flag)

            try {
                webUtil.click(reinvestAndCashin);
                log.info("Reinvest and Cash In option is Clicked");
            } catch (Exception e) {
                throw new ExceptionUtils("Reinvest and Cash in link click may have been interrupted ".concat(e.getMessage()));
            }
        else if (webUtil.isElementDisplayed(reinvestAndCashin, 10)) {


            if (!webUtil.getText(By.xpath(String.format(reinvestcashincontent, "").concat("/h6"))).contains("Reinvest and Cash In")) {
                log.error("Reinvest and Cash In option header not verified");
                throw new ExceptionUtils("Reinvest and Cash In option header content not matched");
            }
            if (!webUtil.getText(By.xpath(String.format(reinvestcashincontent, "").concat("/p"))).contentEquals("Reinvest in State Savings product(s) AND cash in")) {
                log.error("Reinvest and Cash In option paragrph not verified");
                throw new ExceptionUtils("Reinvest and Cash In option para content not matched");
            }

        } else {
            throw new ExceptionUtils("Reinvest Cash in link x-path may have been changed");
        }
        return this;
    }


    private void goBackButton() {
        try {
            webUtil.click(goBack);
        } catch (Exception e) {
            throw new ExceptionUtils("Go Back Button Click may have been interrupted");
        }
    }


    public void selectMatureProduct(String option, String ID) throws Exception {
        userSelectsMatureProductAndClicksOnButton(option, ID);
    }

    public double getMaturityValue() {
        return maturity_value;
    }

    public String getMethodType() {
        return methodType;
    }


    public String get_Last_For_Digit_IBAN() throws Exception {
        try {
            lastFourDigit = api.getUserDetails(getdata.get("Username")).get("bankAccountIban").replaceAll("[^0-9]", "");
//                    webUtil.getText(Ibanno).replaceAll("[^0-9]", "");
        } catch (ExceptionUtils e) {
            throw new ExceptionUtils("IBAN text is not displayed ".concat(e.getMessage()));
        }
        return lastFourDigit;
    }

    public void pendingTransaction(String ID) throws IOException {
        String str = "//div[@class='product-summary-info product-summary-info--card']";
        String str1 = "//child::a";
        String str2 = "//strong";

        if (getProductName().contains("Installment Savings") || getProductName().contains("Childcare Plus")) {
            List<WebElement> list = driver.findElements(donor);
            available = list.stream().parallel().filter(i -> i.getText().contains(ID)).findFirst().orElseThrow(() -> new ExceptionUtils("Child holding not found"));
            webUtil.scroll(available);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
        } else {
            List<WebElement> web = driver.findElements(By.xpath(str));
            available = web.stream().parallel().filter(i -> i.getText().contains(ID)).findFirst().orElseThrow(() -> new ExceptionUtils("Holdings id not available"));
            webUtil.scroll(available);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
        }

        System.out.println(available.getText());
        if (available.getText().contains("Pending transaction")) {
            try {
                available.findElement(By.xpath(".//button[@data-modal='pending-transaction-modal']")).click();
                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            } catch (Exception e) {
                throw new ExceptionUtils("Pending transaction not clicked ");
            }

        } else
            throw new ExceptionUtils("Pending Transaction text not verified");
    }
}
