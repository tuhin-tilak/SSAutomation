package com.cucumbercraft.POMPages;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.framework.APIReusuableLibrary;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.Util;
import com.cucumbercraft.framework.WebDriverUtil;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Workflow {

    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private final By adminFormsbutton = By.xpath("//span[contains(text(),'Administration Forms')]");
    private final By setPage = By.xpath("//input[@title=\"Go to page\"]");
    private final By tableElements = By.xpath("//table[@id='ctl00_phMainContent_AdminFormListPart_AdminFormListPart_Grid_ctl00']/tbody/tr");
    private final By holdingID = By.xpath("//input[@id='AccountNumber_Editor']");
    private final By search = By.id("buttonSearch");
    private final By resultFirstrow = By.xpath("//table[@id='gridResults_Editor_ctl00']//tr[not(contains(@style,'none'))]");
    private final By tableheader = By.xpath("//table[@id='gridResults_Editor_ctl00_Header']/descendant::th");
    private final String resultrow = "//table[@id='gridResults_Editor_ctl00']//tr[%s]/td";
    private String index;
    private final String clickrow = "//table[@id='gridResults_Editor_ctl00']//tr[%s]";
    private final By confirmClose = By.xpath("//input[@id='btnConfirm']");
    private final By notes = By.cssSelector("#Action_Notes_Editor");


    public Workflow(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    public void launchWrkflow() {
        driver.get("http://wfdgpo02-v/Metastorm/Default.aspx");


        Assertions.assertThat(driver.getTitle())
                .as("Workflow Title Verification")
                .isNotNull()
                .isNotEmpty()
                .isEqualTo("OpenText MBPM");
        ExtentCucumberAdapter.getCurrentStep().pass("MetaStorm is Up and running");
    }

    @SneakyThrows
    public void getOption(String process, String action) {

        webUtil.clickLog(adminFormsbutton, "adminbutton");
        Thread.sleep(4000);
//        if (!pageno.equals("1")) {
//            webUtil.sendKeys(setPage, pageno);
//            driver.findElement(setPage).sendKeys(Keys.ENTER);
//            Thread.sleep(5000);
//        }

        boolean optionFound = false;
        while (!optionFound) {
            webUtil.waitUntilElementVisible(tableElements, 10);
            List<WebElement> element = driver.findElements(tableElements);
            for (WebElement webElement : element) {

                webUtil.scroll(webElement);
                boolean pr = webElement.findElement(By.xpath(".//td[1]")).getText().equalsIgnoreCase(process);
                boolean ac = webElement.findElement(By.xpath(".//td[2]")).getText().equalsIgnoreCase(action);
                if (pr && ac) {
                    webElement.click();
                    Thread.sleep(4000);
                    optionFound = true;
                    break;
                }
            }

            if (!optionFound) {
                WebElement nextButton = driver.findElement(By.xpath("//a[@title='Next Page']"));
                if (nextButton.isDisplayed()) {
                    nextButton.click();
                    Thread.sleep(3000);
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    wait.until(ExpectedConditions.presenceOfElementLocated(tableElements));
                } else {

                    System.out.println("Option not found on any page.");
                    break;
                }
            }
        }


    }

    public void validatePurchase() throws InterruptedException, IOException {
        String clickrow = "//table[@id='MainPanel_gridResults_Editor_ctl00']//tr[%s]";
        webUtil.switchToNewWindow();
        webUtil.switchToFrame();
        webUtil.click(By.xpath("//a[@id='MainPanel_CaseType_Editor_Arrow']"));
        webUtil.click(By.xpath("//li[text()='Purchases']"));
        webUtil.click(By.xpath("//span[@id='btnSearch']"));
        webUtil.isElementVisible(By.xpath("//table[@id='MainPanel_gridResults_Editor_ctl00']"), 15);
        Actions action = new Actions(driver);
        action.doubleClick(driver.findElement(By.xpath(String.format(clickrow, 2)))).build().perform();
        webUtil.waitFor(5000);
        webUtil.skip_switchToNewWindow(2);
        System.out.println(driver.getTitle());
        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(driver.getTitle());
    }

    public String caseDetails(String holdingId, String colname) throws InterruptedException, IOException {
        webUtil.switchToNewWindow();
        Thread.sleep(3000);
        webUtil.switchToFrame();
//        sendkeys(caseno,CaseNo);
        webUtil.sendKeys(holdingID, holdingId);
        webUtil.clickLog(search, "search button");
        Thread.sleep(15000);
        if (webUtil.isElementDisplayed(resultFirstrow, 20))
            index = String.valueOf(driver.findElements(resultFirstrow).size());
        else throw new ExceptionUtils("Object Not Found");
        System.out.println(index);
        List<String> lst = driver.findElements(tableheader)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
//       lst.forEach(System.out::println);
//        scrolltoview(By.xpath(String.format(resultrow, index)));
        List<String> row = driver.findElements(By.xpath(String.format(resultrow, index)))
                .stream()
                .peek(webUtil::scroll)
                .map(WebElement::getText)
                .collect(Collectors.toList());
        SortedMap<String, String> resultMap = IntStream.range(0, Math.min(lst.size(), row.size()))
                .boxed()
                .collect(Collectors.toMap(lst::get, row::get, (v1, v2) -> v2, TreeMap::new));
//        resultMap.forEach((key,value)->System.out.println(key+" => "+ value));

        if (!resultMap.get(colname).equalsIgnoreCase("REPAY09-Closed")) {
            webUtil.scrollToView(By.xpath(String.format(clickrow, index)));
            Actions action = new Actions(driver);
            action.doubleClick(driver.findElement(By.xpath(String.format(clickrow, index)))).build().perform();
            Thread.sleep(7000);
        } else {
//            throw new RuntimeException("Current Status of" + CaseNo + " is " + resultMap.get(colname));
            ExtentCucumberAdapter.getCurrentStep().fail("Current Status of" + holdingId + " is " + resultMap.get(colname), MediaEntityBuilder.createScreenCaptureFromBase64String(Util.takeScreenshotasFile(driver)).build());
            System.out.println("Current Status of" + holdingId + " is " + resultMap.get(colname));
        }

        return resultMap.get(colname);

    }

    public void caseDetailsclose(String holdingid, String colname) throws InterruptedException, IOException {
        if (holdingid.contains(",")) {
            for (String holdingId : holdingid.split(",")) {
                String currentstatus = caseDetails(holdingId, colname);
//                System.out.println(getColValue("Case Number"));
                if (currentstatus.equalsIgnoreCase("REPAY09-Closed")) {
//                    getDriver().close();
                    System.out.println(currentstatus);
                    webUtil.switchToNewWindow();
                    continue;
                }
//                caseDetails(holdingId,colname);
                webUtil.skip_switchToNewWindow(2);
                webUtil.clickLog(By.xpath("//a/span[text()='Close']"), "close");
                webUtil.skip_switchToNewWindow(3);
                webUtil.switchToFrame();
                webUtil.sendKeys(notes, "automation purpose");
                driver.switchTo().defaultContent();
                webUtil.clickLog(confirmClose, "Confirm button");
                webUtil.skip_switchToNewWindow(1);
                webUtil.switchToFrame();
                webUtil.clickLog(search, "search button");
                String status = getColValue(colname);
                if (status.equalsIgnoreCase("REPAY09-Closed")) {
                    System.out.println(holdingid + " is closed");
                    ExtentCucumberAdapter.getCurrentStep().pass(holdingid + " is closed");
                }
                webUtil.switchToNewWindow();
            }
        } else {
            caseDetails(holdingid, colname);
            webUtil.skip_switchToNewWindow(2);
            webUtil.clickLog(By.xpath("//a/span[text()='Close']"), "close");
            webUtil.skip_switchToNewWindow(3);
            webUtil.switchToFrame();
            webUtil.sendKeys(notes, "automation purpose");
            driver.switchTo().defaultContent();
            webUtil.clickLog(confirmClose, "Confirm button");
            webUtil.skip_switchToNewWindow(1);
            webUtil.switchToFrame();
            webUtil.clickLog(search, "search button");
            String status = getColValue(colname);
            if (status.equalsIgnoreCase("REPAY09-Closed")) {
                System.out.println(holdingid + " is closed");
                ExtentCucumberAdapter.getCurrentStep().pass(holdingid + " is closed");
            }
        }
//        caseNumberDetails(holdingid,colname);


    }

    public String getColValue(String colname) {
        index = String.valueOf(driver.findElements(resultFirstrow).size());
        List<String> lst = driver.findElements(tableheader)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
//       lst.forEach(System.out::println);
        List<String> row = driver.findElements(By.xpath(String.format(resultrow, index)))
                .stream()
                .peek(webUtil::scroll)
                .map(WebElement::getText)
                .collect(Collectors.toList());
        SortedMap<String, String> resultMap = IntStream.range(0, Math.min(lst.size(), row.size()))
                .boxed()
                .collect(Collectors.toMap(lst::get, row::get, (v1, v2) -> v2, TreeMap::new));
        return resultMap.get(colname);

    }


    public void validate_Repayment_Search(String holdingId) throws InterruptedException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        webUtil.switchToNewWindow();
        Thread.sleep(3000);
        webUtil.switchToFrame();
        webUtil.sendKeys(holdingID, holdingId);
        webUtil.clickLog(search, "search button");
        webUtil.scrollToView(By.xpath(String.format(clickrow, 1)));
        Actions action = new Actions(driver);
        action.doubleClick(driver.findElement(By.xpath(String.format(clickrow, 1)))).build().perform();
        wait.until(ExpectedConditions.numberOfWindowsToBe(3));
        webUtil.skip_switchToNewWindow(2);
        webUtil.switchToFrame();
        webUtil.isElementDisplayedLog(By.id("ruleBackgroundLineVerification1"), 5, "Case Details Page");
    }


    public static void main(String[] args) throws FileNotFoundException {
        APIReusuableLibrary api=new APIReusuableLibrary();
        
        System.out.print(api.getUserDetails("UATAUTO-WF-User39@mailinator.com"));
    }
}
