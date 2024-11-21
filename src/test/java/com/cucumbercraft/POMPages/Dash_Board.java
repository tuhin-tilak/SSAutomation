package com.cucumbercraft.POMPages;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Dash_Board {

    private WebDriver driver = null;
    private final WebDriverUtil webUtil;

    public Dash_Board(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    private static final Logger log = LogManager.getLogger(Dash_Board.class);

    private final String navTabs = "//ul[@class='primary-nav__list']/li/a[@title=";
    private final String common = "//div[@class='m40-dashboard-cards--card card-";
    private final String navAction = "//div[@id='p_lt_WebPartZone3_zoneSecondaryHeader_SSSecondaryNav_authenticatedArea']/li/a";
    private final By buyForMyself = By.xpath("//a[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M40DashboardCards_lnkPrizeBondsForMyself']");
    private final By buyGift = By.xpath("//a[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M40DashboardCards_lnkPrizeBondsAsGift']");
    private static String productName;

    private final Map<String, Consumer<WebDriverUtil>> productMap = new HashMap<>();
    private Map<String, String> navMap;

    private final By allProductTile = By.xpath("//div[@class='m40-dashboard-cards']");
    private final By allProductTile1 = By.xpath("//div[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M40DashboardCards_holdingGroup']/div/following::a");
    private final By logo = By.xpath("//a[@class='gtm-nav']/img[1]");
    public static By downloadPdf = By.xpath("//*[@id='btnDownloadSummary']");
    private final By allHoldingAppear = By.xpath("//*[@id='requestToAddHolding']");
    private final String productXpath = "//h4[normalize-space(text())='%s']/../../..//a[normalize-space()='View & Manage']";
    public By profileAndSettingstab = By.xpath("//a[@title='Profile and Settings']");

    private final By productRoot = By.xpath("//div[contains(@class,'m40-dashboard-cards--card card-')]");
    private final By produtName = By.xpath("//div[contains(@class,'m40-dashboard-cards--card card-')]/descendant::h3");
    //div[contains(@class,'m40-dashboard-cards--card card-')]/descendant::div[@class="m40-dashboard-cards--value"]
    private final By productAmount = By.xpath("//*[contains(@class,'m40-dashboard-cards--value')]/span");

    By allHoldingTitle = By.xpath("//*[@class='medium-10 columns']/h4");
    By allHoldingDesc = By.xpath("//*[@class='medium-10 columns']/p");
    By allHoldingPBTitle = By.xpath("//*[@class='product-options-card--title']");
    By allHoldingPBdesc = By.xpath("//*[@for='ddlProductList']");

    By confirmBTN = By.xpath("//*[@id='btnDownloadForm']");
    By dropdown = By.xpath("//*[@id='ddlProductList']");
    By dropDownProd = By.xpath("//*[@id='ddlProductList']//option[3]");

    /**
     * Store all the products and help to click on the tile
     */
    private Consumer<WebDriverUtil> clickProductUtil(String product) {
        productMap.put("Prize Bond", util -> util.click(By.xpath(String.format(productXpath, "Prize Bonds"))));
        productMap.put("Saving Bond", util -> util.click(By.xpath(String.format(productXpath, "Savings Bonds"))));
        productMap.put("Savings Certificate", util -> util.click(By.xpath(String.format(productXpath, "Savings Certificates"))));
        productMap.put("NSB 4 Year", util -> util.click(By.xpath(String.format(productXpath, "4 Year National Solidarity Bonds"))));
        productMap.put("NSB 10 Year", util -> util.click(By.xpath(String.format(productXpath, "10 Year National Solidarity Bonds"))));
        productMap.put("Installment Savings", util -> util.click(By.xpath(String.format(productXpath, "Instalment Savings"))));
        productMap.put("Childcare Plus", util -> util.click(By.xpath(String.format(productXpath, "Childcare Plus"))));
        productMap.put("SSA", util -> util.click(By.xpath(String.format(productXpath, "State Savings Account"))));
        return productMap.get(product);
    }


    /**
     * Help to store the nav actions which is present on site
     *
     * @param action
     */

    private void navAction(String action) {
        navMap = new HashMap<>();
        navMap.put("Your Savings", "'Your Savings']");
        navMap.put("Profile and Settings", "'Profile and Settings']");
        navMap.put("Notifications", "'Notifications']");
        navMap.put("Help and Support", "'Help and Support']");
    }

    private void header(String action) {
        List<WebElement> list = driver.findElements(By.xpath(navAction));
        for (WebElement web : list) {
            if (web.getText().contains(action)) {
                web.click();
            }
        }
    }

    /**
     * Get the action according to the option selected
     *
     * @param action this defines the action
     * @return
     */
    private String getAction(String action) {
        return navMap.get(action);
    }


    public void buyMore(String product) throws InterruptedException {

        switch (product) {
            case "Prize Bonds for Me":
                webUtil.click(By.xpath("//a[@class='m40-dashboard-cards__link js-modalTrigger gtm-linkclick']"));
                webUtil.click(buyForMyself);
                break;
            case "Prize Bonds as a Gift":
                webUtil.click(By.xpath("//a[@class='m40-dashboard-cards__link js-modalTrigger gtm-linkclick']"));
                webUtil.click(buyGift);
                break;
            case "National Solidarity Bond 10Year":
//                webUtil.scrollToView(By.xpath("//div[@class='m40-dashboard-cards--card card-nationalbonds10']//a[@class='m40-dashboard-cards__link gtm-linkclick'][normalize-space()='Buy more']"));
                webUtil.click(By.xpath("//div[@class='m40-dashboard-cards--card card-nationalbonds10']//a[@class='m40-dashboard-cards__link gtm-linkclick'][normalize-space()='Buy more']"));
                break;
            case "Savings Bonds":
                webUtil.scrollToView(By.xpath("//div[@class='m40-dashboard-cards--card card-savingsbonds']//a[@class='m40-dashboard-cards__link gtm-linkclick'][normalize-space()='Buy more']"));
                webUtil.click(By.xpath("//div[@class='m40-dashboard-cards--card card-savingsbonds']//a[@class='m40-dashboard-cards__link gtm-linkclick'][normalize-space()='Buy more']"));
                break;
            case "Savings Certificates":
                webUtil.scrollToView(By.xpath("//div[@class='m40-dashboard-cards--card card-savingscert']//a[@class='m40-dashboard-cards__link gtm-linkclick'][normalize-space()='Buy more']"));
                webUtil.click(By.xpath("//div[@class='m40-dashboard-cards--card card-savingscert']//a[@class='m40-dashboard-cards__link gtm-linkclick'][normalize-space()='Buy more']"));
                break;

        }
    }


    /**
     * Click product
     *
     * @param product product which needs to be clicked
     * @throws Exception
     */
    public void clickProduct(String product) {
        productName = product;
        if (webUtil.isAllElementVisible(allProductTile, 10)) {
            clickProductUtil(product).accept(webUtil);
        } else
            throw new ExceptionUtils("view and mange x-path may have been changed for: " + product);
    }

    public void clickNavTab(String action) throws InterruptedException {
        webUtil.click(By.xpath(navTabs + getAction(action)));
        webUtil.waitForPageLoaded();
    }

    public void clickImage() throws InterruptedException {
        webUtil.click(logo);
        webUtil.waitForPageLoaded();
    }

    public void clickNavActions(String action) throws InterruptedException {
        header(action);
        webUtil.waitForPageLoaded();
    }

    public static String getProductName() {
        return productName;
    }

    public void allHolding() throws InterruptedException {
        webUtil.click(allHoldingAppear);
        webUtil.gettextlog(allHoldingTitle, String::equals, "Request to add holdings to Ireland State Savings Online.");
        webUtil.gettextlog(allHoldingDesc, String::equals, "Complete the information below and then download the form. The completed form should be signed and posted to Ireland State Savings, Fexco Centre, Killorglin, FREEPOST, Co. Kerry");
        webUtil.gettextlog(allHoldingPBTitle, String::equals, "Product details");
        webUtil.gettextlog(allHoldingPBdesc, String::equals, "Ireland State Savings Product");

        webUtil.click(dropdown);
        webUtil.waitUntilElementVisible(dropDownProd, 5);
        webUtil.click(dropDownProd);
        webUtil.click(confirmBTN);


    }

    public void validateWelcomeMsg() throws Exception {
        By greetings = By.xpath("//h4[contains(@class,'greeting')]");
        WebElement element = webUtil.waitUntilElementVisible(greetings, 10);
        if (element.isDisplayed()) {
            webUtil.scrollToView(greetings);
            System.out.println(webUtil.getText(greetings));
            Assertions.assertThat(webUtil.getText(greetings)).as("Welcome message").contains("Welcome");
            log.info("Login Successful");
            ExtentCucumberAdapter.addTestStepLog("<b><font color='yellow'>Dashboard page displayed</b></font>");
        } else {
            log.info("Login failed");
            ExtentCucumberAdapter.addTestStepLog("<b><font color='red'>Login Failed</b></font>");

        }
    }

    public int getProductSize() throws InterruptedException {
        int size;
        if (webUtil.isElementDisplayed(productRoot, 10)) {
            List<WebElement> ssProduct = driver.findElements(productRoot);
            size = ssProduct.size();
            System.out.println("No. of product size = " + size);
            return size;
        }

        return 0;

    }

    public String getProductNames() throws InterruptedException {
        if (webUtil.isElementDisplayed(produtName, 10)) {
            String ssName;
            List<WebElement> listName = driver.findElements(produtName);
            for (WebElement name : listName) {
                ssName = name.getText();
                return ssName;

            }
        }
        return null;
    }

    public String getAmount() {


        try {
            if (webUtil.isElementDisplayed(productAmount, 10)) {
                String amt;
                List<WebElement> amnt = driver.findElements(productAmount);
                for (WebElement ele : amnt) {
                    amt = ele.getText();
                    return amt;

                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


}
