package com.cucumbercraft.framework;

import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * A generic WebDriver manager, which handles multiple instances of WebDriver.
 *
 * @author Cognizant
 */
public class DriverManager {

    /*
     * Used for Multithreading of WebDriver Object
     */
    @SuppressWarnings("rawtypes")
    private static final ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<AppiumDriver>();
    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    private static final ThreadLocal<SeleniumTestParameters> testParameters = new ThreadLocal<SeleniumTestParameters>();

    static Logger log;

    static {
        log = LogManager.getLogger(DriverManager.class);
    }

    /**
     * Function to return the object for WebDriver {@link WebDriver} object
     *
     * @return Instance of the {@link WebDriver} object
     */
    public static WebDriver getWebDriver() {
        if (webDriver.get() == null) {
            // this is need when running tests from IDE
            log.info("Thread has no WedDriver, creating new one");
            setWebDriver(DriverFactory.createWebDriverInstance(getTestParameters()));
//            setWebDriver(webDriver.get());
        }
        log.debug("Getting instance of remote driver" + webDriver.get().getClass());
        return webDriver.get();
    }

    /**
     * Function to return the object for AppiumDriver {@link AppiumDriver}
     * object
     *
     * @return Instance of the {@link AppiumDriver} object
     */
    @SuppressWarnings("rawtypes")
    public static AppiumDriver getAppiumDriver() {
        if (appiumDriver.get() == null) {
            // this is need when running tests from IDE
            log.info("Thread has no Appium driver, creating new one");
            setAppiumDriver(DriverFactory.createAppiumInstance(null));
        }
//        log.debug("Getting instance of remote driver" + appiumDriver.get().getClass());
        return appiumDriver.get();
    }

    /**
     * Function to set the AppiumDriver Object{@link AppiumDriver} object
     *
     * @param driver
     */
    @SuppressWarnings("rawtypes")
    public static void setAppiumDriver(AppiumDriver driver) {
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        DriverManager.appiumDriver.set(driver);
    }

    /**
     * Function to set the WebDriver Object{@link WebDriver} object
     *
     * @param driver
     */
    public static void setWebDriver(WebDriver driver) {
        DriverManager.webDriver.set(driver);
    }

    /**
     * Function to set the SeleniumTestParameters
     * Object{@link SeleniumTestParameters} object
     *
     * @param testParameters
     */
    public static void setTestParameters(SeleniumTestParameters testParameters) {
        DriverManager.testParameters.set(testParameters);
    }

    /**
     * Function to return the object for SeleniumTestParameters
     * {@link SeleniumTestParameters} object
     *
     * @return Instance of the {@link SeleniumTestParameters} object
     */
    public static SeleniumTestParameters getTestParameters() {
        return testParameters.get();
    }
}