package com.cucumbercraft.framework;

import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

/**
 * DriverFactory which will create respective driver Object
 *
 * @author Cognizant
 */
public class DriverFactory {

    static Logger log = LogManager.getLogger(DriverFactory.class);
    private static final Properties mobileProperties = Settings.getInstance();

    /**
     * Function to return the object for AppiumDriver {@link AppiumDriver}
     * object
     *
     * @param testParameters
     * @return Instance of the {@link AppiumDriver} object
     */
    @SuppressWarnings("rawtypes")
    public static AppiumDriver createAppiumInstance(SeleniumTestParameters testParameters) {

        AppiumDriver driver = null;
        try {
            switch (testParameters.getExecutionMode()) {

                case MOBILE:

                    driver = AppiumDriverFactory.getAppiumDriver(testParameters);
                    break;

                case PERFECTO:

                    driver = PerfectoDriverFactory.getPerfectoAppiumDriver(testParameters);

                    break;

                case SAUCELABS:

                    driver = SauceLabsDriverFactory.getSauceAppiumDriver(testParameters);
                    break;

                case TESTOBJECT:

                    driver = SauceLabsDriverFactory.getTestObjectAppiumDriver(testParameters);
                    break;

                case FASTEST:

                    driver = FastestDriverFactory.getMintAppiumDriver(testParameters);
                    break;

                default:
                    throw new Exception("Unhandled Execution Mode!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
        }
        return driver;
    }

    /**
     * Function to return the object for WebDriver {@link WebDriver} object
     *
     * @param testParameters
     * @return Instance of the {@link WebDriver} object
     */
    public static WebDriver createWebDriverInstance(SeleniumTestParameters testParameters) {
        WebDriver driver = null;
        try {
            switch (testParameters.getExecutionMode()) {

                case LOCAL:
                    driver = WebDriverFactory.getWebDriver(testParameters.getBrowser());
                    break;

                case GRID:
                    driver = WebDriverFactory.getRemoteWebDriver(testParameters.getBrowser(),
                            testParameters.getBrowserVersion(), testParameters.getPlatform(),
                            mobileProperties.getProperty("RemoteUrl"));
                    break;

                case MOBILE:

                    driver = AppiumDriverFactory.getRemoteWebDriver(testParameters);
                    break;

                case PERFECTO:
                    driver = PerfectoDriverFactory.getPerfectoRemoteWebDriverForDesktop(testParameters);
                    break;

                case SAUCELABS:
                    driver = SauceLabsDriverFactory.getSauceRemoteWebDriver(mobileProperties.getProperty("SauceHost"),
                            testParameters.getBrowser(), testParameters.getBrowserVersion(), testParameters.getPlatform(),
                            testParameters);
                    break;

                default:
                    throw new Exception("Unhandled Execution Mode!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
        }
        return driver;
    }

}