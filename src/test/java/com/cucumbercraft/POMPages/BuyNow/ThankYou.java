package com.cucumbercraft.POMPages.BuyNow;

import com.cucumbercraft.Models.PurchaseModel;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ThankYou {

    WebDriver driver;
    WebDriverUtil webUtil;
    Review review;
    AnpostPayment payment;
    BuyNowContext context = BuyNowContext.getInstance();
    private final By tyheader = By.xpath("//h1[contains(text(),'Thank you')]");
    private final By orderRefnum = By.xpath("//*[contains(text(),'Order reference number')]");
    private final By tyfirstpara = By.xpath("//div[contains(@class,'thank-you--content')]//p[2]");
    private final By ty2nd = By.xpath("//div[contains(@class,'thank-you--content')]//p[3]");
    private final By pbtyfirstContent = By.xpath("//div[contains(@class,'cash-in-reinvest-journey__confirmation-info')]");
    private final By pbtyfirstPara = By.xpath("//div[contains(@class,'thank-you--content')]//p[2]");
    private final By pbtysecondPara = By.xpath("//div[contains(@class,'thank-you--content')]//p[3]");
    private final By pbtythirdPara = By.xpath("//div[contains(@class,'thank-you--content')]//p[4]");
    private final By pbtyfourthPara = By.xpath("//div[contains(@class,'thank-you--content')]//p[5]");

    public ThankYou(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);

        review = new Review(driver);
        payment = new AnpostPayment(driver);
    }

    public void validateTyPage(PurchaseModel purchaseModel) {
        if (webUtil.isElementVisible(tyheader, 25)) {
            webUtil.gettextlog(tyheader, String::equals, "Thank you for your purchase");
            if (purchaseModel.getProduct().indexOf("Prize Bonds as a Gift") == 0) {
                String firstcontent = "Please note that gift purchases will not appear in your Ireland State Savings Online.\n" +
                        "If the gift recipient is registered for Ireland State Savings Online, gift purchases will appear in their holdings online in the next 7 to 10 working days";
                if (!purchaseModel.getJourney().contains("Guest")) {
                    webUtil.gettextlog(pbtyfirstContent, String::equals, firstcontent);
                }
                webUtil.gettextlog(orderRefnum, String::equals, "Order reference number: " + context.getOrderRefNo());
                webUtil.gettextlog(pbtyfirstPara, String::equals, "Please retain the reference number (save or print off this page) for all future correspondence.");
                webUtil.gettextlog(pbtysecondPara, String::equals, "Once all your details have been verified, the Ireland State Savings product(s) will be sent by post within 7 to 10 working days.");
                webUtil.gettextlog(pbtythirdPara, String::equals, "If you have any queries, please contact us at 0818 20 50 60 / 01 705 7200 quoting your purchase reference number.");
                webUtil.gettextlog(pbtyfourthPara, String::equals, "A copy of your purchase receipt has been sent to the e-mail address provided.");
            } else {
                if (purchaseModel.getJourney().contains("Guest")) {
                    webUtil.gettextlog(orderRefnum, String::equals, "Order reference number: " + context.getOrderRefNo());
                    webUtil.gettextlog(pbtyfirstPara, String::equals, "Please retain the reference number (save or print off this page) for all future correspondence.");
                    webUtil.gettextlog(pbtysecondPara, String::equals, "Once all your details have been verified, the Ireland State Savings product(s) will be sent by post within 7 to 10 working days.");
                    webUtil.gettextlog(pbtythirdPara, String::equals, "If you have any queries, please contact us at 0818 20 50 60 / 01 705 7200 quoting your purchase reference number.");
                    webUtil.gettextlog(pbtyfourthPara, String::equals, "A copy of your purchase receipt has been sent to the e-mail address provided.");
                } else if (purchaseModel.getJourney().contains("SignIn")) {
                    webUtil.gettextlog(orderRefnum, String::equals, "Order reference number: " + context.getOrderRefNo());
                    webUtil.gettextlog(tyfirstpara, String::equals, "Thank you for your purchase. A confirmation email has been sent to your registered email address.");
                    webUtil.gettextlog(ty2nd, String::equals, "Please note that it will take 7-10 working days for your purchase to appear in your Ireland State Savings Online holdings.");


                }
            }

        } else {
            System.err.println("Thank You page not displayed");
            throw new ExceptionUtils("Thank You page not displayed");
        }
    }

}
