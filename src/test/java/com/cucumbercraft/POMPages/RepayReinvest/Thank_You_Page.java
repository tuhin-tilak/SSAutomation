package com.cucumbercraft.POMPages.RepayReinvest;

import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.FrameworkLogger;
import com.cucumbercraft.framework.LogType;
import com.cucumbercraft.framework.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.cucumbercraft.stepdefinitions.ReinvestSteps.getdata;

public class Thank_You_Page {

    WebDriver driver;
    WebDriverUtil webUtil;

    public Thank_You_Page(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    private final By header = By.xpath("//h1[@class='m01-banner--title ']");
    private final String headerContent = "Thank you for your request";
    private String paragraph = "//div[@class='m38-cash-in-reinvest-journey__confirmation-info']/div[%s]";
    private final String paraContent1 = "Your transaction request has been successfully submitted. We will notify you with updates on your transaction request.";
    private final String paraContent2 = "Your holding will be temporarily unavailable while the request is being processed.";
    private final String paraContent3 = "It may take 7-10 days for your transaction to appear in your online account.";
    private final String paraContent4 = "Please note, it is not necessary to return your original investment document associated with the %s.";
    private final By contact = By.xpath("//div[@class='m38-cash-in-reinvest-journey__confirmation-info']//p");
    private final String contactContent = "If you have any questions please contact us at 0818 20 50 60 / 01 705 7200.";
    private final By backButton = By.xpath("//*[text()='Back to Your Savings']");


    public void clickBackBtn() throws Exception {


        webUtil.click(backButton);


    }


    /**
     * PB Thank You Page for reinvest
     *
     * @throws Exception
     */
    public void verifyReinvestPBThankYouPage() throws Exception {
        verifyHeaderThankYouPage();
        if (webUtil.isElementDisplayed(By.xpath(String.format(paragraph, "1")), 20)) {
            if (!webUtil.getText(By.xpath(String.format(paragraph, "1"))).contentEquals("Your transaction request has been successfully submitted. We will notify you with updates on your transaction request.")) {
                throw new ExceptionUtils("Paragraph 1 content not verified!");
            }
        } else {
            throw new ExceptionUtils("Paragraph 1 content x-path not found!");
        }
        if (webUtil.isElementDisplayed(By.xpath(String.format(paragraph, "2")), 20)) {
            if (!webUtil.getText(By.xpath(String.format(paragraph, "2"))).equals("Please note that it may take 7-10 days for your transaction to appear in your online account.")) {
                throw new ExceptionUtils("Paragraph 2 content not verified!");
            }
        } else {
            throw new ExceptionUtils("Paragraph 2 content x-path not found!");
        }


        webUtil.gettextlog(contact, String::equals, contactContent);
    }


    /**
     * Cashin PB Thank You Page
     *
     * @throws Exception
     */
    public void verifyCashinPBThankYouPage() throws Exception {
        verifyHeaderThankYouPage();
        if (webUtil.isElementDisplayed(By.xpath(String.format(paragraph, "1")), 20)) {
            if (!webUtil.getText(By.xpath(String.format(paragraph, "1"))).contentEquals("Your transaction request has been successfully submitted. We will notify you with updates on your transaction request.")) {
                throw new ExceptionUtils("Paragraph 1 content not verified!");
            }
        } else {
            throw new ExceptionUtils("Paragraph 1 content x-path not found!");
        }
        if (webUtil.isElementDisplayed(By.xpath(String.format(paragraph, "2")), 20)) {
            if (!webUtil.getText(By.xpath(String.format(paragraph, "2"))).equals("Please note that it may take 7-10 days for your transaction to appear in your online account.")) {
                throw new ExceptionUtils("Paragraph 2 content not verified!");
            }
        } else {
            throw new ExceptionUtils("Paragraph 2 content x-path not found!");
        }

        paragraph = "//div[@class='m38-cash-in-reinvest-journey__confirmation-info']/p[%s]";

        if (webUtil.isElementDisplayed(By.xpath(String.format(paragraph, "1")), 20)) {
            if (!webUtil.getText(By.xpath(String.format(paragraph, "1"))).equals("If you have any queries please contact us at 0818 20 50 60 / 01 705 7200.")) {
                throw new ExceptionUtils("Paragraph 3 content not verified!");
            }
        } else {
            throw new ExceptionUtils("Paragraph 3 content x-path not found!");
        }

        if (webUtil.isElementDisplayed(By.xpath(String.format(paragraph, "2")), 20)) {
            if (!webUtil.getText(By.xpath(String.format(paragraph, "2"))).equals("Please note that it is not necessary to return the Bond Certificates associated with this repayment request. Once the repayment is processed the Bond Certificates will no longer be valid and we would advise that you securely destroy them.")) {
                throw new ExceptionUtils("Paragraph 4 content not verified!");
            }
        } else {
            throw new ExceptionUtils("Paragraph 4 content x-path not found!");
        }

    }

    /**
     * Thank You page for FT product Cashin and Reinvest both
     *
     * @throws Exception
     */
    public void verifyThankYouPage() throws Exception {
        verifyHeaderThankYouPage();
        webUtil.gettextlog(By.xpath(String.format(paragraph, "1")), String::equals, paraContent1);
        webUtil.gettextlog(By.xpath(String.format(paragraph, "2")), String::equals, paraContent2);
        webUtil.gettextlog(By.xpath(String.format(paragraph, "3")), String::equals, paraContent3);
        if (getdata.get("MethodType").contentEquals("Reinvest") || getdata.get("MethodType").contentEquals("Reinvest-Repay")) {
            webUtil.gettextlog(By.xpath(String.format(paragraph, "4")), String::equals, String.format(paraContent4, "reinvestment"));


        } else if (getdata.get("MethodType").contentEquals("null") || getdata.get("MethodType").contentEquals("Cash-In")) {
            webUtil.gettextlog(By.xpath(String.format(paragraph, "4")), String::equals, String.format(paraContent4, "repayment"));


        }
        webUtil.gettextlog(contact, String::equals, contactContent);

    }


    public void verifyHeaderThankYouPage() throws Exception {
//        webUtil.gettextlog(header,String::equals,headerContent);
        boolean thankYouPageDisplayed = webUtil.getWebDriverWait().until(ExpectedConditions.urlContains("thank-you"));
        if (thankYouPageDisplayed) {
            FrameworkLogger.log(LogType.EXTENTANDCONSOLEPASS, "Thank you page is displayed");
        } else {
            FrameworkLogger.log(LogType.EXTENTANDCONSOLEFAIL, "Thank you page not displayed");
        }
    }


}
