package com.cucumbercraft.framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Factory class for creating the {@link WebDriver} object as required
 *
 * @author Cognizant
 */
public class WebDriverFactory {
    private static Properties properties;
    static Logger log = LogManager.getLogger(WebDriverFactory.class);

    private WebDriverFactory() {
        // To prevent external instantiation of this class
    }

    /**
     * Function to return the appropriate {@link WebDriver} object based on the
     * parameters passed
     *
     * @param browser The {@link Browser} to be used for the test execution
     * @return The corresponding {@link WebDriver} object
     */

    public static WebDriver getWebDriver(Browser browser) {
        WebDriver driver = null;
        properties = Settings.getInstance();
        try {
            switch (browser) {
                case CHROME:
                    // Takes the system proxy settings automatically

//				System.setProperty("webdriver.chrome.driver", properties.getProperty("ChromeDriverPath"));
//				WebDriverManager.chromedriver().setup();

                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
//                    options.addArguments("--headless");

                    options.setExperimentalOption("useAutomationExtension", false);
                    options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    options.setExperimentalOption("prefs", prefs);
//                    options.setExperimentalOption("credentials_enable_service", false);
//                    options.setExperimentalOption("profile.password_manager_enabled", false);
//                    WebDriverManager.chromedriver().driverVersion("116.0.5845.111").setup();


                    driver = new ChromeDriver(options);
//                    driver=new ChromeDriver();

                    break;

                case FIREFOX:
                    // Takes the system proxy settings automatically

                    driver = new FirefoxDriver();
                    break;

                case INTERNET_EXPLORER:
                    // Takes the system proxy settings automatically

                    System.setProperty("webdriver.ie.driver", properties.getProperty("InternetExplorerDriverPath"));
                    driver = new InternetExplorerDriver();
                    break;

                case SAFARI:
                    // Takes the system proxy settings automatically

                    driver = new SafariDriver();
                    break;

                default:
                    throw new Exception("Unhandled browser!");
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }

        return driver;
    }

    private static DesiredCapabilities getProxyCapabilities() {
        properties = Settings.getInstance();
        String proxyUrl = properties.getProperty("ProxyHost") + ":" + properties.getProperty("ProxyPort");

        Proxy proxy = new Proxy();
        proxy.setProxyType(ProxyType.MANUAL);
        proxy.setHttpProxy(proxyUrl);
        proxy.setFtpProxy(proxyUrl);
        proxy.setSslProxy(proxyUrl);

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(CapabilityType.PROXY, proxy);

        return desiredCapabilities;
    }

    /**
     * Function to return the {@link RemoteWebDriver} object based on the
     * parameters passed
     *
     * @param browser        The {@link Browser} to be used for the test execution
     * @param browserVersion The browser version to be used for the test execution
     * @param platform       The {@link Platform} to be used for the test execution
     * @param remoteUrl      The URL of the remote machine to be used for the test
     *                       execution
     * @return The corresponding {@link RemoteWebDriver} object
     */
    public static WebDriver getRemoteWebDriver(Browser browser, String browserVersion, Platform platform,
                                               String remoteUrl) {

        properties = Settings.getInstance();
        boolean proxyRequired = Boolean.parseBoolean(properties.getProperty("ProxyRequired"));

        DesiredCapabilities desiredCapabilities = null;
        if (proxyRequired) {
            desiredCapabilities = getProxyCapabilities();
        } else {
            desiredCapabilities = new DesiredCapabilities();
        }

        desiredCapabilities.setBrowserName(browser.getValue());

        if (browserVersion != null) {
            desiredCapabilities.setVersion(browserVersion);
        }
        if (platform != null) {
            desiredCapabilities.setPlatform(platform);
        }

//        desiredCapabilities.setJavascriptEnabled(true); // Pre-requisite for
        // remote execution

        URL url = getUrl(remoteUrl);

        return new RemoteWebDriver(url, desiredCapabilities);
    }

    private static URL getUrl(String remoteUrl) {
        URL url = null;
        try {
            url = new URL(remoteUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();

        }
        return url;
    }

    /**
     * Function to return the {@link RemoteWebDriver} object based on the
     * parameters passed
     *
     * @param browser   The {@link Browser} to be used for the test execution
     * @param remoteUrl The URL of the remote machine to be used for the test
     *                  execution
     * @return The corresponding {@link RemoteWebDriver} object
     */
    public static WebDriver getRemoteWebDriver(Browser browser, String remoteUrl) {
        return getRemoteWebDriver(browser, null, null, remoteUrl);
    }

}