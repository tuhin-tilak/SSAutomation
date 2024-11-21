package com.cucumbercraft.POMPages.BuyNow;

import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.FrameworkLogger;
import com.cucumbercraft.framework.LogType;
import com.cucumbercraft.framework.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AnpostPayment {


    WebDriver driver;
    WebDriverUtil webUtil;
    BuyNowContext context = BuyNowContext.getInstance();
    private final By modalErrorLabel = By.id("modalErrorLabel");
    private final By btnClose = By.xpath("//button");
    private final By modalProcessingLabel = By.xpath("//*[contains(text(),'Processing card.')]");
    private final By referenceNum = By.cssSelector("div[class='d-block'] span[class='text-muted float-right d-inline-block align-middle fs-1-rem font-poppins']");
    private final By cardHolderName = By.xpath("//input[@id='CardHolderName']");
    private final By cardNumber = By.xpath("//input[@id='_card_number']");
    private final By cardxpiry = By.xpath("//input[@id='_expiry']");
    private final By cardCvv = By.xpath("//input[@id='_cvv']");
    private final By billing_FirstName = By.xpath("//input[@id='billingDetails_FirstName']");
    private final By billing_LastName = By.xpath("//input[@id='billingDetails_LastName']");
    private final By billing_Address = By.xpath("//input[@id='billingDetails_AddressLine1']");
    private final By billing_City = By.xpath("//input[@id='billingDetails_Town']");
    private final By billing_Country = By.xpath("//input[@role='combobox']");
    private final By billing_Email = By.xpath("//input[@id='billingDetails_Email']");
    private final By checkbox = By.xpath("//span[@id='checbox-checkmak']");
    private final By btnPay = By.xpath("//input[@id='btnPayNow']");
    public String orderRefNo;


    public AnpostPayment(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    private void fillCardHolderName(String predefinedName) {

        webUtil.sendKeys(cardHolderName, predefinedName);

    }

    private void fillCardNumber(String cardNumberValue) {
        webUtil.sendKeys(cardNumber, cardNumberValue);
    }

    private void fillCardExpiry(String expiryDate) {
        webUtil.sendKeys(cardxpiry, expiryDate);
    }

    private void fillCardCvv(String cvv) {
        webUtil.sendKeys(cardCvv, cvv);
    }

    private void fillBillingFirstName(String name) {
        webUtil.sendKeys(billing_FirstName, name);
    }

    private void fillBillingLastName(String lastName) {
        webUtil.sendKeys(billing_LastName, lastName);
    }

    private void fillBillingAddress(String address) {
        webUtil.sendKeys(billing_Address, address);
    }

    public AnpostPayment fillBillingCity(String city) {
        webUtil.sendKeys(billing_City, city);
        return this;
    }

    private void selectBillingCountry(String county) {
        String country = webUtil.getText(billing_Country);
        if (!country.equalsIgnoreCase("Ireland")) {

        }
    }

    private void fillBillingEmail(String email) {
        webUtil.sendKeys(billing_Email, email);
    }

    public AnpostPayment clickCheckbox() {
        webUtil.click(checkbox);
        return this;
    }

    public AnpostPayment clickPayButton() {
        webUtil.click(btnPay);
        return this;
    }


    public AnpostPayment waitForPaymentPage() {
        boolean flag = webUtil.getWebDriverWait().until(ExpectedConditions.urlToBe("https://paymentspaas-qa.dev-anpost.com/Payments/Payment"));
        if (flag) {
            FrameworkLogger.log(LogType.EXTENTANDCONSOLEPASS, "Payment page displayed");
            orderRefNo = webUtil.getText(referenceNum);
            context.setOrderRefNo(orderRefNo);
        } else {
            throw new ExceptionUtils("Payment page is not displayed");
        }
        return this;
    }

    public AnpostPayment enterCardDetails(String cardNumber, String expiry, String cvv) {
        fillCardNumber(cardNumber);
        fillCardCvv(cvv);
        fillCardExpiry(expiry);

        return this;
    }

    public ThankYou retryPaymentIfFailed(String cardNo, String expiry, String cvv) {
        if (!webUtil.isElementVisible(modalProcessingLabel, 7)) {
            webUtil.click(btnPay);
            if (webUtil.isElementVisible(modalErrorLabel, 5) && webUtil.getText(modalErrorLabel).equals("Error processing the transaction.")) {
                webUtil.click(btnClose);
                enterCardDetails(cardNo, expiry, cvv).clickCheckbox().clickPayButton();


            }
        }

        if (webUtil.isElementVisible(modalErrorLabel, 5) && webUtil.getText(modalErrorLabel).equals("Error processing the transaction.")) {
            webUtil.click(btnClose);
            enterCardDetails(cardNo, expiry, cvv).clickCheckbox().clickPayButton();

        }
        return new ThankYou(driver);
    }


}
