package com.cucumbercraft.framework;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TestHarness {

    static Logger log = LogManager.getLogger(TestHarness.class);
    private final Properties properties = Settings.getInstance();

    /**
     * Constructor to initialize the {@link TestHarness} object
     */
    public TestHarness() {
        log.info("Starting test execution");
    }

    @SuppressWarnings("rawtypes")
    public void invokeDriver(Scenario scenario) {
        WebDriver driver;
        AppiumDriver appiumDriver;
        SeleniumTestParameters currentTestParameters = DriverManager.getTestParameters();
        log.info("Running the Scenario : " + scenario.getName() + " in " + currentTestParameters.getExecutionMode());

        switch (currentTestParameters.getExecutionMode()) {

            case API:
                break;
            case LOCAL:
//                driver = DriverFactory.createWebDriverInstance(currentTestParameters);
//                break;
            case GRID:
                driver = DriverFactory.createWebDriverInstance(currentTestParameters);
                DriverManager.setWebDriver(driver);
                break;

            case MOBILE:

                if (currentTestParameters.getMobileToolName().equals(ToolName.APPIUM)) {
                    appiumDriver = DriverFactory.createAppiumInstance(currentTestParameters);
                    DriverManager.setAppiumDriver(appiumDriver);
                } else if (currentTestParameters.getMobileToolName().equals(ToolName.REMOTEDRIVER)) {
                    driver = DriverFactory.createWebDriverInstance(currentTestParameters);
                    DriverManager.setWebDriver(driver);
                }
                break;

            case SAUCELABS:
            case FASTEST:
            case PERFECTO:
                if (currentTestParameters.getMobileToolName().equals(ToolName.APPIUM)) {
                    appiumDriver = DriverFactory.createAppiumInstance(currentTestParameters);
                    DriverManager.setAppiumDriver(appiumDriver);
                } else if (currentTestParameters.getMobileToolName().equals(ToolName.REMOTEDRIVER)) {
                    driver = DriverFactory.createWebDriverInstance(currentTestParameters);
                    DriverManager.setWebDriver(driver);
                }
                break;

            case TESTOBJECT:
                appiumDriver = DriverFactory.createAppiumInstance(currentTestParameters);
                DriverManager.setAppiumDriver(appiumDriver);
                break;

            default:
                break;
        }

    }

    public void downloadAddtionalReports(Scenario scenario) {
        SeleniumTestParameters testParameters = DriverManager.getTestParameters();
        if (Boolean.valueOf(properties.getProperty("PerfectoReport" + "Generation"))
                && testParameters.getExecutionMode().equals(ExecutionMode.PERFECTO)) {
            capturePerfectoReports(scenario, testParameters, testParameters.isMobileExecution());
        }

    }

    @SuppressWarnings("rawtypes")
    public void closeRespestiveDriver(Scenario scenario) {
        SeleniumTestParameters testParameters = DriverManager.getTestParameters();
        if (testParameters.isMobileExecution()) {
            AppiumDriver driver = DriverManager.getAppiumDriver();
            if (driver != null) {
                driver.quit();

            }
        } else if (!testParameters.isAPIExecution()) {
            WebDriver driver = DriverManager.getWebDriver();
            if (driver != null) {
                driver.quit();
            }
        }

    }

    /**
     * Embed a screenshot in test report if test is marked as failed And Update
     * Task in JIRA
     *
     * @param testParameters
     * @throws IOException
     */
    public void updateDefectInJira(Scenario scenario) throws IOException {

        SeleniumTestParameters testParameters = DriverManager.getTestParameters();

        if (Boolean.valueOf(properties.getProperty("TrackIssuesInJira"))) {
            if (scenario.isFailed()) {
                try {
                    if (testParameters.isMobileExecution()) {
                        byte[] screenshot = Util.takeScreenshot(DriverManager.getAppiumDriver());
                        scenario.attach(screenshot, "image/png", scenario.getName());
                    } else {
                        byte[] screenshot = Util.takeScreenshot(DriverManager.getWebDriver());
                        scenario.attach(screenshot, "image/png", scenario.getName());
                    }

                    File filePath = ((TakesScreenshot) DriverManager.getWebDriver()).getScreenshotAs(OutputType.FILE);
                    RestApiForJira.createLog(scenario.getName(), scenario.getName(), filePath.toString());

                } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                    somePlatformsDontSupportScreenshots.printStackTrace();
                    log.error(somePlatformsDontSupportScreenshots.getMessage());
                }
            }
        }
    }

    private String getFileName(Browser browser, String deviceName) {
        String fileName = "";
        if (browser == null) {
            fileName = deviceName;
        } else if (deviceName == null) {
            fileName = browser.toString();
        } else {
            fileName = "report";
        }
        return fileName;
    }

    @SuppressWarnings("rawtypes")
    private void capturePerfectoReports(Scenario scenario, SeleniumTestParameters testParameters,
                                        boolean isMobileExecution) {
        if (isMobileExecution) {
            try {
                AppiumDriver driver = DriverManager.getAppiumDriver();
                driver.close();
                String Udid;

                if (!(driver.getCapabilities().getCapability("model") == null)) {
                    Udid = driver.getCapabilities().getCapability("model").toString();
                } else {
                    Udid = driver.getCapabilities().getCapability("deviceName").toString();
                }
                PerfectoLabUtils.downloadReport(driver, "pdf",
                        Util.getResultsPath() + Util.getFileSeparator() + scenario.getName() + "_" + Udid);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("Problem while downloading Perfecto Report for " + scenario.getName());

            }
        } else {
            WebDriver driver = DriverManager.getWebDriver();
            driver.close();

            Map<String, Object> params = new HashMap<String, Object>();
            ((RemoteWebDriver) driver).executeScript("mobile:execution:close", params);
            params.clear();

            try {
                PerfectoLabUtils.downloadReport((RemoteWebDriver) driver, "pdf",
                        Util.getResultsPath() + Util.getFileSeparator() + scenario.getName() + "_"
                                + getFileName(testParameters.getBrowser(), testParameters.getDeviceName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
