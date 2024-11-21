package com.cucumbercraft.POMPages.RepayReinvest;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.Dash_Board;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

public class Joint {
    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private final By jointButton = By.xpath("//ul[@class='product-sidebar__options js-sidebar-options']/li[2]/a");
    private final By jointname = By.xpath("//div[@class='product-summary-links__item']");
    private final By jointHoldingsbutton = By.xpath("//div[@class='product-summary-info product-summary-info--card']");
    private final By cashinOnDetails = By.xpath("//button[@data-modal='joint-holdings-modal']");
    private final String notice = "This %s is reaching maturity or has matured. Action required.";
    private final String noticeNSB30days = "This %s is reaching maturity or has matured.The maturity value will transfer to your State Savings Account 30 days from maturity date. Action Required.";
    private final String noticePrior2014 = "This %s has matured. To cash in or reinvest please complete the product maturity form you received at account maturity and return it to State Savings.";
    private final String noticecashin = "This %s has been cashed in or reinvested.";
    private final String noticeSSA = "To Reinvest funds that may have transferred from a matured National Solidarity Bond, please use the maturity form you received in the post.";
    private final By modal = By.xpath("//div[@class=\"dashboard-modal__content js-modal dashboard-popup active\"]");
    private final By closemodal = By.xpath(".//button[@class='gtm-cta button button--secondary js-closeModal']");
    private final By textOnMoreDetails = By.xpath("//div[@class='product-summary-info--notification row']");
    private final By donor = By.xpath("//div[contains(@class,'product-summary-info product-summary-info--card')]");


    public Joint(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    private static final Logger log = LogManager.getLogger(Joint.class);
    private String button;
    private WebElement element;
    private HashMap<String, String> productMap;

    private String product() {
        productMap = new HashMap<>();
        productMap.put("Prize Bond", "Prize Bonds");
        productMap.put("Saving Bond", "Savings Bond");
        productMap.put("Savings Certificate", "Savings Certificate");
        productMap.put("NSB 4 Year", "National Solidarity Bond");
        productMap.put("NSB 10 Year", "National Solidarity Bond");
        productMap.put("Installment Savings", "Instalment Save");
        productMap.put("Childcare Plus", "Instalment Save");
        productMap.put("SSA", "State Savings Account");
        return productMap.get(Dash_Board.getProductName());
    }


    public void clickjoint() throws Exception {
        if (webUtil.isElementclickable(jointButton, 10)) {
            if(!webUtil.getDriver().findElement(jointButton).getAttribute("class").contains("active"))
                webUtil.click(jointButton);
            else
                log.info("Already on joint page");

        }
    }

    public void selectJointWithName(String name) throws Exception {
        WebElement avail;
        if (webUtil.isElementDisplayed(jointname, 10)) {
            List<WebElement> names = driver.findElements(jointname);
            avail = names.stream()
                    .parallel()
                    .filter(nam -> nam.getText().contains(name))
                    .findFirst()
                    .orElseThrow(() -> new ExceptionUtils("Joint Holding name not found"));
        } else
            throw new ExceptionUtils("Joint xpath not found");
        if (avail.isDisplayed()) {
            try {
                avail.click();
                //avail.findElement(By.xpath(".//button")).click();
            } catch (Exception e) {
                throw new ExceptionUtils("Joint holding not clicked " + e.getMessage());
            }
        }

    }

    public void selectHoldingWithID(String ID) throws Exception {
        if (webUtil.isElementDisplayed(jointHoldingsbutton, 10)) {
            List<WebElement> elements = driver.findElements(jointHoldingsbutton);
            element = elements.stream().parallel().filter(i -> i.getText().contains(ID)).findFirst().orElseThrow(() -> new ExceptionUtils("Holding ID not found"));

            String productname = element.findElement(By.xpath(".//h4")).getText().replaceAll("[^A-Za-z]", " ").trim();
            if (element.getText().contains("Reinvest / Cash In")) {
                String noticemsg = element.findElement(By.xpath(".//div[contains(@class,'large')]")).getText();
                button = element.findElement(By.xpath(".//button")).getText();
                System.out.println(productname);
                if (noticemsg.contentEquals(String.format(notice, productname))) {
                    log.info("notice verified");
                }
                log.info(button);
                element.findElement(By.xpath(".//button")).click();
                webUtil.waitForPageLoaded();

            } else if (element.getText().contains("Cash In")) {
                button = element.findElement(By.xpath(".//button")).getText();
                log.info(button);
                element.findElement(By.xpath(".//button")).click();
                webUtil.waitForPageLoaded();
            } else {
                String noticemsg = element.findElement(By.xpath(".//div[contains(@class,'large')]")).getText();
                if (!noticemsg.contentEquals(String.format(noticecashin, productname))) {
                    throw new ExceptionUtils("Account flushed msg not verified");
                }
                log.info("Account flushed notice verified");
            }
        } else {
            throw new ExceptionUtils("Joint holdings not found");
        }
    }

    public void jointModals() throws InterruptedException {
        String para = "To cash in or reinvest joint holdings, we require all account holders' signatures. Please download, complete and post the appropriate form to us.";
        String pararc = "To cash in or reinvest joint holdings we require both account holders' signatures. Please use the form you received in the post.";
        if (button.equals("Cash In")) {
            if (webUtil.isElementDisplayed(modal, 5)) {
                if (!webUtil.getDriver().findElement(modal).findElement(By.xpath(".//h2")).getText().contentEquals("Joint Holdings")) {
                    throw new ExceptionUtils("Modal header not verified");
                }
                if (!webUtil.getDriver().findElement(modal).findElement(By.xpath(".//p")).getText().contentEquals(para)) {
                    throw new ExceptionUtils("Modal para not verified");
                }
                webUtil.getDriver().findElement(modal).findElement(closemodal).click();
            }
        } else {
            if (!webUtil.getDriver().findElement(modal).findElement(By.xpath(".//h2")).getText().contentEquals("Joint Holdings")) {
                throw new ExceptionUtils("Modal header not verified");
            }
            if (!webUtil.getDriver().findElement(modal).findElement(By.xpath(".//p")).getText().contentEquals(pararc)) {
                throw new ExceptionUtils("Modal para not verified");
            }
            webUtil.getDriver().findElement(modal).findElement(closemodal).click();
        }

    }

    public void jointMoredetails() throws Exception {
        if (element.isDisplayed()) {
            element.findElement(By.xpath(".//a")).click();
        } else {
            throw new ExceptionUtils("Holding not found");
        }
        webUtil.waitForPageLoaded();
        if (webUtil.isElementDisplayed(cashinOnDetails, 10)) {
            if (webUtil.getText(cashinOnDetails).contentEquals(button)) {
                log.info("Cashin Link Matched on More Details Page");
            } else {
                throw new ExceptionUtils("Cashin link not present on more details page");
            }
        } else {
            try {
                webUtil.waitFor(5000);
                webUtil.scrollToView(By.xpath("//*[text()='Reinvest / Cash In']"));
                webUtil.getDriver().findElement(By.xpath("//*[text()='Reinvest / Cash In']"));
                log.info("Reinvest Cashin link present on details page");
            } catch (NoSuchElementException e) {
                log.info("Reinvest Cashin link not present on details page");
            }
        }

    }


    public void soleholding(String ID) throws Exception {
        String str = "//div[@class='product-summary-info product-summary-info--card']";
        String str1 = "//child::a";
        String str2 = "//strong";
        String message;
        List<WebElement> list;
        WebElement moreDetails;
        WebElement messageElement = null;
        if (!Dash_Board.getProductName().contains("Installment Savings") && !Dash_Board.getProductName().contains("Childcare Plus")) {
            list = webUtil.getElements(By.xpath(str));
            element = list
                    .stream()
                    .peek(webUtil::scroll)
                    .filter(i -> i.getText().contains(ID))
                    .findFirst()
                    .orElseThrow(() -> new ExceptionUtils("Holdings id not available"));
            moreDetails = element.findElement(By.xpath(".//a[@class='button button--secondary gtm-cta']"));
//            moreDetails = element.findElement(By.xpath(".//input[contains(@name,'SoleHoldingProducts')]"));
            webUtil.scroll(element);

        } else {
            list = driver.findElements(donor);
            element = list.stream().parallel().filter(i -> i.getText().contains(ID)).findFirst().orElseThrow(() -> new ExceptionUtils("Holdings id not available"));
            moreDetails = element.findElement(By.xpath(".//a[@class='button button--secondary button--alt gtm-cta']"));
            System.out.println(moreDetails.getText());
        }
        try {
            messageElement = element.findElement(By.xpath(".//div[contains(@class,'large')]"));
        } catch (Exception e) {
            log.info("message not dispalyed");
        }
        String productName = element.findElement(By.xpath(".//h4")).getText().replaceAll("[^A-Za-z]", " ").trim();
        webUtil.scroll(element);
        if (element.getText().contains("Reinvest / Cash In")) {
            try {
                assert messageElement != null;
                message = messageElement.getText();
                log.info(message);
//                webUtil.CompareString(message,String::equals,String.format(notice, productName));
//                moreDetails.click();
//                webUtil.gettextlog(textOnMoreDetails,String::equals,message + "\n" + "Reinvest / Cash In");
                if (message.contentEquals(String.format(notice, productName))) {
                    log.info("notice verified" +productName);
                    ExtentCucumberAdapter.addTestStepLog("Notice verified on" +productName+"summary page" );
                    String val = moreDetails.getAttribute("value");
                    if (val == null || val.equals("More Details")) {
                        moreDetails.click();

                    }
                    if (webUtil.getText(textOnMoreDetails).contains(message + "\n" + "Reinvest / Cash In")) {
                        log.info("Notice and link verified on more details page");
                        ExtentCucumberAdapter.addTestStepLog("Notice and link verified on more details page");
                    }else{
                        log.error("Notice and link not verified on more details page");
                        ExtentCucumberAdapter.addTestStepLog("Notice and link not verified on more details page");
                        throw new ExceptionUtils("Notice or link is not verified on more details page");
                    }
                } else {

                    if (message.contentEquals(String.format(noticeNSB30days, productName))) {
                        log.info("NSB 30 days notice verified");
                        if (moreDetails.getText().contains("More Details")) {
                            moreDetails.click();
                            webUtil.waitForPageLoaded();
                            webUtil.scrollToView(textOnMoreDetails);
                            System.out.println(webUtil.getText(textOnMoreDetails));
                            System.out.println(message.replaceFirst("\\.",". ") + "\n" + "Reinvest / Cash In");
//                            System.out.println(StringUtils.difference(webUtil.getText(textOnMoreDetails), message + "\n" + "Reinvest / Cash In"));
                            if (!webUtil.getText(textOnMoreDetails).contains(message.replaceFirst("\\.",". ") + "\n" + "Reinvest / Cash In")) {
                                log.error("Notice and link not verified on more details page");
                                ExtentCucumberAdapter.addTestStepLog("Notice and link not verified on more details page");
                                throw new ExceptionUtils("Notice or link is not verified on more details page");
                            }
                        }
                    }
                }

            } catch (Exception e) {
//                throw new ExceptionUtils("Reinvest/Cashin button amended in DOM");
                throw new ExceptionUtils(e.getMessage());
            }

        }
        else if (!element.getText().contains("Reinvest / Cash In") && !element.getText().contains("Cash In") && !element.getText().contains("Pending transaction")) {
            assert messageElement != null;
            message = messageElement.getText();
            System.out.println(product());
            System.out.println(StringUtils.difference(message, String.format(noticePrior2014, product())));
            if (message.contains(String.format(noticePrior2014, product()))) {
                log.info("Prior 2014 notice verified");
                    moreDetails.click();
                    webUtil.waitForPageLoaded();
                if (webUtil.getText(textOnMoreDetails).contains(message)) {
                    log.info("Notice and link verified on more details page");
                }
            } else if (message.contentEquals(String.format(noticecashin, productName))) {
                log.info("Product cashin and reinvested notice veified");
                ExtentCucumberAdapter.addTestStepLog(message+" "+":PASSED");
                if (moreDetails.getText().contains("More Details")) {
                    moreDetails.click();
                }
                try {
                    String moreDetailsText=webUtil.getText(textOnMoreDetails);
                    if(moreDetailsText.contains("Reinvest / Cash In"))
                        throw new ExceptionUtils("Reinvest/Cashin link  displayed on more details page");
                    else if(moreDetailsText.contains("Cash In"))
                        throw new ExceptionUtils("Cash In link displayed");
                    else
                        if(moreDetailsText.contains(String.format(noticecashin,product()))) {
                            log.info("Notice verified on more details");
                            ExtentCucumberAdapter.addTestStepLog(message + " verified on more details page" + " :PASSED");
                        }
                        else
                            System.out.println(StringUtils.difference(moreDetailsText, String.format(noticecashin, product())));
                    throw new ExceptionUtils(String.format(noticecashin,product())+" not verified on more details page");

                } catch (NoSuchElementException e) {
                    log.info("Message not present on more details page.");
                }

            } else if (message.contentEquals(String.format(notice, productName))) {
                log.info("NSB 30 days after maturity notice verified");
                if (moreDetails.getAttribute("value").contains("More Details")) {
                    moreDetails.click();
                    webUtil.waitForPageLoaded();
                }
                if (webUtil.getText(textOnMoreDetails).contains(message)) {
                    log.info("Notice and link verified on more details page for NSB 30 days after maturity");
                }
            }
        }
        else if (element.getText().contains("Cash In") && Dash_Board.getProductName().equals("SSA")) {
            assert messageElement != null;
            message = messageElement.getText();
            if (message.contains(noticeSSA)) {
                log.info("SSA notice verified");
                if (moreDetails.getText().contains("More Details")) {
                    moreDetails.click();
                    webUtil.waitForPageLoaded();
                }
                System.out.println(webUtil.getText(By.xpath("//*[normalize-space(text())='Cash In']")));
                if (webUtil.getText(By.xpath("//*[normalize-space(text())='Cash In']")).contains("Cash In")) {
                    log.info("Link verified for SSA on more details page");
                }
            }

        }
        else {
            if (element.getText().contains("Cash In")) {
                log.info("Cashin link verified on Holdings Page");
            }
            if (moreDetails.getText().contains("More Details") || moreDetails.getAttribute("value").contains("More Details")) {
                moreDetails.click();
                webUtil.waitForPageLoaded();
            }
            if (webUtil.getText(By.xpath("//div[@class='cash-in-link']")).contains("Cash In")) {
                log.info("Link verified for Cashin on more details page");
            }
        }

    }

}
