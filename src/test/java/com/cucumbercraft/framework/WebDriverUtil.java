package com.cucumbercraft.framework;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.appium.java_client.functions.ExpectedCondition;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Class containing useful WebDriver utility functions
 *
 * @author Cognizant
 */
public class WebDriverUtil {
    private final WebDriver driver;
    protected static Scenario currentScenario;
    static final Logger log = LogManager.getLogger(WebDriverUtil.class);

    /**
     * Constructor to initialize the {@link WebDriverUtil} object
     *
     * @param driver The {@link WebDriver} object
     */
    public WebDriverUtil(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Function to pause the execution for the specified time period
     *
     * @param milliSeconds The wait time in milliseconds
     */
    public void waitFor(long milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public WebDriverWait getWebDriverWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Function to wait until the specified element is located
     *
     * @param by               The {@link WebDriver} locator used to identify the element
     * @param timeOutInSeconds The wait timeout in seconds
     */
    public WebElement waitUntilElementLocated(By by, long timeOutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Function to wait until the specified element is visible
     *
     * @param by               The {@link WebDriver} locator used to identify the element
     * @param timeOutInSeconds The wait timeout in seconds
     */
    public WebElement waitUntilElementVisible(By by, long timeOutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds)).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * @param webElement
     * @param timeOutInSeconds
     * @return
     */
    public WebElement waitUntilElementVisible(WebElement webElement, long timeOutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds)).until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Function to wait until the specified element is enabled
     *
     * @param by               The {@link WebDriver} locator used to identify the element
     * @param timeOutInSeconds The wait timeout in seconds
     */
    public void waitUntilElementEnabled(By by, long timeOutInSeconds) {
        (new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds))).until((WebDriver d) -> ExpectedConditions.elementToBeClickable(by).apply(d));
    }


    /**
     * Function to wait until the specified element is disabled
     *
     * @param by               The {@link WebDriver} locator used to identify the element
     * @param timeOutInSeconds The wait timeout in seconds
     */
    public WebElement waitUntilElementToBeClickable(By by, long timeOutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds)).until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * Function to select the specified value from a listbox
     *
     * @param by     The {@link WebDriver} locator used to identify the listbox
     * @param action The value to be selected within the listbox
     */
    public void selectListItem(By by, Consumer<Select> action) {
        Select dropDownList = new Select(driver.findElement(by));
        action.accept(dropDownList);
    }

    /**
     * Function to verify whether the specified object exists within the current
     * page
     *
     * @param by The {@link WebDriver} locator used to identify the element
     * @return Boolean value indicating whether the specified object exists
     */
    public Boolean objectExists(By by) {
        return driver.findElements(by).size() != 0;
    }

    /**
     * Function to verify whether the specified text is present within the
     * current page
     *
     * @param textPattern The text to be verified
     * @return Boolean value indicating whether the specified test is present
     */
    public Boolean isTextPresent(String textPattern) {
        return driver.findElement(By.cssSelector("BODY")).getText()
                .matches(textPattern);
    }

    public void clickLog(By locator, String Log) {

        try {

            waitUntilElementToBeClickable(locator, 20).click();

            if (Log.length() > 0) {

                ExtentCucumberAdapter.getCurrentStep().pass("<font color='yellow'><b>" + Log + "</b></font> is clicked successfully :<font color='yellow'><b> PASS</b></font>");

            }
        } catch (TimeoutException e) {
            ExtentCucumberAdapter.addTestStepLog(" <p style='color:red;'> <b>" + Log + "</b> Object not Found:FAIL");
            org.testng.Assert.fail("Object not Found");
        }

    }

    public void isElementDisplayedLog(By locator, int timeout, String Log) {
        try {
            boolean elementPresent = true;
            int count = 0;
            waitUntilElementVisible(locator, timeout);

            if (Log.length() > 0) {
                ExtentCucumberAdapter.addTestStepLog("<b>" + Log + "</b>is displayed");
            }

            while (elementPresent) {
                try {
                    if (!driver.findElement(locator).isDisplayed()) {
                        count++;
                        if (count > timeout) {
                            elementPresent = false;
                            ExtentCucumberAdapter.addTestStepLog("<b>" + Log + "<p style='color:red;'>not found  Fail</p></b>");
                            org.testng.Assert.fail("Element not found");
                            break;
                        }
                    } else {
                        if (Log.length() > 0) {
                            ExtentCucumberAdapter.addTestStepLog("<b>" + Log + "</b>is displayed :<b> Pass </b>");
                        }
                        elementPresent = false;
                        break;
                    }
                } catch (Exception ex) {
//                    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                    count++;
                    if (count > timeout) {
                        elementPresent = false;
                        ExtentCucumberAdapter.addTestStepLog("<font color='red'><b>" + Log + "not found  Fail</b></font>");
                        org.testng.Assert.fail();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            ExtentCucumberAdapter.addTestStepLog("<b><p style='color:red;'> " + Log + " not found : Fail</p></b>");
            org.testng.Assert.fail();
        }
    }



    public void gettextlog(By locator, BiPredicate<String, String> comparisonMethod, String expectedResult) {
        try {
            String actualResult = waitUntilElementVisible(locator, 15).getText().trim();

            boolean condition = comparisonMethod.test(actualResult, expectedResult);
            String color = condition ? "yellow" : "red";
            String resultText = condition ? "PASS" : "FAIL";
            String message = condition ? String.format("The Text: <font color='%s'><b>%s</b></font> : contains the expected text : <font color='%s'><b>%s</b></font>", color, actualResult, color, resultText) :
                    String.format("The Text: <font color='%s'><b>%s</b></font> : does not match the expected text : <font color='%s'><b>%s</b></font> : <font color='%s'><b>%s</b></font>", color, actualResult, color, expectedResult, color, resultText);

            if (condition) {
                ExtentCucumberAdapter.getCurrentStep().pass(message);
                Allure.step(message, io.qameta.allure.model.Status.PASSED);
                System.out.println(">> Actual-" + actualResult + " >> Expected -" + expectedResult);
                log.info(">> Actual-" + actualResult + " >> Expected -" + expectedResult);


            } else {
                ExtentCucumberAdapter.getCurrentStep().fail(message);
                Allure.step(message, io.qameta.allure.model.Status.FAILED);
                System.err.println(">> Actual-" + actualResult + " >> Expected -" + expectedResult);
                log.error(">> Actual-" + actualResult + " >> Expected -" + expectedResult);

            }


        } catch (Exception ex) {
            ExtentCucumberAdapter.addTestStepLog("Exception Found </b>:Fail " + ex);
            org.testng.Assert.fail("Failed " + ex.getMessage());
        }
    }

    public void gettextlog(By locator, BiPredicate<String, String> comparisonMethod, String expectedResult, String strType) {


        String actualResult = "";
        try {
            actualResult = waitUntilElementVisible(locator, 15).getText().trim();
            boolean condition = comparisonMethod.test(actualResult, expectedResult);
            String color = condition ? "yellow" : "red";
            String resultText = condition ? "PASS" : "FAIL";
            String message = condition ? String.format("The Text: <font color='%s'><b>%s</b></font> : contains the expected text : <font color='%s'><b>%s</b></font>", color, actualResult, color, resultText) :
                    String.format("The Text: <font color='%s'><b>%s</b></font> : does not match the expected text : <font color='%s'><b>%s</b></font> : <font color='%s'><b>%s</b></font>", color, actualResult, color, expectedResult, color, resultText);

            if (condition) {
                ExtentCucumberAdapter.getCurrentStep().pass(message);
                Allure.step(message, io.qameta.allure.model.Status.PASSED);
                System.out.println(">> Actual-" + actualResult + " >> Expected -" + expectedResult);

            } else {
                ExtentCucumberAdapter.getCurrentStep().fail(message);
                Allure.step(message, io.qameta.allure.model.Status.FAILED);
                System.err.println(">> Actual-" + actualResult + " >> Expected -" + expectedResult);

            }


        } catch (Exception ex) {
            ExtentCucumberAdapter.getCurrentStep().fail("Exception  Found <b>:Fail " + ex + "</b>");
            System.err.println("Exception  Found <b>:Fail " + ex + "</b>");
            Assert.fail("Failed " + ex.getMessage());
        }
    }

    public void gettextByAttribute(By locator, String comparetype, String ExpectedResult, String Attribute) {

        String ActualResult = "";
        try {
            if (isElementDisplayed(locator, 5)) {
                ActualResult = driver.findElement(locator).getAttribute(Attribute);
            } else {
                ActualResult = "";
            }
            switch (comparetype) {
                case "equalsIgnoreCase":
                case "EqualsIgnoreCase":
                    if (ActualResult.equalsIgnoreCase(ExpectedResult)) {
                        System.out.println(">> Actual-" + ActualResult + " >> Expected -" + ExpectedResult);
                        ExtentCucumberAdapter.getCurrentStep().pass("The Attribute Text: <font color='yellow'><b>" + ActualResult + "</b></font> : contains the expected text : <font color='yellow'><b>PASS</b></font>");
                    } else {
                        System.err.println(">> Actual-" + ActualResult + " >> Expected -" + ExpectedResult);
                        ExtentCucumberAdapter.getCurrentStep().fail("The Attribute Text: <font color='red'><b>" + ActualResult + "</b></font> : does not match the expected text : <font color='red'><b>" + ExpectedResult + "</b></font>: <font color='red'><b>FAIL</b></font>");
                        Allure.step("The Text :" + ActualResult + ": contains the expected text :" + ExpectedResult, io.qameta.allure.model.Status.PASSED);
                    }


                    break;
                case "contains": {
                    if (ActualResult.contains(ExpectedResult)) {
                        ExtentCucumberAdapter.getCurrentStep().pass("The Text: <font color='yellow'><b>" + ActualResult + "</b></font> : contains the expected text : <font color='yellow'><b>PASS</b></font>");
                    } else {
//                        ExtentCucumberAdapter.addTestStepLog("<p style='color:red;'> <b> The Text :" + ActualResult + "</b> :  contains the not expected <b> "+ExpectedResult+" :FAIL </b>");
//                        System.err.println(">>"+ActualResult+" "+ExpectedResult);
                        //currentScenario.embed(Util.takeScreenshot(driver), "image/png");
                        Allure.step("The Text :" + ActualResult + ": contains the expected text :" + ExpectedResult, io.qameta.allure.model.Status.PASSED);

                    }
                    break;
                }
            }

        } catch (Exception ex) {
            ExtentCucumberAdapter.addTestStepLog("Exception  Found </b>:Fail " + ex);
//            currentScenario.attach(Util.takeScreenshot(driver), "image/png",currentScenario.getName());
            Assert.fail("Failed " + ex.getMessage());
        }
    }

    /**
     * @param ActualResult
     * @param comparetype
     * @param ExpectedResult
     * @return
     */
    public boolean CompareString(String ActualResult, String comparetype, String ExpectedResult) {
        try {
            switch (comparetype) {
                case "equalsIgnoreCase":
                case "EqualsIgnoreCase":
                    if (ActualResult.equalsIgnoreCase(ExpectedResult)) {
                        ExtentCucumberAdapter.getCurrentStep().pass("Actual: <font color='yellow'><b>" + ActualResult + "</b></font> : contains the expected text : <font color='yellow'><b>PASS</b></font>");
                        System.out.println(">> Actual-" + ActualResult + " >> Expected -" + ExpectedResult);
                        Allure.step("The Text :" + ActualResult + ": contains the expected text :" + ExpectedResult, io.qameta.allure.model.Status.PASSED);

                    } else {
                        ExtentCucumberAdapter.getCurrentStep().fail("Actual: <font color='red'><b>" + ActualResult + "</b></font> : does not match the expected text : <font color='red'><b>" + ExpectedResult + "</b></font>: <font color='red'><b>FAIL</b></font>");
                        System.err.println(">> Actual-" + ActualResult + " >> Expected -" + ExpectedResult);
                        Allure.step("The Text :" + ActualResult + ": contains the expected : " + ExpectedResult + " FAIL ", io.qameta.allure.model.Status.FAILED);

                        //currentScenario.embed(Util.takeScreenshot(driver), "image/png");
                    }
                    break;
                case "contains": {
                    if (ActualResult.contains(ExpectedResult)) {
                        ExtentCucumberAdapter.getCurrentStep().pass("Actual: <font color='yellow'><b>" + ActualResult + "</b></font> : contains the expected text : <font color='yellow'><b>PASS</b></font>");
                        System.out.println(">> Actual-" + ActualResult + " >> Expected -" + ExpectedResult);
                        Allure.step("The Text :" + ActualResult + ": contains the expected text :" + ExpectedResult, io.qameta.allure.model.Status.PASSED);

                    } else {
                        ExtentCucumberAdapter.getCurrentStep().fail("Actual: <font color='red'><b>" + ActualResult + "</b></font> : does not match the expected text : <font color='red'><b>" + ExpectedResult + "</b></font>: <font color='red'><b>FAIL</b></font>");
                        System.err.println(">> Actual-" + ActualResult + " >> Expected -" + ExpectedResult);
                        Allure.step("Actual :" + ActualResult + ": contains the expected : " + ExpectedResult + " FAIL ", io.qameta.allure.model.Status.FAILED);

//                        currentScenario.embed(Util.takeScreenshot(driver), "image/png");
                    }
                    break;
                }
            }

        } catch (Exception e) {
            //currentScenario.embed(Util.takeScreenshot(driver), "image/png");
            ExtentCucumberAdapter.getCurrentStep().fail("Exception  Found </b>:Fail " + e);
            Assert.fail(e.getMessage());
            return false;
        }
        return true;
    }


    public boolean CompareString(String actualResult, BiPredicate<String, String> comparisonMethod, String expectedResult) {
        try {

            boolean condition = comparisonMethod.test(actualResult, expectedResult);
            String color = condition ? "yellow" : "red";
            String resultText = condition ? "PASS" : "FAIL";
            String message = condition ? String.format("The Text: <font color='%s'><b>%s</b></font> : contains the expected text : <font color='%s'><b>%s</b></font>", color, actualResult, color, resultText) :
                    String.format("The Text: <font color='%s'><b>%s</b></font> : does not match the expected text : <font color='%s'><b>%s</b></font> : <font color='%s'><b>%s</b></font>", color, actualResult, color, expectedResult, color, resultText);

            if (condition) {
                ExtentCucumberAdapter.getCurrentStep().pass(message);
                Allure.step(message, io.qameta.allure.model.Status.PASSED);
                System.out.println(">> Actual-" + actualResult + " >> Expected -" + expectedResult);
                log.info(">> Actual-" + actualResult + " >> Expected -" + expectedResult);

            } else {
                ExtentCucumberAdapter.getCurrentStep().fail(message);
                Allure.step(message, io.qameta.allure.model.Status.FAILED);
                System.err.println(">> Actual-" + actualResult + " >> Expected -" + expectedResult);
                log.error(">> Actual-" + actualResult + " >> Expected -" + expectedResult);

            }


        } catch (Exception ex) {
            ExtentCucumberAdapter.addTestStepLog("Exception Found </b>:Fail " + ex);
            org.testng.Assert.fail("Failed " + ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Function to check if an alert is present on the current page
     *
     * @param timeOutInSeconds The number of seconds to wait while checking for the alert
     * @return Boolean value indicating whether an alert is present
     */
    public Boolean isAlertPresent(long timeOutInSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds))
                    .until((WebDriver d) -> ExpectedConditions.alertIsPresent().apply(d));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public boolean isElementVisible(By by, long timeUnitSecond) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeUnitSecond))
                    .until(driver1 -> ExpectedConditions.visibilityOf(driver1.findElement(by)).apply(driver1));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isElementVisible(WebElement element, long timeUnitSecond) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeUnitSecond))
                    .until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isAllElementVisible(By by, long timeUnitSecond) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeUnitSecond))
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Function to verify if element is displayed within specific time.
     */
    public boolean isElementDisplayed(By locator, int timeout) throws InterruptedException {
        boolean elementPresent = true;
        int count = 0;
//        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        while (elementPresent) {
            try {
                if (driver.findElement(locator).isDisplayed()) {
                    break;
                } else if (driver.findElement(locator).getLocation().x > 0 || driver.findElement(locator).getLocation().y > 0)
                    break;
                else {
                    if (count == timeout) {
                        elementPresent = false;
                        break;
                    }
                    Thread.sleep(1000);
                    count++;
                }
            } catch (Exception ex) {
                if (count == timeout) {
                    elementPresent = false;
                    break;
                }
                Thread.sleep(1000);
                count++;
            }
        }
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return elementPresent;
    }

    /**
     * Function to verify if element is displayed within specific time.
     */
    /**
     * Function to check element is clickable in given time.
     */
    public boolean isElementclickable(By locator, int timeout) throws InterruptedException {
        boolean isClickable = false;
        try {
            WebDriverWait wait = new WebDriverWait((driver), Duration.ofSeconds(timeout));
            wait.until((WebDriver d) -> ExpectedConditions.elementToBeClickable(locator).apply(d));
            isClickable = driver.findElement(locator).isDisplayed();

        } catch (StaleElementReferenceException exception) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until((WebDriver d) -> ExpectedConditions.elementToBeClickable(locator).apply(d));
            isClickable = driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            isClickable = false;
        }
        return isClickable;
    }

//	This function is to check if page is loaded or not


    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
        try {
            Thread.sleep(3000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(expectation);
            System.out.println("Waiting till page is loaded complete");
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

    /**
     * Function to enter text into the textbox
     */
    public void sendKeys(By locator, String valuetoType) {

        try {
            WebElement element = waitUntilElementVisible(locator, 10);
//            scroll(element);
            element.clear();
            element.sendKeys(valuetoType);
        } catch (StaleElementReferenceException e) {
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(valuetoType);
        } catch (TimeoutException e) {

            ExtentCucumberAdapter.getCurrentStep().fail("<font color='red'><b>Object not Found:FAIL</b></font>");
            throw new ExceptionUtils("Object not Found");
        }

    }

    /**
     * Function to click element
     */
    public void click(By locator) {

        try {
            waitUntilElementToBeClickable(locator, 10).click();
        } catch (ElementNotInteractableException e) {
            scrollToView(locator);
            driver.findElement(locator).click();
        } catch (StaleElementReferenceException e) {
            driver.findElement(locator).click();
        } catch (TimeoutException e) {
            ExtentCucumberAdapter.getCurrentStep().fail("<font color='red'><b>Object not Found:FAIL</b></font>");
            throw new ExceptionUtils("Object not Found");
        }

    }

    public void javascriptClick(By locator) {
//        WebElement element=waitUntilElementVisible(locator,7);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(locator));

    }

    public void clickLocator(By locator) throws Exception {
        try {
            if (isElementDisplayed(locator, 10))
                driver.findElement(locator).click();
            else
                throw new ExceptionUtils("Object not found");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Function to clear the existing text from textbox
     */

    public boolean clear(By locator) throws InterruptedException {
        boolean clear = false;
        try {
            if (isElementDisplayed(locator, 5)) {
                driver.findElement(locator).clear();
                clear = true;
            }
        } catch (Exception e) {
            if (isElementclickable(locator, 10)) {
                driver.findElement(locator).clear();
                clear = true;
            }
        }
        return clear;
    }

    /**
     * Function to switch to another frame.
     */
    public void switchToFrame(String frameId) {
        try {
            driver.switchTo().frame(frameId);
        } catch (NoSuchFrameException exp) {
            exp.printStackTrace();
        }
    }


    /**
     * Function to get text of an element
     */

    public String getText(By locator) {
        String str = "";
//        try {
        if (isElementVisible(locator, 10)) {
            str = driver.findElement(locator).getText().trim();
        } else {
            throw new NoSuchElementException("Object not found");
        }
//        } catch (NoSuchElementException exp) {
//            exp.printStackTrace();
//        }
        return str;
    }

    public void verifyText(List<WebElement> elementList, By xpath, String attributeName, String expectedValue, int i, boolean useGetText) {

        var element = waitUntilElementVisible(elementList.get(i).findElement(xpath), 10);

        String actualValue = useGetText ? element.getText() : element.getAttribute(attributeName);
        if (actualValue.equalsIgnoreCase(expectedValue)) {
            log.info("The Text :" + actualValue + ": contains the expected text : PASS ");
            Allure.step("The Text :" + actualValue + ": contains the expected text : PASS ", io.qameta.allure.model.Status.PASSED);
            ExtentCucumberAdapter.getCurrentStep().pass("Actual: <font color='yellow'><b>" + actualValue + "</b></font> : contains the expected text : <font color='yellow'><b>PASS</b></font>");
        } else {
            log.error("The Text :" + actualValue + ": doesnot match expected : " + expectedValue + " FAIL ");
            Allure.step("The Text :" + actualValue + ": contains the expected : " + expectedValue + " FAIL ", io.qameta.allure.model.Status.FAILED);
            ExtentCucumberAdapter.getCurrentStep().fail("Actual: <font color='red'><b>" + actualValue + "</b></font> : does not match the expected text : <font color='red'><b>" + expectedValue + "</b></font>: <font color='red'><b>FAIL</b></font>");
        }


    }


    public void verifyTextLam(List<WebElement> arg1, Integer arg2, String expectedValue, BiFunction<List<WebElement>, Integer, String> biFunction) {

        System.out.println("lm executing");
        String actualValue = biFunction.apply(arg1, arg2);


        Consumer<String> logAction = (actualValue.equalsIgnoreCase(expectedValue)) ?
                message -> {
                    log.info(message);
                    ExtentCucumberAdapter.getCurrentStep().log(Status.PASS, message);
                } :
                message -> {
                    log.error(message);
                    ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, message);
                };
        logAction.accept(actualValue.equalsIgnoreCase(expectedValue) ?
                "The Text :" + actualValue + ": contains the expected text : PASS" :
                "The Text :" + actualValue + ": contains the expected : " + expectedValue + " FAIL");


    }

    public void verifyTextAssert(List<WebElement> elementList, By xpath, String expectedValue, int i) {
        System.out.println("Verifying Assert");
        Assertions.assertThat(elementList)
                .as("Verify the list of WebElements is not empty")
                .isNotEmpty()
                .element(i)
                .extracting(element -> element.findElement(xpath).getText())
                .as("Verify the text of the element is not null and compare text")
                .isNotNull()
                .as("Perform actions based on the text")
                .satisfies(createPerformAction(
                        message -> {
                            ExtentCucumberAdapter.getCurrentStep().log(Status.PASS, message);
                            log.info(message);
                        },
                        message -> {
                            ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, message);
                            log.error(message);
                        },
                        expectedValue // Replace with your actual expected text
                ));
    }

    private static Consumer<String> createPerformAction(Consumer<String> passConsumer, Consumer<String> failConsumer, String expectedText) {

        Function<String, String> logMessageFunction = text -> {
            if (expectedText.equals(text)) {
                return "The Text: <font color='yellow'><b>" + text + "</b></font> : contains the expected text : PASS</font>";
            } else {
                return "The Text: <font color='red'><b>" + text + "</b></font> : does not match the expected text : <font color='red'><b>" + expectedText + "</b></font> FAIL";
            }
        };

        return text -> {
            Consumer<String> selectedConsumer = expectedText.equals(text) ? passConsumer : failConsumer;
            selectedConsumer.accept(logMessageFunction.apply(text));
        };
    }

    /**
     * Function to select radio button option
     */

    public boolean selectRadioButton(By locator) throws InterruptedException {
        if (isElementDisplayed(locator, 5)) {
            driver.findElement(locator).click();
            return true;
        } else {
            return false;
        }

    }


    /**
     * Function to select values from the dropdown list
     */

    public void selectDropDown(By locator, Consumer<Select> action) {
//        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        wait.until(d -> driver.findElement(locator).isDisplayed());
        scrollToView(locator);

        try {

            Select dropDown = new Select(waitUntilElementVisible(locator, 10));
            action.accept(dropDown);

        } catch (Exception exp) {
            Assert.fail(exp.getMessage());
        }
    }

    private void highlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
    }


    public void switchToNewWindow() {

        Set<String> winHandles = driver.getWindowHandles();
        getWebDriverWait().until(ExpectedConditions.numberOfWindowsToBe(2));
        String window = winHandles.stream().skip(1).findFirst().orElseThrow(() -> new RuntimeException("window not found"));
        driver.switchTo().window(window);


    }

    public void skip_switchToNewWindow(int i) {

        Set<String> winHandles = driver.getWindowHandles();
//        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(6));
        getWebDriverWait().until(ExpectedConditions.numberOfWindowsToBe(i + 1));
        String window = winHandles.stream().skip(i).findFirst().orElseThrow(() -> new RuntimeException("window not found"));
        driver.switchTo().window(window);


    }

//    public void enterKey() throws AWTException {
//        Robot r = new Robot();
//        r.keyPress(KeyEvent.VK_ENTER);
//    }

    public void switchToFrame() {
//		WebDriverWait wait=new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Constants.EXPLICITWAIT));
//		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("eFormContents")));

        if (driver.findElements(By.tagName("iframe")).size() == 1) {
            driver.switchTo().frame(0);
        } else if (driver.findElements(By.tagName("iframe")).size() == 2) {
            driver.switchTo().frame(1);
        }
    }

    /**
     * Function to scroll to given element
     */

    public void scrollToView(By locator) {
        try {
            if (isElementVisible(locator, 10)) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView();", driver.findElement(locator));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void scroll(WebElement locator) {
        try {
            if (isElementVisible(locator, 10)) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView();", locator);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to select Checkbox
     */

    public void selectCheckBox(By locator, String checkFlag) throws InterruptedException {
        if (isElementDisplayed(locator, 2)) {
            if ((checkFlag).equalsIgnoreCase("ON")) {
                if (!(driver.findElement(locator).isSelected())) {
                    driver.findElement(locator).click();
                }
            }
        }
    }


    /**
     * Function to set calender date
     */


    public void enterDateCalendar(String strName) throws InterruptedException {
        String[] dateArray = strName.split("\\/");
        click(By.xpath("//span[text()='2020']"));
        By Year = By.xpath("//*[@class='ng-star-inserted']/span[text()='" + dateArray[2] + "']");
        By Month = By.xpath("//*[@class='ng-star-inserted']/span[text()='" + dateArray[1] + "']");

        if (dateArray[0].charAt(0) == '0') {
            dateArray[0] = dateArray[0].substring(1, 2);
        }
        By Date = By.xpath("//*[@class='ng-star-inserted']/span[not(@class='is-other-month')][text()='" + dateArray[0] + "']");
        click(Year);
        click(Month);
        click(Date);
    }


    public String readValue(By locator) {
        String str = "";
        str = driver.findElement(locator).getAttribute("value");
        return str;
    }

    public boolean objIsDisabled(By locator) {
        return driver.findElement(locator).isEnabled();
    }


    public WebElement getobjectfromlist(By locator, int position) {
        WebElement value = (driver.findElements(locator)).get(position);
        return value;
    }

    public String[] dateSplit(String strName) throws InterruptedException {
        return strName.split("\\/");
    }

    public WebDriver getDriver() {
        return driver;
    }

    public List<WebElement> getElements(By by) {
        return getWebDriverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }


}

