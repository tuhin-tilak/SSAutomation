package com.cucumbercraft.POMPages.BuyNow;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.Models.PurchaseModel;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.Context;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class YourOrder {

    private final By fldAmount = By.xpath(".//td//input");
    WebDriver driver;
    WebDriverUtil webUtil;
    Context testContext;
    private final By ftProduct = By.xpath("//div[@class='ec-products_card']");
    private final By hdrYourOrder = By.xpath("// div[@class='wrapper__ecommerce']// h3[@class='ec-form__title']");
    private final By productName = By.tagName("h3");
    private final By txtIssue = By.tagName("p");
    private final By btnBuyNow = By.xpath("//li[contains(@id,'buyMoreBlock')]/a");

    List<WebElement> productList;
    public List<String> products, amounts;
    List<String> lblProductList = List.of(
            "Prize Bonds",
            "Prize Bonds as a Gift",
            "National Solidarity Bonds",
            "Savings Certificates",
            "Savings Bonds"
    );
    private final Map<String, Consumer<Integer>> consumerMap = Map.of(
            lblProductList.get(0), this::verifyProduct,
            lblProductList.get(1), this::verifyProduct,
            lblProductList.get(2), this::verifyNationalSolidarityBonds,
            lblProductList.get(4), this::verifySavingsBonds,
            lblProductList.get(3), this::verifySavingsCertificates
    );

    public Map<String, String[]> productMap = Map.of(
            lblProductList.get(0), new String[]{null, "true"},
            lblProductList.get(1), new String[]{null, "true"},
            lblProductList.get(2), new String[]{"10 year | Issue 9", "false"},
            lblProductList.get(4), new String[]{"3 year | Issue 18", "false"},
            lblProductList.get(3), new String[]{"5 year | Issue 25", "false"}
    );
    private final By yourOrderPrdList = By.xpath("//td/h4");
    YourDetail detail;
    JourneyHandler journeyHandler;
    private Map<String, String> cartMap;
    private final By yourOrderAmtList = By.xpath("//tr[@class='js-ec-your-order-row']");
    public final By productText = By.xpath(".//h4");
    private final By amountText = By.xpath(".//td[2]//input");
    private final By pbAmountText = By.xpath(".//td//span[contains(@class,'js-input-total')]");
    public final By issueText = By.xpath(".//p[1]");
    private final By btnContinueToReview = By.xpath("//button[contains(@id,'SubmitCart')]");

    public YourOrder(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
        detail = new YourDetail(driver);
        journeyHandler = new JourneyHandler(driver);
    }


    public JourneyHandler addProductToCart(String product) {
        String btnAdd = "//a[@title='%s']/following-sibling::a";
        Function<String, WebElement> stringFunction = string -> webUtil.waitUntilElementVisible(By.xpath(String.format(btnAdd, string)), 5);
        WebElement element = stringFunction.apply(product);
        webUtil.scroll(element);
        element.click();
        return new JourneyHandler(driver);

    }

    public YourOrder addMultipleProduct(PurchaseModel data) throws InterruptedException {
        webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("your-order"));
        for (int i = 1; i < products.size(); i++) {
            switch (products.get(i)) {
                case "Prize Bonds":
                case "National Solidarity Bonds":
                case "Savings Bonds":
                case "Savings Certificates":
                    addProductToCart(products.get(i));
                    if (products.indexOf("Prize Bonds as a Gift") == 0) {
                        if (i == 1) journeyHandler.getJourney(data.getJourney(), data).run();
                    }
                    break;
                case "Prize Bonds as a Gift":
                    addProductToCart(products.get(i));
                    detail.enterPBHolderDetails(data.getPbHolderFlag(), data).run();
                    break;
            }

        }
        return this;
    }

    public YourOrder assignDefaultValues(List<String> product, List<String> amount) {
        products = product;
        amounts = amount;


        Function<Integer, String> getAmountOrDefault = i -> amounts.size() > i ? amounts.get(i) :
                (products.get(i).equals("Prize Bonds") || products.get(i).equals("Prize Bonds as a Gift") ? "4" : "50");

        cartMap = IntStream.range(0, products.size())
                .boxed()
                .collect(Collectors.toMap(products::get, getAmountOrDefault));
        return this;
    }


    @SneakyThrows
    public YourOrder enterAmount() {

        ExtentCucumberAdapter.addTestStepLog("<font color='cyan'><b>Added these product for purchase</b></font>");
       By increment = By. xpath("// span[@class='incrementor__up']");
       By pbValue=By.xpath("//input[@class='incrementor__value js-input-value']");


        for (int i = 0; i < products.size(); i++) {

            List<WebElement> webElements = webUtil.getElements(yourOrderAmtList);

            if (products.size() != amounts.size()) amounts.add(i, cartMap.get(products.get(i)));
            if (!products.get(i).equals("Prize Bonds as a Gift")) {
                webUtil.getWebDriverWait().until(ExpectedConditions.visibilityOf(webElements.get(i)));
                WebElement element = webElements.get(i).findElement(fldAmount);
                webUtil.scroll(element);
                element.clear();
                element.sendKeys(amounts.get(i));
                webUtil.getWebDriverWait().until(ExpectedConditions.attributeToBe(element, "value", amounts.get(i)));
            }else{
                int clik;
                int j=4;
               clik = Integer.parseInt(amounts.get(i));
                System.out.println(clik);
                while (j < clik) {

                    webUtil.clickLog(increment, "Increment PB gift");
                    j++;
                }
                Thread.sleep(1000);
            }


            validateProductAndAmount(products.get(i), amounts.get(i), i);
        }
        return this;
    }

    private void validateProductAndAmount(String product, String amount, int i) {
        List<WebElement> prdctRow = webUtil.getElements(yourOrderAmtList);

        amount = product.contains("Prize Bonds") ? "€" + String.format("%,.2f", Double.parseDouble(amount) * 6.25) : amount;

        String[] details = productMap.get(product);

        if (details != null) {
            if (details[0] != null) webUtil.verifyText(prdctRow, issueText, "", details[0], i, true);
            webUtil.verifyText(prdctRow, productText, "", product, i, true);
            if (product.contains("Prize Bonds"))
                webUtil.verifyText(prdctRow, pbAmountText, "", amount, i, Boolean.parseBoolean(details[1]));
            else
                webUtil.verifyText(prdctRow, amountText, "value", amount, i, Boolean.parseBoolean(details[1]));
        }
    }

    public Review clickContReview() throws InterruptedException {

        webUtil.clickLog(btnContinueToReview, "Continue to review button on your order page");
        return new Review(driver);

    }

    public YourOrder verifyProducts() {
        if (webUtil.isElementVisible(ftProduct, 20)) {
            String txtHdrYourOrder = "You have no products selected, please add them below";
            webUtil.gettextlog(hdrYourOrder, String::equals, txtHdrYourOrder, "Product Page Header");
            productList = webUtil.getDriver().findElements(ftProduct);
        } else
            throw new ExceptionUtils("Product not displayed");

        IntStream.range(0, lblProductList.size()).forEach(index -> {
            String expectedValue = lblProductList.get(index);
            Consumer<Integer> consumer = consumerMap.get(expectedValue);
            if (consumer == null) throw new IllegalArgumentException("Product is not available");

            consumer.accept(index);
        });
        return this;
    }

    private void verifyProduct(Integer index) {
        String expectedValue = lblProductList.get(index);
        webUtil.verifyText(productList, productName, "", expectedValue, index, true);
    }

    private void verifyNationalSolidarityBonds(Integer index) {
        String expectedNSBIssue = "10 year Issue 9";
        verifyProduct(index);
        verifyBondIssue(index, expectedNSBIssue);
    }

    private void verifySavingsBonds(Integer index) {
        String expectedSBIssue = "3 year Issue 18";
        verifyProduct(index);
        verifyBondIssue(index, expectedSBIssue);
    }

    private void verifySavingsCertificates(Integer index) {

        String expectedSCIssue = "5 year Issue 25";
        verifyProduct(index);
        verifyBondIssue(index, expectedSCIssue);
    }

    private void verifyBondIssue(Integer index, String expectedIssue) {
        String bondIssueActual = productList.get(index).findElement(txtIssue).getText();
        if (bondIssueActual != null) {
            webUtil.verifyText(productList, txtIssue, "", expectedIssue, index, true);
        }
    }


}

